package com.github.davinkevin.betmanager.dto;

import com.github.davinkevin.betmanager.entity.Bet;
import com.github.davinkevin.betmanager.entity.Match;
import com.github.davinkevin.betmanager.entity.Result;
import com.github.davinkevin.betmanager.entity.Team;

import javax.annotation.concurrent.Immutable;
import java.time.ZonedDateTime;

import static java.util.Objects.nonNull;

/**
 * Created by kevin on 22/08/15 for betmanager
 */
@Immutable
public class MatchWithUserBet {

    final Match match;
    final Bet bet;

    public MatchWithUserBet(Match match, Bet bet) {
        this.match = match;
        this.bet = bet;
    }

    public Long getId() {
        return match.getId();
    }

    public Team getLocalTeam() {
        return match.getLocalTeam();
    }

    public Team getAwayTeam() {
        return match.getAwayTeam();
    }

    public ZonedDateTime getDate() {
        return match.getDate();
    }

    public Integer getLocalScore() {
        return match.getLocalScore();
    }

    public Integer getAwayScore() {
        return match.getAwayScore();
    }

    public Result getResult() {
        return match.getResult();
    }

    public Long getCompetitionId() {
        return nonNull(match.getCompetition()) ? match.getCompetition().getId() : null;
    }

    public Bet getBet() {
        return bet;
    }
}
