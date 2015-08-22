
class AddMatchDirective {

    constructor() {
        this.restrict = 'E';
        this.scope = true;
        this.templateUrl = 'competitions/competition/add-match/add-match.html';
        this.controller = 'AddMatchController';
        this.controllerAs = 'amc';
        this.bindToController = {
            competition : '='
        };
    }
}

class AddMatchController {
    constructor(teamService) {
        this.teamService = teamService;
    }

    onSave(match) {

    }

    findByName(value) {
        return this.teamService.findByName(value);
    }
}

angular.module('bm.competitions.competition.add-match', [
    'bm.common.dataService.teamService'
])
    .directive('addMatch', () => new AddMatchDirective())
    .controller('AddMatchController', AddMatchController);