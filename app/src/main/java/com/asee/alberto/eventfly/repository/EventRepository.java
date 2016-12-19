package com.asee.alberto.eventfly.repository;

import android.util.Log;

import com.asee.alberto.eventfly.manager.UserManager;
import com.asee.alberto.eventfly.model.EventDB;
import com.asee.alberto.eventfly.model.EventDto;
import com.asee.alberto.eventfly.model.MessageDB;
import com.asee.alberto.eventfly.model.MessageDto;
import com.asee.alberto.eventfly.rest.ApiService;
import com.squareup.okhttp.ResponseBody;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by alberto on 18/12/16.
 */

public class EventRepository {

    public interface onSendEventToServer {

        void onSuccess();

        void onError(String message);
    }

    /**
     * This method send a new event to server
     */

    public static void sendEventToServer(String eventName, double latitude, double longitude, float radius, final onSendEventToServer onSendEventToServer) {


        ApiService.getAuthClient().sendNewEvent(new EventDto(eventName, latitude, longitude, radius, UserManager.getUserToken()), new Callback<ResponseBody>() {
            @Override
            public void success(ResponseBody responseBody, Response response) {
                onSendEventToServer.onSuccess();
            }

            @Override
            public void failure(RetrofitError error) {
                onSendEventToServer.onError(error.getLocalizedMessage());

            }
        });

    }
}
