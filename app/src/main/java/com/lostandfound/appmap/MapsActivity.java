package com.lostandfound.appmap;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private EditText txtAddress;
    private Button btnSearch;
    private List<Address> addResults = null;
    private String searchText = "";

//    public void init(){
//        txtAddress = (EditText)findViewById(R.id.txtSearch);
//        btnSearch = (Button) findViewById(R.id.btnSearch);
//        btnSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                searchText = txtAddress.getText().toString();
//                setLocation(searchText);
//            }
//        });
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        txtAddress = (EditText)findViewById(R.id.txtSearch);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchText = txtAddress.getText().toString();
                setLocation(searchText);
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Philippines and move the camera
        LatLng phil = new LatLng(14.416892, 121.04124);
        mMap.addMarker(new MarkerOptions().position(phil).title("I'm here in the Philippines!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(phil));
        //zoom in to current location
        CameraUpdate searchLocation = CameraUpdateFactory.newLatLngZoom(phil, 14);
        mMap.animateCamera(searchLocation);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);

    }

    public void setLocation(String searchText){
        Toast.makeText(getApplicationContext(), "searching " + searchText, Toast.LENGTH_LONG).show();
        LatLng p = getLatLongFromAddress(searchText);
        System.out.println(p.latitude);
        System.out.println(p.longitude);
        mMap.clear(); //clear all pins
        //add pin - pop up of pin
        setDummyMarkers(p);

        //zoom in to current location
        CameraUpdate searchLocation = CameraUpdateFactory.newLatLngZoom(p, 14);
        mMap.animateCamera(searchLocation);
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(p));

    }


    private LatLng getLatLongFromAddress(String address){
        LatLng p = null;
        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
        try{
            List<Address> addresses = geoCoder.getFromLocationName(address, 1);
            if(addresses.size() > 0){
                p = new LatLng((addresses.get(0).getLatitude()), (addresses.get(0).getLongitude()));
                return p;
            }
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "Error searching...", Toast.LENGTH_LONG).show();
        }
        return p;
    }

    public void setDummyMarkers(LatLng latLng){
//        mMap.addMarker(new MarkerOptions()
//                        .position(latLng)
//                        .title("Found")
//                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
//        );

        LatLng latLng1 = new LatLng(latLng.latitude + 0.001, latLng.longitude + 0.0001);
        Marker mark1 = mMap.addMarker(new MarkerOptions()
                        .position(latLng1)
                        .title("Lost Puppy")
                        .snippet("I've lost my brown puppy wearing bell necklace :(")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        );


        LatLng latLng2 = new LatLng(latLng.latitude + 0.003, latLng.longitude + 0.003);
        mMap.addMarker(new MarkerOptions()
                        .position(latLng2)
                        .title("Lost")
                        .snippet("Pink Parisian body bag")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))

        );


        LatLng latLng3 = new LatLng(latLng.latitude + 0.008, latLng.longitude + 0.0005);
        mMap.addMarker(new MarkerOptions()
                        .position(latLng3)
                        .title("Lost")
                        .snippet("Plain pink Armando Caruso handkerchief")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        );

        LatLng latLng4 = new LatLng(latLng.latitude + 0.002, latLng.longitude + 0.009);
        mMap.addMarker(new MarkerOptions()
                        .position(latLng4)
                        .title("Lost")
                        .snippet("Zenfone 5 in a Wellcom paper bag")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        );

        LatLng latLng5 = new LatLng(latLng.latitude - 0.008, latLng.longitude - 0.0005);
        mMap.addMarker(new MarkerOptions()
                        .position(latLng5)
                        .title("Lost")
                        .snippet("Philips headset")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        );

        LatLng latLng6 = new LatLng(latLng.latitude + 0.002, latLng.longitude + 0.008 );
        mMap.addMarker(new MarkerOptions()
                        .position(latLng6)
                        .title("Found")
                        .snippet("Brand new Zenfone")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        );

        LatLng latLng7 = new LatLng(latLng.latitude - 0.005, latLng.longitude - 0.010);
        mMap.addMarker(new MarkerOptions()
                        .position(latLng7)
                        .title("Found")
                        .snippet("Orange cat")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        );



    }

}
