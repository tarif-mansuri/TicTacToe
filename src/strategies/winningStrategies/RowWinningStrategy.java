package strategies.winningStrategies;

import models.Board;
import models.Cell;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy {

    //row --> HashMap(Symbol, Count)
    private Map<Integer, Map<Symbol, Integer>> rowHashMaps = new HashMap<>();
    @Override
    public boolean checkWinner(Move move, Board board) {
        Cell cell = move.getCell();
        int row = cell.getRow();
        Symbol symbol = move.getPlayer().getSymbol();
        if(!rowHashMaps.containsKey(row)) {
            Map<Symbol, Integer> rowMap = new HashMap<>();
            rowMap.put(symbol, 1);
            rowHashMaps.put(row, rowMap);

        }else{
            Map<Symbol, Integer> rowMap = rowHashMaps.get(row);
            if(rowMap.containsKey(symbol)) {
                rowMap.put(symbol, rowMap.get(symbol) + 1);
            }else {
                rowMap.put(symbol, 1);
            }
        }
        if(rowHashMaps.get(row).get(symbol) == board.getDimension()) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void handleUndo(Move move, Board board) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();
        Map<Symbol, Integer> rowMap = rowHashMaps.get(row);
        if(rowMap.containsKey(symbol)) {
            rowMap.put(symbol, rowMap.get(symbol) - 1);
        }
    }
}
