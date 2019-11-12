package com.example.myapplicationdb;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONException;
import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;

public class FilmFinderr  extends AppCompatActivity {
    String TAG = FilmFinderr.class.getSimpleName();
    sqlhelper databaseHelper;
    Boolean hasUserClickedOnBackBtn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_finder);
        setTitle("Add data to SQLite");

        final EditText name=findViewById(R.id.edtsearch);
        Button btnsearch=findViewById(R.id.btnfind);
        Button btnsave=findViewById(R.id.btnsave);
        Button btnshow=findViewById(R.id.btnshow);

        final TextView title = findViewById(R.id.title);
        final TextView year = findViewById(R.id.year);
        final TextView genre = findViewById(R.id.genre);
        final TextView director = findViewById(R.id.director);
        final TextView writer = findViewById(R.id.writer);
        final TextView actors = findViewById(R.id.actors);
        final TextView plot = findViewById(R.id.plot);
        final TextView lang = findViewById(R.id.lang);
        final TextView country = findViewById(R.id.country);
        final TextView awards = findViewById(R.id.awards);
        final TextView poster = findViewById(R.id.poster);
        final TextView rating = findViewById(R.id.rating);
        final TextView website = findViewById(R.id.website);

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //url=http://www.omdbapi.com/?i=tt3896198&apikey=254f905a

                final EditText name=findViewById(R.id.edtsearch);
                String search = "http://www.omdbapi.com/?i="+name.getText()+"&apikey=254f905a";

                AsyncHttpClient client = new AsyncHttpClient();
                client.get(search, new  JsonHttpResponseHandler() {

                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        try {
                            title.setText(response.getString("Title"));
                            year.setText(response.getString("Year"));
                            genre.setText(response.getString("Genre"));
                            director.setText(response.getString("Director"));
                            writer.setText(response.getString("Writer"));
                            actors.setText(response.getString("Actors"));
                            plot.setText(response.getString("Plot"));
                            lang.setText(response.getString("Language"));
                            country.setText(response.getString("Country"));
                            poster.setText(response.getString("Poster"));
                            awards.setText(response.getString("Awards"));
                            rating.setText(response.getString("Rating"));
                            website.setText(response.getString("Website"));
                            Toast.makeText(FilmFinderr.this,"search successful",Toast.LENGTH_SHORT).show();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Toast.makeText(FilmFinderr.this,"search field",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        final sqlhelper helper = new sqlhelper(FilmFinderr.this, "FilmDB", null, 1);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Title=title.getText().toString();
                String Year=year.getText().toString();
                String Genre=genre.getText().toString();
                String Director=director.getText().toString();
                String Writer=writer.getText().toString();
                String Actors=actors.getText().toString();
                String Plot=plot.getText().toString();
                String Lang=lang.getText().toString();
                String Country=country.getText().toString();
                String Poster=poster.getText().toString();
                String Awards=awards.getText().toString();
                String Rating=rating.getText().toString();
                String Website=website.getText().toString();


                helper.insertFilm(Title,Year,Genre,Director,Writer,Actors,Plot,Lang,Country,Poster,Awards,Rating,Website);
                Toast.makeText(FilmFinderr.this,"Saved your search in db",Toast.LENGTH_SHORT).show();

            }
        });
        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FilmFinderr.this,ShowSearchActivity.class);
                v.getContext().startActivity(intent);
                Toast.makeText(FilmFinderr.this,"Show db",Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void onBackPressed(){

        if (hasUserClickedOnBackBtn)
            super.onBackPressed();
        else {
            Toast.makeText(this, "Please press back again", Toast.LENGTH_SHORT).show();
            hasUserClickedOnBackBtn = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    hasUserClickedOnBackBtn = false;
                }
            }, 2000);
        }
    }

}
