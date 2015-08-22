
class BetSelectorDirective {

    constructor() {
        this.restrict = 'E';
        this.scope = true;
        this.templateUrl = 'common/component/bet-selector/bet-selector.html';
        this.controller = 'BetSelectorController';
        this.controllerAs = 'bsc';
        this.bindToController = {
            match : '=',
            bet : '='
        };
    }
}

class BetSelectorController {

    constructor(betService) {
        this.betService = betService;
    }

    buttonStyle(val) {
        return {
            'btn-default' : this.bet != null && this.bet.value === val,
            'btn-primary' : this.bet == null || this.bet.value !== val
        };
    }

    onChange() {
        return this.betService
                .save(this.match, this.bet)
                .then(bet => this.bet = bet);
    }
}

angular.module('bm.common.component.bet-selector', [
    'bm.common.dataService.betService'
])
    .directive('betSelector', () => new BetSelectorDirective())
    .controller('BetSelectorController', BetSelectorController);
