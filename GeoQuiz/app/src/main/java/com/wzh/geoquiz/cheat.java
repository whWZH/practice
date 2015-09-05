package com.wzh.geoquiz;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class cheat extends AppCompatActivity {
private boolean mAnswer;
    private Button mShowAnswer;
    private TextView mTv;
    private boolean mIsChrat;

    public static  final String EXTRA_ANSWER_ISTRYUE="com.wzh.geoquiz.answer_is_true";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        if (savedInstanceState!=null){
            mIsChrat=savedInstanceState.getBoolean("cheat");
        }
        mAnswer=getIntent().getBooleanExtra(EXTRA_ANSWER_ISTRYUE, false);
        mTv= (TextView) findViewById(R.id.answerText);
        mShowAnswer= (Button) findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswer) {
                    mTv.setText(R.string.true_button);
                } else {
                    mTv.setText(R.string.false_button);
                }
                mIsChrat=true;
                setResult1(mIsChrat);
            }
        });
        setResult1(mIsChrat);
    }
    public  void setResult1(boolean mAns){
        Intent intent=new Intent();
        intent.putExtra("show",mAns);
        setResult(RESULT_OK, intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("cheat",mIsChrat);
    }
}
