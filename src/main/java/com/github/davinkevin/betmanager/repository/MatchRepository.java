package com.github.davinkevin.betmanager.repository;

import com.github.davinkevin.betmanager.entity.Match;
import com.github.davinkevin.betmanager.entity.QMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by kevin on 11/08/15 for betmanager
 */
public interface MatchRepository extends JpaRepository<Match, Long>, QueryDslPredicateExecutor<Match> {

    default Iterable<Match> findByCompetition(Long idCompetition) {
        return findAll(QMatch.match.competition.id.eq(idCompetition));
    }
}
