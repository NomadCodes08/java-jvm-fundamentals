# JVM Overview

## What is the JVM?

The Java Virtual Machine (JVM) is a runtime environment that loads and executes Java bytecode.

In simple words, the JVM acts as a bridge between compiled Java code and the machine that runs it.

## JVM vs JRE vs JDK

| Term | Meaning |
|---|---|
| JVM | Executes Java bytecode |
| JRE | Traditionally refers to the JVM and libraries needed to run Java applications |
| JDK | Provides development tools, including the Java compiler, plus runtime components |

Modern Java distributions may package these components differently.

## How Java Code Executes

1. Write source code in a `.java` file.
2. Compile it using `javac`.
3. The compiler generates `.class` bytecode.
4. The JVM loads and verifies the class.
5. The runtime executes the bytecode using interpretation and JIT compilation.

## Why is the JVM Important?

- Enables portability across compatible platforms.
- Manages runtime memory.
- Supports automatic garbage collection.
- Provides runtime checks and security mechanisms.
- Optimizes application performance.

## Key Takeaway

Java source code is compiled into bytecode, and the JVM executes that bytecode on a compatible platform.