package com.asee.alberto.eventfly.repository;

import android.util.Log;

import com.asee.alberto.eventfly.manager.UserManager;
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

public class MessageRepository {


    public interface onMessageResponse {

        void onSuccess(List<MessageDB> messages);

        void onError(String message);
    }

    public interface onSendMessageToServer {

        void onSuccess();

        void onError(String message);
    }


    /**
     * This method return all the messages from the server of given Event Id
     *
     * @param messageEvent    The message event ID
     * @param messageResponse The Callback
     */
    public static void getMessagesForEvent(String messageEvent, final onMessageResponse messageResponse) {

        ApiService.getClient().getMessagesForEvent(messageEvent, new Callback<List<MessageDB>>() {

            @Override
            public void success(List<MessageDB> messages, Response response) {
                messageResponse.onSuccess(messages);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Error", error.getLocalizedMessage());
                messageResponse.onError(error.getLocalizedMessage());

            }
        });
    }


    /**
     * This method send new message to server associated to an event ID (messageEvent)
     */
    public static void sendMessageToServer(String messageEvent, String message, final onSendMessageToServer onSendMessageToServer) {


        ApiService.getAuthClient().sendNewMessage(new MessageDto(messageEvent, message, UserManager.getUserToken()), new Callback<ResponseBody>() {
            @Override
            public void success(ResponseBody responseBody, Response response) {
                onSendMessageToServer.onSuccess();
            }

            @Override
            public void failure(RetrofitError error) {
                onSendMessageToServer.onError(error.getLocalizedMessage());

            }
        });

    }
}
