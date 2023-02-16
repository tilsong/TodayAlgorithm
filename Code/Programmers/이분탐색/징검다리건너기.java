package Programmers.이분탐색;

public class 징검다리건너기 {
	public static void main(String[] args) {
		// Sample Input/Output
		// [2, 4, 5, 3, 2, 1, 4, 2, 5, 1]	3	3
		int[] arr = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};

		solution(arr, 3);
	}

	// 25m (Success)
	// 입력: 각 징검다리의 디딜 수 있는 수 배열(int[] stones), 최대 점프 가능 거리(int k)
	// 출력: 징검다리를 건널 수 있는 사람의 최대 수
	// 풀이 방향: 공유기 설치 문제랑 거의 똑같다. 징검다리를 디딜 수 있는 수 배열이 입력으로 제공되는데, 이는 1~2억의 거리를 가진다.
	//			 따라서 해당 구간을 이분 탐색하면서, 특정 수로 디뎠을 때 0이되는 징검다리의 연속이 k 이상이면 실패로 간주했다.
	//			 성공할 수 있는 최대 mid 값을 구하면 된다.
	// 시간 복잡도: O(N*logL) N은 징검다리 수(최대 20만), L은 각 디딤돌의 최대 디딜 수 있는 수(최대 2억)
	public static int solution(int[] stones, int k) {
		int answer = 0;
		int start = 0;
		int end = (int) 2e9;

		while (start <= end) {
			int mid = (start + end) / 2;

			int count = checkStones(stones, mid, k);

			if (count < k) {
				start = mid + 1;
				answer = mid;
			} else {
				end = mid -1;
			}
		}
		return answer+1;
	}

	static int checkStones(int[] stones, int mid, int k) {
		int count = 0;
		int maxCount = 0;

		for (int i = 0; i < stones.length; i++) {
			if (stones[i] - mid <= 0) {
				count ++;
				if (count > maxCount) {
					maxCount = count;
				}
			} else {
				count = 0;
			}
		}

		return maxCount;
	}

}
