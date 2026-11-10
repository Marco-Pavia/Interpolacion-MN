# Interpolación Polinomial: Lagrange vs. Diferencias Divididas de Newton en Java

Implementación de dos métodos de **interpolación polinomial** que, para un mismo conjunto de nodos, construyen el **único polinomio de grado ≤ n−1** que pasa por todos los puntos:

- **Método de Newton** (`InterpolacionNewton`): basado en la tabla de **diferencias divididas**.
- **Método de Lagrange** (`InterpolacionLagrange`): basado en los **polinomios base** L_i(x).

**Autor:** Marco Antonio Pavia Flores.
**Materia:** SCC-1017 Métodos Numéricos — Unidad 5
**Docente:** Víctor Hugo Vásquez Herrera
**Institución:** Instituto Tecnológico Superior de Xalapa

## Lenguaje de programación

**Java** (JDK 17 o superior recomendado).

## Estructura del proyecto

| Archivo | Descripción |
|---|---|
| `InterpolacionNewton.java` | Diferencias divididas de Newton: `calcularDiferenciasDivididas`, `evaluarPolinomio`, `imprimirTabla`. |
| `InterpolacionLagrange.java` | Interpolación de Lagrange: `validarDatos`, `interpolacionLagrange`. |
| `Main.java` | Clase principal: define los nodos y el punto a evaluar, y ejecuta ambos métodos. |

Todos los archivos pertenecen al paquete `Interpolación`.

## Fundamento matemático

La línea corregida en `InterpolacionLagrange.java` es la que calcula cada factor de la productoria:

```java
termino *= (xEval - x[j]) / (x[i] - x[j]);
```

## Newton vs. Lagrange

| Aspecto | Newton | Lagrange |
|---|---|---|
| Idea | Tabla de diferencias divididas + forma anidada | Suma de polinomios base L_i(x) |
| Construcción de la tabla / coeficientes | O(n²) (se hace una sola vez) | No requiere tabla |
| Evaluación en un punto | **O(n)** (con la tabla ya calculada) | **O(n²)** (dos ciclos anidados) |
| Costo total (tabla + 1 evaluación) | O(n²) | O(n²) |
| Agregar un nodo nuevo | Solo se añade una diagonal a la tabla: **O(n)** | Se recalcula todo: **O(n²)** |
| Resultado | Mismo polinomio (único) | Mismo polinomio (único) |

## Validaciones

`InterpolacionLagrange.validarDatos()` lanza `IllegalArgumentException` si:

- Los arreglos son `null` o están vacíos.
- `x` y `y` tienen distinto tamaño.
- Existen nodos de `x` repetidos (división entre cero en la fórmula).

## Datos de prueba

Los nodos se definen directamente en `Main.java` y provienen de f(x) = x³ − 2x + 1:

```java
double[] x = {0.0, 1.0, 3.0, 4.0};
double[] y = {1.0, 0.0, 22.0, 57.0};
double xEval = 2.5;
```

| x | 0 | 1 | 3 | 4 |
|---|---|---|---|---|
| y | 1 | 0 | 22 | 57 |

- Punto a interpolar: **x = 2.5**
- Valor real: f(2.5) = **11.625**

## Cómo compilar y ejecutar

### Desde la terminal

1. Clona el repositorio (o descarga los archivos en una carpeta llamada `Interpolación`):
   ```bash
   git clone https://github.com/Marco-Pavia/Interpolacion-MN.git
   cd Interpolacion-MN
   ```
2. Compila todos los archivos desde la carpeta raíz (la que contiene a `Interpolación/`):
   ```bash
   javac -encoding UTF-8 Interpolación/*.java
   ```
3. Ejecuta la clase principal:
   ```bash
   java Main.java
   ```

### Desde IntelliJ IDEA

1. Abre la carpeta del proyecto con *File → Open*.
2. Verifica que los tres archivos estén dentro del paquete `Interpolación`.
3. Abre `Main.java` y presiona el botón verde ▶ junto al método `main`.

## Ejemplo de ejecución (` Main.java`)

```
--- TABLA DE DIFERENCIAS DIVIDIDAS ---
x_i	f[x_i]	Orden 1	Orden 2	Orden 3	
-----------------------------------------------------
0.00	1.0000	-1.0000	4.0000	1.0000	
1.00	0.0000	11.0000	8.0000	
3.00	22.0000	35.0000	
4.00	57.0000	

--- COEFICIENTES DEL POLINOMIO (a_k) ---
a_0 = 1.0000
a_1 = -1.0000
a_2 = 4.0000
a_3 = 1.0000

--- RESULTADO DE LA INTERPOLACIÓN (NEWTON) ---
El valor estimado en x = 2.50 es P(2.50) = 11.6250

--- RESULTADO DE LA INTERPOLACIÓN (LAGRANGE) ---
El valor estimado en x = 2.50 es P(2.50) = 11.6250
```