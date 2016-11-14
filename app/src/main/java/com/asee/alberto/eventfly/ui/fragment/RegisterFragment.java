package com.asee.alberto.eventfly.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.asee.alberto.eventfly.R;
import com.asee.alberto.eventfly.ui.activity.MainActivity;


public class RegisterFragment extends Fragment {

    private EditText mUser;
    private EditText mPassword;
    private EditText mPasswordRepeat;
    private Button mCancelButton;
    private Button mRegisterButton;


    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        mUser = (EditText) v.findViewById(R.id.edit_username);
        mPassword = (EditText) v.findViewById(R.id.edit_password);
        mPasswordRepeat = (EditText) v.findViewById(R.id.edit_password_repeat);
        mCancelButton = (Button) v.findViewById(R.id.button_cancel);
        mRegisterButton = (Button) v.findViewById(R.id.button_register);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPassword.equals(mPasswordRepeat)) { //if both passwords match
                    //TODO save in database
                    //Load main activity
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    startActivity(intent);
                }else{
                    Snackbar.make(view, "Passwords doesn't match! :(", Snackbar.LENGTH_SHORT)
                            .show();
                }
            }
        }); // </mRegisterButton.setOnClickListener>

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO remove fragment and return to login activity
            }
        });

        return v;
    }
}
