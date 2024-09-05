package stringall;

import java.util.Scanner;

public class jumpgame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        
        if (n == 0) {
            System.out.println("false");
            return;
        }

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        boolean canReachEnd = canJumpToEnd(arr);

        System.out.println(canReachEnd ? "true" : "false");
    }

    private static boolean canJumpToEnd(int[] arr) {
        int maxReach = 0;

        for (int i = 0; i < arr.length; i++) {
            if (i > maxReach) {
                return false;
            }

            maxReach = Math.max(maxReach, i + arr[i]);

            if (maxReach >= arr.length - 1) {
                return true;
            }
        }

        return false;
    }
}
