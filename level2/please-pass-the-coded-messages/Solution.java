public class Solution {
    public static int solution(int[] l) {
        int[] cnt = new int[10];
        int mod = 0;
        for (int i = 0; i < l.length; i++) {
            cnt[l[i]]++;
            mod += l[i];
        }
        mod %= 3;
        boolean flag = mod == 0;
        for (int i = 0; i < 10 && !flag; i++) {
            if (cnt[i] > 0 && i % 3 == mod) {
                cnt[i]--;
                flag = true;
            }
        }
        for (int i = 0; i < 10 && !flag; i++) {
            if (cnt[i] > 1 && i * 2 % 3 == mod) {
                cnt[i] -= 2;
                flag = true;
                break;
            }
            for (int j = i + 1; j < 10; j++) {
                if (cnt[i] > 0 && cnt[j] > 0 && (i + j) % 3 == mod) {
                    cnt[i]--;
                    cnt[j]--;
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }
        if (flag) {
            int res = 0;
            for (int i = 9; i >= 0; i--) {
                while (cnt[i] > 0) {
                    res = res * 10 + i;
                    cnt[i]--;
                }
            }
            return res;
        }
        return 0;
    }
}

