class CompetitionService {

    constructor(Restangular) {
        this.restangular = Restangular;
        this.route = 'competitions';
    }

    findAll() {
        return this.restangular
            .all(this.route)
            .getList();
    }
}

angular.module('bm.common.dataService.competitionService', [
    'restangular'
])
    .service('competitionService', CompetitionService);