# Review Exercises

These exercises are designed to help you practice and combine the concepts you've learned throughout this PHP tutorial, including PHP for backend logic, HTML for structure, CSS for styling (though minimal here), and JavaScript (specifically Vue.js) for interactive frontend development.

## Exercise 1: "Greeter" - PHP and Vue Interaction

*   **Objective:** Understand basic data passing from a PHP backend to a Vue.js component.
*   **Requirements:**
    *   **PHP Backend:**
        1.  Create a PHP script (e.g., `api/greeter.php`).
        2.  In this script, define a greeting message (e.g., "Hello from the PHP World!").
        3.  The script should output this message as a JSON object. For example: `{"message": "Hello from the PHP World!"}`.
        4.  Set the `Content-Type` header to `application/json`.
    *   **Vue.js Frontend:**
        1.  Create a simple Vue.js component (e.g., `GreetingDisplay.vue`).
        2.  When this component is mounted (e.g., in the `mounted()` lifecycle hook or `onMounted` in Composition API), use Axios (or Fetch API) to make a `GET` request to your PHP script.
        3.  Store the greeting message received from the PHP script in a data property of the Vue component.
        4.  Display this greeting message in the component's template (e.g., within an `<h1>` or `<p>` tag).
*   **Focus:**
    *   PHP: Serving data as JSON.
    *   Vue.js: Making API calls (GET request), handling asynchronous responses, data binding, component lifecycle.
    *   Basic client-server interaction.
*   **Hints:**
    *   PHP: Use `json_encode(['message' => $your_greeting_variable]);` and `header('Content-Type: application/json');`.
    *   Vue: Install Axios if you haven't. Remember to handle the promise returned by Axios.
    *   Ensure your PHP script is accessible by the Vue development server (CORS might be needed if running on different ports).

## Exercise 2: Basic Form Handling - Vue Frontend, PHP Backend

*   **Objective:** Implement form submission from a Vue.js frontend to a PHP backend, including basic server-side validation.
*   **Requirements:**
    *   **Vue.js Frontend:**
        1.  Create a Vue component (e.g., `ContactForm.vue`).
        2.  Design an HTML form within this component. The form should include fields for:
            *   Name (text input)
            *   Email (email input)
            *   Message (textarea)
        3.  Use `v-model` to bind these form fields to data properties in your Vue component.
        4.  Implement a method that is called when the form is submitted (`@submit.prevent`).
        5.  This method should use Axios (or Fetch) to send the form data to a PHP script using a `POST` request. The data should be sent as a JSON payload.
        6.  Display any success or error messages received from the PHP backend.
    *   **PHP Backend:**
        1.  Create a PHP script (e.g., `api/contact_handler.php`).
        2.  This script should expect to receive JSON data via a `POST` request.
        3.  Retrieve the raw POST data (`file_get_contents('php://input')`) and decode it (`json_decode()`).
        4.  Perform basic server-side validation:
            *   Check if `name`, `email`, and `message` fields are present and not empty.
            *   Validate that the `email` field contains a valid email format (e.g., using `filter_var($email, FILTER_VALIDATE_EMAIL)`).
        5.  If validation fails, send a JSON response with an appropriate error status code (e.g., 400 Bad Request) and an array of error messages.
            Example: `{"status": "error", "errors": {"email": "Invalid email format."}}`
        6.  If validation passes, simulate processing the message (e.g., you could log it to a file or just prepare a success message). Send a JSON response indicating success (e.g., status code 200 OK or 201 Created).
            Example: `{"status": "success", "message": "Your message has been received!"}`
        7.  Set the `Content-Type` header to `application/json`.
*   **Focus:**
    *   Vue.js: Form handling with `v-model`, event handling, making `POST` requests with JSON data, displaying dynamic feedback.
    *   PHP: Receiving JSON payloads, server-side validation, structuring JSON responses for success/error.
    *   CORS configuration on the PHP backend will be necessary.
*   **Hints:**
    *   Vue: Remember to set the `Content-Type: application/json` header in your Axios POST request if Axios doesn't do it automatically for objects.
    *   PHP: Check `$_SERVER['REQUEST_METHOD']`. Use `http_response_code()` to set status codes.
    *   Consider how to display multiple validation errors nicely in Vue.

## Exercise 3: To-Do List Application - Full CRUD

*   **Objective:** Build a complete Create, Read, Update, and Delete (CRUD) application using Vue.js for the frontend and PHP with a database for the backend.
*   **Requirements:**
    *   **PHP Backend API:**
        1.  Choose a database: **SQLite** is recommended for this exercise due to its simplicity (single file, no separate server setup needed). If you are comfortable, you can use MySQL/MariaDB with PDO.
        2.  Create a table for to-do items (e.g., `tasks` with columns like `id` (Primary Key, Auto Increment), `title` (TEXT), `completed` (BOOLEAN or INTEGER 0/1), `created_at` (DATETIME)).
        3.  Implement the following API endpoints using PHP and PDO (or SQLite3 class):
            *   `GET /api/todos.php`: Fetch all to-do items from the database. Return as JSON.
            *   `POST /api/todos.php`: Add a new to-do item. Expect JSON data (`{"title": "New task"}`). Insert into the database. Return the newly created task (with its ID) as JSON with a 201 status.
            *   `PUT /api/todos.php?id={id}`: Update an existing to-do item. Expect JSON data (e.g., `{"title": "Updated title", "completed": true}`). Update the corresponding record in the database. Return the updated task or a success message.
            *   `DELETE /api/todos.php?id={id}`: Delete a to-do item by its ID. Return a success message or status (e.g., 204 No Content).
        4.  **Use prepared statements for all database operations involving user input to prevent SQL injection.**
        5.  Ensure appropriate `Content-Type: application/json` headers and HTTP status codes are used.
    *   **Vue.js Frontend:**
        1.  Create a Vue component (e.g., `TodoList.vue`).
        2.  **Display To-Dos:** Fetch and display the list of to-do items from the `GET /api/todos.php` endpoint when the component loads.
        3.  **Add To-Dos:** Include an input field and a button to add new to-do items. Submitting this should make a `POST` request to `/api/todos.php`. Update the list dynamically.
        4.  **Mark as Complete/Incomplete:** Each to-do item in the list should have a way (e.g., a checkbox or a button) to toggle its `completed` status. This should trigger a `PUT` request to `/api/todos.php?id={id}` with the updated status. Visually reflect the change (e.g., strikethrough for completed tasks).
        5.  **Delete To-Dos:** Each to-do item should have a delete button. Clicking it should make a `DELETE` request to `/api/todos.php?id={id}`. Remove the item from the list dynamically.
        6.  **(Optional) Edit To-Dos:** Add functionality to edit the text of an existing to-do item. This would likely involve another `PUT` request.
*   **Focus:**
    *   PHP: RESTful API design, PDO/SQLite3 for database interaction, prepared statements, CRUD operations.
    *   Vue.js: Managing a list of items, making GET, POST, PUT, DELETE requests, updating the UI reactively, component methods, state management (local component state is fine).
    *   Full-stack integration.
*   **Hints:**
    *   **PHP/SQLite Setup:**
        ```php
        // Example connection to SQLite in PHP
        // $db_path = __DIR__ . '/../database/todo.sqlite'; // Store DB outside /api
        // try {
        //    $pdo = new PDO('sqlite:' . $db_path);
        //    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        //    // Create table if it doesn't exist
        //    $pdo->exec("CREATE TABLE IF NOT EXISTS tasks (
        //        id INTEGER PRIMARY KEY AUTOINCREMENT,
        //        title TEXT NOT NULL,
        //        completed INTEGER DEFAULT 0,
        //        created_at DATETIME DEFAULT CURRENT_TIMESTAMP
        //    )");
        // } catch (PDOException $e) { die("DB Error: " . $e->getMessage()); }
        ```
    *   Vue: You'll need methods for `fetchTasks`, `addTask`, `updateTaskStatus`, `deleteTask`.
    *   Consider how IDs will be handled for PUT and DELETE requests (e.g., passing them as query parameters or path parameters if you set up URL rewriting).

## Exercise 4: Basic User Registration & Login (Simplified)

*   **Objective:** Understand the fundamental flow of user registration, login, logout, and managing authentication state using PHP sessions and Vue.js.
*   **Requirements:**
    *   **PHP Backend API:**
        1.  Use **SQLite** (as in Exercise 3) or MySQL/MariaDB for storing user data (e.g., `users` table with `id`, `username` (unique), `email` (unique), `password_hash` (TEXT)).
        2.  `POST /api/auth.php?action=register`:
            *   Accepts `username`, `email`, and `password` (JSON payload).
            *   Validate input (e.g., required fields, valid email, password complexity - basic is fine for exercise).
            *   Check if username or email already exists.
            *   Hash the password using `password_hash()`. **Do not store plain text passwords.**
            *   Store the new user in the database.
            *   Return a success or error JSON response.
        3.  `POST /api/auth.php?action=login`:
            *   Accepts `email` (or `username`) and `password` (JSON payload).
            *   Retrieve the user from the database by email/username.
            *   If user exists, verify the submitted password against the stored hash using `password_verify()`.
            *   If verification is successful, start a PHP session (`session_start()`) and store user identification (e.g., `$_SESSION['user_id'] = $userId; $_SESSION['username'] = $username;`).
            *   Return a success JSON response (e.g., including user info like username).
            *   If verification fails, return an error JSON response (e.g., "Invalid credentials").
        4.  `GET /api/auth.php?action=check`:
            *   Start session.
            *   Check if `$_SESSION['user_id']` (or similar) is set.
            *   Return JSON indicating login status and user data if logged in (e.g., `{"loggedIn": true, "user": {"id": 1, "username": "test"}}` or `{"loggedIn": false}`).
        5.  `POST /api/auth.php?action=logout`:
            *   Start session.
            *   Destroy the PHP session (`session_unset()`, `session_destroy()`, clear session cookie).
            *   Return a success JSON response.
    *   **Vue.js Frontend:**
        1.  Create components for `RegistrationForm.vue`, `LoginForm.vue`.
        2.  Create a simple global state management solution (e.g., a simple object or use Pinia/Vuex if familiar, but a simple reactive object is fine for this exercise) to store the current user's authentication status and information.
        3.  **Registration:** Implement the form to call the `/register` endpoint.
        4.  **Login:** Implement the form to call the `/login` endpoint. On success, update the global auth state.
        5.  **Check Auth State:** When the app loads (e.g., in `App.vue`'s `mounted` hook), call the `/check-auth` endpoint to see if a session already exists. Update the global auth state accordingly.
        6.  **Conditional Rendering:**
            *   Show "Login" and "Register" links/views if the user is not logged in.
            *   If logged in, show a welcome message (e.g., "Welcome, [username]!") and a "Logout" button.
            *   (Optional) Create a "Dashboard" component/route that is only accessible/visible if the user is logged in.
        7.  **Logout:** Implement the "Logout" button to call the `/logout` endpoint and clear the global auth state.
*   **Focus:**
    *   PHP: Session management (`session_start`, `$_SESSION`, `session_destroy`), password hashing (`password_hash`, `password_verify`), database interaction for users.
    *   Vue.js: Managing global authentication state, conditional rendering based on login status, making API calls for auth actions, form handling for login/registration.
*   **Disclaimer:** **This exercise is for learning purposes only and demonstrates a simplified authentication flow. Production authentication systems require much more robust security measures, including but not limited to CSRF protection, more advanced session management, rate limiting, input sanitization, protection against timing attacks, two-factor authentication, etc.**
*   **Hints:**
    *   PHP: Ensure `session_start()` is called at the beginning of any script that needs session access.
    *   Vue: You can create a simple service (e.g., `authService.js`) to encapsulate Axios calls to your auth endpoints.
    *   For global state, a simple reactive object can be created in a separate JS file and imported into components.
        ```javascript
        // services/authStore.js (example)
        import { reactive } from 'vue';
        export const authState = reactive({
          isLoggedIn: false,
          user: null // { id: ..., username: ... }
        });
        ```

These exercises should provide a good challenge and help solidify your understanding of how PHP and Vue.js can work together to build dynamic web applications. Good luck!
