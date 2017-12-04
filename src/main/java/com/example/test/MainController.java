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
import java.nio.charset.StandardCharsets;
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
    private static final String ACTIVE_FALSE = "  active: false";
    private static final String ACTIVE_FALSE_DOUBLESPACE = "    active: false";

    private static final String ACTIVE_TRUE_DOUBLESPACE = "    active: true";

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
        Path tempPath = Files.createTempDirectory("uploadedFiles");
        System.out.println("comment: " + comment + "\nnaming: " + naming + "\nempty_blocks: " + newLine + "\nweight: " + styleWeight + "\nqua: " + quantity);

        for(MultipartFile currentFile: files)
        {
            Path tempFile = Files.createTempFile(tempPath, "temp", ".kt");
            currentFile.transferTo(tempFile.toFile());
        }

        File defaultConfig = new File("src/main/resources/static/config.yml");
        Path tempConfig = Files.createTempFile(tempPath, "config", ".yml");
        //tempConfig.toFile().deleteOnExit();
        Files.copy(defaultConfig.toPath(), tempConfig, StandardCopyOption.REPLACE_EXISTING);

        if(comment)
        {
            replaceNextLine(tempConfig, "comments:", ACTIVE_FALSE);
        }

        if(naming)
        {
            replaceNextLine(tempConfig, "  PackageNaming:", ACTIVE_FALSE_DOUBLESPACE);
            replaceNextLine(tempConfig, "  ClassNaming:", ACTIVE_FALSE_DOUBLESPACE);
            replaceNextLine(tempConfig, "  EnumNaming:", ACTIVE_FALSE_DOUBLESPACE);
            replaceNextLine(tempConfig, "  FunctionNaming:", ACTIVE_FALSE_DOUBLESPACE);
            replaceNextLine(tempConfig, "  VariableNaming:", ACTIVE_FALSE_DOUBLESPACE);
            replaceNextLine(tempConfig, "  ConstantNaming:", ACTIVE_FALSE_DOUBLESPACE);
        }

        if (newLine)
        {
            replaceNextLine(tempConfig, "  NewLineAtEndOfFile:", ACTIVE_TRUE_DOUBLESPACE);
        }

        if(quantity != 2)
            replaceReturns(tempConfig, "  ReturnCount:", quantity);

        System.out.println(tempPath.toString());
        System.out.println(tempConfig.toString());

        String [] args = new String [] {"--input", tempPath.toString(), " -c", tempConfig.toString()};
        Main.main(args);
        FileUtils.deleteDirectory(tempPath.toFile());
    }

    private void replaceNextLine(Path config, String line, String toReplace) throws IOException
    {
        List<String> lines = Files.readAllLines(config);
        for (int i = 0; i < lines.size(); i++) {
            String currentLine = lines.get(i);
            if(currentLine.equals(line))
            {
                lines.set(i + 1, toReplace);
                break;
            }
        }
        Files.write(config, lines, StandardCharsets.UTF_8);
    }

    private void replaceReturns(Path config, String line, int maxReturns) throws IOException
    {
        List<String> lines = Files.readAllLines(config);
        for (int i = 0; i < lines.size(); i++) {
            String currentLine = lines.get(i);
            if(currentLine.equals(line))
            {
                lines.set(i + 2, "    max: " + maxReturns);
                break;
            }
        }
        Files.write(config, lines, StandardCharsets.UTF_8);
    }
}
