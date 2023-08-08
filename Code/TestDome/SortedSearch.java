package TestDome;

public class SortedSearch {
    public static int countNumbers(int[] sortedArray, int lessThan) {
        int start = 0;
        int end = sortedArray.length - 1;

        while (start <= end) {
            int mid = (end + start) / 2;

            if (sortedArray[mid] < lessThan) {
                start = mid + 1;
            } else {
                end = mid -1;
            }
        }

        return start;
    }
    
    public static void main(String[] args) {
        System.out.println(SortedSearch.countNumbers(new int[] { 1, 3, 5, 7 }, 4));
    }
}