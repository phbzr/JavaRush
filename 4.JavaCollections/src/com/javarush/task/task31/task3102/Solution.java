package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* 
Находим все файлы
*/
public class Solution {

    public static List<Path> list2 = new ArrayList<>();

    public static List<String> getFileTree(String root) throws IOException {
        List<String>list = new ArrayList<>();
        Path path = Paths.get(root);
        Files.walkFileTree(path,new MyFileVisor());
        if (Files.isDirectory(path)) {
                for (Path path1 : list2) {
                        System.out.println(path1.toString());
                        list.add(path1.toString());
                }

        }

        return list;

    }

    public static void main(String[] args) throws IOException {
        getFileTree("/home/phbzr/Desktop");

    }


}
class MyFileVisor extends SimpleFileVisitor<Path>{
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Solution.list2.add(file);
        return super.visitFile(file, attrs);
    }
}
