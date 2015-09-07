package com.github.davinkevin.betmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

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

    @Enumerated(EnumType.STRING)
    public Result getValue() {
        return value;
    }

    public Bet setValue(Result value) {
        this.value = value;
        return this;
    }

    @ManyToOne @JsonIgnore
    public Match getMatch() {
        return match;
    }

    public Bet setMatch(Match match) {
        this.match = match;
        return this;
    }

    @ManyToOne @JsonIgnore
    public User getUser() {
        return user;
    }

    public Bet setUser(User user) {
        this.user = user;
        return this;
    }

    @Transient @JsonIgnore
    public Boolean isValid() {
        if (isNull(value) || isNull(match) || isNull(match.getResult())) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    public Boolean won() {
        return value == match.getResult();
    }

    @Transient @JsonIgnore
    public Boolean hasValue() {
        if (isNull(match) || match.getResult() == Result.NONE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bet)) return false;

        Bet bet = (Bet) o;
        return new EqualsBuilder()
                .append(id, bet.id)
                .append(value, bet.value)
                .append(match, bet.match)
                .append(user, bet.user)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(value)
                .append(match)
                .append(user)
                .toHashCode();
    }

    @Transient @JsonProperty("matchId")
    public Long getMatchId() {
        return nonNull(match) ? match.getId() : null;
    }
}
