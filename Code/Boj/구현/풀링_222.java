package Boj.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 풀링_222 {
    static int n = 0;
    static int[][] curMatrix;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        curMatrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                curMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] tempMatrix = new int[0][0];
        while (n != 1) {
            n /= 2;
            tempMatrix = new int[n][n];

            for (int i = 0; i < n*2; i+=2) {
                for (int j = 0; j < n*2; j+=2) {
                    tempMatrix[i/2][j/2] = search(i, j);
                }
            }

            curMatrix = tempMatrix.clone();
        }

        System.out.println(tempMatrix[0][0]);
    }

    private static int search(int i, int j) {
        List<Integer> list = new ArrayList<>();

        list.add(curMatrix[i][j]);
        list.add(curMatrix[i+1][j]);
        list.add(curMatrix[i][j+1]);
        list.add(curMatrix[i+1][j+1]);

        Collections.sort(list);

        return list.get(2);
    }
}
