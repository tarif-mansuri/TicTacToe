import exceptions.InvalidBotCountException;
import exceptions.InvalidPlayerCountException;
import exceptions.InvalidStrategyException;
import models.Game;
import models.Player;
import models.PlayerType;
import models.Symbol;
import strategies.winningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InvalidBotCountException, InvalidPlayerCountException, InvalidStrategyException {
        startGame();
    }

    public static void startGame() throws InvalidBotCountException, InvalidStrategyException, InvalidPlayerCountException {
        Player player1 = new Player("A", new Symbol('@'), PlayerType.HUMAN);
        Player player2 = new Player("B", new Symbol('*'), PlayerType.BOT);
        Player player3 = new Player("C", new Symbol('%'), PlayerType.HUMAN);
        List<Player> players = List.of(player1, player2, player3);

        List<WinningStrategy> strategy = new ArrayList<>();
        Game game = Game.getBuilder().setDimension(4).setPlayers(players).setStrategies(strategy).build();
    }
}