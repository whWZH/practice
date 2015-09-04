package com.wzh.tuling;

import android.net.http.AndroidHttpClient;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2015/8/27.
 */
public class HttpData extends AsyncTask<String,Void ,String> {

    private HttpClient httpClient;
    private HttpGet httpGet;
    private String url;
    private HttpResponse response;
    private HttpEntity entity;
    private InputStream in;
    private HttpGetLison lison;

    public HttpData(String url,HttpGetLison lison){
        this.url=url;
        this.lison=lison;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            httpClient=new DefaultHttpClient();
            httpGet=new HttpGet(url);
            response=httpClient.execute(httpGet);
            entity=response.getEntity();
            in=entity.getContent();
            BufferedReader br=new BufferedReader(new InputStreamReader(in));
            String line =null;
            StringBuffer sb=new StringBuffer();
            while ((line=br.readLine())!=null){
                sb.append(line);
            }
            return sb.toString();
//            String sendInfo = "北京在哪";
//            String INFO =URLEncoder.encode(sendInfo,"UTF-8");
//            String abc="http://www.tuling123.com/openapi/api?key=609fa03cd4102151f1c8409db256ed7e&info="+INFO;
//            URL brl=null;
//            HttpURLConnection urlConnection=null;
//            InputStreamReader inR=null;
//            brl=new URL(abc);
//            urlConnection= (HttpURLConnection) brl.openConnection();
//            inR= new InputStreamReader(urlConnection.getInputStream());
//            BufferedReader bufferedReader=new BufferedReader(inR);
//            StringBuffer sb=new StringBuffer();
//            String line =null;
//            while ((line=bufferedReader.readLine())!=null){
//                sb.append(line);
//            }
//            return sb.toString();


        }
        catch (Exception a){

        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        lison.getDATAurl(s);
        super.onPostExecute(s);
    }
}
