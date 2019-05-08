package com.javarush.task.task31.task3111;



import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;


public class SearchFileVisitor extends SimpleFileVisitor<Path> {

    private String partOfName = null;
    private String partOfContent = null;
    private int minSize = -1;
    private int maxSize = -1;
    private boolean one;
    private boolean two;
    private boolean three;
    private boolean four;
    private boolean five;

    private List<Path> foundFiles = new ArrayList<>();


    public void setPartOfName(String partOfName) {
        three = true;
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        four = true;
        this.partOfContent = partOfContent;
    }


    public void setMinSize(int minSize) {
        one = true;
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        two = true;
        this.maxSize = maxSize;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if(!Files.isDirectory(file)) {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length
        boolean a = minS(content);//min
        boolean b = maxS(content);//max
        boolean c = nameS(file);//name
        boolean d = contentS(file);//content
        boolean e = minMaxS(content);//min & max
        //Если мин сайз и макс должны быть тру
        if (one  && two ){
            five = true;
        }
        // общая проверка
        if (a == one && b == two && c== three && d == four && e == five){
            foundFiles.add(file);
        }

        }
        return super.visitFile(file, attrs);
    }

    public List<Path> getFoundFiles() {

        return foundFiles;
    }

    public boolean minS(byte[] content){

        if(minSize != -1 &&content.length > minSize) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean maxS(byte[] content){

        if(maxSize != -1 && content.length < maxSize) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean minMaxS(byte[] content){

        if(minSize != -1 && content.length > minSize && content.length < maxSize) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean nameS(Path file){

        if(partOfName!=null && file.getFileName().toString().contains(partOfName)) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean contentS(Path file){

        if (partOfContent!= null) {
            try {
                List<String> list = Files.readAllLines(file);
                for (String fileCheck : list) {
                    if (fileCheck.contains(partOfContent)) {
                        return true;
                    }
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

}
