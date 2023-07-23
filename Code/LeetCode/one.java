package LeetCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class one {
    public static void main(String[] args) {
        String[] words = {"a", "and", "an", "bear"};
    }

    public static int longestChain(List<String> words) {
        Collections.sort(words, (a1, a2) -> a1.length()-a2.length());

        HashMap<String, Integer> map = new HashMap<>();

        for(int i = 0; i < words.size(); i++) {
            map.put(words.get(i), 1);
        }

        int max = 1;

        for(int i = 0; i < words.size(); i++) {
            String word = words.get(i);

            for(int j = 0; j < word.length(); j++) {
                String w = word.substring(0,j) + word.substring(j+1);

                if (map.containsKey(w)) {
                    map.put(word, Math.max(map.get(word), map.get(w)+1));
                }
            }
            max = Math.max(max, map.get(word));
        }

        return max;
    }

    public static String getFinalString(String s) {
        // Write your code here
        Stack<Character> stack = new Stack<>();

        for (Character c : s.toCharArray()) {
            if (c == 'S' && stack.peek() == 'W') {
                stack.pop();

                if (stack.peek() == 'A') {
                    stack.pop();
                } else {
                    stack.push('W');
                    stack.push('S');
                }
            } else {
                stack.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        if (sb.length() > 0) {
            return sb.reverse().toString();
        }
        return "-1";
    }

    public static int maxLength(List<Integer> a, int k) {
        int max = 0;

        int left = 0;
        int sum = 0;
        for (int right = 0; right < a.size(); right++) {
            sum += a.get(right);

            while (sum > k) {
                sum -= a.get(left);
                left ++;
            }

            max = Math.max(max, right - left + 1);
        }

        return max;
    }

    public static long findRange(int num) {
        String s = num + "";

        String[] arr = s.split("");
        String target = null;
        for (int i = 0; i < arr.length; i++) {
            if (target == null && !arr[i].equals("9")) {
                target = arr[i];
                arr[i] = "9";
            }
            if (target != null && arr[i].equals(target)) {
                arr[i] = "9";
            }
        }
        long max = Long.parseLong(String.join("", arr));

        arr = s.split("");
        target = null;
        if (arr[0].equals("1")) {
            // 두번째 부터 0이 아닌 첫 수 찾기 && 첫 수와 같지 않기 && ar
            for (int i = 1; i < arr.length; i++) {
                if (target == null && !arr[i].equals("0") && !arr[i].equals("1")) {
                    target = arr[i];
                    arr[i] = "0";
                }
                if (target != null && arr[i].equals(target)) {
                    arr[i] = "0";
                }
            }
        } else {
            // 첫글자와 같은 글자 모두 1로 변환
            target = arr[0];
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].equals(target)) {
                    arr[i] = "1";
                }
            }
        }
        long min = Long.parseLong(String.join("", arr));

        return max - min;
    }

}
