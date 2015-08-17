package com.github.davinkevin.betmanager.repository;

import com.github.davinkevin.betmanager.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.time.ZonedDateTime;

import static com.github.davinkevin.betmanager.repository.dsl.MatchDSL.withCompetitionId;
import static com.github.davinkevin.betmanager.repository.dsl.MatchDSL.withDateAfter;
import static com.github.davinkevin.betmanager.repository.dsl.MatchDSL.withDateBefore;

/**
 * Created by kevin on 11/08/15 for betmanager
 */
public interface MatchRepository extends JpaRepository<Match, Long>, QueryDslPredicateExecutor<Match> {

    default Iterable<Match> findByCompetition(Long competitionId) {
        return findAll(withCompetitionId(competitionId));
    }

    default Iterable<Match> findByCompetitionAndDateBefore(Long competitionId, ZonedDateTime date) {
        return findAll(withCompetitionId(competitionId).and(withDateBefore(date)));
    }

    default Iterable<Match> findByCompetitionAndDateAfter(Long competitionId, ZonedDateTime date) {
        return findAll(withCompetitionId(competitionId).and(withDateAfter(date)));
    }
}
