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
        expected.add("2+2+1+10");
        expected.add("2+2+1+9");
        expected.add("2+2+0+9");
        expected.add("2+2+12");
        expected.add("2+2+10");
        StringInput input = mock(StringInput.class);
        when(input.ask("Enter files directory: ")).thenReturn("src\\test\\resources");
        when(input.ask("Enter result delimiter: ")).thenReturn("+");
        when(input.ask("Enter number of threads: ")).thenReturn(String.valueOf(1));
        Task(input);
        verify(input).ask("Enter files directory: ");
        verify(input).ask("Enter result delimiter: ");
        verify(input).ask("Enter number of threads: ");
        File file = new File("result.txt");
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while(scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }
        assertTrue(lines.containsAll(expected));
    }

}