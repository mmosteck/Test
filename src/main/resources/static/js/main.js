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
    //previewTemplate: '<div class="dz-preview dz-file-preview"><div class="dz-details"><div class="dz-filename"><span data-dz-name></span></div><div class="dz-size" data-dz-size></div></div><div class="dz-error-message"><span data-dz-errormessage></span></div></div>',
    init: function()
    {
         var input = document.getElementById("inputa");
         input.onchange = function(event)
         {
            var dropzone = Dropzone.forElement(".dropzone");
            dropzone.removeAllFiles(true);
            var filesArray = Array.from(input.files);
            for(var i = 0; i < filesArray.length; i++)
            {
                dropzone.addFile(filesArray[i]);
            }
         }
    },

    // File extension validation
    addedfile: function(file) {
                    var extension = file.name.split('.').pop();
                    console.log("added file " + file);
                    console.log("current files " + this.getAddedFiles());
                    console.log("accepted files " + this.getAcceptedFiles());
                    if (extension != "kt")
                    {
                        this.removeFile(file);
                    }
                }};
var app = angular.module('app', []);

app.controller("Ctrl", function ($scope, $http) {

    $scope.upload = function ()
    {
       var dropzone = Dropzone.forElement(".dropzone");
       dropzone.processQueue();

      // var form = document.forms['filesform'];
     //  console.log(form);
      // form.submit();

    };
});

