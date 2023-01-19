package Ecote.part7;

import java.util.Arrays;
import java.util.Scanner;

public class 떡볶이떡만들기 {

	public static void main(String[] args) {
		int[] arr = {19,15,10,17};

		System.out.println(mySolution(4,6, arr));
	}

	// 80m (Success)
	// 입력: 떡의 개수(int n), 요청한 떡의 길이(int m), 떡의 개별 높이 배열(int[] arr)
	// 출력: 절단기의 최대 높이(int h)
	// 풀이 방향: 떡의 개별 높이를 정렬한다. h의 크기를 이진 탐색을 통해 탐색.
	// 시간 복잡도: O((N+2)logN)
	public static int mySolution(int n, int m, int[] arr){
		// 높이 정렬
		Arrays.sort(arr);

		// m = target
		int h = 1000000000;
		int ifH = h/2;

		while (ifH < h) { // 종료 조건
			int result = calcH(arr, ifH);

			if (result == m) return ifH; // result가 h와 동일하면, 현재 ifH가 최대 크기임이 분명
			else if (result < m) { // result가 m보다 작으면, h를 절반 늘리기 줄이기
				h = ifH;
				ifH = h/2;
			} else {
				ifH = (ifH+h)/2+1; // 종료 조건 근처일 경우
			}
		}

		return h;
	}

	// h에 따른 떡 길이 리턴
	public static int calcH(int[] arr, int h) {
		int total = 0;
		for (int i = arr.length-1; i >= 0; i--) {
			if ((arr[i] - h) < 0) break; // 자른 떡 길이가 0보다 작으면 break
			total += (arr[i] - h);
		}
		return total;
	}


	// 보고 느낀 점: 시간은 좀 더 걸릴지도 모르지만, 훨씬 깔끔하고 빠르게 답을 내릴 수 있는 답변인것 같다.(작성자 입장에서)
	//			   이진 탐색을 이렇게 자연스럽게 활용해야 할텐데..ㅠㅠ 반복문 종료 조건 때문에 더 복잡하게 생각했었던 것 같다.
	//			   다른 문제도 풀어봐야지!
	public static void bookSolution() {
		Scanner sc = new Scanner(System.in);

		// 떡의 개수(N)와 요청한 떡의 길이(M)
		int n = sc.nextInt();
		int m = sc.nextInt();

		// 각 떡의 개별 높이 정보
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		// 이진 탐색을 위한 시작점과 끝점 설정
		int start = 0;
		int end = (int) 1e9;
		// 이진 탐색 수행 (반복적)
		int result = 0;
		while (start <= end) {
			long total = 0;
			int mid = (start + end) / 2;
			for (int i = 0; i < n; i++) {
				// 잘랐을 때의 떡의 양 계산
				if (arr[i] > mid) total += arr[i] - mid;
			}
			if (total < m) { // 떡의 양이 부족한 경우 더 많이 자르기(왼쪽 부분 탐색)
				end = mid - 1;
			}
			else { // 떡의 양이 충분한 경우 덜 자르기(오른쪽 부분 탐색)
				result = mid; // 최대한 덜 잘랐을 때가 정답이므로, 여기에서 result에 기록
				start = mid + 1;
			}
		}

		System.out.println(result);
	}
}
