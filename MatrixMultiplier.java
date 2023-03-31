package CodigoProva;

public class MatrixMultiplier {
    private int[][] matrixA;
    private int[][] matrixB;
    private int[][] matrixC;
    private int numThreads;

    public MatrixMultiplier(int[][] matrixA, int[][] matrixB, int[][] matrixC, int numThreads) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.matrixC = matrixC;
        this.numThreads = numThreads;
    }

    public void multiply() {
        int m = matrixA.length;
        int n = matrixB.length;
        int p = matrixC[0].length;

        Thread[] threads = new Thread[numThreads];
        int rowsPerThread = m / numThreads;

        for (int t = 0; t < numThreads; t++) {
            final int startRow = t * rowsPerThread; //Starting row starts at the very beginning of the rows allocated, since in this particular case it'll be an equal number with 2/4/8 threads no matter what.
            final int endRow = (t == numThreads - 1) ? m : (t + 1) * rowsPerThread; //Point of this is to indicate the end row. AND it could also be zero, so... Ternary operator needed.

            threads[t] = new Thread(new MatrixMultiplierRunnable(n, p, startRow, endRow)); //Initializes the runnable, with the correct variables.
            threads[t].start(); // Initializes the thread.
        }

        for (Thread thread : threads) {
            try {
                thread.join(); //Tries to join all the threads which were created. Basically the 'main' has to wait for all the worker threads to do their thing beforehand.
                // thread.join blocks the main thread until it finishes.
            } catch (InterruptedException err) {
                err.printStackTrace();
            }
        }
    } // Fim método multiply

    private class MatrixMultiplierRunnable implements Runnable {
        private int startRow;
        private int endRow;
        private int n;
        private int p;

        public MatrixMultiplierRunnable(int n, int p, int startRow, int endRow) {
            this.n = n;
            this.p = p;
            this.startRow = startRow;
            this.endRow = endRow;
        }

        @Override
        public void run() {
            for (int i = startRow; i < endRow; i++) {
                for (int j = 0; j < p; j++) {
                    for (int k = 0; k < n; k++) {
                        matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
                    }
                }
            }
        }
    } // Fim classe runnable
} // Fim classe MatrixMultiplier
