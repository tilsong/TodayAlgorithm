package Ecote.part4;

import java.util.Scanner;

public class 상하좌우 {
	// public static void main(String[] args) {
	// 	String [] arr = {"R","R","R","U","D","D"};
	// 	int [] a = mySolution(5, arr);
	// 	System.out.println("a[0] = " + a[0]);
	// 	System.out.println("a[1] = " + a[1]);
	// }

	// 15m (Success)
	// 입력: 공간의 크기(int n), 이동할 내용(String [] command)
	// 출력: 마지막에 도착한 위치(String [] location)
	// 풀이 방향: 구현.
	// 			각 command에 대해 대응하는 구문을 작성한다.
	// 			만약 공간을 벗어나는 command일 경우에는 무시한다.
	// 시간 복잡도: O(N)
	public static int [] mySolution(int n, String [] command) {
		int [] location = {1,1};

		// command에 대해 대응하는 구문
		for (int i = 0; i < command.length; i++) {
			if (command[i].equals("U") && location[0] > 1) {
				location[0] --;
			} else if (command[i].equals("D") && location[0] < n) {
				location[0] ++;
			} else if (command[i].equals("L") && location[1] > 1) {
				location[1] --;
			} else if (command[i].equals("R") && location[1] < n) {
				location[1] ++;
			}
		}
		return location;
	}

	// 풀이 방향: 모든 커맨드에 대한 구현을 해놓았다. 표현이 보다 명확하다.
	// 			복잡할수록 이런 표현을 사용하는 것이 좋아 보인다.
	// 시간 복잡도: O(N)
	public static void bookSolution() {
		Scanner sc = new Scanner(System.in);

		// N을 입력받기
		int n = sc.nextInt();
		sc.nextLine(); // 버퍼 비우기
		String[] plans = sc.nextLine().split(" ");
		int x = 1, y = 1;

		// L, R, U, D에 따른 이동 방향
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		char[] moveTypes = {'L', 'R', 'U', 'D'};

		// 이동 계획을 하나씩 확인
		for (int i = 0; i < plans.length; i++) {
			char plan = plans[i].charAt(0);
			// 이동 후 좌표 구하기
			int nx = -1, ny = -1;
			for (int j = 0; j < 4; j++) {
				if (plan == moveTypes[j]) {
					nx = x + dx[j];
					ny = y + dy[j];
				}
			}
			// 공간을 벗어나는 경우 무시
			if (nx < 1 || ny < 1 || nx > n || ny > n) continue;
			// 이동 수행
			x = nx;
			y = ny;
		}

		System.out.println(x + " " + y);
	}
}
