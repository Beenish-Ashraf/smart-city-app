package com.p.smartcitytravelers;

import android.content.Context;
import android.content.Intent;
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
import java.util.List;

public class FamousPlacesAdapter extends RecyclerView.Adapter<FamousPlacesAdapter.FamousPlacesViewHolder> implements Filterable {
    ArrayList<FamousPlaces> famousPlaces;
    ArrayList<FamousPlaces> famousPlacesFull;
    Context context;
    public FamousPlacesAdapter(ArrayList<FamousPlaces> famousPlaces)
    {
        this.famousPlaces=famousPlaces;
        famousPlacesFull=new ArrayList<>(famousPlaces);
    }

    @NonNull
    @Override
    public FamousPlacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.citiescardview,parent,false);
       context=parent.getContext();
       return new FamousPlacesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FamousPlacesViewHolder holder, int position) {
        final FamousPlaces currentFamousPlace=famousPlaces.get(position);
        Picasso.get().load(currentFamousPlace.getImageFamous()).fit().into(holder.famousPlacesImage);
        holder.famousPlacesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context,FamousPlaceDetailActivity.class);
                intent.putExtra("place",currentFamousPlace);
                context.startActivity(intent);
            }
        });
        holder.famousPlaceName.setText(currentFamousPlace.famousPlaceName);
        holder.rating.setText(currentFamousPlace.getRating().toString());

    }

    @Override
    public int getItemCount() {
        return famousPlaces.size();
    }

    @Override
    public Filter getFilter() {
        return filtered;
    }

    private Filter filtered=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List filteredList=new ArrayList<>();

            if(constraint==null || constraint.length()==0)
            {
                filteredList.addAll(famousPlacesFull);
            }
            else
            {
                String search=constraint.toString().toLowerCase().trim();

                for(FamousPlaces fp: famousPlacesFull)
                {
                    if(fp.getFamousPlaceName().toLowerCase().contains(search))
                    {
                        filteredList.add(fp);
                    }
                }
            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=filteredList;
            return  filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
        famousPlaces.clear();
        famousPlaces.addAll((List)results.values);
        notifyDataSetChanged();
        }
    };
    public static class FamousPlacesViewHolder extends RecyclerView.ViewHolder{

        ImageView famousPlacesImage;
        TextView famousPlaceName;
        TextView rating;

        public FamousPlacesViewHolder(@NonNull View itemView) {
            super(itemView);

            famousPlacesImage= itemView.findViewById(R.id.imageFamous);
            famousPlaceName=itemView.findViewById(R.id.famousPlaceName);
            rating=itemView.findViewById(R.id.rating);
        }
    }
}
