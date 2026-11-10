package Interpolación;

public class Main {
    public static void main(String[] args) {
        // Nodos definidos en Main (tomados de f(x) = x^3 - 2x + 1)
        double[] x = {0.0, 1.0, 3.0, 4.0};
        double[] y = {1.0, 0.0, 22.0, 57.0};

        // Punto intermedio a interpolar
        double xEval = 2.5;

        // Newton
        // 1. Calcular tabla de diferencias divididas
        double[][] tablaDiferencias = InterpolacionNewton.calcularDiferenciasDivididas(x, y);

        // 2. Imprimir la tabla
        InterpolacionNewton.imprimirTabla(x, tablaDiferencias);

        // 3. Mostrar los coeficientes
        System.out.println("\n--- COEFICIENTES DEL POLINOMIO (a_k) ---");
        for (int k = 0; k < x.length; k++) {
            System.out.printf("a_%d = %.4f\n", k, tablaDiferencias[0][k]);
        }

        // 4. Evaluar en xEval
        double yNewton = InterpolacionNewton.evaluarPolinomio(x, tablaDiferencias, xEval);

        System.out.println("\n--- RESULTADO DE LA INTERPOLACIÓN (NEWTON) ---");
        System.out.printf("El valor estimado en x = %.2f es P(%.2f) = %.4f\n", xEval, xEval, yNewton);

        // Lagrange
        double yLagrange = InterpolacionLagrange.interpolacionLagrange(x, y, xEval);

        System.out.println("\n--- RESULTADO DE LA INTERPOLACIÓN (LAGRANGE) ---");
        System.out.printf("El valor estimado en x = %.2f es P(%.2f) = %.4f\n", xEval, xEval, yLagrange);
    }
}