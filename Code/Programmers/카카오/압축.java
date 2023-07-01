package Programmers.카카오;

import java.util.ArrayList;
import java.util.List;

public class 압축 {

    public static void main(String[] args) {
        String input = "KAKAO";
        int[] solution = solution(input);

        for (int i = 0; i < solution.length; i++) {
            System.out.print(solution[i] + " ");
        }
    }

    public static int[] solution(String msg) {
        String[] defaultDictionary = {"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        List<String> dictionary = new ArrayList<>(List.of(defaultDictionary));
        List<Integer> temp = new ArrayList<>();
        
        int sId = 0;
        int eId = 0;

        int curNum = 0;
        for (int i = 0; i < msg.length(); i++) {
            String subStr = msg.substring(sId, eId);
            if (dictionary.contains(subStr)) {
                curNum = dictionary.indexOf(subStr);
                eId++;
            } else {
                dictionary.add(subStr);
                temp.add(curNum);
                sId = eId-1;
            }
        }

        int[] answer = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            answer[i]= temp.get(i);
        }

        return answer;
    }
}
