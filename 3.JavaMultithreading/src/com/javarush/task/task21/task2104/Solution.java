package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* 
Equals and HashCode
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!(o instanceof Solution))
            return false;

        Solution solution = (Solution) o;

        if ((this.first==null)&&(solution.first==null)&&(this.last==null)&&(solution.last==null)) {
            return true;
        };

        if(((this.first == null)&& (solution.first != null)) ||
                ((this.last == null) && (solution.last != null)) ||
                ((this.first != null) &&(solution.first == null)) ||
                ((this.last != null) && (solution.last == null))){
            return false;
        }

        return (first == solution.first || (first != null && first.equals(solution.first))
                && last == solution.last || (last !=null && last.equals(solution.last)));
    }

    public int hashCode() {
        int hash = this.first != null? this.first.hashCode()*31 :0;
        return this.last != null? hash + 31*this.last.hashCode() : hash;

        //return 31 * first.hashCode() + last.hashCode();
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution(null , null));
        System.out.println(s.contains(new Solution(null, "Fds")));


    }
}
