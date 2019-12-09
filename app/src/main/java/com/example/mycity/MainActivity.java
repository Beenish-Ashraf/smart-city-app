package com.example.mycity;


import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {


    Location myLocation;
    FusedLocationProviderClient lastLoacation;
    private static final int Request_Code=101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lastLoacation= LocationServices.getFusedLocationProviderClient(getApplicationContext());
        getLastLocation();

    ArrayList<City> cities=new ArrayList<City>();
        ArrayList<Image> images=new ArrayList<Image>();
        ArrayList<FamousPlace> famousPlaces=new ArrayList<FamousPlace>();
        cities.add(new City("Lahore",12,4,"very good","image"));
        cities.add(new City("Karachi",12,4,"very good","image"));

        famousPlaces.add(new FamousPlace("Lahore","Badshahi mosque","mosque",4,12,12,13));
        famousPlaces.add(new FamousPlace("Lahore","Shahiqila","mosque",4,12,12,13));
        famousPlaces.add( new FamousPlace("Karachi","Jinnah","mosque",4,12,12,13));

        images.add(new Image("Lahore","Badshahi mosque",23,45,"hjasd"));

       // addData(cities,famousPlaces,images);


    }

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED

                && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)

        {

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_Code);

            return;

        }
        Task<Location> task= lastLoacation.getLastLocation();


        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {


                if(location != null)
                {
                    myLocation=location;
                    Toast.makeText(getApplicationContext(),myLocation.getLatitude()+" ",Toast.LENGTH_LONG).show();

                    SupportMapFragment smf=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
                    smf.getMapAsync(MainActivity.this);
                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"failure"+" ",Toast.LENGTH_LONG).show();
            }
        });

    }

    public void addData(ArrayList<City> cities,ArrayList<FamousPlace> famousPlaces,ArrayList<Image> images)
    {
        for(City city: cities)
        {
            addCity(city);

        }
        for(FamousPlace famousPlace: famousPlaces)
        {
           addFamousPlace(famousPlace);

        }
        for(Image image: images)
        {
            addImage(image);

        }
    }


    public void addCity(City city)
    {
        DatabaseReference cityTableRef= FirebaseDatabase.getInstance().getReference("City");
        cityTableRef.child(city.getName()).setValue(city);
    }
    public void addFamousPlace(FamousPlace famousPlace)
    {
        DatabaseReference famousPlaceTableRef= FirebaseDatabase.getInstance().getReference("famousPlaces");
        famousPlaceTableRef.child(famousPlace.getCityName()).child(famousPlace.getName()).setValue(famousPlace);    }

        public void addImage(Image image)
        {
            DatabaseReference cityTableRef= FirebaseDatabase.getInstance().getReference("Images");
            cityTableRef.child(image.getCity()).child(image.getFamousPlace()).setValue(image);
        }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latlng=new LatLng(myLocation.getLatitude(),myLocation.getLongitude());
        MarkerOptions mo=new MarkerOptions().position(latlng).title("uritur");
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latlng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,5));
        googleMap.addMarker(mo);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case Request_Code:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    getLastLocation();
                }
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
