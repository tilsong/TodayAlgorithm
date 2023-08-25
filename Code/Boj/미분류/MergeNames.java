package Boj.미분류;

import java.util.*;

public class MergeNames {
    
    public static String[] uniqueNames(String[] names1, String[] names2) {
      HashSet<String> set = new HashSet();
      
      for(int i = 0; i <names1.length; i++) {
        set.add(names1[i]);
      }
      for(int i = 0; i <names2.length; i++) {
        set.add(names2[i]);
      }
      
      return set.toArray(new String[0]);
    }
    
    public static void main(String[] args) {
        String[] names1 = new String[] {"Ava", "Emma", "Olivia"};
        String[] names2 = new String[] {"Olivia", "Sophia", "Emma"};
        System.out.println(String.join(", ", MergeNames.uniqueNames(names1, names2))); // should print Ava, Emma, Olivia, Sophia
    }
}