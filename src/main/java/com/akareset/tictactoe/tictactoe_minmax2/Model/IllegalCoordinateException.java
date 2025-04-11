package com.akareset.tictactoe.tictactoe_minmax2.Model;

public class IllegalCoordinateException extends RuntimeException{
    public IllegalCoordinateException(String message){
        super(message);
    }
}
