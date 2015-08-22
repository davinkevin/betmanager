
class PastBetsDirective {

    constructor() {
        this.restrict = 'E';
        this.scope = true;
        this.templateUrl = 'competitions/competition/past-bets/past-bets.html';
        this.controller = 'PastBetsController';
        this.controllerAs = 'pbc';
        this.bindToController = {
            competition : '=',
            bets : '='
        };
    }
}

class PastBetsController {

    constructor(matchService) {
        this.matchService = matchService;

        this.matchService
            .findAllPast(this.competition)
            .then((matches) => this.matches = matches);
    }
}

angular.module('bm.competitions.competition.past-bets', [
    'bm.common.dataService.matchService'
])
    .directive('pastBet', () => new PastBetsDirective())
    .controller('PastBetsController', PastBetsController);