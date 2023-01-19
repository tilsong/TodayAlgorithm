package Programmers.before;

public class 전화번호목록 {
	public static void main(String[] args) {
		String [] arr = {"119", "97674223", "1195524421"};
		System.out.println(solution(arr));
	}
	// ["12","123","1235","567","88"] , false
	// ["123","456","789"] ,  true
	public static boolean solution(String[] phone_book) {

		for (int i = 0; i < phone_book.length-1; i++) {
			for (int j = 1; j < phone_book.length-1; j++) {
				// 검증
				if(phone_book[i].length() >= phone_book[j].length()) {
					continue;
				}
				if(phone_book[i] == phone_book[j].substring(0, phone_book[i].length())) {
					return false;
				}
			}
		}

		return true;
	}
}
