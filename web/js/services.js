var sharedServices = angular.module("Services", []);


sharedServices.factory('TreeListService', function($http) {
    return $http.get("./input_files/reporting_file.json");
});

sharedServices.factory('SimilarToolService', function($http) {
    return $http.get("./input_files/similarTools.json")
});

sharedServices.factory('DataTreeService', function($http) {
    return  $http.get("./input_files/dataTree.json")
});

sharedServices.service('sharedData', function() {
    var toolId = '001';
    var objectValue = {
        data: 'test object value'
    };

    return {
        getToolId: function(){
            return toolId;
        },
        setToolId: function(id){
            toolId = id;
        },
        getString: function() {
            return stringValue;
        },
        setString: function(value) {
            stringValue = value;
        },
        getObject: function() {
            return objectValue;
        }
    }
});
