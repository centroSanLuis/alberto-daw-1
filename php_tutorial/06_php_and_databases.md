# PHP and Databases

Most modern web applications require a way to store, retrieve, and manage data persistently. Databases are the backbone for this functionality. This section explores how PHP interacts with databases, focusing on the widely-used PDO extension for secure and flexible database operations.

## 1. Introduction to Databases for Web Applications

### What is a Database?

A **database** is an organized collection of structured information, or data, typically stored electronically in a computer system. It allows for efficient storage, retrieval, management, and updating of data. Think of it as a sophisticated digital filing cabinet.

### Why Use Databases in Web Applications?

Databases are essential for web applications for several reasons:

*   **Persistent Storage:** Web servers and PHP scripts are generally stateless. Once a script finishes executing, any variables or data in memory are lost. Databases provide a way to store information permanently, so it's available across different user sessions and over time (e.g., user accounts, product catalogs, blog posts).
*   **Managing Large Amounts of Data:** Databases are optimized to handle vast quantities of data, far more than can be reasonably managed in text files or in-memory structures.
*   **Data Integrity and Consistency:** Databases can enforce rules and constraints (e.g., data types, uniqueness, relationships) to ensure data accuracy and consistency.
*   **Concurrent Access:** Databases are designed to handle multiple users or processes accessing and modifying data simultaneously without conflicts or corruption.
*   **Efficient Data Retrieval and Querying:** They provide powerful query languages (like SQL) to search, sort, filter, and aggregate data efficiently.
*   **Security:** Databases offer mechanisms for controlling access to data, ensuring that only authorized users can view or modify sensitive information.

### Relational Databases (RDBMS) vs. NoSQL Databases

There are different types of database management systems (DBMS):

*   **Relational Database Management Systems (RDBMS):**
    *   These are the most common type for many web applications.
    *   Data is organized into **tables** (similar to spreadsheets).
    *   Tables have predefined **columns** (attributes with specific data types) and **rows** (records or individual entries).
    *   Relationships between tables are established using **keys**.
    *   They use **SQL (Structured Query Language)** for data definition and manipulation.
    *   Examples: MySQL, MariaDB, PostgreSQL, Microsoft SQL Server, Oracle, SQLite.
    *   **Focus of this tutorial:** We will primarily focus on RDBMS, specifically MySQL/MariaDB syntax, as it's very common in PHP development.

*   **NoSQL Databases ("Not Only SQL"):**
    *   Offer more flexible data models than the rigid structure of RDBMS.
    *   Can be document-based (e.g., MongoDB, CouchDB - storing data in JSON-like documents), key-value stores (e.g., Redis, Memcached), column-family stores (e.g., Cassandra), or graph databases (e.g., Neo4j).
    *   Often chosen for applications with very large datasets, high scalability needs, or rapidly evolving data structures.
    *   While powerful, they are beyond the scope of this introductory section.

### Basic SQL Concepts (for RDBMS)

*   **Tables:** A collection of related data entries, consisting of columns and rows. For example, a `users` table or a `products` table.
*   **Columns (Fields/Attributes):** A vertical entity in a table that contains all information associated with a specific field. Each column has a name and a data type (e.g., `INT` for integer, `VARCHAR` for strings, `DATETIME` for dates). Example columns in a `users` table: `id`, `username`, `email`, `password_hash`, `registration_date`.
*   **Rows (Records/Tuples):** A horizontal entity in a table representing a single data item or record. Each row contains a value for each column. Example row in `users` table: `(1, 'johndoe', 'john.doe@example.com', 'hashed_password_here', '2023-01-15 10:00:00')`.
*   **Primary Key (PK):** A column (or a set of columns) in a table that uniquely identifies each row. Values in the primary key column must be unique and usually cannot be null. Often, this is an auto-incrementing integer like `id`.
*   **Foreign Key (FK):** A column (or a set of columns) in one table that references the primary key of another table. Foreign keys are used to establish and enforce links (relationships) between tables. For example, an `orders` table might have a `user_id` column that is a foreign key referencing the `id` column in the `users` table.

### Common RDBMS for PHP

*   **MySQL:** The most popular open-source RDBMS, widely used with PHP. Known for its ease of use and good performance. (Oracle product)
*   **MariaDB:** A community-developed fork of MySQL, created by the original developers of MySQL. It aims to be a drop-in replacement for MySQL with more features and better performance in some cases. Highly compatible with MySQL.
*   **PostgreSQL:** A powerful, open-source object-relational database system with a strong reputation for reliability, feature robustness, and data integrity.
*   **SQLite:** A self-contained, serverless, zero-configuration, transactional SQL database engine. The database is stored in a single file, making it very portable and easy to use for smaller applications or for development/testing.

**This tutorial will use syntax and examples generally applicable to MySQL and MariaDB, accessed via PHP's PDO extension.**

## 2. Connecting to a Database with PHP

PHP needs a way to communicate with the database server. This is done using database extensions.

### Choosing a PHP Extension

There are several ways to connect to databases in PHP:

*   **PDO (PHP Data Objects):**
    *   **This is the recommended approach for most new PHP projects.**
    *   PDO provides a **consistent, object-oriented API** for accessing various database systems (MySQL, PostgreSQL, SQLite, MS SQL Server, Oracle, etc.). This means if you switch your database backend, you only need to change the connection string and possibly some SQL queries (if they use database-specific features), but your PHP data access code (fetching, binding) remains largely the same.
    *   Supports **prepared statements**, which are crucial for preventing SQL injection vulnerabilities.
    *   **Enabling PDO:** PDO and its specific database drivers (e.g., `pdo_mysql`, `pdo_pgsql`) are usually enabled by default in modern PHP installations. If not, you might need to uncomment or add lines like `extension=pdo.so` and `extension=pdo_mysql.so` (or `.dll` on Windows) in your `php.ini` file and restart your web server.

*   **MySQLi (MySQL Improved Extension):**
    *   Specifically designed for working with **MySQL (and MariaDB) databases.**
    *   Offers both a **procedural style** (similar to the older `mysql_*` functions, which are now deprecated and removed) and an **object-oriented style**.
    *   Also supports prepared statements.
    *   If you are *certain* you will only ever use MySQL/MariaDB, MySQLi is a viable option. However, PDO offers better portability.
    *   We will briefly mention it but focus our examples on PDO.

*   `mysql_*` functions (e.g., `mysql_connect()`, `mysql_query()`):
    *   These are **deprecated** as of PHP 5.5.0 and **removed** as of PHP 7.0.0.
    *   **Do not use them in new code.** They lack support for prepared statements and other modern database features, making them insecure and outdated.

### Connecting with PDO

Connecting with PDO involves creating an instance of the `PDO` class.

**Data Source Name (DSN):**
The DSN is a string that specifies the database type, host, database name, and other connection parameters.

*   **For MySQL/MariaDB:**
    `"mysql:host=your_host;dbname=your_database_name;charset=your_charset"`
    *   `mysql`: Specifies the PDO driver to use.
    *   `host`: The hostname or IP address of your database server (e.g., `localhost` or `127.0.0.1` if on the same machine).
    *   `dbname`: The name of the database you want to connect to.
    *   `charset`: The character set to use for the connection. `utf8mb4` is highly recommended for supporting a wide range of characters, including emojis.

    Example: `"mysql:host=localhost;dbname=my_app_db;charset=utf8mb4"`

*   **For PostgreSQL:**
    `"pgsql:host=your_host;dbname=your_database_name;options='--client_encoding=UTF8'"` (Charset might be handled differently)

*   **For SQLite:**
    `"sqlite:/path/to/your/database/file.sqlite"`
    Or for an in-memory database (useful for testing): `"sqlite::memory:"`

**Creating a PDO Instance:**

You create a new `PDO` object by passing the DSN, database username, database password, and optionally an array of driver options.

```php
<?php
$db_host = 'localhost';     // Database host
$db_name = 'my_blog';       // Database name
$db_user = 'blog_user';     // Database username
$db_pass = 'P@$$wOrd';      // Database password
$charset = 'utf8mb4';       // Preferred charset

// DSN (Data Source Name)
$dsn = "mysql:host=$db_host;dbname=$db_name;charset=$charset";

// PDO Connection Options
$options = [
    PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION, // Throw exceptions on errors
    PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,       // Fetch results as associative arrays
    PDO::ATTR_EMULATE_PREPARES   => false,                  // Disable emulated prepared statements for security
];

try {
    // Create a PDO instance (connect to the database)
    $pdo = new PDO($dsn, $db_user, $db_pass, $options);
    echo "Connected to the database successfully!<br>";
    // You can now use the $pdo object to interact with the database
} catch (\PDOException $e) {
    // Handle connection errors
    echo "Connection failed: " . $e->getMessage() . "<br>";
    // In a real application, you might log this error and display a user-friendly message
    // throw new \PDOException($e->getMessage(), (int)$e->getCode()); // Optional: re-throw exception
    die("Database connection error. Please try again later."); // Or exit gracefully
}
?>
```

**Explanation of Options:**

*   **`PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION`**:
    *   This is a **highly recommended** setting. It tells PDO to throw exceptions of type `PDOException` whenever a database error occurs.
    *   This allows you to catch these exceptions using `try...catch` blocks for robust error handling.
    *   Other error modes are `PDO::ERRMODE_SILENT` (default, just sets error codes, you must check manually) and `PDO::ERRMODE_WARNING` (emits E_WARNING). `ERRMODE_EXCEPTION` is generally the most practical.

*   **`PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC`**:
    *   Sets the default way PDO will return rows from the database when you fetch data.
    *   `PDO::FETCH_ASSOC`: Returns rows as associative arrays (keys are column names).
        `['username' => 'johndoe', 'email' => 'john@example.com']`
    *   Other common modes:
        *   `PDO::FETCH_OBJ`: Returns rows as anonymous objects.
          `$row->username`, `$row->email`
        *   `PDO::FETCH_NUM`: Returns rows as numerically indexed arrays.
          `$row[0]`, `$row[1]`
        *   `PDO::FETCH_BOTH` (default if not set): Returns rows as arrays indexed by both column name and number. This can be redundant.
    *   You can override this default fetch mode when calling specific fetch methods.

*   **`PDO::ATTR_EMULATE_PREPARES => false`**:
    *   When set to `false`, PDO uses native prepared statements provided by the database driver. This is generally more secure and sometimes more efficient.
    *   If set to `true` (can be default in older PHP versions or some drivers), PDO emulates prepared statements, which might not offer the same level of security against SQL injection in all edge cases. **It's best to set this to `false` if your driver supports native prepared statements (most modern drivers do).**

**Error Handling with `try...catch`:**
When `PDO::ERRMODE_EXCEPTION` is set, any database operation (connection, query execution) that fails will throw a `PDOException`. You should wrap your database code in `try...catch` blocks to handle these potential errors gracefully.

```php
<?php
// ... (DSN and credentials from above) ...
try {
    $pdo = new PDO($dsn, $db_user, $db_pass, $options);
    // ... perform database operations ...
} catch (\PDOException $e) {
    error_log("Database Error: " . $e->getMessage()); // Log the detailed error
    // Display a generic message to the user
    die("A database error occurred. Please try again later or contact support.");
}
?>
```

## 3. Performing CRUD Operations with PDO

CRUD stands for Create, Read, Update, and Delete – the four basic functions of persistent storage.

**The Golden Rule: Use Prepared Statements**
For any SQL query that involves user-supplied data (or any data that might change), **always use prepared statements**. This is the most effective way to prevent SQL injection attacks.

A prepared statement is a pre-compiled SQL query template. You send the query structure to the database first, and then you send the data separately. The database treats the data purely as data, not as executable SQL code.

### C - Create (INSERT)

To add new records (rows) to a table, you use the SQL `INSERT` statement.

**Steps:**

1.  **Prepare the SQL `INSERT` statement:** Use placeholders (e.g., `?` or named placeholders like `:username`) for the values you want to insert.
    `INSERT INTO users (username, email, password_hash) VALUES (?, ?, ?)`
    `INSERT INTO users (username, email, password_hash) VALUES (:uname, :email, :pword)`
2.  **Create a prepared statement object:** Call `$pdo->prepare($sql)`. This returns a `PDOStatement` object.
3.  **Bind values/parameters to the placeholders:**
    *   **Anonymous placeholders (`?`):** Bind values in the order they appear in the query.
    *   **Named placeholders (`:name`):** Bind values to specific placeholder names. This is often more readable.
    You can bind values using:
    *   `$stmt->bindParam($placeholder, $variable, $dataType)`: Binds a PHP variable to the placeholder. If the variable's value changes before `execute()`, the new value is used.
    *   `$stmt->bindValue($placeholder, $value, $dataType)`: Binds a specific value to the placeholder directly.
    *   Or, pass an array of values directly to the `$stmt->execute()` method. This is often the most concise way for simple inserts.
4.  **Execute the prepared statement:** Call `$stmt->execute()`. For named placeholders, the array passed to `execute()` should be associative. For `?` placeholders, it should be a numerically indexed array.
5.  **Get the ID of the last inserted row (optional):** If your table has an auto-incrementing primary key, you can get its value using `$pdo->lastInsertId()`.

**Example using `?` placeholders and `execute()` with an array:**

```php
<?php
// Assume $pdo is your connected PDO object from the previous section
// And a 'users' table: id (INT, PK, AI), username (VARCHAR), email (VARCHAR), registration_date (DATETIME)

$username = "janedoe";
$email = "jane.doe@example.com";
$registration_date = date('Y-m-d H:i:s'); // Current date and time

try {
    $sql = "INSERT INTO users (username, email, registration_date) VALUES (?, ?, ?)";
    $stmt = $pdo->prepare($sql);

    // Execute the statement by passing an array of values
    $stmt->execute([$username, $email, $registration_date]);

    $lastId = $pdo->lastInsertId();
    echo "New user created successfully! User ID: " . $lastId . "<br>";

} catch (\PDOException $e) {
    echo "Error creating user: " . $e->getMessage() . "<br>";
}
?>
```

**Example using named placeholders:**

```php
<?php
// Assume $pdo is connected
$productName = "Awesome Gadget";
$price = 99.99;
$description = "The most awesome gadget you'll ever own!";

try {
    $sql = "INSERT INTO products (name, price, description) VALUES (:name, :price, :desc)";
    $stmt = $pdo->prepare($sql);

    // Bind values using an associative array in execute()
    $stmt->execute([
        ':name' => $productName,
        ':price' => $price,
        ':desc' => $description
    ]);
    // Or bind individually (more verbose for this case):
    // $stmt->bindValue(':name', $productName);
    // $stmt->bindValue(':price', $price);
    // $stmt->bindValue(':desc', $description);
    // $stmt->execute();

    $lastProductId = $pdo->lastInsertId();
    echo "New product added! Product ID: " . $lastProductId . "<br>";

} catch (\PDOException $e) {
    echo "Error adding product: " . $e->getMessage() . "<br>";
}
?>
```

### R - Read (SELECT)

To retrieve data from the database, you use the SQL `SELECT` statement.

**Fetching Data:**

*   **`PDO::query($sql)` (For simple queries WITHOUT user input):**
    *   You can use `$pdo->query($sql)` for direct queries that do **not** include any variable parts or user input.
    *   It immediately executes the SQL and returns a `PDOStatement` object, or `false` on failure (if error mode isn't `ERRMODE_EXCEPTION`).
    *   **Use with extreme caution.** If the query string is built with user input, this method is vulnerable to SQL injection.
    ```php
    // Example: Fetch all users (ONLY if no user input is involved)
    // try {
    //     $stmt = $pdo->query("SELECT id, username, email FROM users");
    //     while ($row = $stmt->fetch()) { // Default fetch mode (e.g., PDO::FETCH_ASSOC)
    //         echo "User ID: " . $row['id'] . ", Username: " . $row['username'] . "<br>";
    //     }
    // } catch (\PDOException $e) {
    //     echo "Error fetching users: " . $e->getMessage() . "<br>";
    // }
    ```

*   **`SELECT` with Prepared Statements (Recommended):**
    This is the standard and secure way, especially if your `SELECT` query has a `WHERE` clause involving user input.

**Steps for `SELECT` with prepared statements:**

1.  **Prepare the SQL `SELECT` statement:** Use placeholders for any variable parts (e.g., in a `WHERE` clause).
    `SELECT id, username, email FROM users WHERE id = ?`
    `SELECT * FROM products WHERE category = :category AND price < :max_price`
2.  **Create a prepared statement object:** `$stmt = $pdo->prepare($sql);`
3.  **Bind values to the placeholders (if any):** Use `$stmt->bindValue()`, `$stmt->bindParam()`, or pass an array to `execute()`.
4.  **Execute the prepared statement:** `$stmt->execute()`.
5.  **Fetch the results:**
    *   `$stmt->fetch($fetchStyle)`: Fetches the next single row from the result set. Returns `false` if there are no more rows.
        *   `$fetchStyle` (optional): Overrides the default fetch style (e.g., `PDO::FETCH_OBJ`, `PDO::FETCH_NUM`).
    *   `$stmt->fetchAll($fetchStyle)`: Fetches all remaining rows from the result set and returns them as an array of rows.
        *   Be cautious with `fetchAll()` on very large result sets, as it loads everything into memory.
    *   `$stmt->rowCount()`: For `SELECT` statements, `rowCount()` behaviour varies across databases. For MySQL, it typically returns the number of rows *affected* by the last `DELETE`, `INSERT`, or `UPDATE` statement. For `SELECT`, it may not reliably give the number of rows returned until after all rows are fetched, or it might not work as expected with all drivers for `SELECT`. It's often better to check if `fetch()` returns `false` or if `fetchAll()` returns an empty array. With some drivers, it might return the number of rows found by a `SELECT` *after* `fetchAll` or if the statement was prepared with `PDO::CURSOR_SCROLLABLE`.

**Example: Fetching a single user by ID:**

```php
<?php
// Assume $pdo is connected
$userIdToFetch = 1; // Example user ID (could come from $_GET['id'])

try {
    $sql = "SELECT id, username, email, registration_date FROM users WHERE id = ?";
    $stmt = $pdo->prepare($sql);
    $stmt->execute([$userIdToFetch]);

    // Fetch a single row
    $user = $stmt->fetch(); // Uses default fetch mode (e.g., PDO::FETCH_ASSOC)

    if ($user) {
        echo "User Found:<br>";
        echo "ID: " . htmlspecialchars($user['id']) . "<br>";
        echo "Username: " . htmlspecialchars($user['username']) . "<br>";
        echo "Email: " . htmlspecialchars($user['email']) . "<br>";
        echo "Registered: " . htmlspecialchars($user['registration_date']) . "<br>";
    } else {
        echo "User with ID " . htmlspecialchars($userIdToFetch) . " not found.<br>";
    }

} catch (\PDOException $e) {
    echo "Error fetching user: " . $e->getMessage() . "<br>";
}
?>
```

**Example: Fetching all products in a category:**

```php
<?php
// Assume $pdo is connected
$category = 'electronics';

try {
    $sql = "SELECT name, price FROM products WHERE category = :cat ORDER BY price DESC";
    $stmt = $pdo->prepare($sql);
    $stmt->execute([':cat' => $category]);

    // Fetch all matching rows
    $products = $stmt->fetchAll(); // Uses default fetch mode

    if ($products) {
        echo "Products in '" . htmlspecialchars($category) . "':<br>";
        echo "<ul>";
        foreach ($products as $product) {
            echo "<li>" . htmlspecialchars($product['name']) . " - $" . htmlspecialchars($product['price']) . "</li>";
        }
        echo "</ul>";
    } else {
        echo "No products found in category '" . htmlspecialchars($category) . "'.<br>";
    }

} catch (\PDOException $e) {
    echo "Error fetching products: " . $e->getMessage() . "<br>";
}
?>
```

### U - Update (UPDATE)

To modify existing records in a table, you use the SQL `UPDATE` statement.

**Steps:**

1.  **Prepare the SQL `UPDATE` statement:** Use placeholders for values in the `SET` clause and the `WHERE` clause.
    `UPDATE users SET email = ?, last_login = ? WHERE id = ?`
    `UPDATE products SET price = :new_price, stock_quantity = :stock WHERE product_id = :id`
2.  **Create a prepared statement object:** `$stmt = $pdo->prepare($sql);`
3.  **Bind values to all placeholders.**
4.  **Execute the prepared statement:** `$stmt->execute()`.
5.  **Check affected rows (optional):** `$stmt->rowCount()` can tell you how many rows were changed by the `UPDATE` statement.

**Example:**

```php
<?php
// Assume $pdo is connected
$userIdToUpdate = 1;
$newEmail = "john.doe.updated@example.com";
$lastLoginTime = date('Y-m-d H:i:s');

try {
    $sql = "UPDATE users SET email = :email, last_login = :last_login WHERE id = :id";
    $stmt = $pdo->prepare($sql);

    $stmt->execute([
        ':email' => $newEmail,
        ':last_login' => $lastLoginTime,
        ':id' => $userIdToUpdate
    ]);

    $affectedRows = $stmt->rowCount();
    if ($affectedRows > 0) {
        echo "User ID " . htmlspecialchars($userIdToUpdate) . " updated successfully. " . $affectedRows . " row(s) affected.<br>";
    } else {
        echo "No user found with ID " . htmlspecialchars($userIdToUpdate) . " or data was the same.<br>";
    }

} catch (\PDOException $e) {
    echo "Error updating user: " . $e->getMessage() . "<br>";
}
?>
```

### D - Delete (DELETE)

To remove records from a table, you use the SQL `DELETE` statement.

**Steps:**

1.  **Prepare the SQL `DELETE` statement:** Use placeholders in the `WHERE` clause to specify which records to delete. **Be very careful with `DELETE` statements; without a `WHERE` clause, you will delete all rows in the table!**
    `DELETE FROM users WHERE id = ?`
    `DELETE FROM logs WHERE log_date < :cutoff_date`
2.  **Create a prepared statement object:** `$stmt = $pdo->prepare($sql);`
3.  **Bind values to the placeholders in the `WHERE` clause.**
4.  **Execute the prepared statement:** `$stmt->execute()`.
5.  **Check affected rows (optional):** `$stmt->rowCount()` can tell you how many rows were deleted.

**Example:**

```php
<?php
// Assume $pdo is connected
$userIdToDelete = 2; // Example user ID to delete

try {
    $sql = "DELETE FROM users WHERE id = :id";
    $stmt = $pdo->prepare($sql);

    $stmt->execute([':id' => $userIdToDelete]);

    $affectedRows = $stmt->rowCount();
    if ($affectedRows > 0) {
        echo "User ID " . htmlspecialchars($userIdToDelete) . " deleted successfully. " . $affectedRows . " row(s) affected.<br>";
    } else {
        echo "No user found with ID " . htmlspecialchars($userIdToDelete) . " to delete.<br>";
    }

} catch (\PDOException $e) {
    echo "Error deleting user: " . $e->getMessage() . "<br>";
}
?>
```

## 4. Prepared Statements and SQL Injection

### What is SQL Injection?

SQL Injection (SQLi) is a common and dangerous web application vulnerability. It occurs when an attacker is able to insert or "inject" malicious SQL code into input fields, which then gets executed as part of your application's database queries.

**Consequences:**

*   Bypassing authentication.
*   Reading sensitive data (e.g., user credentials, personal information, credit card numbers).
*   Modifying or deleting data.
*   Gaining administrative control over the database server.
*   Executing operating system commands (in some configurations).

**How it Happens (Vulnerable Code Example):**

Imagine you have code that builds a query by concatenating user input directly:

```php
// --- VULNERABLE CODE - DO NOT USE ---
// Assume $pdo is connected
$username_input = $_POST['username']; // e.g., 'admin'
$password_input = $_POST['password']; // e.g., user inputs: 'password' OR '1'='1'

// Vulnerable query construction:
// $sql = "SELECT * FROM users WHERE username = '" . $username_input . "' AND password = '" . $password_input . "'";
// $stmt = $pdo->query($sql); // Directly executing the concatenated query
// $user = $stmt->fetch();

// If $username_input is 'admin' and $password_input is " ' OR '1'='1 ", the SQL becomes:
// SELECT * FROM users WHERE username = 'admin' AND password = '' OR '1'='1'
// The '1'='1' is always true, so the WHERE clause becomes true, potentially logging the attacker in as admin.
// --- END VULNERABLE CODE ---
```
In the example above, if an attacker enters `' OR '1'='1` into the password field, they can bypass the password check.

### How Prepared Statements Prevent SQL Injection

Prepared statements work by separating the SQL query structure (the template) from the data (the values to be inserted or compared).

1.  **Prepare:** The SQL query template with placeholders is sent to the database server first. The server parses this template and plans how to execute it, without seeing the actual data values yet.
    `SELECT * FROM users WHERE username = :uname AND password = :pword`
2.  **Bind & Execute:** The data values are then sent to the server separately. The database engine takes these values and uses them in the pre-compiled template. **Crucially, these values are treated strictly as data, not as executable SQL code.** Even if the data contains SQL-like syntax (e.g., `OR '1'='1'`), it will be treated as a literal string to be compared, not as part of the SQL command.

This separation makes it virtually impossible for an attacker to alter the structure or logic of the original SQL query using input fields.

**Secure Code with Prepared Statements (Counterpart to Vulnerable Example):**

```php
<?php
// --- SECURE CODE ---
// Assume $pdo is connected and in ERRMODE_EXCEPTION
$username_input = $_POST['username'] ?? '';
$password_input = $_POST['password'] ?? ''; // In a real app, you'd compare hashed passwords

try {
    $sql = "SELECT id, username FROM users WHERE username = :uname AND password_hash = :pword_hash"; // Compare against a hashed password
    $stmt = $pdo->prepare($sql);

    // Example: Assume $hashed_password_from_db is fetched based on username first,
    // or you are hashing $password_input to compare. For simplicity here, we're just showing binding.
    // In a real login, you would typically:
    // 1. Fetch user by username.
    // 2. If user exists, use password_verify($password_input, $user['password_hash']).
    // This example primarily demonstrates binding for the WHERE clause.

    $stmt->execute([
        ':uname' => $username_input,
        ':pword_hash' => hash_function($password_input) // Placeholder for actual password hashing
    ]);
    $user = $stmt->fetch();

    if ($user) {
        echo "Login successful (User: " . htmlspecialchars($user['username']) . ")<br>";
        // Set session variables, etc.
    } else {
        echo "Login failed: Invalid username or password.<br>";
    }

} catch (\PDOException $e) {
    echo "Database error: " . $e->getMessage() . "<br>";
}

function hash_function($password) {
    // Placeholder for a strong hashing algorithm like password_hash()
    // return password_hash($password, PASSWORD_DEFAULT);
    return "hashed_" . $password; // Simplified for example
}
?>
```
Even if `$username_input` was `admin' --` or `$password_input` was `' OR '1'='1`, these values would be treated as literal strings when searching for a username or password hash, not as SQL commands.

**Always use prepared statements whenever user input or any variable data is part of your SQL query, especially in `WHERE`, `INSERT`, or `UPDATE` clauses.**

## 5. Working with Result Sets

After executing a `SELECT` query, you often need to process the rows returned.

### Looping Through Results from `fetchAll()`

`fetchAll()` retrieves all rows into an array, making it easy to loop with `foreach`.

```php
<?php
// Assume $pdo is connected
try {
    $stmt = $pdo->query("SELECT id, name, email FROM contacts LIMIT 10"); // Simple query, no user input
    $contacts = $stmt->fetchAll(PDO::FETCH_OBJ); // Fetch all as objects

    if ($contacts) {
        echo "Contacts:<br>";
        foreach ($contacts as $contact) {
            echo "ID: " . htmlspecialchars($contact->id) .
                 " - Name: " . htmlspecialchars($contact->name) .
                 " - Email: " . htmlspecialchars($contact->email) . "<br>";
        }
    } else {
        echo "No contacts found.<br>";
    }
} catch (\PDOException $e) {
    echo "Error: " . $e->getMessage() . "<br>";
}
?>
```

### Looping with `fetch()` in a `while` Loop

For very large result sets, fetching one row at a time with `fetch()` inside a `while` loop can be more memory-efficient than `fetchAll()`.

```php
<?php
// Assume $pdo is connected
try {
    $sql = "SELECT product_name, category FROM products WHERE stock_quantity > :min_stock";
    $stmt = $pdo->prepare($sql);
    $stmt->execute([':min_stock' => 0]);

    echo "Available Products:<br>";
    // Fetch rows one by one using the default fetch mode (e.g., PDO::FETCH_ASSOC)
    while ($row = $stmt->fetch()) {
        echo "Product: " . htmlspecialchars($row['product_name']) .
             " - Category: " . htmlspecialchars($row['category']) . "<br>";
    }
    // Check if any rows were processed if needed (e.g., by using a counter in the loop)

} catch (\PDOException $e) {
    echo "Error: " . $e->getMessage() . "<br>";
}
?>
```

### Checking if a Query Returned Any Results

*   After `fetchAll()`: Check if the returned array is empty (`empty($results)` or `count($results) > 0`).
*   After `fetch()`: Check if the result of `fetch()` is `false`.
*   `$stmt->rowCount()`: As mentioned, its reliability for `SELECT` row counts varies. It's more reliable for `INSERT`, `UPDATE`, `DELETE`.

## 6. Error Handling in Database Operations

Robust error handling is crucial. With `PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION`, PDO will throw `PDOException`s for database errors.

**Using `try...catch` Blocks:**

Always wrap your database interactions (connection, prepare, execute, fetch) in `try...catch` blocks.

```php
<?php
// Assume $pdo is connected
$newUsername = "test_user";
$newEmail = "test@example.com";

try {
    // Start a transaction if multiple related queries
    // $pdo->beginTransaction();

    $sql = "INSERT INTO users (username, email) VALUES (:username, :email)";
    $stmt = $pdo->prepare($sql);

    $stmt->bindValue(':username', $newUsername);
    $stmt->bindValue(':email', $newEmail);
    // $stmt->bindValue(':email', "not_an_email"); // To trigger a potential constraint violation for testing

    $stmt->execute();
    echo "User registered successfully.<br>";

    // If part of a transaction:
    // $pdo->commit();

} catch (\PDOException $e) {
    // If part of a transaction, roll back
    // if ($pdo->inTransaction()) {
    //     $pdo->rollBack();
    //     echo "Transaction rolled back due to error.<br>";
    // }

    // Log the error (server-side)
    error_log("DATABASE ERROR: " . $e->getMessage() . " in " . $e->getFile() . " on line " . $e->getLine());
    // error_log("SQL executed: " . $sql); // Be careful logging SQL with sensitive data in placeholders

    // Display a user-friendly message
    echo "An error occurred while processing your request. Please try again. (Error code: DB" . $e->getCode() . ")<br>";
    // You might have a mapping of error codes to more specific user messages
    // For example, if $e->getCode() == 23000 (integrity constraint violation for MySQL)
    // if ($e->errorInfo[1] == 1062) { // MySQL duplicate entry error code
    //    echo "This username or email is already taken.<br>";
    // }
}
?>
```

**Key Error Handling Practices:**

*   **Log Detailed Errors:** Store detailed error messages (like `$e->getMessage()`, file, line number) in server-side logs. These are invaluable for debugging. **Do not show detailed SQL errors or stack traces to end-users in a production environment** as they can reveal security vulnerabilities.
*   **Display User-Friendly Messages:** Show generic, helpful messages to the user (e.g., "An error occurred, please try again later.").
*   **Specific Error Codes:** `PDOException::getCode()` or `$e->errorInfo` (an array containing SQLSTATE code, driver-specific error code, and driver-specific error message) can sometimes be used to identify specific types of errors (like duplicate entry, foreign key constraint violation) and provide more targeted feedback to the user if appropriate.

## 7. Transactions (Brief Introduction)

### What are Transactions?

A **transaction** is a sequence of one or more SQL operations that are performed as a single, atomic "unit of work." Either all operations within the transaction are successfully completed and committed to the database, or if any operation fails, the entire transaction is rolled back, and the database is left in its state prior to the transaction.

### ACID Properties (Briefly)

Transactions in robust database systems adhere to ACID properties:

*   **Atomicity:** All changes within a transaction are performed as a single unit. Either all complete successfully, or none of them do (they are rolled back).
*   **Consistency:** A transaction brings the database from one valid state to another. Data integrity constraints are maintained.
*   **Isolation:** Concurrent transactions are isolated from each other. The effect of one transaction is not visible to other incomplete transactions.
*   **Durability:** Once a transaction is committed, its changes are permanent and will survive system failures (e.g., power outages, crashes).

### PDO Transaction Methods

PDO provides methods to control transactions:

*   **`PDO::beginTransaction()`**: Turns off auto-commit mode and starts a new transaction. Subsequent queries will not be permanently saved until `commit()` is called.
*   **`PDO::commit()`**: Makes all changes performed since `beginTransaction()` permanent in the database.
*   **`PDO::rollBack()`**: Discards all changes made since `beginTransaction()`. The database is restored to the state it was in before the transaction started.

### Use Case Example

Imagine transferring money from one bank account to another. This involves two operations:
1.  Decrementing the balance of the sender's account.
2.  Incrementing the balance of the receiver's account.

Both operations must succeed. If one fails (e.g., the sender's account update works, but the receiver's fails), you don't want inconsistent data. Transactions handle this.

```php
<?php
// Assume $pdo is connected
$senderId = 1;
$receiverId = 2;
$amountToTransfer = 50.00;

try {
    // 1. Start the transaction
    $pdo->beginTransaction();

    // 2. Decrement sender's balance
    $sqlDebit = "UPDATE accounts SET balance = balance - :amount WHERE id = :id AND balance >= :amount";
    $stmtDebit = $pdo->prepare($sqlDebit);
    $stmtDebit->execute([':amount' => $amountToTransfer, ':id' => $senderId]);
    $debitAffectedRows = $stmtDebit->rowCount();

    if ($debitAffectedRows == 0) {
        // Sender either doesn't exist or has insufficient funds
        throw new \Exception("Insufficient funds or sender account not found.");
    }

    // 3. Increment receiver's balance
    $sqlCredit = "UPDATE accounts SET balance = balance + :amount WHERE id = :id";
    $stmtCredit = $pdo->prepare($sqlCredit);
    $stmtCredit->execute([':amount' => $amountToTransfer, ':id' => $receiverId]);
    $creditAffectedRows = $stmtCredit->rowCount();

    if ($creditAffectedRows == 0) {
        // Receiver account not found
        throw new \Exception("Receiver account not found.");
    }

    // 4. If both operations were successful, commit the transaction
    $pdo->commit();
    echo "Transfer of $" . htmlspecialchars($amountToTransfer) . " successful!<br>";

} catch (\PDOException $e) {
    // A database error occurred (constraint violation, syntax error, connection issue)
    if ($pdo->inTransaction()) {
        $pdo->rollBack(); // Roll back changes on database error
    }
    error_log("Transfer PDOException: " . $e->getMessage());
    echo "Transfer failed due to a database error. Please try again.<br>";
} catch (\Exception $e) {
    // An application logic error occurred (e.g., insufficient funds)
    if ($pdo->inTransaction()) {
        $pdo->rollBack(); // Roll back changes on application error
    }
    error_log("Transfer Application Exception: " . $e->getMessage());
    echo "Transfer failed: " . htmlspecialchars($e->getMessage()) . "<br>";
}
?>
```
In this example, if either the debit or the credit operation fails (or throws an exception), `rollBack()` is called, ensuring that neither account balance is changed incorrectly. If both succeed, `commit()` makes the changes permanent.

Transactions are crucial for maintaining data integrity in operations that involve multiple dependent steps.

This section provides a solid foundation for working with databases in PHP using PDO. Remember that security (especially preventing SQL injection with prepared statements) and robust error handling are paramount.
