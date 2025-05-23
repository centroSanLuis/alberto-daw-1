# PHP para APIs Web

El desarrollo web moderno a menudo implica la creación de Interfaces de Programación de Aplicaciones (APIs) que permiten que diferentes sistemas de software se comuniquen entre sí. PHP es muy adecuado para construir APIs web robustas y escalables, especialmente APIs RESTful. Esta sección te guiará a través de los conceptos y prácticas de creación de APIs web con PHP.

## 1. Entendiendo las APIs RESTful

### ¿Qué es una API (Interfaz de Programación de Aplicaciones)?

Una **API** es un conjunto de reglas, protocolos y herramientas que permite que diferentes aplicaciones de software se comuniquen e intercambien datos entre sí. Define cómo deben interactuar los componentes de software, abstrayendo los detalles de implementación subyacentes. Piénsalo como un contrato entre dos piezas de software.

### ¿Qué es una API Web?

Una **API Web** es una API a la que se accede a través de una red, típicamente usando el protocolo HTTP. Esto significa que puedes interactuar con una API web realizando solicitudes HTTP a URLs (Localizadores Uniformes de Recursos) específicas, y recibirás respuestas, a menudo en un formato de datos estándar como JSON o XML.

### Principios REST (Representational State Transfer - Transferencia de Estado Representacional)

REST es un estilo arquitectónico para diseñar aplicaciones en red. Las APIs que se adhieren a los principios REST se denominan **APIs RESTful**. REST no es un protocolo estricto sino un conjunto de restricciones orientadoras. Los principios clave incluyen:

*   **Ausencia de Estado (Statelessness):** Cada solicitud de un cliente al servidor debe contener toda la información necesaria para comprender y procesar la solicitud. El servidor no almacena ningún contexto de cliente o estado de sesión entre solicitudes. Si se necesita el estado de la sesión, el cliente es responsable de gestionarlo y enviarlo con cada solicitud relevante.
*   **Arquitectura Cliente-Servidor:** El cliente (por ejemplo, un navegador web, una aplicación móvil) y el servidor (el proveedor de la API) son entidades separadas con responsabilidades distintas. Se comunican a través de una red. Esta separación les permite evolucionar de forma independiente.
*   **Interfaz Uniforme:** Esta es una restricción crucial que simplifica y desacopla la arquitectura. Consta de varias sub-restricciones:
    *   **Identificación de Recursos (URIs):** Los recursos (por ejemplo, un usuario, un producto, una entrada de blog) se identifican mediante Identificadores Uniformes de Recursos (URIs). Por ejemplo, `/usuarios` podría representar una colección de usuarios, y `/usuarios/123` podría representar un usuario específico con ID 123.
    *   **Manipulación de Recursos a Través de Representaciones:** Los clientes interactúan con los recursos intercambiando representaciones de esos recursos. Una representación podría estar en formato JSON, XML o HTML. Por ejemplo, una solicitud `GET` a `/usuarios/123` podría devolver una representación JSON de ese usuario.
    *   **Mensajes Autodescriptivos:** Cada solicitud y respuesta debe contener suficiente información para que la otra parte la entienda. Las cabeceras HTTP (como `Content-Type` para el formato de los datos, o `Authorization` para la seguridad) juegan un papel clave aquí.
    *   **Hipermedia como Motor del Estado de la Aplicación (HATEOAS) (Opcional pero avanzado):** Las respuestas del servidor pueden incluir enlaces (hipermedia) que guían al cliente sobre cómo descubrir otras acciones disponibles o recursos relacionados.
*   **Cacheable:** Las respuestas deben definirse explícita o implícitamente como cacheables o no cacheables. Esto ayuda a mejorar el rendimiento y la escalabilidad al permitir que los clientes o intermediarios reutilicen respuestas anteriores. Se utilizan cabeceras HTTP como `Cache-Control` y `Expires` para esto.

### Recursos

En una API RESTful, el enfoque está en los **recursos**. Un recurso es cualquier pieza de información o entidad que puede ser nombrada y accedida. Ejemplos:
*   Un usuario: `/usuarios/juan.perez` o `/usuarios/101`
*   Una colección de productos: `/productos`
*   Un pedido específico: `/pedidos/A-98765`
*   El clima actual en una ciudad: `/clima/londres`

Se utilizan URIs para identificar estos recursos. Deben diseñarse para ser intuitivos y jerárquicos.

### Métodos HTTP para Operaciones CRUD

Las APIs RESTful aprovechan los métodos HTTP estándar para realizar operaciones de Crear, Leer, Actualizar y Eliminar (CRUD) en los recursos:

*   **`GET`**: Recuperar un recurso o una colección de recursos.
    *   `GET /usuarios`: Recupera una lista de todos los usuarios.
    *   `GET /usuarios/123`: Recupera el usuario con ID 123.
    *   Las solicitudes `GET` deben ser **seguras** (no cambiar el estado del servidor) e **idempotentes** (múltiples solicitudes idénticas tienen el mismo efecto que una sola solicitud).

*   **`POST`**: Crear un nuevo recurso.
    *   `POST /usuarios`: Crea un nuevo usuario. Los datos para el nuevo usuario (por ejemplo, nombre de usuario, email) se envían típicamente en el cuerpo de la solicitud (a menudo como JSON).
    *   Las solicitudes `POST` generalmente **no son idempotentes** (múltiples solicitudes idénticas probablemente crearán múltiples recursos nuevos).

*   **`PUT`**: Actualizar un recurso existente (típicamente reemplazándolo por completo).
    *   `PUT /usuarios/123`: Actualiza el usuario con ID 123. La representación completa del usuario actualizado se envía en el cuerpo de la solicitud.
    *   Las solicitudes `PUT` deben ser **idempotentes**. Si envías la misma solicitud `PUT` varias veces, el estado del recurso debe ser el mismo que después de la primera solicitud exitosa.

*   **`PATCH`**: Actualizar parcialmente un recurso existente.
    *   `PATCH /usuarios/123`: Modifica solo campos específicos del usuario con ID 123 (por ejemplo, solo actualizando la dirección de correo electrónico). El cuerpo de la solicitud contiene solo los cambios.
    *   Las solicitudes `PATCH` generalmente **no son idempotentes** si la operación de parcheo es algo como "añadir a una lista" o "incrementar un valor". Sin embargo, si es "establecer el campo X al valor Y", puede ser idempotente.

*   **`DELETE`**: Eliminar un recurso.
    *   `DELETE /usuarios/123`: Elimina el usuario con ID 123.
    *   Las solicitudes `DELETE` deben ser **idempotentes**. Eliminar un recurso varias veces debe tener el mismo efecto que eliminarlo una vez (el recurso desaparece).

### Códigos de Estado HTTP

Los códigos de estado HTTP son cruciales para indicar el resultado de una solicitud de API. Aquí hay algunos comunes:

*   **2xx Éxito:**
    *   `200 OK`: Respuesta estándar para solicitudes `GET` exitosas. También se usa para `PUT` o `PATCH` exitosos si ningún otro código 2xx específico es más apropiado.
    *   `201 Created` (Creado): La solicitud fue exitosa y se creó un nuevo recurso (típicamente después de una solicitud `POST`). La respuesta a menudo incluye una cabecera `Location` que apunta a la URI del nuevo recurso.
    *   `204 No Content` (Sin Contenido): La solicitud fue exitosa, pero no hay contenido para devolver en el cuerpo de la respuesta (a menudo se usa para solicitudes `DELETE` exitosas o solicitudes `PUT` que no devuelven el recurso actualizado).
*   **4xx Errores del Cliente:**
    *   `400 Bad Request` (Solicitud Incorrecta): El servidor no pudo entender la solicitud debido a una sintaxis inválida (por ejemplo, JSON mal formado, parámetros requeridos faltantes).
    *   `401 Unauthorized` (No Autorizado): La solicitud requiere autenticación del usuario, y el cliente no ha proporcionado credenciales válidas o no está autenticado.
    *   `403 Forbidden` (Prohibido): El cliente está autenticado, pero no tiene permiso para acceder al recurso solicitado.
    *   `404 Not Found` (No Encontrado): El recurso solicitado no pudo encontrarse en el servidor.
    *   `405 Method Not Allowed` (Método No Permitido): El método HTTP utilizado en la solicitud (por ejemplo, `POST`) no es compatible con el recurso solicitado (por ejemplo, intentar hacer `POST` a una URI de usuario específica `/usuarios/123` cuando solo se permite `PUT`).
*   **5xx Errores del Servidor:**
    *   `500 Internal Server Error` (Error Interno del Servidor): Un mensaje de error genérico que indica que algo salió mal en el servidor y el servidor no pudo manejar la solicitud. Esto usualmente indica un error en el código del lado del servidor.
    *   `503 Service Unavailable` (Servicio No Disponible): El servidor está temporalmente incapacitado para manejar la solicitud (por ejemplo, sobrecargado o en mantenimiento).

## 2. Creando Endpoints de API en PHP

Un **endpoint de API** es una URI específica donde se puede acceder a una API.

### Estructurando Tu API

Una forma común de organizar los archivos de la API es colocarlos en un directorio dedicado.

*   **Estructura de Directorios:**
    ```
    /raiz_de_tu_proyecto
        /api
            items.php       // Maneja solicitudes para /api/items y /api/items/{id}
            usuarios.php    // Maneja solicitudes para /api/usuarios y /api/usuarios/{id}
            .htaccess       // (Opcional) Para reescritura de URL
            config.php      // Configuración de API, conexión a base de datos
        /app
            // Tus archivos principales de aplicación
        /public
            index.php       // Punto de entrada principal para la aplicación web
        /vendor           // Dependencias de Composer
    ```

### Enrutamiento (Routing)

El enrutamiento es el proceso de dirigir una solicitud HTTP al código manejador apropiado basándose en la URI y el método HTTP.

*   **Enrutamiento Simple con `$_SERVER['REQUEST_URI']` y `$_SERVER['REQUEST_METHOD']`:**
    Para APIs muy básicas, puedes analizar `$_SERVER['REQUEST_URI']` para determinar el recurso y cualquier ID, y usar `$_SERVER['REQUEST_METHOD']` para determinar la acción.

    `api/items.php`:
    ```php
    <?php
    // Análisis básico de la solicitud (simplificado)
    header("Content-Type: application/json"); // Asumir salida JSON para todas las respuestas aquí

    $request_method = $_SERVER['REQUEST_METHOD'];
    $request_uri_parts = explode('/', trim($_SERVER['REQUEST_URI'], '/'));
    
    // Ejemplo: La URI podría ser /api/items.php o /api/items.php/123
    // Un enrutador más robusto manejaría /api/items/123 directamente
    
    $resource_id = null;
    // Encontrar la parte del nombre del script
    $script_name_index = array_search(basename(__FILE__), $request_uri_parts);

    if ($script_name_index !== false && isset($request_uri_parts[$script_name_index + 1])) {
        $resource_id = filter_var($request_uri_parts[$script_name_index + 1], FILTER_SANITIZE_NUMBER_INT);
    }

    // Esta es una forma muy ingenua de obtener el ID si se usan parámetros tipo ruta después del nombre del script
    // ej. /api/items.php/1
    // Una mejor manera es usar parámetros de consulta como /api/items.php?id=1
    // O implementar reescritura de URL.

    // Si se usan parámetros de consulta:
    $resource_id_query = isset($_GET['id']) ? filter_var($_GET['id'], FILTER_SANITIZE_NUMBER_INT) : null;
    if ($resource_id_query) {
        $resource_id = $resource_id_query;
    }


    switch ($request_method) {
        case 'GET':
            if ($resource_id) {
                echo json_encode(['mensaje' => "Obteniendo ítem con ID: $resource_id"]);
                // En una app real: obtener ítem de la BD
            } else {
                echo json_encode(['mensaje' => 'Obteniendo todos los ítems']);
                // En una app real: obtener todos los ítems de la BD
            }
            break;
        case 'POST':
            echo json_encode(['mensaje' => 'Creando un nuevo ítem']);
            // En una app real: obtener datos del cuerpo de la solicitud, validar, guardar
            break;
        // Añadir casos PUT, DELETE
        default:
            http_response_code(405); // Método No Permitido
            echo json_encode(['error' => 'Método No Permitido']);
            break;
    }
    ?>
    ```
    Para acceder con ID usando el enfoque de parámetro de consulta: `/api/items.php?id=1`

*   **Reescritura de URL con `.htaccess` (para Apache):**
    Para lograr URLs más limpias como `/api/items/123` en lugar de `/api/items.php?id=123`, puedes usar `mod_rewrite` de Apache.

    Coloca un archivo `.htaccess` en tu directorio `/api` (o en la raíz del proyecto, ajustando las rutas):
    ```apache
    # /api/.htaccess
    RewriteEngine On

    # Redirigir solicitudes para /api/items/ (barra diagonal final opcional) a items.php
    RewriteRule ^items/?$ items.php [L]

    # Redirigir solicitudes para /api/items/algun_id a items.php?id=algun_id
    RewriteRule ^items/([0-9a-zA-Z_-]+)/?$ items.php?id=$1 [L,QSA]
    
    # Reglas similares para otros recursos como usuarios:
    # RewriteRule ^usuarios/?$ usuarios.php [L]
    # RewriteRule ^usuarios/([0-9a-zA-Z_-]+)/?$ usuarios.php?id=$1 [L,QSA]
    ```
    *   `RewriteEngine On`: Habilita el motor de reescritura.
    *   `RewriteRule patron destino [banderas]`: Define una regla de reescritura.
        *   `^items/?$`: Coincide con `/items` o `/items/`.
        *   `items.php`: El archivo real para manejar la solicitud.
        *   `[L]`: Última regla; si coincide, detiene el procesamiento de más reglas.
        *   `^items/([0-9a-zA-Z_-]+)/?$`: Coincide con `/items/` seguido de uno o más caracteres alfanuméricos, guiones bajos o guiones (capturados en `$1`), con una barra diagonal final opcional.
        *   `items.php?id=$1`: Reescribe a `items.php`, pasando el ID capturado como un parámetro de consulta `id`.
        *   `[QSA]`: Query String Append (Anexar Cadena de Consulta); si la solicitud original tenía una cadena de consulta, se anexa a la URL reescrita.

    Con este `.htaccess` en `/api/`, una solicitud a `/api/items/123` sería enrutada internamente a `/api/items.php?id=123`. Tu script PHP (`items.php`) puede entonces acceder al ID mediante `$_GET['id']`.

## 3. Manejando Diferentes Métodos HTTP

Usas la superglobal `$_SERVER['REQUEST_METHOD']` para determinar qué método HTTP se usó para la solicitud.

```php
<?php
// api/mi_recurso.php
header("Content-Type: application/json"); // Buena práctica general para APIs

$metodo = $_SERVER['REQUEST_METHOD'];
$id = isset($_GET['id']) ? (int)$_GET['id'] : null; // Asumiendo ID de cadena de consulta o regla de reescritura

// Datos ficticios para el ejemplo
$items = [
    1 => ['id' => 1, 'nombre' => 'Ítem A', 'precio' => 10.99],
    2 => ['id' => 2, 'nombre' => 'Ítem B', 'precio' => 5.50],
];

switch ($metodo) {
    case 'GET':
        if ($id !== null) {
            if (isset($items[$id])) {
                echo json_encode($items[$id]);
            } else {
                http_response_code(404); // No Encontrado
                echo json_encode(['error' => 'Ítem no encontrado']);
            }
        } else {
            echo json_encode(array_values($items)); // Devolver todos los ítems
        }
        break;

    case 'POST':
        // Para POST, típicamente leerías datos JSON del cuerpo de la solicitud
        $inputData = json_decode(file_get_contents('php://input'), true);
        if (json_last_error() === JSON_ERROR_NONE && isset($inputData['nombre']) && isset($inputData['precio'])) {
            $nuevoItemId = max(array_keys($items)) + 1; // Generación simple de ID
            $nuevoItem = [
                'id' => $nuevoItemId,
                'nombre' => filter_var($inputData['nombre'], FILTER_SANITIZE_STRING), // Sanitizar
                'precio' => filter_var($inputData['precio'], FILTER_VALIDATE_FLOAT) // Validar
            ];
            // $items[$nuevoItemId] = $nuevoItem; // En una app real, guardar en BD
            http_response_code(201); // Creado
            echo json_encode($nuevoItem);
        } else {
            http_response_code(400); // Solicitud Incorrecta
            echo json_encode(['error' => 'Entrada inválida o JSON mal formado']);
        }
        break;

    case 'PUT':
        if ($id !== null) {
            if (isset($items[$id])) {
                $inputData = json_decode(file_get_contents('php://input'), true);
                if (json_last_error() === JSON_ERROR_NONE && isset($inputData['nombre']) && isset($inputData['precio'])) {
                    $items[$id]['nombre'] = filter_var($inputData['nombre'], FILTER_SANITIZE_STRING);
                    $items[$id]['precio'] = filter_var($inputData['precio'], FILTER_VALIDATE_FLOAT);
                    // En una app real, actualizar en BD
                    echo json_encode($items[$id]);
                } else {
                    http_response_code(400);
                    echo json_encode(['error' => 'Entrada inválida o JSON mal formado para PUT']);
                }
            } else {
                http_response_code(404);
                echo json_encode(['error' => 'Ítem no encontrado para PUT']);
            }
        } else {
            http_response_code(400); // Solicitud Incorrecta - PUT usualmente requiere un ID
            echo json_encode(['error' => 'ID de ítem requerido para PUT']);
        }
        break;

    case 'DELETE':
        if ($id !== null) {
            if (isset($items[$id])) {
                // unset($items[$id]); // En una app real, eliminar de BD
                http_response_code(204); // Sin Contenido (éxito, sin cuerpo de respuesta)
            } else {
                http_response_code(404);
                echo json_encode(['error' => 'Ítem no encontrado para DELETE']);
            }
        } else {
            http_response_code(400); // Solicitud Incorrecta - DELETE requiere un ID
            echo json_encode(['error' => 'ID de ítem requerido para DELETE']);
        }
        break;

    default:
        http_response_code(405); // Método No Permitido
        echo json_encode(['error' => 'Método No Permitido']);
        break;
}
?>
```

## 4. Trabajando con JSON (JavaScript Object Notation)

JSON es el formato de datos estándar de facto para las APIs web modernas.

### ¿Por Qué JSON para APIs?

*   **Ligero:** JSON tiene una sintaxis mínima, lo que resulta en tamaños de mensaje más pequeños en comparación con XML.
*   **Legible por Humanos:** Aunque compacto, JSON sigue siendo relativamente fácil de leer y entender por humanos.
*   **Ampliamente Soportado:** Prácticamente todos los lenguajes de programación del lado del servidor y del lado del cliente tienen un excelente soporte para analizar y generar JSON.
*   **Fácil de Analizar para JavaScript:** JSON es un subconjunto de la sintaxis de literales de objeto de JavaScript, lo que lo hace trivial para los clientes JavaScript (como navegadores web o aplicaciones Node.js) de consumir.

### Enviando JSON desde PHP

1.  **Establecer la Cabecera `Content-Type`:** Antes de enviar cualquier salida JSON, debes decirle al cliente que el cuerpo de la respuesta es JSON.
    ```php
    header('Content-Type: application/json');
    ```
    Esta cabecera debe enviarse **antes de cualquier otra salida** (echo, HTML, espacios en blanco).

2.  **Usar `json_encode()`:** Esta función convierte un array u objeto PHP en una cadena JSON.
    `string json_encode ( mixed $valor [, int $flags = 0 [, int $profundidad = 512 ]] )`
    *   `$valor`: La variable PHP (array, objeto, escalar) a codificar.
    *   `$flags` (Opcional): Máscara de bits de opciones como `JSON_PRETTY_PRINT` (para JSON formateado legible por humanos), `JSON_UNESCAPED_SLASHES`, `JSON_UNESCAPED_UNICODE`.

**Ejemplo: Obteniendo datos y devolviendo JSON:**

```php
<?php
header('Content-Type: application/json');
// header('Access-Control-Allow-Origin: *'); // Para CORS, ver sección posterior

// Datos de muestra (en una API real, esto vendría de una base de datos)
$usuarios = [
    ['id' => 1, 'nombre' => 'Alicia Wonderland', 'email' => 'alicia@example.com'],
    ['id' => 2, 'nombre' => 'Roberto El Constructor', 'email' => 'roberto@example.com'],
    ['id' => 3, 'nombre' => 'Carlos Brown', 'email' => 'carlos@example.com']
];

// Simular la obtención de un usuario específico si se proporciona un ID
if (isset($_GET['id'])) {
    $idSolicitado = (int)$_GET['id'];
    $usuarioEncontrado = null;
    foreach ($usuarios as $usuario) {
        if ($usuario['id'] === $idSolicitado) {
            $usuarioEncontrado = $usuario;
            break;
        }
    }
    if ($usuarioEncontrado) {
        echo json_encode($usuarioEncontrado, JSON_PRETTY_PRINT);
    } else {
        http_response_code(404); // No Encontrado
        echo json_encode(['error' => 'Usuario no encontrado', 'id' => $idSolicitado]);
    }
} else {
    // Devolver todos los usuarios
    echo json_encode($usuarios, JSON_PRETTY_PRINT);
}
?>
```

### Recibiendo JSON en PHP

Cuando un cliente envía datos (por ejemplo, vía `POST` o `PUT`) con una cabecera `Content-Type: application/json`, PHP no puebla automáticamente `$_POST` con los datos JSON. Necesitas leer el cuerpo de la solicitud en crudo.

1.  **Obtener Cuerpo de Solicitud en Crudo:** Usa `file_get_contents('php://input')`.
    `php://input` es un flujo de solo lectura que te permite leer datos crudos del cuerpo de la solicitud.

2.  **Usar `json_decode()`:** Esta función convierte una cadena JSON en una variable PHP (un array asociativo o un objeto).
    `mixed json_decode ( string $cadena_json [, bool $asociativo = false [, int $profundidad = 512 [, int $flags = 0 ]]] )`
    *   `$cadena_json`: La cadena JSON a decodificar.
    *   `$asociativo` (Opcional): Si es `true`, devuelve arrays asociativos. Si es `false` (predeterminado), devuelve objetos (`stdClass`).
    *   `$flags` (Opcional): Opciones como `JSON_THROW_ON_ERROR` (PHP 7.3+) para lanzar una excepción en errores JSON en lugar de devolver `null`.

**Ejemplo: Manejando una solicitud POST con una carga útil JSON:**

```php
<?php
header('Content-Type: application/json');
// header('Access-Control-Allow-Origin: *'); // CORS
// header('Access-Control-Allow-Methods: POST, OPTIONS'); // CORS
// header('Access-Control-Allow-Headers: Content-Type'); // CORS

// Manejar solicitud OPTIONS de pre-vuelo para CORS
if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    http_response_code(204); // Sin Contenido
    exit;
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $jsonPayload = file_get_contents('php://input');
    
    // Intentar decodificar la carga útil JSON
    // Usando true para array asociativo, JSON_THROW_ON_ERROR para mejor manejo de errores
    try {
        $data = json_decode($jsonPayload, true, 512, JSON_THROW_ON_ERROR);
    } catch (JsonException $e) {
        http_response_code(400); // Solicitud Incorrecta
        echo json_encode(['error' => 'JSON mal formado: ' . $e->getMessage()]);
        exit;
    }

    // Validar los datos recibidos (ejemplo)
    if (isset($data['nombre']) && isset($data['email']) && filter_var($data['email'], FILTER_VALIDATE_EMAIL)) {
        // Los datos son válidos (verificación básica)
        // En una aplicación real, guardar en base de datos
        $nuevoIdUsuario = rand(100, 999); // Generación ficticia de ID
        $respuesta = [
            'mensaje' => 'Usuario creado exitosamente',
            'usuario' => [
                'id' => $nuevoIdUsuario,
                'nombre' => filter_var($data['nombre'], FILTER_SANITIZE_STRING),
                'email' => $data['email']
            ]
        ];
        http_response_code(201); // Creado
        echo json_encode($respuesta, JSON_PRETTY_PRINT);
    } else {
        http_response_code(400); // Solicitud Incorrecta
        echo json_encode(['error' => 'Datos inválidos o faltantes. Se requieren "nombre" y "email" válido.']);
    }
} else {
    http_response_code(405); // Método No Permitido
    echo json_encode(['error' => 'Solo se permite el método POST para este endpoint.']);
}
?>
```
**Probando este endpoint `POST`:**
Usarías una herramienta como Postman, Insomnia o cURL para enviar una solicitud `POST` a este script con una cabecera `Content-Type: application/json` y un cuerpo JSON como:
```json
{
    "nombre": "Sara Connor",
    "email": "sara@skynet.com"
}
```

## 5. Construyendo una API Simple - Ejemplo (API de Tareas)

Creemos una API muy simple para gestionar una lista de tareas. Por simplicidad, almacenaremos las tareas en un array PHP en memoria. En una aplicación real, esto sería una base de datos.

**Estructura de Archivos (Conceptual):**
Asume que tenemos un directorio `/api`.
*   `/api/tareas.php`

```php
<?php
// /api/tareas.php

// --- Cabeceras CORS (Permitir solicitudes de cualquier origen para desarrollo) ---
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

// Manejar solicitud OPTIONS de pre-vuelo (importante para CORS)
if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    http_response_code(200); // O 204 Sin Contenido
    exit();
}

// --- Inicializar array de tareas (simulando una base de datos) ---
session_start(); // Usar sesión para persistir datos entre solicitudes para este ejemplo
if (!isset($_SESSION['tareas'])) {
    $_SESSION['tareas'] = [
        1 => ['id' => 1, 'titulo' => 'Aprender Fundamentos de PHP', 'completada' => true],
        2 => ['id' => 2, 'titulo' => 'Construir una API Simple', 'completada' => false],
        3 => ['id' => 3, 'titulo' => 'Entender REST', 'completada' => false]
    ];
    $_SESSION['siguiente_id_tarea'] = 4;
}

$tareas = &$_SESSION['tareas']; // Usar una referencia para modificación más fácil
$siguiente_id_tarea = &$_SESSION['siguiente_id_tarea'];

// --- Función auxiliar para obtener ID de la ruta URL ---
// Ejemplo: /api/tareas.php/1  o /api/tareas.php/ (para todas)
// Esta es una forma muy básica de analizar, un enrutador es mejor para apps complejas.
$pathInfo = $_SERVER['PATH_INFO'] ?? '';
$partesRuta = explode('/', trim($pathInfo, '/'));
$idTarea = isset($partesRuta[0]) && $partesRuta[0] !== '' ? (int)$partesRuta[0] : null;


// --- Manejar Solicitud ---
$metodo = $_SERVER['REQUEST_METHOD'];

switch ($metodo) {
    case 'GET':
        if ($idTarea !== null) { // Obtener tarea específica
            if (isset($tareas[$idTarea])) {
                echo json_encode($tareas[$idTarea]);
            } else {
                http_response_code(404);
                echo json_encode(['error' => 'Tarea no encontrada']);
            }
        } else { // Obtener todas las tareas
            echo json_encode(array_values($tareas));
        }
        break;

    case 'POST': // Crear nueva tarea
        $data = json_decode(file_get_contents('php://input'), true);
        if (isset($data['titulo'])) {
            $nuevaTarea = [
                'id' => $siguiente_id_tarea,
                'titulo' => filter_var($data['titulo'], FILTER_SANITIZE_STRING),
                'completada' => $data['completada'] ?? false
            ];
            $tareas[$siguiente_id_tarea] = $nuevaTarea;
            $siguiente_id_tarea++;
            http_response_code(201); // Creado
            echo json_encode($nuevaTarea);
        } else {
            http_response_code(400);
            echo json_encode(['error' => 'Falta título para nueva tarea']);
        }
        break;

    case 'PUT': // Actualizar tarea existente
        if ($idTarea !== null && isset($tareas[$idTarea])) {
            $data = json_decode(file_get_contents('php://input'), true);
            if ($data) {
                if (isset($data['titulo'])) {
                    $tareas[$idTarea]['titulo'] = filter_var($data['titulo'], FILTER_SANITIZE_STRING);
                }
                if (isset($data['completada'])) {
                    $tareas[$idTarea]['completada'] = (bool)$data['completada'];
                }
                echo json_encode($tareas[$idTarea]);
            } else {
                http_response_code(400);
                echo json_encode(['error' => 'Datos JSON inválidos para actualización']);
            }
        } else {
            http_response_code(404);
            echo json_encode(['error' => 'Tarea no encontrada para actualizar']);
        }
        break;

    case 'DELETE': // Eliminar tarea
        if ($idTarea !== null && isset($tareas[$idTarea])) {
            unset($tareas[$idTarea]);
            http_response_code(204); // Sin Contenido
        } else {
            http_response_code(404);
            echo json_encode(['error' => 'Tarea no encontrada para eliminar']);
        }
        break;

    default:
        http_response_code(405); // Método No Permitido
        echo json_encode(['error' => 'Método No Permitido']);
        break;
}
?>
```

**Probando esta API (usando `tareas.php`):**

Para que el análisis de `idTarea` funcione con URLs como `/api/tareas.php/1`, podrías necesitar un `.htaccess` en el directorio `/api` (si usas Apache):

```apache
# /api/.htaccess
RewriteEngine On

# Si la solicitud no es para un archivo o directorio existente
RewriteCond %{REQUEST_FILENAME} !-f
RewriteCond %{REQUEST_FILENAME} !-d

# Enrutar solicitudes para tareas.php a tareas.php, capturando la información de la ruta
RewriteRule ^tareas.php(/.*)?$ tareas.php$1 [L,QSA]
```
Esta regla permite que `tareas.php` reciba la parte después de `tareas.php` (por ejemplo, `/1`) en `$_SERVER['PATH_INFO']`.

**Solicitudes de Ejemplo (usando cURL o Postman):**

*   `GET /api/tareas.php/`: Listar todas las tareas.
*   `GET /api/tareas.php/1`: Obtener la tarea con ID 1.
*   `POST /api/tareas.php/` con cuerpo JSON `{"titulo": "Nueva Tarea"}`: Crear una nueva tarea.
*   `PUT /api/tareas.php/2` con cuerpo JSON `{"titulo": "Título Tarea Actualizado", "completada": true}`: Actualizar tarea ID 2.
*   `DELETE /api/tareas.php/3`: Eliminar tarea ID 3.

**Nota sobre Persistencia de Datos:** Este ejemplo usa sesiones PHP para hacer que los datos persistan entre solicitudes para demostración. En una API del mundo real, reemplazarías el almacenamiento basado en sesión con operaciones de base de datos (por ejemplo, usando PDO como se cubrió en la sección anterior).

## 6. Autenticación de API y Conceptos Básicos de Seguridad (Breve Resumen)

Proteger tu API es crucial, especialmente si maneja datos sensibles o realiza operaciones críticas.

### ¿Por Qué se Necesita Autenticación?

*   **Controlar Acceso:** Asegurar que solo clientes o usuarios autorizados puedan acceder a tu API.
*   **Identificar Clientes:** Saber quién está usando tu API, lo que puede ser importante para limitación de tasa (rate limiting), registro o facturación.
*   **Proteger Datos:** Prevenir acceso, modificación o eliminación no autorizada de recursos.

### Métodos Comunes de Autenticación (Brevemente)

*   **Claves de API (API Keys):**
    *   A los clientes se les emite una clave secreta única.
    *   El cliente incluye esta clave en las solicitudes, típicamente en una cabecera HTTP (por ejemplo, `X-API-Key: tu_clave_api` o `Authorization: ApiKey tu_clave_api`).
    *   El servidor valida la clave.
    *   Más simple de implementar pero puede ser menos seguro si las claves se comprometen. Las claves suelen ser de larga duración.

*   **Autenticación Basada en Tokens (por ejemplo, JWT - JSON Web Tokens):**
    *   El cliente se autentica (por ejemplo, con nombre de usuario/contraseña) en un endpoint de autenticación.
    *   El servidor emite un token firmado (como un JWT) que contiene "claims" (información sobre el usuario).
    *   El cliente envía este token en la cabecera `Authorization` (por ejemplo, `Authorization: Bearer tu_token_jwt`) con solicitudes de API subsiguientes.
    *   El servidor verifica la firma y los claims del token para autorizar la solicitud.
    *   Los tokens suelen ser de corta duración y pueden renovarse. Los JWTs son apátridas.

*   **OAuth 2.0:**
    *   Un framework de autorización que permite a aplicaciones de terceros acceder a recursos de usuario en un servicio web sin exponer las credenciales del usuario (nombre de usuario/contraseña) a la aplicación de terceros.
    *   Más complejo pero proporciona control de acceso de grano fino y es estándar para autorización de terceros (por ejemplo, "Iniciar sesión con Google/Facebook").

### Consejos Básicos de Seguridad

*   **HTTPS (SSL/TLS):** **Usa siempre HTTPS para tu API.** Esto cifra los datos en tránsito, protegiéndolos de escuchas y manipulaciones.
*   **Validación de Entrada:** Valida rigurosamente todos los datos entrantes (parámetros de ruta, parámetros de consulta, contenido del cuerpo de la solicitud) como se discutió en la sección "Formularios y Entrada de Usuario". Previene XSS, asegura tipos de datos correctos, longitudes, etc.
*   **Codificación de Salida:** Al devolver datos (especialmente si podrían contener contenido generado por el usuario que se muestra en otro lugar), asegúrate de que estén correctamente codificados para el contexto (por ejemplo, `htmlspecialchars` para HTML, aunque para respuestas JSON a un cliente API, esto usualmente no es necesario ya que el cliente maneja la renderización).
*   **Limitación de Tasa (Rate Limiting):** Implementa mecanismos para prevenir el abuso limitando el número de solicitudes que un cliente puede hacer en un cierto período de tiempo.
*   **Códigos de Estado HTTP Apropiados:** Úsalos correctamente para indicar los resultados de las solicitudes.
*   **Manejo de Errores:** Registra errores detallados en el servidor pero devuelve mensajes de error genéricos y no reveladores al cliente.
*   **Principio de Menor Privilegio:** Asegura que la clave de API o token utilizado tenga solo los permisos necesarios para las operaciones que necesita realizar.

La seguridad de las API es un tema profundo. Para APIs de producción, especialmente aquellas que manejan datos sensibles, es esencial un estudio más profundo e implementaciones robustas.

## 7. CORS (Cross-Origin Resource Sharing - Intercambio de Recursos de Origen Cruzado)

### ¿Qué es CORS y Por Qué se Necesita?

Los navegadores web implementan una característica de seguridad llamada **Política del Mismo Origen (Same-Origin Policy - SOP)**. Por defecto, la SOP restringe que las páginas web realicen solicitudes a un dominio (origen) diferente al que sirvió la página web. Un origen se define por la combinación de protocolo (http/https), dominio y puerto.

Si tu API PHP se ejecuta en `http://localhost:8000` y tu frontend Vue.js (o cualquier cliente JavaScript) se ejecuta en `http://localhost:8080` (puerto diferente, por lo tanto, origen diferente), el navegador bloqueará las solicitudes de la aplicación Vue a la API PHP debido a la SOP.

**CORS** es un mecanismo que utiliza cabeceras HTTP adicionales para indicar a los navegadores que otorguen a una aplicación web que se ejecuta en un origen, acceso a recursos seleccionados de un origen diferente.

### Configuración de Cabeceras CORS Básicas en PHP

Para permitir solicitudes de origen cruzado a tu API PHP, necesitas enviar cabeceras CORS específicas en las respuestas de tu API.

```php
<?php
// Permitir solicitudes de un origen específico (ej., el servidor de desarrollo de tu app Vue)
header("Access-Control-Allow-Origin: http://localhost:8080"); 
// O, para APIs públicas o durante desarrollo muy temprano (menos seguro):
// header("Access-Control-Allow-Origin: *"); 

// Métodos HTTP permitidos
header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");

// Cabeceras HTTP permitidas en las solicitudes
header("Access-Control-Allow-Headers: Content-Type, Authorization, X-Requested-With");

// Opcional: Cuánto tiempo pueden almacenarse en caché los resultados de una solicitud de pre-vuelo (en segundos)
// header("Access-Control-Max-Age: 3600");

// Opcional: Si tu API usa cookies o autenticación HTTP con credenciales
// header("Access-Control-Allow-Credentials: true"); // Ten cuidado con esto y los orígenes

// El resto de la lógica de tu API...
// Ejemplo:
// if ($_SERVER['REQUEST_METHOD'] === 'GET') {
//     header("Content-Type: application/json");
//     echo json_encode(["mensaje" => "¡Cabeceras CORS configuradas!"]);
// }
?>
```

**Explicación:**

*   **`Access-Control-Allow-Origin`**: Especifica qué orígenes tienen permitido acceder al recurso.
    *   `*`: Permite cualquier origen. Úsalo con precaución, especialmente para APIs que manejan datos sensibles o requieren autenticación. Es mejor listar orígenes específicos permitidos.
    *   `http://localhost:8080`: Permite solicitudes solo desde este origen específico.
*   **`Access-Control-Allow-Methods`**: Especifica qué métodos HTTP están permitidos al acceder al recurso (por ejemplo, `GET, POST, PUT`).
*   **`Access-Control-Allow-Headers`**: Especifica qué cabeceras HTTP se pueden usar durante la solicitud real. Esto es importante si tu cliente envía cabeceras personalizadas o cabeceras como `Content-Type: application/json` o `Authorization`.

### Manejo de Solicitudes `OPTIONS` de Pre-vuelo (Preflight)

Para ciertos tipos de solicitudes de origen cruzado (llamadas solicitudes "no simples", por ejemplo, aquellas con métodos distintos de `GET`/`HEAD`/`POST`, o con `Content-Type` distinto de `application/x-www-form-urlencoded`, `multipart/form-data`, `text/plain`, o con cabeceras personalizadas), el navegador primero envía una **solicitud `OPTIONS` de pre-vuelo** al servidor.

El servidor debe responder a esta solicitud `OPTIONS` con las cabeceras CORS apropiadas, indicando si la solicitud real está permitida.

Necesitas manejar estas solicitudes `OPTIONS` en tu API PHP, típicamente enviando de vuelta las cabeceras CORS y una respuesta vacía con un estado `200 OK` o `204 No Content`.

```php
<?php
// --- Cabeceras CORS ---
// Permitir solicitudes del servidor de desarrollo de tu app Vue
header("Access-Control-Allow-Origin: http://localhost:8080"); 
// O, para probar con cualquier origen:
// header("Access-Control-Allow-Origin: *"); 

header("Access-Control-Allow-Methods: GET, POST, PUT, PATCH, DELETE, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type, Authorization, X-Requested-With, Origin, Accept");
// header("Access-Control-Allow-Credentials: true"); // Si necesitas permitir cookies/cabeceras de autenticación
// header("Access-Control-Max-Age: 86400"); // Cachear pre-vuelo por 1 día

// --- Manejar solicitud OPTIONS de pre-vuelo ---
if ($_SERVER['REQUEST_METHOD'] == 'OPTIONS') {
    // Devolver una respuesta vacía con estado 200 OK o 204 No Content
    http_response_code(204); 
    exit(); // Importante salir del script después de enviar cabeceras para OPTIONS
}

// --- Continuar con la lógica de tu API ---
header("Content-Type: application/json"); // Establecer para respuestas reales de la API

// Ejemplo:
if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    echo json_encode(["mensaje" => "¡Hola desde la API PHP con CORS!"]);
} elseif ($_SERVER['REQUEST_METHOD'] === 'POST') {
    echo json_encode(["mensaje" => "¡Solicitud POST recibida!"]);
}
// ... etc.
?>
```
Coloca esta lógica de manejo de CORS al principio de tus scripts de API, antes de cualquier otro procesamiento o salida.

Entender e implementar correctamente CORS es esencial cuando se construyen APIs que serán consumidas por aplicaciones JavaScript de frontend que se ejecutan en diferentes orígenes. Para producción, siempre sé específico con `Access-Control-Allow-Origin` en lugar de usar `*` si es posible.

Esto cubre lo esencial para construir APIs Web con PHP. Desde entender los principios REST hasta manejar JSON y asegurar tus endpoints, estos conceptos formarán la base para crear servicios web potentes e interactivos.
[end of php_tutorial/07_php_for_web_apis_es.md]
