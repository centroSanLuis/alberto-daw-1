# Conceptos Básicos de PHP

Ahora que tienes configurado tu entorno PHP, es momento de aprender los bloques de construcción fundamentales del lenguaje PHP. Esta sección cubre la sintaxis básica, variables, tipos de datos, operadores y estructuras de control.

## 1. Sintaxis Básica de PHP

Los scripts PHP generalmente se incrustan dentro de archivos HTML. El intérprete de PHP procesa solo el código dentro de etiquetas especiales de PHP.

### Etiquetas PHP

*   **Etiquetas Estándar:** `<?php ... ?>`
    Esta es la forma más común y recomendada para delimitar código PHP.
    ```php
    <?php
    echo "¡Hola desde etiquetas PHP estándar!";
    ?>
    ```

*   **Etiquetas Cortas de Eco:** `<?= ... ?>`
    Este es un atajo para `<?php echo ... ?>`. Es conveniente para imprimir rápidamente una variable o expresión. Está habilitado por defecto en las versiones modernas de PHP.
    ```html
    <p>Mi nombre es <?= "Juan Pérez"; ?>.</p>
    ```

Aunque existen otros estilos de etiquetas como `<script language="php">...</script>` y etiquetas estilo ASP (`<% ... %>`), son menos comunes, a menudo obsoletas o requieren configuración especial. **Es mejor ceñirse a las etiquetas estándar `<?php ... ?>` para máxima compatibilidad y claridad.**

### Escapando de HTML

El código PHP se procesa en el servidor. Cuando el intérprete de PHP encuentra código fuera de sus etiquetas, simplemente envía ese contenido directamente al navegador. Esto te permite mezclar fácilmente PHP con HTML.

```html
<!DOCTYPE html>
<html>
<head>
    <title>Ejemplo PHP</title>
</head>
<body>
    <h1><?php echo "Bienvenido a Mi Página"; ?></h1>
    <p>Este es un párrafo HTML estándar.</p>
    <?php
    // Esto es código PHP
    $mensaje = "¡PHP es divertido!";
    echo "<p>" . $mensaje . "</p>";
    ?>
    <p>Otro párrafo HTML.</p>
</body>
</html>
```

### Separación de Instrucciones: Punto y Coma

Como muchos otros lenguajes de programación (C, Java, JavaScript), PHP utiliza un punto y coma (`;`) para separar instrucciones individuales.

```php
<?php
$nombre = "Alicia";
echo $nombre;
$edad = 30;
echo $edad;
?>
```
La etiqueta de cierre de PHP `?>` implica automáticamente un punto y coma, por lo que no necesitas estrictamente uno para la última instrucción en un bloque PHP. Sin embargo, es una buena práctica incluirlo siempre por consistencia.

### Comentarios

Los comentarios son partes del código que son ignoradas por el intérprete de PHP. Se utilizan para explicar el código, tomar notas o deshabilitar temporalmente partes de un script.

*   **Comentarios de una sola línea:** Comienzan con `//` o `#`.
    ```php
    <?php
    // Este es un comentario de una sola línea
    $x = 5; // Este comentario explica la asignación de la variable

    # Este también es un comentario de una sola línea (menos común en PHP, más común en scripts de shell)
    $y = 10;
    ?>
    ```

*   **Comentarios multilínea:** Comienzan con `/*` y terminan con `*/`.
    ```php
    <?php
    /*
    Este es un
    bloque de comentario multilínea.
    Puede abarcar varias líneas.
    */
    $suma = $x + $y;
    echo $suma;
    ?>
    ```

### Sensibilidad a Mayúsculas y Minúsculas (Case Sensitivity)

Es importante entender las reglas de sensibilidad a mayúsculas y minúsculas de PHP:

*   **Los nombres de las variables SON sensibles a mayúsculas y minúsculas.**
    ```php
    <?php
    $nombre = "Alicia";
    echo $nombre; // Muestra "Alicia"
    // echo $Nombre; // Esto causaría un error o no mostraría nada si $Nombre no está definida
    ?>
    ```

*   **Los nombres de funciones, nombres de clases, nombres de métodos y palabras clave de PHP (por ejemplo, `echo`, `if`, `else`, `while`, `class`) son generalmente INSENSIBLES a mayúsculas y minúsculas.**
    ```php
    <?php
    ECHO "¡Hola!"; // Funciona
    function miFuncion() {
        PRINT "Dentro de la función."; // Funciona
    }
    miFUNCION(); // Funciona

    // Sin embargo, por consistencia y legibilidad, es mejor práctica ceñirse a
    // un uso consistente de mayúsculas/minúsculas para llamadas a funciones y palabras clave (generalmente minúsculas para funciones y palabras clave).
    ?>
    ```
    **Recomendación:** Siempre usa un uso consistente de mayúsculas/minúsculas (por ejemplo, `camelCase` para variables, `PascalCase` para clases, minúsculas para funciones y palabras clave) para mejorar la legibilidad del código y evitar posibles confusiones.

## 2. Variables

Las variables son contenedores para almacenar información como texto, números o arrays.

### Reglas de Declaración y Nomenclatura

*   Los nombres de las variables en PHP deben comenzar con un signo de dólar (`$`).
*   El primer carácter después del `$` debe ser una letra (a-z, A-Z) o un guion bajo (`_`).
*   Los caracteres subsecuentes pueden ser letras, números (0-9) o guiones bajos.
*   Los nombres de las variables son sensibles a mayúsculas y minúsculas (`$nombre` y `$Nombre` son dos variables diferentes).

```php
<?php
$nombre = "Juan";
$_edad = 30;
$direccion_email_usuario = "juan.perez@example.com";
$totalAmount = 100.50; // MontoTotal
// $1variable = "error"; // Inválido: comienza con un número después de $
// $variable-nombre = "error"; // Inválido: contiene un guion
?>
```

PHP es un **lenguaje de tipado dinámico (débilmente tipado)**. Esto significa que no necesitas declarar el tipo de dato de una variable antes de asignarle un valor. PHP determina automáticamente el tipo basándose en el valor asignado.

### Asignación de Valores

Los valores se asignan a las variables usando el operador de asignación (`=`).

```php
<?php
$primerNombre = "Juana"; // Asigna la cadena "Juana" a $primerNombre
$edad = 25;         // Asigna el entero 25 a $edad
$esEstudiante = true; // Asigna el booleano true a $esEstudiante

// Las variables también pueden ser asignadas con el valor de otras variables
$nombrePersona = $primerNombre;
echo $nombrePersona; // Muestra "Juana"
?>
```

### Ámbito de las Variables (Scope)

El ámbito de una variable define en qué parte de tu script se puede acceder a ella.

*   **Ámbito Local:**
    Las variables declaradas dentro de una función son locales a esa función. Solo se puede acceder a ellas desde dentro de esa función.
    ```php
    <?php
    function saludar() {
        $mensaje = "¡Hola desde dentro de la función!"; // Variable local
        echo $mensaje;
    }

    saludar();
    // echo $mensaje; // Esto causaría un error porque $mensaje no está definida globalmente
    ?>
    ```

*   **Ámbito Global:**
    Las variables declaradas fuera de cualquier función tienen ámbito global. Se puede acceder a ellas desde cualquier parte del script *excepto* desde dentro de una función por defecto.
    ```php
    <?php
    $variableGlobal = "¡Soy global!";

    function probarGlobal() {
        // echo $variableGlobal; // Esto causaría un error o intentaría acceder a una $variableGlobal local no definida
    }
    probarGlobal();
    echo $variableGlobal; // Muestra "¡Soy global!"
    ?>
    ```
    Para acceder a una variable global desde dentro de una función, debes usar la palabra clave `global`:
    ```php
    <?php
    $contador = 0;

    function incrementarContador() {
        global $contador; // Declara que queremos usar la $contador global
        $contador++;
        echo "Contador dentro de la función: " . $contador . "<br>";
    }

    incrementarContador(); // Contador dentro de la función: 1
    incrementarContador(); // Contador dentro de la función: 2
    echo "Contador fuera de la función: " . $contador; // Contador fuera de la función: 2
    ?>
    ```
    Alternativamente, puedes usar el array `$GLOBALS` (ver Superglobales).

*   **Ámbito Estático (Static Scope):**
    Cuando una variable dentro de una función se declara como `static`, retiene su valor entre múltiples llamadas a esa función. Normalmente, cuando una función termina de ejecutarse, todas sus variables locales se destruyen.
    ```php
    <?php
    function contadorEstatico() {
        static $num = 0; // Variable estática
        $num++;
        echo $num . "<br>";
    }

    contadorEstatico(); // Muestra: 1
    contadorEstatico(); // Muestra: 2
    contadorEstatico(); // Muestra: 3
    ?>
    ```

### Superglobales

Las superglobales son variables predefinidas en PHP que siempre están accesibles, independientemente del ámbito. No necesitas usar la palabra clave `global` para acceder a ellas desde dentro de funciones o métodos.

Aquí están algunas de las superglobales más comúnmente usadas:

*   `$_GET`: Un array asociativo de variables pasadas al script actual a través de los parámetros de la URL (cadena de consulta).
    *   Ejemplo: Si la URL es `ejemplo.php?nombre=Alicia&edad=30`, entonces `$_GET['nombre']` es "Alicia" y `$_GET['edad']` es "30".
*   `$_POST`: Un array asociativo de variables pasadas al script actual a través del método HTTP POST (típicamente desde formularios HTML).
*   `$_REQUEST`: Un array asociativo que por defecto contiene el contenido de `$_GET`, `$_POST` y `$_COOKIE`. Generalmente se recomienda usar `$_GET` o `$_POST` directamente por claridad y seguridad.
*   `$_SERVER`: Un array que contiene información como cabeceras, rutas y ubicaciones de scripts.
    *   Ejemplos: `$_SERVER['PHP_SELF']` (nombre del archivo del script que se está ejecutando actualmente), `$_SERVER['HTTP_HOST']` (nombre del host), `$_SERVER['REQUEST_METHOD']` (GET, POST, etc.).
*   `$_SESSION`: Un array asociativo que contiene variables de sesión. Se usa para persistir datos del usuario a través de múltiples solicitudes de página. Necesitas iniciar una sesión con `session_start()` antes de usarla.
*   `$_COOKIE`: Un array asociativo de variables pasadas al script actual a través de Cookies HTTP.
*   `$_FILES`: Un array asociativo de elementos subidos al script actual a través del método HTTP POST. Se usa para manejar la subida de archivos.
*   `$_ENV`: Un array asociativo de variables pasadas al script actual a través del método del entorno.
*   `$GLOBALS`: Un array asociativo que contiene referencias a todas las variables que están actualmente definidas en el ámbito global del script. Las claves de este array son los nombres de las variables globales.
    ```php
    <?php
    $miGlobal = "¡Hola desde el ámbito global!";

    function accederGlobalViaGLOBALS() {
        echo $GLOBALS['miGlobal'];
    }

    accederGlobalViaGLOBALS(); // Muestra: ¡Hola desde el ámbito global!
    ?>
    ```

## 3. Tipos de Datos

PHP soporta varios tipos de datos. Como lenguaje de tipado dinámico, el tipo de una variable no se establece por declaración, sino que se determina en tiempo de ejecución por el valor que contiene.

### Explicación del Tipado Dinámico

En PHP, no le dices explícitamente a PHP qué tipo de dato contendrá una variable. PHP lo averigua automáticamente:

```php
<?php
$dato = "Hola";      // $dato es una cadena (string)
echo gettype($dato) . "<br>"; // Salida: string

$dato = 123;          // Ahora $dato es un entero (integer)
echo gettype($dato) . "<br>"; // Salida: integer

$dato = true;         // Ahora $dato es un booleano (boolean)
echo gettype($dato) . "<br>"; // Salida: boolean
?>
```
`gettype()` es una función que devuelve el tipo de una variable.

### Tipos Escalares

Los tipos escalares representan valores únicos.

*   **String (Cadena):** Una secuencia de caracteres.
    *   Puede encerrarse en comillas simples (`'...'`) o comillas dobles (`"..."`).
    *   **Comillas simples:** Tratan el contenido literalmente. Las variables y la mayoría de las secuencias de escape no se interpretan.
        ```php
        $nombre = 'Alicia';
        $texto = 'Mi nombre es $nombre.'; // Salida: Mi nombre es $nombre.
        $texto2 = 'Esto es una comilla simple: \'';
        ```
    *   **Comillas dobles:** Interpretan variables (interpolación de variables) y secuencias de escape especiales (por ejemplo, `\n` para nueva línea, `\t` para tabulación, `\$` para un signo de dólar literal).
        ```php
        $nombre = "Roberto";
        $texto = "Mi nombre es $nombre.";   // Salida: Mi nombre es Roberto.
        $texto2 = "Esto es una nueva línea:\nY esto es una tabulación:\t.";
        $texto3 = "Él dijo, \"¡Hola!\""; // Escapando comillas dobles
        ```
    *   **Concatenación de Cadenas:** El operador punto (`.`) se usa para unir cadenas.
        ```php
        $saludo = "Hola";
        $sujeto = "Mundo";
        $saludoCompleto = $saludo . ", " . $sujeto . "!"; // "Hola, Mundo!"
        echo $saludoCompleto;
        ```

*   **Integer (Entero):** Números enteros (positivos, negativos o cero) sin puntos decimales.
    ```php
    <?php
    $edad = 30;
    $numero_negativo = -100;
    $numero_octal = 0123; // Octal (equivalente a 83 decimal)
    $numero_hex = 0x1A;   // Hexadecimal (equivalente a 26 decimal)
    $numero_binario = 0b1010; // Binario (equivalente a 10 decimal)
    var_dump($edad, $numero_negativo); // var_dump() muestra tipo y valor
    ?>
    ```

*   **Float (Número de punto flotante o Double):** Números con un punto decimal o en forma exponencial.
    ```php
    <?php
    $precio = 19.99;
    $pi = 3.14159;
    $cientifico = 3.0e8; // 3 * 10^8
    var_dump($precio, $cientifico);
    ?>
    ```

*   **Boolean (Booleano):** Representa dos estados posibles: `true` (verdadero) o `false` (falso). Los valores booleanos son insensibles a mayúsculas/minúsculas (`true`, `TRUE`, `True` son todos lo mismo).
    ```php
    <?php
    $estaLogueado = true;
    $esAdmin = false;
    var_dump($estaLogueado, $esAdmin);
    ?>
    ```
    *   **Valores Truthy y Falsy:** Al convertir valores no booleanos a booleanos, PHP considera ciertos valores como `false`:
        *   El booleano `false` mismo.
        *   El entero `0`.
        *   El flotante `0.0`.
        *   La cadena vacía `""` y la cadena `"0"`.
        *   Un array con cero elementos.
        *   El tipo especial `NULL`.
        *   Objetos SimpleXML creados desde etiquetas vacías.
        Todos los demás valores se consideran `true` (truthy).

### Tipos Compuestos

Los tipos compuestos pueden contener múltiples valores.

*   **Array (Arreglo):** Un mapa ordenado que asocia valores con claves.
    *   **Arrays indexados:** Arrays con índices numéricos (las claves usualmente comienzan desde 0).
        ```php
        <?php
        // Usando la construcción array()
        $colores = array("Rojo", "Verde", "Azul");
        // Usando la sintaxis corta de array [] (PHP 5.4+)
        $frutas = ["Manzana", "Banana", "Cereza"];

        echo $colores[0]; // Muestra: Rojo
        echo $frutas[1]; // Muestra: Banana
        $frutas[1] = "Naranja"; // Modificar un elemento
        var_dump($frutas);
        ?>
        ```
    *   **Arrays asociativos:** Arrays con claves nombradas (cadenas).
        ```php
        <?php
        $persona = array(
            "nombre" => "Alicia",
            "edad" => 30,
            "ciudad" => "Nueva York"
        );
        // Usando sintaxis corta
        $coche = [
            "marca" => "Toyota",
            "modelo" => "Camry",
            "año" => 2021
        ];

        echo $persona["nombre"]; // Muestra: Alicia
        echo $coche["modelo"];  // Muestra: Camry
        $persona["email"] = "alicia@example.com"; // Añadir un nuevo elemento
        var_dump($persona);
        ?>
        ```
    *   **Arrays multidimensionales:** Arrays que contienen otros arrays.
        ```php
        <?php
        $estudiantes = [
            ["nombre" => "Juan", "edad" => 20, "nota" => "A"],
            ["nombre" => "Juana", "edad" => 22, "nota" => "B"],
            ["nombre" => "Pérez",  "edad" => 21, "nota" => "A+"]
        ];
        echo $estudiantes[1]["nombre"]; // Muestra: Juana
        echo $estudiantes[0]["nota"]; // Muestra: A
        var_dump($estudiantes);
        ?>
        ```

*   **Object (Objeto):** Instancias de clases. Los objetos encapsulan datos (propiedades) y comportamiento (métodos). Cubriremos objetos y clases en detalle en la sección de Programación Orientada a Objetos (POO). Por ahora, solo necesitas saber que es un tipo de dato.
    ```php
    <?php
    class MiClase {
        public $propiedad = "¡Hola Objeto!";
    }
    $obj = new MiClase();
    echo $obj->propiedad; // Accediendo a la propiedad del objeto
    var_dump($obj);
    ?>
    ```

### Tipos Especiales

*   **NULL:** Un tipo de dato especial que representa una variable sin valor. Una variable se considera `NULL` si:
    *   Se le ha asignado la constante `NULL`.
    *   Aún no se le ha asignado ningún valor.
    *   Ha sido `unset()`.
    `NULL` es insensible a mayúsculas/minúsculas (`null`, `NULL`, `Null` son lo mismo).
    ```php
    <?php
    $sinValor = NULL;
    $variableIndefinida; // Automáticamente NULL, pero genera un aviso si se accede antes de la asignación

    var_dump($sinValor);
    // var_dump($variableIndefinida); // Mostraría NULL pero también un aviso
    $x = 10;
    unset($x); // $x ahora es NULL
    // var_dump($x); // Mostraría NULL pero también un aviso
    ?>
    ```

*   **Resource (Recurso):** Una variable especial que contiene una referencia a un recurso externo, como una conexión a una base de datos, un manejador de archivos o un lienzo de imagen. Típicamente no creas variables de recurso directamente; son devueltas por funciones específicas.
    ```php
    <?php
    // Ejemplo: Abriendo un archivo
    // Asegúrate de que 'ejemplo.txt' exista o la función fopen podría fallar.
    // Puedes crear un archivo 'ejemplo.txt' vacío en el mismo directorio que este script.
    $manejadorArchivo = @fopen("ejemplo.txt", "r"); // fopen devuelve un recurso o false en error
    if ($manejadorArchivo) {
        echo gettype($manejadorArchivo) . "<br>"; // Salida: resource
        var_dump($manejadorArchivo);
        fclose($manejadorArchivo); // Importante cerrar los recursos
    } else {
        echo "Falló al abrir el archivo.";
    }
    ?>
    ```

### Conversión de Tipos (Type Juggling y Casting)

PHP a menudo convierte tipos automáticamente cuando es necesario en una expresión ("type juggling" o conversión automática de tipos).

```php
<?php
$cadenaNumero = "10"; // Cadena
$entero = 5;      // Entero
$resultado = $cadenaNumero + $entero; // $cadenaNumero se convierte a entero para la suma
echo $resultado; // Salida: 15
var_dump($resultado); // Salida: int(15)

$valorBooleano = true;
$suma = 1 + $valorBooleano; // true se convierte a 1
echo $suma; // Salida: 2
?>
```

También puedes convertir tipos explícitamente usando **type casting (conversión explícita de tipos)**:

*   `(int)`, `(integer)` - convertir a entero
*   `(bool)`, `(boolean)` - convertir a booleano
*   `(float)`, `(double)`, `(real)` - convertir a flotante
*   `(string)` - convertir a cadena
*   `(array)` - convertir a array
*   `(object)` - convertir a objeto
*   `(unset)` - convertir a NULL (PHP 7.2+)

```php
<?php
$numeroFlotante = 123.789;
$numeroEntero = (int) $numeroFlotante;
echo $numeroEntero; // Salida: 123 (la parte fraccionaria se trunca)

$valorCadena = "false";
$valorBool = (bool) $valorCadena; // Cadena no vacía se convierte a true
var_dump($valorBool); // Salida: bool(true)

$num = 100;
$cadenaNum = (string) $num;
var_dump($cadenaNum); // Salida: string(3) "100"

$valor = 10;
$arr = (array) $valor;
var_dump($arr); // Salida: array(1) { [0]=> int(10) }
?>
```

## 4. Operadores

Los operadores son símbolos que realizan operaciones sobre variables y valores.

### Operadores Aritméticos

Realizan cálculos matemáticos.

*   `+` : Suma
*   `-` : Resta
*   `*` : Multiplicación
*   `/` : División
*   `%` : Módulo (resto de una división)
*   `**`: Exponenciación (PHP 5.6+)

```php
<?php
$x = 10;
$y = 3;
echo $x + $y;  // 13
echo $x - $y;  // 7
echo $x * $y;  // 30
echo $x / $y;  // 3.333...
echo $x % $y;  // 1
echo 2 ** 4; // 16 (2 elevado a la potencia de 4)
?>
```

### Operadores de Asignación

Asignan valores a variables.

*   `=`   : Asignación básica (`$a = $b`)
*   `+=`  : Asignación con suma (`$a += $b` es `$a = $a + $b`)
*   `-=`  : Asignación con resta (`$a -= $b` es `$a = $a - $b`)
*   `*=`  : Asignación con multiplicación (`$a *= $b` es `$a = $a * $b`)
*   `/=`  : Asignación con división (`$a /= $b` es `$a = $a / $b`)
*   `%=`  : Asignación con módulo (`$a %= $b` es `$a = $a % $b`)
*   `.=`  : Asignación con concatenación (`$a .= $b` es `$a = $a . $b`) (para cadenas)

```php
<?php
$a = 5;
$b = 3;
$a += $b; // $a ahora es 8
echo $a . "<br>";

$cad = "Hola";
$cad .= " Mundo!"; // $cad ahora es "Hola Mundo!"
echo $cad . "<br>";
?>
```

### Operadores de Comparación

Comparan dos valores y devuelven un booleano (`true` o `false`).

*   `==`  : Igual (valor). Puede ocurrir conversión de tipos.
*   `===` : Idéntico (valor y tipo). Sin conversión de tipos.
*   `!=`  : No igual (valor).
*   `<>`  : No igual (valor, alternativa a `!=`).
*   `!==` : No idéntico (valor o tipo).
*   `<`   : Menor que.
*   `>`   : Mayor que.
*   `<=`  : Menor o igual que.
*   `>=`  : Mayor o igual que.
*   `<=>` : Operador nave espacial (PHP 7+). Devuelve -1 si el izquierdo es menor que el derecho, 0 si son iguales, 1 si el izquierdo es mayor que el derecho.

```php
<?php
$x = 5;
$y = "5";

var_dump($x == $y);  // bool(true) porque "5" se convierte a 5
var_dump($x === $y); // bool(false) porque $x es int, $y es string

var_dump($x != $y);  // bool(false)
var_dump($x !== $y); // bool(true)

$a = 10;
$b = 20;
var_dump($a < $b);   // bool(true)

var_dump(5 <=> 5); // int(0)
var_dump(5 <=> 10); // int(-1)
var_dump(10 <=> 5); // int(1)
?>
```
**Diferencia entre `==` y `===`:**
Siempre prefiere `===` para comparaciones si también quieres asegurar que los tipos sean los mismos, ya que es más estricto y puede prevenir errores sutiles causados por la conversión de tipos. Usa `==` solo cuando necesites específicamente comparar valores después de la conversión de tipos.

### Operadores Lógicos

Combinan declaraciones condicionales.

*   `&&` (y): Verdadero si ambos operandos son verdaderos.
*   `||` (o) : Verdadero si alguno de los operandos es verdadero.
*   `!` (no) : Verdadero si el operando es falso.
*   `and`     : Y lógico (menor precedencia que `&&`).
*   `or`      : O lógico (menor precedencia que `||`).
*   `xor`     : O exclusivo lógico (XOR - verdadero si un operando es verdadero, pero no ambos).

```php
<?php
$edad = 25;
$esCiudadano = true;

if ($edad >= 18 && $esCiudadano) {
    echo "Puede votar.<br>";
}

if ($edad < 18 || !$esCiudadano) {
    echo "No puede votar.<br>";
}

// Usando 'and' (nota la diferencia de precedencia con la asignación)
// ($resultado = true and false;) asignaría true a $resultado y luego evaluaría (true and false)
// $resultado = (true and false); // $resultado es false
?>
```
**Cortocircuito (Short-circuiting):** Los operadores lógicos `&&` y `||` realizan evaluación de cortocircuito.
*   Para `&&`, si el operando izquierdo es `false`, el operando derecho no se evalúa porque el resultado siempre será `false`.
*   Para `||`, si el operando izquierdo es `true`, el operando derecho no se evalúa porque el resultado siempre será `true`.
Esto puede ser útil para el rendimiento o para evitar errores:
```php
<?php
$divisor = 0;
// if ($divisor != 0 && (10 / $divisor) > 2) { // Esto causaría un error de división por cero
//     echo "El resultado es mayor que 2";
// }

if ($divisor != 0 && (10 / $divisor) > 2) { // El cortocircuito previene la división por cero
    echo "El resultado es mayor que 2";
} else {
    echo "Condición no cumplida o división por cero evitada.<br>";
}
?>
```

### Operadores de Incremento/Decremento

Aumentan o disminuyen el valor de una variable en uno.

*   `++$var` (Pre-incremento): Incrementa `$var` en uno, luego devuelve `$var`.
*   `$var++` (Post-incremento): Devuelve `$var`, luego incrementa `$var` en uno.
*   `--$var` (Pre-decremento): Decrementa `$var` en uno, luego devuelve `$var`.
*   `$var--` (Post-decremento): Devuelve `$var`, luego decrementa `$var` en uno.

```php
<?php
$a = 5;
echo ++$a; // 6 ($a se convierte en 6, luego se imprime 6)
echo $a;   // 6

$b = 5;
echo $b++; // 5 (se imprime 5, luego $b se convierte en 6)
echo $b;   // 6

$c = 5;
echo --$c; // 4 ($c se convierte en 4, luego se imprime 4)

$d = 5;
echo $d--; // 5 (se imprime 5, luego $d se convierte en 4)
?>
```

### Operador de Concatenación de Cadenas

*   `.` : Une dos cadenas.

```php
<?php
$cad1 = "Hola";
$cad2 = "PHP";
$mensaje = $cad1 . " " . $cad2 . "!"; // "Hola PHP!"
echo $mensaje;
?>
```

### Operadores de Array

*   `+`   : Unión. Añade elementos del array de la derecha al array de la izquierda. Si las claves son las mismas, se conservan los elementos del array de la izquierda.
*   `==`  : Igualdad. Devuelve `true` si ambos arrays tienen los mismos pares clave/valor (el orden no importa para los asociativos, pero sí para los indexados a menos que las claves se especifiquen explícitamente).
*   `===` : Identidad. Devuelve `true` si ambos arrays tienen los mismos pares clave/valor en el mismo orden y de los mismos tipos.
*   `!=`, `<>` : Desigualdad.
*   `!==` : No identidad.

```php
<?php
$arr1 = ["a" => "manzana", "b" => "banana"];
$arr2 = ["b" => "arándano", "c" => "cereza"]; // la clave "b" existe en $arr1
$union = $arr1 + $arr2;
print_r($union); // Salida: Array ( [a] => manzana [b] => banana [c] => cereza )

$x = [1, 2, 3];
$y = [3, 2, 1];
$z = ["1", 2, "3"];
var_dump($x == $y); // bool(false) - el orden y los valores importan para los indexados
var_dump($x == $z); // bool(true) - los valores se comparan después de la conversión de tipos

$a = ["nombre" => "Alicia", "edad" => 30];
$b = ["edad" => 30, "nombre" => "Alicia"];
var_dump($a == $b); // bool(true) - el orden no importa para los asociativos con los mismos pares clave/valor
var_dump($a === $b); // bool(false) - el orden todavía importa para === si los pares clave/valor no están en la misma secuencia internamente. Para ===, el orden de las claves también importa.
?>
```

### Operador de Control de Errores

*   `@` : Cuando se antepone a una expresión, cualquier mensaje de error que pueda ser generado por esa expresión será ignorado.

```php
<?php
// Ejemplo: Intentando acceder a una variable no definida
$valor = @$variableIndefinida; // Suprime el aviso "Undefined variable"
echo "El valor es: " . $valor . "<br>"; // El valor es: (vacío)

// Ejemplo: Abriendo un archivo que podría no existir
$archivo = @fopen("archivo_inexistente.txt", "r");
if ($archivo) {
    echo "Archivo abierto.";
    fclose($archivo);
} else {
    echo "Falló al abrir el archivo (error suprimido).<br>";
}
?>
```
**Precaución:** Aunque el operador `@` puede ser útil para suprimir errores en situaciones específicas (por ejemplo, cuando sabes que podría ocurrir un error y tienes un plan para manejarlo), debe usarse con moderación. Su uso excesivo puede ocultar problemas reales en tu código y dificultar mucho la depuración. Generalmente es mejor manejar los errores explícitamente usando bloques `try-catch` (para excepciones) o verificando los valores de retorno de las funciones.

### Operador Ternario

Una forma abreviada para una declaración `if-else`.
Sintaxis: `condicion ? expresion_si_verdadero : expresion_si_falso`

```php
<?php
$edad = 20;
$estado = ($edad >= 18) ? "adulto" : "menor";
echo "Estado: " . $estado . "<br>"; // Estado: adulto

// Equivalente if-else:
// if ($edad >= 18) {
//     $estado = "adulto";
// } else {
//     $estado = "menor";
// }
?>
```

### Operador de Fusión de Null (Null Coalescing Operator)

Disponible desde PHP 7.0. Devuelve su primer operando si existe y no es `NULL`; de lo contrario, devuelve su segundo operando.
Sintaxis: `operando1 ?? operando2`

```php
<?php
// Ejemplo 1: La variable podría no estar definida
// $nombreUsuario = $_GET['usuario']; // Esto produciría un aviso si 'usuario' no está en los parámetros GET
$nombreUsuario = $_GET['usuario'] ?? 'invitado'; // Establece $nombreUsuario a 'invitado' si $_GET['usuario'] no está definido o es NULL
echo "Nombre de usuario: " . $nombreUsuario . "<br>";

// Ejemplo 2: Encadenamiento
$nombrePreferido = $perfilUsuario['apodo'] ?? $perfilUsuario['primerNombre'] ?? 'Anónimo';
echo "Nombre preferido: " . $nombrePreferido . "<br>";

// Esto es una abreviatura para:
// isset($operando1) ? $operando1 : $operando2
// o más precisamente para null:
// $operando1 !== null ? $operando1 : $operando2
?>
```

## 5. Estructuras de Control

Las estructuras de control te permiten dictar el flujo de ejecución en tus scripts PHP. Habilitan la toma de decisiones (condicionales) y la repetición (bucles).

### Declaraciones Condicionales

Ejecutan diferentes bloques de código basados en condiciones especificadas.

*   **`if`, `elseif`, `else`:**
    ```php
    <?php
    $puntuacion = 85;

    if ($puntuacion >= 90) {
        echo "Calificación: A<br>";
    } elseif ($puntuacion >= 80) {
        echo "Calificación: B<br>"; // Este bloque se ejecutará
    } elseif ($puntuacion >= 70) {
        echo "Calificación: C<br>";
    } else {
        echo "Calificación: D o F<br>";
    }

    $temperatura = 15;
    if ($temperatura > 25) {
        echo "Hace calor.<br>";
    } else {
        echo "Hace fresco.<br>";
    }
    ?>
    ```

*   **Sintaxis alternativa para estructuras de control:**
    PHP ofrece una sintaxis alternativa para algunas estructuras de control (como `if`, `while`, `for`, `foreach`, `switch`), que puede ser útil al incrustar PHP dentro de HTML. Esto implica reemplazar la llave de apertura `{` con dos puntos `:` y la llave de cierre `}` con `endif;`, `endwhile;`, `endfor;`, `endforeach;`, o `endswitch;` respectivamente.

    ```html
    <?php $estaLogueado = true; ?>

    <?php if ($estaLogueado): ?>
        <p>¡Bienvenido, usuario!</p>
        <a href="logout.php">Cerrar sesión</a>
    <?php else: ?>
        <p>Por favor, inicia sesión.</p>
        <a href="login.php">Iniciar sesión</a>
    <?php endif; ?>
    ```

*   **`switch`:**
    Se usa para seleccionar uno de muchos bloques de código a ejecutar. Compara una expresión con múltiples valores diferentes (casos).
    ```php
    <?php
    $diaDeLaSemana = "Miércoles";

    switch ($diaDeLaSemana) {
        case "Lunes":
            echo "Inicio de la semana laboral.<br>";
            break; // ¡Importante! Sale de la declaración switch
        case "Martes":
        case "Miércoles":
        case "Jueves":
            echo "Mitad de semana.<br>"; // Miércoles coincidirá aquí
            break;
        case "Viernes":
            echo "¡Fin de la semana laboral!<br>";
            break;
        case "Sábado":
        case "Domingo":
            echo "¡Fin de semana!<br>";
            break;
        default: // Opcional: se ejecuta si ningún caso coincide
            echo "Día inválido.<br>";
    }
    ?>
    ```
    Sin `break`, la ejecución "cae" al siguiente `case`. Esto puede ser intencional, pero a menudo es una fuente de errores si se olvida.

### Estructuras de Bucle (Looping Constructs)

Ejecutan un bloque de código repetidamente.

*   **Bucle `for`:**
    Se usa cuando sabes de antemano cuántas veces debe ejecutarse el script.
    Sintaxis: `for (inicializador; condicion; incrementador)`
    ```php
    <?php
    for ($i = 0; $i < 5; $i++) { // Inicializa $i a 0; continúa mientras $i < 5; incrementa $i en 1 después de cada iteración
        echo "El número es: " . $i . "<br>";
    }
    // Salida:
    // El número es: 0
    // El número es: 1
    // El número es: 2
    // El número es: 3
    // El número es: 4
    ?>
    ```

*   **Bucle `while`:**
    Recorre un bloque de código mientras la condición especificada sea verdadera. La condición se verifica *antes* de cada iteración.
    ```php
    <?php
    $contador = 1;
    while ($contador <= 3) {
        echo "El contador es: " . $contador . "<br>";
        $contador++;
    }
    // Salida:
    // El contador es: 1
    // El contador es: 2
    // El contador es: 3
    ?>
    ```

*   **Bucle `do-while`:**
    Similar a un bucle `while`, pero la condición se verifica *después* de cada iteración. Esto significa que el bloque de código siempre se ejecutará al menos una vez.
    ```php
    <?php
    $x = 6;
    do {
        echo "El número es: " . $x . "<br>"; // Esto se ejecutará una vez
        $x++;
    } while ($x <= 5); // La condición es falsa, por lo que el bucle se detiene
    // Salida: El número es: 6
    ?>
    ```

*   **Bucle `foreach`:**
    Proporciona una forma fácil de iterar sobre arrays (y objetos).
    ```php
    <?php
    // Iterando sobre valores
    $colores = ["Rojo", "Verde", "Azul"];
    foreach ($colores as $color) {
        echo $color . "<br>";
    }
    // Salida: Rojo, Verde, Azul (cada uno en una nueva línea)

    // Iterando sobre claves y valores
    $persona = ["nombre" => "Alicia", "edad" => 30, "ciudad" => "Londres"];
    foreach ($persona as $clave => $valor) {
        echo ucfirst($clave) . ": " . $valor . "<br>";
    }
    // Salida:
    // Nombre: Alicia
    // Edad: 30
    // Ciudad: Londres
    ?>
    ```

### Otras Declaraciones de Flujo de Control

*   **`break`:**
    Termina inmediatamente la ejecución de la estructura actual `for`, `foreach`, `while`, `do-while` o `switch`.
    ```php
    <?php
    for ($i = 0; $i < 10; $i++) {
        if ($i == 5) {
            break; // Sale del bucle cuando $i es 5
        }
        echo "Número: " . $i . "<br>";
    }
    // Salida: Números del 0 al 4
    ?>
    ```
    `break` también puede aceptar un argumento numérico opcional para especificar de cuántas estructuras anidadas debe salir. `break 2;` saldría de dos bucles anidados.

*   **`continue`:**
    Omite el resto de la iteración actual de un bucle (`for`, `while`, `do-while`, `foreach`) y procede a la siguiente iteración (o evaluación de condición).
    ```php
    <?php
    for ($i = 0; $i < 5; $i++) {
        if ($i == 2) {
            continue; // Omite esta iteración cuando $i es 2
        }
        echo "Número: " . $i . "<br>";
    }
    // Salida:
    // Número: 0
    // Número: 1
    // Número: 3
    // Número: 4
    ?>
    ```
    Al igual que `break`, `continue` también puede tomar un argumento numérico opcional.

*   **`include` y `require`:**
    Estas construcciones se utilizan para incluir y evaluar el contenido de otro archivo PHP en el script actual. Esto es fundamental para organizar el código en módulos reutilizables.
    *   `include 'nombre_archivo.php';`
    *   `require 'nombre_archivo.php';`

    La principal diferencia entre ellos es cómo manejan los errores si no se encuentra el archivo:
    *   `include`: Si no se encuentra el archivo, genera una **advertencia** (E_WARNING), y el script continúa la ejecución.
    *   `require`: Si no se encuentra el archivo, genera un **error fatal** (E_COMPILE_ERROR), y el script termina.

    Usa `require` para archivos que son esenciales para que tu aplicación se ejecute (por ejemplo, configuraciones de base de datos, funciones principales). Usa `include` para archivos que son opcionales o cuya ausencia se puede manejar con gracia (por ejemplo, partes de plantillas).

    ```php
    <?php
    // Asume que 'header.php' contiene: echo "<h1>Mi Sitio Web</h1>";
    @include 'header.php'; // Usamos @ para suprimir el warning si header.php no existe para este ejemplo

    echo "<p>Este es el contenido principal.</p>";

    // Asume que 'functions.php' contiene funciones esenciales
    // require 'functions.php';
    // Si functions.php falta, 'require' detendría el script.
    ?>
    ```

    *   **`include_once` y `require_once`:**
        Estas son variantes que aseguran que un archivo se incluya solo una vez, incluso if la declaración se encuentra varias veces. Esto previene errores por re-declarar funciones o clases.
        *   `include_once 'nombre_archivo.php';`
        *   `require_once 'nombre_archivo.php';`
        Generalmente es una buena práctica usar las versiones `_once` para incluir archivos que definen funciones, clases o establecen configuraciones para evitar problemas.

*   **`goto`:**
    Te permite saltar a otra sección en el programa, identificada por una etiqueta.
    ```php
    <?php
    goto punto_final;
    echo "Esto se omitirá.<br>";

    punto_final:
    echo "Se alcanzó el punto final.<br>";
    ?>
    ```
    **Consejo:** La declaración `goto` está disponible en PHP pero generalmente se **desaconseja** en las prácticas de programación modernas. Puede dificultar la lectura, comprensión y depuración del código, lo que lleva a un "código espagueti". Casi siempre hay mejores formas de estructurar tu código usando bucles, condicionales, funciones o excepciones. Úsalo con mucha moderación, si es que lo usas.

¡Esto cubre los conceptos básicos de PHP! Con estos fundamentos, puedes comenzar a escribir scripts simples y comprender código más complejo. En las próximas secciones, construiremos sobre este conocimiento.
[end of php_tutorial/02_php_basics_es.md]
