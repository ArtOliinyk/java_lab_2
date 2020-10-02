package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.company.Parser.parseLine;
import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) throws Exception {
        InputAndElse(new StringInput(System.in, System.out));
    }

    public static void InputAndElse(StringInput input) throws IOException {
        String fileName = input.ask("Enter input file name: ");
        String separator = input.ask("Enter result delimiter: ");
        FileReader fileReader = new FileReader();
        Formatter formatter = new Formatter();
        List<String> lines = fileReader.read(fileName).collect(toList());
        List<String> parsedLines = new ArrayList<>();
        lines.forEach(line -> parsedLines.add(formatter.format(parseLine(line), separator)));
        File file = new File("result.txt");
        String absolutePath = file.getAbsolutePath();
        Files.write(Paths.get(absolutePath), parsedLines, StandardCharsets.UTF_8);
    }
}
