package com.github.davinkevin.betmanager.repository;

import com.github.davinkevin.betmanager.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kevin on 11/08/15 for betmanager
 */
public interface TeamRepository extends JpaRepository<Team, Long> {
}
