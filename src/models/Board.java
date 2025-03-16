package models;

import java.util.List;

public class Board {
    private Integer dimension;
    List<List<Cell>> board;

    public Board(Integer dimension) {
        this.dimension = dimension;
    }

}
