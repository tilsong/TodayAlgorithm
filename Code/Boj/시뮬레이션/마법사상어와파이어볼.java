package Boj.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 마법사상어와파이어볼 {
    static int[] dRow = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dCol = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<Fb> curList = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            curList.add(new Fb(1, r, c, m, s, d));
        }

        for (int a = 0; a < K; a++) {
            // curList에서 fb를 하나씩 꺼내어 위치를 이동시킨다.
            ArrayList<Fb> tempList = new ArrayList<>();
            for (int i = 0; i < curList.size(); i++) {
                Fb fb = curList.get(i);
                int newR = (fb.r + dRow[fb.d] * fb.s + N * fb.s) % N;
                int newC = (fb.c + dCol[fb.d] * fb.s + N * fb.s) % N;
                tempList.add(new Fb(1, newR, newC, fb.m, fb.s, fb.d));
            }
            curList = tempList;

            Fb[][] arr = new Fb[N][N];
            for (int i = 0; i < curList.size(); i++) {
                Fb fb = curList.get(i);
                if (arr[fb.r][fb.c] != null) {
                    Fb before = arr[fb.r][fb.c];
                    before.add(fb);
                } else {
                    arr[fb.r][fb.c] = fb;
                }
            }

            curList.clear();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] != null) {
                        if (arr[i][j].count == 1) {
                            curList.add(arr[i][j]);
                        } else {
                            curList.addAll(arr[i][j].divide());
                        }
                    }
                }
            }
        }

        int count = 0;
        for (int i = 0; i < curList.size(); i++) {
            count += curList.get(i).m;
        }

        System.out.println(count);
    }

    private static class Fb {
        int count, r, c, m, s, d, sameD;

        public Fb(int count, int r, int c, int m, int s, int d) {
            this.count = count;
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;

            addD(d);
        }

        public void add(Fb other) {
            this.count ++;
            this.m += other.m;
            this.s += other.s;
            addD(other.d);
        }

        private void addD(int d) {
            if (d%2==0) {
                sameD ++;
            } else {
                sameD --;
            }
        }

        public ArrayList<Fb> divide() {
            if (count < 2) {
                throw new RuntimeException();
            }

            ArrayList<Fb> list = new ArrayList<>();
            int newM = m / 5;
            if (newM == 0) {
                return list;
            }

            int newS = s / count;
            if (Math.abs(sameD) == count) {
                for (int i = 0; i <= 6; i+=2) {
                    Fb fb = new Fb(1, r, c, newM, newS, i);
                    list.add(fb);
                }
            } else {
                for (int i = 1; i <= 7; i+=2) {
                    Fb fb = new Fb(1, r, c, newM, newS, i);
                    list.add(fb);
                }
            }

            return list;
        }
    }
}