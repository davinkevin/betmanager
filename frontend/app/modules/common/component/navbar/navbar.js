
class NavBarController {

    constructor(identityService, $scope, $location) {
        this.$location = $location;
        this.identityService = identityService;
        this.identity = undefined;

        $scope.$watch(
            'navbar.identityService.identity',
            (newVal) => this.identity = newVal
        );
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

class NavBarDirective {
    constructor() {
        this.replace = true;
        this.restrict = 'E';
        this.scope = true;
        this.templateUrl = 'common/component/navbar/navbar.html';
        this.controller = 'NavBarController';
        this.controllerAs = 'navbar';
    }

    link(_, element) {
        element.removeClass('hidden');
    }
}

angular.module('bm.common.component.navbar', [])
    .directive('navbar', () => new NavBarDirective())
    .controller('NavBarController', NavBarController);