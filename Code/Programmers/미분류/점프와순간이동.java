package Programmers.미분류;

import java.util.*;

public class 점프와순간이동 {
    public int solution(int n) {
        int answer = 0;

        while (n != 1) {
            if (n%2 != 0) {
                answer ++;
            }
            n /= 2;
        }

        return answer + 1;
    }
}
