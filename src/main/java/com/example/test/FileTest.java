package com.example.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

public class FileTest
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("ke.txt");
        Scanner sc = new Scanner(file);

        List<String> lines = Files.readAllLines(file.toPath());

        lines.forEach(System.out::println);

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if(line.equals("line 3"))
                lines.set(i, "line2137");
        }


        Files.write(file.toPath(), lines, StandardCharsets.UTF_8);
        File file2 = new File("ke.txt");
        Scanner sc2 = new Scanner(file2);
        List<String> lines2 = Files.readAllLines(file2.toPath());
        lines2.forEach(System.out::println);
    }
}
