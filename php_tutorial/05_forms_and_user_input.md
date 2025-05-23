# Working with Forms and User Input

Interacting with users is a fundamental aspect of web applications. HTML forms are the primary way to collect user input, and PHP provides powerful tools to process, validate, and secure this data. This section covers how to handle form submissions, manage user data with cookies and sessions, and ensure your application is secure.

## 1. HTML Forms - A Quick Recap

Before diving into PHP, let's quickly review the basics of HTML forms.

An HTML form is a section of a document containing controls such as text fields, password fields, checkboxes, radio buttons, select menus, and buttons. Users fill out these controls and submit the form to a web server for processing.

**Basic Structure:**

```html
<form action="process_form.php" method="POST">
    <!-- Input field for text -->
    <label for="username">Username:</label>
    <input type="text" id="username" name="username"><br><br>

    <!-- Input field for password -->
    <label for="password">Password:</label>
    <input type="password" id="password" name="password"><br><br>

    <!-- Textarea for longer messages -->
    <label for="message">Message:</label>
    <textarea id="message" name="message" rows="4" cols="50"></textarea><br><br>

    <!-- Select dropdown -->
    <label for="color">Favorite Color:</label>
    <select id="color" name="color">
        <option value="red">Red</option>
        <option value="green">Green</option>
        <option value="blue">Blue</option>
    </select><br><br>

    <!-- Checkbox -->
    <input type="checkbox" id="subscribe" name="subscribe" value="yes">
    <label for="subscribe">Subscribe to newsletter</label><br><br>

    <!-- Radio buttons -->
    <p>Gender:</p>
    <input type="radio" id="male" name="gender" value="male">
    <label for="male">Male</label><br>
    <input type="radio" id="female" name="gender" value="female">
    <label for="female">Female</label><br><br>

    <!-- Submit button -->
    <button type="submit">Submit Form</button>
</form>
```

**Key Attributes:**

*   **`<form>` Tag:** The container for all form elements.
    *   **`action` Attribute:** Specifies the URL of the PHP script (or other server-side resource) that will process the form data. In the example, it's `process_form.php`. If empty, it submits to the current page.
    *   **`method` Attribute:** Defines the HTTP method to be used when submitting the form data.
        *   **`GET`**:
            *   Appends form data to the URL specified in the `action` attribute (e.g., `process_form.php?username=john&color=red`).
            *   Data is visible in the URL, browser history, and server logs.
            *   Suitable for non-sensitive data, idempotent requests (requests that can be repeated without changing server state), such as search queries or filtering.
            *   Limited data length (URLs have a maximum length).
        *   **`POST`**:
            *   Sends form data in the body of the HTTP request.
            *   Data is not visible in the URL.
            *   Suitable for sensitive information (like passwords), large amounts of data, or actions that modify server state (e.g., creating a new user, submitting a blog post).
            *   No practical limit on data length.

*   **Input Elements (`<input>`, `<textarea>`, `<select>`, `<button>`):**
    *   **`type` Attribute (for `<input>`):** Specifies the kind of input control. Common types include:
        *   `text`: Single-line text input.
        *   `password`: Single-line text input that masks characters.
        *   `email`: For email addresses (provides basic client-side validation in modern browsers).
        *   `number`: For numerical input.
        *   `checkbox`: Allows selecting zero or more options.
        *   `radio`: Allows selecting one option from a group (all radio buttons in a group must have the same `name`).
        *   `file`: For selecting files to upload.
        *   `submit`: A button that submits the form.
        *   `hidden`: An input field not visible to the user, often used to send extra data.
    *   **`name` Attribute:** **This is crucial for PHP.** The `name` attribute provides the key that PHP will use to access the submitted value in the `$_GET` or `$_POST` superglobal arrays. Each form field that you want to process in PHP must have a unique `name` attribute (except for radio buttons in the same group).
    *   **`value` Attribute:** For most input types, this specifies the initial value or the value sent when the form is submitted. For checkboxes and radio buttons, it's the value sent if that option is selected. For `<textarea>`, the content between the tags is its value. For `<option>` within `<select>`, it's the value sent.
    *   **`id` Attribute:** Used to associate a `<label>` with an input field for better accessibility and user experience.
    *   **`<label for="id_of_input">`:** Provides a descriptive label for a form control. Clicking the label focuses on or activates the associated control.

## 2. Retrieving Data in PHP

When a form is submitted, PHP makes the data available through special associative arrays called **superglobals**. These are available in all scopes throughout a script.

### `$_GET` Superglobal

*   Used to collect values from a form submitted with the `method="GET"`.
*   Also used to collect values from the URL's query string (e.g., `mypage.php?id=123&category=books`).
*   It's an associative array where keys are the `name` attributes of the form fields (or query string parameters) and values are the submitted data.

**Example:**
If a form with `method="GET"` has an input field `<input type="text" name="search_term">` and the user types "PHP tutorial" and submits, the URL might be `process.php?search_term=PHP+tutorial`.

In `process.php`:

```php
<?php
if (isset($_GET['search_term'])) {
    $searchTerm = $_GET['search_term'];
    echo "You searched for: " . htmlspecialchars($searchTerm) . "<br>";
} else {
    echo "No search term provided.<br>";
}

// Using null coalescing operator (PHP 7+) for a default value
$category = $_GET['category'] ?? 'all';
echo "Category: " . htmlspecialchars($category) . "<br>";
?>
```
*   **`isset($_GET['search_term'])`**: Checks if the `search_term` key exists in the `$_GET` array. This is important to avoid "undefined index" notices if the form field wasn't submitted or the parameter isn't in the URL.
*   **`htmlspecialchars($searchTerm)`**: **Crucial for security!** This function converts special HTML characters (like `<`, `>`, `&`, `"`) into their HTML entity equivalents (e.g., `&lt;`, `&gt;`). This prevents Cross-Site Scripting (XSS) attacks if you're displaying the user input back on the page. (More on this in Data Sanitization).

### `$_POST` Superglobal

*   Used to collect values from a form submitted with the `method="POST"`.
*   Data is sent in the HTTP request body and is not visible in the URL.
*   It's an associative array, similar to `$_GET`.

**Example:**
If a form with `method="POST"` has `<input type="text" name="username">` and `<input type="password" name="password">`.

In `login_handler.php`:

```php
<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") { // Check if the form was submitted via POST
    // Check if username is set and not empty
    if (isset($_POST['username']) && !empty(trim($_POST['username']))) {
        $username = $_POST['username']; // No need to htmlspecialchars if not immediately displaying
        echo "Username: " . htmlspecialchars($username) . "<br>";
    } else {
        echo "Username is required.<br>";
    }

    // Check for password
    if (isset($_POST['password']) && !empty($_POST['password'])) {
        $password = $_POST['password'];
        // Process the password (e.g., hash it, verify it)
        // NEVER display or store passwords in plain text!
        echo "Password received (but not displayed for security).<br>";
    } else {
        echo "Password is required.<br>";
    }
} else {
    echo "Please submit the form.<br>";
}
?>
```
*   **`$_SERVER["REQUEST_METHOD"] == "POST"`**: A common way to check if the script is being accessed via a POST request (i.e., the form was submitted).
*   **`!empty(trim($_POST['username']))`**: Checks if the username is not empty after removing leading/trailing whitespace.

### `$_REQUEST` Superglobal

*   `$_REQUEST` is another superglobal array that, by default, contains the contents of `$_GET`, `$_POST`, and `$_COOKIE`.
*   While it can be convenient, it's generally **recommended to use `$_GET` or `$_POST` directly.**
    *   **Clarity:** Using `$_GET` or `$_POST` makes your code more explicit about where you expect the data to come from.
    *   **Security:** Relying on `$_REQUEST` can introduce security vulnerabilities if you're not careful about the source of the data, especially due to the inclusion of `$_COOKIE` data. For instance, if `$_GET['action']` is expected but a cookie named `action` exists, `$_REQUEST['action']` might take the cookie's value if the order of variables in `request_order` (php.ini setting) prioritizes cookies.

**Example Script Processing Both GET (for an ID) and POST (for form data):**

`edit_item.php`:

```php
<?php
// Initialize variables
$itemId = null;
$itemName = '';
$itemPrice = '';
$message = '';

// --- Handle GET request for item ID (e.g., from a link like edit_item.php?id=1) ---
if (isset($_GET['id'])) {
    $itemId = (int)$_GET['id']; // Cast to integer for basic sanitization
    // In a real app, you would fetch item details from a database using $itemId
    $message = "Editing item ID: " . htmlspecialchars($itemId);
    // For demonstration, let's pre-fill some dummy data if ID is found
    if ($itemId == 1) {
        $itemName = "Sample Item";
        $itemPrice = "19.99";
    }
}

// --- Handle POST request from form submission ---
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $submittedItemId = $_POST['item_id'] ?? null; // Keep track of the ID submitted with the form
    $itemName = $_POST['item_name'] ?? '';
    $itemPrice = $_POST['item_price'] ?? '';

    // Basic validation (more on this next)
    if (empty(trim($itemName))) {
        $message = "Error: Item name cannot be empty.";
    } elseif (!is_numeric($itemPrice) || $itemPrice < 0) {
        $message = "Error: Item price must be a non-negative number.";
    } else {
        // Process the data (e.g., save to database)
        $message = "Item '" . htmlspecialchars($itemName) . "' (ID: " . htmlspecialchars($submittedItemId) . ") updated successfully with price $" . htmlspecialchars($itemPrice) . "!";
        // Usually, you'd redirect after successful POST to prevent re-submission
        // header("Location: success_page.php");
        // exit;
    }
}
?>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Item</title>
</head>
<body>
    <h1>Edit Item</h1>
    <?php if (!empty($message)): ?>
        <p style="color: <?php echo strpos($message, 'Error') === 0 ? 'red' : 'green'; ?>;">
            <?php echo $message; ?>
        </p>
    <?php endif; ?>

    <form action="edit_item.php<?php echo $itemId ? '?id=' . $itemId : ''; ?>" method="POST">
        <!-- Hidden field to send item ID back if we are editing an existing item -->
        <?php if ($itemId): ?>
            <input type="hidden" name="item_id" value="<?php echo htmlspecialchars($itemId); ?>">
        <?php endif; ?>

        <label for="item_name">Item Name:</label>
        <input type="text" id="item_name" name="item_name" value="<?php echo htmlspecialchars($itemName); ?>"><br><br>

        <label for="item_price">Item Price:</label>
        <input type="text" id="item_price" name="item_price" value="<?php echo htmlspecialchars($itemPrice); ?>"><br><br>

        <button type="submit">Save Changes</button>
    </form>

    <p><a href="edit_item.php?id=1">Edit Item 1</a></p>
    <p><a href="edit_item.php">Start New Item (No ID)</a></p>
</body>
</html>
```
This example shows how a single PHP script can handle both displaying a form (optionally pre-filled via GET) and processing its submission (via POST).

## 3. Data Validation

**Never trust user input!** Data validation is the process of ensuring that user input is appropriate, accurate, and secure before processing it. It's a critical step in building robust and safe web applications.

**Why Server-Side Validation is Essential:**
Client-side validation (using HTML5 attributes like `required` or JavaScript) can improve user experience by providing immediate feedback. However, it **must not be relied upon for security or data integrity.**
*   Users can disable JavaScript.
*   Malicious users can bypass client-side validation and submit arbitrary data directly to your server (e.g., using tools like cURL or browser developer tools).
**Always validate data on the server-side (in PHP).**

**Common Validation Tasks:**

*   **Checking for Empty/Required Fields:**
    ```php
    <?php
    $username = $_POST['username'] ?? '';
    if (empty(trim($username))) {
        $errors['username'] = "Username is required.";
    }
    ?>
    ```
    *   `trim()`: Removes whitespace from the beginning and end of a string. Useful to prevent users from submitting only spaces.
    *   `empty()`: Checks if a variable is empty. Considers `""`, `0`, `"0"`, `NULL`, `FALSE`, and an empty array as empty.

*   **Validating Data Types:**
    *   `is_numeric()`: Checks if a variable is a number or a numeric string.
    *   `is_int()`: Checks if a variable is an integer.
    *   `is_string()`: Checks if a variable is a string.
    *   `is_bool()`: Checks if a variable is a boolean.
    ```php
    <?php
    $age = $_POST['age'] ?? '';
    if (!is_numeric($age) || $age < 0) {
        $errors['age'] = "Age must be a non-negative number.";
    } else {
        $age = (int)$age; // Optionally cast to integer after validation
    }
    ?>
    ```

*   **Validating String Length (Min/Max):**
    ```php
    <?php
    $password = $_POST['password'] ?? '';
    if (strlen($password) < 8) {
        $errors['password'] = "Password must be at least 8 characters long.";
    } elseif (strlen($password) > 255) {
        $errors['password'] = "Password is too long (max 255 characters).";
    }
    ?>
    ```

*   **Validating Formats:**
    *   **Email:** Use `filter_var()` with `FILTER_VALIDATE_EMAIL`.
        ```php
        <?php
        $email = $_POST['email'] ?? '';
        if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
            $errors['email'] = "Invalid email format.";
        }
        ?>
        ```
    *   **URL:** Use `filter_var()` with `FILTER_VALIDATE_URL`.
        ```php
        <?php
        $website = $_POST['website'] ?? '';
        if (!empty($website) && !filter_var($website, FILTER_VALIDATE_URL)) {
            $errors['website'] = "Invalid URL format.";
        }
        ?>
        ```
    *   **Regular Expressions (`preg_match()`):** For complex patterns (e.g., specific username formats, postal codes).
        ```php
        <?php
        $zipcode = $_POST['zipcode'] ?? '';
        // Example: US ZIP code (5 digits, optionally +4)
        if (!preg_match('/^\d{5}(-\d{4})?$/', $zipcode)) {
            $errors['zipcode'] = "Invalid ZIP code format.";
        }
        ?>
        ```

*   **Validating Against a List of Allowed Values:**
    ```php
    <?php
    $userRole = $_POST['role'] ?? '';
    $allowedRoles = ['editor', 'author', 'subscriber'];
    if (!in_array($userRole, $allowedRoles)) {
        $errors['role'] = "Invalid role selected.";
    }
    ?>
    ```

**Building a Simple Validation System:**

It's common to collect all validation errors in an array.

```php
<?php
$errors = []; // Array to store validation errors

// --- Assume form data is in $_POST ---
$name = $_POST['name'] ?? '';
$email = $_POST['email'] ?? '';
$age = $_POST['age'] ?? '';

// Validate Name
if (empty(trim($name))) {
    $errors['name'] = "Name is required.";
} elseif (strlen($name) > 100) {
    $errors['name'] = "Name cannot exceed 100 characters.";
}

// Validate Email
if (empty($email)) {
    $errors['email'] = "Email is required.";
} elseif (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
    $errors['email'] = "Invalid email format.";
}

// Validate Age
if (empty($age)) {
    $errors['age'] = "Age is required.";
} elseif (!is_numeric($age) || $age < 1 || $age > 120) {
    $errors['age'] = "Age must be a number between 1 and 120.";
}

// Check if there are any errors
if (empty($errors)) {
    // Process the data (e.g., save to database)
    echo "Form submitted successfully!<br>";
    // ... your processing logic ...
} else {
    // Display errors (or pass them back to the form)
    echo "<h3>Please correct the following errors:</h3>";
    echo "<ul>";
    foreach ($errors as $field => $error) {
        echo "<li>" . htmlspecialchars(ucfirst($field)) . ": " . htmlspecialchars($error) . "</li>";
    }
    echo "</ul>";
    // You would typically re-display the form with error messages next to fields
}
?>
```

**Displaying Error Messages to the User:**
Error messages should be clear and displayed near the relevant form fields to help the user correct them. (See "Sticky Forms" for how to combine this with repopulating the form).

## 4. Data Sanitization

Data sanitization is the process of cleaning or filtering user input to remove or escape potentially malicious characters or code. This is crucial for preventing security vulnerabilities.

**Importance:**

*   **Preventing Cross-Site Scripting (XSS):** XSS occurs when malicious scripts are injected into web pages viewed by other users. If you display user input directly in HTML without sanitization, an attacker could input `<script>alert('XSS');</script>`, which would then execute in other users' browsers.
*   **Preventing SQL Injection (SQLi):** SQLi occurs when attackers manipulate input to alter SQL queries executed against your database. (This will be covered in more detail in the database section, but proper input handling starts here).
*   **Preventing Other Injection Attacks:** Such as command injection if user input is used in system commands (highly discouraged).

**Sanitizing for HTML Output (Preventing XSS):**

*   **`htmlspecialchars($string, $flags = ENT_QUOTES | ENT_SUBSTITUTE | ENT_HTML401, $encoding = 'UTF-8')`:**
    *   Converts special HTML characters into their HTML entity equivalents.
    *   `&` (ampersand) becomes `&amp;`
    *   `"` (double quote) becomes `&quot;` (if `ENT_QUOTES` is set)
    *   `'` (single quote) becomes `&apos;` (or `&#039;`) (if `ENT_QUOTES` is set)
    *   `<` (less than) becomes `&lt;`
    *   `>` (greater than) becomes `&gt;`
    *   This is the **most common and recommended function** for preventing XSS when displaying user input in an HTML context.
    *   **Flags:**
        *   `ENT_QUOTES`: Converts both double and single quotes.
        *   `ENT_SUBSTITUTE`: Replaces invalid code unit sequences with a Unicode Replacement Character (U+FFFD) instead of returning an empty string (PHP 5.4+).
        *   `ENT_HTML401`, `ENT_HTML5`, `ENT_XML1`, `ENT_XHTML`: Specify the document type.

    ```php
    <?php
    $userInput = '<script>alert("XSS Attack!");</script>';
    $safeOutput = htmlspecialchars($userInput, ENT_QUOTES, 'UTF-8');
    echo $safeOutput; // Output: &lt;script&gt;alert(&quot;XSS Attack!&quot;);&lt;/script&gt;
                      // This will be displayed as text, not executed as a script.
    ?>
    ```

*   **`htmlentities($string, $flags = ENT_QUOTES | ENT_SUBSTITUTE | ENT_HTML401, $encoding = 'UTF-8')`:**
    *   Similar to `htmlspecialchars()`, but it converts **all** characters that have HTML entity equivalents, not just the special ones (`<`, `>`, `&`, etc.). For example, it would convert `©` to `&copy;`.
    *   Can be more aggressive than necessary for general XSS prevention. `htmlspecialchars()` is usually sufficient.

*   **`strip_tags($string, $allowed_tags = null)`:**
    *   Removes HTML and PHP tags from a string.
    *   You can optionally specify a string of allowed tags (e.g., `strip_tags($input, '<p><a><b>')`).
    *   **Use with caution.** It can break legitimate markup if users are allowed to submit some HTML. It's also not foolproof for XSS prevention on its own, as attackers can use attributes or malformed HTML. `htmlspecialchars()` is generally safer for displaying data.
    *   Might be useful if you want to get plain text content from user input that might contain HTML.

**Sanitizing for Database Input:**
*   When working with databases, the primary defense against SQL Injection is using **prepared statements** with placeholders (e.g., with PDO or MySQLi). This separates the SQL query structure from the data.
*   While input validation (checking types, lengths) is still important, `htmlspecialchars()` is **not** the correct function for database sanitization.
*   Specific database escaping functions (like `mysqli_real_escape_string()`) exist but are generally less preferred and more error-prone than prepared statements. (More in the Database section).

**Sanitizing for Other Contexts:**
*   **File System:** Be extremely careful if user input is used in file paths or names. Use functions like `basename()` to prevent directory traversal attacks (e.g., `../../etc/passwd`). Whitelist allowed characters.
*   **Shell Commands:** **Avoid using user input in shell commands if at all possible.** If absolutely necessary, use `escapeshellcmd()` and `escapeshellarg()` with extreme caution and thorough validation. This is a high-risk area.

**General Rule:** Sanitize data appropriately for the *context* in which it will be used. Data safe for HTML output might not be safe for a SQL query, and vice-versa.

## 5. Sticky Forms (Repopulating Form Fields)

A "sticky form" is a form that, upon submission (especially if there are validation errors), redisplays with the values the user previously entered. This improves user experience as users don't have to re-enter everything.

**How to Implement:**
You achieve this by setting the `value` attribute of input fields (or the `checked` / `selected` attributes for checkboxes/radios/selects) to the previously submitted data. Remember to use `htmlspecialchars()` on any data you output back into form fields to prevent XSS.

**Example:**

```php
<?php
// Assume validation code from earlier, populating $errors and $_POST data
$name = $_POST['name'] ?? '';
$email = $_POST['email'] ?? '';
$age = $_POST['age'] ?? '';
$selectedColor = $_POST['color'] ?? ''; // For select
$isSubscribed = isset($_POST['subscribe']); // For checkbox
$gender = $_POST['gender'] ?? ''; // For radio

$errors = []; // Assume this gets populated by validation logic
// Example validation:
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (empty(trim($name))) $errors['name'] = "Name is required.";
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) $errors['email'] = "Invalid email.";
    // ... more validation
}
?>
<!DOCTYPE html>
<html>
<head>
    <title>Sticky Form</title>
    <style> .error { color: red; font-size: 0.9em; } </style>
</head>
<body>
    <h2>User Registration</h2>
    <?php if ($_SERVER["REQUEST_METHOD"] == "POST" && !empty($errors)): ?>
        <div style="color: red; border: 1px solid red; padding: 10px; margin-bottom: 15px;">
            <strong>Please correct the errors:</strong>
            <ul>
                <?php foreach ($errors as $error): ?>
                    <li><?php echo htmlspecialchars($error); ?></li>
                <?php endforeach; ?>
            </ul>
        </div>
    <?php elseif ($_SERVER["REQUEST_METHOD"] == "POST" && empty($errors)): ?>
        <div style="color: green; border: 1px solid green; padding: 10px; margin-bottom: 15px;">
            Form submitted successfully!
        </div>
    <?php endif; ?>

    <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="POST">
        <div>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="<?php echo htmlspecialchars($name); ?>">
            <?php if (isset($errors['name'])): ?><span class="error"><?php echo htmlspecialchars($errors['name']); ?></span><?php endif; ?>
        </div><br>

        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="<?php echo htmlspecialchars($email); ?>">
            <?php if (isset($errors['email'])): ?><span class="error"><?php echo htmlspecialchars($errors['email']); ?></span><?php endif; ?>
        </div><br>

        <div>
            <label for="age">Age:</label>
            <input type="number" id="age" name="age" value="<?php echo htmlspecialchars($age); ?>">
            <?php if (isset($errors['age'])): ?><span class="error"><?php echo htmlspecialchars($errors['age']); ?></span><?php endif; ?>
        </div><br>

        <div>
            <label for="color">Favorite Color:</label>
            <select id="color" name="color">
                <option value="">--Select Color--</option>
                <option value="red" <?php if ($selectedColor === 'red') echo 'selected'; ?>>Red</option>
                <option value="green" <?php if ($selectedColor === 'green') echo 'selected'; ?>>Green</option>
                <option value="blue" <?php if ($selectedColor === 'blue') echo 'selected'; ?>>Blue</option>
            </select>
            <?php if (isset($errors['color'])): ?><span class="error"><?php echo htmlspecialchars($errors['color']); ?></span><?php endif; ?>
        </div><br>

        <div>
            <input type="checkbox" id="subscribe" name="subscribe" value="yes" <?php if ($isSubscribed) echo 'checked'; ?>>
            <label for="subscribe">Subscribe to newsletter</label>
        </div><br>

        <div>
            <p>Gender:</p>
            <input type="radio" id="male" name="gender" value="male" <?php if ($gender === 'male') echo 'checked'; ?>>
            <label for="male">Male</label>
            <input type="radio" id="female" name="gender" value="female" <?php if ($gender === 'female') echo 'checked'; ?>>
            <label for="female">Female</label>
            <?php if (isset($errors['gender'])): ?><span class="error"><?php echo htmlspecialchars($errors['gender']); ?></span><?php endif; ?>
        </div><br>

        <button type="submit">Register</button>
    </form>
</body>
</html>
```
*   `htmlspecialchars($_SERVER["PHP_SELF"])`: Used as the form action to submit to the current script. `htmlspecialchars` prevents XSS if the `PHP_SELF` variable is manipulated.
*   Values are echoed into `value` attributes.
*   `selected` attribute is added to `<option>` tags if that value was previously chosen.
*   `checked` attribute is added to checkbox/radio inputs if they were previously checked.
*   Error messages are displayed next to their respective fields (a common practice).

## 6. File Uploads

PHP can handle files uploaded via HTML forms. This is commonly used for profile pictures, document sharing, etc.

**HTML Form for File Upload:**

1.  The form's `method` attribute **must be `POST`**.
2.  The form's `enctype` attribute **must be set to `multipart/form-data`**. This encoding type allows files to be sent along with the form data.
3.  Use `<input type="file" name="your_file_input_name">`.

```html
<form action="upload_parser.php" method="POST" enctype="multipart/form-data">
    <label for="profile_pic">Choose a profile picture:</label>
    <input type="file" id="profile_pic" name="profile_picture"><br><br>

    <label for="document">Upload a document:</label>
    <input type="file" id="document" name="user_document"><br><br>

    <button type="submit">Upload Files</button>
</form>
```

### The `$_FILES` Superglobal

When a form with `enctype="multipart/form-data"` is submitted, PHP populates the `$_FILES` superglobal array with information about the uploaded files.

If you have an input like `<input type="file" name="profile_picture">`, the data for this file will be in `$_FILES['profile_picture']`. This itself will be an associative array containing:

*   **`$_FILES['profile_picture']['name']`**: The original name of the file on the client machine (e.g., `my_photo.jpg`). **Trust this only as a hint.**
*   **`$_FILES['profile_picture']['type']`**: The MIME type of the file as provided by the browser (e.g., `image/jpeg`). **Do not trust this for validation; it can be easily spoofed.**
*   **`$_FILES['profile_picture']['tmp_name']`**: The temporary filename where the uploaded file is stored on the server (e.g., `/tmp/phpA1b2C3`). You must move this file to a permanent location if you want to keep it.
*   **`$_FILES['profile_picture']['error']`**: An error code associated with this file upload.
    *   `UPLOAD_ERR_OK` (Value: 0): No error, file uploaded successfully.
    *   `UPLOAD_ERR_INI_SIZE`: File exceeds `upload_max_filesize` directive in `php.ini`.
    *   `UPLOAD_ERR_FORM_SIZE`: File exceeds `MAX_FILE_SIZE` directive specified in the HTML form (if present).
    *   `UPLOAD_ERR_PARTIAL`: File was only partially uploaded.
    *   `UPLOAD_ERR_NO_FILE`: No file was uploaded.
    *   Other error codes exist for more specific issues.
*   **`$_FILES['profile_picture']['size']`**: The size of the uploaded file in bytes.

### Handling Uploaded Files

**`upload_parser.php` example:**

```php
<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {

    // --- Handle 'profile_picture' upload ---
    if (isset($_FILES['profile_picture']) && $_FILES['profile_picture']['error'] == UPLOAD_ERR_OK) {
        $fileInfo = $_FILES['profile_picture'];
        $uploadDir = 'uploads/'; // Create this directory and make it writable by the web server
        
        // Ensure upload directory exists
        if (!is_dir($uploadDir)) {
            mkdir($uploadDir, 0755, true);
        }

        // Sanitize the original filename (basic example)
        $originalName = $fileInfo['name'];
        $safeName = time() . "_" . preg_replace("/[^a-zA-Z0-9._-]/", "", basename($originalName));
        $destination = $uploadDir . $safeName;

        // Validate file type (example: allow only JPEG and PNG)
        $allowedTypes = ['image/jpeg', 'image/png'];
        // For more robust type checking, use finfo_open() or getimagesize()
        $detectedType = mime_content_type($fileInfo['tmp_name']); // Requires fileinfo extension

        if (!in_array($detectedType, $allowedTypes)) {
            echo "Error: Only JPEG and PNG images are allowed for profile picture.<br>";
        } elseif ($fileInfo['size'] > 2 * 1024 * 1024) { // Max 2MB
            echo "Error: Profile picture size exceeds 2MB limit.<br>";
        } else {
            // Move the temporary file to the permanent location
            if (move_uploaded_file($fileInfo['tmp_name'], $destination)) {
                echo "Profile picture '" . htmlspecialchars($safeName) . "' uploaded successfully!<br>";
                echo "Stored at: " . htmlspecialchars($destination) . "<br>";
            } else {
                echo "Error moving uploaded profile picture.<br>";
            }
        }
    } elseif (isset($_FILES['profile_picture']) && $_FILES['profile_picture']['error'] != UPLOAD_ERR_NO_FILE) {
        echo "Error uploading profile picture: Error code " . $_FILES['profile_picture']['error'] . "<br>";
    }


    // --- Handle 'user_document' upload (add similar logic) ---
    if (isset($_FILES['user_document']) && $_FILES['user_document']['error'] == UPLOAD_ERR_OK) {
        // ... (similar validation and move logic for document)
        $docName = htmlspecialchars($_FILES['user_document']['name']);
        echo "Document '{$docName}' received (processing logic not fully implemented here).<br>";
    } elseif (isset($_FILES['user_document']) && $_FILES['user_document']['error'] != UPLOAD_ERR_NO_FILE) {
        echo "Error uploading document: Error code " . $_FILES['user_document']['error'] . "<br>";
    }

} else {
    echo "Please upload files using the form.<br>";
}
?>
```

**Key Steps and Security Considerations for File Uploads:**

1.  **Check for Errors:** Always check `$_FILES['input_name']['error']`.
2.  **`move_uploaded_file($tmp_name, $destination)`:** This function is crucial. It checks if the file specified by `$tmp_name` is a valid uploaded file (preventing attackers from trying to make your script move other files on the server). Then it moves it to `$destination`.
3.  **Validate File Type:**
    *   **DO NOT trust `$_FILES['input_name']['type']`**. This value is sent by the browser and can be easily faked.
    *   Use server-side methods like `mime_content_type($tmp_name)` (requires the `fileinfo` extension) or `getimagesize($tmp_name)` (for images, also returns type) to determine the actual file type from its content.
    *   Maintain a whitelist of allowed MIME types or extensions.
4.  **Validate File Size:** Check `$_FILES['input_name']['size']` against reasonable limits to prevent denial-of-service attacks (filling up server storage).
5.  **Generate Safe Filenames:**
    *   Do not use the user-supplied filename (`$_FILES['input_name']['name']`) directly for storage, as it might contain malicious characters or try to overwrite existing files.
    *   Generate a new, unique filename (e.g., using `uniqid()`, `time()`, or a hash of the file content) or thoroughly sanitize the original name (e.g., `basename()` combined with character whitelisting).
6.  **Secure Upload Directory:**
    *   Ideally, store uploaded files **outside the web root directory** if they don't need to be directly accessible via a URL. You can then serve them via a PHP script that handles authentication and authorization.
    *   If files must be in the web root, disable script execution in the upload directory (e.g., using `.htaccess` for Apache: `php_flag engine off` or `RemoveHandler .php .phtml .php3` and `AddType text/plain .php .phtml .php3`).
    *   Ensure the upload directory is not browsable.
7.  **Permissions:** The directory where files are moved must be writable by the web server user (e.g., `www-data` on Linux).

## 7. Working with Cookies

Cookies are small pieces of data that a web server stores on the client's (user's) browser. The browser then sends these cookies back to the server with subsequent requests to the same domain.

**Use Cases:**

*   Remembering user preferences (e.g., theme, language).
*   Tracking user activity (e.g., items in a shopping cart before login, pages visited).
*   Storing non-sensitive identifiers for session management (though PHP sessions often handle this automatically).

**Setting Cookies: `setcookie()`**

`bool setcookie ( string $name [, string $value = "" [, int $expires = 0 [, string $path = "" [, string $domain = "" [, bool $secure = false [, bool $httponly = false ]]]]]] )`

*   `$name`: The name of the cookie.
*   `$value`: The value of the cookie. This value is stored on the clients computer; do not store sensitive information.
*   `$expires` (Optional): The Unix timestamp when the cookie should expire. If 0 or omitted, the cookie expires at the end of the session (when the browser closes). Use `time() + seconds` for future expiration (e.g., `time() + 3600 * 24 * 30` for 30 days).
*   `$path` (Optional): The path on the server where the cookie will be available. `'/'` means available for the entire domain.
*   `$domain` (Optional): The domain that the cookie is available to (e.g., `.example.com` for all subdomains).
*   `$secure` (Optional): If `true`, the cookie will only be sent over HTTPS connections.
*   `$httponly` (Optional): If `true`, the cookie will not be accessible via JavaScript (helps mitigate XSS attacks trying to steal cookie data). **Highly recommended.**

**Important:** `setcookie()` **must be called before any HTML output is sent to the browser** (before `<html>`, `echo`, etc.) because it modifies HTTP headers.

```php
<?php
// Set a simple cookie
setcookie("username_preference", "JohnDoe", time() + (86400 * 30), "/"); // Expires in 30 days

// Set a secure, httponly cookie
setcookie("user_id", "12345", time() + 3600, "/", "", true, true); // Expires in 1 hour

// This must be before any HTML output
?>
<!DOCTYPE html>
<html>
<head><title>Cookies</title></head>
<body>
<?php
echo "Cookies have been set (or attempted).<br>";
?>
</body>
</html>
```

**Accessing Cookies: `$_COOKIE` Superglobal**

The `$_COOKIE` superglobal is an associative array containing cookies sent by the browser to the script.

```php
<?php
if (isset($_COOKIE['username_preference'])) {
    $usernamePref = htmlspecialchars($_COOKIE['username_preference']);
    echo "Welcome back, " . $usernamePref . "!<br>";
} else {
    echo "No username preference cookie found.<br>";
}

if (isset($_COOKIE['user_id'])) {
    $userId = htmlspecialchars($_COOKIE['user_id']);
    echo "User ID from cookie: " . $userId . "<br>";
}
?>
```
*   Always use `isset()` to check if a cookie exists before trying to access it.
*   Sanitize cookie values with `htmlspecialchars()` if you display them, as they can be modified by the user.

**Modifying/Deleting Cookies:**

*   **Modify:** Call `setcookie()` again with the same name but a new value and/or expiration time.
*   **Delete:** Call `setcookie()` with the same name and an expiration date in the past (e.g., `time() - 3600`).

```php
<?php
// To delete the 'username_preference' cookie
setcookie("username_preference", "", time() - 3600, "/");
// It's good practice to also unset it from the $_COOKIE array for the current script execution
if (isset($_COOKIE['username_preference'])) {
    unset($_COOKIE['username_preference']);
}
echo "Username preference cookie deleted (or attempt made).<br>";
?>
```

**Security Notes for Cookies:**

*   **Client-Side Storage:** Cookies are stored on the user's machine and can be viewed, modified, or deleted by the user.
*   **Don't Store Sensitive Data:** Avoid storing highly sensitive information like passwords or credit card details directly in cookies. Store identifiers that can be used to look up data on the server if needed.
*   **`HttpOnly` Flag:** Use `httponly = true` to prevent JavaScript from accessing the cookie, which helps protect against XSS cookie theft.
*   **`Secure` Flag:** Use `secure = true` to ensure cookies are only transmitted over HTTPS.
*   **Size Limit:** Browsers impose limits on the size and number of cookies per domain (typically around 4KB per cookie).

## 8. Working with Sessions

Sessions provide a way to store information about a user across multiple page requests. Unlike cookies, session data is primarily stored on the **server-side**. A unique session ID is typically stored in a cookie on the client's browser to identify the session.

**How Sessions Work:**

1.  User visits a page that starts a session.
2.  PHP generates a unique Session ID (a long random string).
3.  PHP sends this Session ID back to the browser, usually stored in a cookie (e.g., named `PHPSESSID`).
4.  On subsequent page requests from the user, the browser sends the Session ID cookie back to the server.
5.  PHP uses this ID to retrieve the corresponding session data stored on the server.

**Starting/Resuming a Session: `session_start()`**

`bool session_start ( [ array $options = [] ] )`

*   This function checks if a session ID is present (e.g., from a cookie).
    *   If an ID exists and is valid, it loads the existing session data into the `$_SESSION` superglobal.
    *   If no ID exists or it's invalid, it creates a new session, generates a new session ID, and prepares `$_SESSION` as an empty array.
*   `session_start()` **must be called at the very beginning of your script**, before any output is sent to the browser (similar to `setcookie()`), because it may need to send session cookies.

**Storing Session Data: `$_SESSION` Superglobal**

Once a session is started, you can store and retrieve data using the `$_SESSION` associative array.

```php
<?php
session_start(); // Start or resume the session

// Storing data in the session
$_SESSION['username'] = 'JohnDoe';
$_SESSION['user_role'] = 'administrator';
$_SESSION['login_time'] = time();

echo "Session data set.<br>";
?>
```

**Accessing Session Data:**

```php
<?php
session_start(); // Essential to access session data

if (isset($_SESSION['username'])) {
    $username = htmlspecialchars($_SESSION['username']);
    $role = htmlspecialchars($_SESSION['user_role'] ?? 'guest');
    echo "Welcome, " . $username . "! Your role is: " . $role . ".<br>";

    if (isset($_SESSION['login_time'])) {
        echo "You logged in at: " . date("Y-m-d H:i:s", $_SESSION['login_time']) . "<br>";
    }
} else {
    echo "You are not logged in (no username in session).<br>";
    // You might redirect to a login page here
    // header('Location: login.php');
    // exit;
}
?>
```

**Destroying a Session:**

To completely end a session and remove all its data:

1.  **`session_unset()`**: Frees all session variables currently registered in `$_SESSION`. This clears the `$_SESSION` array.
2.  **`session_destroy()`**: Destroys all data registered to a session on the server. It removes the session file or database entry.
3.  **Optional: Delete the session cookie:** While `session_destroy()` handles server-side data, the session cookie might still exist in the user's browser. To fully log out, it's good practice to also delete this cookie.

```php
<?php
session_start(); // Session must be started to destroy it

// 1. Unset all session variables
$_SESSION = array(); // Or session_unset();

// 2. Destroy the session cookie (optional but good practice for logout)
if (ini_get("session.use_cookies")) {
    $params = session_get_cookie_params();
    setcookie(session_name(), '', time() - 42000,
        $params["path"], $params["domain"],
        $params["secure"], $params["httponly"]
    );
}

// 3. Finally, destroy the session
session_destroy();

echo "Session destroyed. You have been logged out.<br>";
?>
```

**Session Configuration:**
PHP's session behavior can be configured in `php.ini` or using `ini_set()` or `session_set_cookie_params()`. Some common settings:
*   `session.name`: Name of the session cookie (default: `PHPSESSID`).
*   `session.cookie_lifetime`: Lifetime of the session cookie in seconds (0 means until browser closes).
*   `session.gc_maxlifetime`: How long inactive session data is kept on the server (garbage collection).
*   `session.cookie_secure`: Send session cookie only over HTTPS. **Recommended for production.**
*   `session.cookie_httponly`: Make session cookie inaccessible to JavaScript. **Highly recommended.**
*   `session.use_strict_mode`: Prevents PHP from using a session ID supplied by the user if it hasn't been initialized by the server. Good for security.

**Use Cases for Sessions:**
*   **User Login Status:** Storing whether a user is logged in and their user ID.
*   **Shopping Carts:** Keeping track of items a user has added to their cart before checkout.
*   **User Preferences:** Storing temporary user settings for their current visit.
*   **Flash Messages:** Displaying messages across page redirects (e.g., "Item saved successfully!").

Sessions are a powerful tool for creating interactive and personalized web experiences. Always remember to call `session_start()` before you need to access or modify `$_SESSION` data.
