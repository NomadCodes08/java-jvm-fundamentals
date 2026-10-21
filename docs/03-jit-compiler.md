# Just-In-Time (JIT) Compiler in Java

## 1. What Is JIT?

**JIT stands for Just-In-Time compiler.**

It is part of the JVM's execution engine that compiles frequently executed Java bytecode into native machine instructions while the program is running.

## 2. Simple Execution Flow

```text
Java Source Code (.java)
        ↓
   Java Compiler (javac)
        ↓
   Bytecode (.class)
        ↓
       JVM
        ↓
 Interpreter + Runtime Profiling
        ↓
 JIT Compiler (for hot code)
        ↓
Native Machine Instructions
        ↓
        CPU
```

**Remember:**
- `javac`: Java source code → bytecode.
- **JIT:** Frequently executed bytecode → native machine code at runtime.

The JVM can interpret bytecode while also profiling execution and compiling frequently used code.

## 3. Why Does the JVM Use JIT?

The JVM observes which parts of the program are executed frequently.

The JIT's basic idea is:

> “This code runs frequently. Instead of interpreting it repeatedly, I'll compile it into native instructions for this machine.”

Once compiled, the code can run using the generated native instructions on subsequent executions, often improving performance.

## 4. Example: Different CPUs

Suppose the same Java bytecode runs on two computers:

```text
                 Same Java bytecode
                         ↓
                    JVM  +  JIT
                    ↙        ↘
              x86-64 CPU    ARM CPU
                  ↓            ↓
          x86-64 native    ARM native
            instructions   instructions
```

The JIT generates native code suitable for the CPU on which that JVM is running. This is one reason the same Java bytecode can run on different platforms when a compatible JVM is available.

## 5. How Does the JVM Identify Hot Code?

The JVM uses runtime profiling and internal counters to gather information about program execution. This may include:

- **Method invocation counts** — how often a method is called.
- **Loop/back-edge counts** — how often execution repeats through a loop.
- **Execution behavior** — information that can help the JVM optimize code.

When the relevant counters or profiling information reach implementation-dependent thresholds, the JVM may identify code as **hot** and compile it with the JIT.

You do not need to write these counters yourself; the JVM manages them internally.

### Example

```java
for (int i = 0; i < 1_000_000; i++) {
    calculate(i);
}
```

If `calculate()` or the loop runs frequently, the JVM may identify that code as hot and compile suitable parts into native code.

**Important:** It is not as simple as “called 100 times = JIT.” Actual behavior depends on the JVM implementation, runtime profiling, compilation thresholds, and optimization decisions.

## 6. A Simple Analogy

Imagine a manager observing which tasks employees perform most often:

```text
Manager (JVM)
      ↓
Observes repeated tasks
      ↓
Task A → performed a few times
Task B → performed very often
      ↓
Task B may benefit from optimization
      ↓
JIT compiles hot code
```

Similarly, the JVM profiles running code, identifies frequently executed parts, and may compile them into optimized native instructions.

## 7. Interpreter and JIT Can Coexist

Imagine an application with several methods:

```java
methodA(); // called once
methodB(); // called many times
methodC(); // called occasionally
```

Initially, the JVM may interpret bytecode. As the program runs, it gathers profiling information and may JIT-compile frequently executed code.

At a given point, execution can look conceptually like this:

```text
Method A → Interpreted
Method B → JIT-compiled native code
Method C → Interpreted
Method D → JIT-compiled native code
```

This is a simplified model. Modern JVMs, such as HotSpot, use adaptive and tiered compilation, so compilation and optimization can happen in stages.

## 8. Why Is JIT Useful?

Consider two methods:

```java
methodA(); // called a few times
methodB(); // called millions of times
```

Compiling frequently executed code can provide a meaningful performance improvement. The JVM can focus optimization effort on code that matters most, rather than compiling every method immediately.

## 9. Key Takeaway

**The JVM starts executing bytecode, profiles the program as it runs, identifies hot code, and may JIT-compile that code into optimized native machine instructions. Other code can continue to be interpreted.**
