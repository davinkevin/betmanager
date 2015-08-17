package com.github.davinkevin.betmanager.service;

import com.github.davinkevin.betmanager.entity.Competition;
import com.github.davinkevin.betmanager.repository.CompetitionRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by kevin on 11/08/15 for betmanager
 */
@Service
public class CompetitionService {

    final CompetitionRepository competitionRepository;

    @Inject
    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public Competition findOne(Long id) {
        return competitionRepository.findOne(id);
    }

    public List<Competition> findAll() {
        return competitionRepository.findAll();
    }

    public void delete(Long id) {
        competitionRepository.delete(id);
    }

    public Competition save(Competition competition) {
        return competitionRepository.save(competition);
    }
}
