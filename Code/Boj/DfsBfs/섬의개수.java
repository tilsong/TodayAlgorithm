package Boj.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 50m (Success)
// 입력: 입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스의 첫째 줄에는 지도의 너비 w와 높이 h가 주어진다. w와 h는 50보다 작거나 같은 양의 정수이다.
//       둘째 줄부터 h개 줄에는 지도가 주어진다. 1은 땅, 0은 바다이다.
//      입력의 마지막 줄에는 0이 두 개 주어진다.
// 출력: 각 테스트 케이스에 대해서, 섬의 개수를 출력한다.
// 풀이 방향: 현재 위치가 육지이면, 인접한 모든 육지를 바다로 만들고, 섬의 개수를 1 더한다. 이렇게 모든 위치에 대해 반복.
// 시간 복잡도: w와 h 이중 for 문, 8 방향 고려 => O(w * h *8) , 약 50 * 50 * 8 = 2만

public class 섬의개수 {
    static int [][] map;
    static int w;
    static int h;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; // = n

        while(true) {
            int ans = 0;

            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) break;

            map = new int[h][w];

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                   map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == 1) { // 육지이면 ok
                        bfs(j, i);
                        ans ++;
                    }
                }
            }

            System.out.println(ans);
        }
    }

    public static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        map[y][x] = 0;
        q.add(new Node(x, y));

        while(!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 8; i++) {
                int gx = now.x + dx[i];
                int gy = now.y + dy[i];

                if (gx >= 0 && gx < w && gy >= 0 && gy < h) {
                    if (map[gy][gx] == 1) {
                        q.offer(new Node(gx, gy));
                        map[gy][gx] = 0;
                    }
                }
            }
        }
    }

    static int [] dx = {0, 0, -1, 1, -1, 1, -1, 1};
    static int [] dy = {1, -1, 0, 0, 1, 1, -1, -1};

    static class Node {
        public int x;
        public int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


}
