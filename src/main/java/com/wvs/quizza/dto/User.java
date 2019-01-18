package com.wvs.quizza.dto;


import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Martin Beyer
 * DTO zum Speichern von Information über User
 */
@Entity(name = "tblUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    @ColumnTransformer(read = "gp_sym_decrypt(passwort, ‘mySecretKey’)", write = "pgp_sym_encrypt(?, ‘mySecretKey’)")
    //TODO: secret erstellem
    private String passwort;
    private boolean isAdmin;

    public User() {
    }

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
