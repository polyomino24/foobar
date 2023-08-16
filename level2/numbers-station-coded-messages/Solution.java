public class Solution {
    public static int[] solution(int[] a, int t) {
        int l = 0, r = 0;
        int sum = 0;
        while (r < a.length) {
            if (sum < t) {
                sum += a[r++];
            }
            while (sum > t) {
                sum -= a[l++];
            }
            if (sum == t) {
                return new int[]{l, r - 1};
            }
        }
        return new int[]{-1, -1};
    }
}

