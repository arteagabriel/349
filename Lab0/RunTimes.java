public class RunTimes {
    public static void main(String[] args) {
        long startTime;
        long endTime;

        System.out.println("Logarithmic algorithm's running times:");
        for (long i = 10000; i < 100000000; i *= 2) {
            startTime = System.nanoTime();
            Algorithms.logarithmicAlgorithm(i);
            endTime = System.nanoTime();

            System.out.println("T(" + i + ") = " + (endTime - startTime) / 1000000);
        }

        System.out.println();
        System.out.println("Linear algorithm's running times:");
        for (long i = 10000; i < 100000000; i *= 2) {
            startTime = System.nanoTime();
            Algorithms.linearAlgorithm(i);
            endTime = System.nanoTime();

            System.out.println("T(" + i + ") = " + (endTime - startTime) / 1000000);
        }

        System.out.println();
        System.out.println("NlogN algorithm's running times:");
        for (long i = 10000; i < 100000000; i *= 2) {
            startTime = System.nanoTime();
            Algorithms.NlogNAlgorithm(i);
            endTime = System.nanoTime();

            System.out.println("T(" + i + ") = " + (endTime - startTime) / 1000000);
        }

        System.out.println();
        System.out.println("Quadratic algorithm's running times:");
        for (long i = 10000; i <= 320000; i *= 2) {
            startTime = System.nanoTime();
            Algorithms.quadraticAlgorithm(i);
            endTime = System.nanoTime();

            System.out.println("T(" + i + ") = " + (endTime - startTime) / 1000000);
        }

        System.out.println();
        System.out.println("Cubic algorithm's running times:");
        startTime = System.nanoTime();
        Algorithms.cubicAlgorithm(10000);
        endTime = System.nanoTime();

        System.out.println("T(" + 10000 + ") = " + (endTime - startTime) / 1000000);
    }
}

