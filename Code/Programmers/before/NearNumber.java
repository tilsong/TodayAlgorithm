package Programmers.before;

public class NearNumber {
	// s: "banana"  answer: [-1, -1, -1, 2, 2, 2]
	public int[] solution(String s) {
		int[] answer = new int [s.length()];

		answer[0] = -1;
		for (int i=1; i<s.length(); i++) {
			int ind = s.lastIndexOf(String.valueOf(s.charAt(i)), i-1);
			if (ind == -1) {
				answer[i] = -1;
			} else {
				answer[i] = i-ind;
			}
		}

		return answer;
	}
}
