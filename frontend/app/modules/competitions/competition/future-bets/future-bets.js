
class FutureBetsDirective {

    constructor() {
        this.restrict = 'E';
        this.scope = true;
        this.templateUrl = 'competitions/competition/future-bets/future-bets.html';
        this.controller = 'FutureBetsController';
        this.controllerAs = 'fbc';
        this.bindToController = {
            competition : '='
        };
    }
}

class FutureBetsController {

    constructor(matchService, $scope) {
        this.matchService = matchService;

        $scope.$on('competition:'+ this.competition.id + ':future', () => this.loadMatches());
    }

    loadMatches() {
        this.matchService
            .findAllFuture(this.competition)
            .then((matches) => this.matches = matches);
    }
}

angular.module('bm.competitions.competition.future-bets', [
    'bm.common.dataService.matchService'
])
    .directive('futureBet', () => new FutureBetsDirective())
    .controller('FutureBetsController', FutureBetsController);