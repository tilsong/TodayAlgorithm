package Boj.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 적록색약 {
	//
	// 입력: 첫째 줄에 N이 주어진다.(1 ≤ N ≤ 100) 둘째 줄부터 N개 줄에는 그림이 주어진다.
	// 출력: 적록색약이 아닌 사람이 봤을 때의 구역의 개수와 적록색약인 사람이 봤을 때의 구역의 수를 공백으로 구분해 출력한다.
	// 풀이 방향:
	// 시간 복잡도:
	static String[][] map;
	static boolean[][] visited;
	static int n;
	static int[] dx = {0,0,1,-1}; // 상 하 좌 우
	static int[] dy = {-1,1,0,0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		map = new String[n+1][n+1];
		visited = new boolean[n+1][n+1];

		for (int i = 1; i <= n; i++) {
			System.out.println(i);
			st = new StringTokenizer(br.readLine());
			String[] s = st.nextToken().split("");

			for (int j = 0; j < n; j++) {
				System.out.print("S["+j+"]=" + s[j]+ " ");
				map[i][j+1] = s[j];
			}
			System.out.println();
		}

		int count = 0;

		// 정상
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n ; j++) {
				if (!visited[i][j]) { // 미방문시
					count++; // 구역 개수 카운트
					bfs(map[i][j], i, j); // 해당 컬러에 해당하면 모두 방문 처리
				}
			}
		}

		// 방문 처리초기화
		visited = new boolean[n+1][n+1];

		int rgCount = 0;
		// 적녹색맹
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n ; j++) {
				if (!visited[i][j]) { // 미방문시
					if (map[i][j].equals("B")) { // "B" 일때
						bfs("B", i, j);
					} else {
						rgBfs(i,j);
					}
					rgCount++;
				}
			}
		}

		System.out.println(count + " " + rgCount);
	}

	public static void bfs(String color, int x, int y) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(x,y));

		while (!q.isEmpty()) {
			Node p = q.poll();
			int gx = p.getX();
			int gy = p.getY();

			if (gx < 1 | gx > n | gy < 1 | gy > n) continue; // 범위 외이면 패스
			if (visited[gx][gy]) continue;
			if (map[gx][gy] != color) continue; // 같은 색이 아니면 패스
			visited[gx][gy] = true;

			for (int i = 0; i < dx.length; i++) {
				q.offer(new Node(gx + dx[i], gy + dy[i]));
			}
		}
	}

	public static void rgBfs(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(x,y));

		while (!q.isEmpty()) {
			Node p = q.poll();
			int gx = p.getX();
			int gy = p.getY();

			if (gx < 1 | gx > n | gy < 1 | gy > n) continue; // 범위 외이면 패스
			if (visited[gx][gy]) continue; // 방문 시 패스
			if (map[gx][gy].equals("B")) continue; // B면 패스
			visited[gx][gy] = true;

			for (int i = 0; i < dx.length; i++) {
				q.offer(new Node(gx + dx[i], gy + dy[i]));
			}
		}
	}



	private static class Node {
		private int x;
		private int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
	}
}
