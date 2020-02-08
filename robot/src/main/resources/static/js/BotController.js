angular.module('myApp',[]).controller('BotController',
	function($scope,BotServices){
	$scope.charge="100";
	$scope.message="Fully Charged";
	
	$scope.init = function () {
		$scope.chargeBot();
	};
	$scope.chargeBot=function(){
		BotServices.charge().then(function(response){
			if(response.data && (response.data.battery || response.data.battery==0)){
				$scope.charge=response.data.battery;
				$scope.message = response.data.message.toUpperCase();
				var element = document.getElementById("battery");
				element.style.width = $scope.charge+"%";
				 element.classList.add("bg-success");
				  element.classList.remove("bg-danger");
			}
		},
		function(reason){
			console.log("Error");
		});
	}
	
	
	$scope.scanBot=function(){
		$scope.scanModel={
				"price":$scope.price
				
		}
		BotServices.scan($scope.scanModel).then(function(response){
			if(response.data && (response.data.battery || response.data.battery==0)){
				$scope.charge=response.data.battery;
				var msg="";
				if(isNaN(response.data.message))
					msg = response.data.message;
				else
					msg="$"+response.data.message;
				$scope.message = msg.toUpperCase();
				
			}
		},
		function(reason){
			console.log("Error");
		});
	}
	
	$scope.actionBot=function(){
		$scope.actionModel={
				"distance":$scope.distance,
				"load":$scope.load
		}
		BotServices.action($scope.actionModel).then(function(response){
			if(response.data && (response.data.battery || response.data.battery==0)){
				$scope.charge=response.data.battery;
				$scope.message = response.data.message.toUpperCase();
				var element = document.getElementById("battery");
				element.style.width = $scope.charge+"%";
			
				if($scope.charge<15){
					  element.classList.remove("bg-success");
					  element.classList.add("bg-danger");
				}
				else if(!($scope.message.toUpperCase()=="OVERWEIGHT")){
					$scope.message = "REMAINING BATTERY - "+response.data.battery+"%";
					  element.classList.remove("bg-danger");
					 element.classList.add("bg-success");
				}
			}
		},
		function(reason){
			console.log("Error");
		});
	}
});