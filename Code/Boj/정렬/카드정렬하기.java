package Boj.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 카드정렬하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());

		Queue<Integer> q = new PriorityQueue<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			q.offer(Integer.parseInt(st.nextToken()));
		}

		if (n == 1) {
			System.out.println(0);
			return;
		} else if (n == 2){
			System.out.println(q.poll()+ q.poll());
			return;
		}

		int total = 0;
		// 3개 이상의 element
		while (q.size() > 1) {
			int one = q.poll();
			int two = q.poll();
			int changeCost = one + two;
			total += changeCost;
			q.offer(changeCost);
		}

		System.out.println(total);
	}
}
