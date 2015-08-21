
class CompetitionController {

    constructor($location) {
        this.$location = $location;
    }
}

angular.module('bm.competitions', [
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