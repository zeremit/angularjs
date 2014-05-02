<html ng-app="phonecatApp" ng-controller="PhoneListCtrl">
<head>
    <script src="lib/angular.min.js"></script>
    <script src="js/controllers.js"></script>
    <title ng-bind-template="Google Phone Gallery: {{query}}">Google Phone Gallery</title>
</head>
<body>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span2">
            <!--Sidebar content-->

            Search: <input ng-model="query">
            Sort by:
            <select ng-model="orderProp">
                <option value="name">Alphabetical</option>
                <option value="age">Newest</option>
            </select>

        </div>
        <div class="span10">
            <!--Body content-->
            {{orderProp}}
            <ul class="phones">
                <li ng-repeat="phone in phones | filter:query | orderBy:orderProp">
                    {{phone.name}}
                    <p>{{phone.snippet}}</p>
                </li>
            </ul>

        </div>
    </div>
</div>

</body>
</html>