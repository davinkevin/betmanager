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

    findOne(id) {
        return this.restangular
            .all(this.route)
            .one(id)
            .get();
    }
}

angular.module('bm.common.dataService.competitionService', [
    'restangular'
])
    .service('competitionService', CompetitionService);