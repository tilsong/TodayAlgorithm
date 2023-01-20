package Boj.미분류;

public class CaesarCipher { // B15874
	public static void main(String[] args) {
		mySolution(4, 10, "I AM JOHN."); // M EQ NSLR.
		mySolution(5, 37, "I love Red Black Tree, how about you.");
		// N qtaj Wji Gqfhp Ywjj, mtb fgtzy dtz.
	}

	// 30m (Success)
	// 입력: key 값(int k), 문자열의 길이 (int l), 문자열(String s)
	// 출력: 암호화된 문자열(단, 공백 문자와 온점, 쉼표는 그대로 출력)
	// 풀이 방향: String을 char[]로 변환하고, ascii 코드를 사용하여 변환한다.
	// 시간 복잡도: O(N)
	public static void mySolution(int k, int l, String s) {
		char[] cArr = s.toCharArray();
		StringBuilder sb = new StringBuilder();

		for (char c: cArr) {
			if (97 <= c && c <= 122) {
				c = (char)(((int) c + k - 97) % 26 + 97);
			} else if (65 <= c && c <= 90) {
				c = (char)(((int) c + k - 65) % 26 + 65);
			}
			sb.append(c);
		}

		System.out.println("encoded -> " + sb.toString());
	}
}
