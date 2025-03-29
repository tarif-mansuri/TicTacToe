package factory;

import models.Board;
import models.BotDifficultyLevel;
import strategies.botPlayingStrategies.BotPlayingStrategy;
import strategies.botPlayingStrategies.EasyBotStrategy;
import strategies.botPlayingStrategies.HardBotStrategy;
import strategies.botPlayingStrategies.MediumBotStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel level) {
        if(level == BotDifficultyLevel.EASY) {
            return new EasyBotStrategy();
        }else if(level == BotDifficultyLevel.MEDIUM) {
            return new MediumBotStrategy();
        }else{
            return new HardBotStrategy();
        }
    }
}
