package com.p.smartcitytravelers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class FamousPlaceDetailActivity extends AppCompatActivity {

    ImageButton btn_direction;
    ImageView place_image;
    TextView txt_name,txt_rating,detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_famous_place_detail);
        btn_direction=findViewById(R.id.btn_direction);
        place_image=findViewById(R.id.famousPlaceDetailImage);
        txt_name=findViewById(R.id.famousPlaceDetailName);
        txt_rating=findViewById(R.id.famousPlaceDetailRating);
        detail=findViewById(R.id.famousPlaceDetail);
        final FamousPlaces famousPlaces=(FamousPlaces) getIntent().getSerializableExtra("place");
        displayImage(famousPlaces.getImageFamous(),place_image);
        txt_name.setText(famousPlaces.famousPlaceName);
        txt_rating.setText(""+famousPlaces.rating);
        detail.setText(famousPlaces.famousPlaceName);
        btn_direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", famousPlaces.latitude, famousPlaces.longitude, famousPlaces.famousPlaceName);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
               intent.setPackage("com.google.android.apps.maps");
               startActivity(intent);
            }
        });
    }

    void displayImage(String ref, ImageView imageView)
    {
        Picasso.get().load(ref).fit().into(imageView);
    }
}
