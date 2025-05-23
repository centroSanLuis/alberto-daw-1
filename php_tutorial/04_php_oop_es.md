# Programación Orientada a Objetos (POO) en PHP

La Programación Orientada a Objetos (POO) es un paradigma de programación potente que proporciona una forma de estructurar el código para crear aplicaciones más organizadas, reutilizables y mantenibles. PHP tiene un soporte robusto para la POO, y entender sus principios es crucial para construir aplicaciones web modernas y escalables.

## 1. Introducción a los Conceptos de POO

### ¿Qué es la POO?

La POO es un modelo de programación basado en el concepto de "objetos". Un objeto es una unidad autónoma que combina datos (propiedades o atributos) y comportamiento (métodos o funciones que operan sobre esos datos). En lugar de escribir procedimientos o funciones que operan sobre datos separados, la POO te anima a crear objetos que gestionan sus propios datos y comportamiento.

Piensa en objetos del mundo real: un coche es un objeto. Tiene propiedades (color, modelo, velocidad) y métodos (arrancarMotor, acelerar, frenar). La POO te permite modelar tales entidades del mundo real en tu código.

### ¿Por Qué Usar POO?

*   **Código Organizado:** La POO ayuda a estructurar el código de una manera que refleja las entidades del mundo real, haciéndolo más intuitivo y fácil de gestionar, especialmente para proyectos grandes.
*   **Reutilización (DRY - No Te Repitas):** Las clases, los planos para los objetos, pueden reutilizarse para crear múltiples objetos del mismo tipo. La herencia permite crear nuevas clases que reutilizan código de las existentes.
*   **Escalabilidad:** El código POO bien estructurado es más fácil de extender y escalar a medida que crecen los requisitos del proyecto. A menudo se pueden añadir nuevas características creando nuevas clases o extendiendo las existentes sin cambiar drásticamente el código base existente.
*   **Mantenibilidad:** La POO promueve la modularidad. Los cambios en una parte de la aplicación (por ejemplo, modificar una clase) tienen menos probabilidades de romper otras partes, lo que facilita el mantenimiento y la depuración del código.
*   **Colaboración:** Diferentes desarrolladores pueden trabajar en diferentes clases simultáneamente, a menudo con menos conflictos.

### Principios Fundamentales de la POO

Hay cuatro principios principales que forman la base de la POO:

1.  **Encapsulación:**
    *   **Concepto:** Agrupar los datos (propiedades) y los métodos que operan sobre esos datos dentro de una única unidad (un objeto). También implica **ocultar el estado interno** de un objeto y restringir el acceso directo a sus datos desde el exterior.
    *   **Beneficio:** Protege la integridad interna de un objeto y previene modificaciones externas no deseadas. El acceso a los datos se controla típicamene a través de métodos públicos (getters y setters).

2.  **Herencia:**
    *   **Concepto:** Un mecanismo donde una nueva clase (llamada **clase hija** o **subclase**) deriva propiedades y métodos de una clase existente (llamada **clase padre** o **superclase**). La clase hija hereda las características de la clase padre y también puede añadir sus propias propiedades y métodos únicos o sobrescribir los heredados.
    *   **Beneficio:** Promueve la reutilización de código y crea una relación jerárquica entre clases (relación "es-un", por ejemplo, un `Perro` es un `Animal`).

3.  **Polimorfismo:**
    *   **Concepto:** Literalmente significa "muchas formas". Permite que objetos de diferentes clases respondan a la misma llamada de método de su propia manera específica. Esto se logra a menudo a través de interfaces o clases abstractas, donde diferentes clases implementan la misma firma de método pero proporcionan diferentes implementaciones.
    *   **Beneficio:** Permite un código flexible y desacoplado. Puedes trabajar con objetos de diferentes tipos a través de una interfaz común sin necesidad de conocer su clase específica.

4.  **Abstracción:**
    *   **Concepto:** Ocultar detalles complejos de implementación y exponer solo las características o funcionalidades esenciales de un objeto al mundo exterior. Los usuarios de un objeto interactúan con él a través de una interfaz simplificada sin necesidad de conocer los intrincados detalles de cómo funciona internamente.
    *   **Beneficio:** Simplifica el uso de sistemas complejos al proporcionar una vista de alto nivel y reducir la complejidad.

Exploraremos cada uno de estos principios en detalle a medida que avanzamos en esta sección.

## 2. Clases y Objetos

### Definiendo una Clase

Una **clase** es un plano o plantilla para crear objetos. Define las propiedades y métodos que todos los objetos de esa clase tendrán.

**Sintaxis:**

```php
<?php
class NombreClase {
    // Propiedades (variables miembro)
    // Métodos (funciones miembro)
}
?>
```

**Convenciones de Nomenclatura:**
Los nombres de las clases en PHP típicamente siguen la convención **PascalCase** (también conocida como StudlyCaps), donde la primera letra de cada palabra está en mayúscula (por ejemplo, `Coche`, `PerfilUsuario`, `ConexionBaseDatos`).

**Ejemplo:**

```php
<?php
class Perro {
    // Las propiedades se definirán aquí
    // Los métodos se definirán aquí
}
?>
```

### Propiedades (Variables Miembro)

Las propiedades son variables definidas dentro de una clase que almacenan datos relacionados con el objeto. Representan el estado o atributos de un objeto.

**Declarando Propiedades:**
Declaras propiedades usando palabras clave como `public`, `private`, o `protected` (modificadores de acceso, discutidos más adelante), seguidas por el nombre de la variable.

```php
<?php
class Perro {
    public $nombre; // Todos los objetos Perro tendrán un nombre
    public $raza;   // Y una raza
    public $edad = 0; // Propiedad con un valor predeterminado
}
?>
```
*   `public $nombre;`: Declara una propiedad pública llamada `nombre`.
*   `public $edad = 0;`: Declara una propiedad pública `edad` con un valor predeterminado de `0`.

### Métodos (Funciones Miembro)

Los métodos son funciones definidas dentro de una clase. Definen el comportamiento de un objeto – lo que un objeto puede hacer.

**Definiendo Métodos:**
Los métodos se definen de manera similar a las funciones PHP regulares pero residen dentro de una clase. También usan modificadores de acceso.

```php
<?php
class Perro {
    public $nombre;
    public $raza;

    // Método para establecer el nombre del perro
    public function establecerNombre($nombrePerro) {
        $this->nombre = $nombrePerro; // Usa $this para referirse a la propiedad del objeto actual
    }

    // Método para obtener el nombre del perro
    public function obtenerNombre() {
        return $this->nombre;
    }

    // Método para hacer que el perro ladre
    public function ladrar() {
        return "¡Guau! Mi nombre es " . $this->nombre . ".<br>";
    }
}
?>
```

**La Pseudo-Variable `$this`:**
Dentro de un método de clase, la pseudo-variable `$this` se refiere a la **instancia actual del objeto**. Se usa para acceder a las propiedades y otros métodos de ese objeto específico.
*   `$this->nombre = $nombrePerro;` asigna el valor de `$nombrePerro` a la propiedad `nombre` del objeto `Perro` *actual*.
*   `$this->nombre` en `ladrar()` accede a la propiedad `nombre` del objeto `Perro` *actual*.

### Creando Objetos (Instanciación)

Crear un objeto a partir de una clase se llama **instanciación**. Usas la palabra clave `new` para crear una instancia de una clase.

**Sintaxis:**
`$variableObjeto = new NombreClase();`

**Ejemplo:**

```php
<?php
class Perro {
    public $nombre;
    public $raza;

    public function establecerNombre($nombrePerro) {
        $this->nombre = $nombrePerro;
    }

    public function obtenerNombre() {
        return $this->nombre;
    }

    public function ladrar() {
        return "¡Guau! Mi nombre es " . $this->nombre . ".<br>";
    }
}

// Crear (instanciar) objetos Perro
$perro1 = new Perro();
$perro2 = new Perro();

// Accediendo a propiedades y llamando a métodos
// Para perro1
$perro1->nombre = "Buddy"; // Estableciendo directamente la propiedad pública (generalmente es mejor usar métodos como establecerNombre)
$perro1->raza = "Golden Retriever";
echo $perro1->ladrar(); // Salida: ¡Guau! Mi nombre es Buddy.

// Para perro2
$perro2->establecerNombre("Lucy"); // Usando un método para establecer el nombre
$perro2->raza = "Caniche";
echo "El nombre del Perro 2 es: " . $perro2->obtenerNombre() . "<br>"; // Salida: El nombre del Perro 2 es: Lucy
echo $perro2->ladrar(); // Salida: ¡Guau! Mi nombre es Lucy.

// Accediendo a propiedades
echo $perro1->nombre . " es un " . $perro1->raza . ".<br>"; // Buddy es un Golden Retriever.
?>
```
*   `$objeto->nombrePropiedad`: Accede a una propiedad del objeto.
*   `$objeto->nombreMetodo();`: Llama a un método del objeto.

## 3. Constructores y Destructores

### Constructor: `__construct()`

Un constructor es un método especial dentro de una clase que se llama automáticamente cuando se crea un objeto de esa clase usando la palabra clave `new`. Se usa principalmente para inicializar las propiedades del objeto o realizar cualquier tarea de configuración necesaria cuando se crea el objeto.

**Sintaxis:**

```php
<?php
class NombreClase {
    public function __construct(/* parámetros */) {
        // Código de inicialización
    }
}
?>
```
*   El nombre del método debe ser `__construct` (dos guiones bajos).

**Ejemplo:**

```php
<?php
class Coche {
    public $marca;
    public $modelo;
    public $anio;

    // Método constructor
    public function __construct($marcaCoche, $modeloCoche, $anioCoche) {
        echo "¡Objeto Coche creado para un " . $marcaCoche . "!<br>";
        $this->marca = $marcaCoche;
        $this->modelo = $modeloCoche;
        $this->anio = $anioCoche;
    }

    public function obtenerDetalles() {
        return "Marca: " . $this->marca . ", Modelo: " . $this->modelo . ", Año: " . $this->anio . "<br>";
    }
}

// Creando objetos con argumentos de constructor
$coche1 = new Coche("Toyota", "Camry", 2021); // Se imprimirá "¡Objeto Coche creado para un Toyota!"
$coche2 = new Coche("Honda", "Civic", 2022);  // Se imprimirá "¡Objeto Coche creado para un Honda!"

echo $coche1->obtenerDetalles(); // Marca: Toyota, Modelo: Camry, Año: 2021
echo $coche2->obtenerDetalles(); // Marca: Honda, Modelo: Civic, Año: 2022
?>
```

**Promoción de Propiedades del Constructor en PHP 8:**
PHP 8 introdujo una sintaxis más concisa para los constructores donde puedes declarar e inicializar propiedades directamente desde los parámetros del constructor.

```php
<?php
// Promoción de Propiedades del Constructor en PHP 8
class Libro {
    // Las propiedades se declaran e inicializan directamente en el constructor
    public function __construct(
        public string $titulo,
        public string $autor,
        public int $anioPublicacion
    ) {
        echo "Objeto Libro para '" . $this->titulo . "' creado.<br>";
    }

    public function obtenerResumen() {
        return "'" . $this->titulo . "' por " . $this->autor . ", publicado en " . $this->anioPublicacion . ".<br>";
    }
}

$libro1 = new Libro("El Gran Gatsby", "F. Scott Fitzgerald", 1925);
echo $libro1->obtenerResumen(); // 'El Gran Gatsby' por F. Scott Fitzgerald, publicado en 1925.

echo "Título: " . $libro1->titulo . "<br>"; // Accediendo a la propiedad pública promocionada
?>
```
Esta sintaxis reduce el código repetitivo al crear automáticamente la propiedad y asignar el valor pasado al constructor.

### Destructor: `__destruct()`

Un destructor es un método especial que se llama automáticamente cuando un objeto ya no es referenciado o cuando la ejecución del script termina. Se usa para tareas de limpieza, como cerrar conexiones de base de datos, liberar manejadores de archivos o realizar otras operaciones de liberación de recursos.

**Sintaxis:**

```php
<?php
class NombreClase {
    public function __destruct() {
        // Código de limpieza
    }
}
?>
```
*   El nombre del método debe ser `__destruct` (dos guiones bajos).
*   Los destructores no pueden tener parámetros.

**Ejemplo:**

```php
<?php
class ManejadorArchivo {
    private $manejadorArchivo;
    private $nombreArchivo;

    public function __construct($nombrearchivo, $modo = "r") {
        $this->nombreArchivo = $nombrearchivo;
        // Crea un archivo ficticio si no existe para que el ejemplo se ejecute
        if (!file_exists($this->nombreArchivo)) {
            file_put_contents($this->nombreArchivo, "Contenido inicial para " . $this->nombreArchivo);
        }
        $this->manejadorArchivo = fopen($nombrearchivo, $modo);
        if ($this->manejadorArchivo) {
            echo "Archivo '" . $this->nombreArchivo . "' abierto exitosamente.<br>";
        } else {
            echo "Falló al abrir el archivo '" . $this->nombreArchivo . "'.<br>";
        }
    }

    public function leerArchivo() {
        if ($this->manejadorArchivo && filesize($this->nombreArchivo) > 0) {
            return fread($this->manejadorArchivo, filesize($this->nombreArchivo));
        }
        return false;
    }

    // Método destructor
    public function __destruct() {
        if ($this->manejadorArchivo) {
            fclose($this->manejadorArchivo);
            echo "Archivo '" . $this->nombreArchivo . "' cerrado en el destructor.<br>";
        }
        echo "Objeto ManejadorArchivo para '" . $this->nombreArchivo . "' está siendo destruido.<br>";
    }
}

$archivo = new ManejadorArchivo("archivodeprueba_poo.txt"); // Creará archivodeprueba_poo.txt si no está presente
if ($archivo) {
    // echo "Contenido: " . $archivo->leerArchivo() . "<br>";
}
// Cuando $archivo sale del ámbito o el script termina, se llama a __destruct().
// Por ejemplo, si lo eliminamos explícitamente:
// unset($archivo);
// O se llamará al final del script.
?>
```
Los destructores se llaman en el orden inverso a su creación si múltiples objetos se destruyen al final del script.

## 4. Modificadores de Acceso (Visibilidad)

Los modificadores de acceso controlan la visibilidad y accesibilidad de las propiedades y métodos de clase desde diferentes partes de tu código. Son clave para implementar la encapsulación.

*   **`public`**:
    *   Las propiedades y métodos declarados como `public` pueden ser accedidos desde cualquier lugar:
        *   Desde dentro de la propia clase.
        *   Desde subclases (clases derivadas).
        *   Desde fuera de la clase (por ejemplo, en tu código de script principal).
    *   Este es el nivel de acceso menos restrictivo. Si no se especifica ningún modificador de acceso, por defecto es `public` (aunque declararlo explícitamente es una buena práctica).

*   **`private`**:
    *   Las propiedades y métodos declarados como `private` **solo** pueden ser accedidos desde dentro de la clase que los define.
    *   No son accesibles desde subclases ni desde fuera de la clase.
    *   Este es el nivel de acceso más restrictivo, usado para ocultar detalles internos de implementación.

*   **`protected`**:
    *   Las propiedades y métodos declarados como `protected` pueden ser accedidos:
        *   Desde dentro de la propia clase.
        *   Desde subclases (clases que heredan de esta clase).
    *   No son accesibles desde fuera de la clase.
    *   Esto es útil cuando quieres permitir que las clases hijas usen o modifiquen ciertas partes de la clase padre, pero aun así mantener esas partes ocultas del mundo exterior.

**Propósito:**
Los modificadores de acceso ayudan a reforzar la encapsulación. Al hacer las propiedades `private` o `protected`, previenes la modificación externa directa y controlas el acceso a través de métodos `public` (getters y setters).

**Ejemplo:**

```php
<?php
class CuentaBancaria {
    public $numeroCuenta;      // Accesible desde cualquier lugar
    private $saldo;           // Solo accesible dentro de la clase CuentaBancaria
    protected $titularCuenta;   // Accesible dentro de CuentaBancaria y sus subclases

    public function __construct($numCuenta, $titular, $saldoInicial) {
        $this->numeroCuenta = $numCuenta;
        $this->titularCuenta = $titular;
        $this->establecerSaldo($saldoInicial); // Usa un setter para el saldo inicial
    }

    // Método público para depositar dinero
    public function depositar($monto) {
        if ($monto > 0) {
            $this->saldo += $monto;
            echo "Depositado: $" . number_format($monto, 2) . ". Nuevo saldo: $" . number_format($this->saldo, 2) . "<br>";
        } else {
            echo "El monto a depositar debe ser positivo.<br>";
        }
    }

    // Método público para obtener el saldo (Getter)
    public function obtenerSaldo() {
        // Podría añadir registro u otras verificaciones aquí
        return $this->saldo;
    }

    // Método privado para establecer el saldo (Setter) - uso interno o acceso controlado
    private function establecerSaldo($monto) {
        if ($monto >= 0) {
            $this->saldo = $monto;
        } else {
            $this->saldo = 0;
            echo "El saldo inicial no puede ser negativo. Se estableció en 0.<br>";
        }
    }

    public function obtenerTitularCuenta() {
        return $this->titularCuenta;
    }
}

class CuentaAhorros extends CuentaBancaria {
    private $tasaInteres;

    public function __construct($numCuenta, $titular, $saldoInicial, $tasa) {
        parent::__construct($numCuenta, $titular, $saldoInicial); // Llama al constructor padre
        $this->tasaInteres = $tasa;
    }

    public function aplicarInteres() {
        $saldoActual = $this->obtenerSaldo(); // Accede a obtenerSaldo() público del padre
        $interes = $saldoActual * $this->tasaInteres;
        // $this->saldo += $interes; // ¡ERROR! No se puede acceder a $saldo privado directamente desde la subclase
        $this->depositar($interes); // Forma correcta: usar el método público depositar del padre
        echo "Interés de $" . number_format($interes, 2) . " aplicado. Titular de la Cuenta: " . $this->titularCuenta . "<br>"; // OK: $titularCuenta es protected
    }
}

$cuenta1 = new CuentaBancaria("12345", "Juan Pérez", 100.00);
echo "Número de Cuenta: " . $cuenta1->numeroCuenta . "<br>"; // OK: public
// echo "Saldo: " . $cuenta1->saldo; // ¡ERROR! No se puede acceder a la propiedad privada $saldo
echo "Saldo: $" . number_format($cuenta1->obtenerSaldo(), 2) . "<br>"; // OK: usando getter público

$cuenta1->depositar(50.50);
// $cuenta1->establecerSaldo(5000); // ¡ERROR! No se puede llamar al método privado establecerSaldo() desde fuera

$ahorros1 = new CuentaAhorros("67890", "Ana López", 500.00, 0.05);
$ahorros1->depositar(100.00);
$ahorros1->aplicarInteres();
echo "Saldo de Ahorros: $" . number_format($ahorros1->obtenerSaldo(), 2) . "<br>";
echo "Titular de Ahorros: " . $ahorros1->obtenerTitularCuenta() . "<br>"; // Accediendo a propiedad protegida vía método público
?>
```

**Métodos Getter y Setter:**
Cuando una propiedad es `private` o `protected`, a menudo proporcionas métodos `public` para obtener su valor (getter) y establecer su valor (setter). Esto te permite controlar cómo se accede y modifica la propiedad, añadiendo validación u otra lógica si es necesario.

*   **Getter:** `public function obtenerNombrePropiedad() { return $this->nombrePropiedad; }`
*   **Setter:** `public function establecerNombrePropiedad($valor) { /* validación */ $this->nombrePropiedad = $valor; }`

## 5. Herencia

La herencia es un principio fundamental de la POO que permite a una clase (clase hija o subclase) heredar propiedades y métodos de otra clase (clase padre o superclase). Esto promueve la reutilización de código y establece una relación "es-un" (por ejemplo, un `Gerente` es un `Empleado`).

**Sintaxis:**
`class ClaseHija extends ClasePadre { ... }`

**Ejemplo:**

```php
<?php
// Clase padre (Superclase)
class Animal {
    public $nombre;
    protected $sonido; // Protegido para que las clases hijas puedan accederlo

    public function __construct($nombre, $sonido = "Algún sonido genérico") {
        $this->nombre = $nombre;
        $this->sonido = $sonido;
        echo $this->nombre . " el Animal creado.<br>";
    }

    public function comer() {
        echo $this->nombre . " está comiendo.<br>";
    }

    public function hacerSonido() {
        echo $this->nombre . " dice " . $this->sonido . ".<br>";
    }
}

// Clase hija (Subclase) heredando de Animal
class Perro extends Animal {
    public $raza;

    // Constructor propio de Perro
    public function __construct($nombre, $raza) {
        // Llama al constructor de la clase padre
        parent::__construct($nombre, "Guau"); // Pasa "Guau" como el sonido para los perros
        $this->raza = $raza;
        echo $this->nombre . " el Perro de raza " . $this->raza . " creado.<br>";
    }

    // Método específico de Perro
    public function traerPelota() {
        echo $this->nombre . " está trayendo una pelota.<br>";
    }

    // Sobrescribiendo el método hacerSonido de la clase padre
    public function hacerSonido() {
        echo $this->nombre . " (un " . $this->raza . ") ladra fuertemente: ¡" . $this->sonido . "!<br>";
        // También puedes llamar a la versión del padre si es necesario:
        // parent::hacerSonido();
    }
}

class Gato extends Animal {
    public function __construct($nombre) {
        parent::__construct($nombre, "Miau");
        echo $this->nombre . " el Gato creado.<br>";
    }

    // Método específico de Gato
    public function treparArbol() {
        echo $this->nombre . " está trepando un árbol.<br>";
    }

    // Sobrescribiendo hacerSonido
    public function hacerSonido() {
        echo $this->nombre . " maúlla: " . $this->sonido . "...<br>";
    }
}

$animalGenerico = new Animal("Criatura Genérica");
$animalGenerico->comer();
$animalGenerico->hacerSonido();
echo "<hr>";

$perro = new Perro("Buddy", "Golden Retriever");
$perro->comer();         // Heredado de Animal
$perro->hacerSonido();   // Sobrescrito en Perro
$perro->traerPelota();   // Específico de Perro
echo "<hr>";

$gato = new Gato("Bigotes");
$gato->comer();         // Heredado
$gato->hacerSonido();   // Sobrescrito en Gato
$gato->treparArbol();   // Específico de Gato
?>
```

**Conceptos Clave en Herencia:**

*   **Reutilización de Funcionalidad:** Las clases `Perro` y `Gato` obtienen automáticamente la propiedad `nombre` y el método `comer()` de la clase `Animal`.
*   **Extensión de Funcionalidad:** Las clases hijas pueden añadir sus propias propiedades (como `$raza` en `Perro`) y métodos (como `traerPelota()` en `Perro` o `treparArbol()` en `Gato`).
*   **Sobrescritura de Métodos:** Una clase hija puede proporcionar su propia implementación específica de un método que heredó de la clase padre (por ejemplo, `hacerSonido()` se sobrescribe tanto en `Perro` como en `Gato`). La firma del método (nombre, parámetros y compatibilidad del modificador de acceso) generalmente debe permanecer igual o ser menos restrictiva.
*   **Palabra Clave `parent::`:**
    Dentro de una clase hija, puedes usar `parent::` para acceder a métodos y propiedades de la clase padre. Esto se usa a menudo en constructores (`parent::__construct()`) o cuando quieres extender la funcionalidad del método del padre en lugar de reemplazarla por completo.
    ```php
    class MiClaseHija extends MiClasePadre {
        public function algunMetodo() {
            parent::algunMetodo(); // Llama a la versión del padre
            // Añade más lógica específica de la hija aquí
            echo "Clase hija extendiendo funcionalidad.<br>";
        }
    }
    ```
*   **Palabra Clave `final`:**
    *   Si declaras un método como `final` en la clase padre, **no puede ser sobrescrito** por clases hijas.
      `public final function noSePuedeSobrescribir() { ... }`
    *   Si declaras una clase como `final`, **no puede ser heredada** (es decir, ninguna otra clase puede extenderla).
      `final class NoMasHijos { ... }`

## 6. Clases y Métodos Abstractos

Una **clase abstracta** es una clase que no puede ser instanciada directamente. Está diseñada para ser una clase base de la que otras clases heredan. Las clases abstractas pueden contener tanto métodos regulares (con implementación) como **métodos abstractos**.

Un **método abstracto** se declara en una clase abstracta pero no contiene ninguna implementación (sin cuerpo). Solo define la firma del método (nombre, parámetros y tipo de retorno si lo hay). Las subclases que heredan de una clase abstracta **deben** proporcionar implementaciones concretas para todos los métodos abstractos declarados en la clase abstracta padre.

**Sintaxis:**

```php
<?php
abstract class NombreClaseAbstracta {
    // Propiedades y métodos regulares
    public $nombre;

    public function metodoRegular() {
        echo "Este es un método regular en una clase abstracta.<br>";
    }

    // Método abstracto (sin cuerpo, solo firma)
    abstract protected function metodoAbstracto($param);
    // Los métodos abstractos también pueden ser públicos
    // abstract public function otroMetodoAbstracto();
}

class ClaseConcreta extends NombreClaseAbstracta {
    // Debe implementar todos los métodos abstractos de NombreClaseAbstracta
    protected function metodoAbstracto($param) {
        echo "Implementación de metodoAbstracto con parámetro: " . $param . "<br>";
        $this->nombre = $param; // Puede usar propiedades de la clase abstracta
    }

    public function imprimirNombre() {
        echo "Nombre desde ClaseConcreta: " . $this->nombre . "<br>";
    }
}

// $obj1 = new NombreClaseAbstracta(); // ¡ERROR! No se puede instanciar una clase abstracta.
$obj2 = new ClaseConcreta();
$obj2->metodoRegular(); // Llama al método de la clase abstracta
$obj2->metodoAbstracto("Valor de Prueba");
$obj2->imprimirNombre(); // Nombre desde ClaseConcreta: Valor de Prueba
?>
```

**Propósito y Casos de Uso:**

*   **Definir una Plantilla/Contrato:** Las clases abstractas definen una estructura común y un conjunto de métodos a los que todas las subclases deben adherirse, asegurando la consistencia.
*   **Reutilización de Código:** Pueden proporcionar funcionalidad común (métodos implementados) que las subclases pueden compartir, mientras obligan a las subclases a implementar comportamientos específicos (métodos abstractos).
*   Útil cuando tienes un grupo de clases relacionadas que comparten alguna lógica común pero también tienen implementaciones específicas para ciertas acciones. Por ejemplo, una clase abstracta `Forma` podría tener un método abstracto `calcularArea()`, y clases concretas como `Circulo` y `Rectangulo` lo implementarían de manera diferente.

**Reglas para Clases/Métodos Abstractos:**

*   Una clase que contiene al menos un método abstracto debe declararse abstracta.
*   Los métodos abstractos no pueden tener un cuerpo (implementación) en la clase abstracta.
*   Los métodos abstractos deben ser implementados por cualquier clase hija que no sea abstracta.
*   La visibilidad del método implementado en la clase hija debe ser la misma o menos restrictiva que la visibilidad del método abstracto (por ejemplo, si el método abstracto es `protected`, la implementación puede ser `protected` o `public`, pero no `private`).
*   La firma (número y tipo de parámetros, tipo de retorno) del método implementado debe coincidir con la firma del método abstracto.

## 7. Interfaces

Una **interfaz** define un contrato de qué métodos debe implementar una clase. Especifica las firmas de los métodos (nombre, parámetros, tipo de retorno) pero no proporciona ninguna implementación para esos métodos.

Cuando una clase **implementa** una interfaz, está obligada a proporcionar implementaciones concretas para todos los métodos declarados en esa interfaz.

**Sintaxis:**

```php
<?php
// Definir una interfaz
interface NombreInterfaz {
    public function metodo1($param1);
    public function metodo2(): string; // Puede especificar tipos de retorno
    // Las interfaces también pueden definir constantes públicas
    // const MI_CONSTANTE = 'valor';
}

// Clase implementando la interfaz
class MiClase implements NombreInterfaz /*, OtraInterfaz, ... */ {
    public function metodo1($param1) {
        echo "Implementación de metodo1 con: " . $param1 . "<br>";
    }

    public function metodo2(): string {
        return "Implementación de metodo2.<br>";
    }
}

$obj = new MiClase();
$obj->metodo1("Hola");
echo $obj->metodo2();
?>
```

**Características Clave y Reglas:**

*   **Contrato:** Las interfaces aseguran que diferentes clases proporcionen un conjunto común de funcionalidades, haciéndolas intercambiables en ciertos contextos (polimorfismo).
*   **Palabra Clave `implements`:** Una clase usa la palabra clave `implements` para adoptar una interfaz.
*   **Múltiples Interfaces:** Una clase puede implementar múltiples interfaces listándolas separadas por comas: `class MiClase implements InterfazA, InterfazB { ... }`.
*   **Los Métodos son Implícitamente Públicos y Abstractos:** Todos los métodos declarados en una interfaz son implícitamente `public` y `abstract`. No necesitas escribir `public abstract function ...`, solo `public function ...` (o incluso solo `function ...` ya que `public` está implícito).
*   **Sin Propiedades:** Las interfaces no pueden declarar propiedades (variables miembro), aunque pueden declarar constantes `public const`.
*   **Sin Implementación:** Los métodos de interfaz no pueden tener cuerpos.

**Diferencias entre Interfaces y Clases Abstractas:**

| Característica    | Clase Abstracta                                     | Interfaz                                           |
| ----------------- | -------------------------------------------------- | --------------------------------------------------- |
| **Instanciación** | No se puede instanciar.                            | No se puede instanciar.                             |
| **Métodos**       | Puede tener métodos abstractos y concretos.       | Todos los métodos son implícitamente abstractos (sin implementación). |
| **Propiedades**    | Puede tener propiedades (variables miembro).            | No puede tener propiedades (puede tener constantes).        |
| **Herencia**   | Una clase puede `extender` solo **una** clase abstracta.  | Una clase puede `implementar` **múltiples** interfaces.    |
| **Propósito**       | Proporcionar una clase base con alguna implementación compartida y una plantilla para subclases. Establece una relación "es-un". | Definir un contrato de métodos que una clase *debe* implementar. Establece una relación "puede-hacer" o "se-comporta-como". |
| **Constructores**  | Puede tener constructores.                             | No puede tener constructores.                           |

**Cuándo Usar Cuál:**

*   **Usa una clase abstracta cuando:**
    *   Quieres compartir código (métodos implementados) entre varias clases estrechamente relacionadas.
    *   Esperas que las clases que extienden tu clase abstracta tengan muchos métodos o propiedades comunes, o requieran modificadores de acceso distintos de `public` para alguna funcionalidad base.
    *   Quieres definir una base común para una jerarquía de clases.
*   **Usa una interfaz cuando:**
    *   Quieres definir un contrato que clases no relacionadas puedan implementar.
    *   Quieres especificar el comportamiento de un tipo de dato particular, pero no te preocupa quién implementa su comportamiento.
    *   Quieres aprovechar la herencia múltiple de tipo (una clase puede implementar múltiples interfaces).

## 8. Polimorfismo

El polimorfismo, que significa "muchas formas", es un concepto central de la POO que permite que objetos de diferentes clases sean tratados como objetos de una superclase o interfaz común. Pueden responder a la misma llamada de método de sus propias maneras distintas.

**Ejemplo demostrando Polimorfismo usando una Interfaz:**

```php
<?php
// Interfaz que define un contrato para objetos registrables (loggable)
interface Registrable {
    public function obtenerMensajeLog(): string;
}

// Clase 1 implementando Registrable
class Usuario implements Registrable {
    private $nombreUsuario;

    public function __construct($nombreUsuario) {
        $this->nombreUsuario = $nombreUsuario;
    }

    public function obtenerMensajeLog(): string {
        return "Usuario '" . $this->nombreUsuario . "' realizó una acción.";
    }
}

// Clase 2 implementando Registrable
class Producto implements Registrable {
    private $nombreProducto;

    public function __construct($nombreProducto) {
        $this->nombreProducto = $nombreProducto;
    }

    public function obtenerMensajeLog(): string {
        return "Producto '" . $this->nombreProducto . "' fue actualizado.";
    }
}

// Clase 3, también registrable
class Pedido implements Registrable {
    private $idPedido;

    public function __construct($idPedido) {
        $this->idPedido = $idPedido;
    }

    public function obtenerMensajeLog(): string {
        return "Estado del Pedido ID '" . $this->idPedido . "' cambiado.";
    }
}

// Una función que puede trabajar con cualquier objeto que implemente Registrable
function escribirEnLog(Registrable $objetoRegistrable) {
    // $objetoRegistrable puede ser un Usuario, un Producto, o un Pedido
    $mensaje = $objetoRegistrable->obtenerMensajeLog(); // Llama a la implementación específica
    echo "Registrando: " . $mensaje . "<br>";
    // En una aplicación real, esto escribiría a un archivo o base de datos
}

// Crear objetos de diferentes tipos
$usuario = new Usuario("Alicia");
$producto = new Producto("Portátil XYZ");
$pedido = new Pedido("ORD12345");

// Pasarlos a la misma función - ¡Polimorfismo en acción!
escribirEnLog($usuario);    // Registrando: Usuario 'Alicia' realizó una acción.
escribirEnLog($producto); // Registrando: Producto 'Portátil XYZ' fue actualizado.
escribirEnLog($pedido);   // Registrando: Estado del Pedido ID 'ORD12345' cambiado.

// También puedes almacenarlos en un array de ítems Registrables
$itemsRegistrables = [$usuario, $producto, $pedido];
foreach ($itemsRegistrables as $item) {
    // $item es tratado como Registrable, se llama a su obtenerMensajeLog() específico
    echo "Procesando log para ítem: " . $item->obtenerMensajeLog() . "<br>";
}
?>
```
En este ejemplo, `escribirEnLog()` no necesita saber si está tratando con un objeto `Usuario`, `Producto` o `Pedido`. Solo le importa que el objeto implemente la interfaz `Registrable` y, por lo tanto, tenga un método `obtenerMensajeLog()`. Cada tipo de objeto proporciona su propia versión de `obtenerMensajeLog()`, demostrando el polimorfismo.

## 9. Traits (Rasgos)

Los traits son un mecanismo para la reutilización de código en lenguajes de herencia única como PHP. Una clase solo puede heredar de una clase padre, pero puede usar múltiples traits. Los traits te permiten componer conjuntos de métodos en clases.

**Sintaxis:**

```php
<?php
trait NombreTrait {
    // Propiedades y métodos
    public $propiedadTrait = "Valor del Trait";

    public function metodoTrait() {
        return "Este es un método de NombreTrait.<br>";
    }
}

class MiClaseConTrait {
    use NombreTrait; // Usa el trait en esta clase

    public function metodoPropio() {
        return "Esto es de MiClaseConTrait. Propiedad del trait: " . $this->propiedadTrait . "<br>";
    }
}

class OtraClaseConTrait {
    use NombreTrait;
    // Puede tener sus propios métodos y propiedades
}

$obj = new MiClaseConTrait();
echo $obj->metodoTrait();    // Este es un método de NombreTrait.
echo $obj->metodoPropio();   // Esto es de MiClaseConTrait. Propiedad del trait: Valor del Trait

$obj2 = new OtraClaseConTrait();
echo $obj2->metodoTrait();   // Este es un método de NombreTrait.
$obj2->propiedadTrait = "Nuevo Valor para obj2";
echo $obj2->propiedadTrait . "<br>"; // Nuevo Valor para obj2
echo $obj->propiedadTrait . "<br>";  // Valor del Trait (la propiedad de obj es separada)
?>
```

**Características Clave:**

*   **Reutilización de Código:** Comparte métodos (y propiedades) entre múltiples clases independientes que no comparten un padre común.
*   **Palabra Clave `use`:** Una clase incorpora los métodos y propiedades de un trait usando la palabra clave `use` dentro de la definición de la clase.
*   **Composición Horizontal:** Los traits ofrecen una forma de componer funcionalidad horizontalmente, en oposición a la composición vertical de la herencia.
*   **Precedencia:** Los métodos de la clase actual sobrescriben los métodos de un trait, que a su vez sobrescriben los métodos de una clase padre.
    *   Método de Clase Actual > Método de Trait > Método Heredado del Padre.

**Resolución de Conflictos:**
Si dos traits definen un método con el mismo nombre, o un trait tiene un método con el mismo nombre que uno en la propia clase o su clase padre, PHP generará un error fatal a menos que el conflicto se resuelva explícitamente.

*   **`insteadof`:** Para especificar qué método de trait usar cuando hay una colisión de nombres entre traits.
*   **`as`:** Para cambiar la visibilidad o dar un alias a un método de un trait.

```php
<?php
trait Compartible {
    public function compartir($item) {
        echo "Compartiendo " . $item . " vía trait Compartible.<br>";
    }
}

trait Gustable { // Likeable
    public function compartir($item) { // Mismo nombre de método que en Compartible
        echo "Compartiendo " . $item . " vía trait Gustable (implementación diferente).<br>";
    }

    public function gustar($item) { // like
        echo "Gustando de " . $item . ".<br>";
    }
}

class PublicacionSocial {
    use Compartible, Gustable {
        Gustable::compartir insteadof Compartible; // Usar compartir() de Gustable
        Compartible::compartir as compartirViaCompartible; // Alias de compartir() de Compartible a un nuevo nombre
        Gustable::gustar as public gustarItem; // Puede cambiar la visibilidad (aunque 'gustar' ya es público)
    }

    public $contenido;
    public function __construct($contenido) {
        $this->contenido = $contenido;
    }
}

$publicacion = new PublicacionSocial("Mi nueva entrada de blog");
$publicacion->compartir($publicacion->contenido);           // Compartiendo Mi nueva entrada de blog vía trait Gustable.
$publicacion->compartirViaCompartible($publicacion->contenido); // Compartiendo Mi nueva entrada de blog vía trait Compartible.
$publicacion->gustarItem($publicacion->contenido);        // Gustando de Mi nueva entrada de blog.
?>
```

Los traits son una herramienta poderosa para organizar y reutilizar código de manera flexible.

## 10. Métodos y Propiedades Estáticos

Los métodos y propiedades estáticos pertenecen a la **clase misma**, no a una instancia específica (objeto) de la clase. Se acceden directamente usando el nombre de la clase y el operador de resolución de ámbito (`::`).

**Declarando Miembros Estáticos:**
Usa la palabra clave `static`.

```php
<?php
class UtilidadesMatematicas {
    public static $pi = 3.14159; // Propiedad estática

    // Método estático
    public static function sumar($a, $b) {
        return $a + $b;
    }

    public static function areaCirculo($radio) {
        // Accediendo a propiedad estática desde dentro de la clase usando self::
        return self::$pi * $radio * $radio;
        // O usando NombreClase::$pi: return UtilidadesMatematicas::$pi * $radio * $radio;
    }

    public function metodoNoEstatico() {
        echo "Este es un método no estático. PI es " . self::$pi . "<br>";
        // Los métodos no estáticos pueden acceder a propiedades/métodos estáticos.
    }
}

// Accediendo a propiedad estática
echo "Valor de PI: " . UtilidadesMatematicas::$pi . "<br>"; // Valor de PI: 3.14159

// Llamando a método estático
$suma = UtilidadesMatematicas::sumar(5, 10);
echo "Suma: " . $suma . "<br>"; // Suma: 15

echo "Área de círculo con radio 5: " . UtilidadesMatematicas::areaCirculo(5) . "<br>"; // Área de círculo con radio 5: 78.53975

// Los miembros estáticos se acceden vía la clase, no una instancia
// $objMatematicas = new UtilidadesMatematicas();
// echo $objMatematicas::$pi; // Funciona pero no se recomienda, usa NombreClase::$pi
// echo $objMatematicas::sumar(2,3); // Funciona pero no se recomienda, usa NombreClase::sumar()

$objMatematicas = new UtilidadesMatematicas();
$objMatematicas->metodoNoEstatico(); // Acceder a propiedad estática desde contexto de método no estático está bien.

// Nota: Dentro de un método estático, no puedes usar $this porque $this se refiere a una instancia de objeto,
// y los métodos estáticos no están ligados a un objeto específico.
// Usas `self::` para referirte a miembros estáticos de la clase actual.
?>
```

**Puntos Clave:**

*   **No se Necesita Instancia de Objeto:** Los miembros estáticos pueden usarse sin crear un objeto de la clase.
*   **Palabra Clave `self::`:** Dentro de una clase, usa `self::` para acceder a propiedades estáticas o llamar a métodos estáticos de la *misma* clase.
    *   `self::$propiedadEstatica`
    *   `self::metodoEstatico()`
*   **Palabra Clave `parent::`:** También se puede usar para acceder a miembros estáticos de una clase padre.
*   **Estado:** Las propiedades estáticas comparten un único valor entre todos los usos de la clase, no por objeto.
*   **`$this` en Métodos Estáticos:** La pseudo-variable `$this` **no está disponible** dentro de métodos estáticos porque no están asociados con una instancia de objeto específica. Intentar usar `$this` en un método estático resultará en un error.
*   **Casos de Uso:**
    *   **Funciones de utilidad:** Funciones que realizan una tarea pero no necesitan depender del estado de un objeto específico (por ejemplo, `UtilidadesMatematicas::sumar()`).
    *   **Contadores o configuraciones globales para una clase:** Por ejemplo, contar cuántos objetos de una clase se han instanciado.
    *   **Métodos de fábrica (Factory methods):** Métodos estáticos que crean y devuelven instancias de la clase (por ejemplo, `Usuario::crearUsuarioInvitado()`).

## 11. Espacios de Nombres (Namespaces)

Los espacios de nombres son una forma de encapsular ítems (clases, interfaces, funciones, constantes) para prevenir colisiones de nombres. Imagina dos bibliotecas diferentes que quieres usar en tu proyecto, y ambas definen una clase llamada `Logger`. Sin espacios de nombres, PHP no sabría qué clase `Logger` intentas usar.

**Propósito:**

*   **Prevenir Conflictos de Nombres:** Te permite usar el mismo nombre de clase o función en diferentes partes de tu código o de diferentes bibliotecas sin conflicto.
*   **Organizar Código:** Ayuda a organizar el código en grupos lógicos, similar a cómo los directorios organizan archivos.

**Definiendo un Espacio de Nombres:**
La palabra clave `namespace` se usa para declarar un espacio de nombres. Debe ser la **primera declaración** en un archivo PHP (antes de cualquier otro código, excepto declaraciones `declare`).

```php
<?php
// Archivo: MiProyecto/Utilidades/Logger.php
namespace MiProyecto\Utilidades; // Declara el espacio de nombres

class Logger {
    public static function log($mensaje) {
        echo "Log de MiProyecto: " . $mensaje . "<br>";
    }
}

// Archivo: OtroProyecto/Registro/Logger.php
namespace OtroProyecto\Registro; // Un espacio de nombres diferente

class Logger { // Mismo nombre de clase, pero en un espacio de nombres diferente
    public static function log($mensaje) {
        echo "Log de OtroProyecto: " . $mensaje . "<br>";
    }
}
?>
```

**Usando Espacios de Nombres:**
Para usar clases, interfaces, funciones o constantes de un espacio de nombres, puedes:

1.  **Nombre Completamente Calificado:** Referirte a ellos por su nombre completo incluyendo el espacio de nombres.
    ```php
    <?php
    // Asumiendo que las clases Logger están definidas en sus respectivos archivos y espacios de nombres
    // require 'MiProyecto/Utilidades/Logger.php'; 
    // require 'OtroProyecto/Registro/Logger.php';

    // Para que este ejemplo sea autocontenido, definimos las clases aquí (normalmente estarían en archivos separados)
    if (!class_exists('MiProyecto\Utilidades\Logger')) {
        namespace MiProyecto\Utilidades;
        class Logger { public static function log($m) { echo "Log de MiProyecto: $m<br>"; } }
    }
    if (!class_exists('OtroProyecto\Registro\Logger')) {
        namespace OtroProyecto\Registro;
        class Logger { public static function log($m) { echo "Log de OtroProyecto: $m<br>"; } }
    }
    
    // Volver al espacio de nombres global para las llamadas
    namespace {
        \MiProyecto\Utilidades\Logger::log("Sistema iniciado.");
        // Salida: Log de MiProyecto: Sistema iniciado.

        \OtroProyecto\Registro\Logger::log("Usuario ha iniciado sesión.");
        // Salida: Log de OtroProyecto: Usuario ha iniciado sesión.
    }
    ?>
    ```

2.  **Palabra Clave `use` (Importando/Alias):** La palabra clave `use` te permite importar un espacio de nombres o una clase/interfaz/etc. específica de un espacio de nombres, para que puedas referirte a ella con un nombre más corto o un alias.

    ```php
    <?php
    // Asumiendo que las clases Logger están en sus archivos
    // require 'MiProyecto/Utilidades/Logger.php';
    // require 'OtroProyecto/Registro/Logger.php';

    // Definiciones dummy para el ejemplo
    if (!class_exists('MiProyecto\Utilidades\Logger')) { namespace MiProyecto\Utilidades; class Logger { public static function log($m){ echo "Log de MiProyecto: $m<br>"; }}}
    if (!class_exists('OtroProyecto\Registro\Logger')) { namespace OtroProyecto\Registro; class Logger { public static function log($m){ echo "Log de OtroProyecto: $m<br>"; }}}

    // `use` debe estar en el ámbito global o dentro de otro namespace.
    namespace MiAplicacion { // Declaramos un namespace para el contexto de 'use'

    use MiProyecto\Utilidades\Logger; // Importa Logger de MiProyecto
    use OtroProyecto\Registro\Logger as OtroLogger; // Importa y da alias a Logger de OtroProyecto

    Logger::log("Verificación del sistema.");        // Usa MiProyecto\Utilidades\Logger
    // Salida: Log de MiProyecto: Verificación del sistema.

    OtroLogger::log("Datos procesados."); // Usa OtroProyecto\Registro\Logger
    // Salida: Log de OtroProyecto: Datos procesados.

    // También puedes importar un espacio de nombres completo
    // use MiProyecto\Utilidades;
    // Utilidades\Logger::log("Importación de espacio de nombres."); // Luego usar relativo al espacio de nombres importado

    // Para usar múltiples clases del mismo espacio de nombres (PHP 7+):
    // use MiProyecto\Utilidades\{Logger, Formateador, Ayudante};
    }
    ?>
    ```

**Refiriéndose a Clases/Funciones Globales:**
Si estás dentro de un espacio de nombres y quieres referirte a una clase, función o constante del ámbito global (es decir, sin espacio de nombres, como las clases incorporadas de PHP), necesitas prefijarla con una barra invertida `\`.

```php
<?php
namespace MiAppPersonalizada;

class ManejadorFechaHora {
    public function obtenerFechaHoraActual() {
        // Sin '\', PHP buscaría MiAppPersonalizada\DateTime
        $fechaHora = new \DateTime('now', new \DateTimeZone('UTC'));
        return $fechaHora->format('Y-m-d H:i:s');
    }
}

$manejador = new ManejadorFechaHora();
echo $manejador->obtenerFechaHoraActual() . "<br>"; // ej., 2023-10-27 10:00:00

// Llamando a una función global:
$longitud = \strlen("prueba"); // Se refiere a la función global strlen()
echo "Longitud: " . $longitud . "<br>"; // Longitud: 4
?>
```
Los espacios de nombres son esenciales para el desarrollo moderno de PHP, especialmente cuando se trabaja con bibliotecas de terceros o se construyen aplicaciones grandes.

## 12. Autocarga de Clases (Autoloading)

En proyectos PHP, podrías tener muchos archivos de clase. Incluir cada uno manualmente usando `require` o `include` al principio de tus scripts puede ser engorroso y propenso a errores.

`require 'clases/Usuario.php';`
`require 'clases/Producto.php';`
`require 'clases/Pedido.php';`
`// ... y así sucesivamente`

La **Autocarga** resuelve este problema cargando automáticamente los archivos de clase cuando se necesitan por primera vez en tu código.

**Función `spl_autoload_register()`:**
PHP proporciona la función `spl_autoload_register()` para registrar una o más funciones "autoloader". Cuando tu código intenta usar una clase que aún no ha sido definida, PHP llamará a cada función autoloader registrada en secuencia, pasándole el nombre de la clase que está tratando de cargar. La función autoloader es entonces responsable de localizar e incluir el archivo para esa clase.

**Cómo Funcionan Típicamente los Autoloaders (Basado en Convenciones):**
Una convención común es nombrar tus archivos de clase de una manera que se mapee directamente al nombre de la clase (y su espacio de nombres). Por ejemplo:
*   La clase `MiProyecto\Utilidades\Logger` estaría en el archivo `MiProyecto/Utilidades/Logger.php`.
*   La clase `Usuario` (espacio de nombres global) estaría en `Usuario.php` o `clases/Usuario.php`.

**Ejemplo de un Autoloader Simple:**

```php
<?php
// Esto típicamente estaría en un archivo central de arranque (bootstrap) o configuración

spl_autoload_register(function ($nombreClase) {
    // Reemplazar separadores de espacio de nombres con separadores de directorio
    $rutaArchivo = str_replace('\\', DIRECTORY_SEPARATOR, $nombreClase) . '.php';

    // Anteponer un directorio base si tus clases están en una subcarpeta (ej. 'src/')
    // $rutaCompleta = __DIR__ . '/src/' . $rutaArchivo; 
    $rutaCompleta = $rutaArchivo; // Asume que la estructura de directorios coincide con el espacio de nombres desde la raíz del include_path

    echo "Intentando cargar: " . $nombreClase . " desde la ruta: " . $rutaCompleta . "<br>";

    // Verificar si el archivo existe e incluirlo
    if (file_exists($rutaCompleta)) {
        require_once $rutaCompleta;
        echo "Cargado exitosamente " . $nombreClase . ".<br>";
    } else {
        echo "No se pudo encontrar el archivo: " . $rutaCompleta . " para la clase " . $nombreClase . ".<br>";
    }
});

// --- Ahora, si intentas usar clases, el autoloader se activará ---

// Asume que tienes archivos:
// MiLibreria/Red/Solicitud.php conteniendo la clase MiLibreria\Red\Solicitud
// Servicios/ServicioAutenticacion.php conteniendo la clase Servicios\ServicioAutenticacion

/*
// Uso de ejemplo (crea estos archivos para probar):
// Archivo: MiLibreria/Red/Solicitud.php
namespace MiLibreria\Red;
class Solicitud { public function __construct() { echo "MiLibreria\Red\Solicitud cargada e instanciada!<br>"; } }

// Archivo: Servicios/ServicioAutenticacion.php
namespace Servicios;
class ServicioAutenticacion { public function __construct() { echo "Servicios\ServicioAutenticacion cargado e instanciado!<br>"; } }
*/

// Para probar, crea estos archivos en la estructura de directorios correcta relativa a este script.
// Por ejemplo, si este script está en /var/www/html/, crea /var/www/html/MiLibreria/Red/Solicitud.php etc.
// Y asegúrate de que estos directorios estén en el include_path de PHP o ajusta $rutaCompleta.

// $solicitud = new MiLibreria\Red\Solicitud();
// $auth = new Servicios\ServicioAutenticacion();

// Si los archivos existen en las rutas esperadas, la salida sería:
// Intentando cargar: MiLibreria\Red\Solicitud desde la ruta: MiLibreria/Red/Solicitud.php
// Cargado exitosamente MiLibreria\Red\Solicitud.
// MiLibreria\Red\Solicitud cargada e instanciada!
// Intentando cargar: Servicios\ServicioAutenticacion desde la ruta: Servicios/ServicioAutenticacion.php
// Cargado exitosamente Servicios\ServicioAutenticacion.
// Servicios\ServicioAutenticacion cargado e instanciado!
?>
```
**Nota:** Para que el ejemplo de autoloader anterior funcione correctamente, necesitas crear la estructura de directorios y los archivos (por ejemplo, `MiLibreria/Red/Solicitud.php` conteniendo la clase `MiLibreria\Red\Solicitud`) relativos a donde se está ejecutando el script con `spl_autoload_register`, o asegurar que la ruta base esté correctamente configurada en tu autoloader.

**Autoloader de Composer:**
En el desarrollo moderno de PHP, la gran mayoría de los proyectos usan **Composer**, un gestor de dependencias para PHP. Composer genera un archivo autoloader altamente optimizado (`vendor/autoload.php`) que puede cargar clases basándose en configuraciones en tu archivo `composer.json` (por ejemplo, el estándar de autocarga PSR-4).

Para usar el autoloader de Composer:
1.  Instala Composer ([https://getcomposer.org/](https://getcomposer.org/)).
2.  Define tus reglas de autocarga en `composer.json`.
3.  Ejecuta `composer dump-autoload` o `composer install`.
4.  Incluye el autoloader de Composer en el punto de entrada de tu proyecto:
    `require_once 'vendor/autoload.php';`

Esta es la forma estándar y recomendada de manejar la autocarga en proyectos PHP contemporáneos.

## 13. Métodos Mágicos

Los métodos mágicos en PHP son métodos especiales que PHP llama automáticamente cuando se realizan ciertas acciones sobre un objeto. Son reconocibles por su prefijo de doble guion bajo (`__`). Ya hemos visto `__construct()` y `__destruct()`.

Aquí hay algunos otros métodos mágicos comunes:

*   **`__get($propiedad)`:**
    *   Se llama al intentar leer una propiedad inaccesible (privada o protegida) o inexistente.
    *   Te permite implementar propiedades "virtuales".
    ```php
    <?php
    class AlmacenDatos {
        private $datos = [];
        public function __set($nombre, $valor) { $this->datos[$nombre] = $valor; }
        public function __get($nombre) {
            echo "Accediendo a '{$nombre}' vía __get.<br>";
            return $this->datos[$nombre] ?? null;
        }
    }
    $almacen = new AlmacenDatos();
    $almacen->nombre = "Alicia"; // Llama a __set()
    echo $almacen->nombre . "<br>"; // Llama a __get()
    ?>
    ```

*   **`__set($propiedad, $valor)`:**
    *   Se llama al intentar escribir en una propiedad inaccesible o inexistente.
    ```php
    <?php
    class PerfilUsuario {
        private $propiedades = [];
        public function __set($clave, $val) {
            echo "Estableciendo '{$clave}' a '{$val}' vía __set.<br>";
            $this->propiedades[$clave] = strtoupper($val); // Ejemplo: siempre almacenar en mayúsculas
        }
        public function __get($clave) { return $this->propiedades[$clave] ?? null; }
    }
    $perfil = new PerfilUsuario();
    $perfil->ciudad = "Londres"; // Llama a __set()
    echo $perfil->ciudad . "<br>"; // LONDRES
    ?>
    ```

*   **`__isset($propiedad)`:**
    *   Se dispara al llamar a `isset()` o `empty()` en una propiedad inaccesible o inexistente.
    *   Debe devolver `true` si la propiedad existe y no es nula, `false` en caso contrario.

*   **`__unset($propiedad)`:**
    *   Se llama cuando se usa `unset()` en una propiedad inaccesible o inexistente.

*   **`__call($metodo, $argumentos)`:**
    *   Se llama cuando se invoca un método de instancia inaccesible o inexistente.
    *   `$metodo`: El nombre del método que se está llamando.
    *   `$argumentos`: Un array de argumentos pasados al método.
    ```php
    <?php
    class LlamadorDinamico {
        public function __call($nombreMetodo, $args) {
            echo "Se intentó llamar al método '{$nombreMetodo}' con argumentos: ";
            print_r($args);
            echo "<br>";
            if ($nombreMetodo == 'hacerAlgo') {
                return "Se hizo algo con " . implode(', ', $args) . "<br>";
            }
            return "Método '{$nombreMetodo}' no encontrado.<br>";
        }
    }
    $llamador = new LlamadorDinamico();
    echo $llamador->hacerAlgo("param1", "param2"); // Llama a __call()
    echo $llamador->otroMetodo();                // Llama a __call()
    ?>
    ```

*   **`__callStatic($metodo, $argumentos)`:**
    *   Similar a `__call()`, pero para métodos estáticos inaccesibles o inexistentes.

*   **`__toString()`:**
    *   Se llama cuando un objeto se trata como una cadena (por ejemplo, usando `echo $objeto;` o concatenación de cadenas).
    *   El método **debe** devolver una cadena.
    ```php
    <?php
    class Punto {
        public function __construct(public int $x, public int $y) {}
        public function __toString(): string {
            return "Punto({$this->x}, {$this->y})";
        }
    }
    $p = new Punto(10, 20);
    echo $p . "<br>"; // Salida: Punto(10, 20)
    ?>
    ```

*   **`__clone()`:**
    *   Se llama cuando un objeto se clona usando la palabra clave `clone` (por ejemplo, `$nuevoObj = clone $viejoObj;`).
    *   Útil para personalizar el proceso de clonación, especialmente para objetos que contienen referencias a otros objetos (clonación profunda).

*   **`__invoke(...$argumentos)`:**
    *   Permite que un objeto sea llamado como si fuera una función.
    ```php
    <?php
    class ObjetoInvocable {
        public function __invoke($param) {
            echo "Objeto llamado como función con parámetro: " . $param . "<br>";
        }
    }
    $objInvocable = new ObjetoInvocable();
    $objInvocable("Prueba"); // Salida: Objeto llamado como función con parámetro: Prueba
    ?>
    ```

Los métodos mágicos proporcionan formas poderosas de controlar el comportamiento de los objetos y crear clases más dinámicas y flexibles. Sin embargo, deben usarse con prudencia, ya que a veces pueden dificultar la comprensión del código si se usan en exceso o de forma inapropiada.

Esto concluye nuestra visión general de la Programación Orientada a Objetos en PHP. Dominar estos conceptos te permitirá escribir aplicaciones PHP más robustas, escalables y mantenibles. Recuerda practicar construyendo tus propias clases y explorando cómo estos principios funcionan juntos.
[end of php_tutorial/04_php_oop_es.md]
