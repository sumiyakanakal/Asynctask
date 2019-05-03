package com.example.pn.booksearchnew;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
EditText et1;
RecyclerView rv1;String Bookname;
public static final String TAG=MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=findViewById(R.id.et);
                rv1=findViewById(R.id.rv);
        Log.e(TAG,"Inseide Mainactivity");
    }

   // @Override
   /* protected void onSaveInstanceState(Bundle outState) {
        outState.putString("hi",Bookname);
        super.onSaveInstanceState(outState);
    }*/

    //@Override
   /* protected void onRestoreInstanceState(Bundle savedInstanceState) {
       // rv1.setAdapter(savedInstanceState.);
        super.onRestoreInstanceState(savedInstanceState);
    }
*/
    public void bookSearch(View view) {
        String Bookname=et1.getText().toString();
        Myclass myclass=new Myclass();
        myclass.execute(Bookname);
    }
    public class Myclass extends AsyncTask<String,Void,String>{
        ProgressDialog pd;
        String Myurl="https://www.googleapis.com/books/v1/volumes?q=";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

          /*  pd=new ProgressDialog(MainActivity.this);
            pd.setProgress(ProgressDialog.STYLE_SPINNER);
            pd.setMessage("loading...");
            pd.show();
*/
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
           // pd.dismiss();
            //Toast.makeText(MainActivity.this, ""+s, Toast.LENGTH_SHORT).show();
            ArrayList<Booksmodel> list=new ArrayList();
            try {
                JSONObject ob=new JSONObject(s);
                JSONArray itemarray=ob.getJSONArray("items");
                for(int i=0;i<itemarray.length();i++){
                    JSONObject jsonObject=itemarray.getJSONObject(i);
                    String id=jsonObject.getString("id");
                    JSONObject volumeInfo=jsonObject.getJSONObject("volumeInfo");

                    String title=volumeInfo.optString("title");
                    String authors=volumeInfo.optString("authors");
                    String publisher=volumeInfo.optString("publisher");
                    String publishedDate=volumeInfo.optString("publishedDate");

                    JSONObject imageObject=volumeInfo.getJSONObject("imageLinks");
                    String image=imageObject.optString("thumbnail");
                    Toast.makeText(MainActivity.this, ""+title+"\n"+authors+"\n"+id, Toast.LENGTH_SHORT).show();
                    Booksmodel model=new Booksmodel(id,title,authors,publisher,publishedDate,image);
                   // Booksmodel model=new Booksmodel()
                    list.add(model);
                }
            } catch (JSONException e) {
                e.printStackTrace();

            }
           rv1.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            rv1.setAdapter(new MyAdapter(MainActivity.this,list));
            }


        @Override
        protected String doInBackground(String... strings) {
            String u=strings[0];
            try {
                URL url=new URL(Myurl+u);
                HttpsURLConnection connection=(HttpsURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                InputStream is=connection.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                StringBuilder stringBuilder=new StringBuilder();
                String Line=null;
                while((Line=br.readLine())!=null){
                    stringBuilder.append(Line+"\n");
                }
                return stringBuilder.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        }
    }


