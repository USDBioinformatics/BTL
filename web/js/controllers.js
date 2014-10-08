var controllers = angular.module("Controllers", []);

controllers.controller('GlobalController', function ($scope) { //pass Tree factory
    $scope.test = {};
    $scope.test.toolId = '1';

});

controllers.controller('ToolTreeController', function ($scope, TreeListService, sharedData) { //pass Tree factory
    TreeListService.success(function (data) {
        $scope.treeList = data;
    });

    $scope.$watch('toolTree.currentNode', function (newObj, oldObj) {
        $scope.test.toolId = $scope.toolTree.currentNode.id;

//        if ($scope.toolTree && angular.isObject($scope.toolTree.currentNode)) {
//            console.log('Node Selected!!');
//            console.log($scope.toolTree.currentNode);
//            
//            
////            $scope.setToolId = function(newValue) {
////                $scope.objectValue.data = newValue;
////                sharedData.setToolId(newValue);
////            };
//        }
//    }, false);

//    $scope.objectValue = sharedData.getObject();

    });
});

controllers.controller('ToolDataController', function ($scope, TreeListService, sharedData) {
    TreeListService.success(function (data) {
        $scope.toolId = sharedData.getToolId();
    });
});

controllers.controller('DataTreeController', function ($scope, DataTreeService, sharedData) {
    DataTreeService.success(function (data) {
        $scope.dataTree = data;
        $scope.toolId = sharedData.getToolId();
    });
});

controllers.controller('SimilarToolController', function ($scope, SimilarToolService, sharedData) { //data is injected from app.factory 'Data' service
    SimilarToolService.success(function (data) {
        $scope.similarToolList = data;

        $scope.$watch('toolTree.currentNode', function (newObj, oldObj) {
            alert('watched');
        }
        , false);

    });
});

controllers.controller('PrevNextToolController', function ($scope, sharedData) { //data is injected from app.factory 'Data' service
    $scope.toolId = sharedData.getToolId();
});



//app.controller('BookController', ['$scope', '$http', function($scope, $http) {
//    var bookId = 1;
//
//    $http.get('ourserver/books/' + bookId).success(function(bookData) {
//        $scope.book = bookData;
//    });
//}]);