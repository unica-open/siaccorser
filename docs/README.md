# Configurations
All configuration MUST be set in the `buildfiles/<env>.properties` file used for compilation
- current.env = the currently executing environment
- nome.ambiente = the name of the environment
- datasource.jndi-url = no more used. May be left to blank or to a default value
- messageSources.cacheSeconds = no more used. May be left to -1
- flag-debug = the Java compiler flag to activate debug symbols (on/off)
- flag-optimize = the Java compiler flag to activate compile-time optimizations (on/off)
- flag-compress = the Java packager flag to activate packaging compressions for
    WAR/EAR/JAR files (on/off)
- persistence.unit.showSql = Specifies whether the JPA-generated SQL should be logged
- persistence.unit.formatSql = Specifies whether the JPA-generated SQL should be formatted
- persistence.unit.use_get_generated_keys = Tells the JPA provider to retrieve the
    generated keys
- persistence.unit.use_jdbc_metadata_defaults = Tells the JPA provider not to connect to
    the database to retrieve metadata informations
