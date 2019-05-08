package com.javarush.task.task31.task3112;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("/home/phbzr/Desktop/JAVA/zala"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException, URISyntaxException {
        // implement this method
        URL url = new URL(urlString);
        InputStream is = url.openStream();
        Path tempFile = Files.createTempFile("loadtmp","");
        //OutputStream os = Files.newOutputStream(tempFile);
        /*
        os.write(is.readAllBytes());
        os.close();
        is.close();
        */
        Files.copy(is, tempFile.toAbsolutePath(), StandardCopyOption.REPLACE_EXISTING);
        is.close();
        Path resFile = Files.createFile(Paths.get(downloadDirectory.toString() + "/" + Paths.get(url.getFile()).getFileName()));
        Files.move(tempFile,resFile,REPLACE_EXISTING);
        return resFile;
    }
}
