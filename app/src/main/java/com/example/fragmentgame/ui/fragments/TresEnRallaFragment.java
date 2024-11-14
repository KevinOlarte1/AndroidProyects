package com.example.fragmentgame.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fragmentgame.R;
import com.example.fragmentgame.listeners.TresEnRallaListener;
import com.example.fragmentgame.models.ButtonData;
import com.example.fragmentgame.models.TresEnRallaGame;

public class TresEnRallaFragment extends Fragment {

    private interface IonAttachListener{
        Context getContext();
    }

    public TresEnRallaFragment(){super(R.layout.fragment_tres_rallas);}

    private TresEnRallaGame game;
    private String scorePlayer1;
    private String scorePlayer2;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.game = new TresEnRallaGame();
        //TextView txtScorePlayer1 = view.findViewById(R.id.txtScorePlayer1);
        //txtScorePlayer1.setText("asd");
        //TextView txtScorePlayer2 = view.findViewById(R.id.txtScorePalyer2);
        //txtScorePlayer2.setText("asd");
        TresEnRallaListener listener = new TresEnRallaListener(game);
        for (int i = 1; i < 10; i++) {
            String buttonId = "button" + i;
            int resId = getResources().getIdentifier(buttonId, "id", getContext().getPackageName());
            Button button = view.findViewById(resId);
            button.setOnClickListener(listener);

            int row = (i - 1) / 3;
            int col = (i - 1) % 3;
            Log.i("Button" + i, "row : " + row + ", col : " + col);

            button.setTag(new ButtonData(row, col));
        }

        Button but = view.findViewById(R.id.btnPlay);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("asd", "asd");
            }
        });
        //but.setOnClickListener(new View.OnClickListener() {
          //  @Override
          //  public void onClick(View v) {
                //game.startGame();
                //.i("asd", "asd");
                //Log.i(String.valueOf(txtScorePlayer1), String.valueOf(txtScorePlayer2));
                //txtScorePlayer1.setText(String.valueOf(game.getWinsX()));
               // txtScorePlayer2.setText(String.valueOf(game.getWinsY()));
                //resetButtons(view);
           // }
       // });

    }

    private void resetButtons(View view){
        for (int i = 1; i < 10; i++) {
            String buttonId = "button" + i;
            int resId = getResources().getIdentifier(buttonId, "id", getContext().getPackageName());
            Button button = view.findViewById(resId);
            button.setBackgroundResource(R.drawable.battleship_unknown);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}
