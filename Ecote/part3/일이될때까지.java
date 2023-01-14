package part3;

import java.util.Scanner;

public class 일이될때까지 {
	// public static void main(String[] args) {
	// 	System.out.println(mySolution2(25, 5));
	// }

	// 10m(Success)
	// 입력: 자연수 2개(long n, long k),  출력: n이 1이 되는 연산 반복 횟수
	// 풀이 방향: 그리디.
	// 			n이 k로 나누어지면 나누고, 그렇지 않을 때는 1로 빼는 연산을 한다.
	// 시간 복잡도:
	public static int mySolution(long n, long m) {
		int count = 0;

		while(n != 1) {
			if (n % m == 0) {
				n /= m;
			} else {
				n -= 1;
			}
			count ++;
		}
		return count;
	}

	// 개선 코드
	// 개선 방향 - 뺄셈 연산의 횟수를 줄임
	public static int mySolution2(long n, long m) {
		int count = 0;

		while (n != 1) {
			if(n%m != 0) {
				count += n%m; // 뺄셈 횟수 count
			} else { // 나눗셈
				n = n/m;
				count ++;
			}
		}

		return count;
	}

	// 풀이 방향: 동일. 이 풀이는 내가 더 잘한듯?ㅎㅎ
	// 시간 복잡도: 의미 없음.
	public static void bookSolution() {
		Scanner sc = new Scanner(System.in);

		// N, K를 공백을 기준으로 구분하여 입력 받기
		int n = sc.nextInt();
		int k = sc.nextInt();
		int result = 0;

		while (true) {
			// N이 K로 나누어 떨어지는 수가 될 때까지만 1씩 빼기
			int target = (n / k) * k;
			result += (n - target);
			n = target;
			// N이 K보다 작을 때 (더 이상 나눌 수 없을 때) 반복문 탈출
			if (n < k) break;
			// K로 나누기
			result += 1;
			n /= k;
		}

		// 마지막으로 남은 수에 대하여 1씩 빼기
		result += (n - 1);
		System.out.println(result);
	}
}
