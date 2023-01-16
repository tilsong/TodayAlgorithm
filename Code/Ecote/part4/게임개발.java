package Ecote.part4;

import java.util.ArrayList;
import java.util.List;

public class 게임개발 {
	// 실행
	// public static void main(String[] args) {
	// }

	// 70m (1차 Fail 230114)
	// Fail 사유: 모든 구현 조건에 대해 명확히 정의하지 않고 코드를 작성했다.
	// 			 그래서 코드를 작성하다가 추가적인 조건을 넣는 과정이 많아졌다.
	// 			 앞선 조건과 뒷선 조건에 대해 스스로 헷갈리게 되었고, 시간까지 많이 소모하게 되었다.
	//			 따라서 이를 중지하며, 일주일 후 다시 풀어보기로 한다.
	//			 그 때는 조건을 먼저 명확히 정의한 후에, 정확한 순서로 코드를 구성할 것!
	// 입력: 맵의 가로 세로 크기(int n, int m), 캐릭터의 위치 좌표와 바라보는 방향(int a, int b, int d), 맵(int [][] map)
	// 출력: 캐릭터가 이동을 마칠 때까지 방문한 육지의 수
	// 풀이 방향: 현재 바라보는 방향을 정의하고, 왼쪽 방향으로 및 이동할 수 있는지 여부를 판단한다.
	// 			이동 가능하면 이동, 없으면 방향만 바꿈. 제한 이상으로 방향 전환 시 종료
	// 시간 복잡도:
	public static int mySolution(int n, int m, int a, int b, int d, int [][] map) {
		int[] xForD = {0, 1, 0, -1};
		int[] yForD = {1, 0, -1, 0};

		List<String> visit = new ArrayList<>();
		visit.add(a+""+b); // "11"

		int change = 0;

		while (true) {

			// 방향 전환 횟수
			change ++;
			if (change == 5) { // 모든 방향 순회했을 때
				// 현재의 반대 방향으로 한 칸 이동
				// 반대 방향 전환
				int revD = d;
				revD -= 2;
				if (revD == -2) revD = 2;
				if (revD == -1) revD = 3;

				int x = a + xForD[revD]; // 방향 전환 후 이동 시 x좌표
				int y = b + yForD[revD]; // 방향 전환 후 이동 시 y좌표

				if (map[x][y] != 1) { // 육지이면 이동
					a = x;
					b = y;
				} else {
					change ++;
				}

				// 이동 불가하면(뒤가 바다일 경우)


				change = 0;
				break;
			}

			// 방향 전환
			d --;
			if (d == -1) {
				d = 3;
			}

			int x = a + xForD[d]; // 방향 전환 후 이동 시 x좌표
			int y = b + yForD[d]; // 방향 전환 후 이동 시 y좌표

			if (x >= 0 && x < n && y >= 0 && y < m) { // 지도 내부이면
				if (!visit.contains(x+""+y) && map[x][y] != 1) { // 방문하지 않았고, 육지이면
					a = x;
					b = y;
					visit.add(a+""+b); // 방문 선언
				}
			} else {
				change ++;
			}
		}

		return visit.size();
	}

	// 풀이 방향:
	// 시간 복잡도:
	public static void bookSolution() {
	}
}
