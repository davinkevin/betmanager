package com.github.davinkevin.betmanager.controller.api;

import com.github.davinkevin.betmanager.entity.Team;
import com.github.davinkevin.betmanager.service.TeamService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Created by kevin on 11/08/15 for betmanager
 */
@RestController
@RequestMapping("/api/teams")
public class TeamController {

    final TeamService teamService;

    @Inject TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @RequestMapping(value = "{id}")
    public Team findOne(@PathVariable Long id) {
        return teamService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Team save(@RequestBody Team team) {
        return teamService.save(team);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Team update(@RequestBody Team team) {
        return teamService.save(team);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        teamService.delete(id);
    }
}
