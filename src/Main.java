import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Matriz matriz = new Matriz(6, 6);
        matriz.generarMatriz();

        System.out.println("====GENERACIÓN INICIAL====");
        matriz.imprimirMatriz();
        for (int i = 1; i <= 10; i++) {
            matriz.evolucionar();
            System.out.println("==== GENERACIÓN "+i+" ====");
            matriz.imprimirMatriz();
            matriz.imprimirMatrizEdades();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}