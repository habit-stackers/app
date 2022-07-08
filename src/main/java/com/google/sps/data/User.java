package com.google.sps.data;

public final class User {
    private final String username;
    private final String sessionKey;

    public User(String username, String sessionKey) {
        this.username = username;
        this.sessionKey = sessionKey;
    }
}
