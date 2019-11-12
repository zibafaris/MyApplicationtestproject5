package com.example.myapplicationdb;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class ShowSearchActivity extends AppCompatActivity {

    private static final String TAG = ShowSearchActivity.class.getSimpleName();

    private RecyclerView recyclerView;

    private Recycler_Search_film databaseAdapter;

    private sqlhelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showfilm);
        setTitle("Add Data to Sqlite");
        recyclerView=findViewById(R.id.recyclefilmview);
        setUpRecyclerView();
        databaseHelper = new sqlhelper(this,"FilmDB",null,1);
        List<DbModel> databaseModels = databaseHelper.getFilmData();

        //populate list
        populateRecyclerViewFromSqliteDatabase(databaseModels);

    }

    private void populateRecyclerViewFromSqliteDatabase(List<DbModel> databaseModels) {
        databaseAdapter = new Recycler_Search_film(databaseModels);
        recyclerView.setAdapter(databaseAdapter);
    }

    private void setUpRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
