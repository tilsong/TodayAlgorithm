package Boj.정렬;

public class DualPivotQuickSort {
	public static void main(String[] args) {
		int[] arr = {3,2,6,1,7,2,3,65,10};
		sort(arr,0, arr.length-1);

		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	// 값 바꾸기
	static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	static void sort(int[] arr, int left, int right) {
		if (left > right) return;

		if (arr[left] > arr[right]) {
			swap(arr, left, right);
		}

		int lp = arr[left];
		int rp = arr[right];

		int f = left + 1;
		int now = left + 1;
		int b = right - 1;

		while(now <= b) {
			if (arr[now] < lp) {
				swap(arr, f, now);
				f++;
			} else if (arr[now] >= rp) {
				while(arr[b] > rp && now < b) b--;

				swap(arr, now, b);

				if (arr[now] < lp) {
					swap(arr, f, now);
					f++;
				}
				b--;
			}

			now ++;
		}

		f--;
		b++;

		swap(arr, left, f);
		swap(arr, b, right);

		sort(arr, left, f-1);
		sort(arr, f+1, b-1);
		sort(arr, b+1, right);
	}
}
