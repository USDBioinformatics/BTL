var sharedServices = angular.module("Services", []);

sharedServices.factory('Data', function() {
    return {message: "I'm data from a service"};
});

sharedServices.factory('TreeListService', function($http) {
    return $http.get("./input_files/reporting_file.json");
});

sharedServices.factory('SimilarToolService', function($http) {
    return $http.get("./input_files/similarTools.json")
});