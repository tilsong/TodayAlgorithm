package Boj.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N과M_9 {
    static int n;
    static int m;
    static int[] array;
    static int[] numbers;
    static boolean[] visited;
    static LinkedHashSet<String> combi = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        visited = new boolean[n];

        Arrays.sort(numbers);
        array = new int[m];

        search(0);

        combi.forEach(System.out::println);
    }

    public static void search(int depth) {
        if (depth == m) {
            String temp = "";
            for (int i = 0; i < m; i++) {
                temp += array[i] + " ";
            }
            combi.add(temp);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                array[depth] = numbers[i];
                search(depth + 1);
                visited[i] = false;
            }
        }
    }
}
