package com.github.davinkevin.betmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by kevin on 11/08/15 for betmanager
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Role implements Serializable {

    private Integer id;
    private String name;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public Role setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Role setName(String name) {
        this.name = name;
        return this;
    }
}
