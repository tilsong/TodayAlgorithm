package Programmers.카카오;

import java.util.ArrayList;
import java.util.List;

public class 불량사용자 {
    public static void main(String[] args) {
        String[] users = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned = {"fr*d*", "abc1**"};

        System.out.println(solution(users, banned));
    }

    public static int solution(String[] user_id, String[] banned_id) {
        int answer = 0;

        List<List<Integer>> expects = new ArrayList<>();
        for (int i = 0; i < banned_id.length; i++) {
            expects.add(new ArrayList<>());
        }

        // 각 banned_id에 포함될 수 있는 id의 인덱스 고르기
        for (int i = 0; i < banned_id.length ; i++) {
            for (int j = 0; j < user_id.length; j++) {
                if (banned_id[i].length() == user_id[i].length()) {
                    int count = 0;
                    for (int k = 0; k < banned_id[i].length(); k++) {
                        if ((banned_id[i].equals(user_id[j])) || banned_id[i].equals("*")) {
                            count++;
                        }
                    }
                    if (count == banned_id.length) {
                        expects.get(i).add(j);
                    }
                }
            }
        }

        
        




        return answer;
    }
}
