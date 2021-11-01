package com.nguyenduc.model.login;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {
    private Long id;

    private String token;

    private String type = "Bearer";

    private String username;

    private String firstName;

    private String lastName;

    private Collection<? extends GrantedAuthority> roles;

    public JwtResponse(Long id, String accessToken, String username, String firstName, String lastName, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.token = accessToken;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String token) {
        this.token = token;
    }

    public String getTypeToken() {
        return type;
    }

    public void setTypeToken(String typeToken) {
        this.type = typeToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }
}
