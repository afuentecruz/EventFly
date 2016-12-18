package com.asee.alberto.eventfly.rest;

import com.asee.alberto.eventfly.manager.UserManager;
import com.asee.alberto.eventfly.model.TokenDto;
import com.asee.alberto.eventfly.model.UserDB;
import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.Proxy;

/**
 * Created by alberto on 18/12/16.
 */

    public class TokenAuthenticator implements Authenticator {

        @Override
        public Request authenticate(Proxy proxy, Response response) throws IOException {

            UserDB user = UserManager.getUser();

            // Refresh token using an api request
            String userToken = ApiService.getClient().refreshToken(new TokenDto(user.getEmail(), user.getPassword())).getToken();

            UserManager.saveUserToken(userToken);

            // Add new header to rejected request and retry it
            return response.request().newBuilder().header("token", userToken).build();
        }

    @Override
    public Request authenticateProxy(Proxy proxy, Response response) throws IOException {
        return null;
    }
}
