package Boj.구현;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 경비원 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int col = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        int count = 0;

        int[][] arr = new int[t][2];
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int d = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        for (int i = 0; i < t; i++) {
            int nd = arr[i][0];
            int nl = arr[i][1];

            if (d == nd) {
                count += Math.abs(l-nl);
            } else if((d==1 && nd==2) || ((d==2)&&(nd==1))) { // 남북
                count += (row + Math.min(l+nl, Math.abs(col-l)+Math.abs(col-nl)));
            } else if((d==3 && nd==4) || ((d==4)&&(nd==3))) { // 동서
                count += (col + Math.min(l+nl, Math.abs(row-l)+Math.abs(row-nl)));
            } else if(d==1 && nd==3) {
                count += (l+nl);
            } else if(d==1 && nd==4) {
                count += (col-l) + nl;
            } else if(d==2 && nd==3) {
                count += l + (row-nl);
            } else if(d==2 && nd==4) {
                count += (col-l) + (row - nl);
            } else if(d==3 && nd==1) {
                count += (l + nl);
            } else if(d==3 && nd==2) {
                count += (col-l) + (nl);
            } else if(d==4 && nd==1) {
                count += l + (col - nl);
            } else if(d==4 && nd==2) {
                count += (row-l) + (col-nl);
            }
        }

        System.out.println(count);

    }
}
