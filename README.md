## Angular server for java web application 'Travel diary'
### Used technologies:
* Java 8
* Spring Boot 2.X with embedded Tomcat server
* Postgresql 9.6
* Apache ActiveMQ Artemis 2.X
* OAuth 2.0
* Jasypt integration for Spring boot 2.X

### To deploy on your local machine
1. Clone or download [travel-diary-server project](https://github.com/kverchi/travel-diary-server.git)
2. Prepeare you local database:
     * Clone or download application database backup file form here https://github.com/kverchi/diary-db-backup.git
     * Create the database with name *diary*
     * Enable *pgcrypto* extension: `CREATE EXTENSION pgcrypto;`
     * Store encrypt.key value in postgresql.conf configuration file (you should be provided with this encrypt key)
     * Restore postgresql backup file *diary-v2.0.tar* on your local Postgresql server for newly created database *diary*. [How to restore backup](https://www.postgresql.org/docs/9.6/backup-dump.html#BACKUP-DUMP-RESTORE) 
     * Set Jasypt enctiption key as local environment variable DIARY_PASS_VAR
     * Encrypt JDBC_DATABASE_USERNAME and JDBC_DATABASE_PASSWORD credentials with your environment variable. [How to encrypt with Jasypt](https://apereo.atlassian.net/wiki/spaces/CASUM/pages/103261428/HOWTO+Use+Jasypt+to+encrypt+passwords+in+configuration+files)
     * Set encrypted values into application's */resources/properties/local/app.properties* file 
3. Open project's directory, build and start your project `mvn spring-boot:run`
