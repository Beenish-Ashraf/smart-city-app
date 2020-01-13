package com.p.smartcitytravelers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity implements PlacesNearYouAdapterHome.PlacesNearYouViewHolder.PlacesNearYouInterface, FamousCitiesAdapterHome.FamousCitiesViewHolder.FamousCitiesInterface, CountriesToVisitAdapterHome.CountriesToVisitViewHolder.CountriesToVisitInterface {

    ArrayList<FamousPlaces> placesToVisit;
    ArrayList<FamousPlaces> placesNeraYou1;
    ArrayList<FamousPlaces> citiesForAdapter;
    TextView name;
    Button logout;
    FirebaseDatabase database;

    protected RecyclerView recyclerViewFamousCities;
    protected RecyclerView recyclerViewPlacesNearYou;
    protected RecyclerView recyclerViewCountriesToVisit;
    public static DatabaseInteraction databaseInteraction;
    protected Button switchBtn;

    protected PlacesNearYouAdapterHome placesNearYouAdapterHome;
    protected CountriesToVisitAdapterHome countriesToVisitAdapterHome;
    protected FamousCitiesAdapterHome adpater;
    protected RecyclerView.LayoutManager layoutManager;
    protected RecyclerView.LayoutManager layoutManagerPlacesNearYou;
    protected RecyclerView.LayoutManager layoutManagerCountriesToVisit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        name=findViewById(R.id.userName);
        String userName=FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        if(!userName.equals("")) {
            name.setText(userName);
        }
        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        ArrayList<FamousPlaces> arr=insertData();
        database=FirebaseDatabase.getInstance();
        databaseInteraction=new DatabaseInteraction(this);
        setRecyclerView();
    }
    void logoutUser()
    {
        FirebaseAuth.getInstance().signOut();
        goToSignIn();
    }
    void goToSignIn()
    {
        Intent intent=new Intent(this,SignInActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    private ArrayList<FamousPlaces> insertData()
    {
        ArrayList<FamousPlaces> arr=new ArrayList<FamousPlaces>();

        //arr.add(new FamousPlaces(MainActivity.databaseInteraction.images.get(i).getSource(),MainActivity.databaseInteraction.allCities.get()))
        arr.add(new FamousPlaces("https://www.history.com/.image/ar_16:9%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cg_faces:center%2Cq_auto:good%2Cw_768/MTY1MTc1MTk3ODIzOTM2MDcz/topic-statue-of-liberty-gettyimages-960610006-feature.jpg","Staue Liberty",4.5));
        arr.add(new FamousPlaces("/res/drawable/time_squares.jpg","Time Square",4.5));
        arr.add(new FamousPlaces("https://media-cdn.tripadvisor.com/media/photo-s/0e/16/1a/e6/new-york-city-central.jpg","Rock Center",4.5));
        arr.add(new FamousPlaces("https://cdn.britannica.com/82/133582-050-1B2986D4/Badshahi-Mosque-Lahore-Pakistan.jpg","Badshah",4.5));
        arr.add(new FamousPlaces("https://cdn.britannica.com/82/133582-050-1B2986D4/Badshahi-Mosque-Lahore-Pakistan.jpg","Badshah",4.5));
        arr.add(new FamousPlaces("https://cdn.britannica.com/82/133582-050-1B2986D4/Badshahi-Mosque-Lahore-Pakistan.jpg","Badshah",4.5));
        return arr;
    }



    private void setRecyclerView()
    {
        DatabaseReference ref;

        recyclerViewPlacesNearYou=findViewById(R.id.recyclerViewPlacesNearYou);
        recyclerViewFamousCities=findViewById(R.id.recyclerViewFamousCities);
        recyclerViewCountriesToVisit=findViewById(R.id.recyclerViewCountriesToVisit);
        layoutManager=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        layoutManagerPlacesNearYou=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        layoutManagerCountriesToVisit=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);

        recyclerViewFamousCities.setLayoutManager(layoutManager);
        recyclerViewPlacesNearYou.setLayoutManager(layoutManagerPlacesNearYou);

        recyclerViewCountriesToVisit.setLayoutManager(layoutManagerCountriesToVisit);

        //famousPlacesToVisit
        ref=database.getReference("famousPlaces").child("Islamabad");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<FamousPlace> placesNearYou=new ArrayList<>();

                for(DataSnapshot currentPlaceNearYou:dataSnapshot.getChildren())
                {
                    placesNearYou.add(currentPlaceNearYou.getValue(FamousPlace.class));
                }
                 placesToVisit= databaseInteraction.mapFamousPlaceData(placesNearYou);
                countriesToVisitAdapterHome=new CountriesToVisitAdapterHome(placesToVisit,HomePageActivity.this);
                recyclerViewCountriesToVisit.setAdapter(countriesToVisitAdapterHome);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    //forPlacesNearYou
        ref=database.getReference("famousPlaces").child("Abbottabad");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<FamousPlace> placesNearYou=new ArrayList<>();

                for(DataSnapshot currentPlaceNearYou:dataSnapshot.getChildren())
                {
                    placesNearYou.add(currentPlaceNearYou.getValue(FamousPlace.class));
                }
                placesNeraYou1= databaseInteraction.mapFamousPlaceData(placesNearYou);
                placesNearYouAdapterHome =new PlacesNearYouAdapterHome(placesNeraYou1,HomePageActivity.this);
                recyclerViewPlacesNearYou.setAdapter(placesNearYouAdapterHome);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    //ForCities
        ref=database.getReference("City");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<City> cities1=new ArrayList<>();

                for(DataSnapshot currentCity : dataSnapshot.getChildren())
                {
                    cities1.add(currentCity.getValue(City.class));
                }

                 citiesForAdapter=databaseInteraction.mapCityData(cities1);
                adpater=new FamousCitiesAdapterHome(citiesForAdapter,HomePageActivity.this);
                recyclerViewFamousCities.setAdapter(adpater);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onPlacesNearYouClick(int position) {

    }

    @Override
    public void onFamousCityClick(int position) {
        citiesForAdapter.get(position);
        Intent intent=new Intent(HomePageActivity.this,FamousPlaceActivity.class);
        intent.putExtra("cityName",citiesForAdapter.get(position).getFamousPlaceName());
        startActivity(intent);
    }

    @Override
    public void onCountriesToVisitClick(int position) {

    }
}
