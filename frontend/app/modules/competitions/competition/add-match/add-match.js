
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
    constructor(teamService, matchService) {
        this.teamService = teamService;
        this.matchService = matchService;
        this.newMatch = {
            date : new Date(),
            competition : this.competition
        };
    }

    open() {
        this.opened = true;
    }

    /*
    onChangeDate() {
        var gmtDate = new Date(this.newMatch.stringDate);
        this.newMatch.date = new Date(gmtDate.valueOf() + gmtDate.getTimezoneOffset() * 60000);
    }*/

    save() {
        return this.matchService
            .save(this.newMatch);
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