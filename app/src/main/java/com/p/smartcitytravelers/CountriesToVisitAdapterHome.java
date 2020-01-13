package com.p.smartcitytravelers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CountriesToVisitAdapterHome extends RecyclerView.Adapter<CountriesToVisitAdapterHome.CountriesToVisitViewHolder> implements Filterable {
    ArrayList<FamousPlaces> famousPlaces;
    ArrayList<FamousPlaces> famousPlacesFull;
    CountriesToVisitViewHolder.CountriesToVisitInterface countriesToVisitInterface;

    public CountriesToVisitAdapterHome(ArrayList<FamousPlaces> famousPlaces, CountriesToVisitViewHolder.CountriesToVisitInterface countriesToVisitInterface) {
        this.famousPlaces = famousPlaces;
        this.countriesToVisitInterface = countriesToVisitInterface;
        famousPlacesFull = new ArrayList<>(famousPlaces);
    }

    @Override
    public Filter getFilter() {
        return null;
    }


    @NonNull
    @Override
    public CountriesToVisitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_page_cardview, parent, false);
        return new CountriesToVisitViewHolder(v, countriesToVisitInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesToVisitViewHolder holder, int position) {
        FamousPlaces currentFamousPlace = famousPlaces.get(position);
        Picasso.get().load(currentFamousPlace.getImageFamous()).fit().into(holder.famousPlacesImage);
        holder.famousPlaceName.setText(currentFamousPlace.famousPlaceName);
        holder.rating.setText(currentFamousPlace.getRating().toString());
    }


    @Override
    public int getItemCount() {
        return famousPlaces.size();
    }

    public static class CountriesToVisitViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView famousPlacesImage;
        TextView famousPlaceName;
        TextView rating;
        CountriesToVisitInterface countriesToVisitInterface;

        public CountriesToVisitViewHolder(@NonNull View itemView, CountriesToVisitInterface countriesToVisitInterface) {
            super(itemView);

            famousPlacesImage = itemView.findViewById(R.id.cityImage);
            famousPlaceName = itemView.findViewById(R.id.cityName);
            rating = itemView.findViewById(R.id.cityRating);
            this.countriesToVisitInterface = countriesToVisitInterface;
        }

        @Override
        public void onClick(View v) {
            countriesToVisitInterface.onCountriesToVisitClick(getAdapterPosition());
        }

        public interface CountriesToVisitInterface {
            public void onCountriesToVisitClick(int position);

        }
    }
}

