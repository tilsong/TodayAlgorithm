package Programmers.카카오;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 메뉴리뉴얼 {
    public static void main(String[] args) throws IOException {
        String [] ord = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        int[] course = {2,3,5};

        String[] solution = solution(ord, course);
        System.out.println(solution);
    }

    static int combi;
    static HashMap<String, Integer> map;
    static String[] cur;
    static boolean[] visited;
    static String[] arr;

    public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        List<String> list = new ArrayList();

        for (int i = 0; i < course.length; i++) {
            combi = course[i];
            map = new HashMap<>();

            for (int j = 0; j < orders.length; j++) {
                if (combi <= orders[j].length()) {

                    cur = orders[j].split("");
                    visited = new boolean[cur.length];
                    arr = new String[combi];
                    search(0, -1);
                }
            }
            String[] keys = getKeys(map);
            for (int j = 0; j < keys.length; j++) {
                list.add(keys[j]);
            }
        }

        Collections.sort(list);

        answer = new String[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    public static String[] getKeys(HashMap<String, Integer> map) {
        int maxValue = Integer.MIN_VALUE;
        ArrayList<String> keys = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int value = entry.getValue();
            if (value > maxValue) {
                maxValue = value;
                keys.clear();
                keys.add(entry.getKey());
            } else if (value == maxValue) {
                keys.add(entry.getKey());
            }
        }
        if (keys.size() > 1) {
            return keys.toArray(new String[0]);
        } else {
            return new String[0];
        }
    }

    // 오름차순, 요소 중복x
    private static void search(int depth, int before) {
        if (depth == combi) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < arr.length; i++) {
                sb.append(arr[i]);
            }

            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
            return;
        }

        for (int i = 0; i < cur.length; i++) {
            if (!visited[i] && before < i) {
                visited[i] = true;

                arr[depth] = cur[i];
                search(depth+1, i);

                visited[i] = false;
            }
        }
    }
}
