package com.codecool.marsexploration.output.service;

import com.codecool.marsexploration.mapelements.model.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class MapFileWriterImplTest {
    private static final String FILE_PATH = "src/main/resources/test_map.map";
    @AfterEach
    public void cleanup() throws IOException {
        Files.deleteIfExists(Paths.get(FILE_PATH));
    }
    @Test
    public void testWriteMapFile(){
        String[][] representation = {
                {"\uD83C\uDF35", "\uD83D\uDFE8", "\uD83D\uDDFB"},
                {"\uD83D\uDC8E", "\uD83C\uDF0A", "\uD83D\uDDFB"},
                {"\uD83C\uDF0A", "\uD83C\uDF0A", "\uD83D\uDDFB"},
        };

        Map map = new Map(representation);
        MapFileWriter mapFileWriter = new MapFileWriterImpl();

        mapFileWriter.writeMapFile(map, FILE_PATH);

        StringBuilder mapAsString = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH))){
            String line;
            while ((line = bufferedReader.readLine()) != null){
                mapAsString.append(line).append("\n");
            }
        }catch (IOException e){
            System.out.println("Error " + e.getMessage());
        }

        assertEquals("\uD83C\uDF35\uD83D\uDFE8\uD83D\uDDFB\n\uD83D\uDC8E\uD83C\uDF0A\uD83D\uDDFB\n\uD83C\uDF0A\uD83C\uDF0A\uD83D\uDDFB\n"
                , mapAsString.toString());
    }
}