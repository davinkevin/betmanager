class LeaderBoardDirective {
    constructor() {
        this.restrict = 'E';
        this.scope = true;
        this.templateUrl = 'competitions/competition/leader-board/leader-board.html';
        this.controller = 'LeaderBoardController';
        this.controllerAs = 'lc';
        this.bindToController = {
            competition : '='
        };
    }
}

class LeaderBoardController {
    constructor(leaderBoardService) {
        this.leaderBoardService = leaderBoardService;
        this.orderBy = 'score';
        this.reverse = 'reverse';

        this.leaderBoardService
            .leaderBoardOf(this.competition)
            .then((leaderBoard) => this.leaderBoard = leaderBoard);
    }

    orderWith(value) {
        if (this.orderBy === value) {
            this.reverse = !this.reverse;
        } else {
            this.orderBy = value;
            this.reverse = true;
        }
    }
}

angular.module('bm.competitions.competition.leader-board', [
    'bm.common.dataService.leaderBoardService'
])
    .directive('leaderBoard', () => new LeaderBoardDirective())
    .controller('LeaderBoardController', LeaderBoardController);