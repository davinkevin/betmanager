
class CompetitionController {

    constructor(competitionService, $location) {
        this.competitionService = competitionService;
        this.$location = $location;

        this.competitionService
            .findAll()
            .then((competitions) => this.competitions = competitions);
    }
}

angular.module('bm.competitions', [
    'bm.common.dataService.competitionService',
    'ngRoute'
]).config(($routeProvider) => {
    $routeProvider.
        when('/', {
            templateUrl: 'competitions/competitions.html',
            controller: 'CompetitionController',
            controllerAs: 'cc'
        });
})
    .controller('CompetitionController', CompetitionController);