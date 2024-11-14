package com.example.fragmentgame.listeners;


import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fragmentgame.R;
import com.example.fragmentgame.models.ButtonData;
import com.example.fragmentgame.models.TresEnRallaGame;

public class TresEnRallaListener implements View.OnClickListener{

    private TresEnRallaGame game;
    private char[][] board;
    private boolean pintar = true;

    public TresEnRallaListener(TresEnRallaGame game){
        this.game = game;
    }
    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        ButtonData data = (ButtonData) button.getTag();
        TresEnRallaGame.GameState resultState = game.playTurn(data.getRow(), data.getCol());
        if(resultState == TresEnRallaGame.GameState.PLAYER_O_TURN || resultState == TresEnRallaGame.GameState.WINNER_X){
            if(pintar)
                button.setBackgroundResource(R.drawable.tictactoe_x);
            if (resultState == TresEnRallaGame.GameState.WINNER_X)
                pintar = false;

        } else if (resultState == TresEnRallaGame.GameState.PLAYER_X_TURN || resultState == TresEnRallaGame.GameState.WINNER_O) {

            if (pintar)
                button.setBackgroundResource(R.drawable.tictactoe_o);
            if (resultState == TresEnRallaGame.GameState.WINNER_O)
                pintar = false;

        }

    }

}
