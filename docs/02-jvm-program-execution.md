# Java Program Execution: How the JVM Works

## 1. Compilation (`javac`)

- `javac PlatformCheck.java` parses the source code, checks types, and generates `PlatformCheck.class`.
- The `.class` file is not executable machine code. It is a binary format containing a constant pool, method signatures, and bytecode instructions (opcodes such as `iload`, `invokevirtual`, and `areturn`).
- Every `.class` file begins with the magic number `0xCAFEBABE`, followed by a version number. The JVM uses this information to check class-file compatibility before proceeding.

## 2. Class Loading

The JVM's class-loader subsystem reads `.class` files from disk or from a `.jar` file using a delegation model:

- **Bootstrap ClassLoader** loads core JDK classes.
- **Platform ClassLoader** loads platform classes and modules.
- **Application ClassLoader** loads application classes and third-party libraries.

Each class loader generally delegates to its parent first before attempting to load a class itself.

## 3. Bytecode Verification and Linking

- Before execution, the bytecode verifier checks the class file for structural and type safety—for example, invalid operand-stack operations, illegal type usage, and invalid control-flow instructions.
- This is an important security boundary: bytecode can come from different sources, so the JVM verifies it rather than blindly trusting it.
- **Linking** includes:
  - **Preparation:** creates static-field storage and assigns default values.
  - **Resolution:** converts symbolic references into direct references when needed.
- **Initialization** then runs class initialization code, including static initializers.

## 4. Runtime Data Areas

- **Method Area / Metaspace** — stores class-level metadata, such as method information and the runtime constant pool. In HotSpot, since Java 8, class metadata is stored in native memory called **Metaspace**, replacing PermGen. Static fields themselves are associated with their class, but their storage details depend on the JVM implementation.
- **Heap** — a memory area shared by threads in a JVM instance where objects and arrays are allocated. It is managed by the **Garbage Collector (GC)**.
- **JVM Stack** — each thread has its own stack. It contains stack frames for method calls, including local variables and an operand stack.
- **PC Register** — each thread has its own program-counter register, which tracks the current JVM instruction (for a non-native method).
- **Native Method Stack** — supports execution of native methods, where applicable, such as code called through the Java Native Interface (JNI).

## 5. Execution Engine

The JVM executes bytecode using interpretation and just-in-time (JIT) compilation:

- The **interpreter** executes bytecode instructions as the program runs.
- The **JIT compiler** identifies frequently executed code (often called *hot* code) and compiles it into native machine code for the current CPU. HotSpot uses tiered compilation, which can involve the C1 and C2 compilers.
- The compiled native code can be reused on subsequent calls, reducing the need to interpret that code again.

This is where CPU-specific instructions are generated. For example, the JIT may produce x86-64 or ARM64 machine code depending on the system running the JVM. This process happens at runtime.

Meanwhile, the **Garbage Collector** reclaims heap memory occupied by objects that are no longer reachable. Java code generally does not manually free object memory as it would in languages such as C.

## Summary

1. `javac` compiles Java source code into platform-independent bytecode (`.class` files).
2. The class loader loads classes, and the JVM verifies, links, and initializes them.
3. The JVM uses runtime memory areas to manage class information, objects, and per-thread execution state.
4. The execution engine interprets bytecode and JIT-compiles frequently used code into native machine instructions.
5. The Garbage Collector reclaims memory from unreachable objects.

**Key idea:** Java bytecode can run on different operating systems and CPUs as long as a compatible JVM is available. The JVM handles the platform-specific execution details.
