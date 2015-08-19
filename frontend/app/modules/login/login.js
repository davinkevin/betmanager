
class LoginController {

    constructor(identityService, $location) {
        this.$location = $location;
        this.identityService = identityService;
        identityService
            .me()
            .then(
                () => this.goToHome()
        );
    }

    login() {
        return this
            .identityService
            .login(this.credentials.username, this.credentials.password)
            .then(
                (user) => this.goToHome(),
                () => {
                    this.credentials = {};
                    this.loginFailed = true;
                }
        );
    }

    goToHome() {
        this.$location.path('/');
    }
}

angular.module('bm.login', [
    'bm.common.identityService',
    'ngRoute'
]).config(($routeProvider) => {
    $routeProvider.
        when('/login', {
            templateUrl: 'login/login.html',
            controller: 'LoginController',
            controllerAs: 'lc'
        });
})
    .controller('LoginController', LoginController);