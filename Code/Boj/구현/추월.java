package Boj.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 추월 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        Map<String, Integer> in = new HashMap<>();

        for (int i = 0; i < n; i++) {
            in.put(br.readLine(), i);
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            String out = br.readLine();
            arr[i] = in.get(out);
        }

        int answer = 0;
        for (int i = 0; i< n-1; i++){
            for (int j = i+1; j < n; j++){
                if (arr[i] > arr[j]){
                    answer += 1;
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}
