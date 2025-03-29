package models;

import java.util.Scanner;

public class Player {
    private String name;
    private Symbol symbol;
    private PlayerType playerType;
    Scanner scanner = new Scanner(System.in);

    public Player(String name, Symbol symbol, PlayerType playerType) {
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public Move makeMove(){
        System.out.println("Please enter the row number");
        int row = scanner.nextInt();
        System.out.println("Please enter the column number");
        int column = scanner.nextInt();
        return new Move(this, new Cell(row, column));
    }
}
