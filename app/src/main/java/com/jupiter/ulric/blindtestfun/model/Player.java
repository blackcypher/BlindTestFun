package com.jupiter.ulric.blindtestfun.model;

/**
 * Created by ulric on 06/05/2018.
 */

public class Player {

    private String id;
    private String email;

    public Player(){
    }

    public Player(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
