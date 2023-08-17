package Programmers.카카오;

import java.util.LinkedList;
import java.util.Queue;

// 70m (Fail -> Success)
// 입력: 두 개의 큐
// 출력: 각 큐의 원소 합을 같게 만들기 위해 필요한 작업의 최소 횟수. 안되면 -1 반환
// 풀이 방향: 각 큐의 원소 합을 갖게 만들면 되므로, 현재 원소의 합이 더 큰 큐의 원소를 빼서 더 작은 큐에 더해준다. 
//           각 큐 원소 합이 같거나, 최대 반복 횟수를 초과하면 반복 종료 후 값 리턴.
// 시간 복잡도: 큐 사이즈 2번 반복 + 큐 사이즈 최대 3번 반복 = 큐 사이즈 * 5 => O(N*5)  // N= 최대 300,000
public class 두큐합같게만들기2 {
    public static void main(String[] args) {
        int[] q1 = {3, 2, 7, 2};
        int[] q2 = {4, 6, 5, 1};
//        int[] q1 = {3, 3, 3, 3};
//        int[] q2 = {3, 3, 21, 3};

        System.out.println(solution2(q1, q2));
    }
    public static int solution2(int[] queue1, int[] queue2) {
        int answer = 0;
        long sum1 = 0;
        long sum2 = 0;
        Queue<Integer> q1 = new LinkedList<Integer>();
        Queue<Integer> q2 = new LinkedList<Integer>();

        for(int x=0;x<queue1.length;x++) {
            sum1 += queue1[x];
            q1.add(queue1[x]);
            sum2 += queue2[x];
            q2.add(queue2[x]);
        }

        while(sum1 != sum2) {
            if(sum1 > sum2) {
                int num = q1.peek();
                q1.poll();
                q2.add(num);
                sum2 += num;
                sum1 -= num;
            } else {
                int num = q2.peek();
                q2.poll();
                q1.add(num);
                sum1 += num;
                sum2 -= num;
            }
            answer++;
            if(answer > queue1.length * 3) {
                return -1;
            }
        }
        return answer;
    }

}