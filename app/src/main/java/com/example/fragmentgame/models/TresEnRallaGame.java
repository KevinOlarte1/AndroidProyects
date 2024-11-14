package com.example.fragmentgame.models;

import android.util.Log;

public class TresEnRallaGame {

    private static  final char DEFAULT_SIMBOL_PLAYERX = 'X';
    private static  final char DEFAULT_SIMBOL_PLAYERO = 'O';
    private static  final char DEFAULT_SIMBOL_DRAW = '=';
    private static final  char DEFAULT_EMPTY_SIMBOL = '-';

    public enum GameState{
        PLAYER_X_TURN(DEFAULT_SIMBOL_PLAYERX), PLAYER_O_TURN(DEFAULT_SIMBOL_PLAYERO), WINNER_X(DEFAULT_SIMBOL_PLAYERX), WINNER_O(DEFAULT_SIMBOL_PLAYERO), DRAW(DEFAULT_SIMBOL_DRAW),
        ERROR('E');

        private char c;
        private GameState(char c){
            this.c = c;
        }

        public char getC() {
            return c;
        }
    };
    private char[][] board;
    private GameState currentState;

    private int winsX;
    private int winsY;

    public TresEnRallaGame(){
        this.board = new char[3][3];
        resetBoard();
        this.currentState = GameState.PLAYER_X_TURN;
        this.winsX = 0;
        this.winsY = 0;
    }

    /**
     * Metodo para reiniciar la partida, los puntos de victorias se mantienen guardado.
     */
    public void startGame(){
        resetBoard();
        this.currentState = GameState.PLAYER_X_TURN;
    }

    /**
     * Metodo para jugar al juego decir donde se quiere introducir la figura.
     * @param row fila seleccionada para introducir la jugada
     * @param col columna seleccionada para introducir la jugada.
     * @return devuelve un estado del juego para saber el estado de la partida
     */
    public GameState playTurn(int row, int col){
            Log.i(currentState.toString(), currentState.toString());
            StringBuilder mapa = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                mapa.append(" ").append(board[i][j]).append(" ");
            }
            mapa.append("\n");
        }
        Log.i("Mapa", mapa.toString());
            if(currentState == GameState.PLAYER_X_TURN || currentState == GameState.PLAYER_O_TURN){
                if(movimentValid(row, col)){
                    board[row][col] = currentState.getC();
                    checkGameState();
                }
                else{
                    Log.i("Error", "Position X :" + row + ", Y : " + col + "No Posible");

                    return GameState.ERROR;
                }
            }
            return currentState;


    }

    public GameState getCurrentState() {
        return currentState;
    }

    public int getWinsX() {
        return winsX;
    }

    public int getWinsY() {
        return winsY;
    }

    /**
     * Actualizar el estado del juego.
     */
    private void  checkGameState(){

        if(checkWin()){
            if(currentState == GameState.PLAYER_X_TURN)
                currentState = GameState.WINNER_X;
            else
                currentState = GameState.WINNER_O;
        }
        else if(isboardFull()){
            currentState = GameState.DRAW;
        }
        else{
            currentState = currentState == GameState.PLAYER_X_TURN? GameState.PLAYER_O_TURN : GameState.PLAYER_X_TURN;
        }

    }

    /**
     * Comprobar si el jugador acual es el ganador
     * @return booleano combrobando si es verdad
     */
    private boolean checkWin(){
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentState.getC() && board[i][1] == currentState.getC() && board[i][2] == currentState.getC())
                return true;
            if (board[0][i] == currentState.getC() && board[1][i] == currentState.getC() && board[2][i] == currentState.getC())
                return true;
        }
        if (board[0][0] == currentState.getC() && board[1][1] == currentState.getC() && board[2][2] == currentState.getC())
            return true;
        if (board[0][2] == currentState.getC() && board[1][1] == currentState.getC() && board[2][0] == currentState.getC())
            return true;
        return false;
    }

    /**
     * Metodo para comprobar si esta lleno la matriz
     * @return devuelve un booleano verificando eso.
     */
    private boolean isboardFull(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == DEFAULT_EMPTY_SIMBOL)
                    return false;
            }
        }
        return true;
    }

    /**
     * Metodo para comprobar si el movimiento es valido.
     * @param row fila de la matriz
     * @param col columna de la matriz
     * @return un buleano si la fila o colummna esta dentro del rango y esa posicion no esta siendo usada por X o O.
     */
    private boolean movimentValid(int row, int col){
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-';
    }

    /**
     * Metodo para resetear a default el tablero.
     */
    private void resetBoard(){
        for (int i = 0; i <  board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = DEFAULT_EMPTY_SIMBOL;
            }
        }
    }

}
