package com.github.davinkevin.betmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Created by kevin on 11/08/15 for betmanager
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Match {

    private Long id;
    private Team localTeam;
    private Team awayTeam;
    private ZonedDateTime date;
    private Integer localScore;
    private Integer awayScore;
    private Result result;
    private Competition competition;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public Match setId(Long id) {
        this.id = id;
        return this;
    }

    @ManyToOne
    public Team getLocalTeam() {
        return localTeam;
    }

    public Match setLocalTeam(Team localTeam) {
        this.localTeam = localTeam;
        return this;
    }

    @ManyToOne
    public Team getAwayTeam() {
        return awayTeam;
    }

    public Match setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
        return this;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public Match setDate(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public Integer getLocalScore() {
        return localScore;
    }

    public Match setLocalScore(Integer localScore) {
        this.localScore = localScore;
        return this;
    }

    public Integer getAwayScore() {
        return awayScore;
    }

    public Match setAwayScore(Integer awayScore) {
        this.awayScore = awayScore;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public Result getResult() {
        return result;
    }

    public Match setResult(Result result) {
        this.result = result;
        return this;
    }

    @ManyToOne @JsonIgnore
    public Competition getCompetition() {
        return competition;
    }

    public Match setCompetition(Competition competition) {
        this.competition = competition;
        return this;
    }

    @PrePersist @PreUpdate
    public void calculateResult() {
        if (Objects.nonNull(localScore) && Objects.nonNull(awayScore)) {
            result = localScore.equals(awayScore) ? Result.N : localScore > awayScore ? Result.ONE : Result.TWO;
        }
    }
}
