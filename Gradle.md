Ahora el proyecto completo de JavaMexico 2.0 se puede construir con [Gradle](http://gradle.org/). Simplemente hay que tener Gradle instalado y desde el directorio 'java' teclear `gradle` para que se compile todo y se ejecuten las pruebas (unitarias/integración).

Para ejecutar la aplicación, hay que entrar al directorio JavaMexico y teclear `gradle jettyRun` para iniciar una instancia de Jetty que ejecute el WAR de la aplicación. Para esto ya se requiere tener la base de datos en PostgreSQL (o en otro DBMS si se modifican las dependencias del build.gradle y el dataSource definido en el application context de la app).

### Eclipse ###

Si quieren usar Eclipse para trabajar en los proyectos, es recomendable que entren a cada uno de los proyectos y tecleen `gradle eclipse` para que se actualicen los archivos .project y .classpath, con las referencias a todas las dependencias. Luego de eso ya abren los proyectos en Eclipse o les dan Refresh (F5).