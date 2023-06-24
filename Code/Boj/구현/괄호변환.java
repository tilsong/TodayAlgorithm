package Boj.구현;

import java.util.Queue;
import java.util.Stack;

public class 괄호변환 {
    public static void main(String[] args) {
        String str = "()))((()";
        System.out.println(solution(str));
    }

    public static String solution(String p) {
        if (p.trim().equals("")) {
            return "";
        }


        String u = "";
        String v = "";

        // 균형 잡힌 괄호 문자열 구하기
        int left = 0;
        int right = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                left++;
            }
            if (p.charAt(i) == ')') {
                right++;
            }

            if (i%2 == 0 && left == right) {
                u = p.substring(i);
                v = p.substring(i, p.length()-1);
                break;
            }
        }

        // u가 올바른 괄호 문자열인지 확인하기
        String temp = u;
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) == '(') {
                st.push('(');
            } else {
                st.pop();
            }
        }
        if (st.capacity() == 0) {
            // 올바른 문자열

        } else {
            // 아님
//            4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
//            4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
//            4-3. ')'를 다시 붙입니다.
//            4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
//            4-5. 생성된 문자열을 반환합니다.
        }



        String answer = "";







        return answer;
    }
}
//
// 1. 균형잡힌 괄호 문자열인지 확인하는 로직
//  1.1 짝수개의 문자열일 경우, 이 때 (와 )의 개수가 일치하면 균잡임.
//  1.2 u로 이어 붙이기
// 2. u가 올바른 괄호 문자열인지 확인
//  2.1
// 4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
// 4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
// 4-3. ')'를 다시 붙입니다.
// 4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
// 4-5. 생성된 문자열을 반환합니다.