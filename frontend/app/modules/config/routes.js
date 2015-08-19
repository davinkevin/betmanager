angular.module('bm.config.routes', [
    'ngRoute'
])
    .config($routeProvider => $routeProvider.otherwise({redirectTo: '/login'}))
    .config($locationProvider => $locationProvider.html5Mode(true));