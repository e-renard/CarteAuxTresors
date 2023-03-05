package com.carbon.lacarteauxtresors.commons;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FileManagementTest {
    /*
    * hasfilebeencreated
    * does file min 3 lines (1 map 1 perso 1 tresor)
    * hasfilebeensaved
    * doesfilehavecorrectname
    * isfilelocationcorrect
    * */

    @Test
    public void test() throws IOException {
        assertNotNull(FileManager.readFile());
    }
}
