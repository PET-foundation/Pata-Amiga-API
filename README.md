# Pata Amiga API
![java](https://img.shields.io/badge/java-17-blue)
![spring](https://img.shields.io/badge/spring-3.1-green)
![issues](https://img.shields.io/github/contributors/PET-foundation/Pata-Amiga-API)
![GitHub issues](https://img.shields.io/github/issues/PET-foundation/Pata-Amiga-API)
![GitHub pull requests](https://img.shields.io/github/issues-pr/PET-foundation/Pata-Amiga-API)
![Commits](https://img.shields.io/github/commit-activity/m/PET-foundation/Pata-Amiga-API)
[![Run tests on pull request](https://github.com/PET-foundation/Pata-Amiga-API/actions/workflows/tests_pull_request.yml/badge.svg)](https://github.com/PET-foundation/Pata-Amiga-API/actions/workflows/tests_pull_request.yml)
[![Build Docker Image](https://github.com/PET-foundation/Pata-Amiga-API/actions/workflows/build_and_push_to_dockerhub.yml/badge.svg)](https://github.com/PET-foundation/Pata-Amiga-API/actions/workflows/build_and_push_to_dockerhub.yml)

## About Pata Amiga API

* The [Pata Amiga](https://pet-foundation.github.io/Pata-Amiga-LandingPage/) is an open source customizable and extensible platform for helping homeless animals, It provides a simple and scalabel soluction for users and NGOs
* Project status: ![spring](https://img.shields.io/badge/working-green)
* [P.E.T Support](mailto:matheusvictorhenrique@gmail.com)

## UML

[![](https://mermaid.ink/img/pako:eNrNVU2P0zAQ_SuWj5Ct8tFu2tyAvSABqlRxQbnMOpPE2sS2bIfdUvW_43xUmy7OCgmByCF23huPZ-ZN7BNlskCaUdaAMXccKg1tLoh7BoR8NajJaUT65y35JEVFeDGHDlZzB3adFxbQogfGFnjjwZXb9lFqnyelZckb3HNmO-3zeQ9CoJ4TH6SwwCxh4zin7sAiYRrdULz7lelUMWfOuZjX5eL35AuzlsIX3WMN1oBSHooLY2e1v-JKYHgv5YM3jr001vwdgQo0THNluRS-JBdFaCSDl4s-ilK6JEs5B4fe6sy1YH-kyrCNTxKjkHFfsNoV12ePTx4UKq-syKv6KlKnVoMgCANjdR-uj_wOjHGxxHaiQt0u8woLXmlEHzckC80XxMLM-b3Ulij3WugkvdDOSysONTb2nx8P_8MZMHRuTqOckpubaXLZJSM1TGUff86Z3Rs3Gda-ZtRPhka-MnLivLAZF86MLnr4N5yyfH981fh3nHqypQFtXb8CL9xdMvRDTm2NTlmauWkB-iGnuTg7O-isPBwFo5nVHQZ0rPF09dCshMY41LW3lfrzdDn1Q0AViG9SPtu4b5qd6BPNoiRdbXe7bRSmuyiMNrdxQI8O3q2iKNpu4tv1Zpuuwzg5B_TH4CJaJZs4TKN0nSRhz8bnn1EWCLI?type=png)](https://mermaid.live/edit#pako:eNrNVU2P0zAQ_SuWj5Ct8tFu2tyAvSABqlRxQbnMOpPE2sS2bIfdUvW_43xUmy7OCgmByCF23huPZ-ZN7BNlskCaUdaAMXccKg1tLoh7BoR8NajJaUT65y35JEVFeDGHDlZzB3adFxbQogfGFnjjwZXb9lFqnyelZckb3HNmO-3zeQ9CoJ4TH6SwwCxh4zin7sAiYRrdULz7lelUMWfOuZjX5eL35AuzlsIX3WMN1oBSHooLY2e1v-JKYHgv5YM3jr001vwdgQo0THNluRS-JBdFaCSDl4s-ilK6JEs5B4fe6sy1YH-kyrCNTxKjkHFfsNoV12ePTx4UKq-syKv6KlKnVoMgCANjdR-uj_wOjHGxxHaiQt0u8woLXmlEHzckC80XxMLM-b3Ulij3WugkvdDOSysONTb2nx8P_8MZMHRuTqOckpubaXLZJSM1TGUff86Z3Rs3Gda-ZtRPhka-MnLivLAZF86MLnr4N5yyfH981fh3nHqypQFtXb8CL9xdMvRDTm2NTlmauWkB-iGnuTg7O-isPBwFo5nVHQZ0rPF09dCshMY41LW3lfrzdDn1Q0AViG9SPtu4b5qd6BPNoiRdbXe7bRSmuyiMNrdxQI8O3q2iKNpu4tv1Zpuuwzg5B_TH4CJaJZs4TKN0nSRhz8bnn1EWCLI)

## Table of contents

> * [Pata-Amiga-API](#pata-amiga-api)
>   * [About / Synopsis](#about-pata-amiga-api)
>   * [UML](#uml)
>   * [Documentation](#documentation)
>   * [Getting starter](#getting-starter)
>     * [Content](#content)
>     * [Requirements](#requirements)
>     * [Build](#build)
>   * [Contributing / Reporting issues](#contributing--reporting-issues)
>   * [License](#license)

## Documentation

Sample:

* You can see documentation on [localhost](http://localhost:8080/swagger-ui).

## Getting starter
* Clone the repository:
  
```bash
git clone https://github.com/PET-foundation/Pata-Amiga-API
cd Pata-Amiga-API
```
* Change the database configs in yours ```applicatio.yml```

* Run on:

**Unix**

```bash
./mvnw clean install
```

**Windows**

```bash
mvnw.cmd clean install
```

- Run the Spring Boot:

**Unix**

```bash
./mvnw spring-boot:run
```

**Windows**

```bash
mvnw.cmd spring-boot:run
```

**or**

* You can Run with docker. You can run oficial docker image:

```docker
docker run matheusvict/pataamiga
```

**or**

* build with Dockerfile
```
docker build .
```

* Open on your [localhost](http://localhost:8080/api)

### Content

Description, sub-modules organization...

### Requirements

[Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[MySQL](https://www.mysql.com/)

### Build

```bash
 mvn clean install
```

Build options:

```bash
 mvn clean install -Dspring.profiles.active=dev
```

```bash
 mvn clean install -Dspring.profiles.active=prod
```

```bash
 mvn clean install -Dspring.flyway.enabled=false
```

## Contributing / Reporting issues

* First of all, to contributing, clone the repository:

```bash
git clone https://github.com/PET-foundation/Pata-Amiga-API
```

```bash
cd Pata-Amiga-API
```

```bash
docker compose up
```

**or**

```bash
mvn clean package -Dspring.profiles.active=dev
```
### And just do your part

## License

[Gnu license](https://www.gnu.org/licenses/gpl-3.0.pt-br.html)
