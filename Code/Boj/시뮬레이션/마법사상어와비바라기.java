package Boj.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//- 100m (Success)
//
//        - 풀이
//        - 백트래킹 문제인만큼, 문제에서 주어진 조건대로 코드를 구현하는 것이 관건이었다.
//        - 계속 문제가 생기고 디버깅 해야 했던 가장 큰 이유는 Row와 Col에 대한 정의였다.
//
//        ```java
//          static int[] dCol = {-1, -1, 0, 1, 1, 1, 0, -1};
//          static int[] dRow = {0, -1, -1, -1, 0, 1, 1, 1};
//        ```
//
//        결국 이런 식으로 이동할 row와 col에 대해 정의해 놓게 되는데, row와 col 사이에서도 헷갈리고, row가 언제 +이고 -인지에 대해서도 헷갈릴 때가 많았다.
//
//        이런 부분이 정리가 되는 것이 중요하다고 생각된다.
//
//        - Row | 위 ⇒ - , 아래 ⇒ +
//        - Col   | 왼 ⇒ - , 오 ⇒ +
//        - 그러나 전체적으로 코드가 매우 길었음. 거의 100줄
//
//- **개선된 풀이**
//        - 참고 https://namhandong.tistory.com/220
//        1. **간결한 수식 사용하기**
//          코드의 의미는 거의 같지만, 아래의 코드에서
//      ```
//        cloud[0] += (n + dRow[move[i][0]] * move[i][1] % n) % n;
//       ```
//        이 부분을 눈 여겨볼 필요가 있다. 음수이든 양수이든 n을 더해주고, %n을 통해 식을 훨씬 간결하게 가져간 것을 알 수 있다.
//        2.방문 배열 사용하기
//        그냥 생각하기에는 메모리도 사용하고 굳이 필요 없는 변수니까 선언을 해주지 않는 것이 더 낫다고 생각해서 안 썼었는데, 이렇게 보니까 기존보다 훨씬 코드도 깔끔하고, 훨씬 나은 시간 복잡도를 사용하게 된다.
//        게다가 n은 최대 50이므로 그렇게 많은 공간을 사용하지도 않는다.(최대 2500)
//
//        방문 배열을 잘 활용해야겠다!

public class 마법사상어와비바라기 {
    public static void upgradeMain(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] basket = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                basket[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<int[]> clouds = new ArrayList<>();
        clouds.add(new int[]{n-1,0});
        clouds.add(new int[]{n-1,1});
        clouds.add(new int[]{n-2,0});
        clouds.add(new int[]{n-2,1});

        int[][] move = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            move[i][0] = Integer.parseInt(st.nextToken()) - 1; // 실제 배열에 맞추기.
            move[i][1] = Integer.parseInt(st.nextToken()) % n; // n번 이동 시 제자리이므로.
        }

        for (int i = 0; i < m; i++) {
            boolean[][] visited = new boolean[n][n];

            // 1. 모든 구름 이동! + 2. 비내리기
            for (int j = 0; j < clouds.size(); j++) {
                int[] cloud = clouds.get(j);
                cloud[0] += (n + dRow[move[i][0]] * move[i][1] % n) % n;
                cloud[1] += (n + dCol[move[i][0]] * move[i][1] % n) % n;
                basket[cloud[0]][cloud[1]] ++;
                visited[cloud[0]][cloud[1]] = true;
            }

            // 3. 구름 있는 칸의 대각선에 물이 있으면 그만큼 물양 증가. 경계 넘어가면 계산 x
            for (int j = 0; j < clouds.size(); j++) {
                int[] cloud = clouds.get(j);

                int[] nRow = {-1, -1, 1, 1};
                int[] nCol = {-1, 1, 1, -1};

                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int row = cloud[0] + nRow[k];
                    int col = cloud[1] + nCol[k];

                    if (row >= 0 && row < n && col >= 0 && col < n) {
                        if (basket[row][col] > 0) {
                            count ++;
                        }
                    }
                }
                basket[cloud[0]][cloud[1]] += count;
            }
            clouds.clear();

            // 4. 바구니에서 물의 양이 2 이상인 모든 칸에 구름 생기고, 물의 양 2 줄어듦. 기존에 구름 있던 곳에는 구름 x
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (basket[j][k] >= 2 && !visited[j][k]) {
                        basket[j][k] -= 2;
                        clouds.add(new int[]{j, k});
                    }
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += basket[i][j];
            }
        }

        System.out.println(sum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] basket = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                basket[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<int[]> clouds = new ArrayList<>();
        clouds.add(new int[]{n-1,0});
        clouds.add(new int[]{n-1,1});
        clouds.add(new int[]{n-2,0});
        clouds.add(new int[]{n-2,1});

        int[][] move = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            move[i][0] = Integer.parseInt(st.nextToken()) - 1; // 실제 배열에 맞추기.
            move[i][1] = Integer.parseInt(st.nextToken()) % n; // n번 이동 시 제자리이므로.
        }

        for (int i = 0; i < m; i++) {
            // 1. 모든 구름 이동!
            for (int j = 0; j < clouds.size(); j++) {
                int[] cloud = clouds.get(j);
                cloud[0] += (dRow[move[i][0]] * move[i][1]);
                cloud[1] += (dCol[move[i][0]] * move[i][1]);

                if (cloud[0] < 0) {
                    cloud[0] += n;
                } else if (cloud[0] >= n) {
                    cloud[0] -= n;
                }

                if (cloud[1] < 0) {
                    cloud[1] += n;
                } else if (cloud[1] >= n) {
                    cloud[1] -= n;
                }
            }

            // 2. 구름 있는 칸의 물의 양 + 1
            for (int j = 0; j < clouds.size(); j++) {
                int[] cloud = clouds.get(j);
                basket[cloud[0]][cloud[1]] ++;
            }

            // 3. 구름 있는 칸의 대각선에 물이 있으면 그만큼 물양 증가. 경계 넘어가면 계산 x
            int[] sums = new int[clouds.size()];
            for (int j = 0; j < clouds.size(); j++) {
                int[] cloud = clouds.get(j);

                int[] nRow = {-1, -1, 1, 1};
                int[] nCol = {-1, 1, 1, -1};

                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int row = cloud[0] + nRow[k];
                    int col = cloud[1] + nCol[k];

                    if (row >= 0 && row < n && col >= 0 && col < n) {
                        if (basket[row][col] > 0) {
                            count ++;
                        }
                    }
                }
                sums[j] = count;
            }
            for (int j = 0; j < clouds.size(); j++) {
                int[] cloud = clouds.get(j);
                basket[cloud[0]][cloud[1]] += sums[j];
            }

            // 4. 바구니에서 물의 양이 2 이상인 모든 칸에 구름 생기고, 물의 양 2 줄어듦. 기존에 구름 있던 곳에는 구름 x
            List<int[]> newClouds = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (basket[j][k] >= 2) {
                        boolean canCreate = true;
                        for (int l = 0; l < clouds.size(); l++) {
                            int[] cloud = clouds.get(l);
                            if (cloud[0] == j && cloud[1] == k) {
                                canCreate = false;
                                break;
                            }
                        }
                        if (canCreate) {
                            newClouds.add(new int[]{j, k});
                            basket[j][k] -= 2;
                        }
                    }
                }
            }
            clouds = newClouds;
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += basket[i][j];
            }
        }


        System.out.println(sum);
    }

    static int[] dCol = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dRow = {0, -1, -1, -1, 0, 1, 1, 1};
}
