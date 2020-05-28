package com.example.project;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class AddressActivity extends AppCompatActivity implements OnMapReadyCallback {

    static TextView address;
    LatLng companyLocation;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        SupportMapFragment smf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        smf.getMapAsync(this);



        address = (TextView) findViewById(R.id.addressText);

        address.setText(getIntent().getStringExtra("ADDRESS"));
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        map = googleMap;
        companyLocation = new LatLng(41.0422, 29.0093);
        MarkerOptions o = new MarkerOptions().position(companyLocation).title("Umbrella Corp");
        map.addMarker(o);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(companyLocation, 17));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.add("To Company").setShowAsAction((MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW));
        menu.add("Normal View").setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        menu.add("Satellite View").setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        menu.add("Hybrid View").setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        menu.add("Terrain View").setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        String s = item.getTitle().toString();
        if(s.equals("To Company"))
        {
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(companyLocation, 17));
        }

        if(s.equals("Normal View"))
        {
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }

        if(s.equals("Satellite View"))
        {
            map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }

        if(s.equals("Hybrid View"))
        {
            map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }

        if(s.equals("Terrain View"))
        {
            map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        }

        return super.onOptionsItemSelected(item);
    }
}
