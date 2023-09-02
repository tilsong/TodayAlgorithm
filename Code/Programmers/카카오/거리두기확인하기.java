package Programmers.카카오;

public class 거리두기확인하기 {
    public static void main(String[] args) {
        String [][] places = {
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        };

        int[] ans = solution(places);

        for (int a : ans) {
            System.out.print(a + " ");
        }
    }

    static int[] dRow = {-1, 0, 1, 0};
    static int[] dCol = {0, 1, 0, -1};
    static String[][] curStr;
    static int isOk;
    static int curR;
    static int curC;

    public static int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for (int k = 0; k < places.length; k++) {
            String[] strs = places[k];

            curStr = new String[5][5];
            for (int i = 0; i < strs.length; i++) {
                curStr[i] = strs[i].split("");
            }

            isOk = 1;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (curStr[i][j].equals("P")) {
                        curR = i;
                        curC = j;
                        search(0, curR, curC);
                    }
                }
            }

            answer[k] = isOk;
        }

        return answer;
    }

    private static void search(int depth, int row, int col) {
        if (depth == 2) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nRow = row + dRow[i];
            int nCol = col + dCol[i];

            if (nRow >= 0 && nRow < 5 && nCol >= 0 && nCol < 5) {
                if (curStr[nRow][nCol].equals("P")) {
                    if (nRow == curR && nCol == curC) {
                        continue;
                    }
                    isOk = 0;
                    return;
                } else if (curStr[nRow][nCol].equals("X")){
                    continue;
                }
                search(depth + 1, nRow, nCol);
            }
        }
    }
}
