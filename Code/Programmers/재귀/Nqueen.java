package Programmers.재귀;

public class Nqueen {
	// 대표적인 백트래킹 문제.
	// 푼 건 아니고 백트래킹을 공부하면서 알게 된 코드를 외워서 타이핑 해봄.
	// 백트래킹은 재귀를 사용해서 문제를 푼다는 데서 DFS와 유사하게 보이기도 하지만,
	// 가지치기를 해서 안될 것 같으면 중간에 그만두고, 해당 방문 처리를 다시 원복시킨다는 점이 있다.
	// 따라서 완전 탐색 보다는 빠르게 답에 도달하게 된다.
	// 문제 -> NxN의 체스판에, N개의 퀸을 상호 공격을 하지 못하도록 놓기
	static int N = 5;
	static int[][] board = new int[N][N];

	public static void main(String[] args) {
		if (!putQueen(0)) {
			System.out.println("put queen failed.");
		} else {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(board[i][j] + " ");
				}
				System.out.println();
			}
		}
	}

	public static boolean putQueen(int col) {
		if (col >= N) return true; // N보다 크거나 같으면 모든 열에 퀸이 다 배치된 것.

		for (int i = 0; i < N; i++) { // 열을 각 행에서 넣을 수 있는지 파악
			if (check(i,col)) {
				board[i][col] = 1;

				// 이후 열에서도 퀸 배치가 가능한지 파악
				if (putQueen(col+1)) return true;

				// 백트래킹 - 가지치기
				board[i][col] = 0;
			}
 		}

		return false;
	}

	public static boolean check(int row, int col) {
		// 현재 행에 이미 퀸이 있는지 확인
		for (int i = 0; i < N; i++) {
			if (board[row][i] == 1) return false;
		}


		return true; // 모두 해당 안되면 true
	}
}
