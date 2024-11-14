package com.example.fragmentgame.models;

public class ButtonData {

    private final int row;
    private final int col;

    public ButtonData(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
