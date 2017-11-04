/*global angular */
'use strict';

/**
 * The main app module
 * @name app
 * @type {angular.Module}
 */
var app = angular.module('app', ['flow'])
    .config(['flowFactoryProvider', function (flowFactoryProvider) {
        flowFactoryProvider.defaults = {
            target: '/upload',
            permanentErrors: [500, 501],
            maxChunkRetries: 1,
            chunkRetryInterval: 5000,
            simultaneousUploads: 1,
            testChunks: false
        };
        flowFactoryProvider.on('catchAll', function (event) {
            console.log('catchAll', arguments);
        });
        // Can be used with different implementations of Flow.js
        flowFactoryProvider.factory = fustyFlowFactory;


    }]);

app.controller("Ctrl", function ($scope, $http) {
    $scope.check = function check(files)
    {
        console.log(files);
        var xhr = new XMLHttpRequest();
        var formData=new FormData();
        xhr.open('POST', '/upload');
        xhr.send(formData);
        // formData.append("file",files);
        // $http.post('/upload', formData, {
        // transformRequest: angular.identity,
        //     headers: {'Content-Type': undefined}
   // });

    };
});

