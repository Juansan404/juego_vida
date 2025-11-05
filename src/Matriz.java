import java.util.Random;

public class Matriz {
    private int x;
    private int y;
    private int[][] matriz;
    private int[][] edades;
    private Random random;

    public Matriz(int x, int y) {
        this.x = x;
        this.y = y;
        this.matriz = new int[x][y];
        this.edades = new int[x][y];
        this.random = new Random();
    }

    public void generarMatriz() {
        Random random = new Random();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                this.matriz[i][j] = random.nextInt(2);
                this.edades[i][j] = 0;
            }
        }
    }
    public void imprimirMatriz() {
        System.out.println("Matriz de "+x+"x"+y+":");
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print(this.matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
    public void imprimirMatrizEdades() {
        System.out.println("Matriz de "+x+"x"+y+":");
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (this.matriz[i][j] == 1) {
                    System.out.print(this.edades[i][j] + " ");
                } else {
                    System.out.print("-");
                }
            }
            System.out.println();
        }
    }
    private int contarVecinos(int fila, int columna) {
        int vecinos = 0;

        // Recorrer las 8 posiciones vecinas
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                // Saltar la célula central
                if (i == 0 && j == 0) continue;

                // Calcular posición del vecino con wrapping (mundo esférico)
                int vecinoFila = (fila + i + x) % x;
                int vecinoColumna = (columna + j + y) % y;

                vecinos += matriz[vecinoFila][vecinoColumna];
            }
        }
        return vecinos;
    }

    // Evolucionar una generación
    public void evolucionar() {
        int[][] nuevaMatriz = new int[x][y];
        int[][] nuevasEdades = new int[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int vecinos = contarVecinos(i, j);
                int celula = matriz[i][j];

                // Aplicar reglas del juego
                if (celula == 1) { // Célula viva
                    if (vecinos < 2) {
                        // Muere por aislamiento
                        nuevaMatriz[i][j] = 0;
                        nuevasEdades[i][j] = 0;
                    } else if (vecinos > 4) {
                        // Muere por sofocación
                        nuevaMatriz[i][j] = 0;
                        nuevasEdades[i][j] = 0;
                    } else if (vecinos >= 2 && vecinos <= 4) {
                        // Sobrevive
                        nuevaMatriz[i][j] = 1;
                        nuevasEdades[i][j] = edades[i][j] + 1;
                    }
                } else { // Célula vacía
                    if (vecinos == 3) {
                        // Nace una nueva célula
                        nuevaMatriz[i][j] = 1;
                        nuevasEdades[i][j] = 0;
                    } else {
                        nuevaMatriz[i][j] = 0;
                        nuevasEdades[i][j] = 0;
                    }
                }

                // Aplicar eventos aleatorios (10% de probabilidad)
                double probabilidad = random.nextDouble();
                if (probabilidad < 0.1) {
                    if (nuevaMatriz[i][j] == 1) {
                        // Muerte aleatoria
                        nuevaMatriz[i][j] = 0;
                        nuevasEdades[i][j] = 0;
                    } else {
                        // Nacimiento aleatorio
                        nuevaMatriz[i][j] = 1;
                        nuevasEdades[i][j] = 0;
                    }
                }
            }
        }

        // Actualizar la matriz
        matriz = nuevaMatriz;
        edades = nuevasEdades;
    }
    public int[][] getMatriz() {
        return this.matriz;
    }
    public int[][] getEdades() {
        return this.edades;
    }
}
