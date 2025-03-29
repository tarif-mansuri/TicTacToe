package models;

import exception.BotValidationException;
import exception.InvalidMoveException;
import exception.NoStrategyException;
import exception.WrongDimentionsException;
import strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private Player winner;
    private int nextPlayerIndex;
    private GameState gameState;
    private List<WinningStrategy> strategies;

    private Game(Builder builder) {
        this.players = builder.players;
        this.moves = new ArrayList<>();
        this.nextPlayerIndex = 0;
        this.board = new Board(builder.dimension);
        this.gameState = GameState.INPROGRESS;
        this.strategies = builder.strategies;
    }

    public static Builder getInstance() {
        return new Builder();
    }

    public Board getBoard() {
        return board;
    }

    public GameState makeMove() throws InvalidMoveException {
        //Get the player using player index
        Player currentPlayer = players.get(nextPlayerIndex);

        System.out.println("It is " + currentPlayer.getName()+ "'s turn");
        //Ask the player to choose the cell where he wants to make move
        Move currentMove = currentPlayer.makeMove();
        //To validate current move
        //if cell is empty, if not out of boundaries
        if(!isValidateMove(currentMove)){
            throw new InvalidMoveException("Move is invalid");
        }
        //execute it on the board,
        int row = currentMove.getCell().getRow();
        int column = currentMove.getCell().getColumn();
        Cell cell = board.getBoard().get(row).get(column);
        cell.setCellState(CellState.FILLED);
        cell.setSymbol(currentPlayer.getSymbol());
        //update index of next player
        nextPlayerIndex = (nextPlayerIndex + 1) % players.size();
        //store move
        moves.add(currentMove);
        //check if player has won the game
        if(checkWinner(currentMove)){
            gameState = GameState.ENDED;
            winner = currentPlayer;
        }else if(moves.size() == board.getDimension() * board.getDimension()){
            //draw --> count total number of moves are equal to n*n
            // need to store moves for efficiency
            gameState = GameState.DRAW;
        }
        return gameState;
    }

    public boolean isValidateMove(Move dummyMove) {
        int row = dummyMove.getCell().getRow();
        int column = dummyMove.getCell().getColumn();
        if(row<0 || row>=board.getDimension() || column<0 || column>=board.getDimension()) {
            return false;
        }
        return board.getBoard().get(row).get(column).getCellState() == CellState.EMPTY;
    }

    public boolean checkWinner(Move move){
        for(WinningStrategy strategy : strategies){
            if(strategy.checkWinner(move, board)){
                return true;
            }
        }
        return false;
    }

    public Player getWinner() {
        return winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public static class Builder {
        private List<Player> players;
        private int dimension;
        private List<WinningStrategy> strategies;

        public Builder setPlayers(List<Player> players) throws BotValidationException {
            //validate bot count, should be less than or equal to one
            int botCount = 0;
            for (Player player : players) {
                if(player.getPlayerType().equals(PlayerType.BOT)) {
                    botCount++;
                    if(botCount > 1) {
                        throw new BotValidationException("maximum one Bot is allowed");
                    }
                }
            }
            this.players = players;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setStrategies(List<WinningStrategy> strategies) throws NoStrategyException {
            if(strategies == null || strategies.isEmpty()) {
                throw new NoStrategyException("At least one strategy is required");
            }
            this.strategies = strategies;
            return this;
        }
        public Game build() throws WrongDimentionsException {
            validateDimension();
            return new Game(this);
        }

        void validateDimension() throws WrongDimentionsException {
            if(this.dimension != this.players.size()+1) {
                throw new WrongDimentionsException("Player count should be one lesser than Diamension");
            }
        }

    }

    public void undo(){
        Move lastMoves = moves.getLast();
        moves.remove(lastMoves);
        //update the cell state
        Cell lastCell = lastMoves.getCell();
        int row = lastCell.getRow();
        int column = lastCell.getColumn();
        board.getBoard().get(row).get(column).setCellState(CellState.EMPTY);

        //update symbol count in rows/column/diag etc
        for(WinningStrategy strategy : strategies){
            strategy.handleUndo(lastMoves, board);
        }
        //update nextPlayerIndex
        nextPlayerIndex = (nextPlayerIndex - 1) % players.size();
        if(nextPlayerIndex == -1){
            nextPlayerIndex = 0;
        }
    }

}
