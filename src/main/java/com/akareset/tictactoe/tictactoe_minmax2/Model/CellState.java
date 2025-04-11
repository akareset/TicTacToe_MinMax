package com.akareset.tictactoe.tictactoe_minmax2.Model;

public enum CellState {
    X("x"),O("o"), NP(" ");
    private String s;
    private CellState(String s){
        this.s = s;
    }
    public String getField(){
        return this.s;
    }

}
