package Boj.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 카드섞기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        List<Integer> target = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            target.add(Integer.parseInt(st.nextToken()));
        }

        // maxK 구하기
        int maxK = 0;
        while (n > 1) {
            n /= 2;
            maxK++;
        }
        
        for (int k1 = 1; k1 <= maxK; k1++) {
            for (int k2 = 1; k2 <= maxK; k2++) {
                List<Integer> cardList = new ArrayList<>();
                for (int i = 1; i <= n; i++) {
                    cardList.add(i);
                }
                List<Integer> shuffledList = shuffle(cardList, (int) Math.pow(2, k1));
                if (shuffle(shuffledList, (int) Math.pow(2, k2)).equals(target)) {
                    System.out.println(k1 + " " + k2);
                }
            }
        }
    }

    private static List<Integer> shuffle(List<Integer> cardList, int index) {
        if (index == 0) {
            return cardList;
        }
        List<Integer> divideCardList = cardList.subList(cardList.size() - index, cardList.size());

        List<Integer> result = new ArrayList<>(shuffle(divideCardList, index / 2));
        result.addAll(cardList.subList(0, cardList.size() - index));

        return result;
    }
}

// ========================================================
//  틀린 풀이 1
//
//    static int n;
//    static int[] result;
//    static int[] deck;
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        n = Integer.parseInt(st.nextToken());
//
//        st = new StringTokenizer(br.readLine());
//        result = new int[n];
//        for (int i = 0; i < n; i++) {
//            result[i] = Integer.parseInt(st.nextToken());
//        }
//
//        // 2^k < n
//        int limit = 0;
//        for (int i = 0; i < n; i++) {
//            if (Math.pow(2, i) >= n || i >= n) {
//                limit = i-1;
//                break;
//            }
//        }
//
//        for (int i = 1; i <= limit; i++) {
//            deck = new int[n];
//            shuffle(i);
//            for (int j = 1; j <= limit; j++) {
//                if (i == j) {
//                    continue;
//                }
//                shuffle(j);
//                if (check()) {
//                    System.out.println(i + " " + j);
//                    return;
//                }
//            }
//        }
//    }
//
//    private static void shuffle(int k) {
//        Stack<Integer> one = new Stack<>();
//        Stack<Integer> temp = new Stack<>();
//        Stack<Integer> back = new Stack<>();
//
//        for (int i = 1; i <= n; i++) {
//            one.push(i);
//        }
//        double pow = Math.pow(2, k);
//        for (int i = 0; i < pow; i++) {
//            back.push(one.pop());
//        }
//
//        for (int i = k; i > 0; i--) {
//            int iter = (int) Math.pow(2, i-1);
//            for (int j = 0; j < iter; j++) {
//                temp.push(back.pop());
//            }
//            System.out.println();
//            for (int j = 0; j < iter; j++) {
//                one.push(temp.pop());
//            }
//            System.out.println();
//        }
//        one.push(back.pop());
//
//        for (int i = 0; i < n; i++) {
//            deck[i] = one.pop();
//        }
//    }
//
//    private static boolean check() {
//        for (int i = 0; i < n; i++) {
//            if (result[i] != deck[i]) {
//                return false;
//            }
//        }
//        return true;
//    }
//==========================
// 틀린 풀이 2
//    static int n;
//    static int[] result;
//    static LinkedList<Integer> list;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        n = Integer.parseInt(st.nextToken());
//
//        st = new StringTokenizer(br.readLine());
//        result = new int[n];
//        for (int i = 0; i < n; i++) {
//            result[i] = Integer.parseInt(st.nextToken());
//        }
//
//        int limit = 0;
//        for (int i = 0; i < n; i++) {
//            if (Math.pow(2, i) >= n || i >= n) {
//                limit = i - 1;
//                break;
//            }
//        }
//
//        for (int i = 1; i <= limit; i++) {
//            list = new LinkedList<>();
//            for (int j = 1; j <= n; j++) {
//                list.add(j);
//            }
//
//            shuffle(i);
//            for (int j = 1; j <= limit; j++) {
//                shuffle(j);
//
//                if (check()) {
//                    System.out.println(i + " " + j);
//                    return;
//                }
//            }
//        }
//
//    }
//
//    private static void shuffle(int k) {
//        int cnt = (int) Math.pow(2, k);
//
//        firstShuffle(cnt);
//
//        int limit = k - 1;
//
//        while (limit >= 0) {
//            cnt = (int) Math.pow(2, limit);
//
//            LinkedList<Integer> left = new LinkedList<>();
//            LinkedList<Integer> right = new LinkedList<>();
//
//            for (int j = 0; j < cnt; j++) {
//                Integer num = list.pollFirst();
//                left.add(num);
//            }
//            for (int j = 0; j < cnt; j++) {
//                right.add(list.pollFirst());
//            }
//
//            for (int j = 0; j < cnt; j++) {
//                list.addFirst(left.pollLast());
//            }
//            for (int j = 0; j < cnt; j++) {
//                list.addFirst(right.pollLast());
//            }
//
//            limit--;
//        }
//    }
//
//    private static void firstShuffle(int cnt) {
//        while (cnt-- > 0) {
//            list.addFirst(list.pollLast());
//        }
//    }
//
//    private static boolean check() {
//        for (int i = 0; i < n; i++) {
//            if (result[i] != list.get(i)) {
//                return false;
//            }
//        }
//        return true;
//    }