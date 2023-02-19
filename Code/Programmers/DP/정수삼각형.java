package Programmers.DP;

public class 정수삼각형 {
	public static void main(String[] args) {
		// Sample Input/Output
		// 입력값 〉	[[7], [3, 8], [8, 1, 0], [2, 7, 4, 4], [4, 5, 2, 6, 5]]
		// 기댓값 〉	30
		int[][] arr= {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
		System.out.println(solution(arr));
	}
	// 25m (Success)
	// 풀이 방향: 각 삼각형 요소의 합을 위에서부터 누적합으로 계산한다.
	//			 마지막 줄에 가장 큰 요소가 있을 것이므로, 이를 출력
	// 시간 복잡도: O(n^2)
	public static int solution(int[][] triangle) {
		for (int i = 1; i < triangle.length; i++) {
			triangle[i][0] += triangle[i-1][0];
			triangle[i][triangle[i].length-1] += triangle[i-1][triangle[i-1].length-1];
			for (int j = 1; j < i; j++) {
				triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]);
			}
		}

		int max = 0;
		for (int i = 0; i < triangle.length; i++) {
			if (max < triangle[triangle.length-1][i]) {
				max = triangle[triangle.length-1][i];
			}
		}
		return max;
	}
}
