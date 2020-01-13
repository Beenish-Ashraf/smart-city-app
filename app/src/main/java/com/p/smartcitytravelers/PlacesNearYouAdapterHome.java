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

public class PlacesNearYouAdapterHome extends RecyclerView.Adapter<PlacesNearYouAdapterHome.PlacesNearYouViewHolder> implements Filterable {
    ArrayList<FamousPlaces> famousPlaces;
    ArrayList<FamousPlaces> famousPlacesFull;
    PlacesNearYouViewHolder.PlacesNearYouInterface placesNearYouInterface;

    public PlacesNearYouAdapterHome(ArrayList<FamousPlaces> famousPlaces, PlacesNearYouViewHolder.PlacesNearYouInterface placesNearYouInterface) {
        this.famousPlaces = famousPlaces;
        this.placesNearYouInterface=placesNearYouInterface;
        famousPlacesFull=new ArrayList<>(famousPlaces);
    }

    @Override
    public Filter getFilter() {
        return null;
    }


    @NonNull
    @Override
    public PlacesNearYouViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_page_cardview,parent,false);
        return new PlacesNearYouViewHolder(v,placesNearYouInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull PlacesNearYouViewHolder holder, int position) {
        FamousPlaces currentFamousPlace=famousPlaces.get(position);
        Picasso.get().load(currentFamousPlace.getImageFamous()).fit().into(holder.famousPlacesImage);
        holder.famousPlaceName.setText(currentFamousPlace.famousPlaceName);
        holder.rating.setText(currentFamousPlace.getRating().toString());
    }





    @Override
    public int getItemCount() {
        return famousPlaces.size();
    }

    public static class PlacesNearYouViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView famousPlacesImage;
        TextView famousPlaceName;
        TextView rating;
        PlacesNearYouInterface placesNearYouInterface;

        public PlacesNearYouViewHolder(@NonNull View itemView,PlacesNearYouInterface placesNearYouInterface) {
            super(itemView);

            famousPlacesImage= itemView.findViewById(R.id.cityImage);
            famousPlaceName=itemView.findViewById(R.id.cityName);
            rating=itemView.findViewById(R.id.cityRating);
            this.placesNearYouInterface=placesNearYouInterface;
        }

        @Override
        public void onClick(View v) {

            placesNearYouInterface.onPlacesNearYouClick(getAdapterPosition());
        }

        public interface PlacesNearYouInterface
        {
            public void onPlacesNearYouClick(int position);
        }
    }
}
