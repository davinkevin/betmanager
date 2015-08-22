package com.github.davinkevin.betmanager.service;

import com.github.davinkevin.betmanager.dto.Score;
import com.github.davinkevin.betmanager.entity.Bet;
import com.github.davinkevin.betmanager.entity.Match;
import com.github.davinkevin.betmanager.entity.User;
import com.github.davinkevin.betmanager.exception.BetAfterMatchBeginningNotAllowedException;
import com.github.davinkevin.betmanager.repository.BetRepository;
import com.github.davinkevin.betmanager.repository.MatchRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

/**
 * Created by kevin on 15/08/15 for betmanager
 */
@Service
public class BetService {

    final BetRepository betRepository;
    final MatchRepository matchRepository;
    final MatchService matchService;

    @Inject BetService(BetRepository betRepository, MatchRepository matchRepository, MatchService matchService) {
        this.betRepository = betRepository;
        this.matchRepository = matchRepository;
        this.matchService = matchService;
    }

    public Bet findOne(Long aLong) {
        return betRepository.findOne(aLong);
    }

    public void delete(Long aLong) {
        betRepository.delete(aLong);
    }

    public Bet create(Long matchId, Bet bet, User user) {

        Match match = matchRepository.findOne(matchId);

        if (match.getDate().isBefore(ZonedDateTime.now())) {
            throw new BetAfterMatchBeginningNotAllowedException();
        }

        if (betRepository.existsByMatchIdAndUserId(matchId, user.getId())) {
            return update(matchId, bet, user);
        }

        return betRepository
                .save(
                        bet
                            .setMatch(match)
                            .setUser(user)
                );
    }

    public Bet update(Long matchId, Bet bet, User user) {
        Bet betToUpdate = betRepository.findByMatchIdAndUserId(matchId, user.getId());

        if (nonNull(bet.getMatch()) && bet.getMatch().getDate().isAfter(ZonedDateTime.now())) {
            throw new BetAfterMatchBeginningNotAllowedException();
        }

        return betRepository.save(betToUpdate.setValue(bet.getValue()));
    }

    public Iterable<Bet> findByMatch(Long idMatch) {
        return betRepository.findByMatchId(idMatch);
    }

    public Iterable<Bet> findByUserAndCompetition(User user, Long competitionId) {
        return betRepository.findByUserAndCompetitionId(user, competitionId);
    }

    public Score calculateScore(User user) {
        List<Bet> bets = StreamSupport
                .stream(betRepository.findByUser(user).spliterator(), false)
                .collect(toList());

        List<Match> matches = bets
                .stream()
                .map(Bet::getMatch)
                .collect(toList());

        return new Score(bets, matchService.matchWithQuote(betRepository.findByMatchIn(matches)));
    }
}
