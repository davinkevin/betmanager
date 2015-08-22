package com.github.davinkevin.betmanager.controller.api;

import com.github.davinkevin.betmanager.entity.Bet;
import com.github.davinkevin.betmanager.entity.User;
import com.github.davinkevin.betmanager.service.BetService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Created by kevin on 15/08/15 for betmanager
 */
@RestController
@RequestMapping("/api/competitions/{idCompetition}/matches/{idMatch}/bets")
public class BetController {

    final BetService betService;

    @Inject BetController(BetService betService) {
        this.betService = betService;
    }

    @RequestMapping
    public Iterable<Bet> findAll(@PathVariable Long idMatch) {
        return betService.findByMatch(idMatch);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Bet create(@PathVariable Long idMatch, @RequestBody Bet bet, @AuthenticationPrincipal User user) {
        return betService.create(idMatch, bet, user);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Bet update(@PathVariable Long idMatch, @RequestBody Bet bet, @AuthenticationPrincipal User user) {
        return betService.update(idMatch, bet, user);
    }

    @RequestMapping(value = "{id}")
    public Bet findOne(@PathVariable Long id) {
        return betService.findOne(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        betService.delete(id);
    }
}
