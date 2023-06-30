package Boj.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 다른 거는 제쳐두고 이해해야 하는 부분
//  같은 문자열일 경우
//  -> 출력해야 하는 것은 중복 없는 집합이므로 선택에서 제외 시켜야 한다.
//     따라서 아래 로직에서는 if (count == n-1) 로직이 수행되도록 같은 문자열일 때도 count에 추가해준다.

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
