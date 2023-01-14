package org.programmers.before;

public class DartGame {
	public int solution(String dartResult) {

		int answer = 0;
		int [] scoreArr = new int[3];
		String [] spArr = new String[3];

		String[] str = dartResult.split("");

		String tempScore = "";
		int arrCount = 0;

		for(int i=0; i< str.length; i++) {
			if(str[i].equals("S")) { // 그대로
				scoreArr[arrCount] = Integer.parseInt(tempScore);
				tempScore = "";
				arrCount ++;
			} else if (str[i].equals("D")) { // 2제곱
				scoreArr[arrCount] = Integer.parseInt(tempScore) * Integer.parseInt(tempScore);
				tempScore = "";
				arrCount ++;
			} else if (str[i].equals("T")) { // 3제곱
				scoreArr[arrCount] = Integer.parseInt(tempScore) * Integer.parseInt(tempScore) * Integer.parseInt(tempScore);
				tempScore = "";
				arrCount ++;
			} else if(str[i].equals("*")){
				scoreArr[arrCount -1] *= 2;
				if((arrCount-1) != 0) {
					scoreArr[arrCount -2] *= 2;
				}
			} else if(str[i].equals("#")) {
				scoreArr[arrCount -1] *= -1;
			} else {
				tempScore += str[i];
			}
		}

		// 총합
		for(int i=0; i<scoreArr.length; i++) {
			answer += scoreArr[i];
		}

		return answer;
	}

	public int mgSolution(String dartResult) {
		int answer = 0;
		int game = 3;
		int[] dart = new int[game];

		int point = 0;

		for (int i = 0; i < dartResult.length(); i++) {

			if (Character.isDigit(dartResult.charAt(i))) {
				if (i + 1 < dartResult.length() && Character.isDigit(dartResult.charAt(i + 1))) {
					dart[point] = 10;
					point++;
					i++;
					continue;
				}
				dart[point] = dartResult.charAt(i) - '0';
				point++;
			}

			if (dartResult.charAt(i) == 'D') {
				dart[point - 1] = (int) Math.pow(dart[point - 1], 2);
			}

			if (dartResult.charAt(i) == 'T') {
				dart[point - 1] = (int) Math.pow(dart[point - 1], 3);
			}

			if (dartResult.charAt(i) == '*') {
				dart[point - 1] *= 2;
				if (point - 2 >= 0) {
					dart[point - 2] *= 2;
				}
			}

			if (dartResult.charAt(i) == '#') {
				dart[point - 1] *= -1;
			}
		}

		for (int i = 0; i < game; i++) {
			answer += dart[i];
		}

		return answer;
	}
}
