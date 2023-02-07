package Programmers.정렬;

public class HIndex {
	public static void main(String[] args) {
		int[] citation = {0, 1, 3, 5, 6}; // n = 5
		System.out.println(solution(citation));
	}


	// Input/Output Sample
	// [0, 1, 3, 5, 6]      3
	// 40m (Success)
	// 입력: 각 논문의 인용 수가 담긴 배열
	// 출력: 발표한 논문 n편 중, h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었다면 이 때 h의 최댓값
	// 풀이 방향: h번 인용된 논문들의 개수를 계수 정렬을 통해 표현하고, 출력 조건에 맞게 조건 식을 표현했다.
	//			문제를 처음에 제대로 이해하지 못해서 시간이 오래 걸린 게 아쉽다.
	// 시간 복잡도: O(N)

	public static int solution(int[] citations) {
		int[] arr = new int[10001]; // 최대 인용수 10000
		for (int c : citations) {
			arr[c] ++;
		}

		int hl = 0;
		for (int i = arr.length-1; i >= 0 ; i--) {
			int h = i;
			hl += arr[h];

			if (h <= hl) {
				return h;
			}
		}

		return 0;
	}
}
