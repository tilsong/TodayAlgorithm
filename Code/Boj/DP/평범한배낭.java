package Boj.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 평범한배낭 {
	// Input/Output Sample
	// 5 10
	// 9 5
	// 4 8
	// 8 9
	// 7 8
	// 5 3
	//
	// 11
	// 80m (Fail -> Success)
	// 입력: 첫 줄에 물품의 수 N(1 ≤ N ≤ 100)과 준서가 버틸 수 있는 무게 K(1 ≤ K ≤ 100,000)가 주어진다. 두 번째 줄부터 N개의 줄에 거쳐 각 물건의 무게 W(1 ≤ W ≤ 100,000)와 해당 물건의 가치 V(0 ≤ V ≤ 1,000)가 주어진다.
	// 입력으로 주어지는 모든 수는 정수이다.
	// 출력: 한 줄에 배낭에 넣을 수 있는 물건들의 가치합의 최댓값을 출력한다.
	// 풀이 방향: 배낭 문제 스탠다드. 매우매우매우 어렵게 느껴졌다. 처음에 로직을 이해하는 데 힘이 들었다.
	//			 만약 다들 어렵다고 한 문제가 아니었다면 코테 그만하고 싶었을 것이다.
	//			 아무튼 핵심은, 모든 상황을 점화식으로 풀어내 TOP-DOWN, BOTTOM-UP 방식으로 접근할 수 있어야 한다는 것이고,
	//			 이를 코드로 구현할 수도 있어야 한다는 것이다. 그치만 쉽지는 않다..ㅎㅎ
	//			 배낭 문제는 이러한 조건들을 순차적으로 만들어내고, 각 조건에 대한 최선의 결과 값을 항상 DP배열에 넣을 수 있어야 한다는 교훈을 준다.
	// 시간 복잡도: O(N*K), 최대 1000만
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int itemNum = Integer.parseInt(st.nextToken());
		int possibleWeight = Integer.parseInt(st.nextToken());
		int[][] items = new int [itemNum+1][2];

		for (int i = 1; i <= itemNum; i++) {
			st = new StringTokenizer(br.readLine());
			items[i][0] = Integer.parseInt(st.nextToken()); // 0 -> w
			items[i][1] = Integer.parseInt(st.nextToken()); // 1 -> v
		}

		// itemNum개를 넣었을 때, possibleWeight가 총 무게였을 때, 최대 value 값
		int[][] maxV = new int[itemNum+1][possibleWeight+1];

		int maxValue = 0;
		for (int itemCount = 1; itemCount <= itemNum; itemCount++) {
			for (int weight = 0; weight <= possibleWeight; weight++) {
				if (items[itemCount][0] > weight) {
					maxV[itemCount][weight] = maxV[itemCount-1][weight];
				} else {
					maxV[itemCount][weight]
						= Math.max(
							maxV[itemCount-1][weight],
							maxV[itemCount-1][weight - items[itemCount][0]] + items[itemCount][1]
						);
				}
				maxValue = Math.max(maxValue, maxV[itemCount][weight]);
			}
		}

		System.out.println(maxValue);
	}
}
