package Programmers.정렬;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 미완료
public class 가장큰수 {
	// Input/Output Sample
	// [6, 10, 2]	"6210"
	// [3, 30, 34, 5, 9]	"9534330"
	public static void main(String[] args) {
		int[] arr = {3, 30, 34, 5, 9};

		System.out.println(mySolution(arr));
	}

	public static String mySolution(int[] arr) {
		List<El> list = new ArrayList<>();
		for (int a: arr) {
			list.add(new El(a));
		}
		Collections.sort(list);
		// 맨 앞자리로 구분, 그 다음 자리로 구분
		StringBuilder sb = new StringBuilder();

		for (El e: list) {
			sb.append(e.getV());
		}

		return sb.toString();
	}
	private static class El implements Comparable{
		private String s;

		El (int s) {
			this.s = s+"";
		}

		public String getV() {
			return s;
		}

		@Override
		public int compareTo(Object o) {
			El node = (El) o;
			String v = node.getV();
			int l = 0;

			if(v.length() > this.s.length()) {
				l = this.s.length();
			} else {
				l = v.length();
			}

			for (int i = 0; i < l; i++) {
				if (v.charAt(i) > this.s.charAt(i)) {
					return 1;
				} else if(v.charAt(i) < this.s.charAt(i)){
					return -1;
				}
			}
			// 3 34
			// v.charAt(v.length()-1)
			if (l==v.length()) return 1;
			return -1;
		}
	}
}

