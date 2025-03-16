package models;

import exceptions.InvalidBotCountException;
import exceptions.InvalidPlayerCountException;
import exceptions.InvalidStrategyException;
import strategies.winningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private Player winner;
    private Integer nextPlayerIndex;
    private GameState gameState;
    List<WinningStrategy> strategies;

//    public Game(List<Player> players, List<WinningStrategy> strategies, int dimensions) {
//        this.players = players;
//        this.strategies = strategies;
//        this.nextPlayerIndex = 0;
//        this.board = new Board(dimensions);
//        this.moves = new ArrayList<>();
//        this.gameState = GameState.IN_PROGRESS;
//    }
    /**
     * why we should go for the builder pattern ?
     * 1. Dimensions-> N,  players should be-> N-1
     * 2. At least one winning strategy must be passed
     * 3. we have a max limit for Bots to check
     * That's why we must follow builder pattern
     * hence commenting out the above public constructor and making it private to
     * implement builder pattern
     */

    /**
     * Below code is for builder pattern
     */
    private Game(Builder builder) {
        this.players = builder.players;
        this.strategies = builder.strategies;
        this.nextPlayerIndex = 0;
        this.board = new Board(builder.dimensions);
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
    }

    public static class Builder {
        private List<Player> players;
        private List<WinningStrategy> strategies;
        private int dimensions;

        public Builder setPlayers(List<Player> players) throws InvalidBotCountException {
            // Check for bot limit
            int botCount = 0;
            for (Player player : players) {
                if(player.getPlayerType().equals(PlayerType.BOT)) {
                    botCount++;
                    if(botCount > 1) {
                        throw new InvalidBotCountException("Number of bot cant be more than 1");
                    }
                }
            }
            this.players = players;
            return this;
        }

        public Builder setStrategies(List<WinningStrategy> strategies) throws InvalidStrategyException {
            if(strategies== null || strategies.size() <= 1) {
                throw new InvalidStrategyException("At least one strategy must be passed");
            }
            this.strategies = strategies;
            return this;
        }

        public Builder setDimension(int dimensions) {
            this.dimensions = dimensions;
            return this;
        }
        public Game build() throws InvalidStrategyException, InvalidBotCountException, InvalidPlayerCountException {
            validatePlayerCount();
            return new Game(this);
        }

        public void validatePlayerCount() throws InvalidPlayerCountException {
            if(players.size() + 1 !=dimensions ) {
                throw new InvalidPlayerCountException("Number of players should be 1 less than the dimentions");
            }
        }
    }
}

