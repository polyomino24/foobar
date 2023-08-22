import java.util.Random;

public class Solution {

    private static class ModInt {
        private static final int MOD = 1000000007;
        private final int value;


        private static int getMod() {
            return MOD;
        }

        public ModInt(int value) {
            this.value = value;
        }

        public ModInt add(ModInt other) {
            return new ModInt((value + other.value) % MOD);
        }

        public ModInt subtract(ModInt other) {
            return new ModInt((value - other.value + MOD) % MOD);
        }

        public ModInt multiply(ModInt other) {
            return new ModInt((int) ((long) value * other.value % MOD));
        }

        public ModInt divide(ModInt other) {
            return new ModInt((int) ((long) value * other.inverse().value % MOD));
        }

        public ModInt inverse() {
            return new ModInt(inverse(value));
        }

        private static int inverse(int value) {
            if (value == 0) {
                throw new ArithmeticException("Division by zero");
            }
            if (value == 1) {
                return 1;
            }
            return (int) ((long) (MOD - MOD / value) * inverse(MOD % value) % MOD);
        }
    }

    private static void swap(ModInt[] a, ModInt[] b) {
        for (int i = 0; i < a.length; i++) {
            ModInt tmp = a[i];
            a[i] = b[i];
            b[i] = tmp;
        }
    }

    private static int rank(ModInt[][] a) {
        int n = a.length;
        int m = a[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            if (res == n) break;
            for (int j = res + 1; j < n; j++) {
                if (a[j][i].value > 0 && a[res][i].value == 0) {
                    swap(a[j], a[res]);
                    break;
                }
            }
            if (a[res][i].value == 0) continue;
            ModInt inv = new ModInt(1).divide(a[res][i]);
            for (int j = i; j < m; j++) {
                a[res][j] = a[res][j].multiply(inv);
            }
            for (int j = res + 1; j < n; j++) {
                ModInt coef = a[j][i];
                for (int k = i; k < m; k++) {
                    a[j][k] = a[j][k].subtract(coef.multiply(a[res][k]));
                }
            }
            res++;
        }
        return res;
    }

    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    private static boolean can(int a, int b) {
        int g = gcd(a, b);
        int k = (a + b) / g;
        return (k & (k - 1)) != 0;
    }


    public static int solution(int[] banana_list) {
        int n = banana_list.length;
        ModInt[][] graph = new ModInt[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = new ModInt(0);
            }
        }
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (can(banana_list[i], banana_list[j])) {
                    graph[i][j] = new ModInt(0).subtract(graph[j][i] = new ModInt(rand.nextInt(ModInt.getMod() - 1)));
                }
            }
        }
        return n - rank(graph);
    }

}

