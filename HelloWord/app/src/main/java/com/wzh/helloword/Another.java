package com.wzh.helloword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Another extends AppCompatActivity {

    private TextView TV;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        editText=(EditText)findViewById(R.id.editText);
        Intent i=getIntent();
//        Bundle b=i.getExtras();
        TV=(TextView)findViewById(R.id.TV);
        //TV.setText(i.getStringExtra("name"));
       // TV.setText(String.format("name=%s,age=%d,school=%s",b.getString("name"),b.getInt("age"),b.getString("school","WD")));
        User user=i.getParcelableExtra("user");
        TV.setText(String.format("User info(name=%s,age=%d)",user.getName(),user.getAge()));
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.putExtra("date",editText.getText().toString());
                setResult(1, i);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_another, menu);
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
}
