package com.github.davinkevin.betmanager.service;

import com.github.davinkevin.betmanager.dto.MatchWithUserBet;
import com.github.davinkevin.betmanager.dto.Quote;
import com.github.davinkevin.betmanager.entity.*;
import com.github.davinkevin.betmanager.repository.BetRepository;
import com.github.davinkevin.betmanager.repository.CompetitionRepository;
import com.github.davinkevin.betmanager.repository.MatchRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

/**
 * Created by kevin on 11/08/15 for betmanager
 */
@Service
public class MatchService {

    final MatchRepository matchRepository;
    final CompetitionRepository competitionRepository;
    final BetRepository betRepository;

    @Inject
    public MatchService(MatchRepository matchRepository, CompetitionRepository competitionRepository, BetRepository betRepository) {
        this.matchRepository = matchRepository;
        this.competitionRepository = competitionRepository;
        this.betRepository = betRepository;
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

    public Iterable<MatchWithUserBet> findAllInPastWithUserBet(Long idCompetition, ZonedDateTime date, User user) {
        Iterable<Match> pastMatches = matchRepository.findByCompetitionAndDateBefore(idCompetition, date);
        return attachBetFromUser(pastMatches, user);
    }

    private Set<MatchWithUserBet> attachBetFromUser(Iterable<Match> pastMatches, User user) {
        return StreamSupport
                .stream(pastMatches.spliterator(), false)
                .map(match -> new MatchWithUserBet(match, betRepository.findByMatchIdAndUserId(match.getId(), user.getId())))
                .collect(toSet());
    }

    public Iterable<MatchWithUserBet> findAllInFutureWithUserBet(Long idCompetition, ZonedDateTime date, User user) {
        Iterable<Match> futureMatches = matchRepository.findByCompetitionAndDateAfter(idCompetition, date);
        return attachBetFromUser(futureMatches, user);
    }

    public Quote calculateQuote(Long matchId) {
        return StreamSupport.stream(betRepository.findByMatchId(matchId).spliterator(), false)
                .filter(Bet::hasValue)
                .map(Quote::of)
                .reduce(new Quote(), Quote::merge);
    }

    public Map<Match, Quote> matchWithQuote(Iterable<Bet> bets) {
        return StreamSupport
                .stream(bets.spliterator(), false)
                .filter(Bet::hasValue)
                .collect(
                        groupingBy(
                                Bet::getMatch,
                                Quote.collectQuote()
                        )
                );
    }
}
