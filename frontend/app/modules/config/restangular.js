angular
    .module('bm.config.restangular', ['restangular'])
    .config((RestangularProvider) =>  RestangularProvider.setBaseUrl('/api/'))
    .config(($httpProvider) => $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest');