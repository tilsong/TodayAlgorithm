package Boj.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 나는 이제 내가 실버 레벨이라는 확신이 들었다. 넘 어렵네ㅠㅠ

public class 부등호 {
    static int k;
    static char[] signs;
    static boolean[] visited;
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());

        signs = new char[10];
        visited = new boolean[10];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            signs[i] = st.nextToken().charAt(0);
        }
        search( "", 0);

        System.out.println(list.get(list.size()-1));
        System.out.println(list.get(0));
    }

    static private void search(String nums, int depth) {
        if (depth == k+1) {
            list.add(nums);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (visited[i]) continue;

            if (depth == 0 || check(Character.getNumericValue(nums.charAt(depth-1)), i, signs[depth-1])) {
                visited[i] = true;
                search(nums+i, depth+1);
                visited[i] = false;
            }
        }
    }

    private static boolean check(int a, int b, char sign) {
        if (sign == '<') {
            if (a > b) {
                return false;
            }
        } else {
            if (a < b) {
                return false;
            }
        }
        return true;
    }

}