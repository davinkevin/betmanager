package com.github.davinkevin.betmanager.service;

import com.github.davinkevin.betmanager.dto.LeaderBoardResult;
import com.github.davinkevin.betmanager.dto.Quote;
import com.github.davinkevin.betmanager.entity.Bet;
import com.github.davinkevin.betmanager.repository.BetRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Set;
import java.util.stream.StreamSupport;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

/**
 * Created by kevin on 16/08/15 for betmanager
 */
@Service
public class LeaderBoardService {

    final BetRepository betRepository;

    @Inject LeaderBoardService(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    public Set<Object> leaderBoard(Long competitionId) {
        Iterable<Bet> bets = betRepository.findByCompetitionId(competitionId);

        Set<Quote> quotes = StreamSupport
                .stream(bets.spliterator(), false)
                .filter(bet -> nonNull(bet.getMatch().getResult()))
                .collect(groupingBy(Bet::getMatch))
                .entrySet()
                .stream()
                .map(tuple -> new Quote(tuple.getKey(), tuple.getValue()))
                .collect(toSet());

        return StreamSupport
                .stream(bets.spliterator(), false)
                .filter(bet -> nonNull(bet.getMatch().getResult()))
                .collect(groupingBy(Bet::getUser))
                .entrySet()
                .stream()
                .map(tuple -> new LeaderBoardResult(tuple.getKey(), tuple.getValue(), quotes))
                .collect(toSet());
    }
}
