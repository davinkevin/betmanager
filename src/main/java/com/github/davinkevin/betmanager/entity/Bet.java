package com.github.davinkevin.betmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by kevin on 11/08/15 for betmanager
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bet {

    private Long id;
    private Result value;
    private Match match;
    private User user;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public Bet setId(Long id) {
        this.id = id;
        return this;
    }

    public Result getValue() {
        return value;
    }

    public Bet setValue(Result value) {
        this.value = value;
        return this;
    }

    @ManyToOne
    public Match getMatch() {
        return match;
    }

    public Bet setMatch(Match match) {
        this.match = match;
        return this;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public Bet setUser(User user) {
        this.user = user;
        return this;
    }
}
