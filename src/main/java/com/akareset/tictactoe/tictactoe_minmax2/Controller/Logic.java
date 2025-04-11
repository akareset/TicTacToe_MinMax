package com.akareset.tictactoe.tictactoe_minmax2.Controller;

import com.akareset.tictactoe.tictactoe_minmax2.Model.CellState;
import com.akareset.tictactoe.tictactoe_minmax2.Model.PlayField;

public interface Logic {
    PlayField nextStep(PlayField playField);

    default boolean terminal(PlayField grid){
        for(int i = 0; i<3; i++){
            if(grid.getState(0,i)==(grid.getState(1,i)) &&  grid.getState(0,i)==(grid.getState(2,i)) && !(grid.getState(0,i)==(CellState.NP))){
                return true;
            }
            if(grid.getState(i,0)==(grid.getState(i,1)) &&  grid.getState(i,0)==(grid.getState(i,2)) && !(grid.getState(i,0)==(CellState.NP))){
                return true;
            }
        }
        if (grid.getState(0,0)==(grid.getState(1,1)) &&  grid.getState(0,0)==(grid.getState(2,2)) && !(grid.getState(0,0)==(CellState.NP))){
            return true;
        }
        if (grid.getState(2,0)==(grid.getState(1,1)) &&  grid.getState(2,0)==(grid.getState(0,2)) && !(grid.getState(2,0)==(CellState.NP))){
            return true;
        }
        for(int y =0; y<3; y++){
            for(int x =0; x<3; x++){
                if(grid.getState(x,y)==CellState.NP){
                    return false;
                }
            }
        }
        return true;
    }

}
