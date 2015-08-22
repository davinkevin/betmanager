class MatchService {

    constructor(Restangular) {
        this.restangular = Restangular;
    }

    findAllFuture(competition) {
        return this.restangular
                .one('competitions', competition.id)
                .all('matches')
                .all('future')
                .getList();
    }
}

angular.module('bm.common.dataService.matchService', [
    'restangular'
])
    .service('matchService', MatchService);