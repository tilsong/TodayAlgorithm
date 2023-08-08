package Boj.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//    - 수많은 fail 끝에 어거지 Success ㅋ
//    - 첫 실패 요인
//        - 동서남북으로 주사위를 굴릴 때, 동서남북에 대한 기준이 변경되지 않을 것이라 생각했었음. 그러나 변경됨.
//            ⇒ 굴렸을 때 변경되는 지점들을 일일히 확인해서 변경해주어야만 했음.
//        - 주사위의 윗면이 동서남북으로 굴릴 때 어떻게 지정되는지를 확인해야 했음.
//            ⇒ 남, 북 방향으로 굴릴 때 윗면 헷갈려서 이것만 2시간 봄 ㄹㅇ..
//    - 좋은 문제였음. 특정 방향으로 굴리고, 주사위를 사용하는 방법을 알 수 있었다.

public class 주사위굴리기 {
    static int[] dRow = {0, 0, 0, -1, 1};
    static int[] dCol = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int curCol = Integer.parseInt(st.nextToken());
        int curRow = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dice = new int[6];

        st = new StringTokenizer(br.readLine());
        while (k-- > 0) {
            int d = Integer.parseInt(st.nextToken());

            int nRow = dRow[d] + curRow;
            int nCol = dCol[d] + curCol;

            if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m) {
                curRow = nRow;
                curCol = nCol;

                int temp = dice[2];

                switch (d) {
                    case 1:
                        dice[2] = dice[3];
                        dice[3] = dice[5];
                        dice[5] = dice[1];
                        dice[1] = temp;
                        break;
                    case 2:
                        dice[2] = dice[1];
                        dice[1] = dice[5];
                        dice[5] = dice[3];
                        dice[3] = temp;
                        break;
                    case 3:
                        dice[2] = dice[4];
                        dice[4] = dice[5];
                        dice[5] = dice[0];
                        dice[0] = temp;
                        break;
                    case 4:
                        dice[2] = dice[0];
                        dice[0] = dice[5];
                        dice[5] = dice[4];
                        dice[4] = temp;
                        break;
                }

                if (map[curRow][curCol] == 0) {
                    map[curRow][curCol] = dice[5];
                } else {
                    dice[5] = map[curRow][curCol];
                    map[curRow][curCol] = 0;
                }

                System.out.println(dice[2]);
            }
        }

    }
}
