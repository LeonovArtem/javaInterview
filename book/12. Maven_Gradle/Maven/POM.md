# POM (Project Object Model)
![POM.png](img%2FPOM.png)

General Project Information - общая информация о проекте

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <!-- POM Relationships -->
    <groupId>org.aleonov.javainteview</groupId>
    <artifactId>javaInterview</artifactId>
    <version>1.0-SNAPSHOT</version>
    <name>New Project</name>
    <contributors>
        <contributor>
            <name>a.leonov</name>
            <organization>Dats.Team</organization>
            <roles>
                <role>Developer</role>
            </roles>
        </contributor>
    </contributors>
    
    <!-- Переменные   -->
    <properties>
        <java.version>17</java.version>
    </properties>
    <!-- Зависимости   -->
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>3.8.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <build>
        
    </build>
    
    <reporting>
        
    </reporting>
    
</project>

```

http://maven.apache.org/xsd/maven-4.0.0.xsd - XSD схема (нужна для валидации)