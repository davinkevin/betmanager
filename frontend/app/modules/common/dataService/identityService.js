

class IdentityService {

    constructor(Restangular, $q) {
        this.$q = $q;
        this.Restangular = Restangular;
        this.route = {
            users : 'users',
            logout : 'logout'
        };
        this.identity = undefined;
        this.me();
    }

    me() {
        if (this.identity) {
            return this.$q.resolve(this.identity);
        }

        return this.Restangular
            .one(this.route.users)
            .one('me')
            .get()
            .then(
            (user) => {
                this.identity = user;
                return this.identity;
            },
            (error) => {
                return this.$q.reject(error);
            }
        );
    }
    login(login, password) {
        return this.Restangular
            .one(this.route.users)
            .one('me')
            .get({}, {'Authorization' : 'Basic ' + btoa(login + ':' + password)} )
            .then(
            (user) => {
                this.identity = user;
                return this.identity;
            },
            (error) => {
                return this.$q.reject(error);
            }
        );
    }
    logout() {
        return this.Restangular
            .one(this.route.logout)
            .post()
            .finally( () => this.identity = undefined );
    }

    hasRole(role) {
        if (!this.identity) {
            return false;
        }

        return _.findIndex(this.identity.roles, 'name', role) > -1;
    }
}

angular.module('bm.common.dataService.identityService', [
    'restangular'
])
    .service('identityService', IdentityService);