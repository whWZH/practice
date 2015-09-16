package com.wzh.photogallery;

import android.widget.RelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Administrator on 2015/9/15.
 */
public class FlickrFetchr {
    private static final String XML_PHOTO="photo";
    byte[] grtUrlBytes(String urlSpec) throws IOException{
        URL url=new URL(urlSpec);
        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
        try{
            ByteArrayOutputStream out=new ByteArrayOutputStream();
            InputStream in=connection.getInputStream();
            if (connection.getResponseCode()!=HttpURLConnection.HTTP_OK){
                return null;
            }
            int bytesRead=0;
            byte[] buffer=new byte[1024];
            while((bytesRead=in.read(buffer))>0){
                out.write(buffer,0,bytesRead);
            }
            out.close();
            return out.toByteArray();
        }finally {
            connection.disconnect();
        }
    }
    public String getUrl(String urlSpec) throws IOException{
        return new String(grtUrlBytes(urlSpec));
    }
    public ArrayList<GalleryItem> fetchItems() throws JSONException {
        ArrayList<GalleryItem> items=new ArrayList<GalleryItem>();
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();

        String Urlll="http://apis.baidu.com/txapi/mvtp/meinv"+"?"+"num=10";
        try {
            URL url = new URL(Urlll);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey",  "531900ecbd4b3cd208b20fdfd8576971");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
        parseJsonItema(items,result);
        return items;
    }
    void parseJsonItema(ArrayList<GalleryItem> items,String json ) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        ArrayList<String> getJsons=new ArrayList<String>();
        for (int i=0;i<10;i++){
            String id=String.valueOf(i);
            String getS0=new String();
            getS0=jsonObject.getString(id);
            getJsons.add(getS0);
        }
        for (int i=0;i<getJsons.size();i++){
            GalleryItem item=new GalleryItem();
            JSONObject jsonObject1=new JSONObject(getJsons.get(i));
            String title=jsonObject1.getString("title");
            String description=jsonObject1.getString("description");
            String picUrl=jsonObject1.getString("picUrl");
            String url=jsonObject1.getString("url");
            item.setmTitle(title);
            item.setmUrl(url);
            item.setmDescriotion(description);
            item.setmPicUrl(picUrl);
            items.add(item);
            System.out.println(title + " " + description + " " + picUrl + " " + url);
        }
    }
}
