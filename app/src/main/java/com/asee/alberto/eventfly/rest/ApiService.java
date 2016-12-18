package com.asee.alberto.eventfly.rest;

/**
 * Created by alberto on 17/12/16.
 */

import android.util.Base64;
import android.util.Log;

import com.asee.alberto.eventfly.manager.UserManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;


import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;
/**
 * APISERVICE, class that implements the http request
 * and responses to the api server.
 */
public class ApiService {

    //Server base url
    public static final String BASE_URL = "http://46.101.133.116:8585";

    //Username to authenticate with the api
    private static String username = "compadre";

    //Password to authenticate with the api
    private static String password = "compadrito";

    //Access to the endpoints interface
    private static ApiInterface client;

    //Access to an authenticate interface
    private static ApiInterface authClient;


    //Json deserializer
    private static Gson gson = new GsonBuilder()
            .setLongSerializationPolicy(LongSerializationPolicy.DEFAULT)
            .setLenient()
            .create();

    private ApiService(){}

    /**
     * Returns an instance of ApiInterface to call the api
     * @return
     */
    public static ApiInterface getClient() {
        if (client == null) {

            // set endpoint url and use OkHTTP as HTTP client,
            // we have auth in headerString

            RestAdapter.Builder builder = new RestAdapter.Builder()
                    .setEndpoint(BASE_URL)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setConverter(new GsonConverter(gson))
                    .setClient(new OkClient(new OkHttpClient()))
                    .setLogLevel(RestAdapter.LogLevel.FULL).setLog(new RestAdapter.Log() {
                        public void log(String msg) {
                            Log.i("retrofit", msg);
                        }
                    });


            if (username != null && password != null) {
                // concatenate username and password with colon for authentication
                final String credentials = username + ":" + password;
                builder.setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        // If we dont need auth, only comment the line below
                        request.addHeader("Authorization", "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP));
                    }
                });
            }
            RestAdapter adapter = builder.build();
            client = adapter.create(ApiInterface.class);
        }

        return client;
    }

    /**
     * This method returns an authenticate (with token) client
     * @return
     */
    public static ApiInterface getAuthClient() {
        if (authClient == null) {

            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setAuthenticator(new TokenAuthenticator());

            // set endpoint url and use OkHTTP as HTTP client,
            // we have auth in headerString
            RestAdapter.Builder builder = new RestAdapter.Builder()
                    .setEndpoint(BASE_URL)
                    .setLogLevel(RestAdapter.LogLevel.FULL) // this is the important line
                    .setConverter(new GsonConverter(gson))
                    .setClient(new OkClient(okHttpClient))
                    .setLogLevel(RestAdapter.LogLevel.FULL).setLog(new RestAdapter.Log() {
                        public void log(String msg) {
                            Log.i("retrofit", msg);
                        }
                    });


            if (username != null && password != null) {
                // concatenate username and password with colon for authentication
                final String credentials = username + ":" + password;
                builder.setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        // If we dont need auth, only comment the line below
                        request.addHeader("Authorization", "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP));
                        request.addHeader("token", UserManager.getUserToken());

                        //  request.addHeader("Content-Type","application/x-www-form-urlencoded");
                    }
                });
            }
            RestAdapter adapter = builder.build();
            authClient = adapter.create(ApiInterface.class);
        }

        return authClient;
    }

}
