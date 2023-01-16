package Programmers.before;

public class PartialString {
	// t: "3141592",	p: "271"	answer: 2
	public int solution(String t, String p) {
		int answer = 0;

		int pSize = p.length();
		int pInt = Integer.parseInt(p);

		for(int i=0; i<t.length()-pSize; i++) {
			String temp = t.substring(i, pSize);

			if(Integer.parseInt(temp) <= pInt) {
				answer ++;
			}
		}

		return answer;
	}
}
