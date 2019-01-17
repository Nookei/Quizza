package com.wvs.quizza.dto;


/**
 * @author Martin Beyer
 * DTO zum Speichern von Information über User
 */
public class User {
    private String username;
    private String passwort;
    private boolean isAdmin;

    public User(String username, String passwort, boolean isAdmin) {
        this.username = username;
        this.passwort = passwort;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswort() {
        return passwort;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
