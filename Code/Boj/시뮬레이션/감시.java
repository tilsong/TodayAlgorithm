package Boj.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
// - 골드 4, 감시
//    - 100분 정도 풀다가 포기. 로직이 너무 장황해져서 어떤 문제인지도 알 수 없게 되버렸음..ㅋ
//    - 장황해졌던 이유
//        - 경우의 수가 depth 별로 여러 개가 발생하는 문제였음. 이러한 경우, 각 depth들을 넘어가는 로직들을 단순화해서 풀어나가는 것이 핵심. but 단순화 실패..ㅋㅋ 단순화가 안되면 이러한 문제는 거진 실패하게 되는 듯 싶다.
//    - 풀이
//        - 참고 https://developer-ellen.tistory.com/121
//            - 이 풀이가 좋았다고 느꼈던 이유는 여러 depth들에 접근하는 로직을 단순화해서 풀어나갔기 때문이다. 가령 몇 번 cctv의 경우 어떤 방향으로 돌아야 하는 지를 어떻게 풀어나가야 할지 막막했는데, 이 풀이는 아에 3중 배열로 이를 표현해버렸다. 모든 경우에 적합하지는 않을 수 있지만, 이 풀이에 한해서는 매우 로직을 깔끔하게 표현하는 방법이다.
//
//                ```java
//                static int[][][] types = {
//                        {{0}}, // 경우 x
//                        {{0}, {1}, {2}, {3}}, // 1번 cctv
//                        {{0,2}, {1,3}}, // 2..
//                        {{0,1},{1,2},{2,3},{3,0}},
//                        {{0,1,2},{1,2,3},{2,3,0},{3,0,1}},
//                        {{0,1,2,3}}
//                };
//                ```
//
//            - `search` 에서 각 depth별로 내려갈 때 dfs 로직을 처리해버린 점도 매우 맘에 든다.
//
//                ```java
//                for (int j = 0; j < types[type][i].length; j++) {
//                    int d = types[type][i][j];
//                    int nRow = dRow[d] + row;
//                    int nCol = dCol[d] + col;
//
//                    while (true) {
//                        if (nRow < 0 || nRow >= n || nCol < 0 || nCol >= m) {
//                            break;
//                        }
//                        if (copy_map[nRow][nCol] == 6) {
//                            break;
//                        }
//
//                        copy_map[nRow][nCol] = -1;
//
//                        nRow += dRow[d];
//                        nCol += dCol[d];
//                    }
//                }
//                ```
//
//            - 또한 전역 변수의 사용이 적은 편이다. 이는 각 함수의 매개변수로 배열을 내려주었기 때문이다. 이를 위해서는 각 함수에서 독립적인 배열을 위해 .clone을 활용했다.
//
//                ```java
//                for (int i = 0; i < types[type].length; i++) {
//                            int[][] copy_map = new int[n][m];
//                            for (int j = 0; j < n; j++) {
//                                copy_map[j] = map[j].clone();
//                            }
//                ```

public class 감시 {
    static int n;
    static int m;
    static int[][] map;
    static int min;
    static List<Cctv> cctvs;
    static int[][][] types = {
            {{0}},
            {{0}, {1}, {2}, {3}},
            {{0,2}, {1,3}},
            {{0,1},{1,2},{2,3},{3,0}},
            {{0,1,2},{1,2,3},{2,3,0},{3,0,1}},
            {{0,1,2,3}}
    };
    static int[] dRow = {-1, 0, 1, 0};
    static int[] dCol = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        cctvs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctvs.add(new Cctv(i, j, map[i][j]));
                } else if (map[i][j] == 0) {
                    min ++;
                }
            }
        }
        
        search(0, map);

        System.out.println(min);
    }

    private static void search(int depth, int[][] map) {
        if (depth == cctvs.size()) {
            int count = count(map);
            min = Math.min(min, count);
            return;
        }

        int type = cctvs.get(depth).type;
        int row = cctvs.get(depth).row;
        int col = cctvs.get(depth).col;

        for (int i = 0; i < types[type].length; i++) {
            int[][] copy_map = new int[n][m];
            for (int j = 0; j < n; j++) {
                copy_map[j] = map[j].clone();
            }

            for (int j = 0; j < types[type][i].length; j++) {
                int d = types[type][i][j];
                int nRow = dRow[d] + row;
                int nCol = dCol[d] + col;

                while (true) {
                    if (nRow < 0 || nRow >= n || nCol < 0 || nCol >= m) {
                        break;
                    }
                    if (copy_map[nRow][nCol] == 6) {
                        break;
                    }

                    copy_map[nRow][nCol] = -1;

                    nRow += dRow[d];
                    nCol += dCol[d];
                }
            }
            
            search(depth+1, copy_map);
        }
    }

    private static int count(int[][] map) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    static class Cctv {
        int row;
        int col;
        int type;

        public Cctv(int row, int col, int type) {
            this.row = row;
            this.col = col;
            this.type = type;
        }
    }
}
