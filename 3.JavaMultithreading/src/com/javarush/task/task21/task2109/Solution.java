package com.javarush.task.task21.task2109;

import java.util.Objects;

/*
Запретить клонирование
*/
public class Solution {
    public static class A implements Cloneable {
        private int i;
        private int j;

        @Override
        protected Object clone() throws CloneNotSupportedException {
            A a =  (A) new A(((A)super.clone()).i, ((A) super.clone()).j);
            return a;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            A a = (A) o;
            return i == a.i &&
                    j == a.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }

        public String getName() {
            return name;
        }
    }

    public static class C extends B  {
        @Override
        protected Object clone() throws CloneNotSupportedException {
            C c = (C)new C(super.getI(),super.getJ(),super.name);
            return c;
        }

        public C(int i, int j, String name) {
            super(i, j, name);
        }
    }

    public static void main(String[] args) {
        A a = new A(12,5);
        A aclone = null;
        try {
            aclone = (A) a.clone();
        } catch (CloneNotSupportedException e){

        }
        System.out.println(a);
        System.out.println(aclone);
        System.out.println(a.i);
        System.out.println(a.j);
        System.out.println(aclone.i);
        System.out.println(aclone.j);
        C c = new C(6,7,"lox");
        C cclone = null;
        try {
            cclone = (C) c.clone();
        } catch (CloneNotSupportedException e){

        }
        System.out.println(c);
        try {
            System.out.println(c.clone());
        } catch (CloneNotSupportedException e) {

        }

    }
}
