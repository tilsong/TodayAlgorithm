package Boj.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class 단축키지정 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        Map<Character, Boolean> check = new HashMap<>();
        List<Character> alp = List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');

        for (Character ch : alp) {
            check.put(ch, true);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String origin = br.readLine();
            String lower = origin.toLowerCase();

            // 각 단어의 첫글자 check
            String[] o = origin.split(" ");
            String[] s = lower.split(" ");
            boolean flag = false;
            for (int j = 0; j < s.length; j++) {
                if(alp.contains(s[j].charAt(0))) {
                    if (check.get(s[j].charAt(0))) {
                        check.put(s[j].charAt(0), false);
                        for (int k = 0; k < s.length; k++) {
                            if (k == j) {
                                sb.append("[").append(o[j].substring(0, 1)).append("]").append(o[j].substring(1)).append(" ");
                            } else {
                                sb.append(o[k]).append(" ");
                            }
                        }
                        sb.append('\n');
                        flag = true;
                        break;
                    }
                }
            }


            if (!flag) {
                char[] chars = lower.toCharArray();

                int ind = -1;
                for (int j = 0; j < chars.length; j++) {
                    if (check.containsKey(chars[j])) {
                        if (check.get(chars[j])) {
                            check.put(chars[j], false);
                            ind = j;
                            break;
                        }
                    }
                }

                if (ind != -1) {
                    String front = "";
                    if (ind != 0) {
                        front = origin.substring(0, ind);
                    }
                    String back = origin.substring(ind+1);
                    sb.append(front).append("[").append(origin.charAt(ind)).append("]").append(back).append('\n');
                } else {
                    sb.append(origin).append('\n');
                }

            }
        }

        System.out.println(sb);
    }
}