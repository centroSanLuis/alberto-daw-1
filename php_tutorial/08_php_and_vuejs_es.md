# Integrando PHP con Vue.js

Vue.js es un popular framework progresivo de JavaScript para construir interfaces de usuario. Aunque Vue.js sobresale en el desarrollo frontend, a menudo necesita un backend para manejar la persistencia de datos, la lógica de negocio y otras tareas del lado del servidor. PHP es una excelente opción para construir este backend, especialmente al crear APIs que Vue.js pueda consumir.

## 1. Introducción a PHP como Backend para Vue.js

### Resumen

*   **Vue.js:** Un framework de JavaScript que se ejecuta en el navegador web del usuario. Es responsable de renderizar la interfaz de usuario, manejar las interacciones del usuario en el frontend y gestionar el estado de la aplicación en el lado del cliente.
*   **PHP:** Un lenguaje de scripting del lado del servidor. Se ejecuta en tu servidor web, no en el navegador del usuario. PHP puede acceder a bases de datos, realizar cálculos complejos, gestionar archivos e implementar lógica de negocio.

### Cómo Interactúan

La forma principal en que Vue.js (frontend) y PHP (backend) interactúan es a través de **solicitudes HTTP**, comúnmente conocidas como llamadas API.

1.  **Vue.js Realiza una Solicitud:** La aplicación Vue.js, ejecutándose en el navegador del usuario, realiza una solicitud HTTP a una URL específica en tu servidor. Esta URL apunta a un script PHP (un endpoint de API).
    *   Esta solicitud puede ser para obtener datos (por ejemplo, una lista de productos), enviar nuevos datos (por ejemplo, un nuevo registro de usuario), actualizar datos existentes o eliminar datos.
    *   Bibliotecas como Axios o la API Fetch incorporada se usan comúnmente en Vue.js para realizar estas solicitudes.

2.  **PHP Procesa la Solicitud:**
    *   El script PHP en el servidor recibe la solicitud HTTP.
    *   Procesa la solicitud, lo que podría implicar:
        *   Validar datos de entrada.
        *   Interactuar con una base de datos (consultar, insertar, actualizar, eliminar).
        *   Ejecutar lógica de negocio.
        *   Autenticar al usuario.

3.  **PHP Envía una Respuesta:**
    *   Después del procesamiento, el script PHP envía una respuesta HTTP de vuelta a la aplicación Vue.js.
    *   Esta respuesta típicamente contiene:
        *   **Datos:** Usualmente formateados como **JSON** (JavaScript Object Notation), que es fácil de analizar y usar para JavaScript.
        *   **Código de Estado HTTP:** Indica el resultado de la solicitud (por ejemplo, `200 OK`, `201 Created`, `404 Not Found`, `500 Internal Server Error`).

4.  **Vue.js Maneja la Respuesta:**
    *   La aplicación Vue.js recibe la respuesta.
    *   Analiza los datos JSON (si los hay) y actualiza las propiedades de datos del componente.
    *   El sistema de reactividad de Vue luego actualiza automáticamente la interfaz de usuario para reflejar los nuevos datos.
    *   También puede manejar errores basados en el código de estado HTTP o mensajes de error en la respuesta.

### Beneficios de Esta Separación

Esta arquitectura cliente-servidor, donde Vue.js maneja el frontend y PHP maneja el backend, ofrece varias ventajas:

*   **Aplicaciones de Página Única (SPAs):** Vue.js puede crear SPAs que se sienten rápidas y receptivas porque solo se intercambian datos con el servidor después de la carga inicial de la página, no páginas HTML completas. La UI se actualiza dinámicamente en el navegador.
*   **Mejor Experiencia de Usuario:** Las SPAs a menudo proporcionan una experiencia más fluida y similar a una aplicación en comparación con las aplicaciones tradicionales de múltiples páginas.
*   **Modularidad y Separación de Preocupaciones:**
    *   El desarrollo frontend y backend pueden ocurrir de forma independiente.
    *   Diferentes equipos pueden trabajar en el frontend Vue.js y el backend PHP.
    *   La API se convierte en un contrato claro entre el frontend y el backend.
*   **Reutilización:** El backend de la API PHP también puede ser consumido por otros clientes, no solo la aplicación web Vue.js (por ejemplo, una aplicación móvil, otro servicio web).
*   **Escalabilidad:** El frontend y el backend pueden escalarse independientemente según sus necesidades específicas.

## 2. Consideraciones de Configuración del Proyecto

### Sirviendo Vue.js y PHP

Cómo sirves tu frontend Vue.js y tu backend PHP depende de si estás en desarrollo o en producción.

#### Servidores de Desarrollo Separados (Común para Desarrollo)

Esta es una configuración muy común y conveniente durante el desarrollo:

*   **Servidor de Desarrollo Vue.js:**
    *   Típicamente se ejecuta usando Vue CLI: `npm run serve` (o `yarn serve`).
    *   Se ejecuta en un puerto específico, por ejemplo, `http://localhost:8080` o `http://localhost:5173` (para proyectos Vue basados en Vite).
    *   Proporciona características como recarga de módulos en caliente (los cambios en el código Vue se reflejan en el navegador instantáneamente sin una recarga completa de la página), mensajes de error detallados y una compilación optimizada para desarrollo.

*   **Servidor de API PHP de Desarrollo:**
    *   Necesitas un servidor separado para ejecutar tus scripts PHP. Las opciones incluyen:
        *   **XAMPP, MAMP, WAMP:** Estos paquetes incluyen Apache (o Nginx), PHP y MySQL, proporcionando un entorno de servidor web completo. Tus archivos de API PHP se colocarían en la raíz web del servidor (por ejemplo, `htdocs` para XAMPP).
        *   **Servidor Web Incorporado de PHP:** Para APIs más simples o pruebas rápidas, puedes usar el servidor incorporado de PHP. Navega al directorio raíz de tu API PHP en la terminal y ejecuta:
            ```bash
            php -S localhost:8000
            ```
            Esto servirá archivos PHP desde ese directorio en `http://localhost:8000`.
            Si tus rutas API están bajo un prefijo `/api`, podrías estructurar tus archivos PHP en consecuencia o usar un script de enrutamiento.

*   **Necesidad de CORS:**
    Dado que el servidor de desarrollo Vue.js (por ejemplo, `localhost:8080`) y el servidor de API PHP (por ejemplo, `localhost:8000`) se ejecutan en **orígenes** diferentes (puertos diferentes), las políticas de seguridad del navegador (Política del Mismo Origen) impedirán las llamadas API directas. Necesitarás configurar **CORS (Cross-Origin Resource Sharing)** en tu backend PHP para permitir solicitudes desde el origen de tu servidor de desarrollo Vue.js. (Más sobre esto en la sección 5).

#### Servidor Único en Producción

En un entorno de producción, típicamente compilas tu aplicación Vue.js en activos estáticos y sirves tanto estos activos como tu API PHP desde el mismo servidor web (como Apache o Nginx).

1.  **Compilar Aplicación Vue.js:**
    *   Ejecuta el comando de compilación en el directorio de tu proyecto Vue.js: `npm run build` (o `yarn build`).
    *   Esto crea una carpeta `dist` (o similar) que contiene archivos estáticos optimizados (HTML, CSS, paquetes JavaScript).

2.  **Configurar Servidor Web:**
    *   **Servir Activos Estáticos:** Configura tu servidor web (Apache/Nginx) para servir el contenido de la carpeta `dist` de Vue.js como el frontend de tu aplicación.
    *   **Manejar Solicitudes API:** Configura el servidor para enrutar patrones de URL específicos (por ejemplo, aquellos que comienzan con `/api/`) a tus scripts/enrutador PHP.

    **Ejemplo con Apache:**
    Podrías colocar el contenido de la carpeta `dist` de Vue en la raíz web de tu Apache (por ejemplo, `/var/www/html`). Tus archivos de API PHP podrían estar en un subdirectorio como `/var/www/html/api`.
    Tu configuración de Apache (o `.htaccess` en la raíz) típicamente incluiría:
    *   Una regla para dirigir todas las solicitudes que no sean de archivos/directorios al `index.html` de Vue (para enrutamiento del lado del cliente).
    *   Reglas para asegurar que las solicitudes a `/api/...` sean manejadas por scripts PHP en el directorio `api/`.

    En esta configuración, si tanto el frontend como el backend se sirven desde el mismo dominio y puerto (por ejemplo, `https://tudominio.com` sirve los activos de Vue, y las llamadas API van a `https://tudominio.com/api/...`), CORS generalmente no es un problema porque son del mismo origen.

### Estructura de Directorios (Ejemplo)

Una forma común de estructurar un proyecto con un frontend Vue.js y un backend PHP:

```
raiz_del_proyecto/
├── frontend_vue/          # Tu proyecto Vue CLI (ej., creado con `vue create frontend_vue`)
│   ├── public/            # Activos estáticos para Vue (index.html, favicon)
│   ├── src/               # Código fuente de la aplicación Vue (componentes, main.js, App.vue)
│   │   ├── assets/
│   │   ├── components/
│   │   ├── router/        # Si usas Vue Router
│   │   ├── store/         # Si usas Vuex/Pinia
│   │   ├── views/
│   │   ├── App.vue
│   │   └── main.js
│   ├── .env               # Variables de entorno para Vue (ej., URL base de la API)
│   ├── package.json
│   └── vue.config.js      # Configuración de Vue CLI (ej., para proxy del servidor de desarrollo)
│
├── backend_php/           # Tus archivos de API PHP
│   ├── api/               # Scripts de endpoint de API accesibles públicamente
│   │   ├── usuarios.php
│   │   ├── productos.php
│   │   └── .htaccess      # (Opcional) Para reescritura de URL de Apache dentro de /api
│   ├── includes/          # Archivos PHP no públicos (conexión BD, utilidades, modelos)
│   │   ├── db_connect.php
│   │   └── functions.php
│   ├── vendor/            # Dependencias de Composer para PHP (si las hay)
│   ├── composer.json      # (Si usas Composer)
│   └── config.php         # Configuración general de PHP
│
└── README.md
```

**Durante el Desarrollo (Servidores Separados):**
*   `cd frontend_vue && npm run serve` (inicia el servidor de desarrollo de Vue, por ejemplo, en `localhost:8080`)
*   `cd backend_php && php -S localhost:8000 -t api` (inicia el servidor de desarrollo de PHP para el directorio `api`, por ejemplo, en `localhost:8000`)
    (El `-t api` hace que `localhost:8000/usuarios.php` sea accesible si `usuarios.php` está en `backend_php/api/`)

## 3. Realizando Llamadas API desde Vue.js a PHP

### Usando Axios (Recomendado)

Axios es un cliente HTTP popular basado en promesas para el navegador y Node.js. Simplifica la realización de solicitudes HTTP.

1.  **Instalar Axios en tu proyecto Vue:**
    Abre tu terminal en el directorio `frontend_vue`:
    ```bash
    npm install axios
    # o
    yarn add axios
    ```

2.  **Importar Axios en Componentes Vue:**
    En la sección `<script>` de tu componente Vue:
    ```javascript
    import axios from 'axios';
    ```

3.  **Realizando Solicitudes `GET`:**
    Para obtener datos de un endpoint PHP.
    ```javascript
    // En un método de componente Vue o hook de ciclo de vida (ej., mounted())
    // Asume que la API PHP está en http://localhost:8000/api/
    axios.get('http://localhost:8000/api/items.php') // Reemplaza con la URL de tu API
      .then(response => {
        // Manejar respuesta exitosa
        console.log('Datos obtenidos:', response.data);
        this.items = response.data; // Asumiendo que 'items' es una propiedad de datos en tu componente
      })
      .catch(error => {
        // Manejar error
        console.error('Error al obtener ítems:', error);
        if (error.response) {
          // La solicitud se realizó y el servidor respondió con un código de estado
          // que cae fuera del rango de 2xx
          console.error('Datos del error:', error.response.data);
          console.error('Estado del error:', error.response.status);
        } else if (error.request) {
          // La solicitud se realizó pero no se recibió respuesta
          console.error('No se recibió respuesta:', error.request);
        } else {
          // Algo sucedió al configurar la solicitud que provocó un Error
          console.error('Mensaje de error:', error.message);
        }
      });
    ```

4.  **Realizando Solicitudes `POST`:**
    Para enviar datos para crear un nuevo recurso.
    ```javascript
    // En un método de componente Vue
    const nuevoItem = {
      nombre: 'Nuevo Gadget',
      precio: 29.99
    };
    axios.post('http://localhost:8000/api/items.php', nuevoItem) // nuevoItem se enviará como cuerpo JSON
      .then(response => {
        console.log('Ítem creado:', response.data);
        // Opcionalmente, refrescar lista o añadir nuevo ítem a los datos locales
      })
      .catch(error => {
        console.error('Error al crear ítem:', error);
      });
    ```
    Axios serializa automáticamente los objetos JavaScript a JSON para el cuerpo de la solicitud y establece la cabecera `Content-Type: application/json`.

5.  **Manejando Respuestas:**
    *   El bloque `.then(response => { ... })` se ejecuta cuando la solicitud es exitosa (típicamente estado HTTP 2xx).
    *   `response.data`: Contiene los datos devueltos por el servidor (Axios analiza automáticamente las respuestas JSON).
    *   `response.status`: El código de estado HTTP.
    *   `response.headers`: Las cabeceras HTTP.

6.  **Manejando Errores:**
    *   El bloque `.catch(error => { ... })` se ejecuta si la solicitud falla o el servidor devuelve un código de estado de error.
    *   El objeto `error` proporciona detalles sobre el fallo.

7.  **Usando `async/await` con Axios:**
    Hace que el código asíncrono parezca más síncrono y a menudo más fácil de leer.
    ```javascript
    // En un método de componente Vue asíncrono o hook de ciclo de vida
    async obtenerItems() {
      try {
        const response = await axios.get('http://localhost:8000/api/items.php');
        this.items = response.data;
        console.log('Ítems obtenidos con async/await:', this.items);
      } catch (error) {
        console.error('Error al obtener ítems con async/await:', error);
        // Manejar propiedades de error específicas como se muestra en el ejemplo .catch
      }
    }
    ```

### Usando Fetch API (Incorporada)

La API Fetch es una API moderna incorporada en el navegador para realizar solicitudes HTTP. También está basada en promesas.

*   **Solicitud `GET` Básica:**
    ```javascript
    fetch('http://localhost:8000/api/items.php')
      .then(response => {
        if (!response.ok) { // Comprueba si el estado HTTP es 200-299
          throw new Error('La respuesta de red no fue ok: ' + response.statusText);
        }
        return response.json(); // Analiza el cuerpo de la respuesta JSON
      })
      .then(data => {
        console.log('Datos obtenidos con Fetch:', data);
        this.items = data;
      })
      .catch(error => {
        console.error('Error al obtener ítems con Fetch:', error);
      });
    ```

*   **Solicitud `POST` Básica:**
    ```javascript
    const nuevoItem = { nombre: 'Otro Ítem', precio: 15.00 };
    fetch('http://localhost:8000/api/items.php', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json', // Importante: especificar tipo de contenido
      },
      body: JSON.stringify(nuevoItem), // Convertir manualmente el objeto JS a JSON
    })
    .then(response => {
      if (!response.ok) {
        // Intentar obtener mensaje de error del cuerpo si el servidor envía error JSON
        return response.json().then(errData => {
            throw new Error(errData.error || 'Falló la solicitud POST: ' + response.statusText);
        }).catch(() => { // Si el cuerpo del error no era JSON
            throw new Error('Falló la solicitud POST: ' + response.statusText);
        });
      }
      return response.json();
    })
    .then(data => {
      console.log('Ítem creado con Fetch:', data);
    })
    .catch(error => {
      console.error('Error al crear ítem con Fetch:', error);
    });
    ```

*   **Diferencias en el Manejo de Errores con Fetch:**
    *   Fetch solo considera un error de red (por ejemplo, la solicitud no pudo realizarse) como un rechazo de promesa. Los estados de error HTTP (como 404 o 500) **no** se tratan automáticamente como rechazos. Necesitas comprobar `response.ok` o `response.status` manualmente.
    *   Axios tiene más comodidades incorporadas para la transformación de solicitudes/respuestas y el manejo de errores.

### Configurando una URL Base

Es una buena práctica definir una URL base para tus llamadas API, especialmente si se usa en múltiples lugares.

*   **En `main.js` para configuración global de Axios:**
    ```javascript
    // frontend_vue/src/main.js
    import { createApp } from 'vue';
    import App from './App.vue';
    import axios from 'axios';

    // Establecer una URL base global para Axios
    axios.defaults.baseURL = 'http://localhost:8000/api'; // URL base de tu API PHP

    // También puedes establecer cabeceras globales aquí si es necesario
    // axios.defaults.headers.common['Authorization'] = 'TOKEN_AUTENTICACION';

    const app = createApp(App);
    // Si quieres que axios esté disponible globalmente en componentes vía this.$axios (menos común ahora con Composition API)
    // app.config.globalProperties.$axios = axios; 
    app.mount('#app');
    ```
    Luego en los componentes, puedes hacer solicitudes como: `axios.get('/items.php')`.

*   **Creando una Instancia de Axios:** Para escenarios más complejos o múltiples endpoints de API.
    ```javascript
    // frontend_vue/src/services/api.js (archivo de ejemplo)
    import axios from 'axios';

    const apiClient = axios.create({
      baseURL: 'http://localhost:8000/api',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    });

    // Puedes añadir interceptores aquí para el manejo de solicitudes/respuestas (ej., añadir tokens de autenticación)
    // apiClient.interceptors.request.use(config => { ... });

    export default apiClient;
    ```
    Luego en los componentes: `import apiClient from '@/services/api'; apiClient.get('/items.php');`

*   **Variables de Entorno para Vue:**
    Para diferentes URLs base en desarrollo vs. producción, usa variables de entorno en tu proyecto Vue (por ejemplo, archivos `.env.development` y `.env.production`).
    En `.env.development`: `VUE_APP_API_BASE_URL=http://localhost:8000/api`
    En `.env.production`: `VUE_APP_API_BASE_URL=https://tudominio.com/api`
    Luego accede a ella en tu código: `axios.defaults.baseURL = process.env.VUE_APP_API_BASE_URL;`

## 4. Intercambio de Datos - Vue.js y PHP

### Enviando Datos de Vue a PHP

*   **Solicitudes `GET` (Parámetros de Consulta):**
    Los datos se envían como parte de la URL.
    **Vue.js (Axios):**
    ```javascript
    axios.get('/items.php', {
      params: {
        id: 1,
        categoria: 'electronica'
      }
    })
    .then(response => { /* ... */ });
    // La URL de la solicitud será: /items.php?id=1&categoria=electronica
    ```
    **PHP (`/api/items.php`):**
    ```php
    <?php
    // GET /api/items.php?id=1&categoria=electronica
    $itemId = isset($_GET['id']) ? (int)$_GET['id'] : null;
    $categoria = isset($_GET['categoria']) ? filter_var($_GET['categoria'], FILTER_SANITIZE_STRING) : null;

    // ... lógica para obtener ítem basada en $itemId y $categoria ...
    // header('Content-Type: application/json');
    // echo json_encode(['mensaje' => "Obteniendo ítem $itemId en categoría $categoria"]);
    ?>
    ```

*   **Solicitudes `POST`/`PUT`/`PATCH` (Cuerpo de Solicitud como JSON):**
    Los datos se envían en el cuerpo de la solicitud HTTP, típicamente como una cadena JSON.
    **Vue.js (Axios):**
    ```javascript
    const datosUsuario = { nombre_usuario: 'usuario_vue', email: 'vue@example.com' };
    axios.post('/usuarios.php', datosUsuario) // Axios automáticamente convierte a cadena y establece Content-Type
      .then(response => { /* ... */ });
    ```
    **PHP (`/api/usuarios.php`):**
    ```php
    <?php
    // POST /api/usuarios.php
    // Asegurar que Content-Type sea application/json
    if ($_SERVER['REQUEST_METHOD'] === 'POST' && 
        isset($_SERVER['CONTENT_TYPE']) && 
        strpos(strtolower($_SERVER['CONTENT_TYPE']), 'application/json') !== false) {

        $jsonPayload = file_get_contents('php://input');
        $data = json_decode($jsonPayload, true); // true para array asociativo

        if (json_last_error() === JSON_ERROR_NONE && isset($data['nombre_usuario']) && isset($data['email'])) {
            $nombreUsuario = filter_var($data['nombre_usuario'], FILTER_SANITIZE_STRING);
            $email = filter_var($data['email'], FILTER_VALIDATE_EMAIL);
            
            if (!$email) {
                // http_response_code(400);
                // echo json_encode(['error' => 'Formato de email inválido.']);
                // exit;
            }
            // Procesar $nombreUsuario y $email (ej., guardar en base de datos)
            // header('Content-Type: application/json');
            // http_response_code(201); // Creado
            // echo json_encode(['mensaje' => 'Usuario creado', 'nombre_usuario' => $nombreUsuario, 'email' => $email]);
        } else {
            // http_response_code(400); // Solicitud Incorrecta
            // echo json_encode(['error' => 'JSON inválido o datos faltantes.']);
        }
    } else {
        // http_response_code(405); // Método No Permitido o 415 Tipo de Medio No Soportado
        // echo json_encode(['error' => 'Método de solicitud o tipo de contenido inválido.']);
    }
    ?>
    ```

### Recibiendo y Mostrando Datos en Vue

1.  **Almacenar Respuesta API en `data()` del Componente (Options API) o `ref`/`reactive` (Composition API):**
    ```javascript
    // Ejemplo Options API
    // <script>
    // export default {
    //   data() {
    //     return {
    //       usuarios: [],
    //       cargando: false,
    //       error: null
    //     };
    //   },
    //   mounted() {
    //     this.obtenerUsuarios();
    //   },
    //   methods: {
    //     async obtenerUsuarios() {
    //       this.cargando = true;
    //       this.error = null;
    //       try {
    //         const response = await axios.get('http://localhost:8000/api/usuarios.php');
    //         this.usuarios = response.data;
    //       } catch (err) {
    //         this.error = 'Falló al cargar usuarios.';
    //         console.error(err);
    //       } finally {
    //         this.cargando = false;
    //       }
    //     }
    //   }
    // };
    // </script>

    // Ejemplo Composition API
    // <script setup>
    import { ref, onMounted } from 'vue';
    import axios from 'axios'; // O tu apiClient configurado

    const usuarios = ref([]);
    const cargando = ref(false);
    const error = ref(null);

    const obtenerUsuarios = async () => {
      cargando.value = true;
      error.value = null;
      try {
        const response = await axios.get('http://localhost:8000/api/usuarios.php');
        usuarios.value = response.data;
      } catch (err) {
        error.value = 'Falló al cargar usuarios.';
        console.error(err);
      } finally {
        cargando.value = false;
      }
    };

    onMounted(obtenerUsuarios); // Obtener usuarios cuando el componente se monta
    // </script>
    ```

2.  **Vincular Datos a la Plantilla:**
    Usa la sintaxis de plantilla de Vue para mostrar los datos.
    ```html
    <!-- En la <template> de tu componente Vue -->
    <div class="lista-usuarios">
      <h2>Lista de Usuarios</h2>
      <div v-if="cargando">Cargando usuarios...</div>
      <div v-if="error" class="mensaje-error">{{ error }}</div>
      <ul v-if="!cargando && !error && usuarios.length > 0">
        <li v-for="usuario in usuarios" :key="usuario.id">
          {{ usuario.nombre }} ({{ usuario.email }})
        </li>
      </ul>
      <div v-if="!cargando && !error && usuarios.length === 0">
        No se encontraron usuarios.
      </div>
      <button @click="obtenerUsuarios" :disabled="cargando">Refrescar Usuarios</button>
    </div>
    ```
    *   `v-if`, `v-else-if`, `v-else`: Renderizado condicional.
    *   `v-for`: Iterar sobre el array `usuarios`.
    *   `:key`: Esencial para `v-for` para ayudar a Vue a rastrear eficientemente los ítems de la lista.
    *   `{{ usuario.nombre }}`: Vinculación de datos (interpolación).
    *   `@click="obtenerUsuarios"`: Manejo de eventos.
    *   `:disabled="cargando"`: Vinculación a atributos.

3.  **Actualizaciones Reactivas:** Cuando las propiedades de datos `usuarios`, `cargando` o `error` en tu componente Vue cambian (por ejemplo, después de que se completa una llamada API), el sistema de reactividad de Vue actualiza automáticamente las partes del DOM que dependen de estas propiedades.

## 5. Manejando CORS (Cross-Origin Resource Sharing) - Resumen y Contexto

Como se mencionó en la sección "PHP para APIs Web" y configuración del proyecto:

*   **Por qué es necesario:** Durante el desarrollo, tu servidor de desarrollo Vue.js (por ejemplo, `http://localhost:8080`) y el servidor de API PHP (por ejemplo, `http://localhost:8000`) están en orígenes diferentes. Los navegadores bloquean que JavaScript realice solicitudes a un origen diferente a menos que el servidor lo permita explícitamente mediante cabeceras CORS.

*   **Cabeceras PHP para CORS:**
    Coloca estas al principio de tus scripts de API PHP (por ejemplo, en un archivo de arranque central o en la parte superior de cada script de endpoint).

    ```php
    <?php
    // Permitir solicitudes desde el origen de tu servidor de desarrollo Vue.js
    // Sé más específico en producción si es posible
    header("Access-Control-Allow-Origin: http://localhost:8080"); 

    // Métodos HTTP permitidos para la solicitud real
    header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, PATCH, OPTIONS");

    // Cabeceras permitidas en la solicitud del cliente
    // Cabeceras comunes incluyen Content-Type (para JSON POST/PUT), Authorization (para tokens)
    header("Access-Control-Allow-Headers: Content-Type, Authorization, X-Requested-With, Origin, Accept");

    // Opcional: Si tu frontend necesita enviar cookies o usar autenticación HTTP
    // header("Access-Control-Allow-Credentials: true");

    // Opcional: Edad máxima para la caché de la solicitud de pre-vuelo (en segundos)
    // header("Access-Control-Max-Age: 86400"); // 1 día

    // Manejar solicitud OPTIONS de pre-vuelo (enviada por el navegador para solicitudes "no simples")
    if ($_SERVER['REQUEST_METHOD'] == 'OPTIONS') {
        // No es necesario ejecutar el resto del script para OPTIONS
        // Solo envía las cabeceras y una respuesta 200 OK o 204 No Content
        http_response_code(204); // O 200
        exit();
    }

    // ... resto de tu lógica API para GET, POST, PUT, DELETE etc.
    // Ejemplo: header("Content-Type: application/json"); // Para tu respuesta API real
    ?>
    ```
    **Nota:** Para `Access-Control-Allow-Origin`, usa `*` solo para APIs completamente públicas o pruebas muy tempranas. Es más seguro especificar el/los origen(es) exacto(s) que tienen permitido conectarse.

*   **Entorno de Producción:** Si tus archivos estáticos Vue.js y la API PHP se sirven desde el **mismo dominio y puerto** en producción, las cabeceras CORS generalmente no son necesarias porque las solicitudes son del mismo origen.

## 6. Ejemplo: Aplicación Simple de Visualización de Datos

### Backend PHP (`backend_php/api/usuarios.php`)

```php
<?php
// backend_php/api/usuarios.php

// --- Cabeceras CORS ---
header("Access-Control-Allow-Origin: http://localhost:8080"); // Tu servidor de desarrollo Vue
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

if ($_SERVER['REQUEST_METHOD'] == 'OPTIONS') {
    http_response_code(200);
    exit();
}

// Datos de usuario hardcodeados para el ejemplo
$usuarios = [
    ['id' => 1, 'nombre' => 'Alicia Johnson', 'email' => 'alicia.j@example.com', 'rol' => 'Admin'],
    ['id' => 2, 'nombre' => 'Roberto Williams', 'email' => 'roberto.w@example.com', 'rol' => 'Editor'],
    ['id' => 3, 'nombre' => 'Carolina Davis', 'email' => 'carolina.d@example.com', 'rol' => 'Espectador'],
    ['id' => 4, 'nombre' => 'David Brown', 'email' => 'david.b@example.com', 'rol' => 'Editor']
];

// Simular un retraso (opcional, para probar estados de carga)
// sleep(1); 

if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    echo json_encode($usuarios);
} else {
    http_response_code(405); // Método No Permitido
    echo json_encode(['error' => 'Solo se admite el método GET para este endpoint.']);
}
?>
```

### Frontend Vue.js (`frontend_vue/src/components/ListaUsuarios.vue`)

```html
<template>
  <div class="contenedor-lista-usuarios">
    <h2>Nuestros Usuarios</h2>
    <button @click="obtenerUsuarios" :disabled="cargando" class="boton-refrescar">
      {{ cargando ? 'Cargando...' : 'Refrescar Usuarios' }}
    </button>

    <div v-if="cargando" class="indicador-carga">Obteniendo datos de usuario...</div>
    
    <div v-if="errorApi" class="mensaje-error">
      <p>Error al obtener usuarios: {{ errorApi }}</p>
      <p>Por favor, asegúrate de que el servidor API PHP se esté ejecutando en <code>http://localhost:8000/api/usuarios.php</code> y que CORS esté configurado.</p>
    </div>

    <table v-if="!cargando && !errorApi && usuarios.length > 0" class="tabla-usuarios">
      <thead>
        <tr>
          <th>ID</th>
          <th>Nombre</th>
          <th>Email</th>
          <th>Rol</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="usuario in usuarios" :key="usuario.id">
          <td>{{ usuario.id }}</td>
          <td>{{ usuario.nombre }}</td>
          <td>{{ usuario.email }}</td>
          <td>{{ usuario.rol }}</td>
        </tr>
      </tbody>
    </table>
    <div v-if="!cargando && !errorApi && usuarios.length === 0" class="no-usuarios">
      No se encontraron usuarios.
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios'; // O tu apiClient configurado

const usuarios = ref([]);
const cargando = ref(false);
const errorApi = ref(null);

// Definir la URL del endpoint de la API
const API_USUARIOS_URL = 'http://localhost:8000/api/usuarios.php'; // URL de tu API PHP

const obtenerUsuarios = async () => {
  cargando.value = true;
  errorApi.value = null;
  usuarios.value = []; // Limpiar usuarios anteriores

  try {
    const response = await axios.get(API_USUARIOS_URL);
    if (response.data && Array.isArray(response.data)) {
      usuarios.value = response.data;
    } else {
      // Manejar casos donde response.data no es lo esperado
      console.warn('Se recibió un formato de datos inesperado:', response.data);
      errorApi.value = 'Se recibió un formato de datos inesperado del servidor.';
    }
  } catch (error) {
    console.error('Error al obtener usuarios:', error);
    if (error.response) {
      errorApi.value = `Error del Servidor: ${error.response.status} - ${error.response.data.error || error.response.statusText}`;
    } else if (error.request) {
      errorApi.value = 'Sin respuesta del servidor. ¿Está la API en ejecución?';
    } else {
      errorApi.value = `Error de Solicitud: ${error.message}`;
    }
  } finally {
    cargando.value = false;
  }
};

// Obtener usuarios cuando el componente se monta por primera vez
onMounted(obtenerUsuarios);
</script>

<style scoped>
.contenedor-lista-usuarios {
  font-family: sans-serif;
  padding: 20px;
  max-width: 800px;
  margin: 20px auto;
  box-shadow: 0 0 10px rgba(0,0,0,0.1);
}
.boton-refrescar {
  margin-bottom: 15px;
  padding: 8px 15px;
  cursor: pointer;
}
.indicador-carga, .no-usuarios {
  padding: 10px;
  color: #555;
}
.mensaje-error {
  color: red;
  background-color: #ffe0e0;
  border: 1px solid red;
  padding: 10px;
  margin-bottom: 15px;
}
.tabla-usuarios {
  width: 100%;
  border-collapse: collapse;
}
.tabla-usuarios th, .tabla-usuarios td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}
.tabla-usuarios th {
  background-color: #f2f2f2;
}
</style>
```
**Para usar este componente:**
1.  Guárdalo como `ListaUsuarios.vue` en tu directorio `frontend_vue/src/components/`.
2.  Impórtalo y úsalo en `App.vue` u otra vista:
    ```html
    <!-- frontend_vue/src/App.vue -->
    <template>
      <div id="app">
        <ListaUsuarios />
      </div>
    </template>

    <script setup>
    import ListaUsuarios from './components/ListaUsuarios.vue';
    </script>
    ```

## 7. Ejemplo: Enviando Datos de Vue a PHP

### Frontend Vue.js (`frontend_vue/src/components/FormularioAgregarUsuario.vue`)

```html
<template>
  <div class="contenedor-agregar-usuario">
    <h2>Agregar Nuevo Usuario</h2>
    <form @submit.prevent="manejarEnvioFormulario" class="formulario-usuario">
      <div class="grupo-formulario">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" v-model="nuevoUsuario.nombre" required>
      </div>
      <div class="grupo-formulario">
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="nuevoUsuario.email" required>
      </div>
      <div class="grupo-formulario">
        <label for="rol">Rol:</label>
        <select id="rol" v-model="nuevoUsuario.rol">
          <option value="Espectador">Espectador</option>
          <option value="Editor">Editor</option>
          <option value="Admin">Admin</option>
        </select>
      </div>
      <button type="submit" :disabled="enviando" class="boton-enviar">
        {{ enviando ? 'Agregando...' : 'Agregar Usuario' }}
      </button>
    </form>

    <div v-if="mensajeEnvio" 
         :class="['mensaje-envio', estadoEnvio === 'exito' ? 'exito' : 'error']">
      {{ mensajeEnvio }}
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios'; // O tu apiClient configurado

const nuevoUsuario = ref({
  nombre: '',
  email: '',
  rol: 'Espectador' // Rol predeterminado
});

const enviando = ref(false);
const mensajeEnvio = ref('');
const estadoEnvio = ref(''); // 'exito' o 'error'

const API_AGREGAR_USUARIO_URL = 'http://localhost:8000/api/agregarUsuario.php'; // URL de tu API PHP

const manejarEnvioFormulario = async () => {
  enviando.value = true;
  mensajeEnvio.value = '';
  estadoEnvio.value = '';

  try {
    // Validación básica del lado del cliente (puede ser más extensa)
    if (!nuevoUsuario.value.nombre.trim() || !nuevoUsuario.value.email.trim()) {
        mensajeEnvio.value = 'Nombre y Email son requeridos.';
        estadoEnvio.value = 'error';
        enviando.value = false;
        return;
    }

    const response = await axios.post(API_AGREGAR_USUARIO_URL, nuevoUsuario.value);
    
    mensajeEnvio.value = response.data.mensaje || '¡Usuario agregado exitosamente!';
    estadoEnvio.value = 'exito';
    
    // Opcionalmente, resetear formulario o emitir un evento para refrescar una lista de usuarios
    nuevoUsuario.value = { nombre: '', email: '', rol: 'Espectador' }; 

  } catch (error) {
    console.error('Error al agregar usuario:', error);
    if (error.response && error.response.data && error.response.data.error) {
      mensajeEnvio.value = `Error: ${error.response.data.error}`;
    } else if (error.request) {
      mensajeEnvio.value = 'Error: Sin respuesta del servidor. ¿Está la API en ejecución?';
    } else {
      mensajeEnvio.value = `Error: ${error.message}`;
    }
    estadoEnvio.value = 'error';
  } finally {
    enviando.value = false;
  }
};
</script>

<style scoped>
.contenedor-agregar-usuario {
  font-family: sans-serif;
  padding: 20px;
  max-width: 500px;
  margin: 20px auto;
  box-shadow: 0 0 10px rgba(0,0,0,0.1);
}
.formulario-usuario .grupo-formulario {
  margin-bottom: 15px;
}
.formulario-usuario label {
  display: block;
  margin-bottom: 5px;
}
.formulario-usuario input[type="text"],
.formulario-usuario input[type="email"],
.formulario-usuario select {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
}
.boton-enviar {
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
.boton-enviar:disabled {
  background-color: #aaa;
}
.mensaje-envio {
  margin-top: 15px;
  padding: 10px;
  border-radius: 4px;
}
.mensaje-envio.exito {
  color: #155724;
  background-color: #d4edda;
  border: 1px solid #c3e6cb;
}
.mensaje-envio.error {
  color: #721c24;
  background-color: #f8d7da;
  border: 1px solid #f5c6cb;
}
</style>
```

### Backend PHP (`backend_php/api/agregarUsuario.php`)

```php
<?php
// backend_php/api/agregarUsuario.php

// --- Cabeceras CORS ---
header("Access-Control-Allow-Origin: http://localhost:8080"); // Tu servidor de desarrollo Vue
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

if ($_SERVER['REQUEST_METHOD'] == 'OPTIONS') {
    http_response_code(200);
    exit();
}

// --- Manejar Solicitud POST ---
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Comprobar si el tipo de contenido es application/json
    if (isset($_SERVER['CONTENT_TYPE']) && strpos(strtolower($_SERVER['CONTENT_TYPE']), 'application/json') !== false) {
        $jsonPayload = file_get_contents('php://input');
        $data = json_decode($jsonPayload, true); // true para array asociativo

        // Validación Básica
        if (json_last_error() === JSON_ERROR_NONE && 
            isset($data['nombre']) && !empty(trim($data['nombre'])) &&
            isset($data['email']) && filter_var($data['email'], FILTER_VALIDATE_EMAIL) &&
            isset($data['rol'])) {

            $nombre = filter_var(trim($data['nombre']), FILTER_SANITIZE_STRING);
            $email = $data['email']; // Ya validado
            $rol = filter_var($data['rol'], FILTER_SANITIZE_STRING);

            // En una aplicación real, guardarías esto en una base de datos.
            // Para este ejemplo, solo simularemos éxito y devolveremos los datos.
            // También podrías añadir a un array de sesión si continúas la lógica del ejemplo de la API de Tareas.
            session_start(); // Si usas sesión como en el ejemplo de tareas.php
            // Por ejemplo:
            // if (!isset($_SESSION['todos_los_usuarios'])) $_SESSION['todos_los_usuarios'] = [];
            // $nuevoIdUsuario = count($_SESSION['todos_los_usuarios']) + 1;
            // $_SESSION['todos_los_usuarios'][] = ['id' => $nuevoIdUsuario, 'nombre' => $nombre, 'email' => $email, 'rol' => $rol];

            http_response_code(201); // Creado
            echo json_encode([
                'mensaje' => '¡Usuario agregado exitosamente!',
                'usuario' => [
                    // 'id' => $nuevoIdUsuario, // Si generas un ID
                    'nombre' => $nombre,
                    'email' => $email,
                    'rol' => $rol
                ]
            ]);

        } else {
            http_response_code(400); // Solicitud Incorrecta
            $errores = [];
            if (json_last_error() !== JSON_ERROR_NONE) $errores[] = "Carga útil JSON inválida.";
            if (empty(trim($data['nombre'] ?? ''))) $errores[] = "El nombre es requerido.";
            if (!filter_var($data['email'] ?? '', FILTER_VALIDATE_EMAIL)) $errores[] = "Se requiere un email válido.";
            if (empty($data['rol'] ?? '')) $errores[] = "El rol es requerido.";
            echo json_encode(['error' => 'Entrada inválida.', 'detalles' => $errores]);
        }
    } else {
        http_response_code(415); // Tipo de Medio No Soportado
        echo json_encode(['error' => 'Content-Type inválido. Por favor, envía application/json.']);
    }
} else {
    http_response_code(405); // Método No Permitido
    echo json_encode(['error' => 'Solo se admite el método POST para este endpoint.']);
}
?>
```

Esta guía detallada debería proporcionar un punto de partida sólido para integrar backends PHP con frontends Vue.js, cubriendo los conceptos centrales y ejemplos prácticos para escenarios comunes. Recuerda que para aplicaciones de producción, serían necesarias medidas de manejo de errores más robustas, medidas de seguridad y, potencialmente, un enrutamiento más sofisticado en el lado de PHP.
[end of php_tutorial/08_php_and_vuejs_es.md]
