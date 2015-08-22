
class CompetitionsController {
    constructor(competitions) {
        this.competitions = competitions;
    }
}

function competitionsRouteConfig($routeProvider) {
    return $routeProvider.
        when('/', {
            templateUrl: 'competitions/competitions.html',
            controller: 'CompetitionsController',
            controllerAs: 'cc',
            resolve : {
                competitions : (competitionService) => competitionService.findAll()
            }
        });
}

angular.module('bm.competitions', [
    'bm.common.dataService.competitionService',
    'bm.competitions.competition',
    'ngRoute'
])
    .config(competitionsRouteConfig)
    .controller('CompetitionsController', CompetitionsController);