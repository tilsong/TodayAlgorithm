package Ecote.part6;

public class 위에서아래로 {
	public static void main(String[] args) {
		int [] arr = {1,4,6,78,4,2,7,8,2,1,7,3345,6,2,1,7375,400,44,32,65};

		mySolution(20, arr);
	}

	// 5m (Success)
	// 입력: 수열의 수의 개수(int n), n개의 수 배열(int[] arr)
	// 출력: 정렬된 수 배열
	// 풀이 방향: 수의 범위가 주어졌고(1<=수<=100000) 양의 정수 데이터이므로 계수 정렬 사용해서 풀기!
	// 시간 복잡도: O(n+k) // k는 배열 요소의 최대 값
	public static void mySolution(int n, int[] arr) {
		int[] countArr = new int[100001];

		for (int i = 0; i < arr.length; i++) {
			countArr[arr[i]] ++;
		}

		// 출력
		for (int i = countArr.length-1; i >= 0; i--) {
			for (int j = 0; j < countArr[i]; j++) {
				System.out.print(i + " ");
			}
		}
	}
}
