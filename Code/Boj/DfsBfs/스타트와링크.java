package Boj.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 스타트와링크 {
    static int n;
    static int[][] arr;
    static boolean[] visited;
    static List<String> list = new ArrayList<>();
    static int subTotal = 0;
    static String[] curArr;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        search(0, "", -1);

        for (int i = 0; i < list.size(); i++) {
            subTotal = 0;
            curArr = list.get(i).trim().split(" ");

            search2(0, "", -1);

            min = Math.min(min, Math.abs(subTotal));
        }

        System.out.println(min);
    }

    static void search2(int depth, String word, int before) {
        if (depth == 2) {
            String[] s = word.trim().split(" ");
            int i = Integer.parseInt(s[0]);
            int j = Integer.parseInt(s[1]);

            subTotal += (arr[i][j] + arr[j][i]);
            return;
        }

        for (int i = 0; i < curArr.length; i++) {
            if(!visited[i] && before < i) {
                visited[i] = true;
                search(depth+1, word + " " + i, i);
                visited[i] = false;

                search2(depth + 1, word + " " + curArr[i], i);
            }
        }
    }

    static void search(int depth, String word, int before) {
        if (depth == n/2) {
            list.add(word);
            return;
        }

        for (int i = 0; i < n; i++) {
            if(!visited[i] && before < i) {
                visited[i] = true;
                search(depth+1, word + " " + i, i);
                visited[i] = false;
            }
        }
    }
}
