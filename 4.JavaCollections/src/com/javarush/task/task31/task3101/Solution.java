package com.javarush.task.task31.task3101;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/*
Проход по дереву файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        File folder = new File(args[0]);

        ///rename;
        File mainFile = new File(args[1]);
        File renamer = new File(mainFile.getParent()+"/allFilesContent.txt");
        if(FileUtils.isExist(mainFile)){
            FileUtils.renameFile(mainFile,renamer);
        }

        //

        List<File> fileList = solution.fileBacker(folder);
        System.out.println(fileList.size());
        try(FileOutputStream outputStream = new FileOutputStream(renamer)) {
            for(int i = 0; i<fileList.size();i++){
                try (FileInputStream inputStream = new FileInputStream(fileList.get(i))){
                    byte[] buffer = new byte[inputStream.available()];
                    if(inputStream.available()>0){
                        inputStream.read(buffer);
                    }
                    outputStream.write(buffer);
                    outputStream.write("\n".getBytes());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static List<File> fileBacker(File direct) throws IOException {




        List<File> fileList = new ArrayList<>();


        for (File file: direct.listFiles())
            if ( file.isDirectory() )
                fileList.addAll(fileBacker(file));
            else if ( file.isFile() ) {
                int size = getSize(file);
                if ( size>0 && size<=50 )
                    fileList.add(file);
            }

        Collections.sort(fileList, new Comparator<File>() {
            @Override
            public int compare(File file, File t1) {
                return file.getName().compareTo(t1.getName());
            }
        });
        return fileList;
    }
    public static int getSize(File file) throws IOException {
        FileInputStream inputFile = new FileInputStream(file);
        int result = inputFile.available();
        inputFile.close();
        return result;
    }
}
