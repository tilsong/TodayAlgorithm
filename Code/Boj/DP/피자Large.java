package Boj.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 피자Large {
	// Input/Output Sample
	// 8
	// 28
	//
	// 60m (Fail -> Success)
	// 입력: 첫 번째 줄에는 피자판의 개수를 의미하는 양의 정수 N(1 ≤ N ≤ 109) 이 주어진다.
	// 출력: 갑이 얻을 수 있는 즐거움의 총합의 최댓값을 한 줄에 출력한다.
	// 풀이 방향: 점화식을 찾아서 다 풀었다고 생각했는데.. 공간 복잡도 때문에 다시 생각해야 했다.
	//			 그래서 재귀로 풀고 하다가.. 결국 질문 게시판에서 내가 세운 점화식이 n*(n-1)/2로 표현될 수 있다는 것을 알게 되었다..
	//			 DP는 정말.. 쉽지 않은 것 같다..
	// 시간 복잡도: 1, 점화식을 사용하면 O(n)이 되지만, 점화식을 사용할 때 필요한 값들을 저장할 공간이 없다.(최대 10억)
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		long answer = n*(n-1)/2;
		System.out.println(answer);
	}

	// static void topDown(int k) {
	// 	if (!map.containsKey(k)) {
	// 		if (k % 2 == 0) {
	// 			int halfK = k / 2;
	// 			topDown(halfK);
	// 			map.put(k, halfK*halfK + map.get(halfK)*2);
	// 		} else {
	// 			int l = k / 2;
	// 			topDown(l);
	// 			topDown(l+1);
	// 			map.put(k, l*(l+1) + map.get(l) + map.get(l+1));
	// 		}
	// 	}
	// }
}
