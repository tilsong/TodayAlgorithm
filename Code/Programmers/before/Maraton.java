package Programmers.before;

import java.util.HashMap;
import java.util.Map;

public class Maraton {
	// participant: ["marina", "josipa", "nikola", "vinko", "filipa"]
	// completion: ["josipa", "filipa", "marina", "nikola"]
	// answer: "vinko"
	public String solution(String[] participant, String[] completion) {
		String answer = "";
		Map<String, Integer> map = new HashMap<>();

		// participant
		for (int i=0; i< participant.length; i++) {
			if (map.containsKey(participant[i])) { // 있으면
				int v = map.get(participant[i]);
				map.put(participant[i], ++v);
			} else { // 없으면
				map.put(participant[i], 1);
			}
		}

		// completion
		for (int i=0; i< completion.length; i++) {
			if (map.get(completion[i]) == 1) {
				map.remove(completion[i]);
			} else {
				int v = map.get(completion[i]);
				map.put(completion[i], --v);
			}
		}

		// 하나 남은 키가 완주하지 못한 선수 이름
		for (String key : map.keySet()) {
			answer = key;
		}

		return answer;
	}
}
