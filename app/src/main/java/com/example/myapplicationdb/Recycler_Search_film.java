package com.example.myapplicationdb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


class Recycler_Search_film extends RecyclerView.Adapter<Recycler_Search_film.RecyclerviewHolder> {

    String TAG = Recycler_Search_film.class.getSimpleName();

    private List<DbModel> databaseModels;


    public Recycler_Search_film(List<DbModel> databaseModels) {
        this.databaseModels = databaseModels;
    }

    @NonNull
    @Override
    public RecyclerviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_film_db,parent,false);
        RecyclerviewHolder holder=new RecyclerviewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewHolder holder, int position) {
        DbModel model = databaseModels.get(position);

        holder.txtTitle.setText(model.getTitle());
        holder.txtYear.setText(model.getYear());
        holder.txtGenre.setText(model.getGenre());
        holder.txtDirector.setText(model.getDirector());
        holder.txtWriter.setText(model.getWriter());
        holder.txtActors.setText(model.getActors());
        holder.txtPlot.setText(model.getPlot());
        holder.txtLang.setText(model.getLang());
        holder.txtCountry.setText(model.getCountry());
        holder.txtPoster.setText(model.getPoster());
        holder.txtAwards.setText(model.getAwards());
        holder.txtRating.setText(model.getRating());
        holder.txtWebsite.setText(model.getWebsite());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


    @Override
    public int getItemCount() {

        return databaseModels.size();
    }

    class RecyclerviewHolder extends RecyclerView.ViewHolder  {
        TextView txtTitle, txtYear, txtGenre, txtDirector, txtWriter, txtActors, txtPlot, txtLang, txtCountry, txtPoster, txtAwards, txtRating,txtWebsite;


        public RecyclerviewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.title);
            txtYear = itemView.findViewById(R.id.year);
            txtGenre = itemView.findViewById(R.id.genre);
            txtDirector = itemView.findViewById(R.id.director);
            txtWriter = itemView.findViewById(R.id.writer);
            txtActors = itemView.findViewById(R.id.actors);
            txtPlot = itemView.findViewById(R.id.plot);
            txtLang = itemView.findViewById(R.id.lang);
            txtCountry = itemView.findViewById(R.id.country);
            txtPoster = itemView.findViewById(R.id.poster);
            txtAwards = itemView.findViewById(R.id.awards);
            txtRating = itemView.findViewById(R.id.rating);
            txtWebsite = itemView.findViewById(R.id.website);


            View view = itemView;

        }


    }
}