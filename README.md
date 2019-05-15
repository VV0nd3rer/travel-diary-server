## Spring Boot server for java web application 'Travel diary'
### Used technologies:
* Java 8
* Spring Boot 2.X with embedded Tomcat server
* JPA 2.1 and Hibernate 5.1
* Postgresql 9.6
* Apache ActiveMQ Artemis 2.X
* OAuth 2.0
* Jasypt integration for Spring boot 2.X

### How to deploy on your local machine
1. Clone or download [Travel diary web application server](https://github.com/kverchi/travel-diary-server.git). 
     * Use your favorite IDE for development: [Spring Tool Suite for Eclipse](https://spring.io/tools), [IntelliJ IDEA](https://www.jetbrains.com/idea/), etc.
2. Set Jasypt enctiption key (ask me for Jasypt encryption key) as local environment variable DIARY_PASS_VAR. [How to set environment variable in Windows](https://www.computerhope.com/issues/ch000549.htm) and [how to set environment variable in Linux](https://www.tecmint.com/set-path-variable-linux-permanently/).

3. Prepeare you local database:
     * Download and install [PostgreSQL](https://www.postgresql.org/download/). Optionally, you can download and install [pgAdmin 4](https://www.pgadmin.org/download/) which is an administration and development platform for PostgreSQL.
     * Clone or download application database backup file form here https://github.com/kverchi/diary-db-backup.git
     * Create the database with name *diary*
     * Restore postgresql backup file *diary-v2.0.tar* on your local Postgresql server for newly created database *diary*. [How to restore backup](https://www.postgresql.org/docs/9.6/backup-dump.html#BACKUP-DUMP-RESTORE) 
     
    * Set database credentials for connection to your database 
     
     There are 2 ways to set database credentials into application's properties file: as a *plain text* or as an *encrypted values*
     
     a. Set credentials as a *plain text*:
     * Open *<path-to-app>/src/main/resources/application.properties* and set your database username and password to JDBC_DATABASE_USERNAME and JDBC_DATABASE_PASSWORD properties.
     
     b. Set credentials as an *encrypted values* with Jasypt encryption tool:
     * Download and install [Jasypt](http://www.jasypt.org/download.html)
     * Encrypt your database username and password with your environment variable. [How to encrypt with Jasypt](https://apereo.atlassian.net/wiki/spaces/CASUM/pages/103261428/HOWTO+Use+Jasypt+to+encrypt+passwords+in+configuration+files)
     * Open *<path-to-app>/src/main/resources/application.properties* and set your encrypted username and encrypted password to JDBC_DATABASE_USERNAME and JDBC_DATABASE_PASSWORD properties
     * Check your local environment variable DIARY_PASS_VAR (see step 2)
     
4. Open project's directory in terminal, build and start your project with Maven tool `mvn spring-boot:run`. [How to install Maven](https://maven.apache.org/install.html)

   This project uses [Spring profiles](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-profiles.html) and the default profile is *dev*. To use other profiles, make sure they are enabled. [How to enable Spring profiles](https://docs.spring.io/spring-boot/docs/current/maven-plugin/examples/run-profiles.html) 


### How to deploy Docker container
> To get started with Docker, read [Docker guide](https://docs.docker.com/get-started/)
1. Prepeare your database as described [here](https://github.com/kverchi/diary-db-backup/tree/master)
2. You can *build Docker image* from a source code or *download image* from a [Docker Hub](https://hub.docker.com)
   
   To *build Docker image*:
   * Clone or download [Travel diary web application server](https://github.com/kverchi/travel-diary-server.git)
   * Go to your project directory and build an image:
     `mvn install dockerfile:build`
   
   To *download image* from Docker Hub:
     `docker pull flyingmind/travel-diary`
 3. Start a container
   `docker run --name <container-name> -p 8080:8080 -t -e DIARY_PASS_VAR=<Jasypt-encryption-key> -e spring.datasource.url=jdbc:postgresql://<your-db-host>:5432/diary -e spring.datasource.username=<your-db-username> -e spring.datasource.password=<your-db-pass> flyingmind/travel-diary:latest`
   
     
   
