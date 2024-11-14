package com.example.fragmentgame.models;

import java.util.ArrayList;
import  java.util.List;
import java.util.Random;

public class AhorcadoGame {

    private final static int DEFAULT_LIVES = 6;
    private final Random rnd;
    private String secretWord; //Palabra secreta
    private int life; //Vidas restantes
    private List<Character> lettersNo; //Letras que no son
    private Character[] word; //Palabra descubierta

    private List<String> wordsRnd; //Diccionario donde obtendremos

    private boolean start;
    private result resultActual;

    public enum result {
        WINNER, LOOSER, CONTINUE;
    }




    public AhorcadoGame(List<String> words){
        this.life = DEFAULT_LIVES;
        this.rnd = new Random();
        this.start = false;
        this.wordsRnd = words;
        this.lettersNo = new ArrayList<>();
    }

    /**
     * Metodo para empezar el juego, formatea todos los parametros necesarios del juego
     */
    public void startGame(){
        this.start = true;
        this.secretWord = getSecretWord();
        this.word = new Character[secretWord.length()];
        this.resultActual = result.CONTINUE;
        resetParametres();
    }

    /**
     * Metodo para poner la letra pero antes se tiene que haber iniciado la partida
     * @param c letra ingresada
     * @return devuelve un enumerador
     */
    public result putLetter(char c){
        if (start){
            boolean coincidencia = false;
            for (int i = 0; i < this.secretWord.length(); i++) {
                if (c == this.secretWord.charAt(i)){
                    coincidencia = true;
                    this.word[i] = c;
                }

            }

            return actualizarDatos(coincidencia, c);
        }
        return resultActual;
    }

    public int getLife() {
        return life;
    }

    public Character[] getWord() {
        return word;
    }

    public List<Character> getLettersNo() {
        return lettersNo;
    }

    public result getResultActual() {
        return resultActual;
    }

    /**
     * Actualizar los datos y devolver un resultado, ganador, perdedor, continuar
     * @param letraAcertada acerto la letra
     * @param c letra acertada
     * @return devuelve un estado resultado.
     */
    private result actualizarDatos(boolean letraAcertada, char c){
        if (!letraAcertada){
            this.life--;
            this.lettersNo.add(c);
            if(life == 0){
                this.start = false;
                this.resultActual =  result.LOOSER;
                return result.LOOSER;
            }
        }
        else{
            boolean win = true;
            for (Character c2: word) {
                if (c2 == '_'){
                    win = false;
                    break;
                }

            }
            if (win){
                this.start = false;
                this.resultActual =  result.WINNER;
                return result.WINNER;
            }

        }
        this.resultActual = result.CONTINUE;
        return result.CONTINUE;
    }
    private void resetParametres(){
        for (int i = 0; i < secretWord.length(); i++) {
            this.word[i] = '_';
        }
        this.lettersNo.clear();
        this.life = 6;
    }

    private String getSecretWord() {
        return wordsRnd.get(rnd.nextInt(wordsRnd.size()));
    }




}
