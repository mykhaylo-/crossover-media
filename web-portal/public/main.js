(function () {
    'use strict';

    angular.module('app', ['ngRoute', 'ui.bootstrap', 'toaster', 'parse-angular'])
        .config(routesConfig)
        .constant("baseUrl","http://localhost:8080")
        .controller('RootController', RootController)
        .factory('AuthFactory', AuthFactory)
        .run(Run);

    /** @ngInject */
    function routesConfig($routeProvider, $locationProvider) {
        $routeProvider.when('/publishersignup', {
            templateUrl: 'publisher_signup.html',
            controller: 'PublisherSignUpController',
            controllerAs: 'signupController'
        }).when('/signin', {
            templateUrl: 'signin.html',
            controller: 'SignInController',
            controllerAs: 'signinController'
        }).when('/home', {
            templateUrl: 'home.html',
            controller: 'HomeController',
            controllerAs: 'homeController'
        }).when('/admin', {
            templateUrl: 'admin.html',
            controller: 'AdminController',
            controllerAs: 'adminController'
        }).when('/users', {
            templateUrl: 'users.html',
            controller: 'UsersController',
            controllerAs: 'usersController'
        }).when('/', {
                templateUrl: 'welcome.html',
            }
        ).otherwise('/');

        $locationProvider.html5Mode(true);

    };

    function AuthFactory($log, $http, $location, baseUrl) {
        var user;

        var isSubscriber = false;
        var isPublisher = false;

        return{
        	signUp : function(aUser, success, error) {
        		$http.post(baseUrl+'/signup', aUser).then(success, error);
        	},
        	signIn : function(aUser) {
        		
        		var headers = aUser ? {authorization : "Basic "
        	        + btoa(aUser.username + ":" + aUser.password)
        	    } : {};
        	    
        		$http.get(baseUrl + '/user', {headers : headers}).then(
        				function(response) {
        					user = response.data.principal;
        					$log.info("login successful")
        					$location.path("/home");
        				}, function(response) {
        					$log.info("LOGIN FAILED")
        					alert("Error: " + response.status);
        				}
        		);
        	},

        	setUser : function(aUser){
                user = aUser;
                if(aUser) {
                } else {
                    isPublisher = false;
                    isSubscriber = false;
                }
            },
            isLoggedIn : function() {
                return (user) ? true : false;
            },
            isSubscriber : function() {
                return isSubscriber;
            },
            isPublisher : function() {
                return isPublisher;
            },
            currentUser : function() {
            	return user;
            }
        }
    };

    function Run($rootScope, $route, $location, AuthFactory) {

        $rootScope.$on('$routeChangeStart', function (event, next, current) {

            if (!AuthFactory.isLoggedIn()) {
                if(next.originalPath !== "/") {
	                	if (next.originalPath !== "/signin" &&
                			next.originalPath !== "/publishersignup" ) {
	                    console.log('DENY');
	                    // event.preventDefault();
	                    $location.path('/');
	                }
                }
            } else {
                if(next.originalPath === "/") {
                    $location.path("/home");
                }
            }
        });
    };

    function RootController(AuthFactory, $location, $scope, $rootScope, $log) {
        var vm = this;
        vm.currentUser = AuthFactory.currentUser();
        vm.isPublisher = AuthFactory.isPublisher;
        vm.isSubscriber = AuthFactory.isSubscriber;
        vm.loggedIn = AuthFactory.isLoggedIn;

        // $scope.$watch(AuthFactory.isSubscriber(), function (value, oldValue)
		// {
        //
        // if(!value && oldValue) {
        // vm.isPublisher = false;
        // vm.isSubscriber = false;
        // }
        //
        // if(value) {
        // vm.isPublisher = AuthFactory.isPublisher();
        // vm.isSubscriber = AuthFactory.isSubscriber();
        // }
        // }, false);

        $scope.$watch(AuthFactory.isLoggedIn, function (value, oldValue) {

            if(!value && oldValue) {
                vm.currentUser = AuthFactory.currentUser();
                // vm.loggedIn = false;
            }

            if(value) {
            	vm.currentUser = AuthFactory.currentUser()
                // vm.loggedIn = AuthFactory.isLoggedIn();
            }
        }, false);


        if(vm.currentUser) {
            // (new Parse.Query(Parse.Role)).equalTo("users",
			// Parse.User.current())
            // .find({
            // success: function (roles) {
            // $log.info("Retrieved " + roles.length + " roles for user");
            //
            // var currentUserRoles = roles.map(function (item) {
            // return item.toJSON().name;
            // });
            // if (currentUserRoles.indexOf("Administrators") >= 0) {
            // vm.isPublisher = true;
            // vm.isSubscriber = true;
            // } else if (currentUserRoles.indexOf("Managers") >= 0) {
            // vm.isSubscriber = true;
            // }
            // },
            // error: function(error) {
            // alert("Error: " + error.code + " " + error.message);
            // }}
            // );
        }
        //

        vm.logout = function() {
            // vm.currentUser = null;
            Parse.User.logOut();
            AuthFactory.setUser(Parse.User.current())
            $location.path("/signin");
            // vm.isPublisher = false;
            // vm.isSubscriber = false;
        };
    };

})();
