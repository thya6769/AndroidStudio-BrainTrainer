package com.caqmei.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private LinearLayout startLayout;
    private TextView questionText;

    private int correctIndex;
    private int correctAnswer;
    private int score = 0;
    private int noOfTry = 0;
    private TextView timeLeft;

    private Button leftTop;
    private Button rightTop;
    private Button leftBottom;
    private Button rightBottom;

    private TextView scoreText;

    private RelativeLayout playLayout;

    private TextView underText;

    private Button playAgain;

    public void changeVisibility(View view) {
        startLayout.setVisibility(View.INVISIBLE);
        playLayout.setVisibility(View.VISIBLE);
        new CountDownTimer(30100, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft.setText(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                timeLeft.setText("0s");
                underText.setText("Your Score: " + score + "/" + noOfTry);
                leftTop.setEnabled(false);
                rightTop.setEnabled(false);
                leftBottom.setEnabled(false);
                rightBottom.setEnabled(false);
                underText.setVisibility(View.VISIBLE);
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();
        setUp();
    }

    // set ups the view
    private void setUp() {
        int num1 = randInt(0, 100);
        int num2 = randInt(0, 100);

        questionText.setText(num1 + "+" + num2);


        // setting up correct answer
        correctAnswer = num1 + num2;

        int[] answer = new int[4];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = randInt(0, 100);
        }
        correctIndex = randInt(0,3);
        answer[correctIndex] = correctAnswer;

        // assign answer to text
        leftTop.setText(Integer.toString(answer[0]));
        rightTop.setText(Integer.toString(answer[1]));
        leftBottom.setText(Integer.toString(answer[2]));
        rightBottom.setText(Integer.toString(answer[3]));

    }

    // check the answer and make new set up
    public void nextQuestion(View view) {

        Log.i("Button is clicked", "Clicked!");
            // if the answer is correct
            if (correctIndex == Integer.parseInt(view.getTag().toString())) {
                score++;
                underText.setText("Correct!");
            } else {
                underText.setText("Wrong!");
            }
            noOfTry++;
            // update the score and no of tries
            scoreText.setText(score + "/" + noOfTry);
            underText.setVisibility(View.VISIBLE);
            Handler myHandler = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    underText.setVisibility(View.INVISIBLE);
                }
            };
            myHandler.postDelayed(run, 1000);
            setUp();
     }

    public void reset(View view) {
        playLayout.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        underText.setVisibility(View.INVISIBLE);
        score = 0;
        noOfTry = 0;
        startLayout.setVisibility(View.VISIBLE);
        leftTop.setEnabled(true);
        rightTop.setEnabled(true);
        leftBottom.setEnabled(true);
        rightBottom.setEnabled(true);

    }



    // Generate random numbers
    private int randInt(int min, int max) {

        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startLayout = (LinearLayout) findViewById(R.id.startLayout);
        questionText = (TextView) findViewById(R.id.questionText);
        timeLeft = (TextView) findViewById(R.id.timeLeft);
        leftTop = (Button) findViewById(R.id.lefTop);
        leftBottom = (Button) findViewById(R.id.leftBottom);
        rightTop = (Button) findViewById(R.id.rightTop);
        rightBottom = (Button) findViewById(R.id.rightBottom);
        scoreText = (TextView) findViewById(R.id.scoreText);
        playLayout = (RelativeLayout) findViewById(R.id.playLayout);
        underText = (TextView) findViewById(R.id.underText);
        playAgain = (Button) findViewById(R.id.playAgain);
    }
}
