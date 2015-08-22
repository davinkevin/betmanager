package com.github.davinkevin.betmanager.service;

import com.github.davinkevin.betmanager.entity.Team;
import com.github.davinkevin.betmanager.repository.TeamRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by kevin on 11/08/15 for betmanager
 */
@Service
public class TeamService {

    final TeamRepository teamRepository;

    @Inject
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public Team findOne(Long id) {
        return teamRepository.findOne(id);
    }

    public void delete(Long id) {
        teamRepository.delete(id);
    }


    public Iterable<Team> findByName(String name) {
        return teamRepository.findByName(name);
    }
}
