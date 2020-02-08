angular.module('myApp').factory('BotServices',["$http",function($http){
	
	var service = {};
	service.charge= function(){
		return $http.get("/robot/charge")
	}
	service.scan=function(scanModel){
		return $http.post("/robot/scan",scanModel)
	}
	service.action=function(actionModel){
		return $http.post("/robot/action",actionModel)
	}
	
	return service;
	
}])

