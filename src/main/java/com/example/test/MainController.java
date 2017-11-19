package com.example.test;



import io.gitlab.arturbosch.detekt.cli.Main;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

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
    public @ResponseBody void handleFileUpload(@RequestParam("plik") List<MultipartFile> files) throws IOException
    {
        System.out.println("dropzone files: ");
        files.forEach(f -> System.out.println(f.getOriginalFilename()));

        Path tempPath = Files.createTempDirectory("uploadedFiles");

        for(MultipartFile currentFile: files)
        {
            Path tempFile = Files.createTempFile(tempPath, "temp", ".kt");
            currentFile.transferTo(tempFile.toFile());
        }

        File defaultConfig = new File("src/main/resources/static/config.yml");
        Path tempConfig = Files.createTempFile(tempPath, "config", ".yml");
        //tempConfig.toFile().deleteOnExit();
        Files.copy(defaultConfig.toPath(), tempConfig, StandardCopyOption.REPLACE_EXISTING);
       // Scanner sc = new Scanner(tempConfig.toFile());

        //System.out.println(sc.next());
        //System.out.println(tempConfig.toString());
        //tempConfig.toFile().delete();
        //sc.close();

        FileWriter fw = new FileWriter(tempConfig.toFile(), true);
        fw.write("build:\n" +
                "  warningThreshold: 5\n" +
                "  failThreshold: 10");
        fw.close();
        System.out.println(tempPath.toString());
        System.out.println(tempConfig.toString());

        String [] args = new String [] {"--input", tempPath.toString(), " -cr", tempConfig.toString()};
        Main.main(args);
        //FileUtils.deleteDirectory(tempPath.toFile());
    }
}
