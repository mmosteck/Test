package com.example.test;



import io.gitlab.arturbosch.detekt.cli.Main;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
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

    @RequestMapping(value = "/set",
    method = RequestMethod.POST
           )
    public @ResponseBody void info(@RequestParam("firstc") Boolean c)
    {
        System.out.println("AAAAAAAAAASDF@#RFGGGGGGGGGGGGGGGGGGGGGGGGGG " + c);
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
    public @ResponseBody void handleFileUpload(@RequestParam("plik") List<MultipartFile> files,
            @RequestParam("comment") Boolean comment,
            @RequestParam("naming") Boolean naming,
            @RequestParam("newLine") Boolean newLine,
            @RequestParam("styleWeight") Integer styleWeight,
            @RequestParam("quantity") Integer quantity) throws IOException, ServletException
    {
        //System.out.println("dropzone files: ");
       // files.forEach(f -> System.out.println(f.getOriginalFilename()));
        //System.out.println(o);
        Path tempPath = Files.createTempDirectory("uploadedFiles");
        System.out.println("comment: " + comment + "\nnaming: " + naming + "\nempty_blocks: " + newLine + "\nweight: " + styleWeight + "\nqua: " + quantity);
       // request.getParameterMap().forEach((key, value) -> System.out.println(key + ":" + value));

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
        Files.lines(tempConfig);
        if(comment)
        {
            fw.write("comments:\n" +
                    "  active: false\n");
        }
        if(naming)
        {
            fw.write("style:\n" +
                    "PackageNaming:\n" +
                    "    active: false\n" +
                    "ClassNaming:\n" +
                    "    active: false\n" +
                    "EnumNaming:\n" +
                    "    active: false\n" +
                    "FunctionNaming:\n" +
                    "    active: false\n");
        }
        if(newLine)
        {
            fw.write("\nstyle:\n" +
                    "NewLineAtEndOfFile:\n" +
                    "    active: true");
        }


        fw.close();
        System.out.println(tempPath.toString());
        System.out.println(tempConfig.toString());

        String [] args = new String [] {"--input", tempPath.toString(), " -c", tempConfig.toString()};
        Main.main(args);
        FileUtils.deleteDirectory(tempPath.toFile());
    }
}
