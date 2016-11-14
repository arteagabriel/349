public class Tester {
    public static void main(String[] args) {
        final int[] set1 = { 100, 50, 25, 10, 5, 1 }; // US denominations
        final int[] set2 = { 100, 50, 20, 15, 10, 5, 3, 2, 1 }; // Soviet denominations
        final int[] set3 = { 64, 32, 16, 8, 4, 2, 1 }; // Powers of two
        final int[] set4 = { 100, 50, 25, 10, 1 }; // US without the nickel
        final int[] set5 = { 66, 35, 27, 18, 10, 1 }; // Some set
        final int MAX = 200;
        int matches1 = 0;
        int matches2 = 0;
        int matches3 = 0;
        int matches4 = 0;
        int matches5 = 0;

        for (int n = 1; n <= 200; n++) {
            matches1 += checkCounts(set1, n);
            matches2 += checkCounts(set2, n);
            matches3 += checkCounts(set3, n);
            matches4 += checkCounts(set4, n);
            matches5 += checkCounts(set5, n);
        }

        System.out.println("Testing change_DP and change_greedy algorithms");
        System.out.println("Testing set1: " + matches1 + " in " + MAX + " tests");
        System.out.println("Testing set2: " + matches2 + " in " + MAX + " tests");
        System.out.println("Testing set3: " + matches3 + " in " + MAX + " tests");
        System.out.println("Testing set4: " + matches4 + " in " + MAX + " tests");
        System.out.println("Testing set5: " + matches5 + " in " + MAX + " tests");
    }

    public static int checkCounts(int[] d, int n) {
        int[] dpResults = ChangeMaker.change_DP(n, d);
        int[] greedyResults = ChangeMaker.change_greedy(n, d);
        int greedyCount = 0;
        int dpCount = 0;
        int matches = 0;

        for (int i = 0; i < d.length; i++) {
            if (dpResults[i] > 0)
                dpCount += dpResults[i];

            if (greedyResults[i] > 0)
                greedyCount += greedyResults[i];
        }

        return (dpCount == greedyCount) ? 1 : 0;
    }
}
