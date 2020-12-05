package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) throws IOException {
        Task(new StringInput(System.in, System.out));
    }

    public static void Task(StringInput input) throws IOException{
        File file = new File("result.txt");
        String absolutePath = file.getAbsolutePath();
        Files.write(Paths.get(absolutePath), new ArrayList<>(), StandardCharsets.UTF_8);
        String fileDirectory = input.ask("Enter files directory: ");
        //src\\main\\resources\\directory1
        String separator = input.ask("Enter result delimiter: ");
        String numberOfThreads = input.ask("Enter number of threads: ");
        List<String> allLines = Main.listAllFiles(fileDirectory);
        ThreadPool threadPool = new ThreadPool();
        threadPool.process(Integer.parseInt(numberOfThreads), allLines, separator);
    }

    public static List<String> listAllFiles(String path){
        List<String> result = new ArrayList<>();
        try(Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    try {
                        List<String> fileList = Files.readAllLines(filePath);
                        result.addAll(fileList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void synchronization(File file) throws IOException {
        List<String> sortedLines = Files.lines(file.toPath())
                .sorted(Comparator.comparing(line -> Integer.valueOf(line.split("\\| ")[0])))
                .map(line -> line.split("\\| ")[1])
                .collect(toList());
        String absolutePath = file.getAbsolutePath();
        Files.write(Paths.get(absolutePath), sortedLines, StandardCharsets.UTF_8);
    }
}
