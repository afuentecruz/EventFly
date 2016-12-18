package com.asee.alberto.eventfly.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.asee.alberto.eventfly.R;
import com.asee.alberto.eventfly.manager.UserManager;
import com.asee.alberto.eventfly.model.UserDB;
import com.asee.alberto.eventfly.ui.activity.MainActivity;

public class RegisterFragment extends Fragment {

    public static String TAG = "RegisterFragment";

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

                if(!mUser.getText().toString().isEmpty() && !mPassword.getText().toString().isEmpty() && mPassword.getText().toString().equals(mPasswordRepeat.getText().toString())){

                    if(!registerUser(view, mUser.getText().toString(), mPassword.getText().toString())){
                        Snackbar snack = Snackbar.make(view, "User already exists!", Snackbar.LENGTH_SHORT);
                        View snackView = snack.getView();
                        snackView.setBackgroundColor(Color.WHITE);
                        snack.show();
                    }else{
                        //Load main activity
                        Intent intent = new Intent(view.getContext(), MainActivity.class);
                        startActivity(intent);
                    }

                }else{
                    if(mUser.getText().toString().isEmpty() || mPassword.getText().toString().isEmpty()){
                        // Get snackbar view to set white background color
                        Snackbar snack = Snackbar.make(view, "Please enter the fields", Snackbar.LENGTH_SHORT);
                        View snackView = snack.getView();
                        snackView.setBackgroundColor(Color.WHITE);
                        snack.show();
                    }else{
                        // Get snackbar view to set white background color
                        Snackbar snack = Snackbar.make(view, "Passwords doesn't match! :(", Snackbar.LENGTH_SHORT);
                        View snackView = snack.getView();
                        snackView.setBackgroundColor(Color.WHITE);
                        snack.show();
                    }


                }
            }
        }); // </mRegisterButton.setOnClickListener>

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Remove the fragment and return to LoginActivity
                getActivity().getSupportFragmentManager().beginTransaction().remove(RegisterFragment.this).commit();
            }
        });
        return v;
    }

    public boolean registerUser(View v, String user, String password){
        Log.d(TAG, " >>> Checking for exisiting user in DB...");
        if(UserManager.getUserByName(user) == null){ //User not found, we must save it
            Log.d(TAG, " >>> User not found!");
            UserManager.saveOrUpdateUser(new UserDB(user, "", password, "", ""));
            return true;
        }else{
            return false;
        }
    }


}
