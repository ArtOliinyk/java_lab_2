package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileReaderTest {

    private FileReader fileReader;

    private static void assertStreamEquals(Stream<?> first, Stream<?> second) {
        Iterator<?> firstIterator = first.iterator(), secondIterator = second.iterator();
        while (firstIterator.hasNext() && secondIterator.hasNext())
            assertEquals(firstIterator.next(), secondIterator.next());
        assert !firstIterator.hasNext() && !secondIterator.hasNext();
    }

    @BeforeEach
    public void setUp() {
        fileReader = new FileReader();
    }

    @Test
    void SuccessedReadFile() throws FileNotFoundException {
        Stream<String> expected = Stream.of("10,AU,,Australia", "11,AU,\"\",Aus\"\"tralia", "\"12\",\"AU\",\" \",\"Australia\"",
                "\"13\",\"AU\",\"Aus \"\" tralia\"", "\"14\",\"AU\",\"Aus,tralia\"");
        Stream<String> actual = fileReader.read("test.csv");
        assertStreamEquals(expected, actual);
    }

    @Test
    void FailedReadFile() {
        assertThrows(FileNotFoundException.class, () -> fileReader.read("Incorrect"), "Missing file \"Incorrect\"");
    }
}