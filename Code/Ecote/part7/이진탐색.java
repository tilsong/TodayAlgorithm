package Ecote.part7;

import java.util.Arrays;

public class 이진탐색 {
	public static void main(String[] args) {
		int [] arr = {1,4,7,2,4,11,6,34};
		Arrays.sort(arr); // 정렬 전제

		System.out.println(recursiveBinarySearch(arr, 11, 0, arr.length-1));
		System.out.println(iterBinarySearch(arr, 11, 0, arr.length-1));
	}

	public static int recursiveBinarySearch(int[] arr, int target, int start, int end) {
		// 재귀 종료 조건 - target이 없을 때
		if(start > end) return -1;

		int mid = (start + end)/2;

		if (arr[mid] == target) return mid;
		else if (arr[mid] < target) return recursiveBinarySearch(arr, target, mid+1, end);
		else return recursiveBinarySearch(arr, target, start, mid -1);
	}

	public static int iterBinarySearch(int[] arr, int target, int start, int end) {
		while (start <= end) {
			int mid = (start + end)/2;

			if (arr[mid] == target) return mid;
			else if (arr[mid] <= target) start = mid + 1;
			else end = mid -1;
		}

		return -1;// 없을 경우
	}
}
