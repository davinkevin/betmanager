

class IdentityService {

    constructor(Restangular, $log, $q) {
        this.$q = $q;
        this.$log = $log;
        this.Restangular = Restangular;
        this.route = {
            users : 'users',
            logout : 'logout'
        };
        this.identity = undefined;
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
                this.$log.error(error);
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
                this.$log.error(error);
                return this.$q.reject(error);
            }
        );
    }

    logout() {
        return this.Restangular
            .one(this.route.logout)
            .post()
            .catch( (error) => this.$log.error(error) )
            .finally( () => this.identity = undefined );
    }

}

angular.module('bm.common.identityService', [
    'restangular'
])
    .service('identityService', IdentityService);