package com.example.test;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/infao")
    String info()
    {
        //System.out.println("first page!");
        return "info.html";
    }

    @RequestMapping("/t")
    String about()
    {
        //System.out.println("first page!");
        return "test.html";
    }
}
