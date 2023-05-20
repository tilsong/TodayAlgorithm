package Boj.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N과M_11 {
    static int n;
    static int m;
    static int[] nums;
    static int[] arr;
    static Set<String> set = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];
        st = new StringTokenizer(br.readLine());
        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        search(0);

        set.forEach(s -> System.out.println(s));
    }

    private static void search(int depth) {
        if (depth == m) {
            String result = "";
            for (int i = 0; i < m; i++) {
                result += (arr[i]+ " ");
            }
            set.add(result);
            return;
        }

        for (int i = 0; i < n; i++) {
                arr[depth] = nums[i];
                search(depth+1);
        }
    }
}
