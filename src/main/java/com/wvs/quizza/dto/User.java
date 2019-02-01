package com.wvs.quizza.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Objects;

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
    @JsonIgnore
    private String passwort;
    @JsonIgnore
    private String[] roles;

    public User() {
    }

    public User(String username, String passwort, String roles) {
        this.username = username;
        setPasswort(passwort);
        this.roles = new String[1];
        this.roles[0] = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = PASSWORD_ENCODER.encode(passwort);
    }

    public boolean isAdmin() {
        return Objects.equals(roles[0], "admin");
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
