package Ecote.part6;

public class 퀵정렬 {
	public static void main(String[] args) {
		String result = "";

		int [] arr = {7,5,9,0,3,1,6,2,4,8};

		quickSort(arr, 0, arr.length-1);

		for (int i = 0; i < arr.length; i++) {
			result += arr[i];
		}
		System.out.println("퀵 정렬: " + result);
	}

	public static void quickSort(int[] arr, int start, int end) {
		if (start >= end) return; // 재귀의 종료 조건: 현재 리스트의 데이터가 1개일 경우
		// pivot -> 중심점, 기준
		int pivot = start;
		int left = start + 1;
		int right = end;

		while (left <= right) {
			// 피벗보다 큰 데이터를 찾을 때까지 반복
			while (left <= end && arr[left] <= arr[pivot]) left++;
			// 피벗보다 작은 데이터를 찾을 때까지 반복
			while (right > start && arr[right] >= arr[pivot]) right--;

			// 엇갈렸다면 피벗과 작은 데이터 교체
			if (left > right) {
				int temp = arr[pivot];
				arr[pivot] = arr[right];
				arr[right] = temp;
			}
			// 엇갈리지 않았다면 큰 데이터와 작은 데이터를 교체
			else {
				int temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;
			}
		}

		quickSort(arr, start, right -1);
		quickSort(arr, right +1, end);
	}
}
