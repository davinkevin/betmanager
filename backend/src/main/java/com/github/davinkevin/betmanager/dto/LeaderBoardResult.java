package com.github.davinkevin.betmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.davinkevin.betmanager.entity.Bet;
import com.github.davinkevin.betmanager.entity.Match;
import com.github.davinkevin.betmanager.entity.User;

import javax.annotation.concurrent.Immutable;
import java.util.List;
import java.util.Map;

/**
 * Created by kevin on 16/08/15 for betmanager
 */
@Immutable
public class LeaderBoardResult {

    final String username;
    final Score score;

    public LeaderBoardResult(User user, List<Bet> bets, Map<Match, Quote> quotes) {
        this.username = user.getUsername();
        this.score = new Score(bets, quotes);
    }


    @JsonProperty("username")
    public String username() {
        return username;
    }

    @JsonProperty("score")
    public Long score() {
        return score.score;
    }

    @JsonProperty("quotedScore")
    public Double quotedscore() {
        return score.quotedScore;
    }
}
