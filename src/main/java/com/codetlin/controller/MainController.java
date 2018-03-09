package com.codetlin.controller;



import com.codetlin.result.ResultCalculator;
import io.gitlab.arturbosch.detekt.cli.Main;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

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

    @RequestMapping(value = "/test.html", method = RequestMethod.GET)
    String about()
    {
        //System.out.println("first page!");
        return "result.html";
    }

    @RequestMapping( value = "/uploada", method = RequestMethod.POST)
    public String aa(@RequestParam("plik") List<MultipartFile> files,
              @RequestParam("comment") Boolean comment,
              @RequestParam("naming") Boolean naming,
              @RequestParam("newLine") Boolean newLine,
              @RequestParam("styleWeight") Integer styleWeight,
              @RequestParam("quantity") Integer quantity,
                                    HttpServletResponse response) throws IOException
    {
        //response.sendRedirect("redirect:/test.html");
        return "result.html";

    }



    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("plik") List<MultipartFile> files,
                                         @RequestParam("comment") Boolean comment,
                                         @RequestParam("naming") Boolean naming,
                                         @RequestParam("newLine") Boolean newLine,
                                         @RequestParam("styleWeight") Integer styleWeight,
                                         @RequestParam("quantity") Integer quantity                                                 ) throws Exception
    {
        Path tempPath = Files.createTempDirectory("uploadedFiles");
        System.out.println("comment: " + comment + "\nnaming: " + naming + "\nempty_blocks: " + newLine + "\nweight: " + styleWeight + "\nqua: " + quantity);

        System.out.println("uploaded " + files.size() + " files ------------------------------");
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

        String [] args = new String [] {"--input", tempPath.toString(), " -c", tempConfig.toString(), " --output", tempPath.toString()};
        Main.main(args);


        ResultCalculator resultCalculator = new ResultCalculator();
        resultCalculator.calculateRatings(new File(tempPath.toString() + "\\detekt-checkstyle.xml"));
        System.out.println("overall result: " + resultCalculator.getOverallRating());

        //response.sendRedirect("t");
        FileUtils.deleteDirectory(tempPath.toFile());

        return "redirect:/result.html";
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
