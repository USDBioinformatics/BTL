var controllers = angular.module("Controllers", []);

controllers.controller('GlobalController', function($scope) { //pass Tree factory
    $scope.selectedNode = {};
    $scope.toolInputs = {
        "format": []
    };
});

controllers.controller('ToolTreeController', function($scope, TreeListService) { //pass Tree factory
    TreeListService.success(function(data) {
        $scope.treeList = data;
    });
    $scope.$watch('toolTree.currentNode', function() {
        var curNode;
        if ($scope.toolTree && angular.isObject($scope.toolTree.currentNode)) {
            curNode = $scope.toolTree.currentNode;
            $scope.selectedNode.id = curNode.id;
            $scope.selectedNode.name = curNode.roleName;
            $scope.selectedNode.children = curNode.children;
            $scope.toolInputs.format.length = 0; //reset inputs array

            if (curNode.isTool) {
                alert('IS A TOOL');
                var inputCt = curNode.inputs.length;
                for (i = 0; i < inputCt; i++) {
                    $scope.toolInputs.format.push(curNode.inputs[i].format);
                    $scope.selectedNode.isTool = true;
                    alert($scope.toolInputs.format.length);
                }
            }else{
                $scope.selectedNode.isTool = false;
            }
            
        }
    }); //End currentNode $watch
});
controllers.controller('ToolDataController', function($scope, TreeListService, sharedData) {
    $scope.toolId = sharedData.getToolId();
});
controllers.controller('DataTreeController', function($scope, DataTreeService, sharedData) {
    DataTreeService.success(function(data) {
        $scope.dataTree = data;
        $scope.toolId = sharedData.getToolId();
    });
});
controllers.controller('SimilarToolController', function($scope, SimilarToolService, sharedData) { //data is injected from app.factory 'Data' service
    SimilarToolService.success(function(data) {
        $scope.similarToolList = data;
    });
    $scope.$watch('toolInputs.count', function(newObj, oldObj) {
        alert("watched in similarTool");
        $scope.similarTools = [];
    });
});
controllers.controller('PrevNextToolController', function($scope, sharedData) { //data is injected from app.factory 'Data' service
    $scope.toolId = sharedData.getToolId();
});
//app.controller('BookController', ['$scope', '$http', function($scope, $http) {
//    var bookId = 1;
//
//    $http.get('ourserver/books/' + bookId).success(function(bookData) {
//        $scope.book = bookData;
//    });
//}]);