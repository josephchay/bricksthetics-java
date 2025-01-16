# Bricksthetics

---

## Project Information
Bricksthetics is a captivating game that reinvents the classic brick-breaking experience with a focus on aesthetics and user engagement. 
It boasts a sleek, minimalist design, complemented by a range of interactive features that elevate gameplay. 
Enjoy dynamic levels with varying patterns and complexities, enhanced by customizable options for a personalized touch. 
The inclusion of intuitive controls and accessibility features ensures a seamless and inclusive gaming experience. 
With its combination of visual beauty and engaging gameplay, Bricksthetics is a delightful escape for gamers seeking both challenge and charm.

![Brand Logo](https://github.com/josephchay/COMP2042_CW_hcyjc11/assets/136827046/37127517-a597-46a4-8084-84ea5be15659)

---

## Table of Contents
- [Bricksthetics](#bricksthetics)
  - [Project Information](#project-information)
  - [Table of Contents](#table-of-contents)
  - [Showcase](#showcase)
  - [Installation Setup](#installation-setup)
    - [Java Development Kit (JDK):](#java-development-kit-jdk)
    - [Apache Maven:](#apache-maven)
    - [Running the Application](#running-the-application)
      - [Intellij](#intellij)
  - [Project Structure](#project-structure)
    - [Core Package](#core-package)
    - [External Framework Package](#external-framework-package)
  - [Contribution Guidelines](#contribution-guidelines)
  - [Code of Conduct](#code-of-conduct)
  - [Contributors and Acknowledgements](#contributors-and-acknowledgements)
  - [Changelog](#changelog)
  - [License](#license)
  - [Final Note](#final-note)

---

## Showcase

![](src/main/resources/images/samples/sample.png)

This project was developed solely on JavaFX version 21 only by utilizing dependencies from the JavaFX library in Maven.

---


## Installation Setup

### Java Development Kit (JDK):
1. Download and install the JDK from [here](https://www.oracle.com/java/technologies/javase-downloads.html) or from [here](https://openjdk.java.net/install/).
2. Set the environment variable `JAVA_HOME` to the JDK installation directory.
3. Add the JDK `bin` directory to the `PATH` environment variable.
4. Verify the installation by running the following command in the terminal:
  ```
  java -version
  ```
5. The output should be similar to the following:
  ```
    java version [java version]
    Java(TM) SE Runtime Environment (build [java version])
    Java HotSpot(TM) [bit]-Bit Server VM (build [java version], mixed mode, sharing)
   ```
### Apache Maven:
1. Download and install Maven from [here](https://maven.apache.org/download.cgi).
2. Set the environment variable `MAVEN_HOME` to the Maven installation directory.
3. Add the Maven `bin` directory to the `PATH` environment variable.
4. Verify the installation by running the following command in the terminal:
  ```
  mvn -version
  ```
5. The output should be similar to the following:
  ```
    Apache Maven 3.x.x ([unique-build-id])
    Maven home: [path to maven installation]
    Java version: [java version], vendor: [vendor name], runtime: [path to java runtime environment]
    Default locale: [locale], platform encoding: [encoding]
    OS name: "[os name]", version: "[os version]", arch: "[os architecture]", family: "[os family]"
  ```

### Running the Application

To run this project form the command line, follow these steps:
1. Clone the repository using the following command:
  ```
  git clone
  ```
1. Open your command line terminal.
2. Navigate to the root directory of the project where the `pom.xml` file is located.
3. Then, execute the following Maven command:
  ```
  mvn clean
  ```
   - This command cleans the `target` directory, removing all previously compiled Java classes and resources.
4. Next, execute the following Maven command:
  ```
  mvn install
  ```
   - This command compiles the source code of the project.
5. Alternatively, you can execute the following combined command:
  ```
  mvn clean install
  ```
6. Now to start the application, run:
  ```
  mvn javafx:run
  ```

  or alternatively:
  ```
  java -jar target/bricksthetics-1.0-SNAPSHOT.jar
  ```

#### Intellij

To run this project from Intellij, follow these steps:
1. Open Intellij IDEA.
2. Choose 'Open form the welcome screen or File > Open' from the menu.
3. Navigate to and select the root directory of the project where the `pom.xml` file is located.
4. In the 'Maven' tool window, which is usually on the right tab sections side, expand the 'Lifecycle' section.
5. Double-click on the 'clean' to execute the cleaning process.
6. Then double-click on 'install' to build the project and install the dependencies.
7. After the build completes, you can then run the `Main.java` file located in the `src/main/java/breakout` directory.

---

## Project Structure

This project is mainly divided into parts of two major packages. One which is the core and the other the overall external framework
that heavily depends on the core package.

### Core Package
The core package can be assigned with a group and artifact ID and be used as a Maven dependency. However for better readability for this project,
it was left within the same directory as the main project.
The core folder is created to account for the separation of concerns and to ensure that the core functionalities of the game can be reused in other projects.

### External Framework Package
Any package then which employs the core directory will be able to implement and further enhance its functionality.
For instance: the `entities` folder in the core package contains `Player` and `Enemy` actors, and a `Projectile` entity.
Their broad terms and surface functionalities serves the purpose of being reused in multiple projects.
In the case of this project, the `players` are further specialized to `Paddle` and `Cannon`, while the `enemies` are further specialized to `Brick` classes.
`projectiles` are then specialized to `Orb` class.

---

## Contribution Guidelines

We enthusiastically welcome and appreciate contributions from the community.
To ensure smooth collaboration and maintain the quality of the project, please follow these guidelines:

1. **Fork & Clone**
    - Begin by forking the repository to your GitHub account.
    - Once done, clone the forked repo to your local machine.

2. **Set Up & Install**
    - Follow the [Installation Setup](#installation-setup) to get the development environment ready.

3. **Create a New Branch**
    - For every new feature or bugfix, create a separate branch.
    - Use a descriptive name for the branch, such as `feature/new-feature-name` or `bugfix/issue-number`.

4. **Write Quality Code**
    - Ensure your code adheres to the existing coding standards and conventions of the project.
    - Properly comment your code and keep functions and classes modular.

5. **Test Thoroughly**
    - Before submitting a pull request, ensure that your code changes do not break any existing functionality.
    - Write tests if applicable.

6. **Commit and Push**
    - Commit your changes with meaningful commit messages.
    - Once satisfied, push the branch to your fork on GitHub.

7. **Submit a Pull Request (PR)**
    - Go to the main repo on GitHub and click on the “New pull request” button.
    - Choose the branch you just pushed from the dropdown and submit the PR with a comprehensive description of the changes.

8. **PR Review**
    - Maintainers will review your PR. Address any comments or requested changes.
    - Once approved, your PR will be merged into the main branch.

---

## Code of Conduct

In order to ensure that the Bricksthetics community is welcoming to all, please review and abide by the [Code of Conduct](

## Contributors and Acknowledgements

This project would not have been possible without the contributions, feedback, and expertise of the following individuals:

- **Joseph Chay**
    - _Lead Developer_
    - [GitHub](https://github.com/josephchay)
    - [Twitter](https://twitter.com/josephchay)
    - [LinkedIn](https://www.linkedin.com/in/josephchay)

---

## Changelog

Detailed changes for each release are documented in the [release notes](https://github.com/josephchay/bricksthetics/releases).

For more detailed changelog entries and version histories, see our [changelog](CHANGELOG.md).

---

## License
MIT License

Copyright (c) 2023-present Bricksthetics Developers

For more details, please refer to the [license](LICENSE.md).

---

## Final Note

If you discover any security vulnerability within Bricksthetics, please [send](mailto:josephemmanuelchay@gmail.com) Joseph Chay an email.

Should you have any questions or detailed discussions requirements,
please use the project's [Issues](https://github.com/josephchay/bricksthetics/issues) section.
