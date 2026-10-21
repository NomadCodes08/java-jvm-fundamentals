# Java Code Execution Flow

## 1. The Big Picture

When you run a Java program, it follows this flow:

```text
Hello.java
   |
   | javac Hello.java
   v
Hello.class (Bytecode)
   |
   | java Hello
   v
JVM loads and checks the class
   |
   v
Execution Engine
   |
   +---- Interpreter: executes bytecode instructions
   |
   +---- JIT Compiler: compiles frequently executed code
   |                    into native machine instructions
   v
CPU executes native instructions
```

**Remember:** `javac` creates bytecode. The JVM executes it on the current platform.

## 2. Step 1 — Java Source Code (`.java`)

Example: `Hello.java`

```java
public class Hello {
    public static void main(String[] args) {
        int number = 10;
        System.out.println(number);
    }
}
```

This is the source code written by the developer.

## 3. Step 2 — Compilation

Run:

```bash
javac Hello.java
```

The Java compiler checks the code and creates `Hello.class`. The `.class` file contains **Java bytecode**.

Bytecode is an intermediate instruction format designed for the JVM. It is not the same as native machine code for a particular CPU.

## 4. Step 3 — Start the JVM

Run:

```bash
java Hello
```

The Java launcher starts the JVM. The JVM loads the required class definitions and performs checks and setup before executing the program.

At a high level, the JVM:

1. Loads the required classes.
2. Verifies bytecode and performs linking.
3. Initializes classes when required.
4. Invokes the `main()` method.

## 5. Step 4 — Interpreter Executes Bytecode

The interpreter executes bytecode instructions as the program runs. You do not need to memorize the exact bytecode instructions to understand the basic flow.

## 6. Step 5 — JIT Compiler Optimizes Frequently Executed Code

JIT means **Just-In-Time compiler**.

While the program runs, the JVM can monitor which methods or loops execute frequently. This frequently executed code is often called **hot code**.

The JIT compiler may compile hot code into native machine instructions suited to the current platform.

- The interpreter can start executing code quickly.
- Compiled native instructions can make frequently executed code run faster.
- The JVM can use both interpreted and JIT-compiled code during one program run.

The exact decision to compile code depends on the JVM implementation and runtime behavior.

## 7. Step 6 — CPU Executes Native Instructions

The CPU does not directly execute Java source code or JVM bytecode as ordinary native instructions. The JVM uses platform-specific native instructions to perform the program's work, and the CPU executes those instructions.

This helps explain Java's portability: the same `.class` bytecode can run on different supported platforms, provided a compatible JVM is available.

## 8. Example: Follow the Commands

Create `Hello.java`:

```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello, Java!");
    }
}
```

Compile it:

```bash
javac Hello.java
```

This creates `Hello.class`.

Run it:

```bash
java Hello
```

Output:

```text
Hello, Java!
```

The command `java Hello` uses the class name, not `Hello.class` or `Hello.java`.

## 9. Common Interview Questions

### Q1. What is the difference between bytecode and machine code?

- **Bytecode** is the intermediate instruction format stored in a `.class` file and executed by a JVM.
- **Machine code** consists of native instructions for a particular processor architecture.

### Q2. Does the JVM compile Java code?

`javac` compiles Java source code into bytecode. At runtime, the JVM can use its JIT compiler to compile frequently executed bytecode into native machine instructions.

### Q3. Does the interpreter stop when the JIT compiler starts?

No. Interpreted and JIT-compiled code can coexist. The JVM decides how different parts of the program are executed.

### Q4. Why can Java run on different operating systems?

The compiler generates JVM bytecode rather than ordinary machine code for one specific operating system and CPU. A compatible JVM for each platform can execute that bytecode.

### Q5. Is all Java bytecode compiled by the JIT?

No. JIT compilation is selective and depends on runtime behavior and JVM implementation. Some code may remain interpreted.

## Quick Revision

| Term | Meaning |
|---|---|
| `.java` | Source code written by the developer |
| `javac` | Java source compiler |
| `.class` | File containing Java bytecode |
| JVM | Loads and executes bytecode |
| Interpreter | Executes bytecode instructions |
| JIT compiler | Compiles frequently executed code into native instructions |
| CPU | Executes native machine instructions |

**One-line interview answer:** Java source code is compiled by `javac` into JVM bytecode. At runtime, the JVM loads and verifies classes, then executes bytecode using an interpreter and may JIT-compile frequently executed code into native machine instructions for the CPU.
