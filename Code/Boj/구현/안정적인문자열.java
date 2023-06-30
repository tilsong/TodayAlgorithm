package Boj.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 안정적인문자열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String t = st.nextToken();
        int i = 1;
        while (!t.contains("-")) {
            Stack<Character> q = new Stack<>();
            int count = 0;

            for (int j = 0; j < t.length(); j++) {
                if (t.charAt(j) == '{') {
                    q.push('{');
                } else { // '}'
                    if (q.isEmpty()) {
                        count++;
                        q.push('{');
                    } else {
                        q.pop();
                    }
                }
            }
            count += q.size()/2;

            System.out.println(i+ ". " + count);
            t = new StringTokenizer(br.readLine()).nextToken();
            i++;
        }
    }
}
