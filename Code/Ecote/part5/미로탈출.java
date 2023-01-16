package Ecote.part5;

import java.util.LinkedList;
import java.util.Queue;

public class 미로탈출 {

	// 책의 풀이 보고 작성해봄! 처음이라 적용의 감을 못 잡았음..
	// 입력: 미로(int[][] map)
	// 출력: 탈출 움직임의 최소 개수(int count)
	// 풀이 방향:  오하좌상 순으로 이동 갈 수 있는 방향 찾기(방문 하지 않은 노드로 이동)
	//			선택한 방향으로 이동. (n,m) 도달 시 stop
	// 시간 복잡도: O(N*3600)
	static int [][] map = {
		{1, 0, 1, 0, 1, 0},
		{1, 1, 1, 1, 1, 1},
		{0, 0, 0, 0, 0, 1},
		{1, 1, 1, 1, 1, 1},
		{1, 1, 1, 1, 1, 1}
	};

	public int  bookSolution(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(x,y));

		int [] dx = {0, 0, -1, 1}; // 상하좌우
		int [] dy = {1, -1, 0, 0};

		while(!q.isEmpty()) {
			Node node = q.poll();
			x = node.getX();
			y = node.getY();

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				// 없는 위치일 경우
				if (nx<0 | nx>=map.length | ny<0 | ny>=map[0].length) continue;

				// 몬스터인 경우
				if(map[nx][ny] == 0) continue;

				// 방문 확인

			}

		}
		return map[map.length-1][map[0].length-1];
	}
}

class Node{
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
