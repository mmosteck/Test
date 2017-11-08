/*global angular */
'use strict';

/**
 * The main app module
 * @name app
 * @type {angular.Module}
 */




var app = angular.module('app', []);


Dropzone.options.myD = {
    uploadMultiple:true,
    paramName: "plik",
    autoProcessQueue: false,
    parallelUploads: 5,
    acceptedFiles: ".kt",
    previewsContainer: "",
   // previewTemplate: '<div style="display:none"></div>',
    previewTemplate: '<div class="dz-preview dz-file-preview" width = "50px"><div class="dz-details"><div class="dz-filename"><span data-dz-name></span></div><div class="dz-size" data-dz-size></div></div><div class="dz-error-message"><span data-dz-errormessage></span></div></div>',
    init: function()
    {
        var dropzone = Dropzone.forElement(".dropzone");
        dropzone["filesCount"] = 0;
        dropzone["filesSize"] = 0;

        this.on("addedfile", function(file) {
            var extension = file.name.split('.').pop();

            var display = document.getElementById("pre");
            //display.style.display = "initial";

            console.log("added file " + file);
            console.log("current files " + this.getAddedFiles());
            console.log("accepted files " + this.getAcceptedFiles());
            dropzone.filesCount++;
            dropzone.filesSize += file.size;

            // check file extension
            if (extension !== "kt") {
                this.removeFile(file);
                dropzone.filesCount--;
                dropzone.filesSize -= file.size;
            }

            // check for duplicates
            for(var i = 0; i < this.files.length - 1; i++)
            {
                var currentFile = this.files[i];
                if(currentFile.name === file.name && currentFile.lastModified === file.lastModified && currentFile.size === file.size)
                {
                    this.removeFile(file);
                    dropzone.filesCount--;
                    dropzone.filesSize -= file.size;
                }
            }

           // filesCount = dropzone.getAcceptedFiles();
           // document.getElementById("totalFiles").innerText = "Project: " + projectName;
            updateProjectView();
            //document.getElementById("totalFiles").innerText = "Total files: " + dropzone.filesCount;
           // document.getElementById("totalSize").innerText = "Total project size: " + dropzone.filesSize + " bytes";


        });
        var input = document.getElementById("inputa");
        input.onchange = function(event)
        {
            var dropzone = Dropzone.forElement(".dropzone");
            console.log("on change called")
            dropzone.removeAllFiles(true);
            var filesArray = Array.from(input.files);
            for(var i = 0; i < filesArray.length; i++)
            {
                dropzone.addFile(filesArray[i]);
            }
        }
    }};
   // File extension validation




app.controller("Ctrl", function ($scope, $http) {

    $scope.upload = function ()
    {
        var dropzone = Dropzone.forElement(".dropzone");
        dropzone.processQueue();

      // var form = document.forms['filesform'];
     //  console.log(form);
      // form.submit();

    };

    $scope.clearFiles = function()
    {
        var dropzone = Dropzone.forElement(".dropzone");
        dropzone.removeAllFiles();
        dropzone.filesSize = 0;
        dropzone.filesCount = 0;
        updateProjectView();
    };


});

function updateProjectView() {
    var dropzone = Dropzone.forElement(".dropzone");
    document.getElementById("totalFiles").innerText = "Total files: " + dropzone.filesCount;
    document.getElementById("totalSize").innerText = "Total project size: " + dropzone.filesSize + " bytes";
}

