

class ScoreAdminDirective {

    constructor() {
        this.restrict = 'E';
        this.scope = true;
        this.templateUrl = 'competitions/competition/score-admin/score-admin.html';
        this.controller = 'ScoreAdminController';
        this.controllerAs = 'sac';
        this.bindToController = {
            competition : '='
        };
    }
}

class ScoreAdminController {
    constructor(matchService, $scope) {
        this.matchService = matchService;

        $scope.$on('competition:'+ this.competition.id + ':score-admin', () => this.loadMatches());
    }

    loadMatches() {
        return this.matchService
            .findAll(this.competition)
            .then((matches) => this.matches = matches);
    }

    onChange(match) {
        if (!angular.isNumber(match.awayScore, match.localScore)) {
            return;
        }

        return this.matchService
            .save(match);
    }
}

angular.module('bm.competitions.competition.score-admin', [
    'bm.common.dataService.matchService'
])
    .directive('scoreAdmin', () => new ScoreAdminDirective())
    .controller('ScoreAdminController', ScoreAdminController);
