package Boj.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 로또 {
    static int[] arr;
    static boolean[] visited;
    static int[] nums;
    static Set<String> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());

        while(k != 0) {
            set = new LinkedHashSet<>();
            nums = new int[6];
            arr = new int[k];
            visited = new boolean[k];
            for (int i = 0; i < k; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            search(0,-1);

            set.forEach(e -> System.out.println(e));

            System.out.println();

            st = new StringTokenizer(br.readLine());

            k = Integer.parseInt(st.nextToken());
        }
    }

    public static void search (int depth, int before) {
        if (depth == 6) {
            String word = "";
            for (int i = 0; i < 6; i++) {
                word += nums[i] + " ";
            }
            set.add(word);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                nums[depth] = arr[i];
                search(depth+1, i);
                visited[i] = false;
            }
        }
    }
}
