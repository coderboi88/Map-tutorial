package com.example.themap;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener{

    private GoogleMap mMap;
    private static final LatLng MUMBAI = new LatLng(19.0760,72.8777);
    private static final LatLng CHENNAI = new LatLng(13.0827,80.2707);
    private static final LatLng DELHI = new LatLng(28.7041,77.1025);

    private Marker mMumbai;
    private Marker mChennai;
    private Marker mDelhi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        List<Marker> markerList = new ArrayList<>();

        mMumbai = mMap.addMarker(new MarkerOptions()
                .position(MUMBAI)
                .title("Mumbai"));
        mMumbai.setTag(0);
        markerList.add(mMumbai);

        mChennai = mMap.addMarker(new MarkerOptions()
                .position(CHENNAI)
                .title("Chennai")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mChennai.setTag(0);
        markerList.add(mChennai);

        mDelhi = mMap.addMarker(new MarkerOptions()
                .position(DELHI)
                .title("Delhi")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        mDelhi.setTag(0);
        markerList.add(mDelhi);

        mMap.setOnMarkerClickListener(this);
        for(Marker m: markerList){
            LatLng latLng = new LatLng(m.getPosition().latitude,m.getPosition().longitude);
            mMap.addMarker(new MarkerOptions().position(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,2));

        }

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Integer clickCount = (Integer) marker.getTag();

        if(clickCount!=null){
            clickCount = clickCount+1;
        }

        marker.setTag(clickCount);
        Toast.makeText(this,marker.getTitle()+"has been clicked"+clickCount+"times",Toast.LENGTH_SHORT).show();
        return false;
    }
}
