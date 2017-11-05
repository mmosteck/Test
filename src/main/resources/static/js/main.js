/*global angular */
'use strict';

/**
 * The main app module
 * @name app
 * @type {angular.Module}
 */


Dropzone.options.myD = {
     uploadMultiple:true,
     paramName: "plik",
    autoProcessQueue: false,
    parallelUploads: 5,
     init: function()
     {
         this.on("processingmultiple", function(file) { alert(this.options.paramName); });
     }
 };
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
        for(var file in files) {
            formData.append("file", file.name);
        }

        xhr.open('POST', '/upload');
        xhr.send(formData);
        console.log(formData);
        // formData.append("file",files);
        // $http.post('/upload', formData, {
        // transformRequest: angular.identity,
        //     headers: {'Content-Type': undefined}
   // });

    };

    $scope.upload = function ()
    {
        console.log("keks");
        var myDropzone = Dropzone.forElement(".dropzone");
        myDropzone.processQueue();
    };
});

