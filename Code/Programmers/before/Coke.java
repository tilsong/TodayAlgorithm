package Programmers.before;

public class Coke {
	public int solution(int a, int b, int n) {
		int answer = 0;

		while(n>=a) {
		  	int getCoke = n/a*b;
			n = n%a;
			n += getCoke;
			answer += getCoke;
		}

		return answer;
	}
}
