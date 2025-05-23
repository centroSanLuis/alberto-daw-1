# PHP Basics

Now that you have your PHP environment set up, it's time to learn the fundamental building blocks of the PHP language. This section covers basic syntax, variables, data types, operators, and control structures.

## 1. Basic PHP Syntax

PHP scripts are typically embedded within HTML files. The PHP interpreter processes only the code within special PHP tags.

### PHP Tags

*   **Standard Tags:** `<?php ... ?>`
    This is the most common and recommended way to delimit PHP code.
    ```php
    <?php
    echo "Hello from standard PHP tags!";
    ?>
    ```

*   **Short Echo Tags:** `<?= ... ?>`
    This is a shortcut for `<?php echo ... ?>`. It's convenient for quickly printing a variable or expression. It is enabled by default in modern PHP versions.
    ```html
    <p>My name is <?= "John Doe"; ?>.</p>
    ```

While other tag styles like `<script language="php">...</script>` and ASP-style tags (`<% ... %>`) exist, they are less common, often deprecated, or require special configuration. **It's best to stick to the standard `<?php ... ?>` tags for maximum compatibility and clarity.**

### Escaping from HTML

PHP code is processed on the server. When the PHP interpreter encounters code outside of its tags, it simply outputs that content directly to the browser. This allows you to easily mix PHP with HTML.

```html
<!DOCTYPE html>
<html>
<head>
    <title>PHP Example</title>
</head>
<body>
    <h1><?php echo "Welcome to My Page"; ?></h1>
    <p>This is a standard HTML paragraph.</p>
    <?php
    // This is PHP code
    $message = "PHP is fun!";
    echo "<p>" . $message . "</p>";
    ?>
    <p>Another HTML paragraph.</p>
</body>
</html>
```

### Instruction Separation: Semicolons

Like many other programming languages (C, Java, JavaScript), PHP uses a semicolon (`;`) to separate individual statements.

```php
<?php
$name = "Alice";
echo $name;
$age = 30;
echo $age;
?>
```
The closing PHP tag `?>` automatically implies a semicolon, so you don't strictly need one for the very last statement in a PHP block. However, it's good practice to always include it for consistency.

### Comments

Comments are parts of the code that are ignored by the PHP interpreter. They are used to explain code, make notes, or temporarily disable parts of a script.

*   **Single-line comments:** Start with `//` or `#`.
    ```php
    <?php
    // This is a single-line comment
    $x = 5; // This comment explains the variable assignment

    # This is also a single-line comment (less common for PHP, more common in shell scripts)
    $y = 10;
    ?>
    ```

*   **Multi-line comments:** Start with `/*` and end with `*/`.
    ```php
    <?php
    /*
    This is a
    multi-line comment block.
    It can span several lines.
    */
    $sum = $x + $y;
    echo $sum;
    ?>
    ```

### Case Sensitivity

PHP's case sensitivity rules are important to understand:

*   **Variable names ARE case-sensitive.**
    ```php
    <?php
    $name = "Alice";
    echo $name; // Outputs "Alice"
    // echo $Name; // This would cause an error or output nothing if $Name is not defined
    ?>
    ```

*   **Function names, class names, method names, and PHP keywords (e.g., `echo`, `if`, `else`, `while`, `class`) are generally case-INSENSITIVE.**
    ```php
    <?php
    ECHO "Hello!"; // Works
    function myFunction() {
        PRINT "Inside function."; // Works
    }
    myFUNCTION(); // Works

    // However, for consistency and readability, it's best practice to stick to a
    // consistent casing for function calls and keywords (usually lowercase for functions and keywords).
    ?>
    ```
    **Recommendation:** Always use consistent casing (e.g., `camelCase` for variables, `PascalCase` for classes, lowercase for functions and keywords) to improve code readability and avoid potential confusion.

## 2. Variables

Variables are containers for storing information like text, numbers, or arrays.

### Declaration and Naming Rules

*   Variable names in PHP must start with a dollar sign (`$`).
*   The first character after the `$` must be a letter (a-z, A-Z) or an underscore (`_`).
*   Subsequent characters can be letters, numbers (0-9), or underscores.
*   Variable names are case-sensitive (`$name` and `$Name` are two different variables).

```php
<?php
$name = "John";
$_age = 30;
$user_email_address = "john.doe@example.com";
$totalAmount = 100.50;
// $1variable = "error"; // Invalid: starts with a number after $
// $variable-name = "error"; // Invalid: contains a hyphen
?>
```

PHP is a **loosely typed language**. This means you don't need to declare the data type of a variable before assigning a value to it. PHP automatically determines the type based on the value assigned.

### Assigning Values

Values are assigned to variables using the assignment operator (`=`).

```php
<?php
$firstName = "Jane"; // Assigns the string "Jane" to $firstName
$age = 25;         // Assigns the integer 25 to $age
$isStudent = true; // Assigns the boolean true to $isStudent

// Variables can also be assigned the value of other variables
$personName = $firstName;
echo $personName; // Outputs "Jane"
?>
```

### Variable Scope

The scope of a variable defines where in your script it can be accessed.

*   **Local Scope:**
    Variables declared inside a function are local to that function. They can only be accessed from within that function.
    ```php
    <?php
    function greet() {
        $message = "Hello from inside the function!"; // Local variable
        echo $message;
    }

    greet();
    // echo $message; // This would cause an error because $message is not defined globally
    ?>
    ```

*   **Global Scope:**
    Variables declared outside of any function have global scope. They can be accessed from anywhere in the script *except* from within a function by default.
    ```php
    <?php
    $globalVar = "I am global!";

    function testGlobal() {
        // echo $globalVar; // This would cause an error or try to access an undefined local $globalVar
    }
    testGlobal();
    echo $globalVar; // Outputs "I am global!"
    ?>
    ```
    To access a global variable from within a function, you must use the `global` keyword:
    ```php
    <?php
    $count = 0;

    function incrementCount() {
        global $count; // Declare that we want to use the global $count
        $count++;
        echo "Count inside function: " . $count . "<br>";
    }

    incrementCount(); // Count inside function: 1
    incrementCount(); // Count inside function: 2
    echo "Count outside function: " . $count; // Count outside function: 2
    ?>
    ```
    Alternatively, you can use the `$GLOBALS` array (see Superglobals).

*   **Static Scope:**
    When a variable inside a function is declared as `static`, it retains its value between multiple calls to that function. Normally, when a function finishes executing, all its local variables are destroyed.
    ```php
    <?php
    function staticCounter() {
        static $num = 0; // Static variable
        $num++;
        echo $num . "<br>";
    }

    staticCounter(); // Outputs: 1
    staticCounter(); // Outputs: 2
    staticCounter(); // Outputs: 3
    ?>
    ```

### Superglobals

Superglobals are predefined variables in PHP that are always accessible, regardless of scope. You don't need to use the `global` keyword to access them from within functions or methods.

Here are some of the most commonly used superglobals:

*   `$_GET`: An associative array of variables passed to the current script via the URL parameters (query string).
    *   Example: If the URL is `example.php?name=Alice&age=30`, then `$_GET['name']` is "Alice" and `$_GET['age']` is "30".
*   `$_POST`: An associative array of variables passed to the current script via the HTTP POST method (typically from HTML forms).
*   `$_REQUEST`: An associative array that by default contains the contents of `$_GET`, `$_POST`, and `$_COOKIE`. It's generally recommended to use `$_GET` or `$_POST` directly for clarity and security.
*   `$_SERVER`: An array containing information such as headers, paths, and script locations.
    *   Examples: `$_SERVER['PHP_SELF']` (filename of the currently executing script), `$_SERVER['HTTP_HOST']` (host name), `$_SERVER['REQUEST_METHOD']` (GET, POST, etc.).
*   `$_SESSION`: An associative array containing session variables. Used to persist user data across multiple page requests. You need to start a session with `session_start()` before using it.
*   `$_COOKIE`: An associative array of variables passed to the current script via HTTP Cookies.
*   `$_FILES`: An associative array of items uploaded to the current script via the HTTP POST method. Used for handling file uploads.
*   `$_ENV`: An associative array of variables passed to the current script via the environment method.
*   `$GLOBALS`: An associative array containing references to all variables which are currently defined in the global scope of the script. The keys of this array are the names of the global variables.
    ```php
    <?php
    $myGlobal = "Hello from global scope!";

    function accessGlobalViaGLOBALS() {
        echo $GLOBALS['myGlobal'];
    }

    accessGlobalViaGLOBALS(); // Outputs: Hello from global scope!
    ?>
    ```

## 3. Data Types

PHP supports several data types. As a dynamically typed language, the type of a variable is not set by declaration but is determined at runtime by the value it holds.

### Explanation of Dynamic Typing

In PHP, you don't explicitly tell PHP what type of data a variable will hold. PHP figures it out automatically:

```php
<?php
$data = "Hello";      // $data is a string
echo gettype($data) . "<br>"; // Output: string

$data = 123;          // Now $data is an integer
echo gettype($data) . "<br>"; // Output: integer

$data = true;         // Now $data is a boolean
echo gettype($data) . "<br>"; // Output: boolean
?>
```
`gettype()` is a function that returns the type of a variable.

### Scalar Types

Scalar types represent single values.

*   **String:** A sequence of characters.
    *   Can be enclosed in single quotes (`'...'`) or double quotes (`"..."`).
    *   **Single quotes:** Treat the content literally. Variables and most escape sequences are not interpreted.
        ```php
        $name = 'Alice';
        $text = 'My name is $name.'; // Output: My name is $name.
        $text2 = 'This is a single quote: \'';
        ```
    *   **Double quotes:** Interpret variables (variable interpolation) and special escape sequences (e.g., `\n` for newline, `\t` for tab, `\$` for a literal dollar sign).
        ```php
        $name = "Bob";
        $text = "My name is $name.";   // Output: My name is Bob.
        $text2 = "This is a newline:\nAnd this is a tab:\t.";
        $text3 = "He said, \"Hello!\""; // Escaping double quotes
        ```
    *   **String Concatenation:** The dot (`.`) operator is used to join strings.
        ```php
        $greeting = "Hello";
        $subject = "World";
        $fullGreeting = $greeting . ", " . $subject . "!"; // "Hello, World!"
        echo $fullGreeting;
        ```

*   **Integer:** Whole numbers (positive, negative, or zero) without decimal points.
    ```php
    <?php
    $age = 30;
    $negative_num = -100;
    $octal_num = 0123; // Octal (equivalent to 83 decimal)
    $hex_num = 0x1A;   // Hexadecimal (equivalent to 26 decimal)
    $binary_num = 0b1010; // Binary (equivalent to 10 decimal)
    var_dump($age, $negative_num); // var_dump() shows type and value
    ?>
    ```

*   **Float (Floating-point number or Double):** Numbers with a decimal point or in exponential form.
    ```php
    <?php
    $price = 19.99;
    $pi = 3.14159;
    $scientific = 3.0e8; // 3 * 10^8
    var_dump($price, $scientific);
    ?>
    ```

*   **Boolean:** Represents two possible states: `true` or `false`. Boolean values are case-insensitive (`true`, `TRUE`, `True` are all the same).
    ```php
    <?php
    $isLoggedIn = true;
    $isAdmin = false;
    var_dump($isLoggedIn, $isAdmin);
    ?>
    ```
    *   **Truthy and Falsy Values:** When converting non-boolean values to boolean, PHP considers certain values as `false`:
        *   The boolean `false` itself.
        *   The integer `0`.
        *   The float `0.0`.
        *   The empty string `""` and the string `"0"`.
        *   An array with zero elements.
        *   The special type `NULL`.
        *   SimpleXML objects created from empty tags.
        All other values are considered `true` (truthy).

### Compound Types

Compound types can hold multiple values.

*   **Array:** An ordered map that associates values with keys.
    *   **Indexed arrays:** Arrays with numeric indices (keys usually start from 0).
        ```php
        <?php
        // Using array() construct
        $colors = array("Red", "Green", "Blue");
        // Using short array syntax [] (PHP 5.4+)
        $fruits = ["Apple", "Banana", "Cherry"];

        echo $colors[0]; // Outputs: Red
        echo $fruits[1]; // Outputs: Banana
        $fruits[1] = "Orange"; // Modify an element
        var_dump($fruits);
        ?>
        ```
    *   **Associative arrays:** Arrays with named (string) keys.
        ```php
        <?php
        $person = array(
            "name" => "Alice",
            "age" => 30,
            "city" => "New York"
        );
        // Using short syntax
        $car = [
            "make" => "Toyota",
            "model" => "Camry",
            "year" => 2021
        ];

        echo $person["name"]; // Outputs: Alice
        echo $car["model"];  // Outputs: Camry
        $person["email"] = "alice@example.com"; // Add a new element
        var_dump($person);
        ?>
        ```
    *   **Multi-dimensional arrays:** Arrays containing other arrays.
        ```php
        <?php
        $students = [
            ["name" => "John", "age" => 20, "grade" => "A"],
            ["name" => "Jane", "age" => 22, "grade" => "B"],
            ["name" => "Doe",  "age" => 21, "grade" => "A+"]
        ];
        echo $students[1]["name"]; // Outputs: Jane
        echo $students[0]["grade"]; // Outputs: A
        var_dump($students);
        ?>
        ```

*   **Object:** Instances of classes. Objects encapsulate data (properties) and behavior (methods). We'll cover objects and classes in detail in the Object-Oriented Programming (OOP) section. For now, just know it's a data type.
    ```php
    <?php
    class MyClass {
        public $property = "Hello Object!";
    }
    $obj = new MyClass();
    echo $obj->property; // Accessing object property
    var_dump($obj);
    ?>
    ```

### Special Types

*   **NULL:** A special data type that represents a variable with no value. A variable is considered `NULL` if:
    *   It has been assigned the constant `NULL`.
    *   It has not been set to any value yet.
    *   It has been `unset()`.
    `NULL` is case-insensitive (`null`, `NULL`, `Null` are the same).
    ```php
    <?php
    $noValue = NULL;
    $undefinedVar; // Automatically NULL, but raises a notice if accessed before assignment

    var_dump($noValue);
    // var_dump($undefinedVar); // Would show NULL but also a notice
    $x = 10;
    unset($x); // $x is now NULL
    // var_dump($x); // Would show NULL but also a notice
    ?>
    ```

*   **Resource:** A special variable holding a reference to an external resource, such as a database connection, a file handle, or an image canvas. You don't typically create resource variables directly; they are returned by specific functions.
    ```php
    <?php
    // Example: Opening a file
    $fileHandle = fopen("example.txt", "r"); // fopen returns a resource or false on error
    if ($fileHandle) {
        echo gettype($fileHandle) . "<br>"; // Output: resource
        var_dump($fileHandle);
        fclose($fileHandle); // Important to close resources
    } else {
        echo "Failed to open file.";
    }
    ?>
    ```

### Type Juggling and Casting

PHP often automatically converts types when needed in an expression ("type juggling").

```php
<?php
$numString = "10"; // String
$integer = 5;      // Integer
$result = $numString + $integer; // $numString is converted to an integer for addition
echo $result; // Output: 15
var_dump($result); // Output: int(15)

$boolValue = true;
$sum = 1 + $boolValue; // true is converted to 1
echo $sum; // Output: 2
?>
```

You can also explicitly convert types using **type casting**:

*   `(int)`, `(integer)` - cast to integer
*   `(bool)`, `(boolean)` - cast to boolean
*   `(float)`, `(double)`, `(real)` - cast to float
*   `(string)` - cast to string
*   `(array)` - cast to array
*   `(object)` - cast to object
*   `(unset)` - cast to NULL (PHP 7.2+)

```php
<?php
$floatNum = 123.789;
$intNum = (int) $floatNum;
echo $intNum; // Output: 123 (fractional part is truncated)

$strValue = "false";
$boolVal = (bool) $strValue; // Non-empty string becomes true
var_dump($boolVal); // Output: bool(true)

$num = 100;
$strNum = (string) $num;
var_dump($strNum); // Output: string(3) "100"

$value = 10;
$arr = (array) $value;
var_dump($arr); // Output: array(1) { [0]=> int(10) }
?>
```

## 4. Operators

Operators are symbols that perform operations on variables and values.

### Arithmetic Operators

Perform mathematical calculations.

*   `+` : Addition
*   `-` : Subtraction
*   `*` : Multiplication
*   `/` : Division
*   `%` : Modulus (remainder of a division)
*   `**`: Exponentiation (PHP 5.6+)

```php
<?php
$x = 10;
$y = 3;
echo $x + $y;  // 13
echo $x - $y;  // 7
echo $x * $y;  // 30
echo $x / $y;  // 3.333...
echo $x % $y;  // 1
echo 2 ** 4; // 16 (2 to the power of 4)
?>
```

### Assignment Operators

Assign values to variables.

*   `=`   : Basic assignment (`$a = $b`)
*   `+=`  : Addition assignment (`$a += $b` is `$a = $a + $b`)
*   `-=`  : Subtraction assignment (`$a -= $b` is `$a = $a - $b`)
*   `*=`  : Multiplication assignment (`$a *= $b` is `$a = $a * $b`)
*   `/=`  : Division assignment (`$a /= $b` is `$a = $a / $b`)
*   `%=`  : Modulus assignment (`$a %= $b` is `$a = $a % $b`)
*   `.=`  : Concatenation assignment (`$a .= $b` is `$a = $a . $b`) (for strings)

```php
<?php
$a = 5;
$b = 3;
$a += $b; // $a is now 8
echo $a . "<br>";

$str = "Hello";
$str .= " World!"; // $str is now "Hello World!"
echo $str . "<br>";
?>
```

### Comparison Operators

Compare two values and return a boolean (`true` or `false`).

*   `==`  : Equal (value). Type juggling may occur.
*   `===` : Identical (value and type). No type juggling.
*   `!=`  : Not equal (value).
*   `<>`  : Not equal (value, alternative to `!=`).
*   `!==` : Not identical (value or type).
*   `<`   : Less than.
*   `>`   : Greater than.
*   `<=`  : Less than or equal to.
*   `>=`  : Greater than or equal to.
*   `<=>` : Spaceship operator (PHP 7+). Returns -1 if left is less than right, 0 if equal, 1 if left is greater than right.

```php
<?php
$x = 5;
$y = "5";

var_dump($x == $y);  // bool(true) because "5" is converted to 5
var_dump($x === $y); // bool(false) because $x is int, $y is string

var_dump($x != $y);  // bool(false)
var_dump($x !== $y); // bool(true)

$a = 10;
$b = 20;
var_dump($a < $b);   // bool(true)

var_dump(5 <=> 5); // int(0)
var_dump(5 <=> 10); // int(-1)
var_dump(10 <=> 5); // int(1)
?>
```
**Difference between `==` and `===`:**
Always prefer `===` for comparisons if you also want to ensure the types are the same, as it's stricter and can prevent subtle bugs caused by type juggling. Use `==` only when you specifically need to compare values after type conversion.

### Logical Operators

Combine conditional statements.

*   `&&` (and): True if both operands are true.
*   `||` (or) : True if either operand is true.
*   `!` (not) : True if the operand is false.
*   `and`     : Logical AND (lower precedence than `&&`).
*   `or`      : Logical OR (lower precedence than `||`).
*   `xor`     : Logical XOR (exclusive OR - true if one operand is true, but not both).

```php
<?php
$age = 25;
$isCitizen = true;

if ($age >= 18 && $isCitizen) {
    echo "Can vote.<br>";
}

if ($age < 18 || !$isCitizen) {
    echo "Cannot vote.<br>";
}

// Using 'and' (note precedence difference with assignment)
// ($result = true and false;) would assign true to $result then evaluate (true and false)
// $result = (true and false); // $result is false
?>
```
**Short-circuiting:** Logical operators `&&` and `||` perform short-circuit evaluation.
*   For `&&`, if the left operand is `false`, the right operand is not evaluated because the result will always be `false`.
*   For `||`, if the left operand is `true`, the right operand is not evaluated because the result will always be `true`.
This can be useful for performance or to avoid errors:
```php
<?php
$divisor = 0;
// if ($divisor != 0 && (10 / $divisor) > 2) { // This would cause a division by zero error
//     echo "Result is greater than 2";
// }

if ($divisor != 0 && (10 / $divisor) > 2) { // Short-circuiting prevents division by zero
    echo "Result is greater than 2";
} else {
    echo "Condition not met or division by zero avoided.<br>";
}
?>
```

### Increment/Decrement Operators

Increase or decrease a variable's value by one.

*   `++$var` (Pre-increment): Increments `$var` by one, then returns `$var`.
*   `$var++` (Post-increment): Returns `$var`, then increments `$var` by one.
*   `--$var` (Pre-decrement): Decrements `$var` by one, then returns `$var`.
*   `$var--` (Post-decrement): Returns `$var`, then decrements `$var` by one.

```php
<?php
$a = 5;
echo ++$a; // 6 (a becomes 6, then 6 is printed)
echo $a;   // 6

$b = 5;
echo $b++; // 5 (5 is printed, then b becomes 6)
echo $b;   // 6

$c = 5;
echo --$c; // 4 (c becomes 4, then 4 is printed)

$d = 5;
echo $d--; // 5 (5 is printed, then d becomes 4)
?>
```

### String Concatenation Operator

*   `.` : Joins two strings.

```php
<?php
$str1 = "Hello";
$str2 = "PHP";
$message = $str1 . " " . $str2 . "!"; // "Hello PHP!"
echo $message;
?>
```

### Array Operators

*   `+`   : Union. Appends elements from the right-hand array to the left-hand array. If keys are the same, elements from the left array are kept.
*   `==`  : Equality. Returns `true` if both arrays have the same key/value pairs (order doesn't matter for associative, but does for indexed unless keys are explicitly specified).
*   `===` : Identity. Returns `true` if both arrays have the same key/value pairs in the same order and of the same types.
*   `!=`, `<>` : Inequality.
*   `!==` : Non-identity.

```php
<?php
$arr1 = ["a" => "apple", "b" => "banana"];
$arr2 = ["b" => "blueberry", "c" => "cherry"]; // "b" key exists in $arr1
$union = $arr1 + $arr2;
print_r($union); // Output: Array ( [a] => apple [b] => banana [c] => cherry )

$x = [1, 2, 3];
$y = [3, 2, 1];
$z = ["1", 2, "3"];
var_dump($x == $y); // bool(false) - order and values matter for indexed
var_dump($x == $z); // bool(true) - values are compared after type juggling

$a = ["name" => "Alice", "age" => 30];
$b = ["age" => 30, "name" => "Alice"];
var_dump($a == $b); // bool(true) - order doesn't matter for associative with same key/value pairs
var_dump($a === $b); // bool(false) - order still matters for === if key/value pairs are not in the same sequence internally. For ===, key order also matters.
?>
```

### Error Control Operator

*   `@` : When prepended to an expression, any error messages that might be generated by that expression will be ignored.

```php
<?php
// Example: Trying to access an undefined variable
$value = @$undefinedVar; // Suppresses the "Undefined variable" notice
echo "Value is: " . $value . "<br>"; // Value is: (empty)

// Example: Opening a file that might not exist
$file = @fopen("non_existent_file.txt", "r");
if ($file) {
    echo "File opened.";
    fclose($file);
} else {
    echo "Failed to open file (error suppressed).<br>";
}
?>
```
**Caution:** While the `@` operator can be useful for suppressing errors in specific situations (e.g., when you know an error might occur and have a plan to handle it), it should be used sparingly. Overusing it can hide real problems in your code and make debugging much harder. It's generally better to handle errors explicitly using `try-catch` blocks (for exceptions) or by checking return values of functions.

### Ternary Operator

A shorthand for an `if-else` statement.
Syntax: `condition ? expression_if_true : expression_if_false`

```php
<?php
$age = 20;
$status = ($age >= 18) ? "adult" : "minor";
echo "Status: " . $status . "<br>"; // Status: adult

// Equivalent if-else:
// if ($age >= 18) {
//     $status = "adult";
// } else {
//     $status = "minor";
// }
?>
```

### Null Coalescing Operator

Available since PHP 7.0. Returns its first operand if it exists and is not `NULL`; otherwise, it returns its second operand.
Syntax: `operand1 ?? operand2`

```php
<?php
// Example 1: Variable might not be set
// $username = $_GET['user']; // This would produce a notice if 'user' is not in GET params
$username = $_GET['user'] ?? 'guest'; // Sets $username to 'guest' if $_GET['user'] is not set or is NULL
echo "Username: " . $username . "<br>";

// Example 2: Chaining
$preferredName = $userProfile['nickname'] ?? $userProfile['firstName'] ?? 'Anonymous';
echo "Preferred Name: " . $preferredName . "<br>";

// This is a shorthand for:
// isset($operand1) ? $operand1 : $operand2
// or more accurately for null:
// $operand1 !== null ? $operand1 : $operand2
?>
```

## 5. Control Structures

Control structures allow you to dictate the flow of execution in your PHP scripts. They enable decision-making (conditionals) and repetition (loops).

### Conditional Statements

Execute different blocks of code based on specified conditions.

*   **`if`, `elseif`, `else`:**
    ```php
    <?php
    $score = 85;

    if ($score >= 90) {
        echo "Grade: A<br>";
    } elseif ($score >= 80) {
        echo "Grade: B<br>"; // This block will be executed
    } elseif ($score >= 70) {
        echo "Grade: C<br>";
    } else {
        echo "Grade: D or F<br>";
    }

    $temperature = 15;
    if ($temperature > 25) {
        echo "It's warm.<br>";
    } else {
        echo "It's cool.<br>";
    }
    ?>
    ```

*   **Alternative syntax for control structures:**
    PHP offers an alternative syntax for some control structures (like `if`, `while`, `for`, `foreach`, `switch`), which can be useful when embedding PHP within HTML. This involves replacing the opening brace `{` with a colon `:` and the closing brace `}` with `endif;`, `endwhile;`, `endfor;`, `endforeach;`, or `endswitch;` respectively.

    ```html
    <?php $isLoggedIn = true; ?>

    <?php if ($isLoggedIn): ?>
        <p>Welcome, user!</p>
        <a href="logout.php">Logout</a>
    <?php else: ?>
        <p>Please log in.</p>
        <a href="login.php">Login</a>
    <?php endif; ?>
    ```

*   **`switch`:**
    Used to select one of many blocks of code to be executed. It compares an expression with multiple different values (cases).
    ```php
    <?php
    $dayOfWeek = "Wednesday";

    switch ($dayOfWeek) {
        case "Monday":
            echo "Start of the work week.<br>";
            break; // Important! Exits the switch statement
        case "Tuesday":
        case "Wednesday":
        case "Thursday":
            echo "Mid-week.<br>"; // Wednesday will match here
            break;
        case "Friday":
            echo "End of the work week!<br>";
            break;
        case "Saturday":
        case "Sunday":
            echo "Weekend!<br>";
            break;
        default: // Optional: executed if no case matches
            echo "Invalid day.<br>";
    }
    ?>
    ```
    Without `break`, execution "falls through" to the next `case`. This can be intentional but is often a source of bugs if forgotten.

### Looping Constructs

Execute a block of code repeatedly.

*   **`for` loop:**
    Used when you know in advance how many times the script should run.
    Syntax: `for (initializer; condition; incrementer)`
    ```php
    <?php
    for ($i = 0; $i < 5; $i++) { // Initializes $i to 0; continues as long as $i < 5; increments $i by 1 after each iteration
        echo "The number is: " . $i . "<br>";
    }
    // Output:
    // The number is: 0
    // The number is: 1
    // The number is: 2
    // The number is: 3
    // The number is: 4
    ?>
    ```

*   **`while` loop:**
    Loops through a block of code as long as the specified condition is true. The condition is checked *before* each iteration.
    ```php
    <?php
    $count = 1;
    while ($count <= 3) {
        echo "Count is: " . $count . "<br>";
        $count++;
    }
    // Output:
    // Count is: 1
    // Count is: 2
    // Count is: 3
    ?>
    ```

*   **`do-while` loop:**
    Similar to a `while` loop, but the condition is checked *after* each iteration. This means the block of code will always be executed at least once.
    ```php
    <?php
    $x = 6;
    do {
        echo "The number is: " . $x . "<br>"; // This will execute once
        $x++;
    } while ($x <= 5); // Condition is false, so loop stops
    // Output: The number is: 6
    ?>
    ```

*   **`foreach` loop:**
    Provides an easy way to iterate over arrays (and objects).
    ```php
    <?php
    // Iterating over values
    $colors = ["Red", "Green", "Blue"];
    foreach ($colors as $color) {
        echo $color . "<br>";
    }
    // Output: Red, Green, Blue (each on a new line)

    // Iterating over keys and values
    $person = ["name" => "Alice", "age" => 30, "city" => "London"];
    foreach ($person as $key => $value) {
        echo ucfirst($key) . ": " . $value . "<br>";
    }
    // Output:
    // Name: Alice
    // Age: 30
    // City: London
    ?>
    ```

### Other Control Flow Statements

*   **`break`:**
    Immediately terminates the execution of the current `for`, `foreach`, `while`, `do-while`, or `switch` structure.
    ```php
    <?php
    for ($i = 0; $i < 10; $i++) {
        if ($i == 5) {
            break; // Exit the loop when $i is 5
        }
        echo "Number: " . $i . "<br>";
    }
    // Output: Numbers 0 through 4
    ?>
    ```
    `break` can also accept an optional numeric argument to specify how many nested enclosing structures it should break out of. `break 2;` would break out of two nested loops.

*   **`continue`:**
    Skips the rest of the current iteration of a loop (`for`, `while`, `do-while`, `foreach`) and proceeds to the next iteration (or condition evaluation).
    ```php
    <?php
    for ($i = 0; $i < 5; $i++) {
        if ($i == 2) {
            continue; // Skip this iteration when $i is 2
        }
        echo "Number: " . $i . "<br>";
    }
    // Output:
    // Number: 0
    // Number: 1
    // Number: 3
    // Number: 4
    ?>
    ```
    Like `break`, `continue` can also take an optional numeric argument.

*   **`include` and `require`:**
    These constructs are used to include and evaluate the contents of another PHP file into the current script. This is fundamental for organizing code into reusable modules.
    *   `include 'filename.php';`
    *   `require 'filename.php';`

    The main difference between them is how they handle errors if the file is not found:
    *   `include`: If the file is not found, it generates a **warning** (E_WARNING), and the script continues execution.
    *   `require`: If the file is not found, it generates a **fatal error** (E_COMPILE_ERROR), and the script terminates.

    Use `require` for files that are essential for your application to run (e.g., database configurations, core functions). Use `include` for files that are optional or whose absence can be handled gracefully (e.g., template parts).

    ```php
    <?php
    // Assume 'header.php' contains: echo "<h1>My Website</h1>";
    include 'header.php';

    echo "<p>This is the main content.</p>";

    // Assume 'functions.php' contains essential functions
    // require 'functions.php';
    // If functions.php is missing, 'require' would stop the script.
    ?>
    ```

    *   **`include_once` and `require_once`:**
        These are variants that ensure a file is included only once, even if the statement is encountered multiple times. This prevents errors from re-declaring functions or classes.
        *   `include_once 'filename.php';`
        *   `require_once 'filename.php';`
        It's generally good practice to use the `_once` versions for including files that define functions, classes, or set configurations to avoid issues.

*   **`goto`:**
    Allows you to jump to another section in the program, identified by a label.
    ```php
    <?php
    goto end_point;
    echo "This will be skipped.<br>";

    end_point:
    echo "Reached the end point.<br>";
    ?>
    ```
    **Advice:** The `goto` statement is available in PHP but is generally **discouraged** in modern programming practices. It can make code difficult to read, understand, and debug, leading to "spaghetti code." There are almost always better ways to structure your code using loops, conditionals, functions, or exceptions. Use it very sparingly, if at all.

This covers the basics of PHP! With these fundamentals, you can start writing simple scripts and understand more complex code. In the next sections, we'll build upon this knowledge.
