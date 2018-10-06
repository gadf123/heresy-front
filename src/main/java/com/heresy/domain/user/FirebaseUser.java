package com.heresy.domain.user;

import lombok.Data;

@Data
public class FirebaseUser {

    private String uid;

    public FirebaseUser(String uid) {
        this.uid = uid;
    }
}
