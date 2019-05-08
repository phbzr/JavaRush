package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {

    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        for (int i =0; i<students.size();i++){
            if (students.get(i).getAverageGrade() == averageGrade){
                return students.get(i);
            }
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        Student mainStudent = null;
        double maxGrade = 0;
        for (int i =0; i<students.size();i++){
            if (students.get(i).getAverageGrade()> maxGrade){
                maxGrade = students.get(i).getAverageGrade();
                mainStudent = students.get(i);
            }
        }
        return mainStudent;
    }

    public Student getStudentWithMinAverageGrade() {
        //TODO:
        Student mainStudent = null;
        double minGrade = students.get(0).getAverageGrade();
        for (int i =0; i<students.size();i++){
            if (students.get(i).getAverageGrade()< minGrade){
                minGrade = students.get(i).getAverageGrade();
                mainStudent = students.get(i);
            }
        }
        expel(mainStudent);
        return mainStudent;
    }
    public void expel(Student student){
        students.remove(student);
    }
}