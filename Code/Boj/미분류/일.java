package Boj.미분류;

import java.util.Arrays;
import java.util.List;

public class 일 {
    public static void main(String[] args) {
        System.out.println(solution("313253123", 3));
    }

    public static int solution(String s, int N) {
        int answer = -1;

        String k = "";
        for (int i = 1; i <= N; i++) {
            k += (i + "");
        }

        for (int i = 0; i < s.length() - N; i++) {
            String substring = s.substring(i, i + N);
            String[] split = substring.split("");
            Arrays.sort(split);
            String join = String.join(" ", split);

            if (k.equals(join)) {
                int num = Integer.parseInt(substring);
                answer = Math.max(answer, num);
            }
        }

        return answer;
    }
}
