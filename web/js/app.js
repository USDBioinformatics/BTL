(function() {
    var app = angular.module('BTL', ['angularTreeview']);
    app.directive('draggable', function() {
        return function(scope, element) {
            // this gives us the native JS object
            var el = element[0];
            el.draggable = true;
            el.addEventListener(
                    'dragstart',
                    function(e) {
                        e.dataTransfer.effectAllowed = 'move';
                        e.dataTransfer.setData('Text', this.id);
                        this.classList.add('drag');
                        return false;
                    },
                    false
                    );
            el.addEventListener(
                    'dragend',
                    function(e) {
                        this.classList.remove('drag');
                        return false;
                    },
                    false
                    );
        }
    });
    app.directive('droppable', function() {
        return {
            scope: {
                drop: '&' // parent
            },
            link: function(scope, element) {
                // again we need the native object
                var el = element[0];
                el.addEventListener(
                        'dragover',
                        function(e) {
                            e.dataTransfer.dropEffect = 'move';
                            // allows us to drop
                            if (e.preventDefault)
                                e.preventDefault();
                            if (e.stopPropagation) {
                                e.stopPropagation();
                            }
                            this.classList.add('over');
                            return false;
                        },
                        false
                        );
                el.addEventListener(
                        'dragenter',
                        function(e) {
                            this.classList.add('over');
                            return false;
                        },
                        false
                        );
                el.addEventListener(
                        'dragleave',
                        function(e) {
                            this.classList.remove('over');
                            return false;
                        },
                        false
                        );
                el.addEventListener('drop', function(e) {
                    // Stops some browsers from redirecting.
                    if (e.stopPropagation)
                        e.stopPropagation();
                    if (e.preventDefault)
                        e.preventDefault();
                    this.classList.remove('over');
                    var outputPanel = document.getElementById('outputPanel');
                    var item = document.getElementById(e.dataTransfer.getData('Text'));
                    var list = $(".listItem");
                    var version = list.data("bets-version");
                    if (item.id == 1) {
                        this.appendChild(item);
                        outputPanel.innerHTML = 'DISPLAY TOOL DATA HERE\r'
                                + 'ID = ' + item.id
                                + '\rVersion = ' + version;
                    } else if (item.id == 2) {
                        this.appendChild(item);
                        outputPanel.innerHTML = 'DISPLAY TOOL DATA HERE - ID = ' + item.id;
                    }
                    else if (item.id == 3) {
                        this.appendChild(item);
                        outputPanel.innerHTML = 'DISPLAY TOOL DATA HERE - ID = ' + item.id;
                    } else if (item.id == 4) {
                        this.appendChild(item);
                        outputPanel.innerHTML = 'DISPLAY TOOL DATA HERE - ID = ' + item.id;
                    } else if (item.id == 5) {
                        this.appendChild(item);
                        outputPanel.innerHTML = 'DISPLAY TOOL DATA HERE - ID = ' + item.id;
                    }
                    else {
                        this.appendChild(item);
                        alert('Item ID = ' + item.id);
                    }// call the drop passed drop function
                    scope.$apply('drop()');
                    return false;
                },
                        false
                        );
            }
        } //end return
    });
    app.controller('ToolListController', function($scope, $http) {
        $scope.toolVal = {version: '1.0'};
        getBets($scope, $http);
        getToolList($scope, $http);
        getReporting($scope, $http);
        $scope.handleDrop = function() {
            //code can go here
        };
    }); // END ToolListController 

    app.controller('ToolTreeController', function($scope) {

        //test tree data
        $scope.treeList = [
            {"roleName": "Applications", "id": "role1", "children": [
                    {"roleName": "subApplication", "id": "role11", "collapsed": true, "children": [
                            {
                                "roleName": "Test Tool 1",
                                "id": 001,
                                "toolName": "Test Tool 1",
                                "toolDescription": "Test tool 1 description",
                                "domains": [
                                    {
                                        "domainName": "Tool 1 Domain Name",
                                        "domainURI": "http://domain.uri1"
                                    }
                                ],
                                "applications": [
                                    {
                                        "appName": "Tool 1 Application Name",
                                        "appURI": "http://app.uri1"
                                    }
                                ]
                            },
                            {
                                "roleName": "Test Tool 2",
                                "id": 002,
                                "toolName": "Test Tool 2",
                                "toolDescription": "Test tool 2 description",
                                "domains": [
                                    {
                                        "domainName": "Tool 2 Domain Name",
                                        "domainURI": "http://domain.uri2"
                                    }
                                ],
                                "applications": [
                                    {
                                        "appName": "Tool 2 Application Name",
                                        "appURI": "http://app.uri2"
                                    }
                                ]
                            }
                        ]},
                    {"roleName": "subApplication2", "id": "role12", "collapsed": true, "children": [
                            {"roleName": "subApplication2-1", "id": "role121", "children": [
                                    {"roleName": "subApplication2-1-1", "id": "role1211", "children": []},
                                    {"roleName": "subApplication2-1-2", "id": "role1212", "children": [
                                             {"roleName": "subApplication2-1-2-1", "id": "role1211", "children": [
                                                      {"roleName": "subApplication2-1-1", "id": "role1211", "children": []}
                                             ]},
                                             {"roleName": "subApplication2-1-2-2", "id": "role1211", "children": []}
                                    ]}
                                ]}
                        ]}
                ]},
            {"roleName": "Domains", "id": "role2", "children": [
                    {"roleName": "subDomain1", "id": "role11", "collapsed": true, "children": []},
                    {"roleName": "subDomain2", "id": "role12", "children": [
                            {"roleName": "subDomain2-1", "id": "role121", "children": [
                                    {"roleName": "subDomain2-1-1", "id": "role1211", "children": []},
                                    {"roleName": "subDomain2-1-2", "id": "role1212", "children": []}
                                ]}
                        ]}
                ]},
            {"roleName": "Other", "id": "role3", "children": [
                    {"roleName": "subGuest1", "id": "role11", "children": []},
                    {"roleName": "subGuest2", "id": "role12", "collapsed": true, "children": [
                            {"roleName": "subGuest2-1", "id": "role121", "children": [
                                    {
                                "roleName": "Test Tool 1",
                                "id": 001,
                                "toolName": "Test Tool 1",
                                "toolDescription": "Test tool 1 description",
                                "domains": [
                                    {
                                        "domainName": "Tool 1 Domain Name",
                                        "domainURI": "http://domain.uri1"
                                    }
                                ],
                                "applications": [
                                    {
                                        "appName": "Tool 1 Application Name",
                                        "appURI": "http://app.uri1"
                                    }
                                ]
                            },
                            {
                                "roleName": "Test Tool 2",
                                "id": 002,
                                "toolName": "Test Tool 2",
                                "toolDescription": "Test tool 2 description",
                                "domains": [
                                    {
                                        "domainName": "Tool 2 Domain Name",
                                        "domainURI": "http://domain.uri2"
                                    }
                                ],
                                "applications": [
                                    {
                                        "appName": "Tool 2 Application Name",
                                        "appURI": "http://app.uri2"
                                    }
                                ]
                            }
                                ]}
                        ]}
                ]}
        ];
    });


    app.controller('ReportingController', function($scope, $filter) {

        var tools = {
            "tools":
                    [
                        {
                            "toolId": 001,
                            "toolName": "Test Tool 1",
                            "toolDescription": "Test tool 1 description",
                            "domains": [
                                {
                                    "domainName": "Tool 1 Domain Name",
                                    "domainURI": "http://domain.uri1"
                                }
                            ],
                            "applications": [
                                {
                                    "appName": "Tool 1 Application Name",
                                    "appURI": "http://app.uri1"
                                }
                            ]
                        },
                        {
                            "toolId": 002,
                            "toolName": "Test Tool 2",
                            "toolDescription": "Test tool 2 description",
                            "domains": [
                                {
                                    "domainName": "Tool 2 Domain Name",
                                    "domainURI": "http://domain.uri2"
                                }
                            ],
                            "applications": [
                                {
                                    "appName": "Tool 2 Application Name",
                                    "appURI": "http://app.uri2"
                                }
                            ]
                        }
                    ]
        };
        var result = $filter('filter')(tools.tools, {toolId: 002})[0];
        $scope.name = result.toolName;
        $scope.tools = tools;
    });
    /**
     * 
     HELPER METHODS
     */
    function getBets($scope, $http) {
        $http.get('input_files/test_bets.json').success(function(data) {
            $scope.toolData = data;
        });
    }
    ;
    function getToolList($scope, $http) {
        $http.get('input_files/tool_list.json').success(function(data) {
            $scope.toolList = data;
        });
    }
    ;
    function getReporting($scope, $http) {
        $http.get('input_files/reporting_file.json').success(function(data) {
            $scope.report = data;
        });
    }
    ;
})();
    