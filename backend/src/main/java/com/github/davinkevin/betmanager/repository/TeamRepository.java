package com.github.davinkevin.betmanager.repository;

import com.github.davinkevin.betmanager.entity.QTeam;
import com.github.davinkevin.betmanager.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by kevin on 11/08/15 for betmanager
 */
public interface TeamRepository extends JpaRepository<Team, Long>, QueryDslPredicateExecutor<Team> {

    default Iterable<Team> findByName(String name) {
        return findAll(QTeam.team.name.contains(name));
    }
}
