package com.wzh.time;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et;
    private TextView tv;
    private Button btnStart;
    private  Button btnStop;
    private Button btnget;
    private int i;
    private Timer timer=null;
    private TimerTask timerTask=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initV();

    }

    public void initV(){
        tv= (TextView) findViewById(R.id.showTime);
        btnStart= (Button) findViewById(R.id.starTime);
        btnStart.setOnClickListener(this);
        btnStop= (Button) findViewById(R.id.stopTime);
        btnStop.setOnClickListener(this);
        btnget= (Button) findViewById(R.id.getTime);
        btnget.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.starTime:
                start();
              break;
            case R.id.stopTime:
                stop();
                break;
            case R.id.getTime:
                et= (EditText) findViewById(R.id.inputTime);
                tv.setText(et.getText().toString());
                i=Integer.parseInt(et.getText().toString());
                break;
        }
    }

  private Handler handler=new Handler(){
      @Override
      public void handleMessage(Message msg) {
          super.handleMessage(msg);
          tv.setText(msg.arg1+"");
          start();
      }
  };


    public void start(){
        timer=new Timer();
        timerTask=new TimerTask() {
            @Override
            public void run() {
                i--;
                Message msg=handler.obtainMessage();
                msg.arg1=i;
                handler.sendMessage(msg);
                System.out.println(i);
            }
        };
        timer.schedule(timerTask,1000);
    }
    public void stop(){
        timer.cancel();
    }
}
