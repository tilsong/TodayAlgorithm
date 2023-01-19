package Ecote.part6;

public class 삽입정렬 {
	public static void main(String[] args) {
		int [] arr = {7,5,9,0,3,1,6,2,4,8};

		insertSort(arr);

		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

	public static void insertSort(int[] arr) {
		// 맨 앞의 요소는 정렬되어 있다고 가정.
		// 정렬된 요소는 모두 오름차순의 형태.(정렬 중이더라도)
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0; j--) {
				// swap
				if (arr[j] < arr[j-1]) {
					int temp = arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = temp;
				} else break; // 정렬된 앞 보다 클 경우
			}
		}
	}

}
