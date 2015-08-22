
class CompetitionController {

    constructor(competition) {
        this.competition = competition;
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
    'ngRoute'
])
    .config(competitionRouteConfig)
    .controller('CompetitionController', CompetitionController);