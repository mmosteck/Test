/*global angular */
'use strict';

/**
 * The main app module
 * @name app
 * @type {angular.Module}
 */


var app = angular.module('app', []);
console.log("keks");
Dropzone.options.myD = {
    uploadMultiple:true,
    clickable: false,
    paramName: "plik",
    autoProcessQueue: false,
    parallelUploads: 5,
    acceptedFiles: ".kt",
    //previewsContainer: "",
   // previewTemplate: '<div style="display:none"></div>',
    previewTemplate: '<div class="dz-preview dz-file-preview"><div class="dz-details"><div class="dz-filename"><span data-dz-name></span></div><div class="dz-size" data-dz-size></div></div><div class="dz-error-message"><span data-dz-errormessage></span></div></div>',
    init: function()
    {
        var dropzone = Dropzone.forElement(".dropzone");
        dropzone["filesCount"] = 0;
        dropzone["filesSize"] = 0;

        this.on("addedfile", function(file) {
            var extension = file.name.split('.').pop();
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
            updateProjectView();
        });

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
    }};

app.controller("Ctrl", function ($scope, $http) {

    $scope.upload = function ()
    {
        var dropzone = Dropzone.forElement(".dropzone");
        dropzone.processQueue();
    };

    $scope.clearFiles = function()
    {
        var dropzone = Dropzone.forElement(".dropzone");
        dropzone.removeAllFiles();
        dropzone.filesSize = 0;
        dropzone.filesCount = 0;
        //updateProjectView();
        document.getElementById("pre").style.visibility = "hidden";
    };


});

function updateProjectView() {
    var dropzone = Dropzone.forElement(".dropzone");
    document.getElementById("pre").style.visibility = "visible";
    document.getElementById("totalFiles").innerText = "Total files: " + dropzone.filesCount;
    document.getElementById("totalSize").innerText = "Total project size: " + dropzone.filesSize + " bytes";
}

