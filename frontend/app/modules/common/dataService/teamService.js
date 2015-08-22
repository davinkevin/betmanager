
class TeamService {
    constructor(Restangular) {
        this.restangular = Restangular;
    }

    findByName(name) {
        return this
            .restangular
            .all('teams')
            .one('findByName')
            .getList('', { name : name });
    }
}

angular.module('bm.common.dataService.teamService', [
    'restangular'
])
.service('teamService', TeamService);
