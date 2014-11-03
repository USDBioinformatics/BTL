var sharedServices = angular.module("Services", []);

sharedServices.factory('TreeListService', function($http) {
    return $http.get("./input_files/reporting_file.json");
});

sharedServices.factory('SimilarToolService', function($http) {
    return $http.get("./input_files/similarTools.json");
});



sharedServices.factory('NewSimTools', function($http){
    return $http.get(".input_files/newSimTools.json");
});

sharedServices.factory('DataTreeService', function($http) {
    return  $http.get("./input_files/dataTree.json")
});


sharedServices.service('sharedData', function() {
    var sharedModel = {
        value: "Initial Value"
    };

    sharedModel.updateValue = function (value) {
        sharedModel.value = value;
    };
    return sharedModel;




//    var toolId = {
//        "id": ""
//    };
//    var objectValue = {
//        data: 'test object value'
//    };
//
//    return {
//        getToolId: function() {
//            return toolId;
//        },
//        setToolId: function(id) {
//            toolId.id = id;
//        },
//        getString: function() {
//            return stringValue;
//        },
//        setString: function(value) {
//            stringValue = value;
//        },
//        getObject: function() {
//            return objectValue;
//        }
//    }
});

