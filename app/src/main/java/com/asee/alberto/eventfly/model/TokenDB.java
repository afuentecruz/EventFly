package com.asee.alberto.eventfly.model;

/**
 * Created by alberto on 17/12/16.
 */

public class TokenDB {
    /**
     * Token class for user api authentication
     */
    private String token;

    TokenDB(String token){
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }

    public void setToken(String token){
        this.token = token;
    }


}
