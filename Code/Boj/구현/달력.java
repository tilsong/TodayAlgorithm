package Boj.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 달력 {
    static ArrayList<Line> lines = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        Line[] lineList = new Line[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lineList[i] = new Line(start, end);
        }

        Arrays.sort(lineList, (o1, o2) -> {
           if (o1.start == o2.start) {
               return o2.size - o1.size; // 크기 내림차순
           }
           return o1.start - o2.start; // 시작 오름차순
        });

        int total = 0;

        int bigStart = 0;
        int bigEnd = 0;

        for (int i = 0; i < lineList.length; i++) {
            int start = lineList[i].start;
            int end = lineList[i].end;
            boolean newArrcheck = true; // 새로운 배열 만들어야 하는지

            int newCheck = 0;
            int newTr = 0;
            for (int j = 0; j < lines.size(); j++) {
                if (lines.get(j).end > start) {
                    newCheck++; // line 생성
                } else if (lines.get(j).start < end) {
                    newTr++; // 새 도형 생성
                }
            }
            // line 생성
            if (newCheck == lines.size()) {
                lines.add(new Line(start, end));
                if (bigStart > start | lines.size() == 1) bigStart = start;
                if (bigEnd < end) bigEnd = end;
                continue;
            }
            // 새 도형 생성
            if (newTr == lines.size()) {
                total += (bigEnd - bigStart) * lines.size();

                // 새로운 사각형 만들기
                lines = new ArrayList<>();
                bigStart = start;
                bigEnd = end;
                lines.add(new Line(start, end));
                continue;
            }

            for (int j = 0; j < lines.size(); j++) {
                Line one = lines.get(j);
                if (one.end < start) {
                    lines.set(j, new Line(one.start, end));
                    if (bigEnd < end) bigEnd = end;
                    break;
                }
            }
        }

        System.out.println(total);
    }

    private static class Line {
        int start;
        int end;
        int size;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
            this.size = end - start;
        }
    }
}