package Boj.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 개선된 코드
//    - 중복될 수 있는 부분을 한번에 사용했다.
//    - 아래 코드는 기존에는 2번에 걸쳐 favoriteCount, emptyStand를 카운트했으나, 이를 하나의 반복으로 처리하도록 했다.
//    - Comparable을 구현한 클래스를 통해 좌석 간 비교를 용이하게 했다. 이렇게 할 경우 여러번의 반복을 제거할 수 있다. 또한 비교하는 로직이 간단해진다.
//      비교를 담당하는 로직을 따로 뺀 것이다.
//
//  비교하는 것이 반복된다면, 이를 Comparable을 구현한 클래스를 통해 응집력 있게 처리할 수 있다.

public class 상어초등학교 {
    static int[] dRow = {-1, 0, 1, 0};
    static int[] dCol = {0, 1, 0, -1};

    // 반복 줄이기
    // Compabale 사용하기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int studentCount = n*n;
        int[] seq = new int[studentCount];
        HashMap<Integer, HashSet> favoriteMap = new HashMap<>();
        for (int i = 0; i < studentCount; i++) {
            st = new StringTokenizer(br.readLine());

            seq[i] = Integer.parseInt(st.nextToken());

            HashSet<Integer> favorites = new HashSet<>();
            for (int j = 0; j < 4; j++) {
                favorites.add(Integer.parseInt(st.nextToken()));
            }
            favoriteMap.put(seq[i], favorites);
        }

        int[][] stands = new int[n][n];


        for (int i = 0; i < seq.length; i++) {
            Stand stand = null;

            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    int emptyStand = 0;
                    int favoriteCount = 0;

                    if (stands[row][col] != 0) {
                        continue;
                    }

                    for (int j = 0; j < 4; j++) {
                        int nRow = row + dRow[j];
                        int nCol = col + dCol[j];

                        if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < n) {
                            if (favoriteMap.get(seq[i]).contains(stands[nRow][nCol])) {
                                favoriteCount ++;
                            }
                            if (stands[nRow][nCol] == 0) {
                                emptyStand ++;
                            }
                        }
                    }

                    Stand cur = new Stand(row, col, favoriteCount, emptyStand);

                    if (stand == null) {
                        stand = cur;
                        continue;
                    }

                    if (stand.compareTo(cur) > 0) {
                        stand = cur;
                    }
                }
            }
            stands[stand.row][stand.col] = seq[i];
        }

        int sum = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int count = 0;
                int studentNum = stands[row][col];

                for (int k = 0; k < 4; k++) {
                    int nRow = row + dRow[k];
                    int nCol = col + dCol[k];

                    if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < n) {
                        if (favoriteMap.get(studentNum).contains(stands[nRow][nCol])) {
                            count++;
                        }
                    }
                }
                if (count >= 1) {
                    sum += (int) Math.pow(10, count-1);
                }
            }
        }

        System.out.println(sum);

    }

    private static class Stand implements Comparable<Stand>{
        int row;
        int col;
        int favoriteCount;
        int emptyCount;

        public Stand(int row, int col, int favoriteCount, int emptyCount) {
            this.row = row;
            this.col = col;
            this.favoriteCount = favoriteCount;
            this.emptyCount = emptyCount;
        }

        @Override
        public int compareTo(Stand o) {
            if (favoriteCount != o.favoriteCount) {
                return -(favoriteCount - o.favoriteCount);
            }

            if (emptyCount != o.emptyCount) {
                return -(emptyCount - o.emptyCount);
            }

            if (row != o.row) {
                return row - o.row;
            }

            return col - o.col;
        }
    }
}

//- 100m(Success)
//        - 풀이
//          - 구현으로 아득바득 풀어내었다. 코드가 매우 길다.
//          - 전체 자리는 2차원 배열로 만들어두고, 매 단계마다 필터링을 통해 남은 자리들을 모아 두었다.
//        - 아쉬웠던 점
//          - 구현 자체가 길어지다 보니, 코드 전체를 관리하기도 어려웠고, 작성하는데도 힘이 많이 들었다.

//public class 상어초등학교 {
//    static int[] dRow = {-1, 0, 1, 0};
//    static int[] dCol = {0, 1, 0, -1};
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int n = Integer.parseInt(st.nextToken());
//        int studentCount = n*n;
//        int[] seq = new int[studentCount];
//        List<List<Integer>> favoriteList = new ArrayList<>();
//        for (int i = 0; i <= studentCount; i++) {
//            favoriteList.add(new ArrayList<>());
//        }
//
//        for (int i = 0; i < studentCount; i++) {
//            st = new StringTokenizer(br.readLine());
//
//            seq[i] = Integer.parseInt(st.nextToken());
//
//            List<Integer> student = new ArrayList<>();
//            for (int j = 0; j < 4; j++) {
//                student.add(Integer.parseInt(st.nextToken()));
//            }
//            List<Integer> list = favoriteList.get(seq[i]);
//            list.addAll(student);
//        }
//
//        int[][] stands = new int[n][n];
//
//        for (int i = 0; i < studentCount; i++) {
//            int studentNum = seq[i];
//
//            // 1. 비어 있는 칸 구하기
//            List<int[]> emptyList = new ArrayList<>();
//            for (int row = 0; row < n; row++) {
//                for (int col = 0; col < n; col++) {
//                    if (stands[row][col] == 0) {
//                        emptyList.add(new int[]{row, col});
//                    }
//                }
//            }
//
//            // 1.1 주변에 좋아하는 친구가 많이 인접한 칸 구하기
//            List<int[]> maxFavoriteList = new ArrayList<>();
//
//            int max = 0;
//            for (int j = 0; j < emptyList.size(); j++) {
//                int[] stand = emptyList.get(j);
//
//                int sum = 0;
//                for (int k = 0; k < 4; k++) {
//                    int nRow = dRow[k] + stand[0];
//                    int nCol = dCol[k] + stand[1];
//
//                    if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < n) {
//                        if (favoriteList.get(studentNum).contains(stands[nRow][nCol])) {
//                            sum++;
//                        }
//                    }
//                }
//                if (sum == max) {
//                    maxFavoriteList.add(stand);
//                } else if (sum > max) {
//                    max = sum;
//                    maxFavoriteList.clear();
//                    maxFavoriteList.add(stand);
//                }
//            }
//
//            if (maxFavoriteList.size() == 1) {
//                int[] stand = maxFavoriteList.get(0);
//                stands[stand[0]][stand[1]] = seq[i];
//                continue;
//            }
//
//            // 2. 인접한 칸 중에 비어 있는 칸이 가장 많은 칸으로 자리 정하기
//            List<int[]> maxNearEmpty = new ArrayList<>();
//            max = 0;
//            for (int j = 0; j < maxFavoriteList.size(); j++) {
//                int[] stand = maxFavoriteList.get(j);
//                int sum = 0;
//
//                for (int k = 0; k < 4; k++) {
//                    int nRow = dRow[k] + stand[0];
//                    int nCol = dCol[k] + stand[1];
//
//                    if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < n) {
//                        if (stands[nRow][nCol] == 0) {
//                            sum++;
//                        }
//                    }
//                }
//                if (sum == max) {
//                    maxNearEmpty.add(stand);
//                } else if (sum > max) {
//                    max = sum;
//                    maxNearEmpty.clear();
//                    maxNearEmpty.add(stand);
//                }
//            }
//
//            if (maxNearEmpty.size() == 1) {
//                int[] stand = maxNearEmpty.get(0);
//                stands[stand[0]][stand[1]] = seq[i];
//                continue;
//            }
//
//            // 3. 가장 작은 행(Row)의 자리 구하기
//            List<int[]> minRowList = new ArrayList<>();
//            int min = n;
//            for (int j = 0; j < maxNearEmpty.size(); j++) {
//                int[] stand = maxNearEmpty.get(j);
//
//                if (stand[0] == min) {
//                    minRowList.add(stand);
//                } else if (stand[0] < min) {
//                    min = stand[0];
//                    minRowList.clear();
//                    minRowList.add(stand);
//                }
//            }
//
//            if (minRowList.size() == 1) {
//                int[] stand = minRowList.get(0);
//                stands[stand[0]][stand[1]] = seq[i];
//                continue;
//            }
//
//            // 3.1 가장 작은 열(Col)의 자리 구하기
//            int[] minCol = new int[2];
//            min = n;
//            for (int j = 0; j < minRowList.size(); j++) {
//                int[] stand = minRowList.get(j);
//
//                if (stand[1] < min) {
//                    min = stand[1];
//                    minCol = new int[]{stand[0], stand[1]};
//                }
//            }
//
//            stands[minCol[0]][minCol[1]] = seq[i];
//        }
//
//        int sum = 0;
//        for (int row = 0; row < n; row++) {
//            for (int col = 0; col < n; col++) {
//                int count = 0;
//                int studentNum = stands[row][col];
//
//                for (int k = 0; k < 4; k++) {
//                    int nRow = row + dRow[k];
//                    int nCol = col + dCol[k];
//
//                    if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < n) {
//                        if (favoriteList.get(studentNum).contains(stands[nRow][nCol])) {
//                            count++;
//                        }
//                    }
//                }
//                if (count >= 1) {
//                    sum += (int) Math.pow(10, count-1);
//                }
//            }
//        }
//
//        System.out.println(sum);
//    }
//}