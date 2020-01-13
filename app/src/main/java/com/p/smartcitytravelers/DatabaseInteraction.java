package com.p.smartcitytravelers;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class DatabaseInteraction extends AsyncTask<Void,Void,Void> {
    Context context;
     public  ArrayList<FamousPlace> allFamousPlaces;
    public  ArrayList<City> allCities;

     public  ArrayList<Image> images;
      FirebaseDatabase database;
   static int i=0;
    CountDownLatch done;


    DatabaseInteraction(Context c)
    {
        context=c;
        allCities=new ArrayList<>();
        allFamousPlaces=new ArrayList<>();
        images=new ArrayList<>();
        database = FirebaseDatabase.getInstance();
       // this.execute();




    }
    @Override
    protected Void doInBackground(Void... voids) {

      //  ref=database.getReference("famousPlaces");

        allCities= fetchAllCities();
        fetchAndStoreAllData(allFamousPlaces,allCities);
        return null;
    }



    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);



        // Toast.makeText(context,i+" ",Toast.LENGTH_LONG).show();

    }

    public ArrayList<City> fetchAllCities()
    {
        final ArrayList<City> cities=new ArrayList<>();
        DatabaseReference databaseReference=database.getReference("City");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for(DataSnapshot currentCity : dataSnapshot.getChildren())
               {
                   cities.add(currentCity.getValue(City.class));


               }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    return cities;
    }

    public ArrayList<FamousPlace> fetchAllFamousPlaces(City city)
    {

        final ArrayList<FamousPlace> famousPlaces= new ArrayList<>();
        DatabaseReference databaseReference=database.getReference("famousPlaces").child(city.getName());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot currentFamousPlace:dataSnapshot.getChildren())
                {
                    famousPlaces.add(currentFamousPlace.getValue(FamousPlace.class));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return famousPlaces;
    }

    public void fetchAndStoreAllData(ArrayList<FamousPlace> allFamousPlaces, ArrayList<City> allCities)
    {
        allCities=fetchAllCities();



        for(City currentCity:allCities)
        {

            allFamousPlaces.addAll(fetchAllFamousPlaces(currentCity));

        }
    }


    public String getImageUrlFromKey(String key)
    {
        String url=("https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+key+"&key=AIzaSyBmPkKD6tfgdavTL4_D6UoF-ZGviV-DZSQ");
        return url;
    }

    public ArrayList<FamousPlaces> mapCityData(ArrayList<City> cities)
    {
        ArrayList<FamousPlaces> arr=new ArrayList<>();
        for(int i=0;i<cities.size();i++)
        {
            arr.add(new FamousPlaces(getImageUrlFromKey(cities.get(i).getImage()),cities.get(i).getName(),cities.get(i).getRating()));
        }
        return arr;
    }

     public ArrayList<FamousPlaces> mapFamousPlaceData(ArrayList<FamousPlace> famousPlaces)
     {
         ArrayList<FamousPlaces> arr=new ArrayList<>();
         for(int i=0;i<famousPlaces.size();i++)
         {
             arr.add(new FamousPlaces(getImageUrlFromKey(famousPlaces.get(i).getImage()),famousPlaces.get(i).getName(),famousPlaces.get(i).getRating()));
             arr.get(i).longitude=famousPlaces.get(i).getLongitude();
             arr.get(i).latitude=famousPlaces.get(i).getLatitude();
         }
         return arr;
     }

}


