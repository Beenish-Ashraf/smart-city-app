package com.e.smartcityapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.FetchPhotoResponse;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RequestQueue queue=null;
    PlacesClient placesClient;
    List<Place.Field> placeFields= Arrays.asList(Place.Field.ID,Place.Field.NAME,Place.Field.ADDRESS);
    AutocompleteSupportFragment places_fragment;
    Button btn_get_photo,btn_get_famous_places,btn_get_current_location;
    TextView txt_detail;
    ImageView imageView;
    String placeId="ChIJ2QeB5YMEGTkRYiR-zGy-OsI";
    City city=new City();
    List<Image> images = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(MainActivity.this);
        btn_get_photo=findViewById(R.id.get_photo);
        txt_detail=findViewById(R.id.text_detail);
        imageView=findViewById(R.id.image_view);
        btn_get_current_location=findViewById(R.id.get_current_location);
        btn_get_famous_places=findViewById(R.id.get_famous_places);
        btn_get_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(placeId==null)
                {
                    Toast.makeText(MainActivity.this,"place ID is requried",Toast.LENGTH_SHORT).show();
                }
                else{
                    getPhotoAndDetail();
                }
            }
        });
        btn_get_famous_places.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                getAndStoreAllData();
        }});
        btn_get_current_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,CurrentLoacation.class);
                startActivity(i);
                finish();
            }
        });
        initializePlaces();

        setupPlacesAutoComplete();
    }
    public void getAndStoreAllData()
    {

        ArrayList<String> cityId=new ArrayList<>();
        cityId.add("ChIJ2QeB5YMEGTkRYiR-zGy-OsI");
        cityId.add("ChIJH1uLaIrCGDkR4v2MoGQbCwg");
        cityId.add("ChIJv0sdZQY-sz4RIwxaVUQv-Zw");
        cityId.add("ChIJqVyllahCIjkRxnEmk4iPxd4");
        cityId.add("ChIJw_NE1PZwTDkRheJBPRi8C8A");
        cityId.add("ChIJy5pBdImU3zgRD9MyFn41hAk");
        cityId.add("ChIJKesg1YMpHzkRaKPnha8z-wY");
        cityId.add("ChIJz3kOD7kX2TgREqRYdWOyFqg");
        cityId.add("ChIJ2afeeFcxOzkRL9RVTscv17o");
        cityId.add("ChIJL3KReNC_3zgRtgLbO1xRWWA");
        cityId.add("ChIJ6ap_yjTe0j4Rl_FvS0zUlkY");
        cityId.add("ChIJ1RphbMSQOzkRYvgD4agN3_w");
        cityId.add("ChIJ45QmUDl0ITkRcLzL7da64VU");
        cityId.add("ChIJ_WzbdFbqHjkRREdtlIM50Kg");
        cityId.add("ChIJ9wq-CubUNTkR1DocWmWdV80");
        cityId.add("ChIJt2wHZsSrNTkRnI0FZ9veQCY");
        cityId.add("ChIJOdtFO3FcNzkRcAEqZ8Ejryg");
        cityId.add("ChIJw8s4saGiIzkRoRGSJbnB1N4");
        cityId.add("ChIJdWX_LOmxOjkRu68h-bT4nhw");
        cityId.add("ChIJLURDHgMbHzkRTGg9VCP9cAg");
        cityId.add("ChIJbVpA2Cy2IjkRt7-M98B5zmw");
        cityId.add("ChIJDbCM31Sm3zgRs1-XjQ1lxM8");
        cityId.add("ChIJ2Xun5yDK3jgR1X-En96W3s4");
        cityId.add("ChIJzaBTZXu5GTkR0o6qZB-ABpM");
        cityId.add("ChIJ5TmWml2nIjkRK7H6F2tbWqI");
        cityId.add("ChIJDTvDwc0i3DgRYqYnTeFz4CQ");
        cityId.add("ChIJ5SjwY7VMSjkRLQDswAZe0pM");
        cityId.add("ChIJs6vuhkM7IjkRi31giSiIx_M");
        cityId.add("ChIJ642TM_Z6TDkRwt0aRD9HSTA");
        cityId.add("ChIJX5LhyuEpGTkRyqDldlSboec");
        cityId.add("ChIJLeHfbLtNHzkRVYuvFFfQec0");
        cityId.add("ChIJ_3c1MOhBNzkReG1r0OUtadc");
        cityId.add("ChIJ9dBkpgKsTjkRBi6ddSsJRvI");
        cityId.add("ChIJWR6oz8TePDkR0NQ1n_sE_6s");
        cityId.add("ChIJFfRarBPv2DgRNLiOdbByRYY");
        cityId.add("ChIJR_CIXwxVOzkRtxAZZPiDcUg");
        cityId.add("ChIJk5AiN3huJjkR6oEXw6dBaac");
        cityId.add("ChIJ6-IEKn2nuD4RNtqQ1CjuwIc");
        cityId.add("ChIJFfokmf_XOjkRKhoRC085PB0");
        cityId.add("ChIJF8V6VREx3jgRLJUusTWmWW4");
        cityId.add("ChIJufdyKn59HzkRk-xfsB_WaJw");
        cityId.add("ChIJiY04XpA7NDkRr2aqZMB9aUY");
        cityId.add("ChIJ-47hxuBzNDkRy_i7seC1cKg");
        cityId.add("ChIJU2QG5eaQHzkRLQeuQiq3h-8");
        cityId.add("ChIJNxSCSaqXHzkROpGDchl6psM");
        cityId.add("ChIJuajKcV7aNTkR_fuNWfP9nzo");
        cityId.add("ChIJlZ1rSgaeyz4RDznUqC6tb0o");
        cityId.add("ChIJhaWuLJNZPTkRjqMT6x53A6Q");
        cityId.add("ChIJEx30jMBusz4R6XXoW9OFzd4");
        cityId.add("ChIJJYB_e6_bHjkRwHJh02Uudt8");
        cityId.add("ChIJI2pkFRUAIzkRZ7NY_m-Gsvw");
        for(int i=0;i<cityId.size();i++)
        {
            placeId=cityId.get(i);
            getCityFromAPI(placeId);
        }
    }

    public void parseJsonFamousPlaces(String json,City c)
    {
        try {
            JSONObject obj = new JSONObject(json);
            if (obj.getString("status").equals("OK")) {
                JSONArray jsonArray = obj.getJSONArray("results");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    FamousPlace f=new FamousPlace();
                    f.setCity_id(c.getId());
                    f.setCity_name(c.getName());
                    f.setId(jsonObject.getString("place_id"));
                    f.setLongitude(jsonObject.getJSONObject("geometry").getJSONObject("location").getDouble("lng"));
                    f.setLatitude(jsonObject.getJSONObject("geometry").getJSONObject("location").getDouble("lat"));
                    f.setRating(jsonObject.getDouble("rating"));
                    f.setUsersRated(jsonObject.getInt("user_ratings_total"));
                    f.setName(jsonObject.getString("name"));
                    addFamousPlace(f);
                    JSONArray photoArray=jsonObject.getJSONArray("photos");
                    for(int j=0;j<photoArray.length();j++)
                    {
                        JSONObject photo=photoArray.getJSONObject(j);
                        Image img=new Image();
                        img.setFamousPlace_id(f.getId());
                        img.setCity_id(f.getCity_id());
                        img.setWidth(photo.getInt("width"));
                        img.setLength(photo.getInt("height"));
                        img.setSource(photo.getString("photo_reference"));
                        addImage(img);
                    }

                }
            }

            } catch(JSONException e){
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

    }
    private void getFamousPlace(City c) {

        final City cc=c;
        String query=c.getName();
        query=query.replace(" ","+");
        String url ="https://maps.googleapis.com/maps/api/place/textsearch/json?query="+query+"+points+of+interest&key="+getString(R.string.API_KEY);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseJsonFamousPlaces(response,cc);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void getPhotoAndDetail() {
        FetchPlaceRequest request= FetchPlaceRequest.builder(placeId,Arrays.asList(Place.Field.PHOTO_METADATAS)).build();
        placesClient.fetchPlace(request).addOnSuccessListener(new OnSuccessListener<FetchPlaceResponse>() {
            @Override
            public void onSuccess(FetchPlaceResponse fetchPlaceResponse) {
                Place place=fetchPlaceResponse.getPlace();
                PhotoMetadata photoMetadata=place.getPhotoMetadatas().get(0);
                FetchPhotoRequest fetchPhotoRequest=FetchPhotoRequest.builder(photoMetadata).build();
                placesClient.fetchPhoto(fetchPhotoRequest).addOnSuccessListener(new OnSuccessListener<FetchPhotoResponse>() {
                    @Override
                    public void onSuccess(FetchPhotoResponse fetchPhotoResponse) {
                        imageView.setImageBitmap(fetchPhotoResponse.getBitmap());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
           Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getImage(Image img)
    {
        StorageReference storageReference=FirebaseStorage.getInstance().getReference("image");
        storageReference.child(img.getCity_id()).getBytes(10000)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Toast.makeText(MainActivity.this,"Downloaded Successfully",Toast.LENGTH_SHORT).show();
                        Bitmap btm=BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                        imageView.setImageBitmap(btm);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(MainActivity.this,exception.getMessage(),Toast.LENGTH_SHORT).show();
                // ...
            }
        });
    }
    public void setupPlacesAutoComplete(){
    places_fragment=(AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.places_autocomplete_fragment);
    places_fragment.setPlaceFields(placeFields);
    places_fragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
        @Override
        public void onPlaceSelected(@NonNull Place place) {
            Toast.makeText(MainActivity.this,""+place.getName(),Toast.LENGTH_SHORT).show();
            placeId=place.getId();
        }

        @Override
        public void onError(@NonNull Status status) {
            Toast.makeText(MainActivity.this,""+status.getStatusMessage(),Toast.LENGTH_SHORT).show();

        }
    });
    }
    public void initializePlaces()
    {
        Places.initialize(this,getString(R.string.API_KEY));
        placesClient=Places.createClient(this);
    }


    public void addData(ArrayList<City> cities, ArrayList<FamousPlace> famousPlaces, ArrayList<Image> images)
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


//    public void addCity(City city)
//    {
//        DatabaseReference cityTableRef= FirebaseDatabase.getInstance().getReference("City");
//        String id=cityTableRef.push().getKey();
//        cityTableRef.child(id).setValue(city);
//    }
//    public void addFamousPlace(FamousPlace famousPlace)
//    {
//        DatabaseReference famousPlaceTableRef= FirebaseDatabase.getInstance().getReference("famousPlaces");
//        String id=famousPlaceTableRef.push().getKey();
//        famousPlaceTableRef.child(id).setValue(famousPlace);
//    }
//
//    public void addImage(Image image)
//    {
//        DatabaseReference imageTableRef= FirebaseDatabase.getInstance().getReference("Images");
//        String id=imageTableRef.push().getKey();
//        imageTableRef.child(id).setValue(image);
//    }

    public void addCity(City city)
    {
        String cname="";
        try {
            DatabaseReference cityTableRef = FirebaseDatabase.getInstance().getReference("City");
            cname=city.getName();
            cityTableRef.child(city.getName()).setValue(city);
        }
        catch(Exception e)
        {
            Toast.makeText(MainActivity.this,"City :"+cname,Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    public void addFamousPlace(FamousPlace famousPlace)
    {
        String fname="",cname="";
        try {
            DatabaseReference famousPlaceTableRef = FirebaseDatabase.getInstance().getReference("famousPlaces");
fname=famousPlace.getName();
cname=famousPlace.getCity_name();
            famousPlaceTableRef.child(famousPlace.getCity_name()).child(famousPlace.getName()).setValue(famousPlace);
        }
        catch(Exception e)
        {
            Toast.makeText(MainActivity.this,"FamousPlace :"+fname+"    "+cname,Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void addImage(Image image)
    {
    DatabaseReference cityTableRef = FirebaseDatabase.getInstance().getReference("Images");

    cityTableRef.child(image.getCity_id()).child(image.getFamousPlace_id()).setValue(image);


    }
    public void getCityFromAPI(String id) {
        List<Place.Field> list = Arrays.asList(Place.Field.PHOTO_METADATAS, Place.Field.NAME, Place.Field.ID,
                Place.Field.RATING, Place.Field.USER_RATINGS_TOTAL);
        FetchPlaceRequest request = FetchPlaceRequest.builder(placeId, list).build();
            placesClient.fetchPlace(request).addOnSuccessListener(new OnSuccessListener<FetchPlaceResponse>() {
            @Override
                public void onSuccess(FetchPlaceResponse fetchPlaceResponse) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                Place place = fetchPlaceResponse.getPlace();
                City city=new City();
                city.setId(place.getId());
                city.setName(place.getName());
                List<PhotoMetadata> imagesMetaData = place.getPhotoMetadatas();
                if(imagesMetaData!=null) {
                    for (int i = 0; i < imagesMetaData.size(); i++) {
                        Image img = new Image();
                        img.setCity_id(place.getId());
                        img.setFamousPlace_id("");
                        img.setLength(imagesMetaData.get(i).getHeight());
                        img.setWidth(imagesMetaData.get(i).getWidth());
                        images.add(img);
                    }
                }
                addCity(city);
                getFamousPlace(city);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Faliure"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public Uri getUriFromBitmap(Bitmap inImage,String title)
    {
        File tempDir= Environment.getExternalStorageDirectory();
        tempDir=new File(tempDir.getAbsolutePath()+"/.temp/");
        tempDir.mkdir();
        File tempFile = null;
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            byte[] bitmapData = bytes.toByteArray();
            tempFile = File.createTempFile(title, ".jpg", tempDir);
            //write the bytes in file
            FileOutputStream fos = new FileOutputStream(tempFile);
            fos.write(bitmapData);
            fos.flush();
            fos.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return Uri.fromFile(tempFile);
    }
    public void firebaseTest()
    {
      getCityFromAPI(placeId);
    }
}