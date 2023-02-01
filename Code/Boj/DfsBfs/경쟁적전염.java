package Boj.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 경쟁적전염 {

	static final int[] dx = {0, 0, 1, -1};
	static final int[] dy = {1, -1, 0, 0};
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		map = new int[n][n];

		List<Node> list = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int v = Integer.parseInt(st.nextToken());
				if (v != 0) list.add(new Node(i, j, 0, v));
			}
		}

		list.sort((o1, o2) -> o1.virus-o2.virus);

		Queue<Node> q = new LinkedList<>();
		q.addAll(list);


		st = new StringTokenizer(br.readLine());

		int s = Integer.parseInt(st.nextToken());
		int sx = Integer.parseInt(st.nextToken());
		int sy = Integer.parseInt(st.nextToken());

		// bfs 수행
		while (!q.isEmpty()) {
			Node p = q.poll();
			int x = p.x;
			int y = p.y;
			int cs = p.s;
			int v = p.virus;

			if (cs > s) break;
			if (x < 0 | x >= n | y < 0 | y >= n) continue;

			if (map[x][y] == 0) { // 미방문 시 방문 처리
				map[x][y] = v;
				for (int i = 0; i < 4; i++) {
					q.offer(new Node(x+dx[i], y+dy[i], cs+1, v));
				}
			}

		}

		System.out.println(map[sx-1][sy-1]);
	}


	private static class Node {

		 int x;
		 int y;
		 int s;
		 int virus;

		public Node(int x, int y, int s, int virus) {
			this.x = x;
			this.y = y;
			this.s = s;
			this.virus = virus;
		}
	}
}
