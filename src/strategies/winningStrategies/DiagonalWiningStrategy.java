package strategies.winningStrategies;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWiningStrategy implements WinningStrategy {

    Map<Symbol, Integer> mainDia = new HashMap<>();
    Map<Symbol, Integer> auxDia = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, Board board) {
        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();
        Symbol symbol = move.getPlayer().getSymbol();

        if(row == col){
            mainDia.put(symbol, mainDia.getOrDefault(symbol, 0) + 1);
            return mainDia.get(symbol) == board.getDimension();
        }
        if(row + col == board.getDimension()-1){
            auxDia.put(symbol, auxDia.getOrDefault(symbol, 0) + 1);
            return auxDia.get(symbol) == board.getDimension();
        }
        return false;
    }

    @Override
    public void handleUndo(Move move, Board board) {
        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();
        Symbol symbol = move.getPlayer().getSymbol();

        if(row == col){
            mainDia.put(symbol, mainDia.get(symbol) - 1);
            return;
        }
        if(row + col == board.getDimension()-1){
            auxDia.put(symbol, auxDia.get(symbol) - 1);
        }
    }
}
