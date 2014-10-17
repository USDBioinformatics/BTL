var controllers = angular.module("Controllers", []);

controllers.controller('GlobalController', function($scope) { //pass Tree factory
    $scope.selectedNode = {
        "childrenCt": ""
    };
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
            $scope.toolInputs.format.length = 0; //reset inputs array

            curNode = $scope.toolTree.currentNode; //set the selected tool tree node to a local variable
            $scope.selectedNode.id = curNode.id; //
            $scope.selectedNode.name = curNode.roleName;
            if (curNode.children) { //if there are children, set them to the $scope.selectedNode object.  Tools do NOT have children.
                $scope.selectedNode.childrenCt = curNode.children.length;
            }

            if (curNode.isTool) {
                var inputCt = curNode.inputs.length;
                for (i = 0; i < inputCt; i++) {
                    $scope.toolInputs.format.push(curNode.inputs[i].format);
                    $scope.selectedNode.isTool = true;
                    $scope.selectedNode.childrenCt = 0;
                }
            } else {
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
    
    $scope.$watch('dataToolTree.currentNode', function() {
        alert("WATCHED IN DATA TREE");
        ///HOW TO GET $SCOPE.CURRENTNODE from the second dataTree????????  Two directives, one for each tree.  Two scopes....how to talk with directives
        //http://seanhess.github.io/2013/10/14/angularjs-directive-design.html 
    }); //End currentNode $watch
});

controllers.controller('SimilarToolController', function($scope, SimilarToolService, sharedData) { //data is injected from app.factory 'Data' service
    SimilarToolService.success(function(data) {
        $scope.similarToolList = data;
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