package Boj.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 회의실배정 {

	public static Time[] timeArr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		timeArr = new Time[n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			int startTime = Integer.parseInt(st.nextToken());
			int endTime = Integer.parseInt(st.nextToken());
			timeArr[i] = new Time(startTime, endTime);
		}

		Arrays.sort(timeArr,(o1, o2) -> {
			if (o1.end == o2.end) {
				return o1.start - o2.start;
			}
			return o1.end - o2.end;
		});

		int count = 1;
		int temp = timeArr[0].end;
		for (int i = 1; i < timeArr.length; i++) {
			if (temp <= timeArr[i].start) {
				count++;
				temp = timeArr[i].end;
			}
		}
		System.out.println(count);
	}

	private static class Time {
		int start;
		int end;

		public Time(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
}