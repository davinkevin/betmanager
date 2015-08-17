package com.github.davinkevin.betmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.davinkevin.betmanager.entity.Match;
import com.github.davinkevin.betmanager.entity.Sport;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by kevin on 11/08/15 for betmanager
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Competition {

    private Long id;
    private String name;
    private Sport sport;
    private Set<Match> matchs;
    private String urlLogo;

    @Id @GeneratedValue
    public Long getId() {
        return id;
    }

    public Competition setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Competition setName(String name) {
        this.name = name;
        return this;
    }

    @ManyToOne
    public Sport getSport() {
        return sport;
    }

    public Competition setSport(Sport sport) {
        this.sport = sport;
        return this;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "competition")
    public Set<Match> getMatchs() {
        return matchs;
    }

    public Competition setMatchs(Set<Match> matchs) {
        this.matchs = matchs;
        return this;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public Competition setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Competition)) return false;

        Competition that = (Competition) o;
        return new EqualsBuilder()
                .append(id, that.id)
                .append(name, that.name)
                .append(sport, that.sport)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(sport)
                .toHashCode();
    }
}
