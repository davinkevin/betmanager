class BetService {

    constructor(Restangular) {
        this.restangular = Restangular;
    }

    save(match, bet) {

        if (!bet.id) {
            return this.parentWith(match).post('bets', bet);
        }

        if (!bet.value) {
            return this.parentWith(match).one('bets', bet.id).remove();
        }

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