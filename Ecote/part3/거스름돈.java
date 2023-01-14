package part3;

public class 거스름돈 {
	// public static void main(String[] args) {
	// 	System.out.println(mySolution(1460));
	// }

	// 10m (Success)
	// 입력: 거스름돈
	// 출력: 거스름돈으로 줄 수 있는 최소 동전 개수
	// 풀이 방향: 그리디 사용.
	// 			최소한의 동전 개수를 구하기 위해서, 우선적으로 가장 큰 단위의 동전을 사용한다.
	//			즉, 500, 100, 50, 10 원 동전 순으로 거스름돈을 만든다.
	// 시간 복잡도: O(동전 종류 개수 * 각 동전의 사용 개수)
	public static int mySolution(long n) {
		int coinCount = 0;

		while (n >= 500) {
			n -= 500;
			coinCount ++;
		}
		while (n >= 100) {
			n -= 100;
			coinCount ++;
		}
		while (n >= 50) {
			n -= 50;
			coinCount ++;
		}
		while (n >= 10) {
			n -= 10;
			coinCount ++;
		}

		return coinCount;
	}

	// 풀이 방향: 그리디 사용.
	//			당연히 반복해야 하니까 반복문 썼는데, 생각해보니까 계산할 때 각 동전의 개수 바로 세서 계산하는게
	//			논리적으로도 맞는 것 같다.. 배 웠 다
	//			풀이도 이게 훨씬 깔끔하다..
	// 시간 복잡도: O(동전 종류 개수)
	public int bookSolution (long n) {
		int cnt = 0;
		int[] coinTypes = {500, 100, 50, 10};

		for (int i = 0; i < 4; i++) {
			int coin = coinTypes[i];
			cnt += n / coin;
			n %= coin;
		}

		return cnt;
	}
}
