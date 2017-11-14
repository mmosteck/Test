package com.example.test;


import com.sun.org.apache.xpath.internal.operations.Mult;
import io.gitlab.arturbosch.detekt.cli.Main;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class MainController
{
    @RequestMapping("/")
    String index(Model model)
    {
        //System.out.println("Keks");
        return "index.html";
    }

    @RequestMapping(value = "/up",
    method = RequestMethod.POST
           )
    public @ResponseBody void info(@RequestParam("ff") List<MultipartFile> files)
    {
        System.out.println("input files: ");
        files.forEach(f -> System.out.println(f.getOriginalFilename()));
    }

    @RequestMapping("/t")
    String about()
    {
        //System.out.println("first page!");
        return "test.html";
    }

    @RequestMapping(value = "/upload",
            method = RequestMethod.POST
           )
    public @ResponseBody void handleFileUpload(@RequestParam("plik") List<MultipartFile> file) throws IOException
    {

        System.out.println("dropzone files: ");
        file.forEach(f -> System.out.println(f.getOriginalFilename()));
        //System.out.println(this.getClass().getResource("").getPath());

        Path tempPath = Files.createTempDirectory("uploadedFiles");
        Path p = Files.createTempFile(tempPath, "plig", ".kt");
        file.get(0).transferTo(p.toFile());
        System.out.println(p);
       // FileUtils.deleteDirectory(tempPath.toFile());
        //System.out.println(p);
        //temp.delete();
       // System.out.println(System.getProperty("java.io.tmpdir"));

       // System.out.println(file.get(0).transferTo(new File("GEGz.kt")));
        String [] args = new String [] {"--input", p.toString()};
        Main.main(args);
    }
}
