package com.asee.alberto.eventfly.ui.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.asee.alberto.eventfly.R;
import com.asee.alberto.eventfly.manager.EventManager;
import com.asee.alberto.eventfly.model.EventDB;
import com.asee.alberto.eventfly.ui.activity.MainActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.util.Map;

public class CreateEventFragment extends Fragment{

    // This interface defines the type of messages that i want to communicate to my owner (mapFragment)
    public interface CreateEventListener extends Parcelable{
        // Onsuccess, when an event is correctly created
        public abstract void onSuccess(EventDB event);
        // When there is no event created
        public abstract void onNoEvent();
    }

    private static String TAG = "CreateEventFragment";

    private EditText mEventName;

    private EditText mEventDescription;

    private EditText mEventTags;

    private FloatingActionButton mFloatButton;

    private Double latitude;

    private Double longitude;

    private CreateEventListener eventListener;

    public CreateEventFragment(){
        this.eventListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_create_event, container, false);
        ((MainActivity) getActivity()).setActionBarTitle("Create Event");

        Bundle bundle = this.getArguments();
        if(bundle != null) {
            latitude = bundle.getDouble("latitude");
            longitude = bundle.getDouble("longitude");
            eventListener = bundle.getParcelable("listener");

            Log.i(TAG, " >>> From CreateEventFragment " + latitude + " " + longitude);
        }
        mEventName = (EditText) v.findViewById(R.id.event_name);
        mEventDescription = (EditText) v.findViewById(R.id.event_description);
        mEventTags = (EditText) v.findViewById(R.id.event_tags);
        mFloatButton = (FloatingActionButton) v.findViewById(R.id.create_floating_action_button);

        mFloatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mEventName.getText().toString().isEmpty() || mEventDescription.getText().toString().isEmpty() || mEventTags.getText().toString().isEmpty()){
                    createSnackbar(getView(), "Please fill the form");
                }else{

                    EventDB event = new EventDB();
                    event.setDescription(mEventDescription.getText().toString());
                    event.setName(mEventName.getText().toString());
                    event.setLatitude(latitude);
                    event.setLongitude(longitude);
                    EventManager.saveOrUpdateEvent(event);

                    // Notify the listener and pass the new object created
                    if(eventListener != null){
                        getActivity().onBackPressed();
                        eventListener.onSuccess(event);

                    }
                }
            }
        });
        return v;
    }

    public void createSnackbar(View v, String text){
        Snackbar snack = Snackbar.make(v, text, Snackbar.LENGTH_SHORT);
        View snackView = snack.getView();
        snackView.setBackgroundColor(Color.WHITE);
        snack.show();
    }
}
