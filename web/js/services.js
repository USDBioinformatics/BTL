angular.module('BTL.services', []).
        factory('JSONservice', function ($http) {
            var betsAPI = {};

            betsAPI.getTool = function ($scope) {
                $http.get('input_files/test_bets.json').success(function (data) {
                    $scope.toolData = data;
                });
            };
            
            return betsAPI;
        });

