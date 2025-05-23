# Ejercicios de Repaso

Estos ejercicios están diseñados para ayudarte a practicar y combinar los conceptos que has aprendido a lo largo de este tutorial de PHP, incluyendo PHP para la lógica del backend, HTML para la estructura, CSS para el estilo (aunque mínimo aquí), y JavaScript (específicamente Vue.js) para el desarrollo frontend interactivo.

## Ejercicio 1: "Saludador" - Interacción PHP y Vue

*   **Objetivo:** Entender el paso básico de datos desde un backend PHP a un componente Vue.js.
*   **Requisitos:**
    *   **Backend PHP:**
        1.  Crea un script PHP (por ejemplo, `api/saludador.php`).
        2.  En este script, define un mensaje de saludo (por ejemplo, "¡Hola desde el Mundo PHP!").
        3.  El script debe enviar este mensaje como un objeto JSON. Por ejemplo: `{"mensaje": "¡Hola desde el Mundo PHP!"}`.
        4.  Establece la cabecera `Content-Type` a `application/json`.
    *   **Frontend Vue.js:**
        1.  Crea un componente Vue.js simple (por ejemplo, `MostrarSaludo.vue`).
        2.  Cuando este componente se monte (por ejemplo, en el hook de ciclo de vida `mounted()` o `onMounted` en Composition API), usa Axios (o la API Fetch) para realizar una solicitud `GET` a tu script PHP.
        3.  Almacena el mensaje de saludo recibido del script PHP en una propiedad de datos del componente Vue.
        4.  Muestra este mensaje de saludo en la plantilla del componente (por ejemplo, dentro de una etiqueta `<h1>` o `<p>`).
*   **Enfoque:**
    *   PHP: Servir datos como JSON.
    *   Vue.js: Realizar llamadas API (solicitud GET), manejar respuestas asíncronas, vinculación de datos, ciclo de vida del componente.
    *   Interacción básica cliente-servidor.
*   **Pistas:**
    *   PHP: Usa `json_encode(['mensaje' => $tu_variable_de_saludo]);` y `header('Content-Type: application/json');`.
    *   Vue: Instala Axios si no lo has hecho. Recuerda manejar la promesa devuelta por Axios.
    *   Asegúrate de que tu script PHP sea accesible por el servidor de desarrollo de Vue (CORS podría ser necesario si se ejecutan en diferentes puertos).

## Ejercicio 2: Manejo Básico de Formularios - Frontend Vue, Backend PHP

*   **Objetivo:** Implementar el envío de formularios desde un frontend Vue.js a un backend PHP, incluyendo validación básica del lado del servidor.
*   **Requisitos:**
    *   **Frontend Vue.js:**
        1.  Crea un componente Vue (por ejemplo, `FormularioContacto.vue`).
        2.  Diseña un formulario HTML dentro de este componente. El formulario debe incluir campos para:
            *   Nombre (entrada de texto)
            *   Email (entrada de email)
            *   Mensaje (textarea)
        3.  Usa `v-model` para vincular estos campos de formulario a propiedades de datos en tu componente Vue.
        4.  Implementa un método que se llame cuando se envíe el formulario (`@submit.prevent`).
        5.  Este método debe usar Axios (o Fetch) para enviar los datos del formulario a un script PHP usando una solicitud `POST`. Los datos deben enviarse como una carga útil JSON.
        6.  Muestra cualquier mensaje de éxito o error recibido del backend PHP.
    *   **Backend PHP:**
        1.  Crea un script PHP (por ejemplo, `api/manejador_contacto.php`).
        2.  Este script debe esperar recibir datos JSON mediante una solicitud `POST`.
        3.  Recupera los datos POST crudos (`file_get_contents('php://input')`) y decodifícalos (`json_decode()`).
        4.  Realiza una validación básica del lado del servidor:
            *   Comprueba si los campos `nombre`, `email` y `mensaje` están presentes y no están vacíos.
            *   Valida que el campo `email` contenga un formato de email válido (por ejemplo, usando `filter_var($email, FILTER_VALIDATE_EMAIL)`).
        5.  Si la validación falla, envía una respuesta JSON con un código de estado de error apropiado (por ejemplo, 400 Bad Request) y un array de mensajes de error.
            Ejemplo: `{"estado": "error", "errores": {"email": "Formato de email inválido."}}`
        6.  Si la validación pasa, simula el procesamiento del mensaje (por ejemplo, podrías registrarlo en un archivo o simplemente preparar un mensaje de éxito). Envía una respuesta JSON indicando éxito (por ejemplo, código de estado 200 OK o 201 Created).
            Ejemplo: `{"estado": "exito", "mensaje": "¡Tu mensaje ha sido recibido!"}`
        7.  Establece la cabecera `Content-Type` a `application/json`.
*   **Enfoque:**
    *   Vue.js: Manejo de formularios con `v-model`, manejo de eventos, realización de solicitudes `POST` con datos JSON, visualización de retroalimentación dinámica.
    *   PHP: Recepción de cargas útiles JSON, validación del lado del servidor, estructuración de respuestas JSON para éxito/error.
    *   Será necesaria la configuración de CORS en el backend PHP.
*   **Pistas:**
    *   Vue: Recuerda establecer la cabecera `Content-Type: application/json` en tu solicitud POST de Axios si Axios no lo hace automáticamente para objetos.
    *   PHP: Comprueba `$_SERVER['REQUEST_METHOD']`. Usa `http_response_code()` para establecer códigos de estado.
    *   Considera cómo mostrar múltiples errores de validación de forma agradable en Vue.

## Ejercicio 3: Aplicación de Lista de Tareas (To-Do List) - CRUD Completo

*   **Objetivo:** Construir una aplicación completa de Crear, Leer, Actualizar y Eliminar (CRUD) usando Vue.js para el frontend y PHP con una base de datos para el backend.
*   **Requisitos:**
    *   **API Backend PHP:**
        1.  Elige una base de datos: Se recomienda **SQLite** para este ejercicio debido a su simplicidad (archivo único, no se necesita configuración de servidor separada). Si te sientes cómodo, puedes usar MySQL/MariaDB con PDO.
        2.  Crea una tabla para los ítems de la lista de tareas (por ejemplo, `tareas` con columnas como `id` (Clave Primaria, Autoincremental), `titulo` (TEXT), `completada` (BOOLEAN o INTEGER 0/1), `creada_en` (DATETIME)).
        3.  Implementa los siguientes endpoints de API usando PHP y PDO (o la clase SQLite3):
            *   `GET /api/todos.php`: Obtener todos los ítems de tareas de la base de datos. Devolver como JSON.
            *   `POST /api/todos.php`: Añadir un nuevo ítem de tarea. Espera datos JSON (`{"titulo": "Nueva tarea"}`). Insertar en la base de datos. Devolver la tarea recién creada (con su ID) como JSON con un estado 201.
            *   `PUT /api/todos.php?id={id}`: Actualizar un ítem de tarea existente. Espera datos JSON (por ejemplo, `{"titulo": "Título actualizado", "completada": true}`). Actualizar el registro correspondiente en la base de datos. Devolver la tarea actualizada o un mensaje de éxito.
            *   `DELETE /api/todos.php?id={id}`: Eliminar un ítem de tarea por su ID. Devolver un mensaje de éxito o estado (por ejemplo, 204 No Content).
        4.  **Usa sentencias preparadas para todas las operaciones de base de datos que involucren entrada del usuario para prevenir la inyección SQL.**
        5.  Asegúrate de usar las cabeceras `Content-Type: application/json` y los códigos de estado HTTP apropiados.
    *   **Frontend Vue.js:**
        1.  Crea un componente Vue (por ejemplo, `ListaTareas.vue`).
        2.  **Mostrar Tareas:** Obtén y muestra la lista de ítems de tareas desde el endpoint `GET /api/todos.php` cuando el componente se cargue.
        3.  **Añadir Tareas:** Incluye un campo de entrada y un botón para añadir nuevos ítems de tareas. Enviar esto debería realizar una solicitud `POST` a `/api/todos.php`. Actualiza la lista dinámicamente.
        4.  **Marcar como Completada/Incompleta:** Cada ítem de tarea en la lista debe tener una forma (por ejemplo, una casilla de verificación o un botón) para alternar su estado `completada`. Esto debería activar una solicitud `PUT` a `/api/todos.php?id={id}` con el estado actualizado. Refleja visualmente el cambio (por ejemplo, tachado para tareas completadas).
        5.  **Eliminar Tareas:** Cada ítem de tarea debe tener un botón de eliminar. Hacer clic en él debería realizar una solicitud `DELETE` a `/api/todos.php?id={id}`. Elimina el ítem de la lista dinámicamente.
        6.  **(Opcional) Editar Tareas:** Añade funcionalidad para editar el texto de un ítem de tarea existente. Esto probablemente implicaría otra solicitud `PUT`.
*   **Enfoque:**
    *   PHP: Diseño de API RESTful, PDO/SQLite3 para interacción con base de datos, sentencias preparadas, operaciones CRUD.
    *   Vue.js: Gestión de una lista de ítems, realización de solicitudes GET, POST, PUT, DELETE, actualización reactiva de la UI, métodos de componente, gestión de estado (el estado local del componente está bien).
    *   Integración full-stack.
*   **Pistas:**
    *   **Configuración PHP/SQLite:**
        ```php
        // Ejemplo de conexión a SQLite en PHP
        // $ruta_bd = __DIR__ . '/../database/todo.sqlite'; // Almacenar BD fuera de /api
        // try {
        //    $pdo = new PDO('sqlite:' . $ruta_bd);
        //    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        //    // Crear tabla si no existe
        //    $pdo->exec("CREATE TABLE IF NOT EXISTS tareas (
        //        id INTEGER PRIMARY KEY AUTOINCREMENT,
        //        titulo TEXT NOT NULL,
        //        completada INTEGER DEFAULT 0,
        //        creada_en DATETIME DEFAULT CURRENT_TIMESTAMP
        //    )");
        // } catch (PDOException $e) { die("Error BD: " . $e->getMessage()); }
        ```
    *   Vue: Necesitarás métodos para `obtenerTareas`, `agregarTarea`, `actualizarEstadoTarea`, `eliminarTarea`.
    *   Considera cómo se manejarán los IDs para las solicitudes PUT y DELETE (por ejemplo, pasándolos como parámetros de consulta o parámetros de ruta si configuras la reescritura de URL).

## Ejercicio 4: Registro e Inicio de Sesión Básico de Usuarios (Simplificado)

*   **Objetivo:** Entender el flujo fundamental del registro de usuarios, inicio de sesión, cierre de sesión y gestión del estado de autenticación usando sesiones PHP y Vue.js.
*   **Requisitos:**
    *   **API Backend PHP:**
        1.  Usa **SQLite** (como en el Ejercicio 3) o MySQL/MariaDB para almacenar datos de usuario (por ejemplo, tabla `usuarios` con `id`, `nombre_usuario` (único), `email` (único), `hash_contrasena` (TEXT)).
        2.  `POST /api/auth.php?accion=registro`:
            *   Acepta `nombre_usuario`, `email` y `contrasena` (carga útil JSON).
            *   Valida la entrada (por ejemplo, campos requeridos, email válido, complejidad de contraseña - básico está bien para el ejercicio).
            *   Comprueba si el nombre de usuario o email ya existen.
            *   Hashea la contraseña usando `password_hash()`. **No almacenes contraseñas en texto plano.**
            *   Almacena el nuevo usuario en la base de datos.
            *   Devuelve una respuesta JSON de éxito o error.
        3.  `POST /api/auth.php?accion=login`:
            *   Acepta `email` (o `nombre_usuario`) y `contrasena` (carga útil JSON).
            *   Recupera el usuario de la base de datos por email/nombre de usuario.
            *   Si el usuario existe, verifica la contraseña enviada contra el hash almacenado usando `password_verify()`.
            *   Si la verificación es exitosa, inicia una sesión PHP (`session_start()`) y almacena la identificación del usuario (por ejemplo, `$_SESSION['user_id'] = $idUsuario; $_SESSION['username'] = $nombreUsuario;`).
            *   Devuelve una respuesta JSON de éxito (por ejemplo, incluyendo información del usuario como nombre de usuario).
            *   Si la verificación falla, devuelve una respuesta JSON de error (por ejemplo, "Credenciales inválidas").
        4.  `GET /api/auth.php?accion=check`:
            *   Inicia sesión.
            *   Comprueba si `$_SESSION['user_id']` (o similar) está establecido.
            *   Devuelve JSON indicando el estado de inicio de sesión y datos del usuario si está conectado (por ejemplo, `{"conectado": true, "usuario": {"id": 1, "nombre_usuario": "test"}}` o `{"conectado": false}`).
        5.  `POST /api/auth.php?accion=logout`:
            *   Inicia sesión.
            *   Destruye la sesión PHP (`session_unset()`, `session_destroy()`, limpia la cookie de sesión).
            *   Devuelve una respuesta JSON de éxito.
    *   **Frontend Vue.js:**
        1.  Crea componentes para `FormularioRegistro.vue`, `FormularioLogin.vue`.
        2.  Crea una solución simple de gestión de estado global (por ejemplo, un objeto simple o usa Pinia/Vuex si estás familiarizado, pero un objeto reactivo simple está bien para este ejercicio) para almacenar el estado de autenticación e información del usuario actual.
        3.  **Registro:** Implementa el formulario para llamar al endpoint `/registro`.
        4.  **Inicio de Sesión:** Implementa el formulario para llamar al endpoint `/login`. En caso de éxito, actualiza el estado de autenticación global.
        5.  **Comprobar Estado de Autenticación:** Cuando la aplicación se cargue (por ejemplo, en el hook `mounted` de `App.vue`), llama al endpoint `/check-auth` para ver si ya existe una sesión. Actualiza el estado de autenticación global en consecuencia.
        6.  **Renderizado Condicional:**
            *   Muestra enlaces/vistas de "Iniciar Sesión" y "Registrarse" si el usuario no está conectado.
            *   Si está conectado, muestra un mensaje de bienvenida (por ejemplo, "¡Bienvenido, [nombre_usuario]!") y un botón de "Cerrar Sesión".
            *   (Opcional) Crea un componente/ruta "PanelDeControl" que solo sea accesible/visible si el usuario está conectado.
        7.  **Cerrar Sesión:** Implementa el botón "Cerrar Sesión" para llamar al endpoint `/logout` y limpiar el estado de autenticación global.
*   **Enfoque:**
    *   PHP: Gestión de sesiones (`session_start`, `$_SESSION`, `session_destroy`), hashing de contraseñas (`password_hash`, `password_verify`), interacción con base de datos para usuarios.
    *   Vue.js: Gestión del estado de autenticación global, renderizado condicional basado en el estado de inicio de sesión, realización de llamadas API para acciones de autenticación, manejo de formularios para inicio de sesión/registro.
*   **Descargo de Responsabilidad:** **Este ejercicio es solo para fines de aprendizaje y demuestra un flujo de autenticación simplificado. Los sistemas de autenticación de producción requieren medidas de seguridad mucho más robustas, incluyendo pero no limitado a protección CSRF, gestión de sesiones más avanzada, limitación de tasa, sanitización de entrada, protección contra ataques de temporización, autenticación de dos factores, etc.**
*   **Pistas:**
    *   PHP: Asegúrate de que `session_start()` se llame al principio de cualquier script que necesite acceso a la sesión.
    *   Vue: Puedes crear un servicio simple (por ejemplo, `servicioAuth.js`) para encapsular las llamadas Axios a tus endpoints de autenticación.
    *   Para el estado global, se puede crear un objeto reactivo simple en un archivo JS separado e importarlo en los componentes.
        ```javascript
        // services/almacenAuth.js (ejemplo)
        import { reactive } from 'vue';
        export const estadoAuth = reactive({
          conectado: false,
          usuario: null // { id: ..., nombre_usuario: ... }
        });
        ```

Estos ejercicios deberían proporcionar un buen desafío y ayudar a consolidar tu comprensión de cómo PHP y Vue.js pueden trabajar juntos para construir aplicaciones web dinámicas. ¡Buena suerte!
[end of php_tutorial/09_review_exercises_es.md]
