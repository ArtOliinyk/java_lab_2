package com.company;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.company.Main.Task;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MainTest {

    @Test
    void main() throws IOException {
        List<String> expected = new ArrayList<>();
        expected.add("1| 2+2+1+10");
        expected.add("2| 2+2+1+9");
        expected.add("3| 2+2+0+9");
        expected.add("4| 2+2+12");
        expected.add("5| 2+2+10");
        StringInput input = mock(StringInput.class);
        when(input.ask("Enter files directory: ")).thenReturn("C:\\Users\\oliyn\\IdeaProjects\\lab_2\\src\\test\\resources\\test.csv");
        when(input.ask("Enter number of threads: ")).thenReturn(String.valueOf(1));
        when(input.ask("Enter result delimiter: ")).thenReturn("+");
        Task(input);
        verify(input).ask("Enter files directory: ");
        verify(input).ask("Enter number of threads: ");
        verify(input).ask("Enter result delimiter: ");
        File file = new File("result.txt");
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while(scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }
        assertTrue(lines.containsAll(expected));
    }

}