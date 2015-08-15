package com.github.davinkevin.betmanager.repository;

import com.github.davinkevin.betmanager.entity.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import static com.github.davinkevin.betmanager.repository.BetDSL.withMatchId;
import static com.github.davinkevin.betmanager.repository.BetDSL.withUserId;

/**
 * Created by kevin on 11/08/15 for betmanager
 */
public interface BetRepository extends JpaRepository<Bet, Long>, QueryDslPredicateExecutor<Bet> {

    default Iterable<Bet> findByMatch(Long idMatch) {
        return findAll(withMatchId(idMatch));
    }

    default Boolean existsByMatchIdAndUserId(Long idMatch, Long idUser) {
        return exists(withMatchId(idMatch).and(withUserId(idUser)));
    }

    default Bet findByMatchIdAndUserId(Long matchId, Long userId) {
        return findOne(withMatchId(matchId).and(withUserId(userId)));
    }
}
