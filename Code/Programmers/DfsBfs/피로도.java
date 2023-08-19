package Programmers.DfsBfs;

import java.util.PriorityQueue;
import java.util.Queue;

public class 피로도 {
	public static void main(String[] args) {

	}


	public static int mySolution(int k, int[][] dungeons) {
		int max = 0;

		for (int i = 1; i <= dungeons.length; i++) {
			int count = bfs(k, i, dungeons);
			if (max < count) max = count;
		}

		return max;
	}

	public static int bfs(int k, int start, int[][] dungeons) {
		int[] visited = new int[dungeons.length+1];
		int count = 0;

		Queue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(dungeons[start][0], dungeons[start][1]));

		while (k > 0) {
			Node node = pq.poll();
			int enter = node.getEnter();
			int need = node.getNeed();

			if (k < need) continue;

		}

		return 1;
	}

	private static class Node {
		private int enter;
		private int need;

		public Node(int enter, int need) {
			this.enter = enter;
			this.need = need;
		}

		public int getEnter() {
			return enter;
		}

		public int getNeed() {
			return need;
		}
	}
}
