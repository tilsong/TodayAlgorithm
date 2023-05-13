package Boj.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

// 요소 중복 x -> visited
// 조합(비내림차) -> before
// 중복 제거 ㅇ -> set
public class N과M_10 {
    static int n;
    static int m;
    static int[] numbers;
    static int[] array;
    static boolean[] visited;
    static LinkedHashSet<String> set = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        numbers = new int[n];
        visited = new boolean[n];
        array = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);

        search(0, -1);

        set.forEach(System.out::println);
    }

    private static void search(int depth, int before) {
        if (depth == m) {
            String temp = "";
            for (int i = 0; i < m; i++) {
                temp += (array[i] + " ");
            }

            set.add(temp);

            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i] && before <= numbers[i]) {
                visited[i] = true;
                array[depth] = numbers[i];
                search(depth+1, array[depth]);
                visited[i] = false;
            }
        }
    }
}
