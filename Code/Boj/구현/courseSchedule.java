package Boj.구현;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class courseSchedule {
    public static void main(String[] args) {
        int numcoureses = 2;
        int[][] pre = {{1,0},{0,1}};

        canFinish(numcoureses, pre);
    }

    static boolean[] visited;
    static HashMap<Integer, List<Integer>> map;
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // hashmap 사용하기
        // integer, list
        // a -> b -> c 일 때, a <- c이거나 b <- c 이면 안됨
        visited = new boolean[numCourses];

        // 1. map 만들기
        map = new HashMap<>();

        for (int i = 0; i < prerequisites.length; i++) {
            if (map.containsKey(prerequisites[i][0])) {
                map.get(prerequisites[i][0]).add(prerequisites[i][1]);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(prerequisites[i][1]);
                map.put(prerequisites[i][0], list);
            }
        }

        // 2. 탐색하기. 탐색하지 못한 인덱스가 있는지?
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                search(0);
            }
        }

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                return false;
            }
        }

        return true;
    }

    public static boolean search(int n) {
        boolean ans = false;
        List<Integer> integers = map.get(n);

        for (int i = 0; i < integers.size(); i++) {
            if (visited[integers.get(i)]) {
                visited[n] = true;
                ans = true;
            } else {
                search(integers.get(i));
            }
        }
        return ans;
    }
}
