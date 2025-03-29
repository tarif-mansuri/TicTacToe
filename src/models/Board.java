package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int dimension;
    List<List<Cell>> board;
    public Board(int dimension) {
        this.dimension = dimension;
        this.board = new ArrayList<>(dimension);
        for (int i = 0; i < this.dimension; i++) {
            this.board.add(new ArrayList<>(dimension));
            for (int j = 0; j < this.dimension; j++) {
                this.board.get(i).add(new Cell(i, j));
            }
        }

    }
    public void printBoard() {
        for(List<Cell> row : this.board) {
            for(Cell cell : row) {
                if(cell.getCellState().equals(CellState.EMPTY)){
                    System.out.print("|   |");
                }else{
                    System.out.print("| "+cell.getSymbol().getCharA()+" |");
                }
            }
            System.out.println();
        }
    }

    public int getDimension() {
        return dimension;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }
}
