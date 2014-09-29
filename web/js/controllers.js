angular.module('BTL.controllers', []).
        controller('betsInputController', function ($scope, $http) {
            $http.get('http://localhost:8383/BTL-DnD/input_files/test_bets.json').success(function (data) {
                            //$http.get('input_files/test_bets.json').success(function (data) {
                $scope.toolData = data;
            });
            
            
        });