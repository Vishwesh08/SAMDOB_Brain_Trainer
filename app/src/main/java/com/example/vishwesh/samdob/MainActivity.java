package com.example.vishwesh.samdob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button bt;
    Button bt0;
    Button bt1;
    Button bt2;
    Button bt3;
    Button againPlay;
    TextView text,sec,result,score;
    int a,b,generateQues = 0,correctAns=0;
    ArrayList<Integer> ans = new ArrayList<Integer>();
    int locationOfCorrectAns,wrongAns;
    //Boolean timeOver = false;
    public int ques=0;
    GridLayout layout;

    public void go(View view){

        bt.setVisibility(View.INVISIBLE);


    }

    public void playAgain(View view){

        againPlay.setVisibility(View.INVISIBLE);
        generateQues = 0;
        layout.animate().alpha(1f).translationX(0f);
        correctAns = 0;
        sec.setText("30s");
        result.setText("");
        score.setText("0/0");

        questions();

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                int seconds = (int) millisUntilFinished / 1000;
                sec.setText(Integer.toString(seconds));

            }

            @Override
            public void onFinish() {

                againPlay.setVisibility(View.VISIBLE);
                sec.setText("0s");
                result.setText("Your Score: " + Integer.toString(correctAns) + " / " + Integer.toString(generateQues));
                layout.animate().alpha(0.2f).translationXBy(1000f);

            }
        }.start();


    }

    public void options(View view){

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAns))){
            result.setText("Correct");
            correctAns++;
        }else{
            result.setText("Wrong");
        }
        generateQues++;
        questions();

        score.setText(Integer.toString(correctAns)+" / "+Integer.toString(generateQues));


    }

    public void questions(){

        Random rand = new Random();

        a = rand.nextInt(21);
        b = rand.nextInt(21);

        locationOfCorrectAns = rand.nextInt(4);


        if(ques == 0) {
            text.setText(Integer.toString(a) + " + " + Integer.toString(b));
            ques=1;
        }
        else{
            text.setText(Integer.toString(a) + " * " + Integer.toString(b));
            ques=0;
        }

        ans.clear();

        for(int i=0;i<4;i++){

            if(i == locationOfCorrectAns){

                if(ques == 1) {
                    ans.add(a + b);
                }
                else{
                    ans.add(a*b);
                }

            }else{

                if (ques == 1) {

                    wrongAns = rand.nextInt(41);

                    if(wrongAns == a+b){

                        wrongAns = rand.nextInt(41);

                    }

                    ans.add(wrongAns);

                }
                else{
                    wrongAns = rand.nextInt(401);

                    if(wrongAns == a*b){

                        wrongAns = rand.nextInt(401);

                    }

                    ans.add(wrongAns);
                }


            }

        }

        bt0.setText(Integer.toString(ans.get(0)));
        bt1.setText(Integer.toString(ans.get(1)));
        bt2.setText(Integer.toString(ans.get(2)));
        bt3.setText(Integer.toString(ans.get(3)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.ques);
        result = (TextView) findViewById(R.id.result);
        score = (TextView) findViewById(R.id.score);
        sec = (TextView) findViewById(R.id.sec);
        bt = (Button) findViewById(R.id.go);
        bt0 = (Button) findViewById(R.id.button0);
        bt1 = (Button) findViewById(R.id.button1);
        bt2 = (Button) findViewById(R.id.button2);
        bt3 = (Button) findViewById(R.id.button3);
        againPlay = (Button) findViewById(R.id.playAgain);
        layout = (GridLayout) findViewById(R.id.layout);



        playAgain(findViewById(R.id.playAgain));
    }
}