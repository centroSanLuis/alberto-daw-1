# PHP Functions

Functions are a cornerstone of almost any programming language, and PHP is no exception. They allow you to organize your code into manageable, reusable pieces, making your scripts more efficient, readable, and easier to maintain.

## 1. Understanding Functions

### What is a Function?

A **function** is a self-contained block of code that performs a specific task. Think of it as a mini-program within your main program. You define it once, and then you can "call" (execute) it multiple times from different parts of your script.

For example, instead of writing the same lines of code every time you want to greet a user, you can write a `greetUser()` function and simply call it whenever needed.

### Why Use Functions?

Using functions offers several significant advantages:

*   **Modularity:** Functions break down complex problems into smaller, manageable parts. Each function handles a specific piece of logic.
*   **Reusability:** Once a function is defined, it can be used many times throughout your application, or even in different applications. This "write once, use many times" approach saves time and effort.
*   **Readability:** Functions make your code easier to read and understand. Well-named functions describe their purpose, making the overall program flow clearer. Instead of a long, complex script, you have a series of logical steps defined by function calls.
*   **Easier Debugging:** When a problem occurs, functions help isolate the issue. If a function isn't working correctly, you know exactly which part of the code to examine. This is much easier than debugging a large, monolithic script.
*   **Maintainability:** If you need to change how a specific task is performed, you only need to update the code within the relevant function. This change will then apply everywhere the function is called.

## 2. User-Defined Functions

While PHP provides a vast library of built-in functions (which we'll touch upon later), you'll often need to create your own custom functions to suit the specific needs of your application. These are called user-defined functions.

### Defining a Function

**Syntax:**

```php
<?php
function functionName($parameter1, $parameter2, /* ... */) {
    // Code to be executed
    // This block of code is the function's body
    // It can perform actions, calculations, etc.

    return $value; // Optional: returns a value back to the caller
}
?>
```

*   `function`: The keyword that tells PHP you are defining a function.
*   `functionName`: The name you give to your function.
    *   **Naming Conventions:**
        *   Function names must start with a letter or underscore (`_`), followed by any number of letters, numbers, or underscores.
        *   Function names are **case-insensitive** in PHP, but it's good practice to be consistent.
        *   Common conventions include:
            *   `camelCase`: `calculateTotalAmount`, `getUserDetails` (first word lowercase, subsequent words capitalized).
            *   `snake_case`: `calculate_total_amount`, `get_user_details` (all words lowercase, separated by underscores).
        *   **Choose one convention and stick to it** throughout your project for consistency. Many PHP developers prefer camelCase for methods (functions within classes) and snake_case for standalone functions, but consistency within your own codebase is key.
*   `($parameter1, $parameter2, ...)`: An optional list of parameters (also called arguments). These are like placeholders for values that you will pass into the function when you call it. Parameters are separated by commas.
*   `{ ... }`: The curly braces enclose the function's body – the actual code that performs the task.
*   `return $value;`: An optional statement that sends a value back to the part of the script that called the function.

**Example:**

```php
<?php
// A simple function to greet a user
function greetUser($name) {
    echo "Hello, " . $name . "! Welcome to our website.<br>";
}
?>
```

### Calling a Function

Once a function is defined, you can execute it by "calling" it. To call a function, you simply write its name followed by parentheses `()`. If the function expects parameters, you provide values (arguments) inside the parentheses.

**Syntax:**

`functionName($argument1, $argument2, ...);`

**Example (calling the `greetUser` function):**

```php
<?php
function greetUser($name) {
    echo "Hello, " . $name . "! Welcome to our website.<br>";
}

// Calling the function
greetUser("Alice");   // Outputs: Hello, Alice! Welcome to our website.
greetUser("Bob");     // Outputs: Hello, Bob! Welcome to our website.

$username = "Carol";
greetUser($username); // Outputs: Hello, Carol! Welcome to our website.
?>
```

### Parameters (Arguments)

Parameters allow you to pass data into a function, making it more flexible and dynamic. The values passed to a function when it's called are called arguments.

*   **Passing Arguments by Value (Default):**
    By default, PHP passes arguments to functions "by value." This means that a copy of the argument's value is passed to the function. Any changes made to the parameter inside the function do not affect the original variable outside the function.

    ```php
    <?php
    function addFive($number) {
        $number += 5;
        echo "Inside function: " . $number . "<br>"; // e.g., Inside function: 15
    }

    $originalNumber = 10;
    addFive($originalNumber);
    echo "Outside function: " . $originalNumber . "<br>"; // Outputs: Outside function: 10 (original is unchanged)
    ?>
    ```

*   **Passing Arguments by Reference (using `&`):**
    If you want a function to modify its arguments, you can pass them by reference. To do this, you prepend an ampersand (`&`) to the parameter name in the function definition. This passes a reference to the original variable, so changes inside the function will affect the original variable.

    ```php
    <?php
    function addTen(&$number) { // Note the &
        $number += 10;
        echo "Inside function: " . $number . "<br>"; // e.g., Inside function: 25
    }

    $originalValue = 15;
    addTen($originalValue);
    echo "Outside function: " . $originalValue . "<br>"; // Outputs: Outside function: 25 (original IS changed)
    ?>
    ```
    **When to use pass by reference:** Use it when you specifically need the function to alter the original variable. This can be useful for functions that modify large arrays or objects directly to avoid the overhead of copying them, or when a function needs to "return" multiple values by modifying variables passed to it. However, use it judiciously as it can sometimes make code harder to follow if it's not clear that a function modifies its arguments.

*   **Default Parameter Values:**
    You can provide default values for parameters. If a function is called without passing an argument for a parameter with a default value, the default value will be used. Default parameters should always be listed after any non-default parameters.

    ```php
    <?php
    function sendMessage($message, $recipient = "User") {
        echo "Sending message to " . $recipient . ": '" . $message . "'<br>";
    }

    sendMessage("Hello there!");                // Outputs: Sending message to User: 'Hello there!'
    sendMessage("Important update", "Admin"); // Outputs: Sending message to Admin: 'Important update'
    ?>
    ```

*   **Variable-Length Argument Lists:**
    Sometimes you might need a function that can accept a variable number of arguments. PHP provides a few ways to handle this:

    1.  **`func_num_args()` and `func_get_args()` (Older way):**
        *   `func_num_args()`: Returns the number of arguments passed to the function.
        *   `func_get_args()`: Returns an array containing all the arguments passed to the function.

        ```php
        <?php
        function sumAll() {
            $total = 0;
            $numArgs = func_num_args();
            echo "Number of arguments: " . $numArgs . "<br>";
            $args = func_get_args();
            foreach ($args as $arg) {
                $total += $arg;
            }
            return $total;
        }

        echo sumAll(1, 2, 3) . "<br>";       // Number of arguments: 3, Outputs: 6
        echo sumAll(10, 20, 30, 40) . "<br>"; // Number of arguments: 4, Outputs: 100
        ?>
        ```

    2.  **The `...` operator (Variadics - PHP 5.6+):**
        This is the modern and preferred way. You can use the `...` (splat operator) before a parameter name to indicate that the function will accept a variable number of arguments for that parameter. These arguments will be collected into an array.

        ```php
        <?php
        function multiply($multiplier, ...$numbers) { // $numbers will be an array
            echo "Multiplier: " . $multiplier . "<br>";
            print_r($numbers); // Shows the array of numbers
            $product = 1;
            foreach ($numbers as $n) {
                $product *= $n;
            }
            return $product * $multiplier;
        }

        echo multiply(2, 3, 4, 5) . "<br>"; // Multiplier: 2, Array ( [0] => 3 [1] => 4 [2] => 5 ), Outputs: 120
        echo multiply(10, 1, 2) . "<br>";   // Multiplier: 10, Array ( [0] => 1 [1] => 2 ), Outputs: 20
        ?>
        ```
        You can also use the `...` operator to unpack an array or Traversable object into arguments when calling a function: `myFunction(...$arrayOfArgs);`

### Return Values

Functions can send data back to the calling code using the `return` statement.

*   **Using the `return` Statement:**
    When a `return` statement is encountered, the function immediately stops executing, and the specified value is sent back.

    ```php
    <?php
    function add($num1, $num2) {
        $sum = $num1 + $num2;
        return $sum;
        // echo "This line will not be executed."; // Code after return is unreachable
    }

    $result = add(5, 7); // $result will be 12
    echo "The sum is: " . $result . "<br>";
    ?>
    ```

*   **Returning Single Values, Multiple Values:**
    *   **Single Value:** As seen above, a function can return any single value (string, integer, array, object, etc.).
    *   **Multiple Values:** To return multiple values, you typically return them as an array or an object.
        ```php
        <?php
        function getUserDetails($id) {
            // In a real app, you might fetch this from a database
            $name = "Alice";
            $age = 30;
            return [$name, $age]; // Return as an indexed array
        }

        list($userName, $userAge) = getUserDetails(1); // Using list() to unpack the array
        // For PHP 7.1+ you can use shorter array destructuring: [$userName, $userAge] = getUserDetails(1);
        echo "Name: " . $userName . ", Age: " . $userAge . "<br>";

        function getProductInfo($id) {
            return [
                "name" => "Laptop",
                "price" => 1200.00,
                "currency" => "USD"
            ]; // Return as an associative array
        }

        $product = getProductInfo(101);
        echo "Product: " . $product["name"] . ", Price: " . $product["price"] . " " . $product["currency"] . "<br>";
        ?>
        ```

*   **Functions That Don't Return a Value:**
    If a function doesn't have a `return` statement, or if it has a `return;` statement without a value, it implicitly returns `NULL`. Such functions are often used for actions, like printing output directly or modifying global variables.

    ```php
    <?php
    function printMessage($message) {
        echo $message . "<br>";
        // No explicit return, so it returns NULL
    }

    $returnValue = printMessage("Testing void function.");
    var_dump($returnValue); // Outputs: NULL
    ?>
    ```

### Type Hinting (Parameter and Return Types)

PHP 7 introduced type declarations for parameters and return values. This helps in writing more robust code by ensuring that functions receive and return the expected data types.

*   **Scalar Type Hints (PHP 7+):** `int`, `float`, `string`, `bool`.
*   **Class/Interface Type Hints:** You can specify that a parameter must be an instance of a specific class or implement a specific interface.
*   **Array Type Hints:** `array`.
*   **Callable Type Hints:** `callable` (for functions or methods that can be called).
*   **Iterable Type Hints (PHP 7.1+):** `iterable` (can be `array` or an object implementing `Traversable`).
*   **Object Type Hint (PHP 7.2+):** `object`.
*   **Mixed Type Hint (PHP 8.0+):** `mixed` (can be any type).
*   **Union Types (PHP 8.0+):** `int|string|null` (can be one of several types).

**Parameter Type Hinting Example:**

```php
<?php
// declare(strict_types=1); // See "Strict Typing" below

function addNumbers(int $a, int $b) {
    return $a + $b;
}

echo addNumbers(5, 10) . "<br>"; // 15
// echo addNumbers(5.5, 10.2); // By default, PHP tries to coerce: 5.5 becomes 5, 10.2 becomes 10, result 15.
                              // If strict_types=1, this would cause a TypeError.
// echo addNumbers("5 apples", "10 oranges"); // By default, PHP tries to coerce "5 apples" to 5, etc.
                                         // If strict_types=1, this would cause a TypeError.

function greet(string $name, ?string $title = null) { // ?string means string or null (nullable type, PHP 7.1+)
    if ($title) {
        echo "Hello, $title $name!<br>";
    } else {
        echo "Hello, $name!<br>";
    }
}
greet("Alice");
greet("Bob", "Dr.");
// greet(123); // TypeError if strict_types=1, or PHP might try to coerce 123 to "123"
?>
```

**Return Type Declarations (PHP 7+):**
You specify the return type after the closing parenthesis of the parameter list, preceded by a colon (`:`).

```php
<?php
// declare(strict_types=1);

function calculateTotal(float $price, int $quantity): float {
    return $price * $quantity;
}

$total = calculateTotal(10.5, 3);
echo "Total: " . $total . "<br>"; // Total: 31.5

function getGreeting(): string {
    return "Hello there!";
    // return 123; // Would cause a TypeError if strict_types=1, or PHP coerces 123 to "123"
}
echo getGreeting() . "<br>";

// For functions that should not return a value, use `void` (PHP 7.1+)
function logMessage(string $message): void {
    error_log($message);
    // return "done"; // This would cause a fatal error, as void functions cannot return a value.
    // A bare `return;` is allowed.
}
logMessage("System critical error!");
?>
```

*   **Strict Typing (`declare(strict_types=1);`):**
    By default, PHP will try to **coerce** values of the wrong type into the expected scalar type if possible (e.g., passing the string `"5"` to a function expecting an `int`).
    To enforce strict type checking, you can use the `declare(strict_types=1);` directive at the very beginning of your PHP file. This declaration must be the first statement in the file. When strict typing is enabled, PHP will throw a `TypeError` if the type of an argument or return value does not exactly match the declared type (no coercion for scalar types).

    ```php
    <?php
    declare(strict_types=1); // Strict typing enabled for this file

    function sumIntegers(int $a, int $b): int {
        return $a + $b;
    }

    echo sumIntegers(2, 3) . "<br>"; // 5
    // echo sumIntegers(2.5, 3.5); // This would now cause a TypeError
    // echo sumIntegers("2", "3");   // This would also cause a TypeError
    ?>
    ```
    Type hinting makes code more predictable and helps catch errors early.

## 3. Variable Scope in Functions

We touched on variable scope in "PHP Basics," but it's crucial to understand how it works with functions.

*   **Local Scope:**
    Variables declared inside a function are **local** to that function. They only exist within that function and cannot be accessed or modified from outside. Once the function finishes executing, its local variables are usually destroyed.

    ```php
    <?php
    function myFunction() {
        $localVar = "I am local.";
        echo $localVar . "<br>";
    }
    myFunction(); // Outputs: I am local.
    // echo $localVar; // This would cause an Error: Undefined variable $localVar
    ?>
    ```

*   **Global Scope:**
    Variables declared outside of any function have **global scope**. However, global variables are not automatically accessible inside functions.

    *   **Using the `global` Keyword:** To access a global variable from within a function, you must use the `global` keyword before the variable name inside the function.

        ```php
        <?php
        $globalMessage = "Hello from global scope!";

        function accessGlobal() {
            global $globalMessage; // Declare intent to use the global variable
            echo $globalMessage . "<br>";
            $globalMessage = "Global message modified."; // Modifies the global variable
        }

        accessGlobal(); // Outputs: Hello from global scope!
        echo $globalMessage . "<br>"; // Outputs: Global message modified.
        ?>
        ```

    *   **The `$GLOBALS` Array:** PHP also provides a superglobal array called `$GLOBALS`. This is an associative array where the keys are the names of all global variables, and the values are references to those variables. You can use it to access global variables from anywhere, including inside functions, without needing the `global` keyword.

        ```php
        <?php
        $appName = "My Awesome App";

        function displayAppName() {
            echo $GLOBALS['appName'] . "<br>";
            $GLOBALS['appName'] = "My Super Awesome App";
        }

        displayAppName(); // Outputs: My Awesome App
        echo $appName . "<br>"; // Outputs: My Super Awesome App
        ?>
        ```
        While `$GLOBALS` works, the `global` keyword is often considered more explicit for declaring intent within a function. Over-reliance on global variables can make code harder to manage and debug, so use them sparingly.

*   **Static Variables within Functions:**
    A `static` variable inside a function retains its value between multiple calls to that function. Normally, when a function completes, its local variables are destroyed. Static variables are an exception.

    ```php
    <?php
    function visitCounter() {
        static $count = 0; // Initialized only on the first call
        $count++;
        echo "This function has been called " . $count . " times.<br>";
    }

    visitCounter(); // This function has been called 1 times.
    visitCounter(); // This function has been called 2 times.
    visitCounter(); // This function has been called 3 times.
    ?>
    ```
    Static variables are useful for tasks like caching results within a function or tracking recursive function depth.

## 4. Built-in PHP Functions

PHP comes with a vast library of hundreds of built-in functions that perform a wide array of tasks. You don't need to write code for these common operations yourself. It's highly recommended to familiarize yourself with them, as they are efficient and well-tested.

Here are a few examples from common categories:

### String Functions

Manipulate strings (sequences of characters).

*   `strlen($string)`: Returns the length of a string.
*   `strpos($haystack, $needle)`: Finds the position of the first occurrence of a substring in a string (case-sensitive).
*   `stripos($haystack, $needle)`: Case-insensitive version of `strpos()`.
*   `substr($string, $start, $length)`: Returns a part of a string.
*   `str_replace($search, $replace, $subject)`: Replaces all occurrences of the search string with the replacement string.
*   `strtolower($string)`: Converts a string to lowercase.
*   `strtoupper($string)`: Converts a string to uppercase.
*   `ucfirst($string)`: Converts the first character of a string to uppercase.
*   `ucwords($string)`: Converts the first character of each word in a string to uppercase.
*   `trim($string, $characters)`: Strips whitespace (or other specified characters) from the beginning and end of a string.
*   `explode($delimiter, $string)`: Splits a string into an array by a specified delimiter.
*   `implode($glue, $pieces)` (or `join()`): Joins array elements with a string.
*   `htmlspecialchars($string)`: Converts special HTML characters to their entity equivalents (e.g., `<` becomes `&lt;`). Crucial for preventing Cross-Site Scripting (XSS) when displaying user input.

```php
<?php
$myString = "  Hello, PHP World!  ";
echo strlen($myString) . "<br>"; // 22 (includes leading/trailing spaces)
echo strpos($myString, "PHP") . "<br>"; // 10 (position of 'P' in 'PHP')
echo substr($myString, 8, 5) . "<br>"; // "PHP W" (starts at index 8, length 5)
$newString = str_replace("World", "Universe", $myString);
echo $newString . "<br>"; //   Hello, PHP Universe!
echo strtolower($myString) . "<br>"; //   hello, php world!
echo trim($myString) . "<br>"; // "Hello, PHP World!" (no leading/trailing spaces)

$csvData = "apple,banana,orange";
$fruitsArray = explode(",", $csvData);
print_r($fruitsArray); // Array ( [0] => apple [1] => banana [2] => orange )

$nameParts = ["John", "Doe"];
$fullName = implode(" ", $nameParts);
echo $fullName . "<br>"; // John Doe
?>
```

### Array Functions

Manipulate arrays.

*   `count($array)` (or `sizeof()`): Returns the number of elements in an array.
*   `is_array($variable)`: Checks if a variable is an array.
*   `array_push($array, $value1, ...)`: Pushes one or more elements onto the end of an array.
*   `array_pop($array)`: Pops (removes and returns) the last element of an array.
*   `array_shift($array)`: Shifts (removes and returns) the first element of an array.
*   `array_unshift($array, $value1, ...)`: Prepends one or more elements to the beginning of an array.
*   `array_merge($array1, $array2, ...)`: Merges one or more arrays.
*   `array_keys($array)`: Returns all the keys or a subset of the keys of an array.
*   `array_values($array)`: Returns all the values from an array.
*   `in_array($needle, $haystack, $strict)`: Checks if a value exists in an array.
*   **Sorting functions:**
    *   `sort($array)`: Sorts an array in ascending order (values). Re-indexes.
    *   `rsort($array)`: Sorts an array in descending order (values). Re-indexes.
    *   `asort($array)`: Sorts an associative array by values in ascending order, preserving keys.
    *   `arsort($array)`: Sorts an associative array by values in descending order, preserving keys.
    *   `ksort($array)`: Sorts an associative array by keys in ascending order.
    *   `krsort($array)`: Sorts an associative array by keys in descending order.
*   `array_map($callback, $array1, ...)`: Applies a callback function to the elements of the given arrays.
*   `array_filter($array, $callback, $flag)`: Filters elements of an array using a callback function.

```php
<?php
$numbers = [3, 1, 4, 1, 5, 9, 2, 6];
echo count($numbers) . "<br>"; // 8

array_push($numbers, 7); // $numbers is now [3, 1, 4, 1, 5, 9, 2, 6, 7]
print_r($numbers); echo "<br>";

sort($numbers); // Sorts in place
print_r($numbers); echo "<br>"; // [1, 1, 2, 3, 4, 5, 6, 7, 9]

$person = ["name" => "Alice", "age" => 30, "city" => "New York"];
$keys = array_keys($person);
print_r($keys); // Array ( [0] => name [1] => age [2] => city )
echo "<br>";

if (in_array(30, $person)) {
    echo "30 is in the person array.<br>";
}
?>
```

### Math Functions

Perform mathematical calculations.

*   `abs($number)`: Absolute value.
*   `round($number, $precision, $mode)`: Rounds a float.
*   `ceil($number)`: Rounds a number up to the nearest integer.
*   `floor($number)`: Rounds a number down to the nearest integer.
*   `rand($min, $max)` / `mt_rand($min, $max)`: Generate a random integer (`mt_rand` is generally preferred for better randomness).
*   `min($values)` or `min($array)`: Finds the lowest value.
*   `max($values)` or `max($array)`: Finds the highest value.
*   `pow($base, $exponent)`: Exponential expression.
*   `sqrt($number)`: Square root.

```php
<?php
echo abs(-7.5) . "<br>";   // 7.5
echo round(4.7) . "<br>"; // 5
echo round(4.3) . "<br>"; // 4
echo ceil(4.3) . "<br>";  // 5
echo floor(4.8) . "<br>"; // 4
echo mt_rand(1, 10) . "<br>"; // Random number between 1 and 10 (inclusive)
echo min(5, 2, 8, 1) . "<br>"; // 1
echo max([5, 2, 8, 1]) . "<br>"; // 8 (can take an array)
echo pow(2, 8) . "<br>"; // 256 (2 to the power of 8)
echo sqrt(64) . "<br>"; // 8
?>
```

### Date and Time Functions

Work with dates and times.

*   `date($format, $timestamp)`: Formats a local time/date.
*   `time()`: Returns the current Unix timestamp (seconds since January 1 1970 00:00:00 GMT).
*   `strtotime($time, $now)`: Parses an English textual datetime description into a Unix timestamp.
*   `mktime($hour, $minute, $second, $month, $day, $year)`: Gets Unix timestamp for a date.

```php
<?php
echo date("Y-m-d H:i:s") . "<br>"; // e.g., 2023-10-27 14:30:00
echo time() . "<br>"; // Current Unix timestamp

$nextWeekTimestamp = strtotime("+1 week");
echo date("Y-m-d", $nextWeekTimestamp) . "<br>"; // Date one week from now

$customTimestamp = mktime(10, 30, 0, 12, 25, 2024); // 10:30:00, Dec 25, 2024
echo date("l, F j, Y", $customTimestamp) . "<br>"; // e.g., Wednesday, December 25, 2024
?>
```

**Encourage looking up functions in the PHP manual:**
This is just a tiny fraction of the available functions. The official PHP manual ([https://www.php.net/manual/en/](https://www.php.net/manual/en/)) is an invaluable resource. Whenever you need to perform a common task, chances are there's a built-in function for it. Always check the manual first!

## 5. Anonymous Functions (Closures)

Anonymous functions, also known as **closures**, are functions without a specified name. They are defined on the fly and can be assigned to variables or passed as arguments to other functions.

**Syntax:**

```php
<?php
$variable = function($parameter1, $parameter2) use ($variableFromParentScope) {
    // function body
    return $value;
}; // Note the semicolon at the end when assigning to a variable
?>
```

*   `function($parameter1, ...) { ... }`: The definition of the anonymous function.
*   `use ($variableFromParentScope)`: This is optional. The `use` keyword allows the anonymous function to "inherit" or "close over" variables from its parent scope (the scope in which it is defined). Without `use`, the anonymous function cannot access variables from the surrounding scope, even global ones, unless explicitly passed via `$GLOBALS` or as parameters.

**Assigning anonymous functions to variables:**

```php
<?php
$sayHello = function($name) {
    echo "Hello, " . $name . "!<br>";
};

$sayHello("Developer"); // Outputs: Hello, Developer!
?>
```

**Using them as callback functions:**
Anonymous functions are particularly useful as callback functions for functions like `array_map`, `array_filter`, `usort`, etc.

```php
<?php
$numbers = [1, 2, 3, 4, 5];

// Using array_map with an anonymous function to square numbers
$squaredNumbers = array_map(function($n) {
    return $n * $n;
}, $numbers);
print_r($squaredNumbers); // Array ( [0] => 1 [1] => 4 [2] => 9 [3] => 16 [4] => 25 )
echo "<br>";

// Using array_filter with an anonymous function to get even numbers
$evenNumbers = array_filter($numbers, function($n) {
    return $n % 2 == 0;
});
print_r($evenNumbers); // Array ( [1] => 2 [3] => 4 )
echo "<br>";
?>
```

**Inheriting variables from the parent scope using `use`:**

```php
<?php
$messagePrefix = "System Log";

$logger = function($message) use ($messagePrefix) {
    echo $messagePrefix . ": " . $message . "<br>";
    // $messagePrefix = "New Prefix"; // This would change the local copy inside the closure,
                                    // not the original $messagePrefix unless passed by reference.
};

$logger("User logged in."); // Outputs: System Log: User logged in.

// To modify the variable from parent scope, pass it by reference in `use`
$counter = 0;
$increment = function() use (&$counter) {
    $counter++;
};
$increment();
$increment();
echo "Counter: " . $counter . "<br>"; // Counter: 2
?>
```

## 6. Arrow Functions (PHP 7.4+)

Arrow functions provide a more concise syntax for creating anonymous functions, especially when the function body consists of a single expression.

**Syntax:**

`fn($parameter1, $parameter2) => expression;`

*   `fn`: Keyword to define an arrow function.
*   `($parameter1, ...)`: Parameter list.
*   `=>`: Separates parameters from the expression.
*   `expression`: A single expression whose result is automatically returned. There's no need for the `return` keyword.

**Key Features:**

*   **Automatic access to variables from the outer scope:** Arrow functions automatically capture variables from the parent scope by value. You don't need the `use` keyword.
*   **Limitations:**
    *   They can only contain one expression.
    *   This single expression is implicitly the return statement.
    *   You cannot have multiple statements or a block of code `{...}`.
    *   They cannot modify variables from the outer scope directly (they are captured by value).

**Example:**

```php
<?php
$numbers = [1, 2, 3, 4, 5];
$multiplier = 10;

// Using array_map with an arrow function
$multipliedNumbers = array_map(fn($n) => $n * $multiplier, $numbers);
// $multiplier is automatically available from the outer scope

print_r($multipliedNumbers); // Array ( [0] => 10 [1] => 20 [2] => 30 [3] => 40 [4] => 50 )
echo "<br>";

$adder = fn($x, $y) => $x + $y;
echo $adder(5, 7) . "<br>"; // 12

// Example of scope (by value)
$message = "Hello";
$greet = fn($name) => $message . ", " . $name; // $message is captured by value
$message = "Goodbye"; // Changing $message here does not affect the one captured by the arrow function

echo $greet("Alice") . "<br>"; // Outputs: Hello, Alice
?>
```
Arrow functions are great for short, simple callback operations, making code more readable.

## 7. Recursive Functions

A recursive function is a function that calls itself, either directly or indirectly through another function. Recursion is a powerful problem-solving technique where a problem can be broken down into smaller, self-similar subproblems.

**Essential Components of a Recursive Function:**

1.  **Base Case(s):** A condition that stops the recursion. Without a base case, the function would call itself indefinitely, leading to a "stack overflow" error (the function call stack runs out of memory).
2.  **Recursive Step:** The part of the function where it calls itself, usually with modified arguments that move it closer to the base case.

**Example: Factorial**
The factorial of a non-negative integer `n` (denoted as `n!`) is the product of all positive integers less than or equal to `n`.
*   `0! = 1` (base case)
*   `n! = n * (n-1)!` (recursive step)

```php
<?php
function factorial($n) {
    // Base case:
    if ($n < 0) { // Factorial not defined for negative numbers
        return null; // Or throw an exception
    }
    if ($n == 0) {
        return 1;
    } else {
        // Recursive step:
        return $n * factorial($n - 1);
    }
}

echo "Factorial of 5 is: " . factorial(5) . "<br>"; // 5 * 4 * 3 * 2 * 1 = 120
echo "Factorial of 0 is: " . factorial(0) . "<br>"; // 1
echo "Factorial of 3 is: " . factorial(3) . "<br>"; // 3 * 2 * 1 = 6
?>
```
**How `factorial(3)` works:**
1.  `factorial(3)` calls `3 * factorial(2)`
2.  `factorial(2)` calls `2 * factorial(1)`
3.  `factorial(1)` calls `1 * factorial(0)`
4.  `factorial(0)` hits the base case and returns `1`.
5.  `factorial(1)` returns `1 * 1 = 1`.
6.  `factorial(2)` returns `2 * 1 = 2`.
7.  `factorial(3)` returns `3 * 2 = 6`.

**Potential Pitfalls of Recursion:**

*   **Infinite Recursion:** If the base case is missing or incorrect, the function may never stop calling itself, leading to a stack overflow error.
*   **Stack Overflow:** Each function call uses memory on the call stack. Deep recursion (many nested calls) can exhaust this memory. For problems that require very deep recursion, an iterative solution (using loops) might be more efficient in PHP.
*   **Performance:** Recursive calls can sometimes be less performant than iterative solutions due to the overhead of function calls. However, for some problems, recursion provides a much cleaner and more intuitive solution.

Recursion is a powerful tool for certain types of problems, like traversing tree-like structures (e.g., file systems, nested comments) or some mathematical calculations. However, always ensure you have a clear base case.

This wraps up our exploration of PHP functions. Mastering functions is key to writing effective and maintainable PHP code!
