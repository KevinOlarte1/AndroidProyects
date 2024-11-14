package com.example.fragmentgame.models.parsers;

import android.content.Context;
import android.util.Log;

import com.example.fragmentgame.R;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WordParser {

    public static List<String> parserWords(Context context) {
        List<String> words = new ArrayList<>();

        // Abrir el archivo en res/raw
        try (InputStream is = context.getResources().openRawResource(R.raw.words_es);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.trim()); // Añadir cada línea como una palabra
            }
        } catch (IOException e) {
            Log.e("Error", Objects.requireNonNull(e.getMessage()));
        }

        return words;
    }
}
