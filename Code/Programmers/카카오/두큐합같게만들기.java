package Programmers.카카오;

import java.util.LinkedList;
import java.util.Queue;

// 70m (Fail -> Success)
// 입력: 두 개의 큐
// 출력: 각 큐의 원소 합을 같게 만들기 위해 필요한 작업의 최소 횟수. 안되면 -1 반환
// 풀이 방향: 각 큐의 원소 합을 갖게 만들면 되므로, 현재 원소의 합이 더 큰 큐의 원소를 빼서 더 작은 큐에 더해준다. 
//           각 큐 원소 합이 같거나, 최대 반복 횟수를 초과하면 반복 종료 후 값 리턴.
// 시간 복잡도: 큐 사이즈 2번 반복 + 큐 사이즈 최대 3번 반복 = 큐 사이즈 * 5 => O(N*5)  // N= 최대 300,000
public class 두큐합같게만들기 {
    public static void main(String[] args) {
        int[] q1 = {3, 2, 7, 2};
        int[] q2 = {4, 6, 5, 1};
//        int[] q1 = {3, 3, 3, 3};
//        int[] q2 = {3, 3, 21, 3};

        System.out.println(solution(q1, q2));
    }

    public static int solution(int[] queue1, int[] queue2) {
        int totalLength = queue1.length + queue2.length;

        Queue<Integer> q1 = new LinkedList<>();
        long q1Total = 0;
        for (int i : queue1) {
            q1Total += i;
            q1.offer(i);
        }

        Queue<Integer> q2 = new LinkedList<>();
        long q2Total = 0;
        for (int i : queue2) {
            q2Total += i;
            q2.add(i);
        }

        int iterUp = totalLength*3;
        while (q1Total != q2Total && iterUp > 0) {
            if (q1Total > q2Total) {
                Integer p = q1.poll();
                q2.add(p);
                q1Total -= p;
                q2Total += p;
            } else {
                Integer p = q2.poll();
                q1.add(p);
                q2Total -= p;
                q1Total += p;
            }

            iterUp --;
        }

        int realIter = totalLength*3 - iterUp;

        if (q1Total == q2Total) {
            return realIter;
        }

        return -1;
    }
}