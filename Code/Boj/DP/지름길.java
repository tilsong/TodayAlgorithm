package Boj.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class 지름길 {
	// Input/Output Sample
	// 5 150
	// 0 50 10
	// 0 50 20
	// 50 100 10
	// 100 151 10
	// 110 140 90
	//
	// 70
	//
	// 60m (Fail -> Success)
	// 입력: 첫째 줄에 지름길의 개수 N과 고속도로의 길이 D가 주어진다. N은 12 이하인 양의 정수이고, D는 10,000보다 작거나 같은 자연수이다.
	// 		다음 N개의 줄에 지름길의 시작 위치, 도착 위치, 지름길의 길이가 주어진다.
	// 		모든 위치와 길이는 10,000보다 작거나 같은 음이 아닌 정수이다. 지름길의 시작 위치는 도착 위치보다 작다.
	// 출력: 세준이가 운전해야하는 거리의 최솟값을 출력하시오.
	// 풀이 방향: 모든 좌표 위의 최단거리를 구한다. 만약 지름길을 통해 최단거리가 수정된다면, 지름길을 선택하여 최단 거리를 갱신한다.
	//			 결국 답 보고 다시 접근해서 풀었다. DP 정말 어렵다.....
	// 시간 복잡도: O(2D), 최대 2만

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		int[] dp = new int[d+1];
		HashMap <Integer, List<int[]>> loads = new HashMap<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			if (!loads.containsKey(end)) {
				loads.put(end, new ArrayList<>());
			}

			loads.get(end).add(new int[]{start, dist});
		}

		for (int i = 1; i <= d; i++) {
			int min = dp[i-1] + 1; // 기본 값 세팅

			if (loads.containsKey(i)) {
				for (int[] arr : loads.get(i)) {
					min = Math.min(min, dp[arr[0]]+arr[1]);
				}
			}
			dp[i] = min;
		}

		System.out.println(dp[d]);
	}
}
