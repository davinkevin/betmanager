package com.github.davinkevin.betmanager.controller.api;

import com.github.davinkevin.betmanager.dto.LeaderBoardResult;
import com.github.davinkevin.betmanager.service.LeaderBoardService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Set;

/**
 * Created by kevin on 16/08/15 for betmanager
 */
@RestController
@RequestMapping("/api")
public class LeaderBoardController {

    final LeaderBoardService leaderBoardService;

    @Inject LeaderBoardController(LeaderBoardService leaderBoardService) {
        this.leaderBoardService = leaderBoardService;
    }

    @RequestMapping("competitions/{competitionId}/leaderboard")
    public Set<LeaderBoardResult> leaderBoard(@PathVariable Long competitionId) {
        return leaderBoardService.leaderBoard(competitionId);
    }

    @RequestMapping("leaderboard")
    public Set<LeaderBoardResult> leaderBoard() {
        return leaderBoardService.leaderBoard();
    }

}
