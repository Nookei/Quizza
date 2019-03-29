package com.wvs.quizza.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

/**
 * @author Martin Beyer
 * DTO zum Speichern von Information über User
 */
@Entity()
@Table(name = "tbl_User")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String passwort;
    private int isAdmin;

    public User() {
    }

    public User(Long id, String username, String passwort, int isAdmin) {
        this.id = id;
        this.username = username;
        encryptPasswort(passwort);
        this.isAdmin = isAdmin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        encryptPasswort(passwort);
    }

    public boolean isAdmin() {
        return isAdmin == 1;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    private void encryptPasswort(String unencrypted) {
        passwort = PASSWORD_ENCODER.encode(unencrypted);
    }

}
