package com.github.davinkevin.betmanager.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.davinkevin.betmanager.entity.Bet;
import com.github.davinkevin.betmanager.entity.Result;

import javax.annotation.concurrent.Immutable;
import java.util.stream.Collector;

import static java.util.stream.Collectors.reducing;

/**
 * Created by kevin on 16/08/15 for betmanager
 */
@Immutable
public class Quote {

    final @JsonProperty Double one;
    final @JsonProperty Double n;
    final @JsonProperty Double two;
    final @JsonProperty Double total;

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

    @JsonIgnore
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


    public static Collector<Bet, ?, Quote> collectQuote() {
        return reducing(
                new Quote(),
                Quote::of,
                Quote::merge
        );
    }
    public static Quote merge(Quote a, Quote b) {
        return new Quote(a.one+b.one, a.n+b.n, a.two+b.two);
    }
    public static Quote of(Bet bet) {
        return new Quote( Result.ONE.equals(bet.getValue()) ? 1d : 0d, Result.N.equals(bet.getValue()) ? 1d : 0d, Result.TWO.equals(bet.getValue()) ? 1d : 0d );
    }



}
