package Programmers.before;

public class NewId {
	public String solution(String new_id) {
		String answer = "";
		String[] idArr = new_id.split("");

		for(int i=0; i<new_id.length(); i++) {
			if(new_id.charAt(i) >= 65 | new_id.charAt(i) <= 90) { // 1. 대문자 이면 -> 소문자로
				idArr[i] = idArr[i].toLowerCase();
			} else if (idArr[i].equals("-") | idArr[i].equals("_") | idArr[i].equals(".")) {
				// 2. 일부 특수 문자 제외
			} else { // 그 외는 모두 ""
				idArr[i] = "";
			}
		}
		String join = String.join("", idArr);
		String[] newArr = join.split("");

		// 연속된 .제외
		for(int i=0; i<newArr.length; i++) {
			if(newArr[i].equals(".") && i < newArr.length -1 && newArr[i+1].equals(".")) {
				newArr[i] = "";
			}
		}

		// 앞 뒤 "." 제외
		join = String.join("", newArr);
		if(join.charAt(0) == '.') {
			join = join.substring(1, join.length() -1);
		}
		if(join.charAt(join.length()-1) == '.') {
			join = join.substring(0,join.length()-2);
		}

		// 빈 문자열이면 a 추가
		if(join.length() == 0) {
			join = "a";
		}

		// length 15 이상이면
		if(join.length() > 16) {
			join = join.substring(0,15);
		}

		// 글자 수가 2 이하이면 length = 3 될 때까지 마지막 문자로 반복
		if(join.length() <= 2) {
			String last = String.valueOf(join.charAt(join.length()-1));
			while(join.length()<3) {
				join += last;
			}
		}

		return join;
	}
}
