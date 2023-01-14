package part4;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 시각 {
	// 실행
	public static void main(String[] args) {
		System.out.println(mySolution(5));
	}

	// 20m (Success)
	// 입력: 시 (int n)
	// 출력: 3이 포함된 경우의 수 (int count)
	// 풀이 방향: 분당 포함된 경우의 수를 계산하고, 시간 당 포함된 경우의 수 계산
	// 			그리고 시간에 3이 포함되면 해당 시간은 모든 초단위 포함하여 경우의 수 계산
	//			결국 완전 탐색해야 함
	// 시간 복잡도: O(N*3600)
	public static int mySolution(int n) {
		int count = 0;
		Integer [] arr = {3, 13, 23, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 43, 53};
		List<Integer> arrs = Arrays.asList(arr);

		for (int hour = 0; hour <= n; hour++) {
			for (int min = 0; min < 60; min++) {
				for (int sec = 0; sec < 60; sec++) {
					// hour min sec 중 3이 포함되면 count++
					if (arrs.contains(hour) | arrs.contains(min) | arrs.contains(sec)) {
						count++;
					}
				}
			}
		}

		return count;
	}


	// 풀이 방향: 비슷. 3 포함 여부를 %와 /로 확인. contains 쓰는 것보다는 적은 연산이 들어갈 것 같다.
	//			훨씬 깔끔..
	// 시간 복잡도: O(N*3600)
	public static void bookSolution() {  // check() 포함
		Scanner sc = new Scanner(System.in);

		// H를 입력받기
		int h = sc.nextInt();
		int cnt = 0;

		for (int i = 0; i <= h; i++) {
			for (int j = 0; j < 60; j++) {
				for (int k = 0; k < 60; k++) {
					// 매 시각 안에 '3'이 포함되어 있다면 카운트 증가
					if (check(i, j, k)) cnt++;
				}
			}
		}

		System.out.println(cnt);
	}

	// 특정한 시각 안에 '3'이 포함되어 있는지의 여부
	public static boolean check(int h, int m, int s) {
		if (h % 10 == 3 || m / 10 == 3 || m % 10 == 3 || s / 10 == 3 || s % 10 == 3)
			return true;
		return false;
	}
}
