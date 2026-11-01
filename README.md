<div align="center">

# 🔬 Modern Java E-Learn Lab

[![CI](https://github.com/ThFoxY/modern-java-e-learn-lab/actions/workflows/ci.yml/badge.svg)](https://github.com/ThFoxY/modern-java-e-learn-lab/actions/workflows/ci.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

A hands-on lab for learning modern Java (25 LTS): language fundamentals,
testing, and professional tooling.

</div>

## Contents

| Class | Description |
|---|---|
| `Slugifier` | Converts any text into a URL-safe slug: lowercase ASCII letters, digits and single hyphens. |

```java
Slugifier.slugify("Crème brûlée!"); // "creme-brulee"
```

## Tech stack

| Tool | Version |
|---|---|
| Java | 25 (LTS) |
| Maven | 3.9.16, via the Maven Wrapper |
| JUnit | 6.1.3 |
| SLF4J / Logback | 2.0.20 / 1.6.5 |
| IDE | IntelliJ IDEA |

## Prerequisites

- JDK 25 (for example Eclipse Temurin), with `JAVA_HOME` set or `java` on the `PATH`.
- No Maven installation needed: the wrapper downloads the pinned version.

## Build and test

```bash
# Linux / macOS
./mvnw verify
```

```powershell
# Windows
.\mvnw.cmd verify
```

## Project structure

```
src/
├── main/java/io/github/thfoxy/
│   ├── Main.java
│   └── Slugifier.java
└── test/java/io/github/thfoxy/
    └── SlugifierTest.java
```

## Roadmap

- [x] Tooling: Maven Wrapper, JUnit 6, logging, CI
- [ ] Object-oriented and modern Java: records, sealed types, pattern matching
- [ ] Generics, streams, exceptions, I/O and concurrency

Web development, security, CI/CD and LLM integration will be covered
in a dedicated project.

## License

Released under the [MIT License](LICENSE).
