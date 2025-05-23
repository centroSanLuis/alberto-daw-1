# PHP for Web APIs

Modern web development often involves creating Application Programming Interfaces (APIs) that allow different software systems to communicate with each other. PHP is well-suited for building robust and scalable web APIs, especially RESTful APIs. This section will guide you through the concepts and practices of creating web APIs with PHP.

## 1. Understanding RESTful APIs

### What is an API (Application Programming Interface)?

An **API** is a set of rules, protocols, and tools that allows different software applications to communicate and exchange data with each other. It defines how software components should interact, abstracting the underlying implementation details. Think of it as a contract between two pieces of software.

### What is a Web API?

A **Web API** is an API that is accessed over a network, typically using the HTTP protocol. This means you can interact with a web API by making HTTP requests to specific URLs (Uniform Resource Locators), and you'll receive responses, often in a standard data format like JSON or XML.

### REST (Representational State Transfer) Principles

REST is an architectural style for designing networked applications. APIs that adhere to REST principles are called **RESTful APIs**. REST is not a strict protocol but a set of guiding constraints. Key principles include:

*   **Statelessness:** Each request from a client to the server must contain all the information needed to understand and process the request. The server does not store any client context or session state between requests. If session state is needed, the client is responsible for managing it and sending it with each relevant request.
*   **Client-Server Architecture:** The client (e.g., a web browser, mobile app) and the server (the API provider) are separate entities with distinct responsibilities. They communicate over a network. This separation allows them to evolve independently.
*   **Uniform Interface:** This is a crucial constraint that simplifies and decouples the architecture. It consists of several sub-constraints:
    *   **Resource Identification (URIs):** Resources (e.g., a user, a product, a blog post) are identified by Uniform Resource Identifiers (URIs). For example, `/users` might represent a collection of users, and `/users/123` might represent a specific user with ID 123.
    *   **Resource Manipulation Through Representations:** Clients interact with resources by exchanging representations of those resources. A representation could be in JSON, XML, or HTML format. For example, a `GET` request to `/users/123` might return a JSON representation of that user.
    *   **Self-descriptive Messages:** Each request and response should contain enough information for the other party to understand it. HTTP headers (like `Content-Type` for the format of the data, or `Authorization` for security) play a key role here.
    *   **Hypermedia as the Engine of Application State (HATEOAS) (Optional but advanced):** Responses from the server can include links (hypermedia) that guide the client on how to discover other available actions or related resources.
*   **Cacheable:** Responses should be explicitly or implicitly defined as cacheable or non-cacheable. This helps improve performance and scalability by allowing clients or intermediaries to reuse previous responses. HTTP headers like `Cache-Control` and `Expires` are used for this.

### Resources

In a RESTful API, the focus is on **resources**. A resource is any piece of information or entity that can be named and accessed. Examples:
*   A user: `/users/john.doe` or `/users/101`
*   A collection of products: `/products`
*   A specific order: `/orders/A-98765`
*   The current weather in a city: `/weather/london`

URIs are used to identify these resources. They should be designed to be intuitive and hierarchical.

### HTTP Methods for CRUD Operations

RESTful APIs leverage standard HTTP methods to perform Create, Read, Update, and Delete (CRUD) operations on resources:

*   **`GET`**: Retrieve a resource or a collection of resources.
    *   `GET /users`: Retrieves a list of all users.
    *   `GET /users/123`: Retrieves the user with ID 123.
    *   `GET` requests should be **safe** (not change server state) and **idempotent** (multiple identical requests have the same effect as a single request).

*   **`POST`**: Create a new resource.
    *   `POST /users`: Creates a new user. The data for the new user (e.g., username, email) is typically sent in the request body (often as JSON).
    *   `POST` requests are generally **not idempotent** (multiple identical requests will likely create multiple new resources).

*   **`PUT`**: Update an existing resource (typically by replacing it entirely).
    *   `PUT /users/123`: Updates the user with ID 123. The complete representation of the updated user is sent in the request body.
    *   `PUT` requests should be **idempotent**. If you send the same `PUT` request multiple times, the resource state should be the same as after the first successful request.

*   **`PATCH`**: Partially update an existing resource.
    *   `PATCH /users/123`: Modifies only specific fields of the user with ID 123 (e.g., only updating the email address). The request body contains only the changes.
    *   `PATCH` requests are generally **not idempotent** if the patch operation is something like "append to a list" or "increment a value." However, if it's "set field X to value Y," it can be idempotent.

*   **`DELETE`**: Delete a resource.
    *   `DELETE /users/123`: Deletes the user with ID 123.
    *   `DELETE` requests should be **idempotent**. Deleting a resource multiple times should have the same effect as deleting it once (the resource is gone).

### HTTP Status Codes

HTTP status codes are crucial for indicating the outcome of an API request. Here are some common ones:

*   **2xx Success:**
    *   `200 OK`: Standard response for successful `GET` requests. Also used for successful `PUT` or `PATCH` if no other specific 2xx code is more appropriate.
    *   `201 Created`: The request was successful, and a new resource was created (typically after a `POST` request). The response often includes a `Location` header pointing to the URI of the new resource.
    *   `204 No Content`: The request was successful, but there is no content to return in the response body (often used for successful `DELETE` requests or `PUT` requests that don't return the updated resource).
*   **4xx Client Errors:**
    *   `400 Bad Request`: The server could not understand the request due to invalid syntax (e.g., malformed JSON, missing required parameters).
    *   `401 Unauthorized`: The request requires user authentication, and the client has not provided valid credentials or is not authenticated.
    *   `403 Forbidden`: The client is authenticated, but does not have permission to access the requested resource.
    *   `404 Not Found`: The requested resource could not be found on the server.
    *   `405 Method Not Allowed`: The HTTP method used in the request (e.g., `POST`) is not supported for the requested resource (e.g., trying to `POST` to a specific user URI `/users/123` when only `PUT` is allowed).
*   **5xx Server Errors:**
    *   `500 Internal Server Error`: A generic error message indicating that something went wrong on the server, and the server couldn't handle the request. This usually indicates a bug in the server-side code.
    *   `503 Service Unavailable`: The server is temporarily unable to handle the request (e.g., overloaded or down for maintenance).

## 2. Creating API Endpoints in PHP

An **API endpoint** is a specific URI where an API can be accessed.

### Structuring Your API

A common way to organize API files is to place them in a dedicated directory.

*   **Directory Structure:**
    ```
    /your_project_root
        /api
            items.php       // Handles requests for /api/items and /api/items/{id}
            users.php       // Handles requests for /api/users and /api/users/{id}
            .htaccess       // (Optional) For URL rewriting
            config.php      // API configuration, database connection
        /app
            // Your main application files
        /public
            index.php       // Main entry point for web app
        /vendor           // Composer dependencies
    ```

### Routing

Routing is the process of directing an HTTP request to the appropriate handler code based on the URI and HTTP method.

*   **Simple Routing with `$_SERVER['REQUEST_URI']` and `$_SERVER['REQUEST_METHOD']`:**
    For very basic APIs, you can parse `$_SERVER['REQUEST_URI']` to determine the resource and any ID, and use `$_SERVER['REQUEST_METHOD']` to determine the action.

    `api/items.php`:
    ```php
    <?php
    // Basic request parsing (simplified)
    header("Content-Type: application/json"); // Assume JSON output for all responses here

    $request_method = $_SERVER['REQUEST_METHOD'];
    $request_uri_parts = explode('/', trim($_SERVER['REQUEST_URI'], '/'));
    
    // Example: URI might be /api/items.php or /api/items.php/123
    // A more robust router would handle /api/items/123 directly
    
    $resource_id = null;
    // Find the script name part
    $script_name_index = array_search(basename(__FILE__), $request_uri_parts);

    if ($script_name_index !== false && isset($request_uri_parts[$script_name_index + 1])) {
        $resource_id = filter_var($request_uri_parts[$script_name_index + 1], FILTER_SANITIZE_NUMBER_INT);
    }

    // This is a very naive way to get ID if using path-like params after script name
    // e.g. /api/items.php/1
    // A better way is to use query parameters like /api/items.php?id=1
    // Or implement URL rewriting.

    // If using query parameters:
    $resource_id_query = isset($_GET['id']) ? filter_var($_GET['id'], FILTER_SANITIZE_NUMBER_INT) : null;
    if ($resource_id_query) {
        $resource_id = $resource_id_query;
    }


    switch ($request_method) {
        case 'GET':
            if ($resource_id) {
                echo json_encode(['message' => "Fetching item with ID: $resource_id"]);
                // In real app: fetch item from DB
            } else {
                echo json_encode(['message' => 'Fetching all items']);
                // In real app: fetch all items from DB
            }
            break;
        case 'POST':
            echo json_encode(['message' => 'Creating a new item']);
            // In real app: get data from request body, validate, save
            break;
        // Add PUT, DELETE cases
        default:
            http_response_code(405); // Method Not Allowed
            echo json_encode(['error' => 'Method Not Allowed']);
            break;
    }
    ?>
    ```
    To access with ID using the query parameter approach: `/api/items.php?id=1`

*   **URL Rewriting with `.htaccess` (for Apache):**
    To achieve cleaner URLs like `/api/items/123` instead of `/api/items.php?id=123`, you can use Apache's `mod_rewrite`.

    Place a `.htaccess` file in your `/api` directory (or project root, adjusting paths):
    ```apache
    # /api/.htaccess
    RewriteEngine On

    # Redirect requests for /api/items/ (optional trailing slash) to items.php
    RewriteRule ^items/?$ items.php [L]

    # Redirect requests for /api/items/some_id to items.php?id=some_id
    RewriteRule ^items/([0-9a-zA-Z_-]+)/?$ items.php?id=$1 [L,QSA]
    
    # Similar rules for other resources like users:
    # RewriteRule ^users/?$ users.php [L]
    # RewriteRule ^users/([0-9a-zA-Z_-]+)/?$ users.php?id=$1 [L,QSA]
    ```
    *   `RewriteEngine On`: Enables the rewrite engine.
    *   `RewriteRule pattern target [flags]`: Defines a rewrite rule.
        *   `^items/?$`: Matches `/items` or `/items/`.
        *   `items.php`: The actual file to handle the request.
        *   `[L]`: Last rule; if it matches, stop processing further rules.
        *   `^items/([0-9a-zA-Z_-]+)/?$`: Matches `/items/` followed by one or more alphanumeric characters, underscores, or hyphens (captured in `$1`), with an optional trailing slash.
        *   `items.php?id=$1`: Rewrites to `items.php`, passing the captured ID as a query parameter `id`.
        *   `[QSA]`: Query String Append; if the original request had a query string, it's appended to the rewritten URL.

    With this `.htaccess` in `/api/`, a request to `/api/items/123` would be internally routed to `/api/items.php?id=123`. Your PHP script (`items.php`) can then access the ID via `$_GET['id']`.

## 3. Handling Different HTTP Methods

You use the `$_SERVER['REQUEST_METHOD']` superglobal to determine which HTTP method was used for the request.

```php
<?php
// api/my_resource.php
header("Content-Type: application/json"); // Generally good practice for APIs

$method = $_SERVER['REQUEST_METHOD'];
$id = isset($_GET['id']) ? (int)$_GET['id'] : null; // Assuming ID from query string or rewrite rule

// Dummy data for example
$items = [
    1 => ['id' => 1, 'name' => 'Item A', 'price' => 10.99],
    2 => ['id' => 2, 'name' => 'Item B', 'price' => 5.50],
];

switch ($method) {
    case 'GET':
        if ($id !== null) {
            if (isset($items[$id])) {
                echo json_encode($items[$id]);
            } else {
                http_response_code(404); // Not Found
                echo json_encode(['error' => 'Item not found']);
            }
        } else {
            echo json_encode(array_values($items)); // Return all items
        }
        break;

    case 'POST':
        // For POST, you'd typically read JSON data from the request body
        $inputData = json_decode(file_get_contents('php://input'), true);
        if (json_last_error() === JSON_ERROR_NONE && isset($inputData['name']) && isset($inputData['price'])) {
            $newItemId = max(array_keys($items)) + 1; // Simple ID generation
            $newItem = [
                'id' => $newItemId,
                'name' => filter_var($inputData['name'], FILTER_SANITIZE_STRING), // Sanitize
                'price' => filter_var($inputData['price'], FILTER_VALIDATE_FLOAT) // Validate
            ];
            // $items[$newItemId] = $newItem; // In a real app, save to DB
            http_response_code(201); // Created
            echo json_encode($newItem);
        } else {
            http_response_code(400); // Bad Request
            echo json_encode(['error' => 'Invalid input or malformed JSON']);
        }
        break;

    case 'PUT':
        if ($id !== null) {
            if (isset($items[$id])) {
                $inputData = json_decode(file_get_contents('php://input'), true);
                if (json_last_error() === JSON_ERROR_NONE && isset($inputData['name']) && isset($inputData['price'])) {
                    $items[$id]['name'] = filter_var($inputData['name'], FILTER_SANITIZE_STRING);
                    $items[$id]['price'] = filter_var($inputData['price'], FILTER_VALIDATE_FLOAT);
                    // In a real app, update in DB
                    echo json_encode($items[$id]);
                } else {
                    http_response_code(400);
                    echo json_encode(['error' => 'Invalid input or malformed JSON for PUT']);
                }
            } else {
                http_response_code(404);
                echo json_encode(['error' => 'Item not found for PUT']);
            }
        } else {
            http_response_code(400); // Bad Request - PUT usually requires an ID
            echo json_encode(['error' => 'Item ID required for PUT']);
        }
        break;

    case 'DELETE':
        if ($id !== null) {
            if (isset($items[$id])) {
                // unset($items[$id]); // In a real app, delete from DB
                http_response_code(204); // No Content (success, no response body)
            } else {
                http_response_code(404);
                echo json_encode(['error' => 'Item not found for DELETE']);
            }
        } else {
            http_response_code(400); // Bad Request - DELETE requires an ID
            echo json_encode(['error' => 'Item ID required for DELETE']);
        }
        break;

    default:
        http_response_code(405); // Method Not Allowed
        echo json_encode(['error' => 'Method Not Allowed']);
        break;
}
?>
```

## 4. Working with JSON (JavaScript Object Notation)

JSON is the de facto standard data format for modern web APIs.

### Why JSON for APIs?

*   **Lightweight:** JSON has a minimal syntax, resulting in smaller message sizes compared to XML.
*   **Human-Readable:** While compact, JSON is still relatively easy for humans to read and understand.
*   **Widely Supported:** Virtually all server-side and client-side programming languages have excellent support for parsing and generating JSON.
*   **Easy for JavaScript to Parse:** JSON is a subset of JavaScript object literal syntax, making it trivial for JavaScript clients (like web browsers or Node.js apps) to consume.

### Outputting JSON from PHP

1.  **Set the `Content-Type` Header:** Before sending any JSON output, you must tell the client that the response body is JSON.
    ```php
    header('Content-Type: application/json');
    ```
    This header must be sent **before any other output** (echo, HTML, whitespace).

2.  **Use `json_encode()`:** This function converts a PHP array or object into a JSON string.
    `string json_encode ( mixed $value [, int $flags = 0 [, int $depth = 512 ]] )`
    *   `$value`: The PHP variable (array, object, scalar) to encode.
    *   `$flags` (Optional): Bitmask of options like `JSON_PRETTY_PRINT` (for human-readable formatted JSON), `JSON_UNESCAPED_SLASHES`, `JSON_UNESCAPED_UNICODE`.

**Example: Fetching data and returning JSON:**

```php
<?php
header('Content-Type: application/json');
// header('Access-Control-Allow-Origin: *'); // For CORS, see later section

// Sample data (in a real API, this would come from a database)
$users = [
    ['id' => 1, 'name' => 'Alice Wonderland', 'email' => 'alice@example.com'],
    ['id' => 2, 'name' => 'Bob The Builder', 'email' => 'bob@example.com'],
    ['id' => 3, 'name' => 'Charlie Brown', 'email' => 'charlie@example.com']
];

// Simulate fetching a specific user if an ID is provided
if (isset($_GET['id'])) {
    $requestedId = (int)$_GET['id'];
    $foundUser = null;
    foreach ($users as $user) {
        if ($user['id'] === $requestedId) {
            $foundUser = $user;
            break;
        }
    }
    if ($foundUser) {
        echo json_encode($foundUser, JSON_PRETTY_PRINT);
    } else {
        http_response_code(404); // Not Found
        echo json_encode(['error' => 'User not found', 'id' => $requestedId]);
    }
} else {
    // Return all users
    echo json_encode($users, JSON_PRETTY_PRINT);
}
?>
```

### Receiving JSON in PHP

When a client sends data (e.g., via `POST` or `PUT`) with a `Content-Type: application/json` header, PHP does not automatically populate `$_POST` with the JSON data. You need to read the raw request body.

1.  **Get Raw Request Body:** Use `file_get_contents('php://input')`.
    `php://input` is a read-only stream that allows you to read raw data from the request body.

2.  **Use `json_decode()`:** This function converts a JSON string into a PHP variable (an associative array or an object).
    `mixed json_decode ( string $json_string [, bool $associative = false [, int $depth = 512 [, int $flags = 0 ]]] )`
    *   `$json_string`: The JSON string to decode.
    *   `$associative` (Optional): If `true`, returns associative arrays. If `false` (default), returns objects (`stdClass`).
    *   `$flags` (Optional): Options like `JSON_THROW_ON_ERROR` (PHP 7.3+) to throw an exception on JSON errors instead of returning `null`.

**Example: Handling a POST request with a JSON payload:**

```php
<?php
header('Content-Type: application/json');
// header('Access-Control-Allow-Origin: *'); // CORS
// header('Access-Control-Allow-Methods: POST, OPTIONS'); // CORS
// header('Access-Control-Allow-Headers: Content-Type'); // CORS

// Handle preflight OPTIONS request for CORS
if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    http_response_code(204); // No Content
    exit;
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $jsonPayload = file_get_contents('php://input');
    
    // Attempt to decode the JSON payload
    // Using true for associative array, JSON_THROW_ON_ERROR for better error handling
    try {
        $data = json_decode($jsonPayload, true, 512, JSON_THROW_ON_ERROR);
    } catch (JsonException $e) {
        http_response_code(400); // Bad Request
        echo json_encode(['error' => 'Malformed JSON: ' . $e->getMessage()]);
        exit;
    }

    // Validate the received data (example)
    if (isset($data['name']) && isset($data['email']) && filter_var($data['email'], FILTER_VALIDATE_EMAIL)) {
        // Data is valid (basic check)
        // In a real application, save to database
        $newUserId = rand(100, 999); // Dummy ID generation
        $response = [
            'message' => 'User created successfully',
            'user' => [
                'id' => $newUserId,
                'name' => filter_var($data['name'], FILTER_SANITIZE_STRING),
                'email' => $data['email']
            ]
        ];
        http_response_code(201); // Created
        echo json_encode($response, JSON_PRETTY_PRINT);
    } else {
        http_response_code(400); // Bad Request
        echo json_encode(['error' => 'Invalid or missing data. "name" and valid "email" are required.']);
    }
} else {
    http_response_code(405); // Method Not Allowed
    echo json_encode(['error' => 'Only POST method is allowed for this endpoint.']);
}
?>
```
**Testing this `POST` endpoint:**
You would use a tool like Postman, Insomnia, or cURL to send a `POST` request to this script with a `Content-Type: application/json` header and a JSON body like:
```json
{
    "name": "Sarah Connor",
    "email": "sarah@skynet.com"
}
```

## 5. Building a Simple API - Example (Tasks API)

Let's create a very simple API for managing a list of tasks. For simplicity, we'll store the tasks in a PHP array in memory. In a real application, this would be a database.

**File Structure (Conceptual):**
Assume we have an `/api` directory.
*   `/api/tasks.php`

```php
<?php
// /api/tasks.php

// --- CORS Headers (Allow requests from any origin for development) ---
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

// Handle preflight OPTIONS request (important for CORS)
if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    http_response_code(200); // Or 204 No Content
    exit();
}

// --- Initialize tasks array (simulating a database) ---
session_start(); // Use session to persist data across requests for this example
if (!isset($_SESSION['tasks'])) {
    $_SESSION['tasks'] = [
        1 => ['id' => 1, 'title' => 'Learn PHP Basics', 'completed' => true],
        2 => ['id' => 2, 'title' => 'Build a Simple API', 'completed' => false],
        3 => ['id' => 3, 'title' => 'Understand REST', 'completed' => false]
    ];
    $_SESSION['next_task_id'] = 4;
}

$tasks = &$_SESSION['tasks']; // Use a reference for easier modification
$next_task_id = &$_SESSION['next_task_id'];

// --- Helper function to get ID from URL path ---
// Example: /api/tasks.php/1  or /api/tasks.php/ (for all)
// This is a very basic way to parse, a router is better for complex apps.
$pathInfo = $_SERVER['PATH_INFO'] ?? '';
$pathParts = explode('/', trim($pathInfo, '/'));
$taskId = isset($pathParts[0]) && $pathParts[0] !== '' ? (int)$pathParts[0] : null;


// --- Handle Request ---
$method = $_SERVER['REQUEST_METHOD'];

switch ($method) {
    case 'GET':
        if ($taskId !== null) { // Get specific task
            if (isset($tasks[$taskId])) {
                echo json_encode($tasks[$taskId]);
            } else {
                http_response_code(404);
                echo json_encode(['error' => 'Task not found']);
            }
        } else { // Get all tasks
            echo json_encode(array_values($tasks));
        }
        break;

    case 'POST': // Create new task
        $data = json_decode(file_get_contents('php://input'), true);
        if (isset($data['title'])) {
            $newTask = [
                'id' => $next_task_id,
                'title' => filter_var($data['title'], FILTER_SANITIZE_STRING),
                'completed' => $data['completed'] ?? false
            ];
            $tasks[$next_task_id] = $newTask;
            $next_task_id++;
            http_response_code(201); // Created
            echo json_encode($newTask);
        } else {
            http_response_code(400);
            echo json_encode(['error' => 'Missing title for new task']);
        }
        break;

    case 'PUT': // Update existing task
        if ($taskId !== null && isset($tasks[$taskId])) {
            $data = json_decode(file_get_contents('php://input'), true);
            if ($data) {
                if (isset($data['title'])) {
                    $tasks[$taskId]['title'] = filter_var($data['title'], FILTER_SANITIZE_STRING);
                }
                if (isset($data['completed'])) {
                    $tasks[$taskId]['completed'] = (bool)$data['completed'];
                }
                echo json_encode($tasks[$taskId]);
            } else {
                http_response_code(400);
                echo json_encode(['error' => 'Invalid JSON data for update']);
            }
        } else {
            http_response_code(404);
            echo json_encode(['error' => 'Task not found for update']);
        }
        break;

    case 'DELETE': // Delete task
        if ($taskId !== null && isset($tasks[$taskId])) {
            unset($tasks[$taskId]);
            http_response_code(204); // No Content
        } else {
            http_response_code(404);
            echo json_encode(['error' => 'Task not found for deletion']);
        }
        break;

    default:
        http_response_code(405); // Method Not Allowed
        echo json_encode(['error' => 'Method Not Allowed']);
        break;
}
?>
```

**Testing this API (using `tasks.php`):**

To make the `taskId` parsing work with URLs like `/api/tasks.php/1`, you might need an `.htaccess` in the `/api` directory (if using Apache):

```apache
# /api/.htaccess
RewriteEngine On

# If the request is not for an existing file or directory
RewriteCond %{REQUEST_FILENAME} !-f
RewriteCond %{REQUEST_FILENAME} !-d

# Route requests for tasks.php to tasks.php, capturing path info
RewriteRule ^tasks.php(/.*)?$ tasks.php$1 [L,QSA]
```
This rule allows `tasks.php` to receive the part after `tasks.php` (e.g., `/1`) in `$_SERVER['PATH_INFO']`.

**Example Requests (using cURL or Postman):**

*   `GET /api/tasks.php/`: List all tasks.
*   `GET /api/tasks.php/1`: Get task with ID 1.
*   `POST /api/tasks.php/` with JSON body `{"title": "New Task"}`: Create a new task.
*   `PUT /api/tasks.php/2` with JSON body `{"title": "Updated Task Title", "completed": true}`: Update task ID 2.
*   `DELETE /api/tasks.php/3`: Delete task ID 3.

**Note on Data Persistence:** This example uses PHP sessions to make the data persist across requests for demonstration. In a real-world API, you would replace the session-based storage with database operations (e.g., using PDO as covered in the previous section).

## 6. API Authentication and Security Basics (Brief Overview)

Protecting your API is crucial, especially if it handles sensitive data or performs critical operations.

### Why is Authentication Needed?

*   **Control Access:** Ensure that only authorized clients or users can access your API.
*   **Identify Clients:** Know who is using your API, which can be important for rate limiting, logging, or billing.
*   **Protect Data:** Prevent unauthorized access, modification, or deletion of resources.

### Common Authentication Methods (Briefly)

*   **API Keys:**
    *   Clients are issued a unique secret key.
    *   The client includes this key in requests, typically in an HTTP header (e.g., `X-API-Key: your_api_key` or `Authorization: ApiKey your_api_key`).
    *   The server validates the key.
    *   Simpler to implement but can be less secure if keys are compromised. Keys are often long-lived.

*   **Token-Based Authentication (e.g., JWT - JSON Web Tokens):**
    *   Client authenticates (e.g., with username/password) to an authentication endpoint.
    *   Server issues a signed token (like a JWT) that contains claims (information about the user).
    *   Client sends this token in the `Authorization` header (e.g., `Authorization: Bearer your_jwt_token`) with subsequent API requests.
    *   Server verifies the token's signature and claims to authorize the request.
    *   Tokens are often short-lived and can be refreshed. JWTs are stateless.

*   **OAuth 2.0:**
    *   An authorization framework that allows third-party applications to access user resources on a web service without exposing the user's credentials (username/password) to the third-party app.
    *   More complex but provides fine-grained access control and is standard for third-party authorization (e.g., "Log in with Google/Facebook").

### Basic Security Tips

*   **HTTPS (SSL/TLS):** **Always use HTTPS for your API.** This encrypts data in transit, protecting it from eavesdropping and tampering.
*   **Input Validation:** Rigorously validate all incoming data (path parameters, query parameters, request body content) as discussed in the "Forms and User Input" section. Prevent XSS, ensure correct data types, lengths, etc.
*   **Output Encoding:** When returning data (especially if it might contain user-generated content displayed elsewhere), ensure it's properly encoded for the context (e.g., `htmlspecialchars` for HTML, though for JSON responses to an API client, this is usually not needed as the client handles rendering).
*   **Rate Limiting:** Implement mechanisms to prevent abuse by limiting the number of requests a client can make in a certain time period.
*   **Proper HTTP Status Codes:** Use them correctly to indicate request outcomes.
*   **Error Handling:** Log detailed errors on the server but return generic, non-revealing error messages to the client.
*   **Principle of Least Privilege:** Ensure the API key or token used has only the permissions necessary for the operations it needs to perform.

API security is a deep topic. For production APIs, especially those handling sensitive data, further study and robust implementations are essential.

## 7. CORS (Cross-Origin Resource Sharing)

### What is CORS and Why is it Needed?

Web browsers implement a security feature called the **Same-Origin Policy (SOP)**. By default, SOP restricts web pages from making requests to a different domain (origin) than the one that served the web page. An origin is defined by the combination of protocol (http/https), domain, and port.

If your PHP API is running on `http://localhost:8000` and your Vue.js frontend (or any JavaScript client) is running on `http://localhost:8080` (different port, thus different origin), the browser will block requests from the Vue app to the PHP API due to SOP.

**CORS** is a mechanism that uses additional HTTP headers to tell browsers to give a web application running at one origin, access to selected resources from a different origin.

### Setting Basic CORS Headers in PHP

To allow cross-origin requests to your PHP API, you need to send specific CORS headers in your API's responses.

```php
<?php
// Allow requests from a specific origin (e.g., your Vue app's development server)
header("Access-Control-Allow-Origin: http://localhost:8080"); 
// Or, for public APIs or during very early development (less secure):
// header("Access-Control-Allow-Origin: *"); 

// Allowed HTTP methods
header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");

// Allowed HTTP headers in requests
header("Access-Control-Allow-Headers: Content-Type, Authorization, X-Requested-With");

// Optional: How long the results of a preflight request can be cached (in seconds)
// header("Access-Control-Max-Age: 3600");

// Optional: If your API uses cookies or HTTP authentication with credentials
// header("Access-Control-Allow-Credentials: true"); // Be cautious with this and origins

// The rest of your API logic...
// Example:
// if ($_SERVER['REQUEST_METHOD'] === 'GET') {
//     header("Content-Type: application/json");
//     echo json_encode(["message" => "CORS headers are set!"]);
// }
?>
```

**Explanation:**

*   **`Access-Control-Allow-Origin`**: Specifies which origins are allowed to access the resource.
    *   `*`: Allows any origin. Use with caution, especially for APIs that handle sensitive data or require authentication. It's better to list specific allowed origins.
    *   `http://localhost:8080`: Allows requests only from this specific origin.
*   **`Access-Control-Allow-Methods`**: Specifies which HTTP methods are allowed when accessing the resource (e.g., `GET, POST, PUT`).
*   **`Access-Control-Allow-Headers`**: Specifies which HTTP headers can be used during the actual request. This is important if your client sends custom headers or headers like `Content-Type: application/json` or `Authorization`.

### Handling Preflight `OPTIONS` Requests

For certain types of cross-origin requests (called "non-simple" requests, e.g., those with methods other than `GET`/`HEAD`/`POST`, or with `Content-Type` other than `application/x-www-form-urlencoded`, `multipart/form-data`, `text/plain`, or with custom headers), the browser first sends a **preflight `OPTIONS` request** to the server.

The server must respond to this `OPTIONS` request with the appropriate CORS headers, indicating whether the actual request is allowed.

You need to handle these `OPTIONS` requests in your PHP API, typically by sending back the CORS headers and an empty response with a `200 OK` or `204 No Content` status.

```php
<?php
// --- CORS Headers ---
// Allow requests from your Vue app's development server
header("Access-Control-Allow-Origin: http://localhost:8080"); 
// Or, for testing with any origin:
// header("Access-Control-Allow-Origin: *"); 

header("Access-Control-Allow-Methods: GET, POST, PUT, PATCH, DELETE, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type, Authorization, X-Requested-With, Origin, Accept");
// header("Access-Control-Allow-Credentials: true"); // If you need to allow cookies/auth headers
// header("Access-Control-Max-Age: 86400"); // Cache preflight for 1 day

// --- Handle preflight OPTIONS request ---
if ($_SERVER['REQUEST_METHOD'] == 'OPTIONS') {
    // Return an empty response with 200 OK or 204 No Content status
    http_response_code(204); 
    exit(); // Important to exit script after sending headers for OPTIONS
}

// --- Continue with your API logic ---
header("Content-Type: application/json"); // Set for actual API responses

// Example:
if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    echo json_encode(["message" => "Hello from PHP API with CORS!"]);
} elseif ($_SERVER['REQUEST_METHOD'] === 'POST') {
    echo json_encode(["message" => "POST request received!"]);
}
// ... etc.
?>
```
Place this CORS handling logic at the very beginning of your API scripts, before any other processing or output.

Understanding and correctly implementing CORS is essential when building APIs that will be consumed by front-end JavaScript applications running on different origins. For production, always be specific with `Access-Control-Allow-Origin` rather than using `*` if possible.

This covers the essentials of building Web APIs with PHP. From understanding REST principles to handling JSON and securing your endpoints, these concepts will form the foundation for creating powerful and interactive web services.
