package Boj.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 접두사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        String [] arr = new String[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new StringTokenizer(br.readLine()).nextToken();
        }

        Set<String> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }

                if (arr[i].length() <= arr[j].length()) {
                    for(int k = 0; k < arr[i].length(); k++) {
                        if (arr[i].equals(arr[j])) {
                            count++;
                            break;
                        }
                        if(arr[i].charAt(k) != arr[j].charAt(k)) {
                            count++;
                            break;
                        }
                    }
                } else {
                    count ++;
                }
            }
            if (count == n-1) {
                set.add(arr[i]);
            }
        }

        System.out.println(set.size());
    }
}
