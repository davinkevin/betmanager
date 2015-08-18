package com.github.davinkevin.betmanager.controller.api;

import com.github.davinkevin.betmanager.dto.Score;
import com.github.davinkevin.betmanager.entity.Bet;
import com.github.davinkevin.betmanager.entity.User;
import com.github.davinkevin.betmanager.service.BetService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by kevin on 15/08/15 for betmanager
 */
@RestController
@RequestMapping("/api/users/me")
public class IdentityController {

    final BetService betService;

    @Inject
    IdentityController(BetService betService) {
        this.betService = betService;
    }

    @RequestMapping
    public User user(@AuthenticationPrincipal User user) {
        return user;
    }

    @RequestMapping("competitions/{competitionId}/bets")
    public Iterable<Bet> findUsersBetOnCompetition(@AuthenticationPrincipal User user, @PathVariable Long competitionId) {
        return betService.findByUserAndCompetition(user, competitionId);
    }

    @RequestMapping("score")
    public Score score(@AuthenticationPrincipal User user) {
        return betService.calculateScore(user);
    }
}
