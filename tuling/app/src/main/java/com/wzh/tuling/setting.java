package com.wzh.tuling;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.InvalidMarkException;

public class setting extends AppCompatActivity implements View.OnClickListener{

    private static final int ACTIVITY_SELECT_IMAGE = 999;
    private static final int ACTIVITY_CROP_IMAGE_USER=888;
    private static final int ACTIVITY_CROP_IMAGE_ROBOT=777;
    private Button btnback,setUser,setRObot,setTitle;
    private ImageView robotImage,userImage;
    private Uri ImageUriUSER,ImageUriROBOT;
    private EditText et;
    private Intent myintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initbtnset();
    }

    public void initbtnset(){
        btnback= (Button) findViewById(R.id.btngo);
        btnback.setOnClickListener(this);
        setTitle= (Button) findViewById(R.id.btntitle);
        setTitle.setOnClickListener(this);
        setRObot= (Button) findViewById(R.id.setRobot);
        setRObot.setOnClickListener(this);
        setUser= (Button) findViewById(R.id.setUser);
        setUser.setOnClickListener(this);
        robotImage= (ImageView) findViewById(R.id.RobotIMG);
        userImage= (ImageView) findViewById(R.id.UsertIMG);
        et= (EditText) findViewById(R.id.ETS);
        myintent=new Intent();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btngo:
                myintent.putExtra("mystring", et.getText().toString());
                setResult(RESULT_OK,myintent);
                finish();
                break;
            case R.id.setRobot:

                File outputimage=new File(Environment.getExternalStorageDirectory(),"outputImageROBOT.jpg");
                try{
                    if (outputimage.exists()){
                        outputimage.delete();
                    }
                    else {
                        outputimage.createNewFile();
                    }
                }catch (IOException a){
                    a.printStackTrace();
                }
                ImageUriROBOT=Uri.fromFile(outputimage);
                Intent intent2=new Intent(Intent.ACTION_PICK);
                intent2.setType("image/*");
                intent2.putExtra("crop", "true");
                intent2.putExtra("scale", "true");
                intent2.putExtra(MediaStore.EXTRA_OUTPUT,ImageUriROBOT);
                startActivityForResult(intent2,ACTIVITY_CROP_IMAGE_ROBOT);
                break;
            case R.id.setUser:
                File outputImage=new File(Environment.getExternalStorageDirectory(),"outputImageUSER.jpg");
                try {
                    if (outputImage.exists()){
                        outputImage.delete();
                    }else {
                        outputImage.createNewFile();
                    }

                }catch (IOException a){
                    a.printStackTrace();
                }
                ImageUriUSER=Uri.fromFile(outputImage);
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.putExtra("crop", "true");
                intent.putExtra("scale", "true");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, ImageUriUSER);
                startActivityForResult(intent, ACTIVITY_CROP_IMAGE_USER);
                break;
            case R.id.btntitle:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       switch (requestCode){
           case ACTIVITY_SELECT_IMAGE:
               if (resultCode==RESULT_OK){
                   Intent intent=new Intent("com.android.camera.action.CROP");
                   intent.setDataAndType(ImageUriROBOT, "image/*");
                   intent.putExtra(MediaStore.EXTRA_OUTPUT, ImageUriROBOT);
                  startActivityForResult(intent, ACTIVITY_CROP_IMAGE_ROBOT);
               }
               break;
           case ACTIVITY_CROP_IMAGE_USER:
                if (resultCode==RESULT_OK){
                    try {
                        Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(ImageUriUSER));
                        userImage.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
               break;
           case ACTIVITY_CROP_IMAGE_ROBOT:
               if (resultCode==RESULT_OK){
                   try {
                       Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(ImageUriROBOT));
                       myintent.putExtra("imageROBOT",ImageUriROBOT.toString());
                       robotImage.setImageBitmap(bitmap);
                   } catch (FileNotFoundException e) {
                       e.printStackTrace();
                   }
               }
               break;
           default:
               break;
       }
    }

    @Override
    public void onBackPressed() {
        myintent.putExtra("mystring", et.getText().toString());
        setResult(RESULT_OK,myintent);
        finish();
    }
}
