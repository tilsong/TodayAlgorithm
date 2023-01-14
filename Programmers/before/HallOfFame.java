package org.programmers.before;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class HallOfFame {
	// K: 4	score: [0, 300, 40, 300, 20, 70, 150, 50, 500, 1000]
	// answer: [0, 0, 0, 0, 20, 40, 70, 70, 150, 300]
	public int[] solution(int k, int[] score) {
		int[] answer = new int[score.length];

		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < score.length; i++) {
			list.add(score[i]);
			Collections.sort(list);
			if (list.size() > k) {
				list.remove(0);
			}
			answer[i] = list.get(0);
		}

		return answer;
	}
}
