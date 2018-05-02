package com.josuecubero.gato;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Gato(Costa rican name)/Tic tac toe game.
 */

public class MainActivity extends AppCompatActivity {

    private Button m00, m01, m02, m10, m11, m12, m20, m21, m22; //hidden buttons in the board
    private Token[][] board; //board matrix representation
    private boolean turn; //current turn
    private int player1Score, player2Score;
    private TextView player1Text, player2Text; //text that displays player's score

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize all needed variables
        this.board = new Token[3][3];
        for (int i=0; i<3; i++) { //fill board with "nulls"
            for (int j=0; j<3; j++) {
                this.board[i][j] = Token.NULL;
            }
        }
        //get buttons in orded to know which one has been clicked.
        this.m00 = findViewById(R.id.m00);
        this.m01 = findViewById(R.id.m01);
        this.m02 = findViewById(R.id.m02);
        this.m10 = findViewById(R.id.m10);
        this.m11 = findViewById(R.id.m11);
        this.m12 = findViewById(R.id.m12);
        this.m20 = findViewById(R.id.m20);
        this.m21 = findViewById(R.id.m21);
        this.m22 = findViewById(R.id.m22);
        this.player1Score = 0;
        this.player2Score = 0;
        //get the score text view
        player1Text = findViewById(R.id.player1Text);
        player2Text = findViewById(R.id.player2Text);
        //go!
        Toast.makeText(this, "Go player 1!", Toast.LENGTH_LONG).show();
        this.turn = true;
    }

    /**
     * Function called when the user clicks a board space (hidden button).
     * @param view the hidden button clicked.
     */
    public void onClick(View view){
        int row = 0;
        int column = 0;
        //check which was the clicked button
        if(view == this.m00){
            row = 0;
            column = 0;
        } else if(view == this.m01){
            row = 0;
            column = 1;
        } else if(view == this.m02){
            row = 0;
            column = 2;
        } else if(view == this.m10){
            row = 1;
            column = 0;
        } else if(view == this.m11){
            row = 1;
            column = 1;
        } else if(view == this.m12){
            row = 1;
            column = 2;
        } else if(view == this.m20){
            row = 2;
            column = 0;
        } else if(view == this.m21){
            row = 2;
            column = 1;
        } else if(view == this.m22){
            row = 2;
            column = 2;
        }
        if(this.showToken(row,column)) {
            GameState gameState = this.getGameState(); //check if a player won or game is over
            if(gameState == GameState.GAME_OVER){ //if game is stuck, reset and send toast
                this.showToast(GameState.GAME_OVER);
                (new Handler()).postDelayed(this::resetBoard, 3000); //wait 3 seconds to reset board
            } else if(gameState == GameState.PLAYER1_WIN){ //if player 1 wins, reset board, ++ score, show toast
                this.player1Score++;
                this.player1Text.setText("P1: "+this.player1Score);
                this.showToast(GameState.PLAYER1_WIN);
                (new Handler()).postDelayed(this::resetBoard, 3000);
            } else if(gameState == GameState.PLAYER2_WIN){ //if player 2 wins, reset board, ++ score, show toast
                this.player2Score++;
                this.player2Text.setText("P2: "+this.player2Score);
                this.showToast(GameState.PLAYER2_WIN);
                (new Handler()).postDelayed(this::resetBoard, 3000);
            } else { //no player has won
                this.showToast(GameState.VALID_CLICK);
            }
        } else {
            this.showToast(GameState.INVALID_CLICK);
        }
    }

    /**
     * Shows the token in screen, in case it was empty.
     * @param row the row in which the token will be displayed.
     * @param column the column in which the token will be displayed.
     * @return a boolean depending if the click was done to an empty space or not.
     */
    private boolean showToken(int row, int column){
        boolean validClick = true;
        if(this.board[row][column] == Token.NULL){ //if it is empty go on
            String imageId;
            if(this.turn){ //player1
                this.board[row][column] = Token.RED; //red will be for player 1
                this.turn = false; //change turn
                imageId = "r"+row+column; //get imageId to display it
            } else { //player2
                this.board[row][column] = Token.YELLOW;
                this.turn = true;
                imageId = "y"+row+column;
            }
            ImageView imageView = this.getImage(imageId);
            imageView.setVisibility(View.VISIBLE); //display the image(token)
        } else { //else choose an empty space
            validClick = false;
        }
        return validClick;
    }

    /**
     * Gets the image view for the selected board space.
     * @param imageId the id of the image we are looking for.
     * @return the ImageView of the token.
     */
    private ImageView getImage(String imageId){
        ImageView imageView = null;
        switch (imageId) {
            case "r00":
                imageView = findViewById(R.id.r00);
                break;
            case "r01":
                imageView = findViewById(R.id.r01);
                break;
            case "r02":
                imageView = findViewById(R.id.r02);
                break;
            case "r10":
                imageView = findViewById(R.id.r10);
                break;
            case "r11":
                imageView = findViewById(R.id.r11);
                break;
            case "r12":
                imageView = findViewById(R.id.r12);
                break;
            case "r20":
                imageView = findViewById(R.id.r20);
                break;
            case "r21":
                imageView = findViewById(R.id.r21);
                break;
            case "r22":
                imageView = findViewById(R.id.r22);
                break;
            case "y00":
                imageView = findViewById(R.id.y00);
                break;
            case "y01":
                imageView = findViewById(R.id.y01);
                break;
            case "y02":
                imageView = findViewById(R.id.y02);
                break;
            case "y10":
                imageView = findViewById(R.id.y10);
                break;
            case "y11":
                imageView = findViewById(R.id.y11);
                break;
            case "y12":
                imageView = findViewById(R.id.y12);
                break;
            case "y20":
                imageView = findViewById(R.id.y20);
                break;
            case "y21":
                imageView = findViewById(R.id.y21);
                break;
            case "y22":
                imageView = findViewById(R.id.y22);
                break;
        }
        return imageView;
    }

    /**
     * Returns the state of the game.
     * @return the game state.
     */
    private GameState getGameState(){
        GameState gameState = null;
        if((this.board[0][0]==Token.RED && this.board[0][1]==Token.RED && this.board[0][2]==Token.RED)||
                (this.board[1][0]==Token.RED && this.board[1][1]==Token.RED && this.board[1][2]==Token.RED)||
                (this.board[2][0]==Token.RED && this.board[2][1]==Token.RED && this.board[2][2]==Token.RED)||
                (this.board[0][0]==Token.RED && this.board[0][1]==Token.RED && this.board[0][1]==Token.RED)||
                (this.board[0][0]==Token.RED && this.board[1][0]==Token.RED && this.board[2][0]==Token.RED)||
                (this.board[0][1]==Token.RED && this.board[1][1]==Token.RED && this.board[2][1]==Token.RED)||
                (this.board[0][2]==Token.RED && this.board[1][2]==Token.RED && this.board[2][2]==Token.RED)||
                (this.board[0][0]==Token.RED && this.board[1][1]==Token.RED && this.board[2][2]==Token.RED)||
                (this.board[2][0]==Token.RED && this.board[1][1]==Token.RED && this.board[0][2]==Token.RED)){
            gameState = GameState.PLAYER1_WIN;
        } else if((this.board[0][0]==Token.YELLOW && this.board[0][1]==Token.YELLOW && this.board[0][2]==Token.YELLOW)||
                (this.board[1][0]==Token.YELLOW && this.board[1][1]==Token.YELLOW && this.board[1][2]==Token.YELLOW)||
                (this.board[2][0]==Token.YELLOW && this.board[2][1]==Token.YELLOW && this.board[2][2]==Token.YELLOW)||
                (this.board[0][0]==Token.YELLOW && this.board[0][1]==Token.YELLOW && this.board[0][1]==Token.YELLOW)||
                (this.board[0][0]==Token.YELLOW && this.board[1][0]==Token.YELLOW && this.board[2][0]==Token.YELLOW)||
                (this.board[0][1]==Token.YELLOW && this.board[1][1]==Token.YELLOW && this.board[2][1]==Token.YELLOW)||
                (this.board[0][2]==Token.YELLOW && this.board[1][2]==Token.YELLOW && this.board[2][2]==Token.YELLOW)||
                (this.board[0][0]==Token.YELLOW && this.board[1][1]==Token.YELLOW && this.board[2][2]==Token.YELLOW)||
                (this.board[2][0]==Token.YELLOW && this.board[1][1]==Token.YELLOW && this.board[0][2]==Token.YELLOW)){
            gameState = GameState.PLAYER2_WIN;
        } else {
            boolean full = true;
            for(int i=0; (i<3)&&full; i++){
                for (int j=0; (j<3)&&full; j++) {
                    if(this.board[i][j] == Token.NULL){
                        full = false;
                    }
                }
            }
            if(full) {
                gameState = GameState.GAME_OVER;
            }
        }
        return gameState;
    }

    /**
     * Shows a toast message depending which is the game state.
     * @param gameState the state of the game.
     */
    private void showToast(GameState gameState){
        if(gameState == GameState.VALID_CLICK) {
            if (this.turn) {
                Toast.makeText(this, "Go player 1!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Go player 2!", Toast.LENGTH_SHORT).show();
            }
        } else if(gameState == GameState.INVALID_CLICK) {
            Toast.makeText(this, "Choose an empty space...", Toast.LENGTH_SHORT).show();
        } else if(gameState == GameState.PLAYER1_WIN){
            Toast.makeText(this, "PLAYER 1 WINS!!!", Toast.LENGTH_LONG).show();
        } else if(gameState == GameState.PLAYER2_WIN){
            Toast.makeText(this, "PLAYER 2 WINS!!!", Toast.LENGTH_LONG).show();
        } else if(gameState == GameState.GAME_OVER){
            Toast.makeText(this, "Game over. Restarting...", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Resets the board, scores are kept.
     */
    private void resetBoard(){
        findViewById(R.id.r00).setVisibility(View.INVISIBLE);
        findViewById(R.id.r01).setVisibility(View.INVISIBLE);
        findViewById(R.id.r02).setVisibility(View.INVISIBLE);
        findViewById(R.id.r10).setVisibility(View.INVISIBLE);
        findViewById(R.id.r11).setVisibility(View.INVISIBLE);
        findViewById(R.id.r12).setVisibility(View.INVISIBLE);
        findViewById(R.id.r20).setVisibility(View.INVISIBLE);
        findViewById(R.id.r21).setVisibility(View.INVISIBLE);
        findViewById(R.id.r22).setVisibility(View.INVISIBLE);
        findViewById(R.id.y00).setVisibility(View.INVISIBLE);
        findViewById(R.id.y01).setVisibility(View.INVISIBLE);
        findViewById(R.id.y02).setVisibility(View.INVISIBLE);
        findViewById(R.id.y10).setVisibility(View.INVISIBLE);
        findViewById(R.id.y11).setVisibility(View.INVISIBLE);
        findViewById(R.id.y12).setVisibility(View.INVISIBLE);
        findViewById(R.id.y20).setVisibility(View.INVISIBLE);
        findViewById(R.id.y21).setVisibility(View.INVISIBLE);
        findViewById(R.id.y22).setVisibility(View.INVISIBLE);
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                this.board[i][j] = Token.NULL;
            }
        }
        Toast.makeText(this, "Go player 1!", Toast.LENGTH_LONG).show();
        this.turn = true;
    }

    /**
     * Resets all the game.
     * @param view the reset button that calls it.
     */
    public void resetAll(View view){
        this.board = new Token[3][3];
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                this.board[i][j] = Token.NULL;
            }
        }
        this.m00 = findViewById(R.id.m00);
        this.m01 = findViewById(R.id.m01);
        this.m02 = findViewById(R.id.m02);
        this.m10 = findViewById(R.id.m10);
        this.m11 = findViewById(R.id.m11);
        this.m12 = findViewById(R.id.m12);
        this.m20 = findViewById(R.id.m20);
        this.m21 = findViewById(R.id.m21);
        this.m22 = findViewById(R.id.m22);
        this.player1Score = 0;
        this.player2Score = 0;
        this.player1Text.setText(R.string.player_1_0);
        this.player2Text.setText(R.string.player_2_0);
        Toast.makeText(this, "Go player 1!", Toast.LENGTH_LONG).show();
        this.turn = true;
    }
}
