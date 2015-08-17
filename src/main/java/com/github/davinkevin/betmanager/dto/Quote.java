package com.github.davinkevin.betmanager.dto;

import com.github.davinkevin.betmanager.entity.Bet;
import com.github.davinkevin.betmanager.entity.Result;

import java.util.stream.Collector;

import static java.util.stream.Collectors.reducing;

/**
 * Created by kevin on 16/08/15 for betmanager
 */
public class Quote {

    final Double one;
    final Double n;
    final Double two;
    final Double total;

    public Quote() {
        one = 0d;
        n = 0d;
        two = 0d;
        total = 0d;
    }

    public Quote(Double one, Double n, Double two) {
        this.one = one;
        this.n = n;
        this.two = two;
        this.total = one + n + two;
    }

    public static Collector<Bet, ?, Quote> collectQuote() {
        return reducing(
                new Quote(),
                bet -> new Quote( Result.ONE.equals(bet.getValue()) ? 1d : 0d, Result.N.equals(bet.getValue()) ? 1d : 0d, Result.TWO.equals(bet.getValue()) ? 1d : 0d ),
                (a, b) -> new Quote(a.one+b.one, a.n+b.n, a.two+b.two)
        );
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
