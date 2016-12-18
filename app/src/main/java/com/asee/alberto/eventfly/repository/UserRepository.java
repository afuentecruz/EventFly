package com.asee.alberto.eventfly.repository;

import android.util.Log;

import com.asee.alberto.eventfly.manager.UserManager;
import com.asee.alberto.eventfly.model.UserDB;
import com.asee.alberto.eventfly.model.UserDto;
import com.asee.alberto.eventfly.rest.ApiService;
import com.squareup.okhttp.Response;

import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by alberto on 18/12/16.
 */

public class UserRepository {

    public interface onUserResponse {

        void onSuccess();

        void onError(String err);
    }

    public UserRepository(){
    }

    /**
     * This method register a new user in the db
     */
    public static void registerUser(String email, String password, final onUserResponse userResponse) {

        ApiService.getClient().registerUser(new UserDto(email, email, password, "1234"), new Callback<String>() {

            @Override
            public void success(String s, retrofit.client.Response response) {
                userResponse.onSuccess();

            }

            @Override
            public void failure(RetrofitError error) {
                userResponse.onError(error.getLocalizedMessage());

            }
        });
    }
}
