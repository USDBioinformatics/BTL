var controllers = angular.module("Controllers", []);

controllers.controller('GlobalController', function($scope) { //pass Tree factory
    $scope.selectedNode = {
        "id": "",
        "name": "",
        "childrenCt": "",
        "parent": ""
        
    };
    
    $scope.toolInputs = {
        "format": []
    };

    $scope.toolOutputs = {
        "format": []
    };

    $scope.dataTree = {
        "id": ""
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
            //if currentNode is a tool
            if (curNode.isTool) {
                var inputCt = curNode.inputs.length;
                if(inputCt === 0) alert('TOOL HAS NO INPUTS');
                for (i = 0; i < inputCt; i++) {
                    $scope.toolInputs.format.push(curNode.inputs[i].format);
                    $scope.selectedNode.isTool = true;
                    $scope.selectedNode.childrenCt = 0;
                }
                //get outputs
                var outputCt = curNode.outputs.length;
                if (outputCt > 0) {
                    for (i = 0; i < outputCt; i++) {
                        $scope.toolOutputs.format.push(curNode.outputs[i].format);
                        alert('tool output= ' + curNode.outputs[i].format);
                    } 
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
        if ($scope.dataToolTree && angular.isObject($scope.dataToolTree.currentNode)) {
            $scope.dataTree.id = $scope.dataToolTree.currentNode.id;
        }
        ;
        ///HOW TO GET $SCOPE.CURRENTNODE from the second dataTree????????  Two directives, one for each tree.  Two scopes....how to talk with directives
        //http://seanhess.github.io/2013/10/14/angularjs-directive-design.html 
    }); //End currentNode $watch
});

controllers.controller('SimilarToolController', function($scope, SimilarToolService, sharedData) { //data is injected from app.factory 'Data' service
    SimilarToolService.success(function(data) {
        $scope.similarToolList = data;
    });
    $scope.showTools = function(tool){
        if(tool.input === '.txt'){
           //create JSON file of similar tools. 
           //$scope.similarToolList = NEWLY CREATED JSON FILE
           
             return "test"; //return true for ng-if
        }
    };
});

controllers.controller('PrevNextToolController', function($scope, sharedData) { //data is injected from app.factory 'Data' service
    $scope.toolId = sharedData.getToolId();
    
    $scope.$watch('toolOutputs.format', function() {
        alert('OUTPUT CHANGED');
    });
});



//app.controller('BookController', ['$scope', '$http', function($scope, $http) {
//    var bookId = 1;
//
//    $http.get('ourserver/books/' + bookId).success(function(bookData) {
//        $scope.book = bookData;
//    });
//}]);