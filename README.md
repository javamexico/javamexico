Instrucciones de instalación
==================

También puedes ver los siguientes artículos:

Entendiendo el proyecto javaMexico 2.0 (un punto de partida): http://bit.ly/hXKVe2

Instalando el Proyecto javaMexico 2.0 for Dummies: http://bit.ly/fp3eXk

Actualmente se puede compilar y correr la aplicación con Eclipse, usando el plugin M2Eclipse (para Maven 2).

También se puede construir el proyecto usando Gradle desde la línea de comandos.

Una vez que bajen su copia de trabajo del repositorio, lo primero que hay que hacer es crear su base de datos. Pueden crearla con PostgreSQL, creando una base de datos "javamexico" cuyo dueño sea el usuario "javamex" (o pueden usar otros nombres, solamente necesitarán editar los datos del DataSource? más adelante). Hay un script para crear las tablas. No está completo así que puede dar errores en varias tablas, pero los pueden ignorar por el momento.

Si crean su base de datos usando otro DBMS y tienen que modificar el script, por favor hagan una copia y la suben al repositorio o la mandan a alguno de los committers para tener el script que corra en una base de datos distinta.

En Eclipse, con la opción de importar proyectos existentes, solamente necesitan apuntar al directorio javamexico/java y seleccionar los proyectos javamex-core, javamex-hibernate3 y JavaMexico.

Una vez que compilen completos los 3 proyectos:

Primero es recomendable que ejecuten las pruebas unitarias en javamex-hibernate3, dentro de src/test/java hay algunas clases de prueba de los DAOs, ejecútenlas para ver que se generen datos de prueba.

Finalmente, para correr el proyecto de JavaMexico lo seleccionan y la opción de "Run as -> Maven build..."; en el diálogo ponen "jetty:run" en el campo de Goals y listo, con eso corre.

Importante: Si usaron algo que no sea PostgreSQL o le cambiaron el nombre a la base de datos o al usuario para entrar a ella, hay que editar los archivos postgres.xml en src/test/java de javamex-hibernate3 y src/main/webapp/WEB-INF de JavaMexico.
