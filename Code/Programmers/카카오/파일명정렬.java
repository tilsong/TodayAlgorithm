package Programmers.카카오;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 파일명정렬 {
    public static void main(String[] args) throws IOException {
        String [] input = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
//        String[] input = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};

        String[] solution = solution(input);
        System.out.println(solution);
    }

    public static String[] solution(String[] files) {
        String[] answer = {};
        List<FileName> fileNames = new ArrayList<>();

        List<Character> arr= List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

        for (int i = 0; i < files.length; i++) {
            String file = files[i];

            int numIdx = 0;
            for (int j = 0; j < file.length(); j++) {
                if (arr.contains(file.charAt(j))){
                    numIdx = j;
                    break;
                }
            }
            int tailIdx = 0;
            for (int j = numIdx; j < file.length(); j++) {
                if (!arr.contains(file.charAt(j))) {
                    tailIdx = j;
                    break;
                }
            }

            String head = file.substring(0, numIdx);
            String num = file.substring(numIdx, tailIdx);
            String tail = file.substring(tailIdx);

            FileName fileName = new FileName(files[i], head, num, tail);
            fileNames.add(fileName);
        }

        Collections.sort(fileNames);

        answer = new String[files.length];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = fileNames.get(i).origin;
        }

        return answer;
    }

    static class FileName implements Comparable<FileName> {
        String origin;
        String head;
        String num;
        String tail;

        public FileName(String origin, String head, String num, String tail) {
            this.origin = origin;
            this.head = head;
            this.num = num;
            this.tail = tail;
        }

        @Override
        public int compareTo(FileName o) {
            if (this.head.toLowerCase().compareTo(o.head.toLowerCase()) == 0) {
                return Integer.parseInt(this.num) - Integer.parseInt(o.num) ;
            }
            return this.head.toLowerCase().compareTo(o.head.toLowerCase());
        }
    }
}
