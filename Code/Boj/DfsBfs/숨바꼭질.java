package Boj.DfsBfs;

import java.util.LinkedList;
import java.util.Queue;

public class 숨바꼭질 {
	public static void main(String[] args) {
		System.out.println("숨바꼭질");
		System.out.println(mySolution(5,17));

	}

	// 710-
	// 입력: 수빈이의 위치(int n), 동생의 위치(int k)
	// 출력: 수빈이가 동생을 찾을 수 있는 가장 빠른 시간(int time)
	// 풀이 방향: 방문할 수 있는 곳은 0~100000 이므로 방문 배열을 크기 100001로 잡고, Node 클래스를 만들어 이전 위치를 기록할 수 있도록 했다.
	//			그래서 방문 배열[이전 크기]를 통해 이전 크기를 참고하고, +1을 할 수 있다.
	//			중요한 것은 그래프로 표현이 되는, 그래서 DFS/BFS를 사용할 수 있는 문제인지를 파악하는 것이고,
	//			그래프로 만들 수 있으면 해당하는 경우의 수만 BFS라면 큐에 추가하거나, DFS면 재귀로 만들면 된다.
	// 시간 복잡도:
	public static int mySolution(int n, int k) {
		int [] visited = new int[100001]; // 방문
		Queue<Node> q = new LinkedList<>();

		q.offer(new Node(n, n));

		while (!q.isEmpty()) {
			Node node = q.poll();
			int x = node.getX();
			int before = node.getBefore();

			// 범위 이탈 시 처리
			if (x < 0 | x >= 100000){
				continue;
			}
			if (visited[x] == 0) { // 미방문 시
				visited[x] = visited[before] + 1; // 방문 처리
				if(x == k) return visited[k]-1;

				q.offer(new Node(x-1, x)); // 뒤로 걷기
				q.offer(new Node(x + 1, x)); // 앞으로 걷기
				q.offer(new Node(x*2, x)); // 순간 이동
			}

		}

		return visited[k]-1;
	}

	private static class Node {
		private int x;
		private int before;

		public Node(int x, int before) {
			this.x = x;
			this.before = before;
		}

		public int getX() {
			return x;
		}

		public int getBefore() {
			return before;
		}
	}
}

