var treeListController = angular.module("Controllers", []);

treeListController.controller('ToolTreeController', function($scope, TreeListService, sharedData) { //pass Tree factory
    TreeListService.success(function(data) {
        $scope.treeList = data;
    });

    
    $scope.objectValue = sharedData.getObject();
    $scope.setToolId = function(newValue) {
        $scope.objectValue.data = newValue;
        sharedData.setToolId(newValue);
        $scope.toolId = sharedData.getToolId();
    }
});

treeListController.controller('ToolDataController', function($scope, TreeListService, sharedData) {
    TreeListService.success(function(data) {
        $scope.toolData = data;
        $scope.toolId = sharedData.getToolId();
    });
});

treeListController.controller('DataTreeController', function($scope, DataTreeService, sharedData) {
    DataTreeService.success(function(data) {
        $scope.dataTree = data;
        $scope.toolId = sharedData.getToolId();
    });
});

treeListController.controller('SimilarToolController', function($scope, SimilarToolService, sharedData) { //data is injected from app.factory 'Data' service
    SimilarToolService.success(function(data) {
        $scope.similarToolList = data;
        $scope.toolId = sharedData.getToolId();
        
    });
});

treeListController.controller('PrevNextToolController', function($scope, sharedData) { //data is injected from app.factory 'Data' service
    $scope.toolId = sharedData.getToolId();
});



//app.controller('BookController', ['$scope', '$http', function($scope, $http) {
//    var bookId = 1;
//
//    $http.get('ourserver/books/' + bookId).success(function(bookData) {
//        $scope.book = bookData;
//    });
//}]);