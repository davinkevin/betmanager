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
    constructor(leaderBoardService, $scope) {
        this.leaderBoardService = leaderBoardService;
        this.orderBy = 'score';
        this.reverse = 'reverse';

        $scope.$on('competition:'+ this.competition.id + ':leader-board', () => this.loadLeaderBoard());
    }

    loadLeaderBoard() {
        return this.leaderBoardService
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