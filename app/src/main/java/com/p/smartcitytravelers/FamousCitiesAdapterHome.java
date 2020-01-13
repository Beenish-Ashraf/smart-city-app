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

public class FamousCitiesAdapterHome extends RecyclerView.Adapter<FamousCitiesAdapterHome.FamousCitiesViewHolder> implements Filterable {

    ArrayList<FamousPlaces> famousPlaces;
    ArrayList<FamousPlaces> famousPlacesFull;
    FamousCitiesViewHolder.FamousCitiesInterface famousCitiesInterface;

    public FamousCitiesAdapterHome(ArrayList<FamousPlaces> famousPlaces, FamousCitiesViewHolder.FamousCitiesInterface famousCitiesInterface) {
        this.famousPlaces = famousPlaces;
        famousPlacesFull=new ArrayList<>(famousPlaces);
        this.famousCitiesInterface=famousCitiesInterface;
    }

    @Override
    public Filter getFilter() {
        return null;
    }


    @NonNull
    @Override
    public FamousCitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_page_cardview,parent,false);
        return new FamousCitiesViewHolder(v,famousCitiesInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull FamousCitiesViewHolder holder, int position) {
        FamousPlaces currentFamousPlace=famousPlaces.get(position);
        Picasso.get().load(currentFamousPlace.getImageFamous()).fit().into(holder.famousPlacesImage);
        holder.famousPlaceName.setText(currentFamousPlace.famousPlaceName);
        holder.rating.setText(currentFamousPlace.getRating().toString());
    }





    @Override
    public int getItemCount() {
        return famousPlaces.size();
    }

    public static class FamousCitiesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView famousPlacesImage;
        TextView famousPlaceName;
        TextView rating;
        FamousCitiesInterface famousCitiesInterface;

        public FamousCitiesViewHolder(@NonNull View itemView,FamousCitiesInterface famousCitiesInterface) {
            super(itemView);

            famousPlacesImage= itemView.findViewById(R.id.cityImage);
            famousPlaceName=itemView.findViewById(R.id.cityName);
            rating=itemView.findViewById(R.id.cityRating);
            this.famousCitiesInterface=famousCitiesInterface;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            famousCitiesInterface.onFamousCityClick(getAdapterPosition());
        }

        public interface FamousCitiesInterface
        {
            public void onFamousCityClick(int position);
        }
    }
}
