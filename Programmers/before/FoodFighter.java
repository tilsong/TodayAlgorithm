package org.programmers.before;

public class FoodFighter {
	// [1, 3, 4, 6]	"1223330333221"
	public String solution(int[] food) {
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i<food.length; i++) {
			int oneFoodCount = food[i]/2;
			for(int j = 0; j<oneFoodCount; i++) {
				sb.append(i);
			}
		}

		return sb.toString() + "0" + sb.reverse().toString();
	}
}
