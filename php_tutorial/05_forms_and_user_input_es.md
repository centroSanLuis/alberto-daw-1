# Trabajando con Formularios y Entrada de Usuario

Interactuar con los usuarios es un aspecto fundamental de las aplicaciones web. Los formularios HTML son la forma principal de recopilar la entrada del usuario, y PHP proporciona herramientas potentes para procesar, validar y asegurar estos datos. Esta sección cubre cómo manejar envíos de formularios, gestionar datos de usuario con cookies y sesiones, y asegurar que tu aplicación sea segura.

## 1. Formularios HTML - Un Rápido Repaso

Antes de sumergirnos en PHP, repasemos rápidamente los conceptos básicos de los formularios HTML.

Un formulario HTML es una sección de un documento que contiene controles como campos de texto, campos de contraseña, casillas de verificación, botones de radio, menús de selección y botones. Los usuarios completan estos controles y envían el formulario a un servidor web para su procesamiento.

**Estructura Básica:**

```html
<form action="procesar_formulario.php" method="POST">
    <!-- Campo de entrada para texto -->
    <label for="nombre_usuario">Nombre de Usuario:</label>
    <input type="text" id="nombre_usuario" name="nombre_usuario"><br><br>

    <!-- Campo de entrada para contraseña -->
    <label for="contrasena">Contraseña:</label>
    <input type="password" id="contrasena" name="contrasena"><br><br>

    <!-- Textarea para mensajes más largos -->
    <label for="mensaje">Mensaje:</label>
    <textarea id="mensaje" name="mensaje" rows="4" cols="50"></textarea><br><br>

    <!-- Menú desplegable de selección -->
    <label for="color">Color Favorito:</label>
    <select id="color" name="color">
        <option value="rojo">Rojo</option>
        <option value="verde">Verde</option>
        <option value="azul">Azul</option>
    </select><br><br>

    <!-- Casilla de verificación -->
    <input type="checkbox" id="suscribir" name="suscribir" value="si">
    <label for="suscribir">Suscribirse al boletín</label><br><br>

    <!-- Botones de radio -->
    <p>Género:</p>
    <input type="radio" id="masculino" name="genero" value="masculino">
    <label for="masculino">Masculino</label><br>
    <input type="radio" id="femenino" name="genero" value="femenino">
    <label for="femenino">Femenino</label><br><br>

    <!-- Botón de envío -->
    <button type="submit">Enviar Formulario</button>
</form>
```

**Atributos Clave:**

*   **Etiqueta `<form>`:** El contenedor para todos los elementos del formulario.
    *   **Atributo `action`:** Especifica la URL del script PHP (u otro recurso del lado del servidor) que procesará los datos del formulario. En el ejemplo, es `procesar_formulario.php`. Si está vacío, se envía a la página actual.
    *   **Atributo `method`:** Define el método HTTP que se utilizará al enviar los datos del formulario.
        *   **`GET`**:
            *   Añade los datos del formulario a la URL especificada en el atributo `action` (por ejemplo, `procesar_formulario.php?nombre_usuario=juan&color=rojo`).
            *   Los datos son visibles en la URL, el historial del navegador y los registros del servidor.
            *   Adecuado para datos no sensibles, solicitudes idempotentes (solicitudes que se pueden repetir sin cambiar el estado del servidor), como consultas de búsqueda o filtrado.
            *   Longitud de datos limitada (las URLs tienen una longitud máxima).
        *   **`POST`**:
            *   Envía los datos del formulario en el cuerpo de la solicitud HTTP.
            *   Los datos no son visibles en la URL.
            *   Adecuado para información sensible (como contraseñas), grandes cantidades de datos o acciones que modifican el estado del servidor (por ejemplo, crear un nuevo usuario, enviar una entrada de blog).
            *   Sin límite práctico en la longitud de los datos.

*   **Elementos de Entrada (`<input>`, `<textarea>`, `<select>`, `<button>`):**
    *   **Atributo `type` (para `<input>`):** Especifica el tipo de control de entrada. Los tipos comunes incluyen:
        *   `text`: Entrada de texto de una sola línea.
        *   `password`: Entrada de texto de una sola línea que enmascara los caracteres.
        *   `email`: Para direcciones de correo electrónico (proporciona validación básica del lado del cliente en navegadores modernos).
        *   `number`: Para entrada numérica.
        *   `checkbox`: Permite seleccionar cero o más opciones.
        *   `radio`: Permite seleccionar una opción de un grupo (todos los botones de radio en un grupo deben tener el mismo `name`).
        *   `file`: Para seleccionar archivos para cargar.
        *   `submit`: Un botón que envía el formulario.
        *   `hidden`: Un campo de entrada no visible para el usuario, a menudo utilizado para enviar datos adicionales.
    *   **Atributo `name`:** **Esto es crucial para PHP.** El atributo `name` proporciona la clave que PHP utilizará para acceder al valor enviado en las matrices superglobales `$_GET` o `$_POST`. Cada campo de formulario que desees procesar en PHP debe tener un atributo `name` único (excepto los botones de radio en el mismo grupo).
    *   **Atributo `value`:** Para la mayoría de los tipos de entrada, especifica el valor inicial o el valor enviado cuando se envía el formulario. Para casillas de verificación y botones de radio, es el valor enviado si esa opción está seleccionada. Para `<textarea>`, el contenido entre las etiquetas es su valor. Para `<option>` dentro de `<select>`, es el valor enviado.
    *   **Atributo `id`:** Se utiliza para asociar una `<label>` con un campo de entrada para una mejor accesibilidad y experiencia de usuario.
    *   **`<label for="id_del_input">`:** Proporciona una etiqueta descriptiva para un control de formulario. Hacer clic en la etiqueta enfoca o activa el control asociado.

## 2. Recuperando Datos en PHP

Cuando se envía un formulario, PHP pone los datos a disposición a través de matrices asociativas especiales llamadas **superglobales**. Estas están disponibles en todos los ámbitos a lo largo de un script.

### Superglobal `$_GET`

*   Se utiliza para recopilar valores de un formulario enviado con `method="GET"`.
*   También se utiliza para recopilar valores de la cadena de consulta de la URL (por ejemplo, `mipagina.php?id=123&categoria=libros`).
*   Es un array asociativo donde las claves son los atributos `name` de los campos del formulario (o parámetros de la cadena de consulta) y los valores son los datos enviados.

**Ejemplo:**
Si un formulario con `method="GET"` tiene un campo de entrada `<input type="text" name="termino_busqueda">` y el usuario escribe "tutorial PHP" y envía, la URL podría ser `procesar.php?termino_busqueda=tutorial+PHP`.

En `procesar.php`:

```php
<?php
if (isset($_GET['termino_busqueda'])) {
    $terminoBusqueda = $_GET['termino_busqueda'];
    echo "Buscaste: " . htmlspecialchars($terminoBusqueda) . "<br>";
} else {
    echo "No se proporcionó ningún término de búsqueda.<br>";
}

// Usando el operador de fusión de null (PHP 7+) para un valor predeterminado
$categoria = $_GET['categoria'] ?? 'todos';
echo "Categoría: " . htmlspecialchars($categoria) . "<br>";
?>
```
*   **`isset($_GET['termino_busqueda'])`**: Comprueba si la clave `termino_busqueda` existe en el array `$_GET`. Esto es importante para evitar avisos de "índice indefinido" si el campo del formulario no se envió o el parámetro no está en la URL.
*   **`htmlspecialchars($terminoBusqueda)`**: **¡Crucial para la seguridad!** Esta función convierte caracteres HTML especiales (como `<`, `>`, `&`, `"`) en sus equivalentes de entidad HTML (por ejemplo, `&lt;`, `&gt;`). Esto previene ataques de Cross-Site Scripting (XSS) si estás mostrando la entrada del usuario de nuevo en la página. (Más sobre esto en Sanitización de Datos).

### Superglobal `$_POST`

*   Se utiliza para recopilar valores de un formulario enviado con `method="POST"`.
*   Los datos se envían en el cuerpo de la solicitud HTTP y no son visibles en la URL.
*   Es un array asociativo, similar a `$_GET`.

**Ejemplo:**
Si un formulario con `method="POST"` tiene `<input type="text" name="nombre_usuario">` y `<input type="password" name="contrasena">`.

En `manejador_login.php`:

```php
<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") { // Comprueba si el formulario fue enviado vía POST
    // Comprueba si el nombre de usuario está establecido y no está vacío
    if (isset($_POST['nombre_usuario']) && !empty(trim($_POST['nombre_usuario']))) {
        $nombreUsuario = $_POST['nombre_usuario']; // No es necesario htmlspecialchars si no se muestra inmediatamente
        echo "Nombre de Usuario: " . htmlspecialchars($nombreUsuario) . "<br>";
    } else {
        echo "El nombre de usuario es requerido.<br>";
    }

    // Comprueba la contraseña
    if (isset($_POST['contrasena']) && !empty($_POST['contrasena'])) {
        $contrasena = $_POST['contrasena'];
        // Procesa la contraseña (por ejemplo, hashearla, verificarla)
        // ¡NUNCA muestres o almacenes contraseñas en texto plano!
        echo "Contraseña recibida (pero no mostrada por seguridad).<br>";
    } else {
        echo "La contraseña es requerida.<br>";
    }
} else {
    echo "Por favor, envía el formulario.<br>";
}
?>
```
*   **`$_SERVER["REQUEST_METHOD"] == "POST"`**: Una forma común de comprobar si se está accediendo al script mediante una solicitud POST (es decir, se envió el formulario).
*   **`!empty(trim($_POST['nombre_usuario']))`**: Comprueba si el nombre de usuario no está vacío después de eliminar los espacios en blanco iniciales/finales.

### Superglobal `$_REQUEST`

*   `$_REQUEST` es otro array superglobal que, por defecto, contiene el contenido de `$_GET`, `$_POST` y `$_COOKIE`.
*   Aunque puede ser conveniente, generalmente se **recomienda usar `$_GET` o `$_POST` directamente.**
    *   **Claridad:** Usar `$_GET` o `$_POST` hace que tu código sea más explícito sobre de dónde esperas que provengan los datos.
    *   **Seguridad:** Confiar en `$_REQUEST` puede introducir vulnerabilidades de seguridad si no tienes cuidado con el origen de los datos, especialmente debido a la inclusión de datos de `$_COOKIE`. Por ejemplo, si se espera `$_GET['accion']` pero existe una cookie llamada `accion`, `$_REQUEST['accion']` podría tomar el valor de la cookie si el orden de las variables en `request_order` (configuración de php.ini) prioriza las cookies.

**Script de Ejemplo Procesando Tanto GET (para un ID) como POST (para datos de formulario):**

`editar_item.php`:

```php
<?php
// Inicializar variables
$itemId = null;
$nombreItem = '';
$precioItem = '';
$mensaje = '';

// --- Manejar solicitud GET para ID de ítem (ej., de un enlace como editar_item.php?id=1) ---
if (isset($_GET['id'])) {
    $itemId = (int)$_GET['id']; // Convertir a entero para sanitización básica
    // En una aplicación real, obtendrías detalles del ítem de una base de datos usando $itemId
    $mensaje = "Editando ítem ID: " . htmlspecialchars($itemId);
    // Para demostración, rellenemos algunos datos ficticios si se encuentra el ID
    if ($itemId == 1) {
        $nombreItem = "Ítem de Muestra";
        $precioItem = "19.99";
    }
}

// --- Manejar solicitud POST del envío del formulario ---
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $itemIdEnviado = $_POST['item_id'] ?? null; // Mantener el ID enviado con el formulario
    $nombreItem = $_POST['item_nombre'] ?? '';
    $precioItem = $_POST['item_precio'] ?? '';

    // Validación básica (más sobre esto a continuación)
    if (empty(trim($nombreItem))) {
        $mensaje = "Error: El nombre del ítem no puede estar vacío.";
    } elseif (!is_numeric($precioItem) || $precioItem < 0) {
        $mensaje = "Error: El precio del ítem debe ser un número no negativo.";
    } else {
        // Procesar los datos (ej., guardar en base de datos)
        $mensaje = "¡Ítem '" . htmlspecialchars($nombreItem) . "' (ID: " . htmlspecialchars($itemIdEnviado) . ") actualizado exitosamente con precio $" . htmlspecialchars($precioItem) . "!";
        // Usualmente, redirigirías después de un POST exitoso para prevenir reenvío
        // header("Location: pagina_exito.php");
        // exit;
    }
}
?>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Ítem</title>
</head>
<body>
    <h1>Editar Ítem</h1>
    <?php if (!empty($mensaje)): ?>
        <p style="color: <?php echo strpos($mensaje, 'Error') === 0 ? 'red' : 'green'; ?>;">
            <?php echo $mensaje; ?>
        </p>
    <?php endif; ?>

    <form action="editar_item.php<?php echo $itemId ? '?id=' . $itemId : ''; ?>" method="POST">
        <!-- Campo oculto para enviar el ID del ítem si estamos editando uno existente -->
        <?php if ($itemId): ?>
            <input type="hidden" name="item_id" value="<?php echo htmlspecialchars($itemId); ?>">
        <?php endif; ?>

        <label for="item_nombre">Nombre del Ítem:</label>
        <input type="text" id="item_nombre" name="item_nombre" value="<?php echo htmlspecialchars($nombreItem); ?>"><br><br>

        <label for="item_precio">Precio del Ítem:</label>
        <input type="text" id="item_precio" name="item_precio" value="<?php echo htmlspecialchars($precioItem); ?>"><br><br>

        <button type="submit">Guardar Cambios</button>
    </form>

    <p><a href="editar_item.php?id=1">Editar Ítem 1</a></p>
    <p><a href="editar_item.php">Comenzar Nuevo Ítem (Sin ID)</a></p>
</body>
</html>
```
Este ejemplo muestra cómo un solo script PHP puede manejar tanto la visualización de un formulario (opcionalmente rellenado mediante GET) como el procesamiento de su envío (mediante POST).

## 3. Validación de Datos

**¡Nunca confíes en la entrada del usuario!** La validación de datos es el proceso de asegurar que la entrada del usuario sea apropiada, precisa y segura antes de procesarla. Es un paso crítico en la construcción de aplicaciones web robustas y seguras.

**Por Qué la Validación del Lado del Servidor es Esencial:**
La validación del lado del cliente (usando atributos HTML5 como `required` o JavaScript) puede mejorar la experiencia del usuario al proporcionar retroalimentación inmediata. Sin embargo, **no se debe confiar en ella para la seguridad o la integridad de los datos.**
*   Los usuarios pueden deshabilitar JavaScript.
*   Los usuarios malintencionados pueden eludir la validación del lado del cliente y enviar datos arbitrarios directamente a tu servidor (por ejemplo, usando herramientas como cURL o las herramientas de desarrollador del navegador).
**Siempre valida los datos en el lado del servidor (en PHP).**

**Tareas Comunes de Validación:**

*   **Comprobación de Campos Vacíos/Requeridos:**
    ```php
    <?php
    $nombreUsuario = $_POST['nombre_usuario'] ?? '';
    if (empty(trim($nombreUsuario))) {
        $errores['nombre_usuario'] = "El nombre de usuario es requerido.";
    }
    ?>
    ```
    *   `trim()`: Elimina los espacios en blanco del principio y el final de una cadena. Útil para evitar que los usuarios envíen solo espacios.
    *   `empty()`: Comprueba si una variable está vacía. Considera `""`, `0`, `"0"`, `NULL`, `FALSE` y un array vacío como vacíos.

*   **Validación de Tipos de Datos:**
    *   `is_numeric()`: Comprueba si una variable es un número o una cadena numérica.
    *   `is_int()`: Comprueba si una variable es un entero.
    *   `is_string()`: Comprueba si una variable es una cadena.
    *   `is_bool()`: Comprueba si una variable es un booleano.
    ```php
    <?php
    $edad = $_POST['edad'] ?? '';
    if (!is_numeric($edad) || $edad < 0) {
        $errores['edad'] = "La edad debe ser un número no negativo.";
    } else {
        $edad = (int)$edad; // Opcionalmente, convertir a entero después de la validación
    }
    ?>
    ```

*   **Validación de Longitud de Cadena (Mín/Máx):**
    ```php
    <?php
    $contrasena = $_POST['contrasena'] ?? '';
    if (strlen($contrasena) < 8) {
        $errores['contrasena'] = "La contraseña debe tener al menos 8 caracteres.";
    } elseif (strlen($contrasena) > 255) {
        $errores['contrasena'] = "La contraseña es demasiado larga (máx. 255 caracteres).";
    }
    ?>
    ```

*   **Validación de Formatos:**
    *   **Email:** Usa `filter_var()` con `FILTER_VALIDATE_EMAIL`.
        ```php
        <?php
        $email = $_POST['email'] ?? '';
        if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
            $errores['email'] = "Formato de email inválido.";
        }
        ?>
        ```
    *   **URL:** Usa `filter_var()` con `FILTER_VALIDATE_URL`.
        ```php
        <?php
        $sitioWeb = $_POST['sitio_web'] ?? '';
        if (!empty($sitioWeb) && !filter_var($sitioWeb, FILTER_VALIDATE_URL)) {
            $errores['sitio_web'] = "Formato de URL inválido.";
        }
        ?>
        ```
    *   **Expresiones Regulares (`preg_match()`):** Para patrones complejos (por ejemplo, formatos específicos de nombre de usuario, códigos postales).
        ```php
        <?php
        $codigoPostal = $_POST['codigo_postal'] ?? '';
        // Ejemplo: Código postal de EE. UU. (5 dígitos, opcionalmente +4)
        if (!preg_match('/^\d{5}(-\d{4})?$/', $codigoPostal)) {
            $errores['codigo_postal'] = "Formato de código postal inválido.";
        }
        ?>
        ```

*   **Validación Contra una Lista de Valores Permitidos:**
    ```php
    <?php
    $rolUsuario = $_POST['rol'] ?? '';
    $rolesPermitidos = ['editor', 'autor', 'suscriptor'];
    if (!in_array($rolUsuario, $rolesPermitidos)) {
        $errores['rol'] = "Rol seleccionado inválido.";
    }
    ?>
    ```

**Construyendo un Sistema de Validación Simple:**

Es común recopilar todos los errores de validación en un array.

```php
<?php
$errores = []; // Array para almacenar errores de validación

// --- Asume que los datos del formulario están en $_POST ---
$nombre = $_POST['nombre'] ?? '';
$email = $_POST['email'] ?? '';
$edad = $_POST['edad'] ?? '';

// Validar Nombre
if (empty(trim($nombre))) {
    $errores['nombre'] = "El nombre es requerido.";
} elseif (strlen($nombre) > 100) {
    $errores['nombre'] = "El nombre no puede exceder los 100 caracteres.";
}

// Validar Email
if (empty($email)) {
    $errores['email'] = "El email es requerido.";
} elseif (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
    $errores['email'] = "Formato de email inválido.";
}

// Validar Edad
if (empty($edad)) {
    $errores['edad'] = "La edad es requerida.";
} elseif (!is_numeric($edad) || $edad < 1 || $edad > 120) {
    $errores['edad'] = "La edad debe ser un número entre 1 y 120.";
}

// Comprueba si hay errores
if (empty($errores)) {
    // Procesar los datos (ej., guardar en base de datos)
    echo "¡Formulario enviado exitosamente!<br>";
    // ... tu lógica de procesamiento ...
} else {
    // Mostrar errores (o pasarlos de nuevo al formulario)
    echo "<h3>Por favor, corrige los siguientes errores:</h3>";
    echo "<ul>";
    foreach ($errores as $campo => $error) {
        echo "<li>" . htmlspecialchars(ucfirst($campo)) . ": " . htmlspecialchars($error) . "</li>";
    }
    echo "</ul>";
    // Típicamente, volverías a mostrar el formulario con mensajes de error junto a los campos
}
?>
```

**Mostrando Mensajes de Error al Usuario:**
Los mensajes de error deben ser claros y mostrarse cerca de los campos de formulario relevantes para ayudar al usuario a corregirlos. (Ver "Formularios Persistentes" para cómo combinar esto con el rellenado del formulario).

## 4. Sanitización de Datos

La sanitización de datos es el proceso de limpiar o filtrar la entrada del usuario para eliminar o escapar caracteres o código potencialmente malicioso. Esto es crucial para prevenir vulnerabilidades de seguridad.

**Importancia:**

*   **Prevención de Cross-Site Scripting (XSS):** XSS ocurre cuando scripts maliciosos se inyectan en páginas web vistas por otros usuarios. Si muestras la entrada del usuario directamente en HTML sin sanitización, un atacante podría ingresar `<script>alert('XSS');</script>`, que luego se ejecutaría en los navegadores de otros usuarios.
*   **Prevención de Inyección SQL (SQLi):** SQLi ocurre cuando los atacantes manipulan la entrada para alterar las consultas SQL ejecutadas contra tu base de datos. (Esto se cubrirá con más detalle en la sección de bases de datos, pero el manejo adecuado de la entrada comienza aquí).
*   **Prevención de Otros Ataques de Inyección:** Como la inyección de comandos si la entrada del usuario se usa en comandos del sistema (altamente desaconsejado).

**Sanitización para Salida HTML (Prevención de XSS):**

*   **`htmlspecialchars($string, $flags = ENT_QUOTES | ENT_SUBSTITUTE | ENT_HTML401, $encoding = 'UTF-8')`:**
    *   Convierte caracteres HTML especiales en sus equivalentes de entidad HTML.
    *   `&` (ampersand) se convierte en `&amp;`
    *   `"` (comillas dobles) se convierte en `&quot;` (si `ENT_QUOTES` está establecido)
    *   `'` (comillas simples) se convierte en `&apos;` (o `&#039;`) (si `ENT_QUOTES` está establecido)
    *   `<` (menor que) se convierte en `&lt;`
    *   `>` (mayor que) se convierte en `&gt;`
    *   Esta es la **función más común y recomendada** para prevenir XSS al mostrar la entrada del usuario en un contexto HTML.
    *   **Flags (Banderas):**
        *   `ENT_QUOTES`: Convierte tanto comillas dobles como simples.
        *   `ENT_SUBSTITUTE`: Reemplaza secuencias de unidades de código inválidas con un Carácter de Reemplazo Unicode (U+FFFD) en lugar de devolver una cadena vacía (PHP 5.4+).
        *   `ENT_HTML401`, `ENT_HTML5`, `ENT_XML1`, `ENT_XHTML`: Especifican el tipo de documento.

    ```php
    <?php
    $entradaUsuario = '<script>alert("¡Ataque XSS!");</script>';
    $salidaSegura = htmlspecialchars($entradaUsuario, ENT_QUOTES, 'UTF-8');
    echo $salidaSegura; // Salida: &lt;script&gt;alert(&quot;¡Ataque XSS!&quot;);&lt;/script&gt;
                      // Esto se mostrará como texto, no se ejecutará como un script.
    ?>
    ```

*   **`htmlentities($string, $flags = ENT_QUOTES | ENT_SUBSTITUTE | ENT_HTML401, $encoding = 'UTF-8')`:**
    *   Similar a `htmlspecialchars()`, pero convierte **todos** los caracteres que tienen equivalentes de entidad HTML, no solo los especiales (`<`, `>`, `&`, etc.). Por ejemplo, convertiría `©` a `&copy;`.
    *   Puede ser más agresivo de lo necesario para la prevención general de XSS. `htmlspecialchars()` suele ser suficiente.

*   **`strip_tags($string, $allowed_tags = null)`:**
    *   Elimina etiquetas HTML y PHP de una cadena.
    *   Opcionalmente, puedes especificar una cadena de etiquetas permitidas (por ejemplo, `strip_tags($entrada, '<p><a><b>')`).
    *   **Usar con precaución.** Puede romper el marcado legítimo si se permite a los usuarios enviar algo de HTML. Tampoco es infalible para la prevención de XSS por sí solo, ya que los atacantes pueden usar atributos o HTML mal formado. `htmlspecialchars()` es generalmente más seguro para mostrar datos.
    *   Podría ser útil si deseas obtener contenido de texto plano de la entrada del usuario que podría contener HTML.

**Sanitización para Entrada de Base de Datos:**
*   Cuando se trabaja con bases de datos, la principal defensa contra la Inyección SQL es usar **sentencias preparadas** con marcadores de posición (por ejemplo, con PDO o MySQLi). Esto separa la estructura de la consulta SQL de los datos.
*   Aunque la validación de entrada (comprobación de tipos, longitudes) sigue siendo importante, `htmlspecialchars()` **no** es la función correcta para la sanitización de bases de datos.
*   Existen funciones específicas de escape para bases de datos (como `mysqli_real_escape_string()`), pero generalmente son menos preferidas y más propensas a errores que las sentencias preparadas. (Más en la sección de Bases de Datos).

**Sanitización para Otros Contextos:**
*   **Sistema de Archivos:** Ten mucho cuidado si la entrada del usuario se utiliza en rutas o nombres de archivos. Usa funciones como `basename()` para prevenir ataques de cruce de directorios (por ejemplo, `../../etc/passwd`). Usa una lista blanca de caracteres permitidos.
*   **Comandos de Shell:** **Evita usar la entrada del usuario en comandos de shell si es posible.** Si es absolutamente necesario, usa `escapeshellcmd()` y `escapeshellarg()` con extrema precaución y validación exhaustiva. Esta es un área de alto riesgo.

**Regla General:** Sanitiza los datos apropiadamente para el *contexto* en el que se utilizarán. Los datos seguros para la salida HTML podrían no ser seguros para una consulta SQL, y viceversa.

## 5. Formularios Persistentes (Repoblando Campos de Formulario)

Un "formulario persistente" (sticky form) es un formulario que, tras su envío (especialmente si hay errores de validación), se vuelve a mostrar con los valores que el usuario ingresó previamente. Esto mejora la experiencia del usuario, ya que no tiene que volver a ingresar todo.

**Cómo Implementarlo:**
Logras esto estableciendo el atributo `value` de los campos de entrada (o los atributos `checked` / `selected` para casillas de verificación/radios/selects) con los datos enviados previamente. Recuerda usar `htmlspecialchars()` en cualquier dato que envíes de vuelta a los campos del formulario para prevenir XSS.

**Ejemplo:**

```php
<?php
// Asume código de validación anterior, poblando $errores y datos de $_POST
$nombre = $_POST['nombre'] ?? '';
$email = $_POST['email'] ?? '';
$edad = $_POST['edad'] ?? '';
$colorSeleccionado = $_POST['color'] ?? ''; // Para select
$estaSuscrito = isset($_POST['suscribir']); // Para checkbox
$genero = $_POST['genero'] ?? ''; // Para radio

$errores = []; // Asume que esto se llena con la lógica de validación
// Validación de ejemplo:
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (empty(trim($nombre))) $errores['nombre'] = "El nombre es requerido.";
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) $errores['email'] = "Email inválido.";
    // ... más validación
}
?>
<!DOCTYPE html>
<html>
<head>
    <title>Formulario Persistente</title>
    <style> .error { color: red; font-size: 0.9em; } </style>
</head>
<body>
    <h2>Registro de Usuario</h2>
    <?php if ($_SERVER["REQUEST_METHOD"] == "POST" && !empty($errores)): ?>
        <div style="color: red; border: 1px solid red; padding: 10px; margin-bottom: 15px;">
            <strong>Por favor, corrige los errores:</strong>
            <ul>
                <?php foreach ($errores as $error): ?>
                    <li><?php echo htmlspecialchars($error); ?></li>
                <?php endforeach; ?>
            </ul>
        </div>
    <?php elseif ($_SERVER["REQUEST_METHOD"] == "POST" && empty($errores)): ?>
        <div style="color: green; border: 1px solid green; padding: 10px; margin-bottom: 15px;">
            ¡Formulario enviado exitosamente!
        </div>
    <?php endif; ?>

    <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="POST">
        <div>
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" value="<?php echo htmlspecialchars($nombre); ?>">
            <?php if (isset($errores['nombre'])): ?><span class="error"><?php echo htmlspecialchars($errores['nombre']); ?></span><?php endif; ?>
        </div><br>

        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="<?php echo htmlspecialchars($email); ?>">
            <?php if (isset($errores['email'])): ?><span class="error"><?php echo htmlspecialchars($errores['email']); ?></span><?php endif; ?>
        </div><br>

        <div>
            <label for="edad">Edad:</label>
            <input type="number" id="edad" name="edad" value="<?php echo htmlspecialchars($edad); ?>">
            <?php if (isset($errores['edad'])): ?><span class="error"><?php echo htmlspecialchars($errores['edad']); ?></span><?php endif; ?>
        </div><br>

        <div>
            <label for="color">Color Favorito:</label>
            <select id="color" name="color">
                <option value="">--Selecciona Color--</option>
                <option value="rojo" <?php if ($colorSeleccionado === 'rojo') echo 'selected'; ?>>Rojo</option>
                <option value="verde" <?php if ($colorSeleccionado === 'verde') echo 'selected'; ?>>Verde</option>
                <option value="azul" <?php if ($colorSeleccionado === 'azul') echo 'selected'; ?>>Azul</option>
            </select>
            <?php if (isset($errores['color'])): ?><span class="error"><?php echo htmlspecialchars($errores['color']); ?></span><?php endif; ?>
        </div><br>

        <div>
            <input type="checkbox" id="suscribir" name="suscribir" value="si" <?php if ($estaSuscrito) echo 'checked'; ?>>
            <label for="suscribir">Suscribirse al boletín</label>
        </div><br>

        <div>
            <p>Género:</p>
            <input type="radio" id="masculino" name="genero" value="masculino" <?php if ($genero === 'masculino') echo 'checked'; ?>>
            <label for="masculino">Masculino</label>
            <input type="radio" id="femenino" name="genero" value="femenino" <?php if ($genero === 'femenino') echo 'checked'; ?>>
            <label for="femenino">Femenino</label>
            <?php if (isset($errores['genero'])): ?><span class="error"><?php echo htmlspecialchars($errores['genero']); ?></span><?php endif; ?>
        </div><br>

        <button type="submit">Registrar</button>
    </form>
</body>
</html>
```
*   `htmlspecialchars($_SERVER["PHP_SELF"])`: Se usa como `action` del formulario para enviar al script actual. `htmlspecialchars` previene XSS si la variable `PHP_SELF` es manipulada.
*   Los valores se imprimen en los atributos `value`.
*   El atributo `selected` se añade a las etiquetas `<option>` si ese valor fue elegido previamente.
*   El atributo `checked` se añade a las entradas de checkbox/radio si fueron marcadas previamente.
*   Los mensajes de error se muestran junto a sus respectivos campos (una práctica común).

## 6. Carga de Archivos

PHP puede manejar archivos cargados mediante formularios HTML. Esto se usa comúnmente para imágenes de perfil, compartición de documentos, etc.

**Formulario HTML para Carga de Archivos:**

1.  El atributo `method` del formulario **debe ser `POST`**.
2.  El atributo `enctype` del formulario **debe establecerse a `multipart/form-data`**. Este tipo de codificación permite enviar archivos junto con los datos del formulario.
3.  Usa `<input type="file" name="tu_nombre_de_input_de_archivo">`.

```html
<form action="parser_carga.php" method="POST" enctype="multipart/form-data">
    <label for="foto_perfil">Elige una foto de perfil:</label>
    <input type="file" id="foto_perfil" name="foto_perfil"><br><br>

    <label for="documento">Sube un documento:</label>
    <input type="file" id="documento" name="documento_usuario"><br><br>

    <button type="submit">Subir Archivos</button>
</form>
```

### La Superglobal `$_FILES`

Cuando se envía un formulario con `enctype="multipart/form-data"`, PHP puebla el array superglobal `$_FILES` con información sobre los archivos cargados.

Si tienes una entrada como `<input type="file" name="foto_perfil">`, los datos para este archivo estarán en `$_FILES['foto_perfil']`. Esto a su vez será un array asociativo que contiene:

*   **`$_FILES['foto_perfil']['name']`**: El nombre original del archivo en la máquina del cliente (por ejemplo, `mi_foto.jpg`). **Confía en esto solo como una pista.**
*   **`$_FILES['foto_perfil']['type']`**: El tipo MIME del archivo proporcionado por el navegador (por ejemplo, `image/jpeg`). **No confíes en esto para la validación; puede ser fácilmente falsificado.**
*   **`$_FILES['foto_perfil']['tmp_name']`**: El nombre de archivo temporal donde se almacena el archivo cargado en el servidor (por ejemplo, `/tmp/phpA1b2C3`). Debes mover este archivo a una ubicación permanente si quieres conservarlo.
*   **`$_FILES['foto_perfil']['error']`**: Un código de error asociado con esta carga de archivo.
    *   `UPLOAD_ERR_OK` (Valor: 0): Sin error, archivo cargado exitosamente.
    *   `UPLOAD_ERR_INI_SIZE`: El archivo excede la directiva `upload_max_filesize` en `php.ini`.
    *   `UPLOAD_ERR_FORM_SIZE`: El archivo excede la directiva `MAX_FILE_SIZE` especificada en el formulario HTML (si está presente).
    *   `UPLOAD_ERR_PARTIAL`: El archivo solo se cargó parcialmente.
    *   `UPLOAD_ERR_NO_FILE`: No se cargó ningún archivo.
    *   Existen otros códigos de error para problemas más específicos.
*   **`$_FILES['foto_perfil']['size']`**: El tamaño del archivo cargado en bytes.

### Manejando Archivos Cargados

Ejemplo de `parser_carga.php`:

```php
<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {

    // --- Manejar carga de 'foto_perfil' ---
    if (isset($_FILES['foto_perfil']) && $_FILES['foto_perfil']['error'] == UPLOAD_ERR_OK) {
        $infoArchivo = $_FILES['foto_perfil'];
        $directorioCargas = 'cargas/'; // Crea este directorio y hazlo escribible por el servidor web
        
        // Asegura que el directorio de cargas exista
        if (!is_dir($directorioCargas)) {
            mkdir($directorioCargas, 0755, true);
        }

        // Sanitizar el nombre de archivo original (ejemplo básico)
        $nombreOriginal = $infoArchivo['name'];
        $nombreSeguro = time() . "_" . preg_replace("/[^a-zA-Z0-9._-]/", "", basename($nombreOriginal));
        $destino = $directorioCargas . $nombreSeguro;

        // Validar tipo de archivo (ejemplo: permitir solo JPEG y PNG)
        $tiposPermitidos = ['image/jpeg', 'image/png'];
        // Para una comprobación de tipo más robusta, usa finfo_open() o getimagesize()
        $tipoDetectado = mime_content_type($infoArchivo['tmp_name']); // Requiere la extensión fileinfo

        if (!in_array($tipoDetectado, $tiposPermitidos)) {
            echo "Error: Solo se permiten imágenes JPEG y PNG para la foto de perfil.<br>";
        } elseif ($infoArchivo['size'] > 2 * 1024 * 1024) { // Máx 2MB
            echo "Error: El tamaño de la foto de perfil excede el límite de 2MB.<br>";
        } else {
            // Mover el archivo temporal a la ubicación permanente
            if (move_uploaded_file($infoArchivo['tmp_name'], $destino)) {
                echo "¡Foto de perfil '" . htmlspecialchars($nombreSeguro) . "' cargada exitosamente!<br>";
                echo "Almacenada en: " . htmlspecialchars($destino) . "<br>";
            } else {
                echo "Error al mover la foto de perfil cargada.<br>";
            }
        }
    } elseif (isset($_FILES['foto_perfil']) && $_FILES['foto_perfil']['error'] != UPLOAD_ERR_NO_FILE) {
        echo "Error al cargar la foto de perfil: Código de error " . $_FILES['foto_perfil']['error'] . "<br>";
    }


    // --- Manejar carga de 'documento_usuario' (añadir lógica similar) ---
    if (isset($_FILES['documento_usuario']) && $_FILES['documento_usuario']['error'] == UPLOAD_ERR_OK) {
        // ... (lógica similar de validación y movimiento para el documento)
        $nombreDoc = htmlspecialchars($_FILES['documento_usuario']['name']);
        echo "Documento '{$nombreDoc}' recibido (lógica de procesamiento no implementada completamente aquí).<br>";
    } elseif (isset($_FILES['documento_usuario']) && $_FILES['documento_usuario']['error'] != UPLOAD_ERR_NO_FILE) {
        echo "Error al cargar el documento: Código de error " . $_FILES['documento_usuario']['error'] . "<br>";
    }

} else {
    echo "Por favor, sube archivos usando el formulario.<br>";
}
?>
```

**Pasos Clave y Consideraciones de Seguridad para Cargas de Archivos:**

1.  **Comprobar Errores:** Siempre comprueba `$_FILES['nombre_input']['error']`.
2.  **`move_uploaded_file($tmp_name, $destino)`:** Esta función es crucial. Comprueba si el archivo especificado por `$tmp_name` es un archivo cargado válido (evitando que los atacantes intenten hacer que tu script mueva otros archivos en el servidor). Luego lo mueve a `$destino`.
3.  **Validar Tipo de Archivo:**
    *   **NO confíes en `$_FILES['nombre_input']['type']`**. Este valor es enviado por el navegador y puede ser fácilmente falsificado.
    *   Usa métodos del lado del servidor como `mime_content_type($tmp_name)` (requiere la extensión `fileinfo`) o `getimagesize($tmp_name)` (para imágenes, también devuelve el tipo) para determinar el tipo de archivo real a partir de su contenido.
    *   Mantén una lista blanca de tipos MIME o extensiones permitidos.
4.  **Validar Tamaño de Archivo:** Comprueba `$_FILES['nombre_input']['size']` contra límites razonables para prevenir ataques de denegación de servicio (llenando el almacenamiento del servidor).
5.  **Generar Nombres de Archivo Seguros:**
    *   No uses el nombre de archivo proporcionado por el usuario (`$_FILES['nombre_input']['name']`) directamente para el almacenamiento, ya que podría contener caracteres maliciosos o intentar sobrescribir archivos existentes.
    *   Genera un nombre de archivo nuevo y único (por ejemplo, usando `uniqid()`, `time()`, o un hash del contenido del archivo) o sanitiza a fondo el nombre original (por ejemplo, `basename()` combinado con una lista blanca de caracteres).
6.  **Directorio de Carga Seguro:**
    *   Idealmente, almacena los archivos cargados **fuera del directorio raíz web** si no necesitan ser directamente accesibles mediante una URL. Luego puedes servirlos mediante un script PHP que maneje la autenticación y autorización.
    *   Si los archivos deben estar en la raíz web, deshabilita la ejecución de scripts en el directorio de carga (por ejemplo, usando `.htaccess` para Apache: `php_flag engine off` o `RemoveHandler .php .phtml .php3` y `AddType text/plain .php .phtml .php3`).
    *   Asegura que el directorio de carga no sea navegable.
7.  **Permisos:** El directorio donde se mueven los archivos debe tener permisos de escritura para el usuario del servidor web (por ejemplo, `www-data` en Linux).

## 7. Trabajando con Cookies

Las cookies son pequeñas piezas de datos que un servidor web almacena en el navegador del cliente (usuario). El navegador luego envía estas cookies de vuelta al servidor con solicitudes posteriores al mismo dominio.

**Casos de Uso:**

*   Recordar preferencias del usuario (por ejemplo, tema, idioma).
*   Rastrear la actividad del usuario (por ejemplo, artículos en un carrito de compras antes de iniciar sesión, páginas visitadas).
*   Almacenar identificadores no sensibles para la gestión de sesiones (aunque las sesiones de PHP a menudo manejan esto automáticamente).

**Estableciendo Cookies: `setcookie()`**

`bool setcookie ( string $nombre [, string $valor = "" [, int $expira = 0 [, string $ruta = "" [, string $dominio = "" [, bool $seguro = false [, bool $httponly = false ]]]]]] )`

*   `$nombre`: El nombre de la cookie.
*   `$valor`: El valor de la cookie. Este valor se almacena en la computadora del cliente; no almacenes información sensible.
*   `$expira` (Opcional): La marca de tiempo Unix cuando la cookie debe expirar. Si es 0 u omitido, la cookie expira al final de la sesión (cuando el navegador se cierra). Usa `time() + segundos` para una expiración futura (por ejemplo, `time() + 3600 * 24 * 30` para 30 días).
*   `$ruta` (Opcional): La ruta en el servidor donde la cookie estará disponible. `'/'` significa disponible para todo el dominio.
*   `$dominio` (Opcional): El dominio al que la cookie está disponible (por ejemplo, `.example.com` para todos los subdominios).
*   `$seguro` (Opcional): Si es `true`, la cookie solo se enviará a través de conexiones HTTPS.
*   `$httponly` (Opcional): Si es `true`, la cookie no será accesible mediante JavaScript (ayuda a mitigar ataques XSS que intentan robar datos de cookies). **Altamente recomendado.**

**Importante:** `setcookie()` **debe llamarse antes de que se envíe cualquier salida HTML al navegador** (antes de `<html>`, `echo`, etc.) porque modifica las cabeceras HTTP.

```php
<?php
// Establecer una cookie simple
setcookie("preferencia_usuario", "JuanPerez", time() + (86400 * 30), "/"); // Expira en 30 días

// Establecer una cookie segura, httponly
setcookie("id_usuario", "12345", time() + 3600, "/", "", true, true); // Expira en 1 hora

// Esto debe estar antes de cualquier salida HTML
?>
<!DOCTYPE html>
<html>
<head><title>Cookies</title></head>
<body>
<?php
echo "Las cookies han sido establecidas (o se intentó).<br>";
?>
</body>
</html>
```

**Accediendo a Cookies: Superglobal `$_COOKIE`**

La superglobal `$_COOKIE` es un array asociativo que contiene las cookies enviadas por el navegador al script.

```php
<?php
if (isset($_COOKIE['preferencia_usuario'])) {
    $preferenciaUsuario = htmlspecialchars($_COOKIE['preferencia_usuario']);
    echo "¡Bienvenido de nuevo, " . $preferenciaUsuario . "!<br>";
} else {
    echo "No se encontró la cookie de preferencia de usuario.<br>";
}

if (isset($_COOKIE['id_usuario'])) {
    $idUsuario = htmlspecialchars($_COOKIE['id_usuario']);
    echo "ID de Usuario desde cookie: " . $idUsuario . "<br>";
}
?>
```
*   Siempre usa `isset()` para comprobar si una cookie existe antes de intentar acceder a ella.
*   Sanitiza los valores de las cookies con `htmlspecialchars()` si los muestras, ya que pueden ser modificados por el usuario.

**Modificando/Eliminando Cookies:**

*   **Modificar:** Llama a `setcookie()` de nuevo con el mismo nombre pero un nuevo valor y/o tiempo de expiración.
*   **Eliminar:** Llama a `setcookie()` con el mismo nombre y una fecha de expiración en el pasado (por ejemplo, `time() - 3600`).

```php
<?php
// Para eliminar la cookie 'preferencia_usuario'
setcookie("preferencia_usuario", "", time() - 3600, "/");
// Es una buena práctica también eliminarla del array $_COOKIE para la ejecución actual del script
if (isset($_COOKIE['preferencia_usuario'])) {
    unset($_COOKIE['preferencia_usuario']);
}
echo "Cookie de preferencia de usuario eliminada (o intento realizado).<br>";
?>
```

**Notas de Seguridad para Cookies:**

*   **Almacenamiento del Lado del Cliente:** Las cookies se almacenan en la máquina del usuario y pueden ser vistas, modificadas o eliminadas por el usuario.
*   **No Almacenes Datos Sensibles:** Evita almacenar información altamente sensible como contraseñas o detalles de tarjetas de crédito directamente en cookies. Almacena identificadores que se puedan usar para buscar datos en el servidor si es necesario.
*   **Bandera `HttpOnly`:** Usa `httponly = true` para evitar que JavaScript acceda a la cookie, lo que ayuda a proteger contra el robo de cookies XSS.
*   **Bandera `Secure`:** Usa `secure = true` para asegurar que las cookies solo se transmitan a través de HTTPS.
*   **Límite de Tamaño:** Los navegadores imponen límites al tamaño y número de cookies por dominio (típicamente alrededor de 4KB por cookie).

## 8. Trabajando con Sesiones

Las sesiones proporcionan una forma de almacenar información sobre un usuario a través de múltiples solicitudes de página. A diferencia de las cookies, los datos de la sesión se almacenan principalmente en el **lado del servidor**. Un ID de sesión único se almacena típicamente en una cookie en el navegador del cliente para identificar la sesión.

**Cómo Funcionan las Sesiones:**

1.  El usuario visita una página que inicia una sesión.
2.  PHP genera un ID de Sesión único (una cadena aleatoria larga).
3.  PHP envía este ID de Sesión de vuelta al navegador, usualmente almacenado en una cookie (por ejemplo, llamada `PHPSESSID`).
4.  En solicitudes de página subsiguientes del usuario, el navegador envía la cookie de ID de Sesión de vuelta al servidor.
5.  PHP usa este ID para recuperar los datos de sesión correspondientes almacenados en el servidor.

**Iniciando/Reanudando una Sesión: `session_start()`**

`bool session_start ( [ array $opciones = [] ] )`

*   Esta función comprueba si un ID de sesión está presente (por ejemplo, desde una cookie).
    *   Si existe un ID y es válido, carga los datos de sesión existentes en la superglobal `$_SESSION`.
    *   Si no existe un ID o es inválido, crea una nueva sesión, genera un nuevo ID de sesión y prepara `$_SESSION` como un array vacío.
*   `session_start()` **debe llamarse al principio de tu script**, antes de que se envíe cualquier salida al navegador (similar a `setcookie()`), porque podría necesitar enviar cookies de sesión.

**Almacenando Datos de Sesión: Superglobal `$_SESSION`**

Una vez que se inicia una sesión, puedes almacenar y recuperar datos usando el array asociativo `$_SESSION`.

```php
<?php
session_start(); // Inicia o reanuda la sesión

// Almacenando datos en la sesión
$_SESSION['nombre_usuario'] = 'JuanPerez';
$_SESSION['rol_usuario'] = 'administrador';
$_SESSION['hora_login'] = time();

echo "Datos de sesión establecidos.<br>";
?>
```

**Accediendo a Datos de Sesión:**

```php
<?php
session_start(); // Esencial para acceder a los datos de la sesión

if (isset($_SESSION['nombre_usuario'])) {
    $nombreUsuario = htmlspecialchars($_SESSION['nombre_usuario']);
    $rol = htmlspecialchars($_SESSION['rol_usuario'] ?? 'invitado');
    echo "¡Bienvenido, " . $nombreUsuario . "! Tu rol es: " . $rol . ".<br>";

    if (isset($_SESSION['hora_login'])) {
        echo "Iniciaste sesión el: " . date("Y-m-d H:i:s", $_SESSION['hora_login']) . "<br>";
    }
} else {
    echo "No has iniciado sesión (no hay nombre de usuario en la sesión).<br>";
    // Podrías redirigir a una página de login aquí
    // header('Location: login.php');
    // exit;
}
?>
```

**Destruyendo una Sesión:**

Para terminar completamente una sesión y eliminar todos sus datos:

1.  **`session_unset()`**: Libera todas las variables de sesión actualmente registradas en `$_SESSION`. Esto vacía el array `$_SESSION`.
2.  **`session_destroy()`**: Destruye todos los datos registrados a una sesión en el servidor. Elimina el archivo de sesión o la entrada de la base de datos.
3.  **Opcional: Eliminar la cookie de sesión:** Aunque `session_destroy()` maneja los datos del lado del servidor, la cookie de sesión podría seguir existiendo en el navegador del usuario. Para cerrar sesión completamente, es una buena práctica eliminar también esta cookie.

```php
<?php
session_start(); // La sesión debe iniciarse para destruirla

// 1. Desestablecer todas las variables de sesión
$_SESSION = array(); // O session_unset();

// 2. Destruir la cookie de sesión (opcional pero buena práctica para logout)
if (ini_get("session.use_cookies")) {
    $params = session_get_cookie_params();
    setcookie(session_name(), '', time() - 42000,
        $params["path"], $params["domain"],
        $params["secure"], $params["httponly"]
    );
}

// 3. Finalmente, destruir la sesión
session_destroy();

echo "Sesión destruida. Has cerrado sesión.<br>";
?>
```

**Configuración de Sesión:**
El comportamiento de la sesión de PHP se puede configurar en `php.ini` o usando `ini_set()` o `session_set_cookie_params()`. Algunas configuraciones comunes:
*   `session.name`: Nombre de la cookie de sesión (predeterminado: `PHPSESSID`).
*   `session.cookie_lifetime`: Duración de la cookie de sesión en segundos (0 significa hasta que se cierre el navegador).
*   `session.gc_maxlifetime`: Cuánto tiempo se conservan los datos de sesión inactivos en el servidor (recolección de basura).
*   `session.cookie_secure`: Enviar la cookie de sesión solo a través de HTTPS. **Recomendado para producción.**
*   `session.cookie_httponly`: Hacer que la cookie de sesión sea inaccesible para JavaScript. **Altamente recomendado.**
*   `session.use_strict_mode`: Evita que PHP use un ID de sesión proporcionado por el usuario si no ha sido inicializado por el servidor. Bueno para la seguridad.

**Casos de Uso para Sesiones:**
*   **Estado de Inicio de Sesión del Usuario:** Almacenar si un usuario ha iniciado sesión y su ID de usuario.
*   **Carritos de Compra:** Mantener un seguimiento de los artículos que un usuario ha añadido a su carrito antes de pagar.
*   **Preferencias del Usuario:** Almacenar configuraciones temporales del usuario para su visita actual.
*   **Mensajes Flash:** Mostrar mensajes a través de redirecciones de página (por ejemplo, "¡Artículo guardado exitosamente!").

Las sesiones son una herramienta poderosa para crear experiencias web interactivas y personalizadas. Recuerda siempre llamar a `session_start()` antes de necesitar acceder o modificar los datos de `$_SESSION`.
[end of php_tutorial/05_forms_and_user_input_es.md]
