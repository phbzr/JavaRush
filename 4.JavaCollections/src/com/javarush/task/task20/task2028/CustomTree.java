package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {

    Entry<String> root;
    private int size;
    List<Entry> list;

    public CustomTree() {
    }

    public CustomTree(Entry<String> root) {
        this.root = new Entry<>("default");
    }

    @Override
    public String get(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        list = new ArrayList<>();
        if(root.isAvailableToAddChildren()) {
            if (root.availableToAddLeftChildren) {
                root.leftChild.elementName = element;

            } else {
                root.rightChild.elementName = element;
            }
        } else {
            root.parent.elementName = element;
        }
        size++;
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }
    @Override
    public int size() {
        return this.size;
    }

    public String getParent(String s){

        return null;
    }

    static class Entry<T> implements Serializable{
        String elementName;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public Entry(String param) {
            this.elementName = param;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren(){
            if (!availableToAddRightChildren && !availableToAddLeftChildren){
                return false;
            }else {
                return true;
            }
        }
    }
}
