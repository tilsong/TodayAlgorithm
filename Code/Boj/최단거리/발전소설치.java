package Boj.최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
// 할 수 있는 모든 경우의 수는 다해봤는데 안됐다. 37% 에서 계속 틀린다.. 왜인지 모르겠다ㅠ
public class 발전소설치 {
	static double INF = 1e9;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		double[] d = new double[n+1];
		Arrays.fill(d, INF);

		// 전선 수
		int w = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		double m = Double.parseDouble(st.nextToken());

		Location[] locs = new Location[n+1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			locs[i] = new Location(x,y);
		}

		// 발전소 간 간선 추가
		ArrayList<ArrayList<Power>> graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}

		Map<String, Integer> zero = new HashMap<>();

		// 최단 거리 설정 - 이미 값이 0인 간선이 있을 때
		for (int i = 0; i < w; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			graph.get(from).add(new Power(to, 0));
			graph.get(to).add(new Power(from, 0));

			zero.put(from+"_"+to, 0);
			zero.put(to+"_"+from, 0);
		}

		// 각 발전소 간의 간선 거리들을 구한다. 음수 고려. 시간복잡도 약 (n*n-1)/2
		for (int i = 1; i <= n ; i++) {
			for (int j = 1; j <= n ; j++) {
				if (i != j) {
					// locs[i] 와 locs[j] 간 거리 구하기
					long xd = Math.abs(locs[i].x - locs[j].x);
					long yd = Math.abs(locs[i].y - locs[j].y);
					double dist = Math.sqrt(xd*xd + yd*yd);

					if (xd*xd + yd*yd <= m*m && !zero.containsKey(i+"_"+j)) { // 거리가 허용 범위 내이면 간선을 추가한다.
						graph.get(i).add(new Power(j, dist));
					}
				}
			}
		}

		// 다익스트라
		Queue<Power> q = new PriorityQueue<>();
		q.offer(new Power(1, 0));
		d[1] = 0;

		while (!q.isEmpty()) {
			Power p = q.poll();
			int now = p.id;

			for (int i = 0; i < graph.get(now).size(); i++) {
				double cost = d[now] + graph.get(now).get(i).dist;

				if (cost < d[graph.get(now).get(i).id]) { // 최단 거리이면
					d[graph.get(now).get(i).id] = cost;
					q.offer(new Power(graph.get(now).get(i).id, cost));
				}
			}
		}

		long answer = (long) Math.floor(d[n] * 1000);

		System.out.println(answer);
	}

	private static class Power implements Comparable<Power>{
		int id;
		double dist;

		public Power(int id, double dist) {
			this.id = id;
			this.dist = dist;
		}

		@Override
		public int compareTo(Power o) {
			return (int) (this.dist - o.dist);
		}
	}

	private static class Location {
		int x;
		int y;

		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
