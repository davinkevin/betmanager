
class CompetitionController {

    constructor(competition, identityService, $scope) {
        this.competition = competition;
        this.identityService = identityService;
        this.$scope = $scope;
    }

    load(scope) {
        this.$scope.$broadcast('competition:' + this.competition.id + ':' + scope);
    }
}

function competitionRouteConfig($routeProvider) {
    return $routeProvider.
        when('/competitions/:competitionId', {
            templateUrl: 'competitions/competition/competition.html',
            controller: 'CompetitionController',
            controllerAs: 'cc',
            resolve : {
                competition : (competitionService, $route) => competitionService.findOne($route.current.params.competitionId)
            }
        });
}


angular.module('bm.competitions.competition', [
    'bm.competitions.competition.future-bets',
    'bm.competitions.competition.past-bets',
    'bm.competitions.competition.leader-board',
    'bm.competitions.competition.score-admin',
    'bm.competitions.competition.add-match',
    'bm.common.dataService.identityService',
    'ngRoute'
])
    .config(competitionRouteConfig)
    .controller('CompetitionController', CompetitionController);