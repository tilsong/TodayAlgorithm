package Boj.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 어두운굴다리 {
	// Sample Input/Output
	// 5
	// 2
	// 2 4

	// 20m (Success)
	// 입력: 첫 번째 줄에 굴다리의 길이 N 이 주어진다. (1 ≤ N ≤ 100,000)
	// 		두 번째 줄에 가로등의 개수 M 이 주어진다. (1 ≤ M ≤ N)
	// 		다음 줄에 M 개의 설치할 수 있는 가로등의 위치 x 가 주어진다. (0 ≤ x ≤ N)
	// 		가로등의 위치 x는 오름차순으로 입력받으며 가로등의 위치는 중복되지 않으며, 정수이다.
	// 출력: 굴다리의 길이 N을 모두 비추기 위한 가로등의 최소 높이를 출력한다.
	// 풀이 방향: 쉽게 풀긴했는데, 왜 이분 탐색 문제로 있는 건지는 모르겠다.. 그리디에 가깝게 문제를 풀었다.
	//			빈 공간의 max 크기를 구하는 데 중점을 뒀고, 가로등 사이일 경우 나누기 2를 했다.
	// 시간 복잡도: O(N), 최대 10만

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int [] x = new int[m];
		for (int i = 0; i < m; i++) {
			x[i] = Integer.parseInt(st.nextToken());
		}

		int max = x[0];
		for (int i = 1; i < m; i++) {
			int d = (x[i] - x[i - 1]);
			if (d % 2 == 0) {
				d /= 2;
			} else {
				d /= 2;
				d++;
			}

			if (max < d) {
				max = d;
			}
		}

		// 마지막 가로등과 n 비교
		int last = n - x[m - 1];
		if (last > max) {
			max = last;
		}

		System.out.println(max);
	}
}
