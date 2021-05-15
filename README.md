# CCP

CCP comes from Computer Components Products and it is a Desktop Applictaion build for selling products as computer components.	

We are using the following tehnologies:

Java 15	

JavaFx (as GUI)	

Maven (as build tool)	

CSS (as design)	

Nitirite Java (as DataBase)	

## Specifitations
To be able to install and run this project, please make sure you have installed at least Java 11 . Otherwise, the setup will not work! To check your java version, please run java -version in the command line.

## Setup and Run

CLone the repository with 
```bash
git clone https://github.com/fis2021/CCP
```
Verify that the project Builds locally

After you acces the folder, write the following command to check if it builds:
```bash
mvn clean install
```
If you prefer to run using the wrappers, you could also build the project using:
```bash
./mvnw clean install (for Linux or MacOS)
or 
mvnw.cmd clean install (for Windows)
```

In order to run it write:
```bash
./mvnw javafx:run or ./mvn javafx:run (for Linux or MacOS)

mvnw.cmd javafx:run or mvn.cmd javafx:run(for Windows)
```
## Open in IntelliJ IDEA

To open the project in IntelliJ idea, you have to import it as either a Maven, or a Gradle project, depending on what you prefer. After you import it, in order to be able to run it, you need to set up your IDE according to the official documentation. Please read the section for Non-Modular Projects from IDE. If you managed to follow all the steps from the tutorial, you should also be able to start the application by pressing the run key to the left of the Main class.


