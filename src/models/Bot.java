package models;

import strategies.botPlayingStrategy.BotPlayingStrategy;

public class Bot extends Player {
    private BotDifficultiLevel difficultiLevel;
    private BotPlayingStrategy strategy;
}

/* Bot should have a family of algorithms depending on difficulty level. it will make move differently */