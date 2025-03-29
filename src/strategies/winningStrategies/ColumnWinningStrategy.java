package strategies.winningStrategies;

import models.Board;
import models.Cell;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy {

    //row --> HashMap(Symbol, Count)
    private Map<Integer, Map<Symbol, Integer>> colHashMaps = new HashMap<>();
    @Override
    public boolean checkWinner(Move move, Board board) {
        Cell cell = move.getCell();
        int col = cell.getColumn();
        Symbol symbol = move.getPlayer().getSymbol();
        if(!colHashMaps.containsKey(col)) {
            Map<Symbol, Integer> rowMap = new HashMap<>();
            rowMap.put(symbol, 1);
            colHashMaps.put(col, rowMap);

        }else{
            Map<Symbol, Integer> rowMap = colHashMaps.get(col);
            if(rowMap.containsKey(symbol)) {
                rowMap.put(symbol, rowMap.get(symbol) + 1);
            }else {
                rowMap.put(symbol, 1);
            }
        }
        if(colHashMaps.get(col).get(symbol) == board.getDimension()) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void handleUndo(Move move, Board board) {
        int col = move.getCell().getColumn();
        Symbol symbol = move.getPlayer().getSymbol();
        Map<Symbol, Integer> rowMap = colHashMaps.get(col);
        if(rowMap.containsKey(symbol)) {
            rowMap.put(symbol, rowMap.get(symbol) - 1);
        }
    }
}
