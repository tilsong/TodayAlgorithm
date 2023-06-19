package Boj.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class nm {

		static int n = 5, m = 5;
		static boolean c[]; static int a[];
		static StringBuilder sb = new StringBuilder();

		public static void main(String[] args) throws IOException {
			// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			// StringTokenizer st = new StringTokenizer(br.readLine());
			//
			// n = Integer.parseInt(st.nextToken());
			// m = Integer.parseInt(st.nextToken());

			c = new boolean[n+1];
			a = new int[n+1];

			go(0);

			System.out.println(sb);
		}

		public static void go(int index) {
			//인덱스가 마지막 위치에 도달하면 수열 출력
			if(index == m) {
				for(int i=0; i<m; i++) {
					sb.append(a[i]).append(" ");
				}
				sb.append('\n');
				return;
			}
			// 1부터 ~ N개의 수를 선택
			for(int i=1; i<=n; i++) {
				if(c[i]) continue; //이미 선택한적이 있으면 다음으로
				c[i] = true;  // 수 i를 사용
				a[index] = i; //해당 위치에 i를 넣는다.
				go(index+1); //위치를 1증가 시키고 재귀
				c[i] = false; // index 뒤에 일어날 모든 경우를 했기때문에 수 i를 사용하지 않았다고 바꾼다.
			}
		}

}
