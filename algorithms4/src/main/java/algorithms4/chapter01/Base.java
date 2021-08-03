package algorithms4.chapter01;

/**
 * base
 *
 * @author jingshouyan
 * 2021-06-16 21:01
 **/
public class Base {

    public static int gcd(int p, int q) {
        System.out.println("p=" + p + ",q=" + q);
        if (q == 0) {
            return p;
        }
        int r = p % q;
        return gcd(q, r);
    }

    public static boolean[][] bs(int n) {
        boolean[][] bs = new boolean[n][n];
        for (int i = 2; i < n; i++) {
            for (int j = 2; j < n; j++) {
                if (j < i) {
                    bs[i][j] = bs[j][i];
                }
                int gcd = gcd(i, j);
                if (gcd > 1) {
                    bs[i][j] = true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(bs[i][j]){
                    System.out.print("+  ");
                }else {
                    System.out.print("-  ");
                }
            }
            System.out.println();
        }
        return bs;
    }

    public static int mystery(int a, int b) {
        if (b == 0) {
            return 0;
        }
        if (b % 2 == 0) {
            return mystery(a * 2, b / 2);
        }
        return mystery(a * 2, b / 2) + a;
    }

    private static int c = 0;

    public static double binomial(int n, int k, double p) {
        double[][] nk = new double[n + 1][k + 1];
        double x = binomial(n, k, p, nk);
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                System.out.print(nk[i][j]);
                System.out.print(",");
            }
            System.out.println();
        }
        return x;
    }

    public static double binomial(int n, int k, double p, double[][] nk) {
        c++;
        if (n == 0 && k == 0) {
            return 1.0;
        }
        if (n < 0 || k < 0) {
            return 0.0;
        }
        if (nk[n][k] != 0) {
            return nk[n][k];
        }
        double x = (1.0 - p) * binomial(n - 1, k, p, nk) + p * binomial(n - 1, k - 1, p, nk);
        nk[n][k] = x;
        return x;
    }

    public static void main(String[] args) {
//        double x = binomial(10, 5, 0.25);
//        System.out.println(c);
//        System.out.println(x);

        bs(10);
    }
}
