# PHP y Bases de Datos

La mayoría de las aplicaciones web modernas requieren una forma de almacenar, recuperar y gestionar datos de forma persistente. Las bases de datos son la columna vertebral de esta funcionalidad. Esta sección explora cómo PHP interactúa con las bases de datos, centrándose en la extensión PDO, ampliamente utilizada para operaciones de base de datos seguras y flexibles.

## 1. Introducción a las Bases de Datos para Aplicaciones Web

### ¿Qué es una Base de Datos?

Una **base de datos** es una colección organizada de información estructurada, o datos, típicamente almacenados electrónicamente en un sistema informático. Permite el almacenamiento, recuperación, gestión y actualización eficientes de datos. Piénsalo como un sofisticado archivador digital.

### ¿Por Qué Usar Bases de Datos en Aplicaciones Web?

Las bases de datos son esenciales para las aplicaciones web por varias razones:

*   **Almacenamiento Persistente:** Los servidores web y los scripts PHP son generalmente apátridas (stateless). Una vez que un script termina de ejecutarse, cualquier variable o dato en memoria se pierde. Las bases de datos proporcionan una forma de almacenar información permanentemente, para que esté disponible a través de diferentes sesiones de usuario y a lo largo del tiempo (por ejemplo, cuentas de usuario, catálogos de productos, entradas de blog).
*   **Gestión de Grandes Cantidades de Datos:** Las bases de datos están optimizadas para manejar grandes cantidades de datos, mucho más de lo que se puede gestionar razonablemente en archivos de texto o estructuras en memoria.
*   **Integridad y Consistencia de Datos:** Las bases de datos pueden imponer reglas y restricciones (por ejemplo, tipos de datos, unicidad, relaciones) para asegurar la precisión y consistencia de los datos.
*   **Acceso Concurrente:** Las bases de datos están diseñadas para manejar múltiples usuarios o procesos que acceden y modifican datos simultáneamente sin conflictos o corrupción.
*   **Recuperación y Consulta Eficiente de Datos:** Proporcionan potentes lenguajes de consulta (como SQL) para buscar, ordenar, filtrar y agregar datos de manera eficiente.
*   **Seguridad:** Las bases de datos ofrecen mecanismos para controlar el acceso a los datos, asegurando que solo los usuarios autorizados puedan ver o modificar información sensible.

### Bases de Datos Relacionales (RDBMS) vs. Bases de Datos NoSQL

Existen diferentes tipos de sistemas de gestión de bases de datos (DBMS):

*   **Sistemas de Gestión de Bases de Datos Relacionales (RDBMS):**
    *   Estos son el tipo más común para muchas aplicaciones web.
    *   Los datos se organizan en **tablas** (similares a hojas de cálculo).
    *   Las tablas tienen **columnas** predefinidas (atributos con tipos de datos específicos) y **filas** (registros o entradas individuales).
    *   Las relaciones entre tablas se establecen usando **claves (keys)**.
    *   Utilizan **SQL (Structured Query Language)** para la definición y manipulación de datos.
    *   Ejemplos: MySQL, MariaDB, PostgreSQL, Microsoft SQL Server, Oracle, SQLite.
    *   **Enfoque de este tutorial:** Nos centraremos principalmente en RDBMS, específicamente en la sintaxis de MySQL/MariaDB, ya que es muy común en el desarrollo con PHP.

*   **Bases de Datos NoSQL ("Not Only SQL" - No Solo SQL):**
    *   Ofrecen modelos de datos más flexibles que la estructura rígida de los RDBMS.
    *   Pueden ser basadas en documentos (por ejemplo, MongoDB, CouchDB - almacenando datos en documentos tipo JSON), almacenes clave-valor (por ejemplo, Redis, Memcached), almacenes de familias de columnas (por ejemplo, Cassandra) o bases de datos de grafos (por ejemplo, Neo4j).
    *   A menudo se eligen para aplicaciones con conjuntos de datos muy grandes, altas necesidades de escalabilidad o estructuras de datos que evolucionan rápidamente.
    *   Aunque potentes, están fuera del alcance de esta sección introductoria.

### Conceptos Básicos de SQL (para RDBMS)

*   **Tablas:** Una colección de entradas de datos relacionadas, que consta de columnas y filas. Por ejemplo, una tabla `usuarios` o una tabla `productos`.
*   **Columnas (Campos/Atributos):** Una entidad vertical en una tabla que contiene toda la información asociada con un campo específico. Cada columna tiene un nombre y un tipo de dato (por ejemplo, `INT` para entero, `VARCHAR` para cadenas, `DATETIME` para fechas). Columnas de ejemplo en una tabla `usuarios`: `id`, `nombre_usuario`, `email`, `hash_contrasena`, `fecha_registro`.
*   **Filas (Registros/Tuplas):** Una entidad horizontal en una tabla que representa un único ítem de dato o registro. Cada fila contiene un valor para cada columna. Fila de ejemplo en la tabla `usuarios`: `(1, 'juanperez', 'juan.perez@example.com', 'hash_contrasena_aqui', '2023-01-15 10:00:00')`.
*   **Clave Primaria (PK - Primary Key):** Una columna (o un conjunto de columnas) en una tabla que identifica unívocamente cada fila. Los valores en la columna de clave primaria deben ser únicos y usualmente no pueden ser nulos. A menudo, esto es un entero autoincremental como `id`.
*   **Clave Foránea (FK - Foreign Key):** Una columna (o un conjunto de columnas) en una tabla que referencia la clave primaria de otra tabla. Las claves foráneas se utilizan para establecer y hacer cumplir los enlaces (relaciones) entre tablas. Por ejemplo, una tabla `pedidos` podría tener una columna `id_usuario` que es una clave foránea que referencia la columna `id` en la tabla `usuarios`.

### RDBMS Comunes para PHP

*   **MySQL:** El RDBMS de código abierto más popular, ampliamente utilizado con PHP. Conocido por su facilidad de uso y buen rendimiento. (Producto de Oracle)
*   **MariaDB:** Un fork de MySQL desarrollado por la comunidad, creado por los desarrolladores originales de MySQL. Su objetivo es ser un reemplazo directo de MySQL con más características y mejor rendimiento en algunos casos. Altamente compatible con MySQL.
*   **PostgreSQL:** Un potente sistema de base de datos objeto-relacional de código abierto con una sólida reputación de fiabilidad, robustez de características e integridad de datos.
*   **SQLite:** Un motor de base de datos SQL transaccional, autónomo, sin servidor y de configuración cero. La base de datos se almacena en un solo archivo, lo que la hace muy portátil y fácil de usar para aplicaciones más pequeñas o para desarrollo/pruebas.

**Este tutorial utilizará sintaxis y ejemplos generalmente aplicables a MySQL y MariaDB, accedidos a través de la extensión PDO de PHP.**

## 2. Conectándose a una Base de Datos con PHP

PHP necesita una forma de comunicarse con el servidor de base de datos. Esto se hace usando extensiones de base de datos.

### Eligiendo una Extensión de PHP

Hay varias formas de conectarse a bases de datos en PHP:

*   **PDO (PHP Data Objects):**
    *   **Este es el enfoque recomendado para la mayoría de los nuevos proyectos PHP.**
    *   PDO proporciona una **API consistente y orientada a objetos** para acceder a varios sistemas de bases de datos (MySQL, PostgreSQL, SQLite, MS SQL Server, Oracle, etc.). Esto significa que si cambias tu backend de base de datos, solo necesitas cambiar la cadena de conexión y posiblemente algunas consultas SQL (si usan características específicas de la base de datos), pero tu código de acceso a datos PHP (obtención, vinculación) permanece en gran medida igual.
    *   Soporta **sentencias preparadas**, que son cruciales para prevenir vulnerabilidades de inyección SQL.
    *   **Habilitando PDO:** PDO y sus controladores de base de datos específicos (por ejemplo, `pdo_mysql`, `pdo_pgsql`) suelen estar habilitados por defecto en las instalaciones modernas de PHP. Si no, es posible que necesites descomentar o añadir líneas como `extension=pdo.so` y `extension=pdo_mysql.so` (o `.dll` en Windows) en tu archivo `php.ini` y reiniciar tu servidor web.

*   **MySQLi (Extensión Mejorada de MySQL):**
    *   Diseñada específicamente para trabajar con bases de datos **MySQL (y MariaDB).**
    *   Ofrece tanto un **estilo procedural** (similar a las antiguas funciones `mysql_*`, que ahora están obsoletas y eliminadas) como un **estilo orientado a objetos**.
    *   También soporta sentencias preparadas.
    *   Si estás *seguro* de que solo usarás MySQL/MariaDB, MySQLi es una opción viable. Sin embargo, PDO ofrece mejor portabilidad.
    *   Lo mencionaremos brevemente pero centraremos nuestros ejemplos en PDO.

*   Funciones `mysql_*` (por ejemplo, `mysql_connect()`, `mysql_query()`):
    *   Están **obsoletas** desde PHP 5.5.0 y **eliminadas** desde PHP 7.0.0.
    *   **No las uses en código nuevo.** Carecen de soporte para sentencias preparadas y otras características modernas de bases de datos, lo que las hace inseguras y desactualizadas.

### Conectando con PDO

Conectar con PDO implica crear una instancia de la clase `PDO`.

**Nombre de Fuente de Datos (DSN - Data Source Name):**
El DSN es una cadena que especifica el tipo de base de datos, host, nombre de la base de datos y otros parámetros de conexión.

*   **Para MySQL/MariaDB:**
    `"mysql:host=tu_host;dbname=tu_nombre_de_base_de_datos;charset=tu_charset"`
    *   `mysql`: Especifica el controlador PDO a usar.
    *   `host`: El nombre de host o dirección IP de tu servidor de base de datos (por ejemplo, `localhost` o `127.0.0.1` si está en la misma máquina).
    *   `dbname`: El nombre de la base de datos a la que quieres conectarte.
    *   `charset`: El juego de caracteres a usar para la conexión. `utf8mb4` es altamente recomendado para soportar una amplia gama de caracteres, incluyendo emojis.

    Ejemplo: `"mysql:host=localhost;dbname=mi_app_db;charset=utf8mb4"`

*   **Para PostgreSQL:**
    `"pgsql:host=tu_host;dbname=tu_nombre_de_base_de_datos;options='--client_encoding=UTF8'"` (El juego de caracteres podría manejarse de forma diferente)

*   **Para SQLite:**
    `"sqlite:/ruta/a/tu/archivo/de/base_de_datos.sqlite"`
    O para una base de datos en memoria (útil para pruebas): `"sqlite::memory:"`

**Creando una Instancia PDO:**

Creas un nuevo objeto `PDO` pasando el DSN, el nombre de usuario de la base de datos, la contraseña de la base de datos y, opcionalmente, un array de opciones del controlador.

```php
<?php
$db_host = 'localhost';     // Host de la base de datos
$db_nombre = 'mi_blog';     // Nombre de la base de datos
$db_usuario = 'usuario_blog'; // Nombre de usuario de la base de datos
$db_contra = 'P@$$wOrd';    // Contraseña de la base de datos
$charset = 'utf8mb4';       // Charset preferido

// DSN (Nombre de Fuente de Datos)
$dsn = "mysql:host=$db_host;dbname=$db_nombre;charset=$charset";

// Opciones de Conexión PDO
$opciones = [
    PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION, // Lanzar excepciones en errores
    PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,       // Obtener resultados como arrays asociativos
    PDO::ATTR_EMULATE_PREPARES   => false,                  // Deshabilitar sentencias preparadas emuladas por seguridad
];

try {
    // Crear una instancia PDO (conectar a la base de datos)
    $pdo = new PDO($dsn, $db_usuario, $db_contra, $opciones);
    echo "¡Conectado a la base de datos exitosamente!<br>";
    // Ahora puedes usar el objeto $pdo para interactuar con la base de datos
} catch (\PDOException $e) {
    // Manejar errores de conexión
    echo "Falló la conexión: " . $e->getMessage() . "<br>";
    // En una aplicación real, podrías registrar este error y mostrar un mensaje amigable al usuario
    // throw new \PDOException($e->getMessage(), (int)$e->getCode()); // Opcional: relanzar la excepción
    die("Error de conexión con la base de datos. Por favor, inténtalo de nuevo más tarde."); // O salir elegantemente
}
?>
```

**Explicación de las Opciones:**

*   **`PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION`**:
    *   Esta es una configuración **altamente recomendada**. Le dice a PDO que lance excepciones de tipo `PDOException` cada vez que ocurra un error de base de datos.
    *   Esto te permite capturar estas excepciones usando bloques `try...catch` para un manejo de errores robusto.
    *   Otros modos de error son `PDO::ERRMODE_SILENT` (predeterminado, solo establece códigos de error, debes verificarlos manualmente) y `PDO::ERRMODE_WARNING` (emite E_WARNING). `ERRMODE_EXCEPTION` es generalmente el más práctico.

*   **`PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC`**:
    *   Establece la forma predeterminada en que PDO devolverá las filas de la base de datos cuando obtengas datos.
    *   `PDO::FETCH_ASSOC`: Devuelve filas como arrays asociativos (las claves son los nombres de las columnas).
        `['nombre_usuario' => 'juanperez', 'email' => 'juan@example.com']`
    *   Otros modos comunes:
        *   `PDO::FETCH_OBJ`: Devuelve filas como objetos anónimos.
          `$fila->nombre_usuario`, `$fila->email`
        *   `PDO::FETCH_NUM`: Devuelve filas como arrays indexados numéricamente.
          `$fila[0]`, `$fila[1]`
        *   `PDO::FETCH_BOTH` (predeterminado si no se establece): Devuelve filas como arrays indexados tanto por nombre de columna como por número. Esto puede ser redundante.
    *   Puedes sobrescribir este modo de obtención predeterminado al llamar a métodos de obtención específicos.

*   **`PDO::ATTR_EMULATE_PREPARES => false`**:
    *   Cuando se establece en `false`, PDO utiliza sentencias preparadas nativas proporcionadas por el controlador de la base de datos. Esto es generalmente más seguro y a veces más eficiente.
    *   Si se establece en `true` (puede ser predeterminado en versiones antiguas de PHP o algunos controladores), PDO emula las sentencias preparadas, lo que podría no ofrecer el mismo nivel de seguridad contra la inyección SQL en todos los casos extremos. **Es mejor establecer esto en `false` si tu controlador admite sentencias preparadas nativas (la mayoría de los controladores modernos lo hacen).**

**Manejo de Errores con `try...catch`:**
Cuando `PDO::ERRMODE_EXCEPTION` está establecido, cualquier operación de base de datos (conexión, ejecución de consulta) que falle lanzará una `PDOException`. Debes envolver tu código de base de datos en bloques `try...catch` para manejar estos errores potenciales elegantemente.

```php
<?php
// ... (DSN y credenciales de arriba) ...
try {
    $pdo = new PDO($dsn, $db_usuario, $db_contra, $opciones);
    // ... realizar operaciones de base de datos ...
} catch (\PDOException $e) {
    error_log("Error de Base de Datos: " . $e->getMessage()); // Registra el error detallado
    // Muestra un mensaje genérico al usuario
    die("Ocurrió un error en la base de datos. Por favor, inténtalo de nuevo más tarde o contacta con el soporte.");
}
?>
```

## 3. Realizando Operaciones CRUD con PDO

CRUD significa Crear (Create), Leer (Read), Actualizar (Update) y Eliminar (Delete) – las cuatro funciones básicas del almacenamiento persistente.

**La Regla de Oro: Usa Sentencias Preparadas**
Para cualquier consulta SQL que involucre datos proporcionados por el usuario (o cualquier dato que pueda cambiar), **siempre usa sentencias preparadas**. Esta es la forma más efectiva de prevenir ataques de inyección SQL.

Una sentencia preparada es una plantilla de consulta SQL precompilada. Primero envías la estructura de la consulta a la base de datos, y luego envías los datos por separado. La base de datos trata los datos puramente como datos, no como código SQL ejecutable.

### C - Crear (INSERT)

Para añadir nuevos registros (filas) a una tabla, usas la sentencia SQL `INSERT`.

**Pasos:**

1.  **Prepara la sentencia SQL `INSERT`:** Usa marcadores de posición (por ejemplo, `?` o marcadores de posición con nombre como `:nombre_usuario`) para los valores que quieres insertar.
    `INSERT INTO usuarios (nombre_usuario, email, hash_contrasena) VALUES (?, ?, ?)`
    `INSERT INTO usuarios (nombre_usuario, email, hash_contrasena) VALUES (:nusuario, :email, :pcont)`
2.  **Crea un objeto de sentencia preparada:** Llama a `$pdo->prepare($sql)`. Esto devuelve un objeto `PDOStatement`.
3.  **Vincula valores/parámetros a los marcadores de posición:**
    *   **Marcadores de posición anónimos (`?`):** Vincula los valores en el orden en que aparecen en la consulta.
    *   **Marcadores de posición con nombre (`:nombre`):** Vincula los valores a nombres de marcadores de posición específicos. Esto suele ser más legible.
    Puedes vincular valores usando:
    *   `$stmt->bindParam($marcador, $variable, $tipoDato)`: Vincula una variable PHP al marcador de posición. Si el valor de la variable cambia antes de `execute()`, se usa el nuevo valor.
    *   `$stmt->bindValue($marcador, $valor, $tipoDato)`: Vincula un valor específico al marcador de posición directamente.
    *   O, pasa un array de valores directamente al método `$stmt->execute()`. Esta suele ser la forma más concisa para inserciones simples.
4.  **Ejecuta la sentencia preparada:** Llama a `$stmt->execute()`. Para marcadores de posición con nombre, el array pasado a `execute()` debe ser asociativo. Para marcadores de posición `?`, debe ser un array indexado numéricamente.
5.  **Obtén el ID de la última fila insertada (opcional):** Si tu tabla tiene una clave primaria autoincremental, puedes obtener su valor usando `$pdo->lastInsertId()`.

**Ejemplo usando marcadores de posición `?` y `execute()` con un array:**

```php
<?php
// Asume que $pdo es tu objeto PDO conectado de la sección anterior
// Y una tabla 'usuarios': id (INT, PK, AI), nombre_usuario (VARCHAR), email (VARCHAR), fecha_registro (DATETIME)

$nombre_usuario = "anaperez";
$email = "ana.perez@example.com";
$fecha_registro = date('Y-m-d H:i:s'); // Fecha y hora actuales

try {
    $sql = "INSERT INTO usuarios (nombre_usuario, email, fecha_registro) VALUES (?, ?, ?)";
    $stmt = $pdo->prepare($sql);

    // Ejecuta la sentencia pasando un array de valores
    $stmt->execute([$nombre_usuario, $email, $fecha_registro]);

    $ultimoId = $pdo->lastInsertId();
    echo "¡Nuevo usuario creado exitosamente! ID de Usuario: " . $ultimoId . "<br>";

} catch (\PDOException $e) {
    echo "Error al crear usuario: " . $e->getMessage() . "<br>";
}
?>
```

**Ejemplo usando marcadores de posición con nombre:**

```php
<?php
// Asume que $pdo está conectado
$nombreProducto = "Gadget Increíble";
$precio = 99.99;
$descripcion = "¡El gadget más increíble que jamás tendrás!";

try {
    $sql = "INSERT INTO productos (nombre, precio, descripcion) VALUES (:nombre, :precio, :desc)";
    $stmt = $pdo->prepare($sql);

    // Vincula valores usando un array asociativo en execute()
    $stmt->execute([
        ':nombre' => $nombreProducto,
        ':precio' => $precio,
        ':desc' => $descripcion
    ]);
    // O vincula individualmente (más verboso para este caso):
    // $stmt->bindValue(':nombre', $nombreProducto);
    // $stmt->bindValue(':precio', $precio);
    // $stmt->bindValue(':desc', $descripcion);
    // $stmt->execute();

    $ultimoIdProducto = $pdo->lastInsertId();
    echo "¡Nuevo producto añadido! ID de Producto: " . $ultimoIdProducto . "<br>";

} catch (\PDOException $e) {
    echo "Error al añadir producto: " . $e->getMessage() . "<br>";
}
?>
```

### R - Leer (SELECT)

Para recuperar datos de la base de datos, usas la sentencia SQL `SELECT`.

**Obteniendo Datos:**

*   **`PDO::query($sql)` (Para consultas simples SIN entrada de usuario):**
    *   Puedes usar `$pdo->query($sql)` para consultas directas que **no** incluyan partes variables o entrada de usuario.
    *   Ejecuta inmediatamente el SQL y devuelve un objeto `PDOStatement`, o `false` en caso de fallo (si el modo de error no es `ERRMODE_EXCEPTION`).
    *   **Usar con extrema precaución.** Si la cadena de consulta se construye con entrada de usuario, este método es vulnerable a la inyección SQL.
    ```php
    // Ejemplo: Obtener todos los usuarios (SOLO si no hay entrada de usuario involucrada)
    // try {
    //     $stmt = $pdo->query("SELECT id, nombre_usuario, email FROM usuarios");
    //     while ($fila = $stmt->fetch()) { // Modo de obtención predeterminado (ej., PDO::FETCH_ASSOC)
    //         echo "ID Usuario: " . $fila['id'] . ", Nombre Usuario: " . $fila['nombre_usuario'] . "<br>";
    //     }
    // } catch (\PDOException $e) {
    //     echo "Error al obtener usuarios: " . $e->getMessage() . "<br>";
    // }
    ```

*   **`SELECT` con Sentencias Preparadas (Recomendado):**
    Esta es la forma estándar y segura, especialmente si tu consulta `SELECT` tiene una cláusula `WHERE` que involucra entrada de usuario.

**Pasos para `SELECT` con sentencias preparadas:**

1.  **Prepara la sentencia SQL `SELECT`:** Usa marcadores de posición para cualquier parte variable (por ejemplo, en una cláusula `WHERE`).
    `SELECT id, nombre_usuario, email FROM usuarios WHERE id = ?`
    `SELECT * FROM productos WHERE categoria = :categoria AND precio < :precio_max`
2.  **Crea un objeto de sentencia preparada:** `$stmt = $pdo->prepare($sql);`
3.  **Vincula valores a los marcadores de posición (si los hay):** Usa `$stmt->bindValue()`, `$stmt->bindParam()`, o pasa un array a `execute()`.
4.  **Ejecuta la sentencia preparada:** `$stmt->execute()`.
5.  **Obtén los resultados:**
    *   `$stmt->fetch($estiloObtencion)`: Obtiene la siguiente fila única del conjunto de resultados. Devuelve `false` si no hay más filas.
        *   `$estiloObtencion` (opcional): Sobrescribe el estilo de obtención predeterminado (por ejemplo, `PDO::FETCH_OBJ`, `PDO::FETCH_NUM`).
    *   `$stmt->fetchAll($estiloObtencion)`: Obtiene todas las filas restantes del conjunto de resultados y las devuelve como un array de filas.
        *   Ten cuidado con `fetchAll()` en conjuntos de resultados muy grandes, ya que carga todo en memoria.
    *   `$stmt->rowCount()`: Para sentencias `SELECT`, el comportamiento de `rowCount()` varía entre bases de datos. Para MySQL, típicamente devuelve el número de filas *afectadas* por la última sentencia `DELETE`, `INSERT` o `UPDATE`. Para `SELECT`, puede que no dé fiablemente el número de filas devueltas hasta después de que todas las filas sean obtenidas, o podría no funcionar como se espera con todos los controladores para `SELECT`. A menudo es mejor comprobar si `fetch()` devuelve `false` o si `fetchAll()` devuelve un array vacío. Con algunos controladores, podría devolver el número de filas encontradas por un `SELECT` *después* de `fetchAll` o si la sentencia fue preparada con `PDO::CURSOR_SCROLLABLE`.

**Ejemplo: Obteniendo un único usuario por ID:**

```php
<?php
// Asume que $pdo está conectado
$idUsuarioAObtener = 1; // ID de usuario de ejemplo (podría venir de $_GET['id'])

try {
    $sql = "SELECT id, nombre_usuario, email, fecha_registro FROM usuarios WHERE id = ?";
    $stmt = $pdo->prepare($sql);
    $stmt->execute([$idUsuarioAObtener]);

    // Obtener una única fila
    $usuario = $stmt->fetch(); // Usa el modo de obtención predeterminado (ej., PDO::FETCH_ASSOC)

    if ($usuario) {
        echo "Usuario Encontrado:<br>";
        echo "ID: " . htmlspecialchars($usuario['id']) . "<br>";
        echo "Nombre de Usuario: " . htmlspecialchars($usuario['nombre_usuario']) . "<br>";
        echo "Email: " . htmlspecialchars($usuario['email']) . "<br>";
        echo "Registrado: " . htmlspecialchars($usuario['fecha_registro']) . "<br>";
    } else {
        echo "Usuario con ID " . htmlspecialchars($idUsuarioAObtener) . " no encontrado.<br>";
    }

} catch (\PDOException $e) {
    echo "Error al obtener usuario: " . $e->getMessage() . "<br>";
}
?>
```

**Ejemplo: Obteniendo todos los productos de una categoría:**

```php
<?php
// Asume que $pdo está conectado
$categoria = 'electronica';

try {
    $sql = "SELECT nombre, precio FROM productos WHERE categoria = :cat ORDER BY precio DESC";
    $stmt = $pdo->prepare($sql);
    $stmt->execute([':cat' => $categoria]);

    // Obtener todas las filas coincidentes
    $productos = $stmt->fetchAll(); // Usa el modo de obtención predeterminado

    if ($productos) {
        echo "Productos en '" . htmlspecialchars($categoria) . "':<br>";
        echo "<ul>";
        foreach ($productos as $producto) {
            echo "<li>" . htmlspecialchars($producto['nombre']) . " - $" . htmlspecialchars($producto['precio']) . "</li>";
        }
        echo "</ul>";
    } else {
        echo "No se encontraron productos en la categoría '" . htmlspecialchars($categoria) . "'.<br>";
    }

} catch (\PDOException $e) {
    echo "Error al obtener productos: " . $e->getMessage() . "<br>";
}
?>
```

### U - Actualizar (UPDATE)

Para modificar registros existentes en una tabla, usas la sentencia SQL `UPDATE`.

**Pasos:**

1.  **Prepara la sentencia SQL `UPDATE`:** Usa marcadores de posición para los valores en la cláusula `SET` y la cláusula `WHERE`.
    `UPDATE usuarios SET email = ?, ultimo_login = ? WHERE id = ?`
    `UPDATE productos SET precio = :nuevo_precio, cantidad_stock = :stock WHERE producto_id = :id`
2.  **Crea un objeto de sentencia preparada:** `$stmt = $pdo->prepare($sql);`
3.  **Vincula valores a todos los marcadores de posición.**
4.  **Ejecuta la sentencia preparada:** `$stmt->execute()`.
5.  **Comprueba las filas afectadas (opcional):** `$stmt->rowCount()` puede decirte cuántas filas fueron cambiadas por la sentencia `UPDATE`.

**Ejemplo:**

```php
<?php
// Asume que $pdo está conectado
$idUsuarioAActualizar = 1;
$nuevoEmail = "juan.perez.actualizado@example.com";
$horaUltimoLogin = date('Y-m-d H:i:s');

try {
    $sql = "UPDATE usuarios SET email = :email, ultimo_login = :ultimo_login WHERE id = :id";
    $stmt = $pdo->prepare($sql);

    $stmt->execute([
        ':email' => $nuevoEmail,
        ':ultimo_login' => $horaUltimoLogin,
        ':id' => $idUsuarioAActualizar
    ]);

    $filasAfectadas = $stmt->rowCount();
    if ($filasAfectadas > 0) {
        echo "Usuario ID " . htmlspecialchars($idUsuarioAActualizar) . " actualizado exitosamente. " . $filasAfectadas . " fila(s) afectada(s).<br>";
    } else {
        echo "No se encontró usuario con ID " . htmlspecialchars($idUsuarioAActualizar) . " o los datos eran los mismos.<br>";
    }

} catch (\PDOException $e) {
    echo "Error al actualizar usuario: " . $e->getMessage() . "<br>";
}
?>
```

### D - Eliminar (DELETE)

Para eliminar registros de una tabla, usas la sentencia SQL `DELETE`.

**Pasos:**

1.  **Prepara la sentencia SQL `DELETE`:** Usa marcadores de posición en la cláusula `WHERE` para especificar qué registros eliminar. **¡Ten mucho cuidado con las sentencias `DELETE`; sin una cláusula `WHERE`, eliminarás todas las filas de la tabla!**
    `DELETE FROM usuarios WHERE id = ?`
    `DELETE FROM logs WHERE fecha_log < :fecha_corte`
2.  **Crea un objeto de sentencia preparada:** `$stmt = $pdo->prepare($sql);`
3.  **Vincula valores a los marcadores de posición en la cláusula `WHERE`.**
4.  **Ejecuta la sentencia preparada:** `$stmt->execute()`.
5.  **Comprueba las filas afectadas (opcional):** `$stmt->rowCount()` puede decirte cuántas filas fueron eliminadas.

**Ejemplo:**

```php
<?php
// Asume que $pdo está conectado
$idUsuarioAEliminar = 2; // ID de usuario de ejemplo a eliminar

try {
    $sql = "DELETE FROM usuarios WHERE id = :id";
    $stmt = $pdo->prepare($sql);

    $stmt->execute([':id' => $idUsuarioAEliminar]);

    $filasAfectadas = $stmt->rowCount();
    if ($filasAfectadas > 0) {
        echo "Usuario ID " . htmlspecialchars($idUsuarioAEliminar) . " eliminado exitosamente. " . $filasAfectadas . " fila(s) afectada(s).<br>";
    } else {
        echo "No se encontró usuario con ID " . htmlspecialchars($idUsuarioAEliminar) . " para eliminar.<br>";
    }

} catch (\PDOException $e) {
    echo "Error al eliminar usuario: " . $e->getMessage() . "<br>";
}
?>
```

## 4. Sentencias Preparadas e Inyección SQL

### ¿Qué es la Inyección SQL?

La Inyección SQL (SQLi) es una vulnerabilidad común y peligrosa en aplicaciones web. Ocurre cuando un atacante es capaz de insertar o "inyectar" código SQL malicioso en campos de entrada, que luego se ejecuta como parte de las consultas de base de datos de tu aplicación.

**Consecuencias:**

*   Eludir la autenticación.
*   Leer datos sensibles (por ejemplo, credenciales de usuario, información personal, números de tarjetas de crédito).
*   Modificar o eliminar datos.
*   Obtener control administrativo sobre el servidor de base de datos.
*   Ejecutar comandos del sistema operativo (en algunas configuraciones).

**Cómo Sucede (Ejemplo de Código Vulnerable):**

Imagina que tienes código que construye una consulta concatenando directamente la entrada del usuario:

```php
// --- CÓDIGO VULNERABLE - NO USAR ---
// Asume que $pdo está conectado
// $entrada_usuario = $_POST['nombre_usuario']; // ej., 'admin'
// $entrada_contrasena = $_POST['contrasena']; // ej., el usuario ingresa: 'contrasena' OR '1'='1'

// Construcción vulnerable de la consulta:
// $sql = "SELECT * FROM usuarios WHERE nombre_usuario = '" . $entrada_usuario . "' AND contrasena = '" . $entrada_contrasena . "'";
// $stmt = $pdo->query($sql); // Ejecutando directamente la consulta concatenada
// $usuario = $stmt->fetch();

// Si $entrada_usuario es 'admin' y $entrada_contrasena es " ' OR '1'='1 ", el SQL se convierte en:
// SELECT * FROM usuarios WHERE nombre_usuario = 'admin' AND contrasena = '' OR '1'='1'
// El '1'='1' siempre es verdadero, por lo que la cláusula WHERE se vuelve verdadera, potencialmente iniciando sesión al atacante como admin.
// --- FIN CÓDIGO VULNERABLE ---
```
En el ejemplo anterior, si un atacante ingresa `' OR '1'='1` en el campo de contraseña, puede eludir la verificación de la contraseña.

### Cómo las Sentencias Preparadas Previenen la Inyección SQL

Las sentencias preparadas funcionan separando la estructura de la consulta SQL (la plantilla) de los datos (los valores a insertar o comparar).

1.  **Preparar:** La plantilla de la consulta SQL con marcadores de posición se envía primero al servidor de base de datos. El servidor analiza esta plantilla y planifica cómo ejecutarla, sin ver aún los valores de datos reales.
    `SELECT * FROM usuarios WHERE nombre_usuario = :nusuario AND contrasena = :pcont`
2.  **Vincular y Ejecutar:** Los valores de los datos se envían luego por separado al servidor. El motor de la base de datos toma estos valores y los usa en la plantilla precompilada. **Crucialmente, estos valores se tratan estrictamente como datos, no como código SQL ejecutable.** Incluso si los datos contienen sintaxis similar a SQL (por ejemplo, `OR '1'='1'`), se tratarán como una cadena literal a comparar, no como parte del comando SQL.

Esta separación hace que sea virtualmente imposible para un atacante alterar la estructura o lógica de la consulta SQL original usando campos de entrada.

**Código Seguro con Sentencias Preparadas (Contraparte del Ejemplo Vulnerable):**

```php
<?php
// --- CÓDIGO SEGURO ---
// Asume que $pdo está conectado y en ERRMODE_EXCEPTION
$entrada_usuario = $_POST['nombre_usuario'] ?? '';
$entrada_contrasena = $_POST['contrasena'] ?? ''; // En una app real, compararías contraseñas hasheadas

try {
    $sql = "SELECT id, nombre_usuario FROM usuarios WHERE nombre_usuario = :nusuario AND hash_contrasena = :hash_pcont"; // Compara contra una contraseña hasheada
    $stmt = $pdo->prepare($sql);

    // Ejemplo: Asume que $hash_contrasena_de_db se obtiene primero basándose en el nombre de usuario,
    // o estás hasheando $entrada_contrasena para comparar. Por simplicidad aquí, solo mostramos la vinculación.
    // En un login real, típicamente harías:
    // 1. Obtener usuario por nombre_usuario.
    // 2. Si el usuario existe, usar password_verify($entrada_contrasena, $usuario['hash_contrasena']).
    // Este ejemplo demuestra principalmente la vinculación para la cláusula WHERE.

    $stmt->execute([
        ':nusuario' => $entrada_usuario,
        ':hash_pcont' => funcion_hash($entrada_contrasena) // Marcador de posición para hashing de contraseña real
    ]);
    $usuario = $stmt->fetch();

    if ($usuario) {
        echo "Inicio de sesión exitoso (Usuario: " . htmlspecialchars($usuario['nombre_usuario']) . ")<br>";
        // Establecer variables de sesión, etc.
    } else {
        echo "Inicio de sesión fallido: Nombre de usuario o contraseña inválidos.<br>";
    }

} catch (\PDOException $e) {
    echo "Error de base de datos: " . $e->getMessage() . "<br>";
}

function funcion_hash($contrasena) {
    // Marcador de posición para un algoritmo de hashing fuerte como password_hash()
    // return password_hash($contrasena, PASSWORD_DEFAULT);
    return "hasheada_" . $contrasena; // Simplificado para el ejemplo
}
?>
```
Incluso si `$entrada_usuario` fuera `admin' --` o `$entrada_contrasena` fuera `' OR '1'='1`, estos valores serían tratados como cadenas literales al buscar un nombre de usuario o hash de contraseña, no como comandos SQL.

**Siempre usa sentencias preparadas cada vez que la entrada del usuario o cualquier dato variable sea parte de tu consulta SQL, especialmente en cláusulas `WHERE`, `INSERT` o `UPDATE`.**

## 5. Trabajando con Conjuntos de Resultados

Después de ejecutar una consulta `SELECT`, a menudo necesitas procesar las filas devueltas.

### Iterando a Través de Resultados de `fetchAll()`

`fetchAll()` recupera todas las filas en un array, lo que facilita la iteración con `foreach`.

```php
<?php
// Asume que $pdo está conectado
try {
    // Consulta simple, sin entrada de usuario directa en la cadena SQL
    $stmt = $pdo->query("SELECT id, nombre, email FROM contactos LIMIT 10"); 
    $contactos = $stmt->fetchAll(PDO::FETCH_OBJ); // Obtener todos como objetos

    if ($contactos) {
        echo "Contactos:<br>";
        foreach ($contactos as $contacto) {
            echo "ID: " . htmlspecialchars($contacto->id) .
                 " - Nombre: " . htmlspecialchars($contacto->nombre) .
                 " - Email: " . htmlspecialchars($contacto->email) . "<br>";
        }
    } else {
        echo "No se encontraron contactos.<br>";
    }
} catch (\PDOException $e) {
    echo "Error: " . $e->getMessage() . "<br>";
}
?>
```

### Iterando con `fetch()` en un Bucle `while`

Para conjuntos de resultados muy grandes, obtener una fila a la vez con `fetch()` dentro de un bucle `while` puede ser más eficiente en cuanto a memoria que `fetchAll()`.

```php
<?php
// Asume que $pdo está conectado
try {
    $sql = "SELECT nombre_producto, categoria FROM productos WHERE cantidad_stock > :stock_min";
    $stmt = $pdo->prepare($sql);
    $stmt->execute([':stock_min' => 0]);

    echo "Productos Disponibles:<br>";
    // Obtener filas una por una usando el modo de obtención predeterminado (ej., PDO::FETCH_ASSOC)
    while ($fila = $stmt->fetch()) {
        echo "Producto: " . htmlspecialchars($fila['nombre_producto']) .
             " - Categoría: " . htmlspecialchars($fila['categoria']) . "<br>";
    }
    // Comprueba si se procesaron filas si es necesario (ej., usando un contador en el bucle)

} catch (\PDOException $e) {
    echo "Error: " . $e->getMessage() . "<br>";
}
?>
```

### Comprobando si una Consulta Devolvió Algún Resultado

*   Después de `fetchAll()`: Comprueba si el array devuelto está vacío (`empty($resultados)` o `count($resultados) > 0`).
*   Después de `fetch()`: Comprueba si el resultado de `fetch()` es `false`.
*   `$stmt->rowCount()`: Como se mencionó, su fiabilidad para los recuentos de filas `SELECT` varía. Es más fiable para `INSERT`, `UPDATE`, `DELETE`.

## 6. Manejo de Errores en Operaciones de Base de Datos

Un manejo de errores robusto es crucial. Con `PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION`, PDO lanzará `PDOException`s para errores de base de datos.

**Usando Bloques `try...catch`:**

Siempre envuelve tus interacciones con la base de datos (conexión, preparación, ejecución, obtención) en bloques `try...catch`.

```php
<?php
// Asume que $pdo está conectado
$nuevoNombreUsuario = "usuario_prueba";
$nuevoEmail = "prueba@example.com";

try {
    // Inicia una transacción si hay múltiples consultas relacionadas
    // $pdo->beginTransaction();

    $sql = "INSERT INTO usuarios (nombre_usuario, email) VALUES (:nombre_usuario, :email)";
    $stmt = $pdo->prepare($sql);

    $stmt->bindValue(':nombre_usuario', $nuevoNombreUsuario);
    $stmt->bindValue(':email', $nuevoEmail);
    // $stmt->bindValue(':email', "no_es_un_email"); // Para provocar una posible violación de restricción para pruebas

    $stmt->execute();
    echo "Usuario registrado exitosamente.<br>";

    // Si es parte de una transacción:
    // $pdo->commit();

} catch (\PDOException $e) {
    // Si es parte de una transacción, revierte
    // if ($pdo->inTransaction()) {
    //     $pdo->rollBack();
    //     echo "Transacción revertida debido a error.<br>";
    // }

    // Registra el error (lado del servidor)
    error_log("ERROR DE BASE DE DATOS: " . $e->getMessage() . " en " . $e->getFile() . " en la línea " . $e->getLine());
    // error_log("SQL ejecutado: " . $sql); // Ten cuidado al registrar SQL con datos sensibles en marcadores de posición

    // Muestra un mensaje amigable al usuario
    echo "Ocurrió un error al procesar tu solicitud. Por favor, inténtalo de nuevo. (Código de error: DB" . $e->getCode() . ")<br>";
    // Podrías tener un mapeo de códigos de error a mensajes de usuario más específicos
    // Por ejemplo, si $e->getCode() == 23000 (violación de restricción de integridad para MySQL)
    // if ($e->errorInfo[1] == 1062) { // Código de error de entrada duplicada de MySQL
    //    echo "Este nombre de usuario o email ya está en uso.<br>";
    // }
}
?>
```

**Prácticas Clave de Manejo de Errores:**

*   **Registra Errores Detallados:** Almacena mensajes de error detallados (como `$e->getMessage()`, archivo, número de línea) en los registros del lado del servidor. Son invaluables para la depuración. **No muestres errores SQL detallados o trazas de pila a los usuarios finales en un entorno de producción**, ya que pueden revelar vulnerabilidades de seguridad.
*   **Muestra Mensajes Amigables al Usuario:** Muestra mensajes genéricos y útiles al usuario (por ejemplo, "Ocurrió un error, por favor inténtalo de nuevo más tarde.").
*   **Códigos de Error Específicos:** `PDOException::getCode()` o `$e->errorInfo` (un array que contiene el código SQLSTATE, el código de error específico del controlador y el mensaje de error específico del controlador) a veces se pueden usar para identificar tipos específicos de errores (como entrada duplicada, violación de restricción de clave foránea) y proporcionar retroalimentación más específica al usuario si es apropiado.

## 7. Transacciones (Breve Introducción)

### ¿Qué son las Transacciones?

Una **transacción** es una secuencia de una o más operaciones SQL que se realizan como una única "unidad de trabajo" atómica. O todas las operaciones dentro de la transacción se completan y confirman (commit) exitosamente en la base de datos, o si alguna operación falla, toda la transacción se revierte (rollback), y la base de datos se deja en su estado anterior a la transacción.

### Propiedades ACID (Brevemente)

Las transacciones en sistemas de bases de datos robustos se adhieren a las propiedades ACID:

*   **Atomicidad (Atomicity):** Todos los cambios dentro de una transacción se realizan como una sola unidad. O todos se completan con éxito, o ninguno de ellos lo hace (se revierten).
*   **Consistencia (Consistency):** Una transacción lleva la base de datos de un estado válido a otro. Se mantienen las restricciones de integridad de los datos.
*   **Aislamiento (Isolation):** Las transacciones concurrentes están aisladas entre sí. El efecto de una transacción no es visible para otras transacciones incompletas.
*   **Durabilidad (Durability):** Una vez que se confirma una transacción, sus cambios son permanentes y sobrevivirán a fallos del sistema (por ejemplo, cortes de energía, caídas).

### Métodos de Transacción PDO

PDO proporciona métodos para controlar las transacciones:

*   **`PDO::beginTransaction()`**: Desactiva el modo de auto-commit e inicia una nueva transacción. Las consultas posteriores no se guardarán permanentemente hasta que se llame a `commit()`.
*   **`PDO::commit()`**: Hace permanentes en la base de datos todos los cambios realizados desde `beginTransaction()`.
*   **`PDO::rollBack()`**: Descarta todos los cambios realizados desde `beginTransaction()`. La base de datos se restaura al estado en que se encontraba antes de que comenzara la transacción.

### Ejemplo de Caso de Uso

Imagina transferir dinero de una cuenta bancaria a otra. Esto implica dos operaciones:
1.  Decrementar el saldo de la cuenta del remitente.
2.  Incrementar el saldo de la cuenta del receptor.

Ambas operaciones deben tener éxito. Si una falla (por ejemplo, la actualización de la cuenta del remitente funciona, pero la del receptor falla), no quieres datos inconsistentes. Las transacciones manejan esto.

```php
<?php
// Asume que $pdo está conectado
$idRemitente = 1;
$idReceptor = 2;
$montoATransferir = 50.00;

try {
    // 1. Iniciar la transacción
    $pdo->beginTransaction();

    // 2. Decrementar el saldo del remitente
    $sqlDebito = "UPDATE cuentas SET saldo = saldo - :monto WHERE id = :id AND saldo >= :monto";
    $stmtDebito = $pdo->prepare($sqlDebito);
    $stmtDebito->execute([':monto' => $montoATransferir, ':id' => $idRemitente]);
    $filasAfectadasDebito = $stmtDebito->rowCount();

    if ($filasAfectadasDebito == 0) {
        // El remitente no existe o no tiene fondos suficientes
        throw new \Exception("Fondos insuficientes o cuenta remitente no encontrada.");
    }

    // 3. Incrementar el saldo del receptor
    $sqlCredito = "UPDATE cuentas SET saldo = saldo + :monto WHERE id = :id";
    $stmtCredito = $pdo->prepare($sqlCredito);
    $stmtCredito->execute([':monto' => $montoATransferir, ':id' => $idReceptor]);
    $filasAfectadasCredito = $stmtCredito->rowCount();

    if ($filasAfectadasCredito == 0) {
        // Cuenta receptora no encontrada
        throw new \Exception("Cuenta receptora no encontrada.");
    }

    // 4. Si ambas operaciones fueron exitosas, confirmar la transacción
    $pdo->commit();
    echo "¡Transferencia de $" . htmlspecialchars($montoATransferir) . " exitosa!<br>";

} catch (\PDOException $e) {
    // Ocurrió un error de base de datos (violación de restricción, error de sintaxis, problema de conexión)
    if ($pdo->inTransaction()) {
        $pdo->rollBack(); // Revertir cambios en error de base de datos
    }
    error_log("PDOException en Transferencia: " . $e->getMessage());
    echo "Transferencia fallida debido a un error de base de datos. Por favor, inténtalo de nuevo.<br>";
} catch (\Exception $e) {
    // Ocurrió un error de lógica de aplicación (ej., fondos insuficientes)
    if ($pdo->inTransaction()) {
        $pdo->rollBack(); // Revertir cambios en error de aplicación
    }
    error_log("Exception de Aplicación en Transferencia: " . $e->getMessage());
    echo "Transferencia fallida: " . htmlspecialchars($e->getMessage()) . "<br>";
}
?>
```
En este ejemplo, si la operación de débito o crédito falla (o lanza una excepción), se llama a `rollBack()`, asegurando que ningún saldo de cuenta se cambie incorrectamente. Si ambas tienen éxito, `commit()` hace los cambios permanentes.

Las transacciones son cruciales para mantener la integridad de los datos en operaciones que involucran múltiples pasos dependientes.

Esta sección proporciona una base sólida para trabajar con bases de datos en PHP usando PDO. Recuerda que la seguridad (especialmente la prevención de la inyección SQL con sentencias preparadas) y un manejo de errores robusto son primordiales.
[end of php_tutorial/06_php_and_databases_es.md]
