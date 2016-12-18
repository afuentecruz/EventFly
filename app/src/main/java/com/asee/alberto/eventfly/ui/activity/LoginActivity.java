package com.asee.alberto.eventfly.ui.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.support.v4.app.FragmentTransaction;


import com.asee.alberto.eventfly.R;
import com.asee.alberto.eventfly.manager.UserManager;
import com.asee.alberto.eventfly.model.TokenDB;
import com.asee.alberto.eventfly.model.TokenDto;
import com.asee.alberto.eventfly.model.UserDB;
import com.asee.alberto.eventfly.rest.ApiService;
import com.asee.alberto.eventfly.ui.fragment.RegisterFragment;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by alberto on 29/10/16.
 */

public class LoginActivity extends AppCompatActivity {

    public static String TAG = "LoginActivity";

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
            public void onClick(final View v) {

                // If the input fields are not empty
                if (!mUser.getText().toString().isEmpty() && !mPassword.getText().toString().isEmpty()) {
                    // Authenticate with the api and request an access token
                    if (checkUserInDB(mUser.getText().toString(), mPassword.getText().toString())) {

                        ApiService.getClient().authenticateUser(new TokenDto(mUser.getText().toString(), mPassword.getText().toString()), new Callback<TokenDB>() {
                            @Override
                            public void success(TokenDB token, Response response) {
                                Log.i(TAG, "Token: " + token.getToken());

                                //Save or update the user in the local db
                                UserManager.saveOrUpdateUser(new UserDB(mUser.getText().toString(), mUser.getText().toString(), mPassword.getText().toString(), "", token.getToken()));
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Log.d(TAG, "Error: " + error.getLocalizedMessage());
                                Snackbar snack = Snackbar.make(v, "Wrong user or password", Snackbar.LENGTH_SHORT);
                                View snackView = snack.getView();
                                snackView.setBackgroundColor(Color.WHITE);
                                snack.show();
                            }
                        });

                    } else {
                        // Get snackbar view to set white background color
                        Snackbar snack = Snackbar.make(v, "Please, enter an user & password", Snackbar.LENGTH_SHORT);
                        View snackView = snack.getView();
                        snackView.setBackgroundColor(Color.WHITE);
                        snack.show();
                    }
                }
            }
        });
        mRegisterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.login_container, new RegisterFragment()).addToBackStack(null).commit();
                Log.i("LoginActivity", "onClick register button");
            }
        });
    }


    private boolean checkUserInDB(String username, String password) {
        UserDB user = UserManager.getUserByName(username);

        if (user != null && user.getPassword().equals(password))
            return true;
        else
            return false;
    }
}