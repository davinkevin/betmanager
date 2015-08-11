package com.github.davinkevin.betmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by kevin on 11/08/15 for betmanager
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class User /*implements UserDetails*/ {

    private Long id;
    private String username;
    private String password;
    private Boolean enabled;
    private Set<Role> roles;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    @ManyToMany
    public Set<Role> getRoles() {
        return roles;
    }

    public User setRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    /*@Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(toSet());
    }*/

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public User setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    @Transient public String getUsername() {
        return username;
    }
    @Transient public boolean isAccountNonExpired() {
        return enabled;
    }
    @Transient public boolean isAccountNonLocked() {
        return enabled;
    }
    @Transient public boolean isCredentialsNonExpired() {
        return enabled;
    }
}
