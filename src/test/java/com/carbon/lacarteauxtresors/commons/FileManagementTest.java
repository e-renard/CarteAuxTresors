package com.carbon.lacarteauxtresors.commons;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class FileManagementTest {

    @Test
    public void shouldReadFile() throws IOException {
        assertNotNull(FileManager.readFile());
        assertFalse(FileManager.readFile().isEmpty());
    }

    /**
     * TODO write test to check file creation.
     * @throws IOException
     */
    @Test
    public void shouldWriteFile() throws IOException {
        ///FileManager.writeFile(any(), any());
    }
}
