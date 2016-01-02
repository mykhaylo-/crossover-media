(function() {
	'use strict';

	angular.module('app').controller('SignInController', SignInController);

	/** @ngInject */
	function SignInController($log, $http, $window, $rootScope, $location,
			AuthFactory) {

		var vm = this;
		vm.user = {};
		vm.signin = function() {
			AuthFactory.signIn(vm.user);
		};
	};
})();