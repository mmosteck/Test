package com.example.test;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

    @RequestMapping("/uploadsdsd")
    String info()
    {
        System.out.println("uploaddd");
        return "info.html";
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
    public @ResponseBody void handleFileUpload() {

        //System.out.println(request.getFiles(null));
    }
}
