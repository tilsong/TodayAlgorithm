package Boj.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 안전영역 {
	// Input/Output Sample

	// 5
	// 	6 8 2 6 2
	// 	3 2 3 4 6
	// 	6 7 3 3 2
	// 	7 2 5 3 6
	// 	8 9 5 2 7
	// 5

	static final int[] dx = {0, 0, 1, -1};
	static final int[] dy = {1, -1, 0, 0};
	static int[][] map;
	static boolean[][] visited;
	static int n;
	static int h;

	// 45m (Success)
	// 입력
	// 첫째 줄에는 어떤 지역을 나타내는 2차원 배열의 행과 열의 개수를 나타내는 수 N이 입력된다. N은 2 이상 100 이하의 정수이다.
	// 둘째 줄부터 N개의 각 줄에는 2차원 배열의 첫 번째 행부터 N번째 행까지 순서대로 한 행씩 높이 정보가 입력된다.
	// 각 줄에는 각 행의 첫 번째 열부터 N번째 열까지 N개의 높이 정보를 나타내는 자연수가 빈 칸을 사이에 두고 입력된다. 높이는 1이상 100 이하의 정수이다.
	// 출력
	// 첫째 줄에 장마철에 물에 잠기지 않는 안전한 영역의 최대 개수를 출력한다.
	// 풀이 방향:
	// - 입력을 이차 배열로 받아 x,y 좌표로 접근했습니다.
	// - 이중 for문을 통해 모든 배열의 요소에 접근하고, 조건(주어진 높이 보다 높을 경우)에 일치하면 해당 요소에서 dfs로 접근할 수 있는 모든 곳에 방문 처리를 하고, 이를 count했습니다. 조건에 일치하지 않으면 방문 처리만하고 패스했습니다.
	// - 그리고 문제에 대한 애매한 부분이 있는데, 비가 오지 않은 경우를 계산하여(즉, 높이가 0인 경우)
	//   모든 지역의 크기가 1이더라도 최소 1의 값을 잠기지 않는 최소 지역 개수로 두겠다는 전제를 한 것 같습니다.
	//   그런데 조건에서 높이는 1 이상 100 이하이고, 비가 오지 않을 수 있다는 전제가 없어, 이 부분이 애매하게 생각됩니다.
	// 시간 복잡도: O(N^3)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		visited = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int maxCount = 1;
		int count = 0;

		for (int k = 1; k <= 100; k++) {
			h = k;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(dfs(i,j)) count++;
				}
			}

			if (maxCount < count) maxCount = count;

			visited = new boolean[n][n];
			count = 0;
		}

		System.out.println(maxCount);
	}

	public static boolean dfs(int x, int y) {
		if (x < 0 | x >= n | y < 0 | y >= n) return false;

		if (!visited[x][y]) { // 미 방문 시
			visited[x][y] = true;
			if (map[x][y] > h) {
				for (int i = 0; i < 4; i++) {
					dfs(x+dx[i], y+dy[i]);
				}
				return true;
			}
		}

		return false; // 방문 시 false
	}



}
