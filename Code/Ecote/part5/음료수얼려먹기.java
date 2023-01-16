package Ecote.part5;

public class 음료수얼려먹기 {

	// 책의 풀이 보고 작성해봄! 처음이라 적용의 감을 못 잡았음..
	// 입력: 얼음틀의 가로 세로 길이(세로 int n, 가로 int m), 얼음틀 모양(int[][] arr)
	// 출력: 생성되는 총 아이스크림 개수(int count)
	// 풀이 방향: DFS.
	// 시간 복잡도: o(n^2)
	static int [][] visited = new int[1000][1000]; // 0 < n,m <= 1000
	static int n;
	static int m;

	public static int bookSolution(int [][] arr) {
		int count = 0;


		// 로직
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(dfs(i,j)) count++;
			}
		}

		return count;
	}

	public static boolean dfs(int x, int y){
		// 영역 외일 경우 return
		if (x <= -1 | x > n | y <= -1 | y > n) return false;

		// 방문 확인
		if (visited[x][y] == 0) {
			// 방문 처리
			visited[x][y] = 1;

			// 상하좌우 방문
			dfs(x, y+1);
			dfs(x, y-1);
			dfs(x-1, y);
			dfs(x+1, y);

			return true;
		}
		return false;
	}

}
