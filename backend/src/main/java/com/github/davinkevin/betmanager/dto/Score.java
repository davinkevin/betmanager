package com.github.davinkevin.betmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.davinkevin.betmanager.entity.Bet;
import com.github.davinkevin.betmanager.entity.Match;

import javax.annotation.concurrent.Immutable;
import java.util.List;
import java.util.Map;

/**
 * Created by kevin on 18/08/15 for betmanager
 */
@Immutable
public class Score {

    final @JsonProperty Long score;
    final @JsonProperty Double quotedScore;

    public Score(List<Bet> bets, Map<Match, Quote> quotes) {
        this.score = bets.stream().filter(Bet::isValid).count();
        this.quotedScore = bets
                .stream()
                .filter(Bet::isValid)
                .mapToDouble(bet -> quotes.get(bet.getMatch()).getResult(bet.getValue()))
                .sum();
    }
}
