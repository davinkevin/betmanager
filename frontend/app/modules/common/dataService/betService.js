class BetService {

    constructor(Restangular) {
        this.restangular = Restangular;
    }

    save(match, bet) {
        return this.restangular
            .restangularizeElement(this.parentWith(match), bet, 'bets')
            .put();
    }

    parentWith(match) {
        return this.restangular
            .one('competitions', match.competitionId)
            .one('matches', match.id);
    }
}

angular.module('bm.common.dataService.betService', [
    'restangular'
])
    .service('betService', BetService);