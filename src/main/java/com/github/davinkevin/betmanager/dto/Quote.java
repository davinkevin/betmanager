package com.github.davinkevin.betmanager.dto;

import com.github.davinkevin.betmanager.entity.Bet;
import com.github.davinkevin.betmanager.entity.Match;
import com.github.davinkevin.betmanager.entity.Result;

import java.util.List;

/**
 * Created by kevin on 16/08/15 for betmanager
 */
public class Quote {

    public static final Quote IDENTITY_QUOTE = new Quote();

    final Match match;
    final Double one;
    final Double n;
    final Double two;
    final Double total;

    public Quote() {
        match = null;
        one = 0d;
        n = 0d;
        two = 0d;
        total = one + n + two;
    }

    public Quote(Match match, List<Bet> bets) {
        this.match = match;
        this.one = betWith(bets, Result.ONE);
        this.n = betWith(bets, Result.N);
        this.two = betWith(bets, Result.TWO);
        this.total = one + n + two;
    }

    private static Double betWith(List<Bet> bets, Result two) {
        return (double) bets
                .stream()
                .filter(bet -> bet.getValue().equals(two))
                .count();
    }

    public Match match() {
        return match;
    }

    public Double getResult(Result value) {
        switch (value) {
            case ONE:
                return total/one;
            case N:
                return total/n;
            case TWO:
                return total/two;
            default:
                return 0d;
        }
    }
}
