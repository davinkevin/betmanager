
class CompetitionController {

    constructor(identityService, $location) {
        this.$location = $location;
        this.identityService = identityService;
        identityService
            .me()
            .then((user) => this.identity = user);
    }

    logout() {
        return this
        .identityService
        .logout()
        .finally(
            () => this.$location.path('/login')
        );
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