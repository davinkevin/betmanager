package com.github.davinkevin.betmanager.service;

import com.github.davinkevin.betmanager.entity.Competition;
import com.github.davinkevin.betmanager.entity.Match;
import com.github.davinkevin.betmanager.repository.CompetitionRepository;
import com.github.davinkevin.betmanager.repository.MatchRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.ZonedDateTime;

/**
 * Created by kevin on 11/08/15 for betmanager
 */
@Service
public class MatchService {

    final MatchRepository matchRepository;
    final CompetitionRepository competitionRepository;

    @Inject
    public MatchService(MatchRepository matchRepository, CompetitionRepository competitionRepository) {
        this.matchRepository = matchRepository;
        this.competitionRepository = competitionRepository;
    }

    public Iterable<Match> findByCompetition(Long idCompetition) {
        return matchRepository.findByCompetition(idCompetition);
    }

    public Match save(Match match) {
        return matchRepository.save(match);
    }

    public Match save(Long idCompetition, Match match) {
        Competition competition = competitionRepository.findOne(idCompetition);
        return matchRepository.save(match.setCompetition(competition));
    }

    public Match findOne(Long id) {
        return matchRepository.findOne(id);
    }

    public void delete(Long id) {
        matchRepository.delete(id);
    }

    public Iterable<Match> findByCompetitionAndDateBefore(Long idCompetition, ZonedDateTime date) {
        return matchRepository.findByCompetitionAndDateBefore(idCompetition, date);
    }

    public Iterable<Match> findByCompetitionAndDateAfter(Long idCompetition, ZonedDateTime date) {
        return matchRepository.findByCompetitionAndDateAfter(idCompetition, date);
    }
}
