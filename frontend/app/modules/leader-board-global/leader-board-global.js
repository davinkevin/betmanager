class LeaderBoardGlobalController {
    constructor(leaderBoardService, leaderBoard) {
        this.leaderBoardService = leaderBoardService;
        this.orderBy = 'score';
        this.reverse = 'reverse';

        this.leaderBoard = leaderBoard;
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

function leaderBoardRouteConfig($routeProvider) {
    return $routeProvider.
        when('/leader-board', {
            templateUrl: 'leader-board-global/leader-board-global.html',
            controller: 'LeaderBoardGlobalController',
            controllerAs: 'lbgc',
            resolve : {
                leaderBoard : (leaderBoardService) => leaderBoardService.leaderBoard()
            }
        });
}


angular.module('bm.leader-board', [
    'bm.common.dataService.leaderBoardService',
    'ngRoute'
])
    .config(leaderBoardRouteConfig)
    .controller('LeaderBoardGlobalController', LeaderBoardGlobalController);