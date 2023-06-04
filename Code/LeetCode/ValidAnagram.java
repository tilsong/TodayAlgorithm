package LeetCode;

import java.util.Arrays;

public class ValidAnagram {
    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram"));
    }
    public static boolean isAnagram(String s, String t) {
        String[] sp = s.split("");
        Arrays.sort(sp);

        String[] tp = t.split("");
        Arrays.sort(tp);

        return sp.toString().equals(tp.toString());
    }
}