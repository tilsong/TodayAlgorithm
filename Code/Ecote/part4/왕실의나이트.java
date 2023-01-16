package Ecote.part4;

import java.util.Scanner;

public class 왕실의나이트 {
	// 실행
	public static void main(String[] args) {
		System.out.println(mySolution("a1"));
	}

	// 30m (Success)
	// 입력: 현재 나이트의 위치(String loc)
	// 출력: 이동할 수 있는 경우의 수(int count)
	// 풀이 방향: 구현
	// 			움직임의 경우의 수는 총 8개이며, 움직였을 때의 좌표가 체스판 위에 존재하는지 여부를 판단하기
	//			입력을 경우의 수에 대입하여 true가 되는지를 판별하고 개수 리턴
	// 시간 복잡도: O(n)
	public static int mySolution(String loc) {
		int count = 0;

		// 위치 구하기
		char col = loc.charAt(0);
		char row = loc.charAt(1);

		// Up
		if (row - 2 >= '1') {
			if (col - 2 >= 'a') count ++;
			if (col + 2 <= 'h') count ++;
		}
		// Down
		if (row + 2 <= '8') {
			if (col - 2 >= 'a') count ++;
			if (col + 2 <= 'h') count ++;
		}
		// Left
		if (col - 2 >= 'a') {
			if (row - 2 >= '1') count++;
			if (row + 2 <= '8') count++;
		}
		// Right
		if (col + 2 <= 'h') {
			if (row - 2 >= '1') count++;
			if (row + 2 <= '8') count++;
		}

		return count;
	}

	// 풀이 방향: 행위를 배열을 통해 정의했고, 위치를 int 값으로 변형시켜 표현함.
	// 			숫자 문자 char는 '0'을 빼주고, 알파벳 문자 char는 'a'을 빼고 1을 더해주었음.
	//			그러면 값을 0부터 구할 수 있으므로, 확실한 표현이 된다. 훨씬 계산이 깔끔해짐!
	//			char의 대소 비교나 연산 시 이런 방식으로 해야겠음!
	// 시간 복잡도: O(8)
	public static void bookSolution() {
		Scanner sc = new Scanner(System.in);

		// 현재 나이트의 위치 입력받기
		String inputData = sc.nextLine();
		int row = inputData.charAt(1) - '0';
		int column = inputData.charAt(0) - 'a' + 1;

		// 나이트가 이동할 수 있는 8가지 방향 정의
		int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
		int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};

		// 8가지 방향에 대하여 각 위치로 이동이 가능한지 확인
		int result = 0;
		for (int i = 0; i < 8; i++) {
			// 이동하고자 하는 위치 확인
			int nextRow = row + dx[i];
			int nextColumn = column + dy[i];
			// 해당 위치로 이동이 가능하다면 카운트 증가
			if (nextRow >= 1 && nextRow <= 8 && nextColumn >= 1 && nextColumn <= 8) {
				result += 1;
			}
		}

		System.out.println(result);
	}
}
