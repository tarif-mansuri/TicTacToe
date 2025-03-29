package models;

import factory.BotPlayingStrategyFactory;
import strategies.botPlayingStrategies.BotPlayingStrategy;

public class Bot extends Player {
    private BotDifficultyLevel difficultyLevel;
    private BotPlayingStrategy strategy;
    public Bot(String name, Symbol symbol, BotDifficultyLevel difficultyLevel) {
        super(name, symbol, PlayerType.BOT);
        this.difficultyLevel = difficultyLevel;
        //factory pattern to create object of BotPlayingStrategy
        this.strategy = BotPlayingStrategyFactory.getBotPlayingStrategy(difficultyLevel);
    }

}
