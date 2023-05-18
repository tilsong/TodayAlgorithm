package Boj.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 처음 접근 -> 재귀-start,end 쓰고 방문 배열 쓰는 것 까지.
// 캐치하지 못했던 부분 -> 오른쪽 이동이 끝나고 왼쪽으로 돌아가는 방법, 결과를 출력하는 방법
// + start -> end 범위는 확실한 범위로. 이번에는 text.length()-1 부분을 기억하기
public class ZOAC {
    static String text;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        text = st.nextToken();

        visited = new boolean[text.length()];

        search(0, text.length()-1); // point -> 재귀의 범위를 어떻게 던질 것인가

        System.out.println(sb.toString());
    }

    private static void search(int start, int end) {
        if (start > end) {
            return;
        }
        int idx = start;

        for (int i = start + 1; i <= end; i++) {
            if (text.charAt(i) < text.charAt(idx)) {
                idx = i;
            }
            System.out.println(idx + " i: " + i);
        }

        System.out.println(idx + "res");

        visited[idx] = true;
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) { // point -> 순서 상관 없이 출력할 수 있는 하나의 방법.
                sb.append(text.charAt(i));
            }
        }
        sb.append("\n");


        search(idx+1, end);  // point -> 재귀의 범위를 어떻게 던질 것인가
        search(start, idx-1); // point -> 왼쪽 오른쪽 두방향을 가야하고, 오른쪽을 먼저가는 재귀의 방식
    }
}
