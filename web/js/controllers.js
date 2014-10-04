var treeListController = angular.module("TreeListController", []);

treeListController.controller('ToolTreeController', function($scope, TreeListService) { //pass Tree factory
    TreeListService.success(function(data) {
        $scope.treeList = data;
    });
});

treeListController.controller('ToolDataController', function($scope, TreeListService) {
    TreeListService.success(function(data) {
        $scope.toolData = data;
    });
});

treeListController.controller('DataTreeController', function($scope, $http) {
    $http.get("./input_files/dataTree.json")
            .success(function(response) {
                $scope.dataTree = response;
            });
});

treeListController.controller('SimilarToolController', function($scope, SimilarToolService) { //data is injected from app.factory 'Data' service
    SimilarToolService.success(function(data) {
        $scope.similarToolList = data;
    });
});