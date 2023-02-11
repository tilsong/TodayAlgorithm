package Boj.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 과자나눠주기 {

	// Sample Input/Output
	// 3 10
	// 1 2 3 4 5 6 7 8 9 105

	// 25m (Success)
	// 입력: 첫째 줄에 조카의 수 M (1 ≤ M ≤ 1,000,000), 과자의 수 N (1 ≤ N ≤ 1,000,000)이 주어진다.
	// 		둘째 줄에 과자 N개의 길이 L1, L2, ..., LN이 공백으로 구분되어 주어진다. 과자의 길이는 (1 ≤ L1, L2, ..., LN ≤ 1,000,000,000) 를 만족한다.
	// 출력: 첫째 줄에 조카 1명에게 줄 수 있는 막대 과자의 최대 길이를 출력한다.
	// 		단, 모든 조카에게 같은 길이의 막대과자를 나눠줄 수 없다면, 0을 출력한다.
	// 풀이 방향: 과자의 길이를 h로 두고, 1부터 과자의 최대 길이 사이에서 이분탐색을 했다. 또한 각 범위 내의 과자들을 h만큼 잘랐을 때의
	//			개수와 조카 수를 비교하여, 이분 탐색의 방향을 정했다. Ea-sy.
	//			시간 복잡도 초과 시 개선의 여지가 있는 부분들은 남겨놓았다. 요즘 이런 경우가 많은데,
	//			이렇게 하는 게 더 낫다고 느꼈다.
	//			먼저는 중요한 로직에 집중하고, 필요할 경우 개선사항을 반영하는 순서를 따르는 것이다.
	//			어떤 코드를 작성하든 반영될 수 있을 것 같다고 생각한다...
	// 시간 복잡도: O(log(N1)*N2), N1은 최대 길이를 가진 과자를 말하고(십억), N2는 과자수의 최대 개수(십만)를 말한다.
	//			  더 감소될 내용이 있기는 한데, 요정도로 계산하겠다.

	static int [] l;
	static int m;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		l = new int[n];
		for (int i = 0; i < n; i++) {
			l[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(l);

		int start = 1;
		int end = l[l.length -1];
		int answer = 0;

		// 이분 탐색
		while (start <= end) {
			int mid = (start + end) / 2;

			if (check(mid)) {
				answer = mid;
				start = mid + 1;
			} else {
				end = mid -1;
			}
		}

		System.out.println(answer);
	}
	static boolean check(int mid) {
		int count = 0;
		for (int i = l.length-1; i >= 0; i--) {
			count += (l[i]/mid);
			// if (count > m) return true;
		}

		if (count < m) {
			return false;
		}
		return true;
	}
}
