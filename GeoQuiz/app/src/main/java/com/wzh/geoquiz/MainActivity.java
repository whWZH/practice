package com.wzh.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreButton;
    private TextView mtext;
    private int CurrentPositon=0;
    private Question[] questions=new Question[]{
            new Question(R.string.question_test1,true),
            new Question(R.string.question_test2,true),
            new Question(R.string.question_test3,true)
    };
    public void updataQuestion(){
        int question=questions[CurrentPositon].getmQuestion();
        mtext.setText(question);
    }
    public void isTrue(boolean answer){
        boolean TrueAns=questions[CurrentPositon].getIsTure();
        if (answer==TrueAns){
            Toast.makeText(MainActivity.this,R.string.correct_toast,Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainActivity.this,R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtext= (TextView)findViewById(R.id.m_text);
        mTrueButton= (Button) findViewById(R.id.true_button);
        mFalseButton= (Button) findViewById(R.id.false_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              isTrue(true);
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               isTrue(false);
            }
        });
        mNextButton= (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentPositon=(CurrentPositon+1)%questions.length;
                updataQuestion();
            }
        });
        mtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentPositon=(CurrentPositon+1)%questions.length;
                updataQuestion();
            }
        });
        mPreButton= (Button) findViewById(R.id.pre_button);
        mPreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CurrentPositon==0){
                    CurrentPositon=questions.length-1;
                    updataQuestion();
                }
                else {
                    CurrentPositon--;
                    updataQuestion();
                }
            }
        });
        updataQuestion();
        System.out.println("creat");
    }

    @Override
    protected void onPause() {
        System.out.println("onPause");
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("onRestart");
        System.out.println("重开");
    }

    @Override
    protected void onResume() {
        System.out.println("onResume");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");
    }
}
