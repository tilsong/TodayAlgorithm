package Boj.구현;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {

        // 1 - 완탐
        int[] answer = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    answer = new int[] {i, j};
                    break;
                }
            }
        }

        return answer;
    }
    // 해보기
//    public static int[] twoSum2(int[] nums, int target) {
//        // 2 - 해시맵 사용
//        HashMap<Integer, Integer> map = new HashMap<>();
//
//        for (int i = 0; i < ; i++) {
//            
//        }
//
//        return answer;
//    }


    public static void main(String[] args) throws IOException {
        int [] nums = {2, 7, 11, 15};
        int target = 9;
        twoSum(nums, target);
    }
}
