package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution {

    public static int dCount = -1;
    public static int fCount;
    public static int allSize;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Path path = Paths.get(reader.readLine());
        if (Files.isDirectory(path)){
            Files.walkFileTree(path,new MyVisiter());
            System.out.println("Всего папок - " + dCount);
            System.out.println("Всего файлов - " + fCount);
            System.out.println("Общий размер - " + allSize);
        } else {
            System.out.println(path.toAbsolutePath().toString() + " - не папка");
        }
    }
}
class MyVisiter extends SimpleFileVisitor<Path>{
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!Files.isDirectory(file)) {
            Solution.fCount++;
        }
        byte[] content = Files.readAllBytes(file);
        Solution.allSize = Solution.allSize + content.length;

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        if (Files.isDirectory(dir)) {
            Solution.dCount++;
        }


        return super.postVisitDirectory(dir, exc);
    }
}
