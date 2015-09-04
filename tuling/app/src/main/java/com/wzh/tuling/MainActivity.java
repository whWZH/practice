package com.wzh.tuling;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowId;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HttpGetLison,View.OnClickListener,View.OnLongClickListener{

    private HttpData Hdata;
    private List<ListData> listDatas;
    private ListView lv;
    private EditText etx;
    private Button btnsend,btnset,btnback;
    private String contstr;
    private TestAdapta testAdapta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       initData();
    }

    public void initData(){
        listDatas=new ArrayList<ListData>();
        lv=(ListView)findViewById(R.id.listv);
        btnsend=(Button)findViewById(R.id.btnSEND);
        btnsend.setOnClickListener(this);
        btnback= (Button) findViewById(R.id.btnBack);
        btnback.setOnClickListener(this);
        btnset= (Button) findViewById(R.id.btnset);
        btnset.setOnClickListener(this);
        etx= (EditText) findViewById(R.id.etx);
        testAdapta=new TestAdapta(listDatas,this);
        lv.setAdapter(testAdapta);
        ListData listData;
        listData=new ListData("主人，奴婢在此恭候多时了",ListData.RECEVER);
        listDatas.add(listData);
    }

    @Override
    public void getDATAurl(String data) {
        getText(data);
    }
    public void getText(String str){
        try {

            JSONObject jsonObject = new JSONObject(str);
            ListData listData=new ListData(jsonObject.getString("text"),ListData.RECEVER);
            listDatas.add(listData);
            testAdapta.notifyDataSetChanged();
        }
        catch (Exception a){
            a.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSEND:
                contstr=etx.getText().toString();
                etx.setText("");
                ListData listData=new ListData(contstr,ListData.SEND);
                listDatas.add(listData);
                testAdapta.notifyDataSetChanged();
                Hdata= (HttpData) new HttpData("http://www.tuling123.com/openapi/api?key=609fa03cd4102151f1c8409db256ed7e&info="+contstr,this).execute();
                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnset:
                Intent intent=new Intent(MainActivity.this,setting.class);
                startActivityForResult(intent,1);
                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode==RESULT_OK){
                    TextView itv= (TextView) findViewById(R.id.title);
                    itv.setText(data.getStringExtra("mystring").toString());
                }
        }
    }

    @Override
    public boolean onLongClick(View v) {
        System.out.println("成功");
        return false;
    }
}
