package Programmers.이분탐색;

import java.util.Arrays;

public class 입국심사 {
	public static void main(String[] args) {
		// Sample Input/Output
		// 입력값 〉	6, [7, 10]
		// 기댓값 〉	28
		int[] arr= {7, 10};
		solution(6, arr);
	}

	// 40m (Success)
	// 입력: 심사를 기다리는 사람의 수
	// 출력: 각 심사위원들의 속도 배열
	// 풀이 방향: 각 시간 별로 심사위원들의 심사 가능 시간을 구하고, n 이상을 만족하는 최소 심사 시간을 구한다.
	// 시간 복잡도: O(N * logN)
	public static long solution(int n, int[] times) {
		long answer = (long) 1e17;

		Arrays.sort(times);

		long start = 0;
		long end = answer; // 최악의 경우

		while (start <= end) {
			long mid = (start + end) / 2;

			long judges = check(times, mid);

			if (judges < n) {
				start = mid + 1;
			} else {
				answer = mid;
				end = mid -1;
			}
		}

		return answer;
	}

	static long check(int[] times, long mid) {
		long count = 0;

		for (int i = 0; i < times.length; i++) {
			count += mid / times[i];
		}

		return count;
	}

}
