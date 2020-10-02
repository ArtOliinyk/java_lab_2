package com.company;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.company.Main.InputAndElse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MainTest {

    @Test
    void main() throws IOException {
        StringInput input = mock(StringInput.class);
        when(input.ask("Enter input file name: ")).thenReturn("text.csv");
        when(input.ask("Enter result delimiter: ")).thenReturn(",");
        InputAndElse(input);
        verify(input).ask("Enter input file name: ");
        verify(input).ask("Enter result delimiter: ");
        File file = new File("result.txt");
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while(scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }
        assertEquals("2,2,0,9", lines.get(0));
    }
}