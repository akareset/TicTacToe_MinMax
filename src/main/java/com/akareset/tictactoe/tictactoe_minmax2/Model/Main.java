package com.akareset.tictactoe.tictactoe_minmax2.Model;

public class Main {
    public static void main(String[] args) {
        PlayField playField = new PlayField();
        System.out.println(playField.toString());
        playField.setState(CellState.X, 1,1);
        System.out.println(playField.toString());
    }
}
