package Boj.구현;

import java.util.HashSet;
import java.util.Set;

public class 보석쇼핑 {
    public static void main(String[] args) {
        String[] gems = { "DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA" };
        int[] solution = solution(gems);

        for (int i = 0; i < solution.length; i++) {
            System.out.print (solution[i] + " ");
        }
    }

    public static int[] solution(String[] gems) {

        Set<String> gemSet = new HashSet<>();

        for (int i = 0; i < gems.length; i++) {
            gemSet.add(gems[i]);
        }
        int gemKinds = gemSet.size();
        gemSet.clear();

        int[] answer = {0,0};

        int start = 0;
        int count = (int) 1e9;

        int end;
        for (end = 0; end < gems.length; end++) {
            gemSet.add(gems[end]);

            if (gemSet.size() == gemKinds) {
                answer[0] = start;
                answer[1] = end;

                if ((end-start+1) < count) {
                    count = (end-start+1);
                }
                gemSet.remove(gems[start]);

                start ++;
            }
        }
        end--;



//        while(true) {
//            if (start < gems.length - gemKinds) {
//                break;
//            }
//
//            if (gemSet.size() == gemKinds) {
//                answer[0] = start;
//                answer[1] = end;
//
//                if ((end-start+1) < count) {
//                    count = (end-start+1);
//                }
//                gemSet.remove(gems[start]);
//
//
//            }
//
//            start ++;
//        }


        return answer;
    }
}
