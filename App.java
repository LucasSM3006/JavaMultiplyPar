package CodigoProva;

import java.util.Random;

/**
 * Classe que inicializa a aplicação.
 */
public class App {
    // Dimensões m, n e p das matrizes
    private final int M_SIZE = 1024;
    private final int N_SIZE = 2048;
    private final int P_SIZE = 1024;
    // Semente do gerador de números aleatórios
    private final int SEED = 42;

    public static void main(String[] args) {
        App app = new App();
        app.start();
    } // fim do método main

    public void start() {
        int[][] matrixA = genMatrix(M_SIZE, N_SIZE);
        int[][] matrixB = genMatrix(N_SIZE, P_SIZE);
        int[][] matrixC = genMatrix(M_SIZE, P_SIZE);

        MatrixMultiplier matrixMultiplier = new MatrixMultiplier(matrixA, matrixB, matrixC, 8);
        matrixMultiplier.multiply();

        for(int i = 0; i < M_SIZE; i++) {
            for(int j = 0; j < P_SIZE; j++) {
                System.out.println(matrixC[i][j]);
            }
        }
        System.out.println(matrixC);
    } // fim do método start

    /**
     * Gera uma matriz com nrows linhas e ncols colunas,
     * preenchida com números inteiros aleatórios.
     */
    private int[][] genMatrix(int nrows, int ncols) {
        Random gen = new Random(SEED);

        int[][] matrix = new int[nrows][ncols];
        for (int i = 0; i < nrows; i++) {
            for (int j = 0; j < ncols; j++) {
                matrix[i][j] = gen.nextInt(10);
            }
        }

        return matrix;
    } // fim do método genMatrix
} // fim da classe App