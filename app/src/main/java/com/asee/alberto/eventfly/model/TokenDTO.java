package com.asee.alberto.eventfly.model;

/**
 * Created by alberto on 17/12/16.
 */

public class TokenDTO {
    /**
     * Class for request a token to the api by user email and password
     */

    //User email
    private String email;
    //User password
    private String password;

    public TokenDTO(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

}
