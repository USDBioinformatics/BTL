(function () {
    var app = angular.module('tools', []);

    app.directive('toolList', function () {
        return{
            restrict: 'E', //type of directive E=element
            templateUrl: 'tool-list.html' //Url of a template
        };
    });
})();