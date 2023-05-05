package Programmers.DfsBfs;

public class 카펫 {
    public int[] solution(int brown, int yellow) {
        int colRowTotal = (brown + 4) / 2;

        int col = 3;
        int row = colRowTotal - col;

        while (row >= col) {
            if(((row - 2) * (col - 2)) == yellow) {
                break;
            }
            col++;
            row--;
        }

        int[] answer = { row, col };
        return answer;
    }
}
