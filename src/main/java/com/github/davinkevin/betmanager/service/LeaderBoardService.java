package com.github.davinkevin.betmanager.service;

import com.github.davinkevin.betmanager.dto.LeaderBoardResult;
import com.github.davinkevin.betmanager.entity.Bet;
import com.github.davinkevin.betmanager.repository.BetRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Objects.nonNull;
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

    public Set<LeaderBoardResult> leaderBoard(Long competitionId) {
        return StreamSupport
                .stream(betRepository.findByCompetitionId(competitionId).spliterator(), false)
                .filter(bet -> nonNull(bet.getMatch().getResult()))
                .collect(Collectors.groupingBy(Bet::getUser))
                .entrySet()
                .stream()
                .map(tuple -> new LeaderBoardResult(tuple.getKey(), tuple.getValue()))
                .collect(toSet());
    }
}
