var sharedDirectives = angular.module("sharedDirectives", []);


sharedDirectives.directive("treeModel", function($compile) {
        return{restrict: "A", link: function(scope, a, g, c) {
                l.search = null;
                
                var e = c.treeModel, h = c.nodeLabel || "label", d = c.nodeChildren || "children", k = '<ul><li draggable data-ng-repeat="node in ' + e + '| filter:search"><i class="collapsed" data-ng-show="node.' + d + '.length && node.collapsed" data-ng-click="selectNodeHead(node, $event)"></i><i class="expanded" data-ng-show="node.' + d + '.length && !node.collapsed" data-ng-click="selectNodeHead(node, $event)"></i><i class="normal" data-ng-hide="node.' +
                        d + '.length"></i> <span data-ng-class="node.selected" data-ng-click="selectNodeLabel(node, $event)">{{node.' + h + '}}</span><div data-ng-hide="node.collapsed" data-tree-model="node.' + d + '" data-node-id=' + (c.nodeId || "id") + " data-node-label=" + h + " data-node-children=" + d + "></div></li></ul>";
                e && e.length && (c.angularTreeview ? (a.$watch(e, function(m, b) {
                    g.empty().html($compile(k)(a))
                }, !1), a.selectNodeHead = a.selectNodeHead || function(a, b) {
                    b.stopPropagation && b.stopPropagation();
                    b.preventDefault && b.preventDefault();
                    b.cancelBubble =
                            !0;
                    b.returnValue = !1;
                    a.collapsed = !a.collapsed
                }, a.selectNodeLabel = a.selectNodeLabel || function(c, b) {
                    b.stopPropagation && b.stopPropagation();
                    b.preventDefault && b.preventDefault();
                    b.cancelBubble = !0;
                    b.returnValue = !1;
                    a.currentNode && a.currentNode.selected && (a.currentNode.selected = void 0);
                    c.selected = "selected";
                    a.currentNode = c;
                    scope.currentNode = c;
                }) : g.html($compile(k)(a)));
            }};
    });








sharedDirectives.directive('draggable', function() {
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
    sharedDirectives.directive('droppable', function() {
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