package controllers;

import exception.BotValidationException;
import exception.InvalidMoveException;
import exception.NoStrategyException;
import exception.WrongDimentionsException;
import models.Game;
import models.GameState;
import models.Player;
import strategies.winningStrategies.WinningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(List<Player> players, List<WinningStrategy> strategies) throws BotValidationException, NoStrategyException, WrongDimentionsException {
        return Game.getInstance().setDimension(players.size()+1).setPlayers(players).setStrategies(strategies).build();
    }

    public GameState makeMove(Game game) throws InvalidMoveException {
        return game.makeMove();
    }

    public void printBoard(Game game){
        game.getBoard().printBoard();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }

    public GameState getGameState(Game game) {
        return game.getGameState();
    }
    public void unDo(Game game){
        game.undo();
    }
}
