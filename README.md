# Pata Amiga API
![java](https://img.shields.io/badge/java-17-blue)
![spring](https://img.shields.io/badge/spring-3.1-green)
![issues](https://img.shields.io/github/contributors/PET-foundation/Pata-Amiga-API)
![GitHub issues](https://img.shields.io/github/issues/PET-foundation/Pata-Amiga-API)
![GitHub pull requests](https://img.shields.io/github/issues-pr/PET-foundation/Pata-Amiga-API)
![Commits](https://img.shields.io/github/commit-activity/m/PET-foundation/Pata-Amiga-API)

## About Pata Amiga API

* The [Pata Amiga](https://pet-foundation.github.io/Pata-Amiga-LandingPage/) is an open source customizable and extensible platform for helping homeless animals, It provides a simple and scalabel soluction for users and NGOs
* Project status: ![spring](https://img.shields.io/badge/working-green)
* [P.E.T Support](mailto:matheusvictorhenrique@gmail.com)

See real examples:

* <https://github.com/PET-foundation/Pata-Amiga>
* <https://github.com/PET-foundation/Pata-Amiga-Android>

## Table of contents

> * [Pata-Amiga-API](#pata-amiga-api)
>   * [About / Synopsis](#about-pata-amiga-api)
>   * [Documentation](#documentation)
>   * [Getting starter](#getting-starter)
>     * [Screenshots](#screenshots)
>     * [Features](#features)
>   * [Code](#code)
>     * [Content](#content)
>     * [Requirements](#requirements)
>     * [Limitations](#limitations)
>     * [Build](#build)
>     * [Deploy (how to install build product)](#deploy-how-to-install-build-product)
>   * [Resources (Documentation and other links)](#resources-documentation-and-other-links)
>   * [Contributing / Reporting issues](#contributing--reporting-issues)
>   * [License](#license)
>   * [About Nuxeo](#about-nuxeo)

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
docker run pata-amiga-api
```

**or**

* build with Dockerfile
```
docker build .
```

* Open on your [localhost](http://localhost:8080/api)

### Screenshots

### Features

## Code

[![Build Status](https://qa.nuxeo.org/jenkins/buildStatus/icon?job=/nuxeo/addons_nuxeo-sample-project-master)](https://qa.nuxeo.org/jenkins/job/nuxeo/job/addons_nuxeo-sample-project-master/)

### Content

Description, sub-modules organization...

### Requirements

See [CORG/Compiling Nuxeo from sources](http://doc.nuxeo.com/x/xION)

Sample: <https://github.com/nuxeo/nuxeo/blob/master/nuxeo-distribution/README.md>

### Limitations

Sample: <https://github.com/nuxeo-archives/nuxeo-features/tree/master/nuxeo-elasticsearch>

### Build

    mvn clean install

Build options:

* ...

### Deploy (how to install build product)

Direct to MP package if any. Otherwise provide steps to deploy on Nuxeo Platform:

 > Copy the built artifacts into `$NUXEO_HOME/templates/custom/bundles/` and activate the `custom` template.

## Resources (Documentation and other links)

## Contributing / Reporting issues

Link to JIRA component (or project if there is no component for that project). Samples:

* [Link to component](https://jira.nuxeo.com/issues/?jql=project%20%3D%20NXP%20AND%20component%20%3D%20Elasticsearch%20AND%20Status%20!%3D%20%22Resolved%22%20ORDER%20BY%20updated%20DESC%2C%20priority%20DESC%2C%20created%20ASC)
* [Link to project](https://jira.nuxeo.com/secure/CreateIssue!default.jspa?project=NXP)

## License

[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

## About Nuxeo

Nuxeo Platform is an open source Content Services platform, written in Java. Data can be stored in both SQL & NoSQL databases.

The development of the Nuxeo Platform is mostly done by Nuxeo employees with an open development model.

The source code, documentation, roadmap, issue tracker, testing, benchmarks are all public.

Typically, Nuxeo users build different types of information management solutions for [document management](https://www.nuxeo.com/solutions/document-management/), [case management](https://www.nuxeo.com/solutions/case-management/), and [digital asset management](https://www.nuxeo.com/solutions/dam-digital-asset-management/), use cases. It uses schema-flexible metadata & content models that allows content to be repurposed to fulfill future use cases.

More information is available at [www.nuxeo.com](https://www.nuxeo.com).
