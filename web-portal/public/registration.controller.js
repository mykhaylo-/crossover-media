(function() {
	'use strict';

	angular.module('app').controller('PublisherSignUpController',
			PublisherSignUpController);

	/** @ngInject */
	function PublisherSignUpController($log, $location, $window, AuthFactory) {

		var vm = this;
		vm.user = {role:"PUBLISHER"};
		vm.register = function() {

			AuthFactory.signUp(vm.user, 
					function(response) {
				$log.info("registration successful")
				$window.location.href = "/signin";
				// $location.path =
													// "/home";
			},
			function(response) {
				alert("Error: " + response.status);
			}
			);

//			user.signUp(null, {
//				success : function(user) {
//					$log.info("registration successful")
//					$window.location.href = "/login";// $location.path =
//														// "/home";
//				},
//				error : function(user, error) {
//					alert("Error: " + error.code + " " + error.message);
//				}
//			});
		}
	}
})();