package Programmers.before;

import java.util.HashMap;
import java.util.Map;

public class DoubleNumber {
	// X: "12321", Y: "42531", answer: "321"
	public String solution(String X, String Y) {

			Map<Integer, Integer> mapX = new HashMap<>();
			Map<Integer, Integer> mapY = new HashMap<>();
			Map<Integer, Integer> result = new HashMap<>();

			for (int i = 0; i < X.length(); i++) {
				char c = X.charAt(i);
				int x = c -'0';
				mapX.put(x, mapX.getOrDefault(x, 0) + 1);
			}

			for (int i = 0; i< Y.length(); i++) {
				char c = Y.charAt(i);
				int y = c - '0';
				mapY.put(y, mapY.getOrDefault(y, 0) + 1);
			}

			for (int i = 0; i <10; i++) {
				int x = mapX.getOrDefault(i, -1);
				int y = mapY.getOrDefault(i, -1);
				if (x != -1 && y != -1) {
					if (x<=y) {
						result.put(i, x);
					} else {
						result.put(i, y);
					}
				}
			}

			if (result.size() == 0) {
				return "-1";
			}

			StringBuffer sb = new StringBuffer();

			if (result.size() == 1 && result.get(0) != null) {
				return "0";
			}

			for (int i = 9; i >= 0; i--) {
				if(result.get(i) != null) {
					int iter = result.get(i);
					for (int j = 0; j < iter; j++) {
						sb.append(i);
					}
				}

			}

			return sb.toString();

	}
}
