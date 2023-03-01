package Boj.최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 50m (Fail -> Success)
// 입력: 첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
// 출력: 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
// 풀이 방향: 처음에는 숨바꼭질1 처럼 단순히 BFS로 완전탐색을 하는 문제인가? 라는 생각을 했다. 그런데 그런 문제는 아닐 것 같아서.. 아래 유형 분류를 보니깐
//			0-1 BFS라고 있었다. 가중치가 0 혹은 1만 있는 BFS 문제의 경우, 우선순위를 나누어 진행할 수 있다는 것이다.
//			큐의 앞 뒤에 모두 새로운 요소를 넣고 뺄 수 있는 Deque 구조를 사용하여, 가중치가 0인 경우를 우선적으로 계산할 수 있도록 했다.
//			0일 경우 앞에 넣고, 1일 경우 뒤에 넣는 식이다.
//			그리고 최단 거리 테이블을 사용하여 풀 수 있다. 이는 다익스트라의 구조와 유사하다. 짬뽕이 참 많다..
// 시간 복잡도: 모르겠다.


public class 숨바꼭질3 {
	static int INF = (int) 1e9;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int[] d = new int[100001];
		Arrays.fill(d, INF);

		int k = Integer.parseInt(st.nextToken());

		Deque<Integer> q = new LinkedList<>();
		q.offer(n);
		d[n] = 0;

		while (!q.isEmpty()) {
			Integer now = q.poll();

			if (now == k) break;

			if (now*2 <= 100000 && d[now*2] == INF) {
				q.addFirst(now*2);
				d[now*2] = d[now];
			}

			if (now-1 >= 0 && d[now-1] == INF) {
				q.offer(now-1);
				d[now-1] = d[now] + 1;
			}

			if (now+1 <= 100000 && d[now+1] == INF) {
				q.offer(now+1);
				d[now+1] = d[now] + 1;
			}
		}

		System.out.println(d[k]);

	}

}
