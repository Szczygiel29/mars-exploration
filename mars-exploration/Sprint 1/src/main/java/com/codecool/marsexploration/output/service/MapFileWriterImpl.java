package com.codecool.marsexploration.output.service;

import com.codecool.marsexploration.mapelements.model.Map;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MapFileWriterImpl implements MapFileWriter {
    @Override
    public void writeMapFile(Map map, String file) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){
            bufferedWriter.write(map.toString());
        }catch (IOException e){
            System.out.println("Error " + e.getMessage());
        }
    }
}
