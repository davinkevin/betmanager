package com.github.davinkevin.betmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by kevin on 11/08/15 for betmanager
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {

    private Long id;
    private String name;
    private String flag;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public Team setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Team setName(String name) {
        this.name = name;
        return this;
    }

    public String getFlag() {
        return flag;
    }

    public Team setFlag(String flag) {
        this.flag = flag;
        return this;
    }
}
