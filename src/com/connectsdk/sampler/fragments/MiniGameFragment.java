package com.connectsdk.sampler.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.connectsdk.sampler.R;
import com.connectsdk.sampler.util.TestResponseObject;

public class MiniGameFragment extends BaseFragment{
    public Button[] Buttons = new Button[9];
    public Button resetButton;
    public TextView player1, player1Score, player2, player2Score;
    public int player1count, player2count, roundCount;

    public boolean playerTurn = true;

    int[] gameState = {0,0,0,0,0,0,0,0};
    int[][] winnerState = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
    };

    public TestResponseObject testResponse;
    public MiniGameFragment() {
        testResponse = new TestResponseObject();
    };

    public MiniGameFragment(Context context)
    {
        super(context);
        testResponse = new TestResponseObject();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(
                R.layout.fragment_minigame, container, false);

        player1 = (TextView) rootView.findViewById(R.id.playerOne);
        player1Score = (TextView) rootView.findViewById(R.id.playerOneScore);
        player2 = (TextView) rootView.findViewById(R.id.playerTwo);
        player2Score = (TextView) rootView.findViewById(R.id.playerTwoScore);

        Buttons[0] = (Button) rootView.findViewById(R.id.btn_0);
        Buttons[1] = (Button) rootView.findViewById(R.id.btn_1);
        Buttons[2] = (Button) rootView.findViewById(R.id.btn_2);
        Buttons[3] = (Button) rootView.findViewById(R.id.btn_3);
        Buttons[4] = (Button) rootView.findViewById(R.id.btn_4);
        Buttons[5] = (Button) rootView.findViewById(R.id.btn_5);
        Buttons[6] = (Button) rootView.findViewById(R.id.btn_6);
        Buttons[7] = (Button) rootView.findViewById(R.id.btn_7);
        Buttons[8] = (Button) rootView.findViewById(R.id.btn_8);

        resetButton = (Button) rootView.findViewById(R.id.resetGame);

        return rootView;
    }

    public boolean checkWinner(){
        boolean winnerResult = false;

        for(int [] winnerState : winnerState){
            if(gameState[winnerState[0]] == gameState[winnerState[1]] &&
                    gameState[winnerState[1]] == gameState[winnerState[2]] &&
                    gameState[winnerState[0]] != 0){

                winnerResult = true;
            }
        }
        return winnerResult;
    }

    public void updateScore(){
        player1Score.setText(Integer.toString(player1count));
        player2Score.setText(Integer.toString(player2count));
    }

    public void playAgain(){
        roundCount = 0;
        playerTurn = true;

        for(int i=0; i <9; i++){
            gameState[i] = 0;
            Buttons[i].setText("");
        }
    }

    @Override
    public void enableButtons() {
        super.enableButtons();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                player2Score.setText(Integer.toString(123));
            }
        });
    }




}
