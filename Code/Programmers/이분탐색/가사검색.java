package Programmers.이분탐색;

import java.util.Arrays;
import java.util.HashMap;

public class 가사검색 {
	public static void main(String[] args) {
		// words	queries	result
		// ["frodo", "front", "frost", "frozen", "frame", "kakao"]	["fro??", "????o", "fr???", "fro???", "pro?"]
		// [3, 2, 4, 1, 0]
		String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
		String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
		int[] s = solution(words, queries);
		for (int i = 0; i < s.length; i++) {
			System.out.print(s[i] + " ");
		}
	}

	public static int[] solution(String[] words, String[] queries) {
		int[] answer = new int[queries.length];

		int max = 10000;
		int[] lengthArr = new int[max+1];
		Arrays.sort(words); // 소문자 순서로 배열 정렬

		String[] revWords = new String[words.length];
		StringBuilder sb;
		for (int i = 0; i < revWords.length; i++) {
			lengthArr[words[i].length()]++;
			sb = new StringBuilder(words[i]);
			revWords[i] = sb.reverse().toString();
		}
		Arrays.sort(revWords); // 문자열이 뒤집어진 상태로 배열 정렬

		for (int i = 0; i < queries.length; i++) {
			int min = (int) 1e6;
			int maxx = 0;

			// 와일드 카드가 뒤에 있으면
			if (queries[i].endsWith("?")) {
				// 와일드 카드가 앞과 뒤 모두에 있으면
				if (queries[i].startsWith("?")) {
					int length = queries.length;
					answer[i] = lengthArr[length];
					continue;
				}

				String front = "";
				for (int j = 0; j < queries[i].length(); j++) {
					if (queries[i].charAt(j) == '?') {
						front = queries[i].substring(0, j-1);
						break;
					}
				}

				int start = 0;
				int end = words.length-1;
				int ans = -1;

				// min
				while(start <= end) {
					int mid = (start+end) / 2;
					int cp = front.compareTo(words[mid]);
					if (cp <= 0) {
						if (queries[i].length() == words[mid].length()) {
							end = mid - 1;
							ans = mid;
						} else {
							start = mid + 1;
						}
					} else {
						start = mid + 1;
					}
				}

				if (ans == -1) {
					answer[i] = 0;
					continue;
				}
				min = ans;

				start = min;
				end = words.length-1;
				// max
				while(start <= end) {
					int mid = (start+end) / 2;
					int cp = front.compareTo(words[mid]);
					if (cp <= 0) {
						if (queries[i].length() == words[mid].length()) {
							start = mid + 1;
							ans = mid;
						} else {
							end = mid - 1;
						}
					} else {
						end = mid -1;
					}
				}
				maxx = ans;

				answer[i] = maxx - min + 1;
			} else if (queries[i].startsWith("?")) {// 와일드 카드가 뒤에만 있으면
				String front = "";
				StringBuilder s = new StringBuilder(queries[i]);
				queries[i] = s.reverse().toString();

				for (int j = 0; j < queries[i].length(); j++) {
					if (queries[i].charAt(j) == '?') {
						front = queries[i].substring(0, j);
						break;
					}
				}

				int start = 0;
				int end = revWords.length;
				int ans = -1;

				// min
				while(start <= end) {
					int mid = (start+end) / 2;
					int cp = front.compareTo(revWords[mid]);
					if (cp <= 0) {
						if (queries[i].length() == revWords[mid].length()) {
							end = mid - 1;
							ans = mid;
						} else {
							start = mid + 1;
						}
					} else {
						start = mid + 1;
					}
				}

				if (ans == -1) {
					answer[i] = 0;
					continue;
				}
				min = ans;

				start = min;
				end = words.length-1;

				// max
				while(start <= end) {
					int mid = (start+end) / 2;
					int cp = front.compareTo(revWords[mid]);
					if (cp <= 0) {
						if (queries[i].length() == revWords[mid].length()) {
							start = mid + 1;
							ans = mid;
						} else {
							end = mid - 1;
						}
					} else {
						end = mid - 1;
					}
				}
				maxx = ans;

				answer[i] = maxx - min + 1;
			}
		}

		return answer;
	}
}
