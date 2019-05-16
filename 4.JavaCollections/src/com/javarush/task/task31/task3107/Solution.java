package com.javarush.task.task31.task3107;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
Null Object Pattern
*/
public class Solution {
    private FileData fileData;

    public Solution(String pathToFile) {
        try {
            Path path = Paths.get(pathToFile);
            boolean hd = Files.isHidden(path);
            boolean ex = Files.isExecutable(path);
            boolean dr = Files.isDirectory(path);
            boolean wr = Files.isWritable(path);
            fileData = new ConcreteFileData(hd, ex, dr, wr);
        } catch (Exception e){
            fileData = new NullFileData(e);
        }
    }

    public FileData getFileData() {
        return fileData;
    }
}
