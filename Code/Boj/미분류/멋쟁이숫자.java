package Boj.미분류;

public class 멋쟁이숫자 {
    public static void main(String[] args) {
        String input = "111999333";
        System.out.println(solution(input));
    }

    private static int solution(String s) {
        int max = -1;

        for (int i = 0; i < s.length()-2; i++) {
            if (s.charAt(i) == s.charAt(i+1) && s.charAt(i+1) == s.charAt(i+2)) {
                max = Math.max(max, Integer.parseInt(s.substring(i, i+3)));
            }
        }

        return max;
    }
}
