# Integrating PHP with Vue.js

Vue.js is a popular progressive JavaScript framework for building user interfaces. While Vue.js excels at frontend development, it often needs a backend to handle data persistence, business logic, and other server-side tasks. PHP is an excellent choice for building this backend, especially when creating APIs that Vue.js can consume.

## 1. Introduction to PHP as a Backend for Vue.js

### Recap

*   **Vue.js:** A JavaScript framework that runs in the user's web browser. It's responsible for rendering the user interface, handling user interactions on the frontend, and managing the application's state on the client-side.
*   **PHP:** A server-side scripting language. It runs on your web server, not in the user's browser. PHP can access databases, perform complex calculations, manage files, and implement business logic.

### How They Interact

The primary way Vue.js (frontend) and PHP (backend) interact is through **HTTP requests**, commonly known as API calls.

1.  **Vue.js Makes a Request:** The Vue.js application, running in the user's browser, makes an HTTP request to a specific URL on your server. This URL points to a PHP script (an API endpoint).
    *   This request can be to fetch data (e.g., a list of products), submit new data (e.g., a new user registration), update existing data, or delete data.
    *   Libraries like Axios or the built-in Fetch API are commonly used in Vue.js to make these requests.

2.  **PHP Processes the Request:**
    *   The PHP script on the server receives the HTTP request.
    *   It processes the request, which might involve:
        *   Validating input data.
        *   Interacting with a database (querying, inserting, updating, deleting).
        *   Executing business logic.
        *   Authenticating the user.

3.  **PHP Sends a Response:**
    *   After processing, the PHP script sends an HTTP response back to the Vue.js application.
    *   This response typically contains:
        *   **Data:** Usually formatted as **JSON** (JavaScript Object Notation), which is easy for JavaScript to parse and use.
        *   **HTTP Status Code:** Indicates the outcome of the request (e.g., `200 OK`, `201 Created`, `404 Not Found`, `500 Internal Server Error`).

4.  **Vue.js Handles the Response:**
    *   The Vue.js application receives the response.
    *   It parses the JSON data (if any) and updates the component's data properties.
    *   Vue's reactivity system then automatically updates the user interface to reflect the new data.
    *   It can also handle errors based on the HTTP status code or error messages in the response.

### Benefits of This Separation

This client-server architecture, where Vue.js handles the frontend and PHP handles the backend, offers several advantages:

*   **Single Page Applications (SPAs):** Vue.js can create SPAs that feel fast and responsive because only data is exchanged with the server after the initial page load, not entire HTML pages. The UI is updated dynamically in the browser.
*   **Better User Experience:** SPAs often provide a smoother, more app-like experience compared to traditional multi-page applications.
*   **Modularity and Separation of Concerns:**
    *   Frontend and backend development can happen independently.
    *   Different teams can work on the Vue.js frontend and the PHP backend.
    *   The API becomes a clear contract between the frontend and backend.
*   **Reusability:** The PHP API backend can be consumed by other clients as well, not just the Vue.js web app (e.g., a mobile application, another web service).
*   **Scalability:** Frontend and backend can be scaled independently based on their specific needs.

## 2. Project Setup Considerations

### Serving Vue.js and PHP

How you serve your Vue.js frontend and PHP backend depends on whether you're in development or production.

#### Separate Development Servers (Common for Development)

This is a very common and convenient setup during development:

*   **Vue.js Development Server:**
    *   Typically run using the Vue CLI: `npm run serve` (or `yarn serve`).
    *   Runs on a specific port, e.g., `http://localhost:8080` or `http://localhost:5173` (for Vite-based Vue projects).
    *   Provides features like hot-module reloading (changes in Vue code are reflected in the browser instantly without a full page refresh), detailed error messages, and a development-optimized build.

*   **PHP API Development Server:**
    *   You need a separate server to run your PHP scripts. Options include:
        *   **XAMPP, MAMP, WAMP:** These packages bundle Apache (or Nginx), PHP, and MySQL, providing a full web server environment. Your PHP API files would be placed in the server's web root (e.g., `htdocs` for XAMPP).
        *   **PHP's Built-in Web Server:** For simpler APIs or quick testing, you can use PHP's built-in server. Navigate to your PHP API's root directory in the terminal and run:
            ```bash
            php -S localhost:8000
            ```
            This will serve PHP files from that directory on `http://localhost:8000`.
            If your API routes are under an `/api` prefix, you might structure your PHP files accordingly or use a router script.

*   **Need for CORS:**
    Since the Vue.js development server (e.g., `localhost:8080`) and the PHP API server (e.g., `localhost:8000`) are running on different **origins** (different ports), browser security policies (Same-Origin Policy) will prevent direct API calls. You'll need to configure **CORS (Cross-Origin Resource Sharing)** on your PHP backend to allow requests from your Vue.js development server's origin. (More on this in section 5).

#### Single Server in Production

In a production environment, you typically build your Vue.js application into static assets and serve both these assets and your PHP API from the same web server (like Apache or Nginx).

1.  **Build Vue.js App:**
    *   Run the build command in your Vue.js project directory: `npm run build` (or `yarn build`).
    *   This creates a `dist` folder (or similar) containing optimized static files (HTML, CSS, JavaScript bundles).

2.  **Configure Web Server:**
    *   **Serve Static Assets:** Configure your web server (Apache/Nginx) to serve the contents of the Vue.js `dist` folder as the frontend of your application.
    *   **Handle API Requests:** Configure the server to route specific URL patterns (e.g., those starting with `/api/`) to your PHP scripts/router.

    **Example with Apache:**
    You might place the contents of Vue's `dist` folder in your Apache web root (e.g., `/var/www/html`). Your PHP API files could be in a subdirectory like `/var/www/html/api`.
    Your Apache configuration (or `.htaccess` in the root) would typically include:
    *   A rule to direct all non-file/non-directory requests to Vue's `index.html` (for client-side routing).
    *   Rules to ensure requests to `/api/...` are handled by PHP scripts in the `api/` directory.

    In this setup, if both frontend and backend are served from the same domain and port (e.g., `https://yourdomain.com` serves Vue assets, and API calls go to `https://yourdomain.com/api/...`), CORS is generally not an issue because they are same-origin.

### Directory Structure (Example)

A common way to structure a project with a Vue.js frontend and a PHP backend:

```
project_root/
├── frontend_vue/          # Your Vue CLI project (e.g., created with `vue create frontend_vue`)
│   ├── public/            # Static assets for Vue (index.html, favicon)
│   ├── src/               # Vue application source code (components, main.js, App.vue)
│   │   ├── assets/
│   │   ├── components/
│   │   ├── router/        # If using Vue Router
│   │   ├── store/         # If using Vuex/Pinia
│   │   ├── views/
│   │   ├── App.vue
│   │   └── main.js
│   ├── .env               # Environment variables for Vue (e.g., API base URL)
│   ├── package.json
│   └── vue.config.js      # Vue CLI configuration (e.g., for dev server proxy)
│
├── backend_php/           # Your PHP API files
│   ├── api/               # Publicly accessible API endpoint scripts
│   │   ├── users.php
│   │   ├── products.php
│   │   └── .htaccess      # (Optional) For Apache URL rewriting within /api
│   ├── includes/          # Non-public PHP files (database connection, utilities, models)
│   │   ├── db_connect.php
│   │   └── functions.php
│   ├── vendor/            # Composer dependencies for PHP (if any)
│   ├── composer.json      # (If using Composer)
│   └── config.php         # General PHP configuration
│
└── README.md
```

**During Development (Separate Servers):**
*   `cd frontend_vue && npm run serve` (starts Vue dev server, e.g., on `localhost:8080`)
*   `cd backend_php && php -S localhost:8000 -t api` (starts PHP dev server for the `api` directory, e.g., on `localhost:8000`)
    (The `-t api` makes `localhost:8000/users.php` accessible if `users.php` is in `backend_php/api/`)

## 3. Making API Calls from Vue.js to PHP

### Using Axios (Recommended)

Axios is a popular, promise-based HTTP client for the browser and Node.js. It simplifies making HTTP requests.

1.  **Install Axios in your Vue project:**
    Open your terminal in the `frontend_vue` directory:
    ```bash
    npm install axios
    # or
    yarn add axios
    ```

2.  **Import Axios in Vue Components:**
    In the `<script>` section of your Vue component:
    ```javascript
    import axios from 'axios';
    ```

3.  **Making `GET` Requests:**
    To fetch data from a PHP endpoint.
    ```javascript
    // In a Vue component method or lifecycle hook (e.g., mounted())
    // Assume PHP API is at http://localhost:8000/api/
    axios.get('http://localhost:8000/api/items.php')
      .then(response => {
        // Handle successful response
        console.log('Data fetched:', response.data);
        this.items = response.data; // Assuming 'items' is a data property in your component
      })
      .catch(error => {
        // Handle error
        console.error('Error fetching items:', error);
        if (error.response) {
          // The request was made and the server responded with a status code
          // that falls out of the range of 2xx
          console.error('Error data:', error.response.data);
          console.error('Error status:', error.response.status);
        } else if (error.request) {
          // The request was made but no response was received
          console.error('No response received:', error.request);
        } else {
          // Something happened in setting up the request that triggered an Error
          console.error('Error message:', error.message);
        }
      });
    ```

4.  **Making `POST` Requests:**
    To send data to create a new resource.
    ```javascript
    // In a Vue component method
    const newItem = {
      name: 'New Gadget',
      price: 29.99
    };
    axios.post('http://localhost:8000/api/items.php', newItem) // newItem will be sent as JSON body
      .then(response => {
        console.log('Item created:', response.data);
        // Optionally, refresh list or add new item to local data
      })
      .catch(error => {
        console.error('Error creating item:', error);
      });
    ```
    Axios automatically serializes JavaScript objects to JSON for the request body and sets the `Content-Type: application/json` header.

5.  **Handling Responses:**
    *   The `.then(response => { ... })` block is executed when the request is successful (typically HTTP status 2xx).
    *   `response.data`: Contains the data returned by the server (Axios automatically parses JSON responses).
    *   `response.status`: The HTTP status code.
    *   `response.headers`: The HTTP headers.

6.  **Handling Errors:**
    *   The `.catch(error => { ... })` block is executed if the request fails or the server returns an error status code.
    *   The `error` object provides details about the failure.

7.  **Using `async/await` with Axios:**
    Makes asynchronous code look more synchronous and often easier to read.
    ```javascript
    // In an async Vue component method or lifecycle hook
    async fetchItems() {
      try {
        const response = await axios.get('http://localhost:8000/api/items.php');
        this.items = response.data;
        console.log('Items fetched with async/await:', this.items);
      } catch (error) {
        console.error('Error fetching items with async/await:', error);
        // Handle specific error properties as shown in the .catch example
      }
    }
    ```

### Using Fetch API (Built-in)

The Fetch API is a modern, built-in browser API for making HTTP requests. It's also promise-based.

*   **Basic `GET` Request:**
    ```javascript
    fetch('http://localhost:8000/api/items.php')
      .then(response => {
        if (!response.ok) { // Check if HTTP status is 200-299
          throw new Error('Network response was not ok: ' + response.statusText);
        }
        return response.json(); // Parses the JSON response body
      })
      .then(data => {
        console.log('Data fetched with Fetch:', data);
        this.items = data;
      })
      .catch(error => {
        console.error('Error fetching items with Fetch:', error);
      });
    ```

*   **Basic `POST` Request:**
    ```javascript
    const newItem = { name: 'Another Item', price: 15.00 };
    fetch('http://localhost:8000/api/items.php', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json', // Important: specify content type
      },
      body: JSON.stringify(newItem), // Manually stringify the JS object to JSON
    })
    .then(response => {
      if (!response.ok) {
        // Try to get error message from body if server sends JSON error
        return response.json().then(errData => {
            throw new Error(errData.error || 'POST request failed: ' + response.statusText);
        }).catch(() => { // If error body wasn't JSON
            throw new Error('POST request failed: ' + response.statusText);
        });
      }
      return response.json();
    })
    .then(data => {
      console.log('Item created with Fetch:', data);
    })
    .catch(error => {
      console.error('Error creating item with Fetch:', error);
    });
    ```

*   **Error Handling Differences with Fetch:**
    *   Fetch only considers a network error (e.g., request couldn't be made) as a promise rejection. HTTP error statuses (like 404 or 500) are **not** automatically treated as rejections. You need to check `response.ok` or `response.status` manually.
    *   Axios has more built-in conveniences for request/response transformation and error handling.

### Configuring a Base URL

It's good practice to define a base URL for your API calls, especially if it's used in multiple places.

*   **In `main.js` for Axios global config:**
    ```javascript
    // frontend_vue/src/main.js
    import { createApp } from 'vue';
    import App from './App.vue';
    import axios from 'axios';

    // Set a global base URL for Axios
    axios.defaults.baseURL = 'http://localhost:8000/api'; // Your PHP API base URL

    // You can also set global headers here if needed
    // axios.defaults.headers.common['Authorization'] = 'AUTH_TOKEN';

    const app = createApp(App);
    // If you want to make axios available globally in components via this.$axios (less common now with Composition API)
    // app.config.globalProperties.$axios = axios; 
    app.mount('#app');
    ```
    Then in components, you can make requests like: `axios.get('/items.php')`.

*   **Creating an Axios Instance:** For more complex scenarios or multiple API endpoints.
    ```javascript
    // frontend_vue/src/services/api.js (example file)
    import axios from 'axios';

    const apiClient = axios.create({
      baseURL: 'http://localhost:8000/api',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    });

    // You can add interceptors here for request/response handling (e.g., adding auth tokens)
    // apiClient.interceptors.request.use(config => { ... });

    export default apiClient;
    ```
    Then in components: `import apiClient from '@/services/api'; apiClient.get('/items.php');`

*   **Environment Variables for Vue:**
    For different base URLs in development vs. production, use environment variables in your Vue project (e.g., `.env.development` and `.env.production` files).
    In `.env.development`: `VUE_APP_API_BASE_URL=http://localhost:8000/api`
    In `.env.production`: `VUE_APP_API_BASE_URL=https://yourdomain.com/api`
    Then access it in your code: `axios.defaults.baseURL = process.env.VUE_APP_API_BASE_URL;`

## 4. Data Exchange - Vue.js and PHP

### Sending Data from Vue to PHP

*   **`GET` Requests (Query Parameters):**
    Data is sent as part of the URL.
    **Vue.js (Axios):**
    ```javascript
    axios.get('/items.php', {
      params: {
        id: 1,
        category: 'electronics'
      }
    })
    .then(response => { /* ... */ });
    // Request URL will be: /items.php?id=1&category=electronics
    ```
    **PHP (`/api/items.php`):**
    ```php
    <?php
    // GET /api/items.php?id=1&category=electronics
    $itemId = isset($_GET['id']) ? (int)$_GET['id'] : null;
    $category = isset($_GET['category']) ? filter_var($_GET['category'], FILTER_SANITIZE_STRING) : null;

    // ... logic to fetch item based on $itemId and $category ...
    // header('Content-Type: application/json');
    // echo json_encode(['message' => "Fetching item $itemId in category $category"]);
    ?>
    ```

*   **`POST`/`PUT`/`PATCH` Requests (Request Body as JSON):**
    Data is sent in the HTTP request body, typically as a JSON string.
    **Vue.js (Axios):**
    ```javascript
    const userData = { username: 'vue_user', email: 'vue@example.com' };
    axios.post('/users.php', userData) // Axios automatically stringifies and sets Content-Type
      .then(response => { /* ... */ });
    ```
    **PHP (`/api/users.php`):**
    ```php
    <?php
    // POST /api/users.php
    // Ensure Content-Type was application/json
    if ($_SERVER['REQUEST_METHOD'] === 'POST' && 
        isset($_SERVER['CONTENT_TYPE']) && 
        strpos(strtolower($_SERVER['CONTENT_TYPE']), 'application/json') !== false) {

        $jsonPayload = file_get_contents('php://input');
        $data = json_decode($jsonPayload, true); // true for associative array

        if (json_last_error() === JSON_ERROR_NONE && isset($data['username']) && isset($data['email'])) {
            $username = filter_var($data['username'], FILTER_SANITIZE_STRING);
            $email = filter_var($data['email'], FILTER_VALIDATE_EMAIL);
            
            if (!$email) {
                // http_response_code(400);
                // echo json_encode(['error' => 'Invalid email format.']);
                // exit;
            }
            // Process $username and $email (e.g., save to database)
            // header('Content-Type: application/json');
            // http_response_code(201); // Created
            // echo json_encode(['message' => 'User created', 'username' => $username, 'email' => $email]);
        } else {
            // http_response_code(400); // Bad Request
            // echo json_encode(['error' => 'Invalid JSON or missing data.']);
        }
    } else {
        // http_response_code(405); // Method Not Allowed or 415 Unsupported Media Type
        // echo json_encode(['error' => 'Invalid request method or content type.']);
    }
    ?>
    ```

### Receiving and Displaying Data in Vue

1.  **Store API Response in Component's `data()` (Options API) or `ref`/`reactive` (Composition API):**
    ```javascript
    // Options API example
    // <script>
    // export default {
    //   data() {
    //     return {
    //       users: [],
    //       isLoading: false,
    //       error: null
    //     };
    //   },
    //   mounted() {
    //     this.fetchUsers();
    //   },
    //   methods: {
    //     async fetchUsers() {
    //       this.isLoading = true;
    //       this.error = null;
    //       try {
    //         const response = await axios.get('http://localhost:8000/api/users.php');
    //         this.users = response.data;
    //       } catch (err) {
    //         this.error = 'Failed to load users.';
    //         console.error(err);
    //       } finally {
    //         this.isLoading = false;
    //       }
    //     }
    //   }
    // };
    // </script>

    // Composition API example
    // <script setup>
    import { ref, onMounted } from 'vue';
    import axios from 'axios'; // Or your configured apiClient

    const users = ref([]);
    const isLoading = ref(false);
    const error = ref(null);

    const fetchUsers = async () => {
      isLoading.value = true;
      error.value = null;
      try {
        const response = await axios.get('http://localhost:8000/api/users.php');
        users.value = response.data;
      } catch (err) {
        error.value = 'Failed to load users.';
        console.error(err);
      } finally {
        isLoading.value = false;
      }
    };

    onMounted(fetchUsers); // Fetch users when component is mounted
    // </script>
    ```

2.  **Bind Data to the Template:**
    Use Vue's template syntax to display the data.
    ```html
    <!-- In your Vue component's <template> -->
    <div class="user-list">
      <h2>User List</h2>
      <div v-if="isLoading">Loading users...</div>
      <div v-if="error" class="error-message">{{ error }}</div>
      <ul v-if="!isLoading && !error && users.length > 0">
        <li v-for="user in users" :key="user.id">
          {{ user.name }} ({{ user.email }})
        </li>
      </ul>
      <div v-if="!isLoading && !error && users.length === 0">
        No users found.
      </div>
      <button @click="fetchUsers" :disabled="isLoading">Refresh Users</button>
    </div>
    ```
    *   `v-if`, `v-else-if`, `v-else`: Conditional rendering.
    *   `v-for`: Loop through the `users` array.
    *   `:key`: Essential for `v-for` to help Vue track list items efficiently.
    *   `{{ user.name }}`: Data binding (interpolation).
    *   `@click="fetchUsers"`: Event handling.
    *   `:disabled="isLoading"`: Binding to attributes.

3.  **Reactive Updates:** When the `users`, `isLoading`, or `error` data properties in your Vue component change (e.g., after an API call completes), Vue's reactivity system automatically updates the parts of the DOM that depend on these properties.

## 5. Handling CORS (Cross-Origin Resource Sharing) - Recap and Context

As mentioned in the "PHP for Web APIs" section and project setup:

*   **Why it's needed:** During development, your Vue.js dev server (e.g., `http://localhost:8080`) and PHP API server (e.g., `http://localhost:8000`) are on different origins. Browsers block JavaScript from making requests to a different origin unless the server explicitly allows it via CORS headers.

*   **PHP Headers for CORS:**
    Place these at the beginning of your PHP API scripts (e.g., in a central bootstrap file or at the top of each endpoint script).

    ```php
    <?php
    // Allow requests from your Vue.js development server origin
    // Be more specific in production if possible
    header("Access-Control-Allow-Origin: http://localhost:8080"); 

    // Allowed HTTP methods for the actual request
    header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, PATCH, OPTIONS");

    // Allowed headers in the client's request
    // Common headers include Content-Type (for JSON POST/PUT), Authorization (for tokens)
    header("Access-Control-Allow-Headers: Content-Type, Authorization, X-Requested-With, Origin, Accept");

    // Optional: If your frontend needs to send cookies or use HTTP authentication
    // header("Access-Control-Allow-Credentials: true");

    // Optional: Max age for preflight request cache (in seconds)
    // header("Access-Control-Max-Age: 86400"); // 1 day

    // Handle preflight OPTIONS request (sent by browser for "non-simple" requests)
    if ($_SERVER['REQUEST_METHOD'] == 'OPTIONS') {
        // No need to run the rest of the script for OPTIONS
        // Just send the headers and a 200 OK or 204 No Content response
        http_response_code(204); // Or 200
        exit();
    }

    // ... rest of your API logic for GET, POST, PUT, DELETE etc.
    // Example: header("Content-Type: application/json"); // For your actual API response
    ?>
    ```
    **Note:** For `Access-Control-Allow-Origin`, use `*` only for completely public APIs or very early testing. It's more secure to specify the exact origin(s) that are allowed to connect.

*   **Production Environment:** If your Vue.js static files and PHP API are served from the **same domain and port** in production, CORS headers are generally not needed because the requests become same-origin.

## 6. Example: Simple Data Display Application

### PHP Backend (`backend_php/api/users.php`)

```php
<?php
// backend_php/api/users.php

// --- CORS Headers ---
header("Access-Control-Allow-Origin: http://localhost:8080"); // Your Vue dev server
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

if ($_SERVER['REQUEST_METHOD'] == 'OPTIONS') {
    http_response_code(200);
    exit();
}

// Hardcoded user data for example
$users = [
    ['id' => 1, 'name' => 'Alice Johnson', 'email' => 'alice.j@example.com', 'role' => 'Admin'],
    ['id' => 2, 'name' => 'Bob Williams', 'email' => 'bob.w@example.com', 'role' => 'Editor'],
    ['id' => 3, 'name' => 'Carol Davis', 'email' => 'carol.d@example.com', 'role' => 'Viewer'],
    ['id' => 4, 'name' => 'David Brown', 'email' => 'david.b@example.com', 'role' => 'Editor']
];

// Simulate a delay (optional, for testing loading states)
// sleep(1); 

if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    echo json_encode($users);
} else {
    http_response_code(405); // Method Not Allowed
    echo json_encode(['error' => 'Only GET method is supported for this endpoint.']);
}
?>
```

### Vue.js Frontend (`frontend_vue/src/components/UserList.vue`)

```html
<template>
  <div class="user-list-container">
    <h2>Our Users</h2>
    <button @click="fetchUsers" :disabled="isLoading" class="refresh-button">
      {{ isLoading ? 'Loading...' : 'Refresh Users' }}
    </button>

    <div v-if="isLoading" class="loading-indicator">Fetching user data...</div>
    
    <div v-if="apiError" class="error-message">
      <p>Error fetching users: {{ apiError }}</p>
      <p>Please ensure the PHP API server is running at <code>http://localhost:8000/api/users.php</code> and CORS is configured.</p>
    </div>

    <table v-if="!isLoading && !apiError && users.length > 0" class="user-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Email</th>
          <th>Role</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="user in users" :key="user.id">
          <td>{{ user.id }}</td>
          <td>{{ user.name }}</td>
          <td>{{ user.email }}</td>
          <td>{{ user.role }}</td>
        </tr>
      </tbody>
    </table>
    <div v-if="!isLoading && !apiError && users.length === 0" class="no-users">
      No users found.
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios'; // Or your configured apiClient

const users = ref([]);
const isLoading = ref(false);
const apiError = ref(null);

// Define the API endpoint URL
const API_USERS_URL = 'http://localhost:8000/api/users.php'; // Your PHP API URL

const fetchUsers = async () => {
  isLoading.value = true;
  apiError.value = null;
  users.value = []; // Clear previous users

  try {
    const response = await axios.get(API_USERS_URL);
    if (response.data && Array.isArray(response.data)) {
      users.value = response.data;
    } else {
      // Handle cases where response.data is not what's expected
      console.warn('Received unexpected data format:', response.data);
      apiError.value = 'Received unexpected data format from the server.';
    }
  } catch (error) {
    console.error('Error fetching users:', error);
    if (error.response) {
      apiError.value = `Server Error: ${error.response.status} - ${error.response.data.error || error.response.statusText}`;
    } else if (error.request) {
      apiError.value = 'No response from server. Is the API running?';
    } else {
      apiError.value = `Request Error: ${error.message}`;
    }
  } finally {
    isLoading.value = false;
  }
};

// Fetch users when the component is first mounted
onMounted(fetchUsers);
</script>

<style scoped>
.user-list-container {
  font-family: sans-serif;
  padding: 20px;
  max-width: 800px;
  margin: 20px auto;
  box-shadow: 0 0 10px rgba(0,0,0,0.1);
}
.refresh-button {
  margin-bottom: 15px;
  padding: 8px 15px;
  cursor: pointer;
}
.loading-indicator, .no-users {
  padding: 10px;
  color: #555;
}
.error-message {
  color: red;
  background-color: #ffe0e0;
  border: 1px solid red;
  padding: 10px;
  margin-bottom: 15px;
}
.user-table {
  width: 100%;
  border-collapse: collapse;
}
.user-table th, .user-table td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}
.user-table th {
  background-color: #f2f2f2;
}
</style>
```
**To use this component:**
1.  Save it as `UserList.vue` in your `frontend_vue/src/components/` directory.
2.  Import and use it in `App.vue` or another view:
    ```html
    <!-- frontend_vue/src/App.vue -->
    <template>
      <div id="app">
        <UserList />
      </div>
    </template>

    <script setup>
    import UserList from './components/UserList.vue';
    </script>
    ```

## 7. Example: Posting Data from Vue to PHP

### Vue.js Frontend (`frontend_vue/src/components/AddUserForm.vue`)

```html
<template>
  <div class="add-user-container">
    <h2>Add New User</h2>
    <form @submit.prevent="handleFormSubmit" class="user-form">
      <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" id="name" v-model="newUser.name" required>
      </div>
      <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="newUser.email" required>
      </div>
      <div class="form-group">
        <label for="role">Role:</label>
        <select id="role" v-model="newUser.role">
          <option value="Viewer">Viewer</option>
          <option value="Editor">Editor</option>
          <option value="Admin">Admin</option>
        </select>
      </div>
      <button type="submit" :disabled="isSubmitting" class="submit-button">
        {{ isSubmitting ? 'Adding...' : 'Add User' }}
      </button>
    </form>

    <div v-if="submissionMessage" 
         :class="['submission-message', submissionStatus === 'success' ? 'success' : 'error']">
      {{ submissionMessage }}
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios'; // Or your configured apiClient

const newUser = ref({
  name: '',
  email: '',
  role: 'Viewer' // Default role
});

const isSubmitting = ref(false);
const submissionMessage = ref('');
const submissionStatus = ref(''); // 'success' or 'error'

const API_ADD_USER_URL = 'http://localhost:8000/api/addUser.php'; // Your PHP API URL

const handleFormSubmit = async () => {
  isSubmitting.value = true;
  submissionMessage.value = '';
  submissionStatus.value = '';

  try {
    // Basic client-side validation (can be more extensive)
    if (!newUser.value.name.trim() || !newUser.value.email.trim()) {
        submissionMessage.value = 'Name and Email are required.';
        submissionStatus.value = 'error';
        isSubmitting.value = false;
        return;
    }

    const response = await axios.post(API_ADD_USER_URL, newUser.value);
    
    submissionMessage.value = response.data.message || 'User added successfully!';
    submissionStatus.value = 'success';
    
    // Optionally, reset form or emit an event to refresh a user list
    newUser.value = { name: '', email: '', role: 'Viewer' }; 

  } catch (error) {
    console.error('Error adding user:', error);
    if (error.response && error.response.data && error.response.data.error) {
      submissionMessage.value = `Error: ${error.response.data.error}`;
    } else if (error.request) {
      submissionMessage.value = 'Error: No response from server. Is the API running?';
    } else {
      submissionMessage.value = `Error: ${error.message}`;
    }
    submissionStatus.value = 'error';
  } finally {
    isSubmitting.value = false;
  }
};
</script>

<style scoped>
.add-user-container {
  font-family: sans-serif;
  padding: 20px;
  max-width: 500px;
  margin: 20px auto;
  box-shadow: 0 0 10px rgba(0,0,0,0.1);
}
.user-form .form-group {
  margin-bottom: 15px;
}
.user-form label {
  display: block;
  margin-bottom: 5px;
}
.user-form input[type="text"],
.user-form input[type="email"],
.user-form select {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
}
.submit-button {
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
.submit-button:disabled {
  background-color: #aaa;
}
.submission-message {
  margin-top: 15px;
  padding: 10px;
  border-radius: 4px;
}
.submission-message.success {
  color: #155724;
  background-color: #d4edda;
  border: 1px solid #c3e6cb;
}
.submission-message.error {
  color: #721c24;
  background-color: #f8d7da;
  border: 1px solid #f5c6cb;
}
</style>
```

### PHP Backend (`backend_php/api/addUser.php`)

```php
<?php
// backend_php/api/addUser.php

// --- CORS Headers ---
header("Access-Control-Allow-Origin: http://localhost:8080"); // Your Vue dev server
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

if ($_SERVER['REQUEST_METHOD'] == 'OPTIONS') {
    http_response_code(200);
    exit();
}

// --- Handle POST Request ---
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Check if content type is application/json
    if (isset($_SERVER['CONTENT_TYPE']) && strpos(strtolower($_SERVER['CONTENT_TYPE']), 'application/json') !== false) {
        $jsonPayload = file_get_contents('php://input');
        $data = json_decode($jsonPayload, true); // true for associative array

        // Basic Validation
        if (json_last_error() === JSON_ERROR_NONE && 
            isset($data['name']) && !empty(trim($data['name'])) &&
            isset($data['email']) && filter_var($data['email'], FILTER_VALIDATE_EMAIL) &&
            isset($data['role'])) {

            $name = filter_var(trim($data['name']), FILTER_SANITIZE_STRING);
            $email = $data['email']; // Already validated
            $role = filter_var($data['role'], FILTER_SANITIZE_STRING);

            // In a real application, you would save this to a database.
            // For this example, we'll just simulate success and return the data.
            // You might also add to a session array if you're continuing the Tasks API example logic.
            session_start(); // If using session like in tasks.php example
            // For example:
            // if (!isset($_SESSION['all_users'])) $_SESSION['all_users'] = [];
            // $newUserId = count($_SESSION['all_users']) + 1;
            // $_SESSION['all_users'][] = ['id' => $newUserId, 'name' => $name, 'email' => $email, 'role' => $role];

            http_response_code(201); // Created
            echo json_encode([
                'message' => 'User added successfully!',
                'user' => [
                    // 'id' => $newUserId, // If generating an ID
                    'name' => $name,
                    'email' => $email,
                    'role' => $role
                ]
            ]);

        } else {
            http_response_code(400); // Bad Request
            $errors = [];
            if (json_last_error() !== JSON_ERROR_NONE) $errors[] = "Invalid JSON payload.";
            if (empty(trim($data['name'] ?? ''))) $errors[] = "Name is required.";
            if (!filter_var($data['email'] ?? '', FILTER_VALIDATE_EMAIL)) $errors[] = "Valid email is required.";
            if (empty($data['role'] ?? '')) $errors[] = "Role is required.";
            echo json_encode(['error' => 'Invalid input.', 'details' => $errors]);
        }
    } else {
        http_response_code(415); // Unsupported Media Type
        echo json_encode(['error' => 'Invalid Content-Type. Please send application/json.']);
    }
} else {
    http_response_code(405); // Method Not Allowed
    echo json_encode(['error' => 'Only POST method is supported for this endpoint.']);
}
?>
```

This detailed guide should provide a solid starting point for integrating PHP backends with Vue.js frontends, covering the core concepts and practical examples for common scenarios. Remember that for production applications, more robust error handling, security measures, and potentially more sophisticated routing on the PHP side would be necessary.
