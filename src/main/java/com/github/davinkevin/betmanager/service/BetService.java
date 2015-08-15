package com.github.davinkevin.betmanager.service;

import com.github.davinkevin.betmanager.entity.Bet;
import com.github.davinkevin.betmanager.entity.User;
import com.github.davinkevin.betmanager.repository.BetRepository;
import com.github.davinkevin.betmanager.repository.MatchRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by kevin on 15/08/15 for betmanager
 */
@Service
public class BetService {

    final BetRepository betRepository;
    final MatchRepository matchRepository;

    @Inject BetService(BetRepository betRepository, MatchRepository matchRepository) {
        this.betRepository = betRepository;
        this.matchRepository = matchRepository;
    }

    public Bet findOne(Long aLong) {
        return betRepository.findOne(aLong);
    }

    public void delete(Long aLong) {
        betRepository.delete(aLong);
    }

    public Bet create(Long matchId, Bet bet, User user) {

        if (betRepository.existsByMatchIdAndUserId(matchId, user.getId())) {
            return changeBet(matchId, bet, user);
        }

        return betRepository
                .save(
                        bet
                            .setMatch(matchRepository.findOne(matchId))
                            .setUser(user)
                );
    }

    private Bet changeBet(Long matchId, Bet bet, User user) {
        Bet betToUpdate = betRepository.findByMatchIdAndUserId(matchId, user.getId());
        return betRepository.save(betToUpdate.setValue(bet.getValue()));
    }

    public Iterable<Bet> findByMatch(Long idMatch) {
        return betRepository.findByMatch(idMatch);
    }
}
