package Programmers.카카오;

public class 이모티콘할인행사 {

    public static void main(String[] args) {
        int[][] arr1 = {{ 40, 10000}, {25, 10000}};
        int[] emo1 = {7000, 9000};
        int[][] arr2 = { {40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
        int[] emo2 = { 1300, 1500, 1600, 4900};
        int[] solution = solution(arr1, emo1);
        System.out.println(solution[0] + " " + solution[1]);
        int[] solution1 = solution(arr2, emo2);
        System.out.println(solution1[0] + " " + solution1[1]);
    }

    static int[] emoDiscount;
    static int serviceJoinMax = 0;
    static int totalPriceMax = 0;
    static int[] disRatio = {10, 20, 30, 40};
    public static int[] solution(int[][] users, int[] emoticons) {
        emoDiscount = new int[emoticons.length];

        dfs(0, users, emoticons);

        return new int[]{serviceJoinMax, totalPriceMax};
    }

    private static void dfs(int depth, int[][] users, int[] emoticons) {
        if (depth == emoticons.length) {
            int serviceJoin = 0;
            int totalSales = 0;

            for (int[] user : users) {
                int ratio = user[0];
                int maxPrice = user[1];
                int totalPrice = 0;

                for (int i = 0; i < emoticons.length; i++) {
                    if (emoDiscount[i] >= ratio) {
                        totalPrice += emoticons[i] * (100 - emoDiscount[i]) / 100;
                    }
                }
                if (totalPrice >= maxPrice) {
                    serviceJoin++;
                } else {
                    totalSales += totalPrice;
                }
            }

            if (serviceJoinMax < serviceJoin) {
                serviceJoinMax = serviceJoin;
                totalPriceMax = totalSales;
            } else if (serviceJoinMax == serviceJoin && totalPriceMax < totalSales) {
                totalPriceMax = totalSales;

            }

            return;
        }

        for (int i = 0; i < 4; i++) {
            emoDiscount[depth] = disRatio[i];
            dfs(depth + 1, users, emoticons);
        }
    }
}
