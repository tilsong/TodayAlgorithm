package Boj.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 통나무건너뛰기 {

	// 60m fail
	// 입력: 각 통나무 테스트
	// 출력: 난이도 min
	// 풀이 방향: 정렬하고, 가장 큰 수와 어떤 수를 교체할 때 난이도의 min 값을 찾을 수 있을 것이라고 생각했다.
	//			근데 안 나옴..ㅠㅠ 다시 풀 것!
	// 시간 복잡도:
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken()); // l번째 테스트 케이스
			arr[i] = new int[l];

			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < l; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()); // 각 난이도 입력 받기
			}
			Arrays.sort(arr[i]); // 오름차순 정렬
		}

		for (int i = 0; i < arr.length; i++) {
			System.out.println(findMin(arr[i]));
		}
	}

	public static int findMin(int[] arr) {
		int min = arr[arr.length-1] - arr[0];
		int l = arr.length;

		for (int i = 1; i <= arr.length -2; i++) { // arr[l-1-i] => 교체할 원소
			if (arr[l-1] - arr[l-2-i] < min && arr[l-1-i] - arr[0] < min && arr[l-2] - arr[l-1-i] < min) {
				if (arr[l-1] - arr[l-2-i] < arr[l-1-i] - arr[0]) {
					min = arr[l-1-i] - arr[0];
				} else {
					min = arr[l-1] - arr[l-2-i];
				}

				if (arr[l-2] - arr[l-1-i] > min) {
					min = arr[l-2] - arr[l-1-i];
				}
			}
		}

		return min;
	}
}
