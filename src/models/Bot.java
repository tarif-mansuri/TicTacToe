package models;

import strategies.botPlayingStrategy.BotPlayingStrategy;

public class Bot extends Player {
    private BotDifficultiLevel difficultiLevel;
    private BotPlayingStrategy strategy;
    public Bot(String name, Symbol symbol, BotDifficultiLevel difficultiLevel, BotPlayingStrategy strategy) {
        super(name, symbol, PlayerType.BOT);
        this.difficultiLevel = difficultiLevel;
        this.strategy = strategy;
    }
}

/* Bot should have a family of algorithms depending on difficulty level. it will make move differently */