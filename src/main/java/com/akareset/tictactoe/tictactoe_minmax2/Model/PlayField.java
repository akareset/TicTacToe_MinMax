package com.akareset.tictactoe.tictactoe_minmax2.Model;

import java.util.Arrays;
import java.util.Objects;


public class PlayField { // Tic-tac-toe field, consists of a 3 by 3 array of CellState
    private CellState[][] grid = new CellState[3][3];
    public PlayField(){
        for(int i= 0; i<3; i++){
            for(int j= 0; j<3; j++){
                grid[j][i] = CellState.NP;
            }
        }
    }
    public PlayField(PlayField copy){
        this.grid = new CellState[3][3];
        for(int y= 0; y<3; y++){
            for(int x = 0; x<3; x++){
                grid[y][x] = copy.grid[y][x];
            }
        }
    }

    public void setState(CellState state, int x, int y){
        if(x<0 || x>2){
            throw new IllegalCoordinateException("x coordinate is not valid");
        }
        if(y<0 || y>2){
            throw new IllegalCoordinateException("x coordinate is not valid");
        }
        grid[y][x] = state;
    }
    public CellState getState(int x, int y){
        if(x<0 || x>2){
            throw new IllegalCoordinateException("x coordinate is not valid");
        }
        if(y<0 || y>2){
            throw new IllegalCoordinateException("x coordinate is not valid");
        }
        return grid[y][x];
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int y = 0; y<3; y++){
            for(int x=0; x<3; x++){
                sb.append(getState(x,y).getField());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    public CellState[][] getGrid(){
        return grid;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(this.getClass()!=obj.getClass()){
            return false;
        }
        PlayField other = (PlayField) obj;
        for(int y= 0; y<3; y++){
            for(int x = 0; x<3; x++){
                if(this.grid[y][x]!=other.grid[y][x]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(grid);
    }
}
