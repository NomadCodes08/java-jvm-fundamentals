# Java Program Execution: How Java Code Runs

## 1. Overview

Java follows this basic flow:

```text
Java Source Code (.java)
        |
        v
Java Compiler (javac)
        |
        v
Bytecode (.class)
        |
        v
JVM loads and checks the class
        |
        v
Execution Engine (Interpreter + JIT Compiler)
        |
        v
Native Machine Instructions
        |
        v
CPU executes the instructions
```

**Key idea:** `javac` converts Java source code into bytecode. The JVM runs that bytecode on the current platform.

---

## 2. Step 1 — Write Java Source Code

A developer writes Java code in a file ending with `.java`.

Example: `Hello.java`

```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello, Java!");
    }
}
```

This is human-readable source code.

## 3. Step 2 — Compile with `javac`

Run:

```bash
javac Hello.java
```

The Java compiler checks the source code for compile-time errors and, if compilation succeeds, creates:

```text
Hello.class
```

The `.class` file contains **bytecode**, not ordinary Java source code and not usually CPU-specific machine code.

Bytecode is designed to be understood by a JVM.

## 4. Step 3 — Load the Class

Run the program with:

```bash
java Hello
```

The JVM starts and uses a **Class Loader** to load the required class definitions.

The main class is `Hello`. Other classes may be loaded when the program needs them.

Java commonly describes three built-in class-loader levels:

- **Bootstrap Class Loader** — loads core Java classes.
- **Platform Class Loader** — loads platform libraries.
- **Application Class Loader** — loads application classes from the class path or module path.

The class-loader delegation process generally asks a parent loader first before a child loader attempts to load a class.

## 5. Step 4 — Verify, Link, and Initialize

After a class is loaded, the JVM prepares it for use.

### Verification

The JVM checks that the bytecode follows JVM rules and is structurally valid. This helps prevent invalid bytecode from being executed.

### Linking

Linking includes three main activities:

- **Verification:** checks bytecode validity.
- **Preparation:** creates static fields and assigns their default values.
- **Resolution:** may convert symbolic references to direct references; some resolution can happen later when needed.

### Initialization

The JVM runs class initialization code, including static field initializers and `static` blocks, in the appropriate order.

Example:

```java
class Example {
    static int count = 10;

    static {
        System.out.println("Class initialized");
    }
}
```

The static initialization runs when the class is initialized, not every time an object is created.

## 6. Step 5 — Prepare Runtime Memory

The JVM uses different runtime memory areas for different purposes.

| Memory area | Simple explanation |
|---|---|
| **Heap** | Stores objects and arrays. |
| **JVM Stack** | Each thread has its own stack of method-call frames, including local variables and intermediate results. |
| **Method Area** | JVM specification area for class-level structures and runtime constant pools. In HotSpot, class metadata is commonly stored in native memory called **Metaspace**. |
| **PC Register** | Each Java thread has a program-counter register that tracks the current JVM instruction position for Java execution. |
| **Native Method Stack** | Supports execution of native methods, depending on the JVM implementation. |

Example:

```java
Employee employee = new Employee();
```

For this example, the `Employee` object is normally stored on the **heap**. The local variable `employee` is stored in the current method's stack frame if it is a local variable. It holds a reference to the object.

**Remember:** A reference variable and the object it refers to are two different things.

## 7. Step 6 — Execute the Bytecode

The JVM's **Execution Engine** executes the bytecode. Two important parts are the interpreter and the Just-In-Time (JIT) compiler.

### Interpreter

The interpreter executes bytecode instructions as the program runs.

- It can start executing code without first compiling the entire application to native code.
- Frequently interpreting the same code can have a performance cost.

### JIT Compiler

JIT stands for **Just-In-Time compiler**.

The JVM monitors execution and may identify frequently executed code, often called *hot code*. The JIT compiler can compile that code into native machine instructions for the current platform.

- The interpreter and JIT-compiled code can work together.
- JIT compilation helps improve performance for frequently executed code.
- The exact compilation decisions and thresholds depend on the JVM implementation and runtime behavior.

## 8. Step 7 — The CPU Executes Native Instructions

Native machine instructions are specific to the processor architecture and operating environment.

The JVM uses the platform's native instructions to carry out the program's work. This is an important part of Java's portability: the same compiled bytecode can run on different supported platforms when a compatible JVM is available.

## 9. Step 8 — Garbage Collection

While the program runs, it may create objects on the heap.

The JVM's **Garbage Collector (GC)** can reclaim heap memory occupied by objects that are no longer reachable by the application.

Garbage collection does not mean that every object is removed immediately when it is no longer needed. The JVM decides when and how to perform collection.

---

## Complete Example

Source code:

```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello, Java!");
    }
}
```

Commands:

```bash
javac Hello.java
java Hello
```

Output:

```text
Hello, Java!
```

What happens:

1. `javac` compiles `Hello.java` into `Hello.class`.
2. The `java` launcher starts the JVM.
3. The JVM loads, verifies, links, and initializes the required class.
4. The JVM invokes the `main()` method.
5. The execution engine interprets bytecode and may JIT-compile frequently executed code.
6. The CPU carries out the resulting native instructions.

---

## Interview-Ready Answer

**Question: How does a Java program execute?**

Java source code is compiled by `javac` into platform-independent bytecode stored in a `.class` file. When we run the program, the JVM loads the required classes, verifies and prepares them, and initializes them. The execution engine runs the bytecode using an interpreter and may use the JIT compiler to compile frequently executed code into native machine instructions. The CPU executes those instructions. During execution, the JVM also manages runtime memory and garbage collection.

## Quick Revision

- `.java` — Java source code.
- `javac` — compiles source code into bytecode.
- `.class` — contains JVM bytecode.
- **Class Loader** — loads class definitions.
- **Verification** — checks bytecode validity.
- **Heap** — stores objects and arrays.
- **JVM Stack** — holds method-call frames for each thread.
- **Interpreter** — executes bytecode instructions.
- **JIT compiler** — compiles frequently executed code into native instructions.
- **Garbage Collector** — reclaims memory from unreachable objects.

**One-line summary:** Java code is compiled to bytecode, and the JVM loads and executes that bytecode on the current platform.
