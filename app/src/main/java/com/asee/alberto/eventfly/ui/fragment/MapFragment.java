package com.asee.alberto.eventfly.ui.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.EventLog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asee.alberto.eventfly.manager.EventManager;
import com.asee.alberto.eventfly.model.EventDB;
import com.asee.alberto.eventfly.ui.activity.MainActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.asee.alberto.eventfly.R;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;


public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener, GoogleMap.OnInfoWindowClickListener {

    //TAG that identifies the fragment
    public static String TAG = "MapFragment";

    // gMap instance
    private GoogleMap mMap;
    // Floating Button for put new events
    private FloatingActionButton mFloatButton;
    // Boolean that lets the user create an event
    private boolean putEvent;

    Snackbar snack;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        mFloatButton = (FloatingActionButton) v.findViewById(R.id.floatingActionButton);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFloatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putEvent = true;
                mFloatButton.setVisibility(View.INVISIBLE);

                snack = Snackbar.make(v, "Long tap on map to define an event", Snackbar.LENGTH_INDEFINITE);
                View snackView = snack.getView();
                snackView.setBackgroundColor(Color.WHITE);
                snack.show();
            }
        });
        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (checkPermission()) { //Checks if had system permissions
            Log.i(TAG, " >>> User perms granted!");
            mMap.setMyLocationEnabled(true);
            centerMapOnMyLocation();
            drawAllMarkers();
        } else {
            Log.i(TAG, "User permission NOT granted");
        }

        mMap.setOnMapLongClickListener(this);
        mMap.setOnInfoWindowClickListener(this);

    }

    @Override
    public void onMapLongClick(LatLng point) {

        if(putEvent) { //If the user pressed the add event button...
            snack.dismiss();
            Vibrator v = (Vibrator) this.getContext().getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(50);
            mFloatButton.setVisibility(View.VISIBLE);

            Log.i(TAG, "  >>> Loong tapped, point=" + point.latitude + " " + point.longitude);


            CreateEventFragment createEventFragment = new CreateEventFragment();
            CreateEventFragment.CreateEventListener listener = new CreateEventFragment.CreateEventListener(){
                @Override
                public void onSuccess(EventDB event) {
                    Log.i(TAG, " >>> Receive event object " + event.getName() + " " + event.getDescription());
                    drawNewMarker(event);
                }

                @Override
                public void onNoEvent() {
                    Log.i(TAG, " >>> no event :(");
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                }
            };
            // Launches new fragment
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            Bundle bundle = new Bundle();
            bundle.putDouble("latitude", point.latitude);
            bundle.putDouble("longitude", point.longitude);
            bundle.putParcelable("listener", listener);
            createEventFragment.setArguments(bundle);
            //Launches CreateEventFragment and add MapFragment to back stack
            fragmentTransaction.add(R.id.main_content, createEventFragment, "CreateEventFragment");
            fragmentTransaction.addToBackStack("MapFragment");

            // Commit the transaction
            fragmentTransaction.commit();

        }
        putEvent = false;
    }

    private void centerMapOnMyLocation() {
        Log.i(TAG, " >>> centerMapOnMyLocation");
        LocationManager locationManager;
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        if (checkPermission()) {
            Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
            if (location != null) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                        .zoom(17)                   // Sets the zoom
                        .bearing(90)                // Sets the orientation of the camera to east
                        .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                        .build();                   // Creates a CameraPosition from the builder
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        }

    }

    /**
     * Checks app location permissions
     *
     * @return true if the user granted permissions, false otherwise.
     */
    private boolean checkPermission() {
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.VIBRATE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    private void drawAllMarkers(){
        Log.i(TAG, " >>> Drawing marker");

        List<EventDB> eventDBList = EventManager.getAllEvents();

        for(EventDB event : eventDBList){
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(event.getLatitude(), event.getLongitude()))
                    .title(event.getName()));
        }

    }

    private void drawNewMarker(EventDB event){
        Log.i(TAG, " >>> Drawing a new marker");
        mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(event.getLatitude(), event.getLongitude()))
                    .title(event.getName()));
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

        Log.i(TAG, " >>> Marker clicked" + marker.getTitle());
        openMessageFragment(marker.getTitle());
    }

    public void openMessageFragment(String eventName){
        Log.i(TAG, " >>> openMessageFragment " + eventName);
        // Launches new fragment
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        Bundle bundle = new Bundle();
        bundle.putString("eventName", eventName);

        MessageFragment messageFragment = new MessageFragment();
        messageFragment.setArguments(bundle);
        //Launches CreateEventFragment and add MapFragment to back stack
        fragmentTransaction.add(R.id.main_content, messageFragment, "messageFragment");
        fragmentTransaction.addToBackStack("MapFragment");

        // Commit the transaction
        fragmentTransaction.commit();
    }

    @Override
    public void onResume(){
        super.onResume();
        if(mMap != null){
            centerMapOnMyLocation();
        }

    }
}
