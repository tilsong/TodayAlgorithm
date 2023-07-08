package Boj.미분류;

import java.util.*;

public class 이 {

    public static void main(String[] args) {
        int[][] rel =  {{1, 2}, {2, 3}, {2, 6}, {3, 4}, {4, 5}};
        System.out.println(solution(rel, 1, 2));
    }

    static Map<Integer, List<Integer>> map;
    static Set<Integer> friends = new HashSet<>();
    static int count = 0;
    static int newCount = 0;
    static int limitDep;
    public static int solution(int[][] relationships, int target, int limit) {
        limitDep = limit;
        friends.add(target);
        map = new HashMap<>();

        for (int i = 0; i < relationships.length; i++) {
            if (map.containsKey(relationships[i][0])) {
                map.get(relationships[i][0]).add(relationships[i][1]);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(relationships[i][1]);
                map.put(relationships[i][0], list);
            }
            if (map.containsKey(relationships[i][1])) {
                map.get(relationships[i][1]).add(relationships[i][0]);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(relationships[i][0]);
                map.put(relationships[i][1], list);
            }
        }


        List<Integer> list = map.get(target);
        count += (list.size() * 5);
        for (int i = 0; i < list.size(); i++) {
            int cur = list.get(i);
            friends.add(cur);
            recur(cur, 1);
        }

        return count + newCount;
    }

    public static void recur(int cur, int depth) {
        if (depth == limitDep) {
            return;
        }

        List<Integer> list = map.get(cur);
        for (int i = 0; i < list.size(); i++) {
            if (!friends.contains(list.get(i))) {
                count += 10;
                friends.add(list.get(i));
                newCount++;

                recur(list.get(i), depth+1);
            }
        }
    }
}
