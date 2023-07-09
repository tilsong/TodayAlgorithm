package Boj.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 흙길보수하기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        List<Pool> pools = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pools.add(new Pool(start, end));
        }

        Collections.sort(pools);

        int count = 0;
        int cur = 0;

        for (int i = 0; i < n; i++) {
            if (cur < pools.get(i).start) {
                cur = pools.get(i).start;
            }
            if (cur >= pools.get(i).end) {
                continue;
            }

            int len = pools.get(i).end - cur;
            if (len % l == 0) {
                count += (len / l); // 필요한 널빤지 개수
                cur += len;
            } else {
                count += (len / l) + 1;
                cur += (len / l) * l + l;
            }
        }

        System.out.println(count);

    }

    private static class Pool implements Comparable<Pool> {
        int start;
        int end;

        @Override
        public int compareTo(Pool o) {
            return this.start - o.start;
        }

        public Pool(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
