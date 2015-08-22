class MatchService {

    constructor(Restangular) {
        this.restangular = Restangular;
    }

    findAllFuture(competition) {
        return this.$$matches(competition)
                .all('future')
                .getList();
    }

    findAllPast(competition) {
        return this.$$matches(competition)
            .all('past')
            .getList();
    }

    $$matches(competition) {
        return this.restangular
            .one('competitions', competition.id)
            .all('matches')
    }
}

angular.module('bm.common.dataService.matchService', [
    'restangular'
])
    .service('matchService', MatchService);