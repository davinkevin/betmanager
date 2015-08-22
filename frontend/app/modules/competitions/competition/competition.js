
class CompetitionController {

    constructor(competition, identityService) {
        this.competition = competition;
        this.identityService = identityService;
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
    'bm.common.dataService.identityService',
    'ngRoute'
])
    .config(competitionRouteConfig)
    .controller('CompetitionController', CompetitionController);