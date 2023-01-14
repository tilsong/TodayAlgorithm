package org.programmers.before;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FailureRate {
	static class Stage {
		int idx;
		double failureRate;

		public Stage(int idx, double failureRate) {
			this.idx = idx;
			this.failureRate = failureRate;
		}
	}

	public int[] solution(int N, int[] stages) {

		int [] answer = new int[N];
		double [] failArr = new double[N];

		int users = stages.length;

		// 1. stage 별 실패율 계산
		for(int i=0; i<N; i++) {
			int failUser = 0;

			// i+1과 동일한 갯수 계산
			for(int j=0; j<stages.length; j++) {
				if(stages[j] == (i+1)) {
					failUser ++;
				}
			}

			failArr[i] = failUser/(users *1.0);

			// 위 갯수를 users에서 빼기
			users -= failUser;
		}

		// 2. 실패율에 따른 스테이지 정렬
		List<Stage> list = new ArrayList<>();
		for(int i=0; i< failArr.length; i++) {
			System.out.println("i: " + i);
			System.out.println("failArr[i]: " + failArr[i]);

			list.add(new Stage(i+1, failArr[i]));
		}

		Collections.sort(list, ((o1, o2) -> Double.compare(o2.failureRate, o1.failureRate)));
		for(int i=0; i< list.size(); i++) {
			answer[i] = list.get(i).idx;
			System.out.println(answer[i]);
		}

		return answer;
	}
}

