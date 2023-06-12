package Boj.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 프린트큐 {
    
    // 문제의 핵심
    // 1. 문제가 의도하는 그대로 작성하는 구현 문제
    // 2. 큐 + 시뮬레이션을 통해 문제를 해결. (Queue 인터페이스 대신 LinkedList 사용)
    // 3. 중간 중간의 디테일한 조건을 챙기기
    // 4. 클래스 대신 배열을 사용할 수 있다. -> 코드가 훨씬 짧고 깔끔하다.(코테에서는 필요)
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            LinkedList<int[]> queue = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int imp = Integer.parseInt(st.nextToken());
                queue.offer(new int[]{imp, i});
            }

            int count = 0;
            while(!queue.isEmpty()) {
                int[] front = queue.poll();
                boolean isMax = true;

                for (int i = 0; i < queue.size(); i++) {
                    if (front[0] < queue.get(i)[0]) {
                        queue.add(front);
                        for (int j = 0; j < i; j++) {
                            queue.add(queue.poll());
                        }

                        isMax = false;
                        break;
                    }
                }

                if (!isMax) {
                    continue;
                }

                count++;

                if (front[1] == m) {
                    System.out.println(count);
                }
            }
        }
    }
}