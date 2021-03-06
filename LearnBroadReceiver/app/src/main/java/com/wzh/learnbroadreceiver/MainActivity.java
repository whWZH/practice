package com.wzh.learnbroadreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        findViewById(R.id.btnSendMessage).setOnClickListener(this);
        findViewById(R.id.btnReg).setOnClickListener(this);
        findViewById(R.id.btnUnReg).setOnClickListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSendMessage:
                Intent i=new Intent(MyReceiver.ACTION);
                i.putExtra("data","机密消息");
//                sendBroadcast(i);
                sendOrderedBroadcast(i,null);
                break;
            case R.id.btnReg:
                if (receiver==null) {
                    receiver=new MyReceiver();
                    registerReceiver(receiver,new IntentFilter(MyReceiver.ACTION));
                }
                break;
            case R.id.btnUnReg:
                if (receiver!=null){
                    unregisterReceiver(receiver);
                    receiver=null;
                }
                break;
        }
    }
    private MyReceiver receiver=null;
}
