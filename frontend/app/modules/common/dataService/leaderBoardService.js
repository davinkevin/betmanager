
class LeaderBoardService {

    constructor(Restangular) {
        this.restangular = Restangular;
    }

    leaderBoardOf(competition) {
        return this.restangular
            .one('competitions', competition.id)
            .one('leaderboard')
            .get();
    }
}


angular.module('bm.common.dataService.leaderBoardService', [
    'restangular'
])
    .service('leaderBoardService', LeaderBoardService);