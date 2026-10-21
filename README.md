# Java JVM Fundamentals

A practical guide to understanding the Java Virtual Machine (JVM), how Java programs execute, and how memory is managed at runtime.

## Overview

The Java Virtual Machine is responsible for executing Java bytecode. It provides a runtime environment that allows Java programs to run on different operating systems when a compatible JVM is available.

This repository covers JVM concepts using simple explanations, diagrams, and practical Java examples.

## Topics Covered

- JVM vs JRE vs JDK
- Java compilation and bytecode execution
- JVM architecture
- Class loading process
- Runtime memory areas
- Stack vs heap memory
- Garbage collection
- JIT compiler and interpreter
- Common JVM memory errors
- Practical Java examples

## Java Execution Flow

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
    JVM Runtime
          |
          v
Interpreter and JIT Compiler
          |
          v
   Machine Execution
```

## Practical Examples

The `src/main/java` directory contains Java examples demonstrating:

- Basic program execution
- Stack and heap memory concepts
- Object creation and references
- Garbage collection basics

## Learning Goals

By exploring this repository, you will understand:

- How Java code runs inside the JVM
- How objects and local variables are handled at runtime
- How class loading works
- How garbage collection reclaims memory
- How the JVM improves application performance

## Technologies

- Java
- JVM
- Markdown
- Git and GitHub

## How to Run

Install a compatible JDK and verify your Java installation:

```bash
java -version
javac -version
```

Compile and run an example:

```bash
javac src/main/java/JVMExecutionDemo.java
java -cp src/main/java JVMExecutionDemo
```

## Further Learning

This repository is part of my ongoing exploration of Java internals and backend engineering concepts.

