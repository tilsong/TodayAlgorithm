package Boj.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 국영수 {
	static int n = 0;
	static Score[] scores;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		scores = new Score[n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int korean = Integer.parseInt(st.nextToken());
			int english = Integer.parseInt(st.nextToken());
			int math = Integer.parseInt(st.nextToken());
			scores[i] = new Score(name, korean, english, math);
		}

		Arrays.sort(scores, (o1, o2) -> {
			// 국어 점수 - 내림차 순
			if (o1.korean < o2.korean) {
				return 1;
			} else if (o1.korean > o2.korean) {
				return -1;
			}
			// 영어 점수 순 - 오름차 순
			if (o1.english > o2.english) {
				return 1;
			} else if (o1.english < o2.english) {
				return -1;
			}
			// 수학 점수 - 내림차 순
			if (o1.math < o2.math) {
				return 1;
			} else if (o1.math > o2.math) {
				return -1;
			}
			// 사전 순
			// 모든 점수가 같으면 이름이 사전 순으로 증가하는 순서로
			// (단, 아스키 코드에서 대문자는 소문자보다 작으므로 사전순으로 앞에 온다.)
			int length = 0;
			if (o1.name.length() < o2.name.length()) {
				length = o1.name.length();
			} else {
				length = o2.name.length();
			}

			for (int i = 0; i < length; i++) {
				if (o1.name.charAt(i) < o2.name.charAt(i)) {
					return -1;
				} else if (o1.name.charAt(i) > o2.name.charAt(i)) {
					return 1;
				}
			}
			// 끝까지 같으면 더 짧은 쪽이 앞
			if (o1.name.length() < o2.name.length()) {
				return 1;
			}
			return -1;

		});

		for (Score score: scores) {
			System.out.println(score.name);
		}
	}

	private static class Score{
		public String name;
		public int korean;
		public int english;
		public int math;

		 public Score(String name, int korean, int english, int math){
			 this.name = name;
			 this.korean = korean;
			 this.english = english;
			 this.math = math;
		 }
	}



}
