package Boj.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 두수의합 {
	// Sample Input/Output
	// 4
	// 10 8
	// -7 9 2 -4 12 1 5 -3 -2 0
	// 10 4
	// -7 9 2 -4 12 1 5 -3 -2 0
	// 4 20
	// 1 7 3 5
	// 5 10
	// 3 9 7 1 5
	//
	// 1
	// 5
	// 1
	// 2

	// 70m (Fail -> Success)
	// 입력: 프로그램은 표준입력으로 입력을 받는다.
	// 		프로그램 입력은 t 개의 테스트 케이스로 구성된다. 입력의 첫 번째 줄에 테스트 케이스의 개수를 나타내는 정수 t 가 주어진다.
	// 		두 번째 줄부터 두 줄에 한 개의 테스트 케이스에 해당하는 데이터가 주어진다.
	// 		각 테스트 케이스의 첫 번째 줄에는 두 개의 정수 n 과 K (2 ≤ n ≤ 1,000,000, -108 ≤ K ≤ 108 )가 한 개의 공백을 사이에 두고 입력된다.
	// 		두 번째 줄에는 n 개의 정수가 하나의 공백을 사이에 두고 주어지며, 각 정수의 최댓값은 108 이고, 최솟값은 -108 이다. 잘못된 데이터가 입력되는 경우는 없다.
	// 출력: 출력은 표준출력(standard output)을 사용한다. 입력되는 테스트 케이스의 순서대로 다음 줄에 이어서 각 테스트 케이스의 결과를 출력한다.
	// 		각 테스트 케이스의 출력되는 첫 줄에 입력으로 주어진 n 개의 정수들 중에서 서로 다른 두 정수의 합이 주어진 또 다른 정수 K 에 가장 가까운 두 정수의 조합의 수를 출력한다.
	// 풀이 방향: 처음엔 이분 탐색을 어떻게든 적용시켜보려고 애썼다. 이분 탐색 분류에 들어가 있어서.. 근데 아무래도 안풀려서 풀이를 보니까,
	//			이분 탐색을 아무도 사용하고 있지 않았다..^^ 그래서 투포인터로 문제를 풀어봤다.
	//			두 포인터를 이동시키는 것이 핵심이고, 한 번 start로 조회한 곳은 다시 start로 방문하지 않는 것이 핵심이다. end 역시 마찬가지.
	//			두 개 요소를 찾을 때 사용되는 알고리즘으로 생각된다.
	// 시간 복잡도: O(n) -> 투포인터는 전체 배열의 요소를 조회해야 하기 때문.

	static int k;
	static int[] numArr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int t = Integer.parseInt(st.nextToken());

		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			numArr = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				numArr[j] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(numArr);

			System.out.println(search());
		}
	}

	static int search() {
		int start = 0;
		int end = numArr.length - 1;
		int count = 1;
		int near = (int) 1e9;

		while (start < end) {
			int sum = numArr[start] + numArr[end];
			int d = Math.abs(sum - k);
			
			// 최소 거리 개수 파악
			if (d < near) {
				near = d;
				count = 1;
			} else if (d == near) {
				count ++;
			}

			if (sum == k) {
				start ++;
				end --;
			} else if (sum < k) {
				start ++;
			} else {
				end --;
			}
		}

		return count;
	}
}
