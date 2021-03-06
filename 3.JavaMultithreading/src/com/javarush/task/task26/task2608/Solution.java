package com.javarush.task.task26.task2608;

/* 
Мудрый человек думает раз, прежде чем два раза сказать
*/
public class Solution {
    volatile Integer var1;
    volatile Integer var2;
    volatile Integer var3;
    volatile Integer var4;

    public Solution(int var1, int var2, int var3, int var4) {
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
        this.var4 = var4;
    }

    public int getSumOfVar1AndVar2() {
        int a;
        synchronized (var1){
            a= var1+var2;

        }
        return a;
    }

    public int getSumOfVar3AndVar4() {
        int a;
        synchronized (var3){
            a = var3 + var4;
        }
        return a;
    }

    public static void main(String[] args) {

    }
}
