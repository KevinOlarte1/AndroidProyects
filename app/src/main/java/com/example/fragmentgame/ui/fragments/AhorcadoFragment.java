package com.example.fragmentgame.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fragmentgame.R;
import com.example.fragmentgame.models.AhorcadoGame;

import java.util.List;

public class AhorcadoFragment extends Fragment {

    public interface IOnAttachListener {
        List<String> getWords();
    }

    private Character[] word;
    private List<Character> lettersIncorrect;
    private String lettersIncorrectString;
    private String intentos;
    private AhorcadoGame game;

    //private AhorcadoGame ahorcadoGame;

    public AhorcadoFragment(){ super(R.layout.fragment_ahorcado);}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView txtIntentos = view.findViewById(R.id.txtIntentos);
        txtIntentos.setText(String.valueOf("Intentos: " + intentos));

        ImageView imgAhorcado = view.findViewById(R.id.imgAhorcado);
        changeImage(imgAhorcado);


        TextView txtLetrasIncorrectas = view.findViewById(R.id.txtLetrasIncorrectas);
        txtLetrasIncorrectas.setText(lettersIncorrectString);

        LinearLayout wordLayout = view.findViewById(R.id.wordLayout);
        resetLayoutWord(wordLayout);

        EditText inputLetras = view.findViewById(R.id.inputLetra);
        Button buttonPlay = view.findViewById(R.id.btnPlay);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ingreo = inputLetras.getText().toString();
                Log.i("Click", "Clicked ");
                AhorcadoGame.result result = game.getResultActual();
                if(result != AhorcadoGame.result.CONTINUE) {
                    Log.i("Ha ganado o a perdido", result.toString());
                    game.startGame();
                    word = game.getWord();
                    resetLayoutWord(wordLayout);
                    lettersIncorrect = game.getLettersNo();
                    intentos = String.valueOf(game.getLife());
                    changeImage(imgAhorcado);
                    lettersIncorrectString = convertCharSequence();
                    txtLetrasIncorrectas.setText(lettersIncorrectString);
                    txtIntentos.setText(String.valueOf("Intentos: " + intentos));
                }
                else if (!ingreo.isEmpty()){
                    Log.i("Ingreso: " , ingreo);
                    AhorcadoGame.result result1 = game.putLetter(ingreo.charAt(0));
                    intentos = String.valueOf(game.getLife());
                    Log.i("IntentosRestantes:" , intentos);
                    changeImage(imgAhorcado);
                    txtIntentos.setText(String.valueOf("Intentos: " + intentos));
                    changeWord(wordLayout);
                    lettersIncorrectString = convertCharSequence();
                    txtLetrasIncorrectas.setText(lettersIncorrectString);
                    Log.i("Erroneas", lettersIncorrectString);

                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < word.length; i++) {
                        sb.append(word[i]);
                    }
                    Log.i("Palabra Actual (Long : " + word.length, sb.toString());

                    if(result1 == AhorcadoGame.result.WINNER){
                        //TODO:Poner un TextView Ganador!!
                    }
                    else{
                        //TODO: Lo mismo pero al reves
                    }

                }
            }
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        AhorcadoFragment.IOnAttachListener attachListener = (AhorcadoFragment.IOnAttachListener) context;
        this.game = new AhorcadoGame(attachListener.getWords());
        this.game.startGame();
        this.word = this.game.getWord();
        this.lettersIncorrect = this.game.getLettersNo();
        this.intentos = String.valueOf(this.game.getLife());
    }

    public void changeWord(LinearLayout lineal){
        int childCount = lineal.getChildCount();
        Log.i("Hijos", String.valueOf(childCount));
        for (int i = 0; i < word.length; i++) {
            View view = lineal.getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setText(String.valueOf(word[i]));
            }
        }
    }
    private void resetLayoutWord(LinearLayout lineal){

        lineal.removeAllViews();
        for (int i = 0; i < word.length; i++) {
            TextView letterView = new TextView(lineal.getContext());
            letterView.setText("_");
            letterView.setTextSize(24);
            letterView.setPadding(5,0,5,0);

            lineal.addView(letterView);
        }
    }

    private void  changeImage(ImageView img){
        int resourceId = getResources().getIdentifier("hangman_" + this.intentos, "drawable", getContext().getPackageName());

        // Establecer la imagen en el ImageView si el recurso existe
        if (resourceId != 0) { // Verificar si el recurso existe
            img.setImageResource(resourceId);
        } else {
            // Manejo del caso en que el recurso no existe
            Log.e("ImageError", "Recurso de imagen no encontrado para " + "hangman_" + this.intentos);
        }
    }

    private String convertCharSequence(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.lettersIncorrect.size(); i++) {
            sb.append(" ");
            sb.append(this.lettersIncorrect.get(i));
        }
        return sb.toString();
    }
}
