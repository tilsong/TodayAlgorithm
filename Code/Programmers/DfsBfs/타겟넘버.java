package Programmers.DfsBfs;

class 타겟넘버 {

    // 풀이 1
//    int answer = 0;
//    public int solution(int[] numbers, int target) {
//        dfs(0, 0, numbers, target);
//        return answer;
//    }
//
//    private void dfs(int sum, int idx, int[] numbers, int target) {
//        if (idx == numbers.length && sum == target) {
//            answer++;
//            return;
//        }
//
//        if (idx >= numbers.length) {
//            return;
//        }
//
//        dfs(sum + numbers[idx], idx + 1, numbers, target);
//        dfs(sum - numbers[idx], idx + 1, numbers, target);
//    }

    // 풀이 2
    public int solution(int[] numbers, int target) {
        return dfs(0, 0, numbers, target);
    }
    int dfs(int depth, int sum, int[] numbers, int target) {
        if(depth == numbers.length) {
            if(sum == target) {
                return 1;
            }
            return 0;
        }
        return dfs(depth + 1, sum + numbers[depth], numbers, target) + dfs(depth + 1, sum - numbers[depth], numbers, target);
    }
}
