package org.programmers.before;

public class Weapon {
	// number: 10,  limit: 3,  power: 2
	// answer: 21
	public int solution(int number, int limit, int power) {

		int answer =0;

		for (int i = 1; i <= number; i++) {
			int count = 0;

			for (int j = 1; j*j <= number; j++) {

				if (j*j == i) {
					 count ++;
				} else if(i%j ==0) {
					count += 2;
				}


			}
			if (count > limit) {
				count = power;
			}
			answer += count;
		}
		return answer;
	}
}
