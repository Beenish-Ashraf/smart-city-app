package com.p.smartcitytravelers;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FamousPlaceActivity extends AppCompatActivity {
protected RecyclerView recyclerView;
   protected FamousPlacesAdapter adpater;
   protected RecyclerView.LayoutManager layoutManager;
   private ArrayList<FamousPlaces> famousPlacesToVisitData=new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
   protected Button switchBtn;
   public static TextView cityN;
   private Button testBtn;
    String cityName;


    @Override
    protected void onStart() {


        super.onStart();



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_famous_place);
       cityN=findViewById(R.id.cityName);
        Intent intent=getIntent();
         cityName=intent.getStringExtra("cityName");
         cityN.setText(cityName);
       // Intent intent=new Intent(getApplicationContext(),HomePageActivity.class);
       // startActivity(intent);
        firebaseDatabase=FirebaseDatabase.getInstance();






// Attach a listener to read the data at our posts reference





//
        //int i=allCities.size();

       //Toast.makeText(this,i+"",Toast.LENGTH_LONG).show();


        ArrayList<FamousPlaces> arr=insertData();
        setRecyclerView(arr);



    }

    private ArrayList<FamousPlaces> insertData()
    {
        ArrayList<FamousPlaces> arr=new ArrayList<FamousPlaces>();
        arr.add(new FamousPlaces("https://www.history.com/.image/ar_16:9%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cg_faces:center%2Cq_auto:good%2Cw_768/MTY1MTc1MTk3ODIzOTM2MDcz/topic-statue-of-liberty-gettyimages-960610006-feature.jpg","Staue Liberty",4.5));
        arr.add(new FamousPlaces("/res/drawable/time_squares.jpg","Time Square",4.5));
        arr.add(new FamousPlaces("https://media-cdn.tripadvisor.com/media/photo-s/0e/16/1a/e6/new-york-city-central.jpg","Rock Center",4.5));
        arr.add(new FamousPlaces("https://cdn.britannica.com/82/133582-050-1B2986D4/Badshahi-Mosque-Lahore-Pakistan.jpg","Badshah",4.5));
        arr.add(new FamousPlaces("https://cdn.britannica.com/82/133582-050-1B2986D4/Badshahi-Mosque-Lahore-Pakistan.jpg","Badshah",4.5));
        arr.add(new FamousPlaces("https://cdn.britannica.com/82/133582-050-1B2986D4/Badshahi-Mosque-Lahore-Pakistan.jpg","Badshah",4.5));
        return arr;
    }

    private void setRecyclerView(ArrayList<FamousPlaces> arr)
    {


        recyclerView=findViewById(R.id.famousPlacesRecyclerView);
        layoutManager=new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        final DatabaseReference databaseReference=firebaseDatabase.getReference("famousPlaces").child(cityName);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<FamousPlace> famousPlaces=new ArrayList<>();
                for(DataSnapshot currentFamousPlace:dataSnapshot.getChildren())
                {
                    famousPlaces.add(currentFamousPlace.getValue(FamousPlace.class));

                }
               famousPlacesToVisitData= HomePageActivity.databaseInteraction.mapFamousPlaceData(famousPlaces);
                adpater=new FamousPlacesAdapter(famousPlacesToVisitData);
                recyclerView.setAdapter(adpater);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appmenu,menu);
        MenuItem searchItem=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView)searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adpater.getFilter().filter(newText);


                return false;
            }
        });


        return true;
    }
}
