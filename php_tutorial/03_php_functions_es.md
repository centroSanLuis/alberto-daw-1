# Funciones en PHP

Las funciones son una piedra angular de casi cualquier lenguaje de programación, y PHP no es una excepción. Te permiten organizar tu código en piezas manejables y reutilizables, haciendo tus scripts más eficientes, legibles y fáciles de mantener.

## 1. Entendiendo las Funciones

### ¿Qué es una Función?

Una **función** es un bloque de código autónomo que realiza una tarea específica. Piensa en ella como un mini-programa dentro de tu programa principal. La defines una vez, y luego puedes "llamarla" (ejecutarla) múltiples veces desde diferentes partes de tu script.

Por ejemplo, en lugar de escribir las mismas líneas de código cada vez que quieres saludar a un usuario, puedes escribir una función `saludarUsuario()` y simplemente llamarla cuando sea necesario.

### ¿Por Qué Usar Funciones?

Usar funciones ofrece varias ventajas significativas:

*   **Modularidad:** Las funciones dividen problemas complejos en partes más pequeñas y manejables. Cada función maneja una pieza específica de lógica.
*   **Reutilización:** Una vez que una función está definida, puede ser usada muchas veces a lo largo de tu aplicación, o incluso en diferentes aplicaciones. Este enfoque de "escribe una vez, usa muchas veces" ahorra tiempo y esfuerzo.
*   **Legibilidad:** Las funciones hacen tu código más fácil de leer y entender. Las funciones bien nombradas describen su propósito, haciendo más claro el flujo general del programa. En lugar de un script largo y complejo, tienes una serie de pasos lógicos definidos por llamadas a funciones.
*   **Depuración Más Fácil:** Cuando ocurre un problema, las funciones ayudan a aislar el inconveniente. Si una función no está funcionando correctamente, sabes exactamente qué parte del código examinar. Esto es mucho más fácil que depurar un script grande y monolítico.
*   **Mantenibilidad:** Si necesitas cambiar cómo se realiza una tarea específica, solo necesitas actualizar el código dentro de la función relevante. Este cambio se aplicará entonces en todos los lugares donde se llame a la función.

## 2. Funciones Definidas por el Usuario

Aunque PHP proporciona una vasta biblioteca de funciones incorporadas (que mencionaremos más adelante), a menudo necesitarás crear tus propias funciones personalizadas para adaptarlas a las necesidades específicas de tu aplicación. Estas se llaman funciones definidas por el usuario.

### Definiendo una Función

**Sintaxis:**

```php
<?php
function nombreFuncion($parametro1, $parametro2, /* ... */) {
    // Código a ejecutar
    // Este bloque de código es el cuerpo de la función
    // Puede realizar acciones, cálculos, etc.

    return $valor; // Opcional: devuelve un valor al llamador
}
?>
```

*   `function`: La palabra clave que le dice a PHP que estás definiendo una función.
*   `nombreFuncion`: El nombre que le das a tu función.
    *   **Convenciones de Nomenclatura:**
        *   Los nombres de las funciones deben comenzar con una letra o un guion bajo (`_`), seguido de cualquier número de letras, números o guiones bajos.
        *   Los nombres de las funciones son **insensibles a mayúsculas y minúsculas** en PHP, pero es una buena práctica ser consistente.
        *   Las convenciones comunes incluyen:
            *   `camelCase`: `calcularMontoTotal`, `obtenerDetallesUsuario` (primera palabra en minúscula, palabras subsiguientes capitalizadas).
            *   `snake_case`: `calcular_monto_total`, `obtener_detalles_usuario` (todas las palabras en minúscula, separadas por guiones bajos).
        *   **Elige una convención y mantenla** en todo tu proyecto por consistencia. Muchos desarrolladores de PHP prefieren camelCase para métodos (funciones dentro de clases) y snake_case para funciones independientes, pero la consistencia dentro de tu propio código base es clave.
*   `($parametro1, $parametro2, ...)`: Una lista opcional de parámetros (también llamados argumentos). Estos son como marcadores de posición para los valores que pasarás a la función cuando la llames. Los parámetros se separan por comas.
*   `{ ... }`: Las llaves encierran el cuerpo de la función – el código real que realiza la tarea.
*   `return $valor;`: Una declaración opcional que envía un valor de vuelta a la parte del script que llamó a la función.

**Ejemplo:**

```php
<?php
// Una función simple para saludar a un usuario
function saludarUsuario($nombre) {
    echo "¡Hola, " . $nombre . "! Bienvenido a nuestro sitio web.<br>";
}
?>
```

### Llamando a una Función

Una vez que una función está definida, puedes ejecutarla "llamándola". Para llamar a una función, simplemente escribes su nombre seguido de paréntesis `()`. Si la función espera parámetros, proporcionas valores (argumentos) dentro de los paréntesis.

**Sintaxis:**

`nombreFuncion($argumento1, $argumento2, ...);`

**Ejemplo (llamando a la función `saludarUsuario`):**

```php
<?php
function saludarUsuario($nombre) {
    echo "¡Hola, " . $nombre . "! Bienvenido a nuestro sitio web.<br>";
}

// Llamando a la función
saludarUsuario("Alicia");   // Salida: ¡Hola, Alicia! Bienvenido a nuestro sitio web.
saludarUsuario("Roberto");  // Salida: ¡Hola, Roberto! Bienvenido a nuestro sitio web.

$nombre_usuario = "Carolina";
saludarUsuario($nombre_usuario); // Salida: ¡Hola, Carolina! Bienvenido a nuestro sitio web.
?>
```

### Parámetros (Argumentos)

Los parámetros te permiten pasar datos a una función, haciéndola más flexible y dinámica. Los valores pasados a una función cuando se llama son llamados argumentos.

*   **Paso de Argumentos por Valor (Predeterminado):**
    Por defecto, PHP pasa argumentos a las funciones "por valor". Esto significa que se pasa una copia del valor del argumento a la función. Cualquier cambio realizado al parámetro dentro de la función no afecta a la variable original fuera de la función.

    ```php
    <?php
    function sumarCinco($numero) {
        $numero += 5;
        echo "Dentro de la función: " . $numero . "<br>"; // ej., Dentro de la función: 15
    }

    $numeroOriginal = 10;
    sumarCinco($numeroOriginal);
    echo "Fuera de la función: " . $numeroOriginal . "<br>"; // Salida: Fuera de la función: 10 (el original no cambia)
    ?>
    ```

*   **Paso de Argumentos por Referencia (usando `&`):**
    Si quieres que una función modifique sus argumentos, puedes pasarlos por referencia. Para hacer esto, antepones un ampersand (`&`) al nombre del parámetro en la definición de la función. Esto pasa una referencia a la variable original, por lo que los cambios dentro de la función afectarán a la variable original.

    ```php
    <?php
    function sumarDiez(&$numero) { // Nota el &
        $numero += 10;
        echo "Dentro de la función: " . $numero . "<br>"; // ej., Dentro de la función: 25
    }

    $valorOriginal = 15;
    sumarDiez($valorOriginal);
    echo "Fuera de la función: " . $valorOriginal . "<br>"; // Salida: Fuera de la función: 25 (el original SÍ cambia)
    ?>
    ```
    **Cuándo usar paso por referencia:** Úsalo cuando necesites específicamente que la función altere la variable original. Esto puede ser útil para funciones que modifican directamente arrays u objetos grandes para evitar la sobrecarga de copiarlos, o cuando una función necesita "devolver" múltiples valores modificando variables que se le pasan. Sin embargo, úsalo con prudencia, ya que a veces puede hacer que el código sea más difícil de seguir si no está claro que una función modifica sus argumentos.

*   **Valores de Parámetro Predeterminados:**
    Puedes proporcionar valores predeterminados para los parámetros. Si se llama a una función sin pasar un argumento para un parámetro con un valor predeterminado, se utilizará el valor predeterminado. Los parámetros predeterminados siempre deben listarse después de cualquier parámetro no predeterminado.

    ```php
    <?php
    function enviarMensaje($mensaje, $destinatario = "Usuario") {
        echo "Enviando mensaje a " . $destinatario . ": '" . $mensaje . "'<br>";
    }

    enviarMensaje("¡Hola!");                     // Salida: Enviando mensaje a Usuario: '¡Hola!'
    enviarMensaje("Actualización importante", "Admin"); // Salida: Enviando mensaje a Admin: 'Actualización importante'
    ?>
    ```

*   **Listas de Argumentos de Longitud Variable:**
    A veces podrías necesitar una función que pueda aceptar un número variable de argumentos. PHP proporciona algunas formas de manejar esto:

    1.  **`func_num_args()` y `func_get_args()` (Forma antigua):**
        *   `func_num_args()`: Devuelve el número de argumentos pasados a la función.
        *   `func_get_args()`: Devuelve un array que contiene todos los argumentos pasados a la función.

        ```php
        <?php
        function sumarTodo() {
            $total = 0;
            $numArgs = func_num_args();
            echo "Número de argumentos: " . $numArgs . "<br>";
            $args = func_get_args();
            foreach ($args as $arg) {
                $total += $arg;
            }
            return $total;
        }

        echo sumarTodo(1, 2, 3) . "<br>";       // Número de argumentos: 3, Salida: 6
        echo sumarTodo(10, 20, 30, 40) . "<br>"; // Número de argumentos: 4, Salida: 100
        ?>
        ```

    2.  **El operador `...` (Variadics - PHP 5.6+):**
        Esta es la forma moderna y preferida. Puedes usar `...` (operador de propagación o splat) antes del nombre de un parámetro para indicar que la función aceptará un número variable de argumentos para ese parámetro. Estos argumentos se recogerán en un array.

        ```php
        <?php
        function multiplicar($multiplicador, ...$numeros) { // $numeros será un array
            echo "Multiplicador: " . $multiplicador . "<br>";
            print_r($numeros); // Muestra el array de números
            $producto = 1;
            foreach ($numeros as $n) {
                $producto *= $n;
            }
            return $producto * $multiplicador;
        }

        echo multiplicar(2, 3, 4, 5) . "<br>"; // Multiplicador: 2, Array ( [0] => 3 [1] => 4 [2] => 5 ), Salida: 120
        echo multiplicar(10, 1, 2) . "<br>";   // Multiplicador: 10, Array ( [0] => 1 [1] => 2 ), Salida: 20
        ?>
        ```
        También puedes usar el operador `...` para desempaquetar un array o un objeto Traversable en argumentos al llamar a una función: `miFuncion(...$arrayDeArgumentos);`

### Valores de Retorno

Las funciones pueden enviar datos de vuelta al código que las llamó usando la declaración `return`.

*   **Usando la Declaración `return`:**
    Cuando se encuentra una declaración `return`, la función deja de ejecutarse inmediatamente y el valor especificado se envía de vuelta.

    ```php
    <?php
    function sumar($num1, $num2) {
        $suma = $num1 + $num2;
        return $suma;
        // echo "Esta línea no se ejecutará."; // El código después de return es inalcanzable
    }

    $resultado = sumar(5, 7); // $resultado será 12
    echo "La suma es: " . $resultado . "<br>";
    ?>
    ```

*   **Retornando Valores Únicos, Múltiples Valores:**
    *   **Valor Único:** Como se vio arriba, una función puede retornar cualquier valor único (cadena, entero, array, objeto, etc.).
    *   **Múltiples Valores:** Para retornar múltiples valores, típicamente los retornas como un array o un objeto.
        ```php
        <?php
        function obtenerDetallesUsuario($id) {
            // En una aplicación real, podrías obtener esto de una base de datos
            $nombre = "Alicia";
            $edad = 30;
            return [$nombre, $edad]; // Retorna como un array indexado
        }

        list($nombreUsuario, $edadUsuario) = obtenerDetallesUsuario(1); // Usando list() para desempaquetar el array
        // Para PHP 7.1+ puedes usar desestructuración de array más corta: [$nombreUsuario, $edadUsuario] = obtenerDetallesUsuario(1);
        echo "Nombre: " . $nombreUsuario . ", Edad: " . $edadUsuario . "<br>";

        function obtenerInfoProducto($id) {
            return [
                "nombre" => "Portátil",
                "precio" => 1200.00,
                "moneda" => "USD"
            ]; // Retorna como un array asociativo
        }

        $producto = obtenerInfoProducto(101);
        echo "Producto: " . $producto["nombre"] . ", Precio: " . $producto["precio"] . " " . $producto["moneda"] . "<br>";
        ?>
        ```

*   **Funciones Que No Retornan un Valor:**
    Si una función no tiene una declaración `return`, o si tiene una declaración `return;` sin un valor, implícitamente retorna `NULL`. Tales funciones se usan a menudo para acciones, como imprimir salida directamente o modificar variables globales.

    ```php
    <?php
    function imprimirMensaje($mensaje) {
        echo $mensaje . "<br>";
        // No hay return explícito, por lo que retorna NULL
    }

    $valorRetornado = imprimirMensaje("Probando función void.");
    var_dump($valorRetornado); // Salida: NULL
    ?>
    ```

### Declaración de Tipos (Parámetros y Tipos de Retorno)

PHP 7 introdujo declaraciones de tipo para parámetros y valores de retorno. Esto ayuda a escribir código más robusto asegurando que las funciones reciban y retornen los tipos de datos esperados.

*   **Declaraciones de Tipo Escalar (PHP 7+):** `int`, `float`, `string`, `bool`.
*   **Declaraciones de Tipo de Clase/Interfaz:** Puedes especificar que un parámetro debe ser una instancia de una clase específica o implementar una interfaz específica.
*   **Declaraciones de Tipo Array:** `array`.
*   **Declaraciones de Tipo Callable:** `callable` (para funciones o métodos que pueden ser llamados).
*   **Declaraciones de Tipo Iterable (PHP 7.1+):** `iterable` (puede ser `array` o un objeto que implemente `Traversable`).
*   **Declaración de Tipo Object (PHP 7.2+):** `object`.
*   **Declaración de Tipo Mixed (PHP 8.0+):** `mixed` (puede ser cualquier tipo).
*   **Tipos de Unión (PHP 8.0+):** `int|string|null` (puede ser uno de varios tipos).

**Ejemplo de Declaración de Tipo de Parámetro:**

```php
<?php
// declare(strict_types=1); // Ver "Tipado Estricto" abajo

function sumarNumeros(int $a, int $b) {
    return $a + $b;
}

echo sumarNumeros(5, 10) . "<br>"; // 15
// echo sumarNumeros(5.5, 10.2); // Por defecto, PHP intenta coaccionar: 5.5 se convierte en 5, 10.2 se convierte en 10, resultado 15.
                              // Si strict_types=1, esto causaría un TypeError.
// echo sumarNumeros("5 manzanas", "10 naranjas"); // Por defecto, PHP intenta coaccionar "5 manzanas" a 5, etc.
                                         // Si strict_types=1, esto causaría un TypeError.

function saludar(string $nombre, ?string $titulo = null) { // ?string significa string o null (tipo anulable, PHP 7.1+)
    if ($titulo) {
        echo "¡Hola, $titulo $nombre!<br>";
    } else {
        echo "¡Hola, $nombre!<br>";
    }
}
saludar("Alicia");
saludar("Roberto", "Dr.");
// saludar(123); // TypeError si strict_types=1, o PHP podría intentar coaccionar 123 a "123"
?>
```

**Declaraciones de Tipo de Retorno (PHP 7+):**
Especificas el tipo de retorno después del paréntesis de cierre de la lista de parámetros, precedido por dos puntos (`:`).

```php
<?php
// declare(strict_types=1);

function calcularTotal(float $precio, int $cantidad): float {
    return $precio * $cantidad;
}

$total = calcularTotal(10.5, 3);
echo "Total: " . $total . "<br>"; // Total: 31.5

function obtenerSaludo(): string {
    return "¡Hola!";
    // return 123; // Causaría un TypeError si strict_types=1, o PHP coacciona 123 a "123"
}
echo obtenerSaludo() . "<br>";

// Para funciones que no deben retornar un valor, usa `void` (PHP 7.1+)
function registrarMensaje(string $mensaje): void {
    error_log($mensaje);
    // return "hecho"; // Esto causaría un error fatal, ya que las funciones void no pueden retornar un valor.
    // Un `return;` vacío está permitido.
}
registrarMensaje("¡Error crítico del sistema!");
?>
```

*   **Tipado Estricto (`declare(strict_types=1);`):**
    Por defecto, PHP intentará **coaccionar** (convertir) valores del tipo incorrecto al tipo escalar esperado si es posible (por ejemplo, pasar la cadena `"5"` a una función que espera un `int`).
    Para forzar la comprobación estricta de tipos, puedes usar la directiva `declare(strict_types=1);` al principio de tu archivo PHP. Esta declaración debe ser la primera instrucción en el archivo. Cuando el tipado estricto está habilitado, PHP lanzará un `TypeError` si el tipo de un argumento o valor de retorno no coincide exactamente con el tipo declarado (no hay coacción para tipos escalares).

    ```php
    <?php
    declare(strict_types=1); // Tipado estricto habilitado para este archivo

    function sumarEnteros(int $a, int $b): int {
        return $a + $b;
    }

    echo sumarEnteros(2, 3) . "<br>"; // 5
    // echo sumarEnteros(2.5, 3.5); // Esto ahora causaría un TypeError
    // echo sumarEnteros("2", "3");   // Esto también causaría un TypeError
    ?>
    ```
    La declaración de tipos hace el código más predecible y ayuda a detectar errores tempranamente.

## 3. Ámbito de las Variables en Funciones

Mencionamos el ámbito de las variables en "Conceptos Básicos de PHP", pero es crucial entender cómo funciona con las funciones.

*   **Ámbito Local:**
    Las variables declaradas dentro de una función son **locales** a esa función. Solo existen dentro de esa función y no pueden ser accedidas o modificadas desde fuera. Una vez que la función termina de ejecutarse, sus variables locales generalmente se destruyen.

    ```php
    <?php
    function miFuncion() {
        $variableLocal = "Soy local.";
        echo $variableLocal . "<br>";
    }
    miFuncion(); // Salida: Soy local.
    // echo $variableLocal; // Esto causaría un Error: Variable indefinida $variableLocal
    ?>
    ```

*   **Ámbito Global:**
    Las variables declaradas fuera de cualquier función tienen **ámbito global**. Sin embargo, las variables globales no son automáticamente accesibles dentro de las funciones.

    *   **Usando la Palabra Clave `global`:** Para acceder a una variable global desde dentro de una función, debes usar la palabra clave `global` antes del nombre de la variable dentro de la función.

        ```php
        <?php
        $mensajeGlobal = "¡Hola desde el ámbito global!";

        function accederGlobal() {
            global $mensajeGlobal; // Declara la intención de usar la variable global
            echo $mensajeGlobal . "<br>";
            $mensajeGlobal = "Mensaje global modificado."; // Modifica la variable global
        }

        accederGlobal(); // Salida: ¡Hola desde el ámbito global!
        echo $mensajeGlobal . "<br>"; // Salida: Mensaje global modificado.
        ?>
        ```

    *   **El Array `$GLOBALS`:** PHP también proporciona un array superglobal llamado `$GLOBALS`. Este es un array asociativo donde las claves son los nombres de todas las variables globales, y los valores son referencias a esas variables. Puedes usarlo para acceder a variables globales desde cualquier lugar, incluso dentro de funciones, sin necesitar la palabra clave `global`.

        ```php
        <?php
        $nombreApp = "Mi Aplicación Increíble";

        function mostrarNombreApp() {
            echo $GLOBALS['nombreApp'] . "<br>";
            $GLOBALS['nombreApp'] = "Mi Súper Aplicación Increíble";
        }

        mostrarNombreApp(); // Salida: Mi Aplicación Increíble
        echo $nombreApp . "<br>"; // Salida: Mi Súper Aplicación Increíble
        ?>
        ```
        Aunque `$GLOBALS` funciona, la palabra clave `global` a menudo se considera más explícita para declarar la intención dentro de una función. La dependencia excesiva de variables globales puede hacer que el código sea más difícil de gestionar y depurar, así que úsalas con moderación.

*   **Variables Estáticas dentro de Funciones:**
    Una variable `static` dentro de una función retiene su valor entre múltiples llamadas a esa función. Normalmente, cuando una función se completa, sus variables locales se destruyen. Las variables estáticas son una excepción.

    ```php
    <?php
    function contadorVisitas() {
        static $contador = 0; // Inicializada solo en la primera llamada
        $contador++;
        echo "Esta función ha sido llamada " . $contador . " veces.<br>";
    }

    contadorVisitas(); // Esta función ha sido llamada 1 veces.
    contadorVisitas(); // Esta función ha sido llamada 2 veces.
    contadorVisitas(); // Esta función ha sido llamada 3 veces.
    ?>
    ```
    Las variables estáticas son útiles para tareas como almacenar en caché resultados dentro de una función o rastrear la profundidad de funciones recursivas.

## 4. Funciones Incorporadas de PHP

PHP viene con una vasta biblioteca de cientos de funciones incorporadas que realizan una amplia gama de tareas. No necesitas escribir código para estas operaciones comunes tú mismo. Es altamente recomendable familiarizarte con ellas, ya que son eficientes y están bien probadas.

Aquí hay algunos ejemplos de categorías comunes:

### Funciones de Cadenas (String)

Manipulan cadenas (secuencias de caracteres).

*   `strlen($string)`: Devuelve la longitud de una cadena.
*   `strpos($haystack, $needle)`: Encuentra la posición de la primera ocurrencia de una subcadena en una cadena (sensible a mayúsculas/minúsculas).
*   `stripos($haystack, $needle)`: Versión insensible a mayúsculas/minúsculas de `strpos()`.
*   `substr($string, $start, $length)`: Devuelve una parte de una cadena.
*   `str_replace($search, $replace, $subject)`: Reemplaza todas las ocurrencias de la cadena de búsqueda con la cadena de reemplazo.
*   `strtolower($string)`: Convierte una cadena a minúsculas.
*   `strtoupper($string)`: Convierte una cadena a mayúsculas.
*   `ucfirst($string)`: Convierte el primer carácter de una cadena a mayúsculas.
*   `ucwords($string)`: Convierte el primer carácter de cada palabra en una cadena a mayúsculas.
*   `trim($string, $characters)`: Elimina espacios en blanco (u otros caracteres especificados) del principio y el final de una cadena.
*   `explode($delimiter, $string)`: Divide una cadena en un array por un delimitador especificado.
*   `implode($glue, $pieces)` (o `join()`): Une elementos de un array con una cadena.
*   `htmlspecialchars($string)`: Convierte caracteres HTML especiales a sus equivalentes de entidad (ej., `<` se convierte en `&lt;`). Crucial para prevenir Cross-Site Scripting (XSS) al mostrar entrada del usuario.

```php
<?php
$miCadena = "  ¡Hola, Mundo PHP!  ";
echo strlen($miCadena) . "<br>"; // 23 (incluye espacios iniciales/finales)
echo strpos($miCadena, "PHP") . "<br>"; // 13 (posición de 'P' en 'PHP')
echo substr($miCadena, 8, 10) . "<br>"; // "Mundo PHP!" (comienza en índice 8, longitud 10)
$nuevaCadena = str_replace("Mundo", "Universo", $miCadena);
echo $nuevaCadena . "<br>"; //   ¡Hola, Universo PHP!
echo strtolower($miCadena) . "<br>"; //   ¡hola, mundo php!
echo trim($miCadena) . "<br>"; // "¡Hola, Mundo PHP!" (sin espacios iniciales/finales)

$datosCsv = "manzana,banana,naranja";
$arrayFrutas = explode(",", $datosCsv);
print_r($arrayFrutas); // Array ( [0] => manzana [1] => banana [2] => naranja )

$partesNombre = ["Juan", "Pérez"];
$nombreCompleto = implode(" ", $partesNombre);
echo $nombreCompleto . "<br>"; // Juan Pérez
?>
```

### Funciones de Arrays

Manipulan arrays.

*   `count($array)` (o `sizeof()`): Devuelve el número de elementos en un array.
*   `is_array($variable)`: Comprueba si una variable es un array.
*   `array_push($array, $value1, ...)`: Añade uno o más elementos al final de un array.
*   `array_pop($array)`: Extrae (elimina y devuelve) el último elemento de un array.
*   `array_shift($array)`: Extrae (elimina y devuelve) el primer elemento de un array.
*   `array_unshift($array, $value1, ...)`: Añade uno o más elementos al principio de un array.
*   `array_merge($array1, $array2, ...)`: Fusiona uno o más arrays.
*   `array_keys($array)`: Devuelve todas las claves o un subconjunto de las claves de un array.
*   `array_values($array)`: Devuelve todos los valores de un array.
*   `in_array($needle, $haystack, $strict)`: Comprueba si un valor existe en un array.
*   **Funciones de ordenación:**
    *   `sort($array)`: Ordena un array en orden ascendente (valores). Reindexa.
    *   `rsort($array)`: Ordena un array en orden descendente (valores). Reindexa.
    *   `asort($array)`: Ordena un array asociativo por valores en orden ascendente, conservando las claves.
    *   `arsort($array)`: Ordena un array asociativo por valores en orden descendente, conservando las claves.
    *   `ksort($array)`: Ordena un array asociativo por claves en orden ascendente.
    *   `krsort($array)`: Ordena un array asociativo por claves en orden descendente.
*   `array_map($callback, $array1, ...)`: Aplica una función de callback a los elementos de los arrays dados.
*   `array_filter($array, $callback, $flag)`: Filtra elementos de un array usando una función de callback.

```php
<?php
$numeros = [3, 1, 4, 1, 5, 9, 2, 6];
echo count($numeros) . "<br>"; // 8

array_push($numeros, 7); // $numeros ahora es [3, 1, 4, 1, 5, 9, 2, 6, 7]
print_r($numeros); echo "<br>";

sort($numeros); // Ordena en el lugar
print_r($numeros); echo "<br>"; // [1, 1, 2, 3, 4, 5, 6, 7, 9]

$persona = ["nombre" => "Alicia", "edad" => 30, "ciudad" => "Nueva York"];
$claves = array_keys($persona);
print_r($claves); // Array ( [0] => nombre [1] => edad [2] => ciudad )
echo "<br>";

if (in_array(30, $persona)) {
    echo "30 está en el array persona.<br>";
}
?>
```

### Funciones Matemáticas

Realizan cálculos matemáticos.

*   `abs($number)`: Valor absoluto.
*   `round($number, $precision, $mode)`: Redondea un flotante.
*   `ceil($number)`: Redondea un número hacia arriba al entero más cercano.
*   `floor($number)`: Redondea un número hacia abajo al entero más cercano.
*   `rand($min, $max)` / `mt_rand($min, $max)`: Genera un entero aleatorio (`mt_rand` es generalmente preferido por mejor aleatoriedad).
*   `min($values)` o `min($array)`: Encuentra el valor más bajo.
*   `max($values)` o `max($array)`: Encuentra el valor más alto.
*   `pow($base, $exponent)`: Expresión exponencial.
*   `sqrt($number)`: Raíz cuadrada.

```php
<?php
echo abs(-7.5) . "<br>";   // 7.5
echo round(4.7) . "<br>"; // 5
echo round(4.3) . "<br>"; // 4
echo ceil(4.3) . "<br>";  // 5
echo floor(4.8) . "<br>"; // 4
echo mt_rand(1, 10) . "<br>"; // Número aleatorio entre 1 y 10 (inclusive)
echo min(5, 2, 8, 1) . "<br>"; // 1
echo max([5, 2, 8, 1]) . "<br>"; // 8 (puede tomar un array)
echo pow(2, 8) . "<br>"; // 256 (2 elevado a la 8)
echo sqrt(64) . "<br>"; // 8
?>
```

### Funciones de Fecha y Hora

Trabajan con fechas y horas.

*   `date($format, $timestamp)`: Formatea una hora/fecha local.
*   `time()`: Devuelve la marca de tiempo Unix actual (segundos desde el 1 de enero de 1970 00:00:00 GMT).
*   `strtotime($time, $now)`: Convierte una descripción textual de fecha/hora en inglés a una marca de tiempo Unix.
*   `mktime($hour, $minute, $second, $month, $day, $year)`: Obtiene la marca de tiempo Unix para una fecha.

```php
<?php
echo date("Y-m-d H:i:s") . "<br>"; // ej., 2023-10-27 14:30:00
echo time() . "<br>"; // Marca de tiempo Unix actual

$marcaTiempoProximaSemana = strtotime("+1 week");
echo date("Y-m-d", $marcaTiempoProximaSemana) . "<br>"; // Fecha de una semana a partir de ahora

$marcaTiempoPersonalizada = mktime(10, 30, 0, 12, 25, 2024); // 10:30:00, Dic 25, 2024
echo date("l, F j, Y", $marcaTiempoPersonalizada) . "<br>"; // ej., Wednesday, December 25, 2024 (adaptar al español si es necesario en la salida)
// Salida en español: Miércoles, Diciembre 25, 2024 (requeriría setlocale o IntlDateFormatter para nombres de días/meses localizados)
?>
```

**Fomentar la consulta de funciones en el manual de PHP:**
Esto es solo una pequeña fracción de las funciones disponibles. El manual oficial de PHP ([https://www.php.net/manual/es/](https://www.php.net/manual/es/)) es un recurso invaluable. Siempre que necesites realizar una tarea común, es probable que haya una función incorporada para ello. ¡Consulta siempre el manual primero!

## 5. Funciones Anónimas (Closures)

Las funciones anónimas, también conocidas como **closures**, son funciones sin un nombre específico. Se definen sobre la marcha y pueden ser asignadas a variables o pasadas como argumentos a otras funciones.

**Sintaxis:**

```php
<?php
$variable = function($parametro1, $parametro2) use ($variableDelAmbitoPadre) {
    // cuerpo de la función
    return $valor;
}; // Nota el punto y coma al final cuando se asigna a una variable
?>
```

*   `function($parametro1, ...) { ... }`: La definición de la función anónima.
*   `use ($variableDelAmbitoPadre)`: Esto es opcional. La palabra clave `use` permite a la función anónima "heredar" o "cerrar sobre" variables de su ámbito padre (el ámbito en el que se define). Sin `use`, la función anónima no puede acceder a variables del ámbito circundante, ni siquiera las globales, a menos que se pasen explícitamente a través de `$GLOBALS` o como parámetros.

**Asignando funciones anónimas a variables:**

```php
<?php
$decirHola = function($nombre) {
    echo "¡Hola, " . $nombre . "!<br>";
};

$decirHola("Desarrollador"); // Salida: ¡Hola, Desarrollador!
?>
```

**Usándolas como funciones de callback:**
Las funciones anónimas son particularmente útiles como funciones de callback para funciones como `array_map`, `array_filter`, `usort`, etc.

```php
<?php
$numeros = [1, 2, 3, 4, 5];

// Usando array_map con una función anónima para elevar números al cuadrado
$numerosCuadrado = array_map(function($n) {
    return $n * $n;
}, $numeros);
print_r($numerosCuadrado); // Array ( [0] => 1 [1] => 4 [2] => 9 [3] => 16 [4] => 25 )
echo "<br>";

// Usando array_filter con una función anónima para obtener números pares
$numerosPares = array_filter($numeros, function($n) {
    return $n % 2 == 0;
});
print_r($numerosPares); // Array ( [1] => 2 [3] => 4 )
echo "<br>";
?>
```

**Heredando variables del ámbito padre usando `use`:**

```php
<?php
$prefijoMensaje = "Registro del Sistema";

$logger = function($mensaje) use ($prefijoMensaje) {
    echo $prefijoMensaje . ": " . $mensaje . "<br>";
    // $prefijoMensaje = "Nuevo Prefijo"; // Esto cambiaría la copia local dentro del closure,
                                        // no el $prefijoMensaje original a menos que se pase por referencia.
};

$logger("Usuario inició sesión."); // Salida: Registro del Sistema: Usuario inició sesión.

// Para modificar la variable del ámbito padre, pásala por referencia en `use`
$contador = 0;
$incrementar = function() use (&$contador) {
    $contador++;
};
$incrementar();
$incrementar();
echo "Contador: " . $contador . "<br>"; // Contador: 2
?>
```

## 6. Funciones Flecha (Arrow Functions) (PHP 7.4+)

Las funciones flecha proporcionan una sintaxis más concisa para crear funciones anónimas, especialmente cuando el cuerpo de la función consiste en una única expresión.

**Sintaxis:**

`fn($parametro1, $parametro2) => expresion;`

*   `fn`: Palabra clave para definir una función flecha.
*   `($parametro1, ...)`: Lista de parámetros.
*   `=>`: Separa los parámetros de la expresión.
*   `expresion`: Una única expresión cuyo resultado se retorna automáticamente. No hay necesidad de la palabra clave `return`.

**Características Clave:**

*   **Acceso automático a variables del ámbito externo:** Las funciones flecha capturan automáticamente variables del ámbito padre por valor. No necesitas la palabra clave `use`.
*   **Limitaciones:**
    *   Solo pueden contener una expresión.
    *   Esta única expresión es implícitamente la declaración de retorno.
    *   No puedes tener múltiples declaraciones o un bloque de código `{...}`.
    *   No pueden modificar variables del ámbito externo directamente (se capturan por valor).

**Ejemplo:**

```php
<?php
$numeros = [1, 2, 3, 4, 5];
$multiplicador = 10;

// Usando array_map con una función flecha
$numerosMultiplicados = array_map(fn($n) => $n * $multiplicador, $numeros);
// $multiplicador está automáticamente disponible desde el ámbito externo

print_r($numerosMultiplicados); // Array ( [0] => 10 [1] => 20 [2] => 30 [3] => 40 [4] => 50 )
echo "<br>";

$sumador = fn($x, $y) => $x + $y;
echo $sumador(5, 7) . "<br>"; // 12

// Ejemplo de ámbito (por valor)
$mensaje = "Hola";
$saludar = fn($nombre) => $mensaje . ", " . $nombre; // $mensaje se captura por valor
$mensaje = "Adiós"; // Cambiar $mensaje aquí no afecta al capturado por la función flecha

echo $saludar("Alicia") . "<br>"; // Salida: Hola, Alicia
?>
```
Las funciones flecha son excelentes para operaciones de callback cortas y simples, haciendo el código más legible.

## 7. Funciones Recursivas

Una función recursiva es una función que se llama a sí misma, ya sea directa o indirectamente a través de otra función. La recursión es una técnica poderosa de resolución de problemas donde un problema se puede dividir en subproblemas más pequeños y auto-similares.

**Componentes Esenciales de una Función Recursiva:**

1.  **Caso(s) Base:** Una condición que detiene la recursión. Sin un caso base, la función se llamaría a sí misma indefinidamente, lo que llevaría a un error de "desbordamiento de pila" (la pila de llamadas a funciones se queda sin memoria).
2.  **Paso Recursivo:** La parte de la función donde se llama a sí misma, usualmente con argumentos modificados que la acercan al caso base.

**Ejemplo: Factorial**
El factorial de un entero no negativo `n` (denotado como `n!`) es el producto de todos los enteros positivos menores o iguales a `n`.
*   `0! = 1` (caso base)
*   `n! = n * (n-1)!` (paso recursivo)

```php
<?php
function factorial($n) {
    // Caso base:
    if ($n < 0) { // El factorial no está definido para números negativos
        return null; // O lanzar una excepción
    }
    if ($n == 0) {
        return 1;
    } else {
        // Paso recursivo:
        return $n * factorial($n - 1);
    }
}

echo "Factorial de 5 es: " . factorial(5) . "<br>"; // 5 * 4 * 3 * 2 * 1 = 120
echo "Factorial de 0 es: " . factorial(0) . "<br>"; // 1
echo "Factorial de 3 es: " . factorial(3) . "<br>"; // 3 * 2 * 1 = 6
?>
```
**Cómo funciona `factorial(3)`:**
1.  `factorial(3)` llama a `3 * factorial(2)`
2.  `factorial(2)` llama a `2 * factorial(1)`
3.  `factorial(1)` llama a `1 * factorial(0)`
4.  `factorial(0)` alcanza el caso base y retorna `1`.
5.  `factorial(1)` retorna `1 * 1 = 1`.
6.  `factorial(2)` retorna `2 * 1 = 2`.
7.  `factorial(3)` retorna `3 * 2 = 6`.

**Posibles Desventajas de la Recursión:**

*   **Recursión Infinita:** Si el caso base falta o es incorrecto, la función puede que nunca deje de llamarse a sí misma, lo que lleva a un error de desbordamiento de pila.
*   **Desbordamiento de Pila (Stack Overflow):** Cada llamada a función usa memoria en la pila de llamadas. La recursión profunda (muchas llamadas anidadas) puede agotar esta memoria. Para problemas que requieren recursión muy profunda, una solución iterativa (usando bucles) podría ser más eficiente en PHP.
*   **Rendimiento:** Las llamadas recursivas a veces pueden ser menos eficientes que las soluciones iterativas debido a la sobrecarga de las llamadas a funciones. Sin embargo, para algunos problemas, la recursión proporciona una solución mucho más limpia e intuitiva.

La recursión es una herramienta poderosa para ciertos tipos de problemas, como recorrer estructuras de tipo árbol (por ejemplo, sistemas de archivos, comentarios anidados) o algunos cálculos matemáticos. Sin embargo, siempre asegúrate de tener un caso base claro.

Esto concluye nuestra exploración de las funciones en PHP. ¡Dominar las funciones es clave para escribir código PHP efectivo y mantenible!
[end of php_tutorial/03_php_functions_es.md]
