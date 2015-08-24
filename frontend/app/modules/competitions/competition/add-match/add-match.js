
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
    constructor(teamService, matchService, toaster) {
        this.teamService = teamService;
        this.matchService = matchService;
        this.toaster = toaster;
        this.resetMatch();
    }

    save() {
        return this.matchService
            .save(this.newMatch)
            .then(() => this.resetMatch())
            .then(() => this.toaster.pop('success', "Match added", "A new match have been added"));
    }

    findByName(value) {
        return this.teamService.findByName(value);
    }

    resetMatch() {
        this.newMatch = {
            date : new Date(),
            competition : this.competition
        };
    }
}

angular.module('bm.competitions.competition.add-match', [
    'bm.common.dataService.teamService'
])
    .directive('addMatch', () => new AddMatchDirective())
    .controller('AddMatchController', AddMatchController);