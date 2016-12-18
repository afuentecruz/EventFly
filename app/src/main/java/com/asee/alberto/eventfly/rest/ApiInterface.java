package com.asee.alberto.eventfly.rest;

import com.asee.alberto.eventfly.model.EventDB;
import com.asee.alberto.eventfly.model.EventDto;
import com.asee.alberto.eventfly.model.MessageDB;
import com.asee.alberto.eventfly.model.MessageDto;
import com.asee.alberto.eventfly.model.TokenDB;
import com.asee.alberto.eventfly.model.TokenDto;
import com.asee.alberto.eventfly.model.UserDB;
import com.asee.alberto.eventfly.model.UserDto;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;


import java.util.List;


import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;


/**
 * Created by alberto on 17/12/16.
 */

public interface ApiInterface {

    /**
     * Api interface with the endpoints to call the api
     */
    @GET("/api/getEvents")
    void getEvents(Callback<List<EventDto>> eventCallback);

    @GET("/api/getMessagesWithOwners/{eventId}")
    void getMessagesForEvent(@Path("eventId") String eventId, Callback<List<MessageDB>> messageCallback);

    @POST("/api/createMessage")
    void sendNewMessage(@Body MessageDto messagePost, Callback<ResponseBody> responseCallback);

    @POST("/api/registerUser")
    void registerUser(@Body UserDto userPost, Callback<String> responseCallback);

    @POST("/api/authenticateUser")
    void authenticateUser(@Body TokenDto tokenRequest, Callback<TokenDB> token);

    @POST("/api/authenticateUser")
    TokenDB refreshToken(@Body TokenDto tokenRequest);

}
