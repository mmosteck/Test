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
    acceptedFiles: ".kt",
    previewContainer: false,
    error: function(file)
    {
        console.log("err");
    }

 };
var app = angular.module('app', []);

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
       var myDropzone = Dropzone.forElement(".dropzone");
       myDropzone.processQueue();

       var form = document.forms['filesform'];
       console.log(form);
       form.submit();
    };
});

