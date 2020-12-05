package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

import static java.lang.System.lineSeparator;

public class ThreadPool extends Thread{

    public void process(int numberOfThreads, List<String> lines, String separator) throws IOException {
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
        long startTime = System.nanoTime();
        int threadNumber = 0;
        for (String line : lines) {
            service.execute(new ThreadParsing(line, separator, ++threadNumber));
        }
        service.shutdown();
        try {
            service.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Execution time: " + elapsedTime/1000000 + " ms");
        Main.synchronizeRows(new File("result.txt"));
    }

    static class ThreadParsing implements Runnable
    {
        String line;
        String separator;
        int lineNumber;

        ThreadParsing(String line, String separator, int lineNumber) {
            this.line = line;
            this.separator = separator;
            this.lineNumber = lineNumber;
        }

        Formatter formatter = new Formatter();

        @Override
        public void run()
        {
            try
            {
                FileWriter fstream = new FileWriter("result.txt", true);
                BufferedWriter out = new BufferedWriter(fstream);
                out.write(lineNumber + "| " + formatter.format(Parser.parseLine(line), separator) + lineSeparator());
                out.close();
            }
            catch (Exception e)
            {
                System.out.println ("Exception: " + e.getLocalizedMessage());
            }
        }
    }
}
