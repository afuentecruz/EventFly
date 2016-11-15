package com.asee.alberto.eventfly.ui.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.support.v4.app.FragmentTransaction;


import com.asee.alberto.eventfly.R;
import com.asee.alberto.eventfly.ui.fragment.RegisterFragment;

/**
 * Created by alberto on 29/10/16.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText mUser;
    private EditText mPassword;
    private Button mLoginButton;
    private Button mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUser = (EditText) findViewById(R.id.edit_username);
        mPassword = (EditText) findViewById(R.id.edit_password);
        mLoginButton = (Button) findViewById(R.id.button_login);
        mRegisterButton = (Button) findViewById(R.id.button_register);


        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mUser.getText().toString().isEmpty() && !mPassword.getText().toString().isEmpty()){
                    //TODO check database

                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    // Get snackbar view to set white background color
                    Snackbar snack = Snackbar.make(v, "Please, enter an user & password", Snackbar.LENGTH_SHORT);
                    View snackView = snack.getView();
                    snackView.setBackgroundColor(Color.WHITE);
                    snack.show();
                }
            }
        });
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.login_container, new RegisterFragment()).addToBackStack(null).commit();
                Log.i("LoginActivity", "onClick register button");
            }
        });
    }
}
