package com.github.davinkevin.betmanager.repository;

import com.github.davinkevin.betmanager.entity.Bet;
import com.github.davinkevin.betmanager.entity.Match;
import com.github.davinkevin.betmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

import static com.github.davinkevin.betmanager.repository.dsl.BetDSL.*;

/**
 * Created by kevin on 11/08/15 for betmanager
 */
public interface BetRepository extends JpaRepository<Bet, Long>, QueryDslPredicateExecutor<Bet> {

    default Iterable<Bet> findByMatchId(Long matchId) {
        return findAll(withMatchId(matchId));
    }
    default Boolean existsByMatchIdAndUserId(Long idMatch, Long idUser) {
        return exists(withMatchId(idMatch).and(withUserId(idUser)));
    }
    default Bet findByMatchIdAndUserId(Long matchId, Long userId) {
        return findOne(withMatchId(matchId).and(withUserId(userId)));
    }
    default Iterable<Bet> findByUserAndCompetitionId(User user, Long competitionId) {
        return findAll(withUserId(user.getId()).and(withCompetitionId(competitionId)));
    };
    default Iterable<Bet> findByCompetitionId(Long competitionId) {
        return findAll(withCompetitionId(competitionId));
    }
    default Iterable<Bet> findByUser(User user) {
        return findAll(withUserId(user.getId()));
    }
    default Iterable<Bet> findByMatchIn(List<Match> matchs) {
        return findAll(withMatchIn(matchs));
    }
}
