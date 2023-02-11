package Boj.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class 개똥벌레 {
	// Sample Input/Output
	// 6 7
	// 1
	// 5
	// 3
	// 3
	// 5
	// 1

	// 70m (Fail -> Success)
	// 입력: 첫째 줄에 N과 H가 주어진다. N은 항상 짝수이다. (2 ≤ N ≤ 200,000, 2 ≤ H ≤ 500,000)
	// 		다음 N개 줄에는 장애물의 크기가 순서대로 주어진다. 장애물의 크기는 H보다 작은 양수이다.
	// 출력: 첫째 줄에 개똥벌레가 파괴해야 하는 장애물의 최솟값과 그러한 구간의 수를 공백으로 구분하여 출력한다.
	// 풀이 방향: 처음 풀이 방향은 이분탐색을 통해 최소 충돌 구간을 찾고, 그 구간을 만족하는 최소, 최대의 값을 찾아내는 방향이었다.
	//			각 구간의 충돌 여부를 파악하는 배열을 활용할 생각은 h가 20만 이하이므로 생각하지 못했었다. 이분 탐색만으로 접근하려고 했었따..
	//			근데 다시 생각해보니까 어차피 종유석과 석순 배열도 n/2이기 때문에, h보다 크다. ㅋ..
	//			아무튼 이 문제의 핵심은 누적합을 고려할 수 있어야 한다는 것이었다.
	//			각 구간에 해당하는 충돌 수는 누적합을 통해 쉽게 구할 수 있기 때문이다.
	//			그리고 종유석과 석순의 방향이 다른 데 그것을 고려해야 하고, 최소 충돌 구간을 카운트 하는 방식을 생각해내는 것 정도가
	//			주요 포인트였다고 생각한다. 
	// 시간 복잡도: O(2h), h는 최대 20만

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());

		int[] s = new int[h + 2];
		int[] j = new int[h + 2];

		for (int i = 0; i < n/2; i++) {
			st = new StringTokenizer(br.readLine());
			s[Integer.parseInt(st.nextToken())]++;  // 1~n
			st = new StringTokenizer(br.readLine());
			j[Integer.parseInt(st.nextToken())]++;
		}

		// 누적합 처리
		for (int i = h; i >= 2; i--) { // O(n)
			s[i-1] += s[i];
			j[i-1] += j[i];
		}

		int minCrash = n;
		int count = 0;
		for (int i = 1; i <= h; i++) {
			// i일 때 crash 수
			int curCrash = s[i] + j[h-i+1];

			if (curCrash < minCrash) {
				minCrash = curCrash;
				count = 1;
			} else if (curCrash == minCrash) {
				count ++;
			}
		}

		System.out.println(minCrash + " " + count);
	}
}

// first try
//public static void main(String[] args) throws IOException {
// 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// 		StringTokenizer st = new StringTokenizer(br.readLine());
//
// 		n = Integer.parseInt(st.nextToken());
// 		h = Integer.parseInt(st.nextToken());
//
// 		tempMid = n/2;
// 		s = new int[tempMid];
// 		j = new int[tempMid];
// 		for (int i = 0; i < tempMid; i++) {
// 			st = new StringTokenizer(br.readLine());
// 			s[i] = Integer.parseInt(st.nextToken());
// 			st = new StringTokenizer(br.readLine());
// 			j[i] = Integer.parseInt(st.nextToken());
// 		}
//
// 		Arrays.sort(s);
// 		Arrays.sort(j);
//
// 		// 최대 크기를 가지는 h를 찾는 이분 탐색
// 		int minCrash = n;
// 		int maxH = 0; // 최소 충돌을 갖는 최대 구간의 높이
// 		int start = 1;
// 		int end = h;
// 		while(start <= end) {
// 			int mid = (start + end) / 2;
// 			int tempCrashCounter = crashCounter(mid);
//
// 			if (tempCrashCounter < minCrash) {
// 				minCrash = tempCrashCounter;
// 				maxH = mid;
// 				start = mid + 1;
// 			} else {
// 				end = mid - 1;
// 			}
// 		}
//
// 		start = 1;
// 		end = maxH;
// 		int minH = h;
// 		while (start <= end) {
// 			int mid = (start + end) / 2;
// 			int tempCrashCounter = crashCounter(mid);
//
// 			if (tempCrashCounter <= minCrash) {
// 				end = mid - 1;
// 				minH = mid;
// 			} else {
// 				start = mid + 1;
// 			}
// 		}
//
// 		System.out.println(minCrash + " " + (maxH-minH));
// 	}
//
// 	static int crashCounter(int mid) {
// 		int count = 0;
//
// 		for (int i = 0; i < tempMid; i++) {
// 			if (s[i] - mid >= 0) {
// 				count++;
// 			}
// 			if ((h - j[i]) <= mid) {
// 				count++;
// 			}
// 		}
//
// 		return count;
// 	}