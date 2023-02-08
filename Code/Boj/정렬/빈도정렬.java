package Boj.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class 빈도정렬 {
	// Sample Input/Output
	// 9 3
	// 1 3 3 3 2 2 2 1 1
	//
	// 1 1 1 3 3 3 2 2 2

	// 40m (Success)
	// 입력: 첫째 줄에 메시지의 길이 N과 C가 주어진다. (1 ≤ N ≤ 1,000, 1 ≤ C ≤ 1,000,000,000)
	// 		둘째 줄에 메시지 수열이 주어진다.
	// 출력: 첫째 줄에 입력으로 주어진 수열을 빈도 정렬한 다음 출력한다.
	// 풀이 방향: LinkedHashMap을 사용해서 나열된 순서와 각 숫자별 개수를 파악하고, 이를 List로 옮겨서 Sorting 했다.
	// 시간 복잡도: O(N+nlog(n))
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		Map<Integer, Integer> map = new LinkedHashMap<>();

		for (int i = 0; i < n; i++) {
			int now = Integer.parseInt(st.nextToken());

			int nowValue = map.getOrDefault(now, 0);
			if(nowValue == 0) {
				map.put(now, 1);
			} else {
				map.put(now, ++nowValue);
			}
		}

		List<Numbers> list = new ArrayList<>(map.size());
		int seq = 0;

		for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
			int key = entry.getKey(); // 수 자체
			int value = entry.getValue(); // 수의 개수
			list.add(new Numbers(key, value, seq++));
		}

		Collections.sort(list, (o1, o2) -> {
			// 1. 수의 개수가 많으면
			// 2. 순서가 앞이면
			if (o1.value == o2.value) {
				return o1.seq - o2.seq;
			}
			return o2.value - o1.value;
		});

		for (Numbers num : list) {
			for (int i = 0; i < num.value; i++) {
				System.out.print(num.key + " ");
			}
		}
	}

	private static class Numbers {
		int key;
		int value;
		int seq;

		public Numbers(int key, int value, int seq) {
			this.key = key;
			this.value = value;
			this.seq = seq;
		}
	}
}
