package com.akareset.tictactoe.tictactoe_minmax2.Controller;

import com.akareset.tictactoe.tictactoe_minmax2.Model.CellState;
import com.akareset.tictactoe.tictactoe_minmax2.Model.PlayField;

import java.util.ArrayList;
import java.util.List;


public class MinMaxLogic implements Logic{
    private boolean userTurn; // true,if it's user's turn and false if computer's
    private CellState userSign;
    //PlayField playField;
    CellState computerSign;
    private int move=1;

    public MinMaxLogic(CellState userSign){
        userTurn = true;
        //this.playField = playField;
        this.userSign = userSign;
        computerSign = userSign.equals(CellState.X) ? CellState.O:CellState.X;
    }
    public void setTurn(boolean turn){
        this.userTurn = turn;
    }
    public boolean getTurn(){
        return userTurn;
    }
    public CellState getUserSign() {
        return userSign;
    }

    public void setUserSign(CellState userSign) {
        this.userSign = userSign;
    }

    @Override
    public PlayField nextStep(PlayField playField) {
        int best_value = Integer.MIN_VALUE;
        PlayField best_move = new PlayField();
        for(PlayField succ : successors(playField, computerSign)){
            int m = min(succ, userSign,1);
            m+=evaluatePlayField(succ);
            if(m>best_value){
                best_value = m;
                best_move = succ;
            }
        }
        return new PlayField(best_move);
    }
    private int max(PlayField grid, CellState player, int depth){
        if(terminal(grid)){
            return utility(grid)==10 ? utility(grid) + 9-depth: utility(grid); // some kind of heuristic - try to win with the minimal amount if steps
        }
        int m = Integer.MIN_VALUE;
        for(PlayField succ : successors(grid, player)){
            m = Math.max(m, min(succ, player.equals(CellState.X)? CellState.O:CellState.X, depth+1));
        }
        return m;
    }
    private int min(PlayField grid, CellState player, int depth){
        if(terminal(grid)){
            return utility(grid)==10 ? utility(grid) + 8-depth: utility(grid);
        }
        int m = Integer.MAX_VALUE;
        for(PlayField succ : successors(grid, player)){
            m = Math.min(m, max(succ, player.equals(CellState.X) ? CellState.O:CellState.X, depth+1));
        }
        return m;
    }

    public int utility(PlayField grid){
        for(int i = 0; i<3; i++){
            if(grid.getState(0,i)==grid.getState(1,i) && grid.getState(0,i)==grid.getState(2,i)){
               if(grid.getState(0,i)==computerSign){
                   return 10;
               }
               else return -10;
            }
            if(grid.getState(i,0)==grid.getState(i,1) && grid.getState(i,0)==grid.getState(i,2)){
                if(grid.getState(i,0)==computerSign){
                    return 10;
                }
                else return -10;
            }
        }
        if (grid.getState(0,0)==grid.getState(1,1) && grid.getState(0,0)==grid.getState(2,2)){
            if(grid.getState(0,0)==computerSign){
                return 10;
            }
            else return -10;
        }
        if (grid.getState(2,0)==grid.getState(1,1) && grid.getState(2,0)==grid.getState(0,2)){
            if(grid.getState(2,0)==computerSign){
                return 10;
            }
            else return -10;
        }
        return 0;
    }
    public List<PlayField> successors(PlayField grid, CellState player){
        List<PlayField> list = new ArrayList<>();
        for(int y = 0; y<3;y++){
            for(int x = 0; x<3; x++){
               if (grid.getState(x,y)==CellState.NP) {
                   PlayField playField_succ = new PlayField(grid);
                   playField_succ.setState(player,x,y);
                    list.add(playField_succ);
                }
            }
        }
        return list;
    }
    public int evaluatePlayField(PlayField grid){ // можно добавить так же в передачу параметра - номер хода
        int num = 0;
        int score = 0;
        for(int y = 0; y<3;y++){
            for(int x = 0; x<3; x++){
                if(grid.getState(x,y)==CellState.NP){
                    num++;
                }
            }
        }
        if(grid.getState(1,1)==computerSign ) score+=2;
        if(grid.getState(0,0)==computerSign && grid.getState(2,2)==computerSign || grid.getState(2,0)==computerSign && grid.getState(0,2)==computerSign){
            if(num<=4){
                score+=4;
            }
        }
        for(int y = 0; y<3;y++){
            List<CellState> line = new ArrayList<>();
            line.add(grid.getState(0,y));
            line.add(grid.getState(1,y));
            line.add(grid.getState(2,y));
            score += evaluateLine(line);
        }
        for(int x = 0; x<3;x++){
            List<CellState> line = new ArrayList<>();
            line.add(grid.getState(x,0));
            line.add(grid.getState(x,1));
            line.add(grid.getState(x,2));
            score += evaluateLine(line);
        }
        score+=evaluateLine(new ArrayList<>(){{
            add(grid.getState(0,0));
            add(grid.getState(1,1));
            add(grid.getState(2,2));
        }});
        score+=evaluateLine(new ArrayList<>(){{
            add(grid.getState(2,0));
            add(grid.getState(1,1));
            add(grid.getState(0,2));
        }});
        return score;
    }
    private int evaluateLine(List<CellState> line){
        if(line.stream().filter(state -> state==computerSign).count()==1 && line.stream().filter(state -> state==CellState.NP).count()==2){
            return 0;
        }
        return line.stream().map(state -> {
            if(state==computerSign) return 3;
            else if(state==userSign) return -3;
            else return 0;
        }).reduce(Integer::sum).get();
    }
    /*public PlayField getPlayField(){
        return this.playField;
    }*/
}
