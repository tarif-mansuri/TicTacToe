package strategies.botPlayingStrategies;

import models.Board;
import models.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board board);
}
