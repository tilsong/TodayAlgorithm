package Boj.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 단어맞추기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        while(t-- > 0) {
            char[] chars = br.readLine().toCharArray();
            int fInd = -1;
            for (int i = chars.length-2; i >= 0; i--) {
                if (chars[i] < chars[i+1]) {
                    fInd = i;
                    break;
                }
            }

            if (fInd == -1) {
                System.out.println(new String(chars));
                continue;
            }

            for (int i = chars.length-1; i > fInd; i--) {
                if (chars[fInd] < chars[i]) {
                    char temp = chars[fInd];
                    chars[fInd] = chars[i];
                    chars[i] = temp;
                    break;
                }
            }

            Arrays.sort(chars, fInd+1, chars.length);
            System.out.println(new String(chars));
        }
    }
}



