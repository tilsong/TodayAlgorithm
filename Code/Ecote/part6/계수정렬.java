package Ecote.part6;

public class 계수정렬 {
	public static void main(String[] args) {
		int[] arr = {7, 5, 9, 0, 3, 1, 6, 2, 9, 1, 4, 8, 0, 5, 2};
		countSort(arr);
	}

	public static void countSort(int[] arr) {
		int [] newA = new int[10];

		for (int i = 0; i < arr.length; i++) {
			newA[arr[i]] ++;
		}

		String result = "";
		for (int i = 0; i < newA.length; i++) {
			for (int j = 0; j < newA[i]; j++) {
				result += (i+"");
			}
		}

		System.out.println(result);
	}


}
