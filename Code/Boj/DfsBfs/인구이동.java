package Boj.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 인구이동 {
	static final int[] dx = {0, 0, 1, -1};
	static final int[] dy = {1, -1, 0, 0};
	static int[][] map;
	static boolean[][] visit;
	static int n;
	static int l;
	static int r;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		visit = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int s = 0;

		for (int i = 0; i < 2000; i++) {
			boolean check = false; // 인구 변동 여부

			for (int k = 0; k < n; k++) {
				for (int j = 0; j < n; j++) {
					if (bfs(k, j)){ check = true;}
				}
			}

			// 인구 변동이 없으면 종료
			if (!check) break;

			s++;
			visit = new boolean[n][n];
		}

		System.out.println(s);
	}

	public static boolean bfs(int x, int y) {
		if(visit[x][y]) return false;

		Queue<Node> q = new LinkedList<>();
		List<Node> list = new ArrayList<>();

		q.offer(new Node(x, y));
		visit[x][y] = true;

		int total = 0;

		while (!q.isEmpty()) {
			Node p = q.poll();
			int cx = p.x;
			int cy = p.y;

			if (!visit[cx][cy]) { // 미방문 시
				visit[cx][cy] = true; // 방문 처리
				list.add(new Node(cx,cy));
				total += map[cx][cy]; // total plus

				for (int i = 0; i < 4; i++) {
					int nx = cx + dx[i];
					int ny = cy + dy[i];
					if (nx < 0 | nx >= n | ny < 0 | ny >= n) continue;

					// 지역간 차이가 범위 이하이면
					int dif = Math.abs(map[cx][cy] - map[nx][ny]);
					if (dif >= l && dif <= r && !visit[nx][ny]) {
						q.offer(new Node(nx,ny));
					}
				}
			}
		}

		if (list.size() < 1) {
			return false;
		}

		int equal = (total / list.size());

		for (Node n: list) {
			map[n.x][n.y] = equal;
		}

		return true;
	}

	private static class Node {

		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
