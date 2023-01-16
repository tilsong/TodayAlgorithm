package Programmers.before;

import java.util.Arrays;

public class FruitSales {
	// k: 4 m: 3 score: [4, 1, 2, 2, 4, 4, 4, 4, 1, 2, 4, 2]  answer 33
	public int solution(int k, int m, int[] score) {
		int answer = 0;

		Arrays.sort(score); // 오름차 순
							// [1, 1, 2, 2, 2, 2, 4, 4, 4, 4, 4, 4]
		for (int i=score.length-m; i>=0; i=i-m) {
			answer += score[i]*m;
		}

		return answer;
	}
}
