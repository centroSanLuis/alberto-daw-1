# PHP Object-Oriented Programming (OOP)

Object-Oriented Programming (OOP) is a powerful programming paradigm that provides a way of structuring code to create more organized, reusable, and maintainable applications. PHP has robust support for OOP, and understanding its principles is crucial for building modern, scalable web applications.

## 1. Introduction to OOP Concepts

### What is OOP?

OOP is a programming model based on the concept of "objects". An object is a self-contained unit that combines data (properties or attributes) and behavior (methods or functions that operate on that data). Instead of writing procedures or functions that operate on separate data, OOP encourages you to create objects that manage their own data and behavior.

Think of real-world objects: a car is an object. It has properties (color, model, speed) and methods (startEngine, accelerate, brake). OOP allows you to model such real-world entities in your code.

### Why Use OOP?

*   **Organized Code:** OOP helps structure code in a way that mirrors real-world entities, making it more intuitive and easier to manage, especially for large projects.
*   **Reusability (DRY - Don't Repeat Yourself):** Classes, the blueprints for objects, can be reused to create multiple objects of the same type. Inheritance allows creating new classes that reuse code from existing ones.
*   **Scalability:** Well-structured OOP code is easier to extend and scale as project requirements grow. New features can often be added by creating new classes or extending existing ones without drastically changing the existing codebase.
*   **Maintainability:** OOP promotes modularity. Changes to one part of the application (e.g., modifying a class) are less likely to break other parts, making the code easier to maintain and debug.
*   **Collaboration:** Different developers can work on different classes simultaneously, often with less conflict.

### Core OOP Principles

There are four main principles that form the foundation of OOP:

1.  **Encapsulation:**
    *   **Concept:** Bundling the data (properties) and the methods that operate on that data within a single unit (an object). It also involves **hiding the internal state** of an object and restricting direct access to its data from outside.
    *   **Benefit:** Protects an object's internal integrity and prevents unintended external modification. Access to the data is typically controlled through public methods (getters and setters).

2.  **Inheritance:**
    *   **Concept:** A mechanism where a new class (called a **child class** or **subclass**) derives properties and methods from an existing class (called a **parent class** or **superclass**). The child class inherits the characteristics of the parent class and can also add its own unique properties and methods or override inherited ones.
    *   **Benefit:** Promotes code reuse and creates a hierarchical relationship between classes ("is-a" relationship, e.g., a `Dog` is an `Animal`).

3.  **Polymorphism:**
    *   **Concept:** Literally means "many forms." It allows objects of different classes to respond to the same method call in their own specific way. This is often achieved through interfaces or abstract classes, where different classes implement the same method signature but provide different implementations.
    *   **Benefit:** Allows for flexible and decoupled code. You can work with objects of different types through a common interface without needing to know their specific class.

4.  **Abstraction:**
    *   **Concept:** Hiding complex implementation details and exposing only the essential features or functionalities of an object to the outside world. Users of an object interact with it through a simplified interface without needing to know the intricate details of how it works internally.
    *   **Benefit:** Simplifies the use of complex systems by providing a high-level view and reducing complexity.

We will explore each of these principles in detail as we go through this section.

## 2. Classes and Objects

### Defining a Class

A **class** is a blueprint or template for creating objects. It defines the properties and methods that all objects of that class will have.

**Syntax:**

```php
<?php
class ClassName {
    // Properties (member variables)
    // Methods (member functions)
}
?>
```

**Naming Conventions:**
Class names in PHP typically follow the **PascalCase** (also known as StudlyCaps) convention, where the first letter of each word is capitalized (e.g., `Car`, `UserProfile`, `DatabaseConnection`).

**Example:**

```php
<?php
class Dog {
    // Properties will be defined here
    // Methods will be defined here
}
?>
```

### Properties (Member Variables)

Properties are variables defined within a class that hold data related to the object. They represent the state or attributes of an object.

**Declaring Properties:**
You declare properties using keywords like `public`, `private`, or `protected` (access modifiers, discussed later), followed by the variable name.

```php
<?php
class Dog {
    public $name; // All Dog objects will have a name
    public $breed; // And a breed
    public $age = 0; // Property with a default value
}
?>
```
*   `public $name;`: Declares a public property named `name`.
*   `public $age = 0;`: Declares a public property `age` with a default value of `0`.

### Methods (Member Functions)

Methods are functions defined within a class. They define the behavior of an object – what an object can do.

**Defining Methods:**
Methods are defined similarly to regular PHP functions but reside inside a class. They also use access modifiers.

```php
<?php
class Dog {
    public $name;
    public $breed;

    // Method to set the dog's name
    public function setName($dogName) {
        $this->name = $dogName; // Use $this to refer to the current object's property
    }

    // Method to get the dog's name
    public function getName() {
        return $this->name;
    }

    // Method to make the dog bark
    public function bark() {
        return "Woof! My name is " . $this->name . ".<br>";
    }
}
?>
```

**The `$this` Pseudo-Variable:**
Inside a class method, the pseudo-variable `$this` refers to the **current object instance**. It's used to access the properties and other methods of that specific object.
*   `$this->name = $dogName;` assigns the value of `$dogName` to the `name` property of the *current* `Dog` object.
*   `$this->name` in `bark()` accesses the `name` property of the *current* `Dog` object.

### Creating Objects (Instantiation)

Creating an object from a class is called **instantiation**. You use the `new` keyword to create an instance of a class.

**Syntax:**
`$objectVariable = new ClassName();`

**Example:**

```php
<?php
class Dog {
    public $name;
    public $breed;

    public function setName($dogName) {
        $this->name = $dogName;
    }

    public function getName() {
        return $this->name;
    }

    public function bark() {
        return "Woof! My name is " . $this->name . ".<br>";
    }
}

// Create (instantiate) Dog objects
$dog1 = new Dog();
$dog2 = new Dog();

// Accessing properties and calling methods
// For dog1
$dog1->name = "Buddy"; // Directly setting public property (generally better to use methods like setName)
$dog1->breed = "Golden Retriever";
echo $dog1->bark(); // Output: Woof! My name is Buddy.

// For dog2
$dog2->setName("Lucy"); // Using a method to set the name
$dog2->breed = "Poodle";
echo "Dog 2's name is: " . $dog2->getName() . "<br>"; // Output: Dog 2's name is: Lucy
echo $dog2->bark(); // Output: Woof! My name is Lucy.

// Accessing properties
echo $dog1->name . " is a " . $dog1->breed . ".<br>"; // Buddy is a Golden Retriever.
?>
```
*   `$object->propertyName`: Accesses a property of the object.
*   `$object->methodName();`: Calls a method of the object.

## 3. Constructors and Destructors

### Constructor: `__construct()`

A constructor is a special method within a class that is automatically called when an object of that class is created using the `new` keyword. It's primarily used to initialize the object's properties or perform any setup tasks needed when the object is created.

**Syntax:**

```php
<?php
class ClassName {
    public function __construct(/* parameters */) {
        // Initialization code
    }
}
?>
```
*   The method name must be `__construct` (two underscores).

**Example:**

```php
<?php
class Car {
    public $make;
    public $model;
    public $year;

    // Constructor method
    public function __construct($carMake, $carModel, $carYear) {
        echo "Car object created for a " . $carMake . "!<br>";
        $this->make = $carMake;
        $this->model = $carModel;
        $this->year = $carYear;
    }

    public function getDetails() {
        return "Make: " . $this->make . ", Model: " . $this->model . ", Year: " . $this->year . "<br>";
    }
}

// Creating objects with constructor arguments
$car1 = new Car("Toyota", "Camry", 2021); // "Car object created for a Toyota!" will be printed
$car2 = new Car("Honda", "Civic", 2022);  // "Car object created for a Honda!" will be printed

echo $car1->getDetails(); // Make: Toyota, Model: Camry, Year: 2021
echo $car2->getDetails(); // Make: Honda, Model: Civic, Year: 2022
?>
```

**PHP 8 Constructor Property Promotion:**
PHP 8 introduced a more concise syntax for constructors where you can declare and initialize properties directly from the constructor parameters.

```php
<?php
// PHP 8 Constructor Property Promotion
class Book {
    // Properties are declared and initialized directly in the constructor
    public function __construct(
        public string $title,
        public string $author,
        public int $yearPublished
    ) {
        echo "Book object for '" . $this->title . "' created.<br>";
    }

    public function getSummary() {
        return "'" . $this->title . "' by " . $this->author . ", published in " . $this->yearPublished . ".<br>";
    }
}

$book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);
echo $book1->getSummary(); // 'The Great Gatsby' by F. Scott Fitzgerald, published in 1925.

echo "Title: " . $book1->title . "<br>"; // Accessing the public promoted property
?>
```
This syntax reduces boilerplate code by automatically creating the property and assigning the value passed to the constructor.

### Destructor: `__destruct()`

A destructor is a special method that is automatically called when an object is no longer referenced or when the script execution ends. It's used for cleanup tasks, such as closing database connections, releasing file handles, or performing other resource-freeing operations.

**Syntax:**

```php
<?php
class ClassName {
    public function __destruct() {
        // Cleanup code
    }
}
?>
```
*   The method name must be `__destruct` (two underscores).
*   Destructors cannot have parameters.

**Example:**

```php
<?php
class FileHandler {
    private $fileHandle;
    private $fileName;

    public function __construct($filename, $mode = "r") {
        $this->fileName = $filename;
        $this->fileHandle = fopen($filename, $mode);
        if ($this->fileHandle) {
            echo "File '" . $this->fileName . "' opened successfully.<br>";
        } else {
            echo "Failed to open file '" . $this->fileName . "'.<br>";
        }
    }

    public function readFile() {
        if ($this->fileHandle) {
            return fread($this->fileHandle, filesize($this->fileName));
        }
        return false;
    }

    // Destructor method
    public function __destruct() {
        if ($this->fileHandle) {
            fclose($this->fileHandle);
            echo "File '" . $this->fileName . "' closed in destructor.<br>";
        }
        echo "FileHandler object for '" . $this->fileName . "' is being destroyed.<br>";
    }
}

// Create a temporary file for testing
file_put_contents("testfile.txt", "Hello from OOP!");

$file = new FileHandler("testfile.txt");
if ($file) {
    // echo "Content: " . $file->readFile() . "<br>";
}
// When $file goes out of scope or script ends, __destruct() is called.
// For example, if we explicitly unset it:
// unset($file);
// Or it will be called at the end of the script.

// Output (order of destructor messages might vary slightly based on script end):
// File 'testfile.txt' opened successfully.
// File 'testfile.txt' closed in destructor.
// FileHandler object for 'testfile.txt' is being destroyed.
?>
```
Destructors are called in the reverse order of their creation if multiple objects are destroyed at the end of the script.

## 4. Access Modifiers (Visibility)

Access modifiers control the visibility and accessibility of class properties and methods from different parts of your code. They are key to implementing encapsulation.

*   **`public`**:
    *   Properties and methods declared as `public` can be accessed from anywhere:
        *   From within the class itself.
        *   From subclasses (derived classes).
        *   From outside the class (e.g., in your main script code).
    *   This is the least restrictive access level. If no access modifier is specified, it defaults to `public` (though explicitly declaring it is good practice).

*   **`private`**:
    *   Properties and methods declared as `private` can **only** be accessed from within the class that defines them.
    *   They are not accessible from subclasses or from outside the class.
    *   This is the most restrictive access level, used to hide internal implementation details.

*   **`protected`**:
    *   Properties and methods declared as `protected` can be accessed:
        *   From within the class itself.
        *   From subclasses (classes that inherit from this class).
    *   They are not accessible from outside the class.
    *   This is useful when you want to allow child classes to use or modify certain parts of the parent class, but still keep those parts hidden from the outside world.

**Purpose:**
Access modifiers help enforce encapsulation. By making properties `private` or `protected`, you prevent direct external modification and control access through `public` methods (getters and setters).

**Example:**

```php
<?php
class BankAccount {
    public $accountNumber;      // Accessible from anywhere
    private $balance;           // Only accessible within BankAccount class
    protected $accountHolder;   // Accessible within BankAccount and its subclasses

    public function __construct($accNum, $holder, $initialBalance) {
        $this->accountNumber = $accNum;
        $this->accountHolder = $holder;
        $this->setBalance($initialBalance); // Use a setter for initial balance
    }

    // Public method to deposit money
    public function deposit($amount) {
        if ($amount > 0) {
            $this->balance += $amount;
            echo "Deposited: $" . $amount . ". New balance: $" . $this->balance . "<br>";
        } else {
            echo "Deposit amount must be positive.<br>";
        }
    }

    // Public method to get the balance (Getter)
    public function getBalance() {
        // Could add logging or other checks here
        return $this->balance;
    }

    // Private method to set balance (Setter) - internal use or controlled access
    private function setBalance($amount) {
        if ($amount >= 0) {
            $this->balance = $amount;
        } else {
            $this->balance = 0;
            echo "Initial balance cannot be negative. Set to 0.<br>";
        }
    }

    public function getAccountHolder() {
        return $this->accountHolder;
    }
}

class SavingsAccount extends BankAccount {
    private $interestRate;

    public function __construct($accNum, $holder, $initialBalance, $rate) {
        parent::__construct($accNum, $holder, $initialBalance); // Call parent constructor
        $this->interestRate = $rate;
    }

    public function applyInterest() {
        $interest = $this->getBalance() * $this->interestRate; // Accesses public getBalance()
        // $this->balance += $interest; // ERROR! Cannot access private $balance directly from subclass
        $this->deposit($interest); // Correct way: use public deposit method
        echo "Interest applied. Account Holder: " . $this->accountHolder . "<br>"; // OK: $accountHolder is protected
    }
}

$account1 = new BankAccount("12345", "John Doe", 100);
echo "Account Number: " . $account1->accountNumber . "<br>"; // OK: public
// echo "Balance: " . $account1->balance; // ERROR! Cannot access private property $balance
echo "Balance: $" . $account1->getBalance() . "<br>"; // OK: using public getter

$account1->deposit(50);
// $account1->setBalance(5000); // ERROR! Cannot call private method setBalance() from outside

$savings1 = new SavingsAccount("67890", "Jane Smith", 500, 0.05);
$savings1->deposit(100);
$savings1->applyInterest();
echo "Savings Balance: $" . $savings1->getBalance() . "<br>";
echo "Savings Holder: " . $savings1->getAccountHolder() . "<br>"; // Accessing protected property via public method

?>
```

**Getter and Setter Methods:**
When a property is `private` or `protected`, you often provide `public` methods to get its value (getter) and set its value (setter). This allows you to control how the property is accessed and modified, adding validation or other logic if needed.

*   **Getter:** `public function getPropertyName() { return $this->propertyName; }`
*   **Setter:** `public function setPropertyName($value) { /* validation */ $this->propertyName = $value; }`

## 5. Inheritance

Inheritance is a fundamental OOP principle that allows a class (child class or subclass) to inherit properties and methods from another class (parent class or superclass). This promotes code reuse and establishes an "is-a" relationship (e.g., a `Manager` is an `Employee`).

**Syntax:**
`class ChildClass extends ParentClass { ... }`

**Example:**

```php
<?php
// Parent class (Superclass)
class Animal {
    public $name;
    protected $sound; // Protected so child classes can access it

    public function __construct($name, $sound = "Some generic sound") {
        $this->name = $name;
        $this->sound = $sound;
        echo $this->name . " the Animal created.<br>";
    }

    public function eat() {
        echo $this->name . " is eating.<br>";
    }

    public function makeSound() {
        echo $this->name . " says " . $this->sound . ".<br>";
    }
}

// Child class (Subclass) inheriting from Animal
class Dog extends Animal {
    public $breed;

    // Dog's own constructor
    public function __construct($name, $breed) {
        // Call the parent class's constructor
        parent::__construct($name, "Woof"); // Pass "Woof" as the sound for dogs
        $this->breed = $breed;
        echo $this->name . " the Dog of breed " . $this->breed . " created.<br>";
    }

    // Dog-specific method
    public function fetch() {
        echo $this->name . " is fetching a ball.<br>";
    }

    // Overriding the makeSound method from the parent class
    public function makeSound() {
        echo $this->name . " (a " . $this->breed . ") barks loudly: " . $this->sound . "!<br>";
        // You can also call the parent's version if needed:
        // parent::makeSound();
    }
}

class Cat extends Animal {
    public function __construct($name) {
        parent::__construct($name, "Meow");
        echo $this->name . " the Cat created.<br>";
    }

    // Cat-specific method
    public function climbTree() {
        echo $this->name . " is climbing a tree.<br>";
    }

    // Overriding makeSound
    public function makeSound() {
        echo $this->name . " purrs: " . $this->sound . "...<br>";
    }
}

$genericAnimal = new Animal("Generic Creature");
$genericAnimal->eat();
$genericAnimal->makeSound();
echo "<hr>";

$dog = new Dog("Buddy", "Golden Retriever");
$dog->eat();         // Inherited from Animal
$dog->makeSound();   // Overridden in Dog
$dog->fetch();       // Specific to Dog
echo "<hr>";

$cat = new Cat("Whiskers");
$cat->eat();         // Inherited
$cat->makeSound();   // Overridden in Cat
$cat->climbTree();   // Specific to Cat
?>
```

**Key Concepts in Inheritance:**

*   **Reusing Functionality:** The `Dog` and `Cat` classes automatically get the `name` property and the `eat()` method from the `Animal` class.
*   **Extending Functionality:** Child classes can add their own properties (like `$breed` in `Dog`) and methods (like `fetch()` in `Dog` or `climbTree()` in `Cat`).
*   **Overriding Methods:** A child class can provide its own specific implementation of a method that it inherited from the parent class (e.g., `makeSound()` is overridden in both `Dog` and `Cat`). The method signature (name, parameters, and access modifier compatibility) should generally remain the same or be less restrictive.
*   **`parent::` Keyword:**
    Inside a child class, you can use `parent::` to access methods and properties from the parent class. This is often used in constructors (`parent::__construct()`) or when you want to extend the parent's method functionality rather than completely replacing it.
    ```php
    class MyChildClass extends MyParentClass {
        public function someMethod() {
            parent::someMethod(); // Call parent's version
            // Add more child-specific logic here
            echo "Child class extending functionality.<br>";
        }
    }
    ```
*   **`final` Keyword:**
    *   If you declare a method as `final` in the parent class, it **cannot be overridden** by child classes.
      `public final function cannotBeOverridden() { ... }`
    *   If you declare a class as `final`, it **cannot be inherited** (i.e., no other class can extend it).
      `final class NoMoreChildren { ... }`

## 6. Abstract Classes and Methods

An **abstract class** is a class that cannot be instantiated directly. It's designed to be a base class that other classes inherit from. Abstract classes can contain both regular methods (with implementation) and **abstract methods**.

An **abstract method** is declared in an abstract class but does not contain any implementation (no body). It only defines the method signature (name, parameters, and return type if any). Subclasses that inherit from an abstract class **must** provide concrete implementations for all abstract methods declared in the parent abstract class.

**Syntax:**

```php
<?php
abstract class AbstractClassName {
    // Regular properties and methods
    public $name;

    public function regularMethod() {
        echo "This is a regular method in an abstract class.<br>";
    }

    // Abstract method (no body, just signature)
    abstract protected function abstractMethod($param);
    // Abstract methods can also be public
    // abstract public function anotherAbstractMethod();
}

class ConcreteClass extends AbstractClassName {
    // Must implement all abstract methods from AbstractClassName
    protected function abstractMethod($param) {
        echo "Implementation of abstractMethod with parameter: " . $param . "<br>";
        $this->name = $param; // Can use properties from abstract class
    }

    public function printName() {
        echo "Name from ConcreteClass: " . $this->name . "<br>";
    }
}

// $obj1 = new AbstractClassName(); // ERROR! Cannot instantiate abstract class.
$obj2 = new ConcreteClass();
$obj2->regularMethod(); // Calls method from abstract class
$obj2->abstractMethod("Test Value");
$obj2->printName(); // Name from ConcreteClass: Test Value
?>
```

**Purpose and Use Cases:**

*   **Defining a Template/Contract:** Abstract classes define a common structure and a set of methods that all subclasses must adhere to, ensuring consistency.
*   **Code Reusability:** They can provide common functionality (implemented methods) that subclasses can share, while forcing subclasses to implement specific behaviors (abstract methods).
*   Useful when you have a group of related classes that share some common logic but also have specific implementations for certain actions. For example, a `Shape` abstract class could have an abstract `calculateArea()` method, and concrete classes like `Circle` and `Rectangle` would implement it differently.

**Rules for Abstract Classes/Methods:**

*   A class containing at least one abstract method must itself be declared abstract.
*   Abstract methods cannot have a body (implementation) in the abstract class.
*   Abstract methods must be implemented by any child class that is not itself abstract.
*   The visibility of the implemented method in the child class must be the same or less restrictive than the abstract method's visibility (e.g., if abstract method is `protected`, implementation can be `protected` or `public`, but not `private`).
*   The signature (number and type of parameters, return type) of the implemented method must match the abstract method's signature.

## 7. Interfaces

An **interface** defines a contract for what methods a class should implement. It specifies the method signatures (name, parameters, return type) but does not provide any implementation for those methods.

When a class **implements** an interface, it is obligated to provide concrete implementations for all methods declared in that interface.

**Syntax:**

```php
<?php
// Define an interface
interface InterfaceName {
    public function methodName1($param1);
    public function methodName2(): string; // Can specify return types
    // Interfaces can also define public constants
    // const MY_CONSTANT = 'value';
}

// Class implementing the interface
class MyClass implements InterfaceName /*, AnotherInterface, ... */ {
    public function methodName1($param1) {
        echo "Implementation of methodName1 with: " . $param1 . "<br>";
    }

    public function methodName2(): string {
        return "Implementation of methodName2.<br>";
    }
}

$obj = new MyClass();
$obj->methodName1("Hello");
echo $obj->methodName2();
?>
```

**Key Features and Rules:**

*   **Contract:** Interfaces ensure that different classes provide a common set of functionalities, making them interchangeable in certain contexts (polymorphism).
*   **`implements` Keyword:** A class uses the `implements` keyword to adopt an interface.
*   **Multiple Interfaces:** A class can implement multiple interfaces by listing them separated by commas: `class MyClass implements InterfaceA, InterfaceB { ... }`.
*   **Methods are Implicitly Public and Abstract:** All methods declared in an interface are implicitly `public` and `abstract`. You don't need to write `public abstract function ...`, just `public function ...` (or even just `function ...` as `public` is implied).
*   **No Properties:** Interfaces cannot declare properties (member variables), though they can declare `public const`ants.
*   **No Implementation:** Interface methods cannot have bodies.

**Differences between Interfaces and Abstract Classes:**

| Feature           | Abstract Class                                     | Interface                                           |
| ----------------- | -------------------------------------------------- | --------------------------------------------------- |
| **Instantiation** | Cannot be instantiated.                            | Cannot be instantiated.                             |
| **Methods**       | Can have both abstract and concrete methods.       | All methods are implicitly abstract (no implementation). |
| **Properties**    | Can have properties (member variables).            | Cannot have properties (can have constants).        |
| **Inheritance**   | A class can `extend` only **one** abstract class.  | A class can `implement` **multiple** interfaces.    |
| **Purpose**       | Provide a base class with some shared implementation and a template for subclasses. Establishes an "is-a" relationship. | Define a contract of methods a class *must* implement. Establishes a "can-do" or "behaves-like" relationship. |
| **Constructors**  | Can have constructors.                             | Cannot have constructors.                           |

**When to Use Which:**

*   **Use an abstract class when:**
    *   You want to share code (implemented methods) among several closely related classes.
    *   You expect that classes that extend your abstract class have many common methods or properties, or require access modifiers other than public for some base functionality.
    *   You want to define a common base for a hierarchy of classes.
*   **Use an interface when:**
    *   You want to define a contract that unrelated classes can implement.
    *   You want to specify the behavior of a particular data type, but you are not concerned about who implements its behavior.
    *   You want to take advantage of multiple inheritance of type (a class can implement multiple interfaces).

## 8. Polymorphism

Polymorphism, meaning "many forms," is a core OOP concept that allows objects of different classes to be treated as objects of a common superclass or interface. They can respond to the same method call in their own distinct ways.

**Example demonstrating Polymorphism using an Interface:**

```php
<?php
// Interface defining a contract for loggable objects
interface Loggable {
    public function getLogMessage(): string;
}

// Class 1 implementing Loggable
class User implements Loggable {
    private $username;

    public function __construct($username) {
        $this->username = $username;
    }

    public function getLogMessage(): string {
        return "User '" . $this->username . "' performed an action.";
    }
}

// Class 2 implementing Loggable
class Product implements Loggable {
    private $productName;

    public function __construct($productName) {
        $this->productName = $productName;
    }

    public function getLogMessage(): string {
        return "Product '" . $this->productName . "' was updated.";
    }
}

// Class 3, also loggable
class Order implements Loggable {
    private $orderId;

    public function __construct($orderId) {
        $this->orderId = $orderId;
    }

    public function getLogMessage(): string {
        return "Order ID '" . $this->orderId . "' status changed.";
    }
}

// A function that can work with any object that implements Loggable
function writeToLog(Loggable $loggableObject) {
    // $loggableObject can be a User, a Product, or an Order
    $message = $loggableObject->getLogMessage(); // Calls the specific implementation
    echo "Logging: " . $message . "<br>";
    // In a real app, this would write to a file or database
}

// Create objects of different types
$user = new User("Alice");
$product = new Product("Laptop XYZ");
$order = new Order("ORD12345");

// Pass them to the same function - Polymorphism in action!
writeToLog($user);    // Logging: User 'Alice' performed an action.
writeToLog($product); // Logging: Product 'Laptop XYZ' was updated.
writeToLog($order);   // Logging: Order ID 'ORD12345' status changed.

// You can also store them in an array of Loggable items
$loggableItems = [$user, $product, $order];
foreach ($loggableItems as $item) {
    // $item is treated as Loggable, its specific getLogMessage() is called
    echo "Processing log for item: " . $item->getLogMessage() . "<br>";
}
?>
```
In this example, `writeToLog()` doesn't need to know if it's dealing with a `User`, `Product`, or `Order` object. It only cares that the object implements the `Loggable` interface and thus has a `getLogMessage()` method. Each object type provides its own version of `getLogMessage()`, demonstrating polymorphism.

## 9. Traits

Traits are a mechanism for code reuse in single-inheritance languages like PHP. A class can only inherit from one parent class, but it can use multiple traits. Traits allow you to compose sets of methods into classes.

**Syntax:**

```php
<?php
trait TraitName {
    // Properties and methods
    public $traitProperty = "Trait Value";

    public function traitMethod() {
        return "This is a method from TraitName.<br>";
    }
}

class MyClassWithTrait {
    use TraitName; // Use the trait in this class

    public function ownMethod() {
        return "This is from MyClassWithTrait. Property from trait: " . $this->traitProperty . "<br>";
    }
}

class AnotherClassWithTrait {
    use TraitName;
    // Can have its own methods and properties
}

$obj = new MyClassWithTrait();
echo $obj->traitMethod();    // This is a method from TraitName.
echo $obj->ownMethod();      // This is from MyClassWithTrait. Property from trait: Trait Value

$obj2 = new AnotherClassWithTrait();
echo $obj2->traitMethod();   // This is a method from TraitName.
$obj2->traitProperty = "New Value for obj2";
echo $obj2->traitProperty . "<br>"; // New Value for obj2
echo $obj->traitProperty . "<br>";  // Trait Value (obj's property is separate)
?>
```

**Key Features:**

*   **Code Reuse:** Share methods (and properties) across multiple independent classes that do not share a common parent.
*   **`use` Keyword:** A class incorporates a trait's methods and properties using the `use` keyword within the class definition.
*   **Horizontal Composition:** Traits offer a way to compose functionality horizontally, as opposed to the vertical composition of inheritance.
*   **Precedence:** Methods from the current class override methods from a trait, which in turn override methods from a parent class.
    *   Current Class Method > Trait Method > Inherited Parent Method.

**Conflict Resolution:**
If two traits define a method with the same name, or a trait has a method with the same name as one in the class itself or its parent class, PHP will raise a fatal error unless the conflict is explicitly resolved.

*   **`insteadof`:** To specify which trait's method to use when there's a name collision between traits.
*   **`as`:** To change the visibility or alias a method from a trait.

```php
<?php
trait Sharable {
    public function share($item) {
        echo "Sharing " . $item . " via Sharable trait.<br>";
    }
}

trait Likeable {
    public function share($item) { // Same method name as in Sharable
        echo "Sharing " . $item . " via Likeable trait (different implementation).<br>";
    }

    public function like($item) {
        echo "Liking " . $item . ".<br>";
    }
}

class SocialPost {
    use Sharable, Likeable {
        Likeable::share insteadof Sharable; // Use Likeable's share()
        Sharable::share as shareViaSharable; // Alias Sharable's share() to a new name
        Likeable::like as public likeItem; // Can change visibility (though 'like' is already public)
    }

    public $content;
    public function __construct($content) {
        $this->content = $content;
    }
}

$post = new SocialPost("My new blog post");
$post->share($post->content);           // Sharing My new blog post via Likeable trait.
$post->shareViaSharable($post->content); // Sharing My new blog post via Sharable trait.
$post->likeItem($post->content);        // Liking My new blog post.
?>
```

Traits are a powerful tool for organizing and reusing code in a flexible way.

## 10. Static Methods and Properties

Static methods and properties belong to the **class itself**, not to a specific instance (object) of the class. They are accessed directly using the class name and the scope resolution operator (`::`).

**Declaring Static Members:**
Use the `static` keyword.

```php
<?php
class MathUtils {
    public static $pi = 3.14159; // Static property

    // Static method
    public static function add($a, $b) {
        return $a + $b;
    }

    public static function circleArea($radius) {
        // Accessing static property from within the class using self::
        return self::$pi * $radius * $radius;
        // Or using ClassName::$pi: return MathUtils::$pi * $radius * $radius;
    }

    public function nonStaticMethod() {
        echo "This is a non-static method. PI is " . self::$pi . "<br>";
        // Non-static methods can access static properties/methods.
    }
}

// Accessing static property
echo "Value of PI: " . MathUtils::$pi . "<br>"; // Value of PI: 3.14159

// Calling static method
$sum = MathUtils::add(5, 10);
echo "Sum: " . $sum . "<br>"; // Sum: 15

echo "Area of circle with radius 5: " . MathUtils::circleArea(5) . "<br>"; // Area of circle with radius 5: 78.53975

// Static members are accessed via the class, not an instance
// $mathObj = new MathUtils();
// echo $mathObj::$pi; // Works but not recommended, use ClassName::$pi
// echo $mathObj::add(2,3); // Works but not recommended, use ClassName::add()

$mathObj = new MathUtils();
$mathObj->nonStaticMethod(); // Accessing static property from non-static method context is fine.

// Note: Inside a static method, you cannot use $this because $this refers to an object instance,
// and static methods are not tied to a specific object.
// You use `self::` to refer to static members of the current class.
?>
```

**Key Points:**

*   **No Object Instance Needed:** Static members can be used without creating an object of the class.
*   **`self::` Keyword:** Inside a class, use `self::` to access static properties or call static methods of the *same* class.
    *   `self::$staticProperty`
    *   `self::staticMethod()`
*   **`parent::` Keyword:** Can also be used to access static members of a parent class.
*   **State:** Static properties share a single value across all uses of the class, not per object.
*   **`$this` in Static Methods:** The `$this` pseudo-variable is **not available** inside static methods because they are not associated with a specific object instance. Trying to use `$this` in a static method will result in an error.
*   **Use Cases:**
    *   **Utility functions:** Functions that perform a task but don't need to rely on the state of a specific object (e.g., `MathUtils::add()`).
    *   **Counters or global settings for a class:** E.g., counting how many objects of a class have been instantiated.
    *   **Factory methods:** Static methods that create and return instances of the class (e.g., `User::createGuestUser()`).

## 11. Namespaces

Namespaces are a way of encapsulating items (classes, interfaces, functions, constants) to prevent naming collisions. Imagine two different libraries you want to use in your project, and both define a class named `Logger`. Without namespaces, PHP wouldn't know which `Logger` class you intend to use.

**Purpose:**

*   **Prevent Naming Conflicts:** Allows you to use the same class or function name in different parts of your code or from different libraries without conflict.
*   **Organize Code:** Helps organize code into logical groups, similar to how directories organize files.

**Defining a Namespace:**
The `namespace` keyword is used to declare a namespace. It must be the **first statement** in a PHP file (before any other code, except `declare` statements).

```php
<?php
// File: MyProject/Utils/Logger.php
namespace MyProject\Utils; // Declares the namespace

class Logger {
    public static function log($message) {
        echo "MyProject Log: " . $message . "<br>";
    }
}

// File: AnotherProject/Logging/Logger.php
namespace AnotherProject\Logging; // A different namespace

class Logger { // Same class name, but in a different namespace
    public static function log($message) {
        echo "AnotherProject Log: " . $message . "<br>";
    }
}
?>
```

**Using Namespaces:**
To use classes, interfaces, functions, or constants from a namespace, you can:

1.  **Fully Qualified Name:** Refer to them by their full name including the namespace.
    ```php
    <?php
    require 'MyProject/Utils/Logger.php';
    require 'AnotherProject/Logging/Logger.php';

    MyProject\Utils\Logger::log("System started.");
    // Output: MyProject Log: System started.

    AnotherProject\Logging\Logger::log("User logged in.");
    // Output: AnotherProject Log: User logged in.
    ?>
    ```

2.  **`use` Keyword (Importing/Aliasing):** The `use` keyword allows you to import a namespace or a specific class/interface/etc. from a namespace, so you can refer to it with a shorter name or an alias.

    ```php
    <?php
    require 'MyProject/Utils/Logger.php';
    require 'AnotherProject/Logging/Logger.php';

    use MyProject\Utils\Logger; // Import MyProject's Logger
    use AnotherProject\Logging\Logger as AnotherLogger; // Import and alias AnotherProject's Logger

    Logger::log("System check.");        // Uses MyProject\Utils\Logger
    // Output: MyProject Log: System check.

    AnotherLogger::log("Data processed."); // Uses AnotherProject\Logging\Logger
    // Output: AnotherProject Log: Data processed.

    // You can also import an entire namespace
    use MyProject\Utils;
    // Utils\Logger::log("Namespace import."); // Then use relative to imported namespace

    // To use multiple classes from the same namespace (PHP 7+):
    // use MyProject\Utils\{Logger, Formatter, Helper};
    ?>
    ```

**Referring to Global Classes/Functions:**
If you are inside a namespace and want to refer to a class, function, or constant from the global scope (i.e., not namespaced, like PHP's built-in classes), you need to prefix it with a backslash `\`.

```php
<?php
namespace MyCustomApp;

class DateTimeHandler {
    public function getCurrentDateTime() {
        // Without '\', PHP would look for MyCustomApp\DateTime
        $dateTime = new \DateTime('now', new \DateTimeZone('UTC'));
        return $dateTime->format('Y-m-d H:i:s');
    }
}

$handler = new DateTimeHandler();
echo $handler->getCurrentDateTime() . "<br>"; // e.g., 2023-10-27 10:00:00

// Calling a global function:
$length = \strlen("test"); // Refers to global strlen()
echo "Length: " . $length . "<br>"; // Length: 4
?>
```
Namespaces are essential for modern PHP development, especially when working with third-party libraries or building large applications.

## 12. Autoloading Classes

In PHP projects, you might have many class files. Including each one manually using `require` or `include` at the beginning of your scripts can be cumbersome and error-prone.

`require 'classes/User.php';`
`require 'classes/Product.php';`
`require 'classes/Order.php';`
`// ... and so on`

**Autoloading** solves this problem by automatically loading class files when they are first needed in your code.

**`spl_autoload_register()` Function:**
PHP provides the `spl_autoload_register()` function to register one or more "autoloader" functions. When your code tries to use a class that hasn't been defined yet, PHP will call each registered autoloader function in sequence, passing it the name of the class it's trying to load. The autoloader function is then responsible for locating and including the file for that class.

**How Autoloaders Typically Work (Convention-Based):**
A common convention is to name your class files in a way that directly maps to the class name (and its namespace). For example:
*   Class `MyProject\Utils\Logger` would be in the file `MyProject/Utils/Logger.php`.
*   Class `User` (global namespace) would be in `User.php` or `classes/User.php`.

**Example of a Simple Autoloader:**

```php
<?php
// This would typically be in a central bootstrap file or config file

spl_autoload_register(function ($className) {
    // Replace namespace separators with directory separators
    $filePath = str_replace('\\', DIRECTORY_SEPARATOR, $className) . '.php';

    echo "Trying to load: " . $className . " from path: " . $filePath . "<br>";

    // Check if the file exists and include it
    if (file_exists($filePath)) {
        require_once $filePath;
        echo "Successfully loaded " . $className . ".<br>";
    } else {
        echo "Could not find file: " . $filePath . " for class " . $className . ".<br>";
    }
});

// --- Now, if you try to use classes, the autoloader will kick in ---

// Assume you have files:
// MyLib/Network/Request.php containing class MyLib\Network\Request
// Services/AuthService.php containing class Services\AuthService

/*
// Example usage (create these files to test):
// File: MyLib/Network/Request.php
namespace MyLib\Network;
class Request { public function __construct() { echo "MyLib\Network\Request loaded and instantiated!<br>"; } }

// File: Services/AuthService.php
namespace Services;
class AuthService { public function __construct() { echo "Services\AuthService loaded and instantiated!<br>"; } }
*/

// To test, create these files in the correct directory structure relative to this script.
// For example, if this script is in /var/www/html/, create /var/www/html/MyLib/Network/Request.php etc.

// $request = new MyLib\Network\Request();
// $auth = new Services\AuthService();

// If the files exist at the expected paths, the output would be:
// Trying to load: MyLib\Network\Request from path: MyLib/Network/Request.php
// Successfully loaded MyLib\Network\Request.
// MyLib\Network\Request loaded and instantiated!
// Trying to load: Services\AuthService from path: Services/AuthService.php
// Successfully loaded Services\AuthService.
// Services\AuthService loaded and instantiated!
?>
```
**Note:** For the above autoloader example to work correctly, you need to create the directory structure and files (e.g., `MyLib/Network/Request.php` containing the `MyLib\Network\Request` class) relative to where the script with `spl_autoload_register` is running.

**Composer's Autoloader:**
In modern PHP development, the vast majority of projects use **Composer**, a dependency manager for PHP. Composer generates a highly optimized autoloader file (`vendor/autoload.php`) that can load classes based on configurations in your `composer.json` file (e.g., PSR-4 autoloading standard).

To use Composer's autoloader:
1.  Install Composer ([https://getcomposer.org/](https://getcomposer.org/)).
2.  Define your autoloading rules in `composer.json`.
3.  Run `composer dump-autoload` or `composer install`.
4.  Include Composer's autoloader in your project's entry point:
    `require_once 'vendor/autoload.php';`

This is the standard and recommended way to handle autoloading in contemporary PHP projects.

## 13. Magic Methods

Magic methods in PHP are special methods that are automatically called by PHP when certain actions are performed on an object. They are recognizable by their double underscore (`__`) prefix. We've already seen `__construct()` and `__destruct()`.

Here are some other common magic methods:

*   **`__get($property)`:**
    *   Called when trying to read an inaccessible (private or protected) or non-existent property.
    *   Allows you to implement "virtual" properties.
    ```php
    <?php
    class DataStore {
        private $data = [];
        public function __set($name, $value) { $this->data[$name] = $value; }
        public function __get($name) {
            echo "Accessing '{$name}' via __get.<br>";
            return $this->data[$name] ?? null;
        }
    }
    $store = new DataStore();
    $store->name = "Alice"; // Calls __set()
    echo $store->name . "<br>"; // Calls __get()
    ?>
    ```

*   **`__set($property, $value)`:**
    *   Called when trying to write to an inaccessible or non-existent property.
    ```php
    <?php
    class UserProfile {
        private $properties = [];
        public function __set($key, $val) {
            echo "Setting '{$key}' to '{$val}' via __set.<br>";
            $this->properties[$key] = strtoupper($val); // Example: always store uppercase
        }
        public function __get($key) { return $this->properties[$key] ?? null; }
    }
    $profile = new UserProfile();
    $profile->city = "London"; // Calls __set()
    echo $profile->city . "<br>"; // LONDON
    ?>
    ```

*   **`__isset($property)`:**
    *   Triggered by calling `isset()` or `empty()` on an inaccessible or non-existent property.
    *   Should return `true` if the property exists and is not null, `false` otherwise.

*   **`__unset($property)`:**
    *   Called when `unset()` is used on an inaccessible or non-existent property.

*   **`__call($method, $arguments)`:**
    *   Called when an inaccessible or non-existent instance method is invoked.
    *   `$method`: The name of the method being called.
    *   `$arguments`: An array of arguments passed to the method.
    ```php
    <?php
    class DynamicCaller {
        public function __call($methodName, $args) {
            echo "Attempted to call method '{$methodName}' with arguments: ";
            print_r($args);
            echo "<br>";
            if ($methodName == 'doSomething') {
                return "Did something with " . implode(', ', $args) . "<br>";
            }
            return "Method '{$methodName}' not found.<br>";
        }
    }
    $caller = new DynamicCaller();
    echo $caller->doSomething("param1", "param2"); // Calls __call()
    echo $caller->anotherMethod();                // Calls __call()
    ?>
    ```

*   **`__callStatic($method, $arguments)`:**
    *   Similar to `__call()`, but for inaccessible or non-existent static methods.

*   **`__toString()`:**
    *   Called when an object is treated as a string (e.g., using `echo $object;` or string concatenation).
    *   The method **must** return a string.
    ```php
    <?php
    class Point {
        public function __construct(public int $x, public int $y) {}
        public function __toString(): string {
            return "Point({$this->x}, {$this->y})";
        }
    }
    $p = new Point(10, 20);
    echo $p . "<br>"; // Outputs: Point(10, 20)
    ?>
    ```

*   **`__clone()`:**
    *   Called when an object is cloned using the `clone` keyword (e.g., `$newObj = clone $oldObj;`).
    *   Useful for customizing the cloning process, especially for objects that contain references to other objects (deep cloning).

*   **`__invoke(...$arguments)`:**
    *   Allows an object to be called as if it were a function.
    ```php
    <?php
    class CallableObject {
        public function __invoke($param) {
            echo "Object called as a function with parameter: " . $param . "<br>";
        }
    }
    $cObj = new CallableObject();
    $cObj("Test"); // Outputs: Object called as a function with parameter: Test
    ?>
    ```

Magic methods provide powerful ways to control object behavior and create more dynamic and flexible classes. However, they should be used judiciously as they can sometimes make code harder to understand if overused or used inappropriately.

This concludes our overview of Object-Oriented Programming in PHP. Mastering these concepts will enable you to write more robust, scalable, and maintainable PHP applications. Remember to practice by building your own classes and exploring how these principles work together.Okay, I've drafted the content for the "PHP Object-Oriented Programming (OOP)" section. It's quite comprehensive and covers all the requested topics, from basic concepts to more advanced features like traits, namespaces, and magic methods. I've included explanations and code examples for each.
