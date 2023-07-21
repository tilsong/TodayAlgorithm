package Boj.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// (S)
//  참고: https://dingdingmin-back-end-developer.tistory.com/entry/백준-17144-자바-java-미세먼지-안녕
//- 수정 사항
//        - 공기청정기에서 바람이 나가는 부분은 0이도록 하는 로직을 추가했다.
//        - 먼지를 회전시킬 때 로직을 최적화할 수 있도록 했다. 방법은 다음과 같다.
//          - 먼지 회전은 상하좌우가 연이어서 일어나게 되는데, 기존의 방식은 매 줄마다 특정 값을 저장해서 다음의 어떤 스텝에서 적절한 곳에 넣어주어야만 했다. 이 때문에 시간과 노력이 많이 들었다.
//          - 여기서는 단 하나의 요소만 저장해도 되도록 로직을 작성했다. 이 경우, 기존과 반대로 회전을 시키게 된다.
//        - 회전 시 “배열돌리기1” 로직을 사용해도 좋을 것 같다.

public class 미세먼지안녕 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[][] room = new int[row][col];
        int[] airCleanRow = new int[2];
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                int state = Integer.parseInt(st.nextToken());
                if (state == -1 && airCleanRow[0] == 0) {
                    airCleanRow[0] = i;
                    airCleanRow[1] = i + 1;
                }
                room[i][j] = state;
            }
        }

        int[] dRow = {-1, 0, 1, 0};
        int[] dCol = {0, 1, 0, -1};

        for (int i = 0; i < t; i++) {
            int[][] tempRoom = new int[row][col];

            for (int j = 0; j < row; j++) {
                for (int k = 0; k < col; k++) {
                    if (room[j][k] == -1 || room[j][k]/5 < 1) {
                        continue;
                    }
                    for (int l = 0; l < 4; l++) {
                        int nRow = j + dRow[l];
                        int nCol = k + dCol[l];

                        if (nRow >= 0 && nRow < row && nCol >= 0 && nCol < col && room[nRow][nCol] != -1) {
                            tempRoom[nRow][nCol] += (room[j][k] / 5);
                            tempRoom[j][k] -= room[j][k] / 5;
                        }
                    }
                }
            }

            for (int j = 0; j < row; j++) {
                for (int k = 0; k < col; k++) {
                    room[j][k] += tempRoom[j][k];
                }
            }

            // 공기 청정기 로직
            // 위
            int top = airCleanRow[0];
            // 좌
            for (int j = top-1; j > 0; j--) {
                room[j][0] = room[j-1][0];
            }
            // 상
            for (int j = 0; j < col-1; j++) {
                room[0][j] = room[0][j+1];
            }
            // 우
            for (int j = 0; j < top; j++) {
                room[j][col-1] = room[j+1][col-1];
            }
            // 하
            for (int j = col-1; j > 1; j--) {
                room[top][j] = room[top][j-1];
            }
            room[top][1] = 0;
            
            // 아래
            int down = airCleanRow[1];
            // 좌
            for (int j = down+1; j < row -1; j++) {
                room[j][0] = room[j+1][0];
            }
            // 하
            for (int j = 0; j < col-1; j++) {
                room[row-1][j] = room[row-1][j+1];
            }
            // 우
            for (int j = row-1; j > down; j--) {
                room[j][col-1] = room[j-1][col-1];
            }
            // 상
            for (int j = col-1; j > 1; j--) {
                room[down][j] = room[down][j-1];
            }
            room[down][1] = 0;
        }

        int sum = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (room[i][j] == -1) {
                    continue;
                }
                sum += room[i][j];
            }
        }

        System.out.println(sum);
    }

// 1차 풀이, 오답 - 바람 및 정화 로직에서 잘못됨..
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int row = Integer.parseInt(st.nextToken());
//        int col = Integer.parseInt(st.nextToken());
//        int t = Integer.parseInt(st.nextToken());
//
//        int[][] room = new int[row][col];
//        int[] airCleanRow = new int[2];
//        for (int i = 0; i < row; i++) {
//            st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < col; j++) {
//                int state = Integer.parseInt(st.nextToken());
//                if (state == -1 && airCleanRow[0] == 0) {
//                    airCleanRow[0] = i;
//                    airCleanRow[1] = i + 1;
//                }
//                room[i][j] = state;
//            }
//        }
//
//        int[] dRow = {-1, 0, 1, 0};
//        int[] dCol = {0, 1, 0, -1};
//
//        for (int i = 0; i < t; i++) {
//            int[][] tempRoom = new int[row][col];
//
//            for (int j = 0; j < row; j++) {
//                for (int k = 0; k < col; k++) {
//                    if (room[j][k] == -1 || room[j][k]/5 < 1) {
//                        continue;
//                    }
//                    for (int l = 0; l < 4; l++) {
//                        int nRow = j + dRow[l];
//                        int nCol = k + dCol[l];
//
//                        if (nRow >= 0 && nRow < row && nCol >= 0 && nCol < col && room[nRow][nCol] != -1) {
//                            tempRoom[nRow][nCol] += (room[j][k] / 5);
//                            tempRoom[j][k] -= room[j][k] / 5;
//                        }
//                    }
//                }
//            }
//
//            for (int j = 0; j < row; j++) {
//                for (int k = 0; k < col; k++) {
//                    room[j][k] += tempRoom[j][k];
//                }
//            }
//
//            // 바람 및 정화
//            // 1. 위 바람
//            // 1.1 맨 밑행
//            room[airCleanRow[0]][0] = 0;
//            room[airCleanRow[1]][0] = 0;
//            int tempOne = room[airCleanRow[0]][col - 1];
//            for (int j = col-1; j > 0; j--) {
//                room[airCleanRow[0]][j] = room[airCleanRow[0]][j-1];
//            }
//            // 1.2 우측 열
//            int tempTwo = room[0][col-1];
//            for (int j = airCleanRow[0]-1; j > 0; j--) {
//                room[j-1][col-1] = room[j][col-1];
//            }
//            room[airCleanRow[0]-1][col-1] = tempOne;
//            // 1.3 맨 위 행
//            int tempThree = room[0][0];
//            for (int j = 0; j < col-2; j++) {
//                room[0][j] = room[0][j+1];
//            }
//            room[0][col-2] = tempTwo;
//            // 1.4 왼쪽 열
//            for (int j = airCleanRow[0]; j > 1; j--) {
//                room[j][0] = room[j-1][0];
//            }
//            room[1][0] = tempThree;
//            room[airCleanRow[0]][0] = -1;
//
//            // 2. 아래 바람
//            // 2.1 맨 위행
//            tempOne = room[airCleanRow[1]][col-1];
//            for (int j = col-1; j > 0; j--) {
//                room[airCleanRow[1]][j] = room[airCleanRow[1]][j-1];
//            }
//            // 2.2 우측 열
//            tempTwo = room[row-1][col-1];
//            for (int j = row-1; j > airCleanRow[1]+1; j--) {
//                room[j][col-1] = room[j-1][col-1];
//            }
//            room[airCleanRow[1]+1][col-1] = tempOne;
//            // 2.3 맨 아래행
//            tempThree = room[row-1][0];
//            for (int j = 0; j < col - 2; j++) {
//                room[row-1][j] = room[row-1][j+1];
//            }
//            room[row-1][col-2] = tempTwo;
//            // 2.4 왼쪽 행
//            for (int j = airCleanRow[1]; j < row - 2; j++) {
//                room[j][0] = room[j+1][0];
//            }
//            room[row-2][0] = tempThree;
//            room[airCleanRow[1]][0] = -1;
//        }
//
//        int sum = 0;
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < col; j++) {
//                if (room[i][j] == -1) {
//                    continue;
//                }
//                sum += room[i][j];
//            }
//        }
//
//        System.out.println(sum);
//    }
}
