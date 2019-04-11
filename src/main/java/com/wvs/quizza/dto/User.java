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
    private String password;
    private Integer isAdmin;

    public User() {
    }

    public User(Long id, String username, String passwort, Integer isAdmin) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        encryptPasswort(password);
    }

    public boolean isAdmin() {
        return isAdmin == 1;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    private void encryptPasswort(String unencrypted) {
        password = PASSWORD_ENCODER.encode(unencrypted);
    }
}