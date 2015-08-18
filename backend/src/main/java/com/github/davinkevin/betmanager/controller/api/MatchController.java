package com.github.davinkevin.betmanager.controller.api;

import com.github.davinkevin.betmanager.dto.Quote;
import com.github.davinkevin.betmanager.entity.Match;
import com.github.davinkevin.betmanager.service.MatchService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.time.ZonedDateTime;

/**
 * Created by kevin on 11/08/15 for betmanager
 */
@RestController
@RequestMapping("/api/competitions/{idCompetition}/matchs")
public class MatchController {

    final MatchService matchService;

    @Inject MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @RequestMapping
    public Iterable<Match> findAll(@PathVariable Long idCompetition){
        return matchService.findByCompetition(idCompetition);
    }

    @RequestMapping(value = "past")
    public Iterable<Match> findAllInPast(@PathVariable Long idCompetition){
        return matchService.findByCompetitionAndDateBefore(idCompetition, ZonedDateTime.now());
    }

    @RequestMapping(value = "future")
    public Iterable<Match> findAllInFuture(@PathVariable Long idCompetition){
        return matchService.findByCompetitionAndDateAfter(idCompetition, ZonedDateTime.now());
    }

    @RequestMapping(value = "{id}")
    public Match findOne(@PathVariable Long id) {
        return matchService.findOne(id);
    }

    @RequestMapping(value = "{id}/quote")
    public Quote quote(@PathVariable Long id) {
        return matchService.calculateQuote(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Match save(@PathVariable Long idCompetition, @RequestBody Match match) {
        return matchService.save(idCompetition, match);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Match update(@RequestBody Match match) {
        return matchService.save(match);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        matchService.delete(id);
    }

}
