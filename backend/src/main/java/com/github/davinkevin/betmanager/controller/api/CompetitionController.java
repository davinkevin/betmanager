package com.github.davinkevin.betmanager.controller.api;

import com.github.davinkevin.betmanager.entity.Competition;
import com.github.davinkevin.betmanager.service.CompetitionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by kevin on 11/08/15 for betmanager
 */
@RestController
@RequestMapping("/api/competitions")
@PreAuthorize("isAuthenticated()")
public class CompetitionController {
    final CompetitionService competitionService;

    @Inject CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @RequestMapping
    public List<Competition> findAll() {
        return competitionService.findAll();
    }

    @RequestMapping(value = "{id}")
    public Competition findOne(@PathVariable Long id) {
        return competitionService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Competition save(@RequestBody Competition competition) {
        return competitionService.save(competition);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Competition update(@RequestBody Competition competition) {
        return competitionService.save(competition);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(Long id) {
        competitionService.delete(id);
    }
}
