package com.examples.myvaccine;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.examples.myvaccine.databinding.ActivityNationalcolomboMapBinding;

public class NationalcolomboMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityNationalcolomboMapBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNationalcolomboMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng nationalhospitalcolombo = new LatLng(6.9187, 79.8690);
        mMap.addMarker(new MarkerOptions().position(nationalhospitalcolombo).title("National Hospital Colombo"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(nationalhospitalcolombo));
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }
}