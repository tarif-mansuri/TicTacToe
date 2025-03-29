import controllers.GameController;
import exception.BotValidationException;
import exception.InvalidMoveException;
import exception.NoStrategyException;
import exception.WrongDimentionsException;
import models.*;
import strategies.winningStrategies.ColumnWinningStrategy;
import strategies.winningStrategies.DiagonalWiningStrategy;
import strategies.winningStrategies.RowWinningStrategy;
import strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws BotValidationException, WrongDimentionsException, NoStrategyException, InvalidMoveException {
        Player p1 = new Player("Alpha", new Symbol('@'), PlayerType.HUMAN);
        Player p2 = new Player("Star", new Symbol('*'), PlayerType.HUMAN);
        List<Player> players = List.of(p1, p2);
        List<WinningStrategy> strategies = new ArrayList<>();
        strategies.add(new RowWinningStrategy());
        strategies.add(new ColumnWinningStrategy());
        strategies.add(new DiagonalWiningStrategy());
        GameController gc = new GameController();
        Game game = gc.startGame(players, strategies);
        Scanner scanner = new Scanner(System.in);
        while(gc.getGameState(game) == GameState.INPROGRESS){
            gc.makeMove(game);
            gc.printBoard(game);
            if(gc.getGameState(game) == GameState.ENDED){
                break;
            }
            System.out.println("Want to undo ? press Y or N");
            char undo = scanner.next().charAt(0);
            if(undo == 'Y' || undo == 'y'){
                gc.unDo(game);
                gc.printBoard(game);
            }

        }
        if(gc.getGameState(game) == GameState.ENDED){
            System.out.println("Game Ended, " + gc.getWinner(game).getName()+" Won the game");
        }else{
            System.out.println("Game was a draw");
        }
    }
}