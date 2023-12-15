# README.md para el Proyecto Terrenia

## Descripción

Terrenia es un proyecto que utiliza una base de datos SQL Server Express y se gestiona a través de Gradle para la compilación y manejo de dependencias. Este archivo README proporciona las instrucciones necesarias para configurar y ejecutar el proyecto.

## Requisitos Previos

- SQL Server Express instalado en su máquina.
- SQL Server Management Studio (SSMS) para ejecutar scripts SQL.
- Gradle para la compilación y ejecución del proyecto.
- Java Development Kit (JDK) adecuado para Gradle.

## Configuración de la Base de Datos

1. **Instalación de SQL Server Express**: Asegúrese de que SQL Server Express esté instalado en su sistema.
2. **Ejecución del Script de Base de Datos**:
   - Localice el script `QueryGeneracionBBDD.sql` incluido en la carpeta raíz del proyecto.
   - Abra SQL Server Management Studio (SSMS).
   - Ejecute el script `QueryGeneracionBBDD.sql` en SSMS para crear la base de datos y las tablas necesarias.

## Configuración del Proyecto

1. **Clonación del Repositorio**: Clone el repositorio de Terrenia en su máquina local.
2. **Instalación de Gradle**: Si aún no tiene Gradle instalado, siga las instrucciones en [Gradle Installation Guide](https://gradle.org/install/).

## Ejecución del Proyecto

1. **Abrir la Terminal**:
   - Navegue hasta la carpeta raíz del proyecto Terrenia en su terminal.
2. **Compilación del Proyecto**:
   - Ejecute el comando: `./gradlew clean build`
   - Esto limpiará cualquier compilación anterior y construirá el proyecto.
3. **Ejecución de la Aplicación**:
   - Ejecute el comando: `./gradlew run`
   - Esto iniciará la aplicación.

## Configuración de la Conexión a la Base de Datos

Antes de proceder con la conexión a la base de datos, es crucial asegurarse de que la URL de conexión en su archivo de configuración refleje correctamente su entorno local. Esta URL se puede encontrar en el archivo **DatabaseConnector.java** dentro del paquete terrenia.util. La URL de conexión proporcionada es un ejemplo y debe adaptarse según la configuración de su sistema.

### Adaptando la URL de Conexión

La URL de conexión típica para SQL Server Express se ve así:
```
jdbc:sqlserver://[NombreDelServidor]\\SQLEXPRESS:[Puerto];databaseName=terrenia;encrypt=false;
```

- **[NombreDelServidor]**: Es el nombre de su servidor SQL Server. Puede ser el nombre de su computadora o una dirección IP local.
- **[Puerto]**: Es el puerto en el que SQL Server está escuchando. Por defecto, SQL Server Express utiliza el puerto 1433, pero puede ser diferente en su instalación.

### Cómo Obtener Esta Información

1. **Nombre del Servidor**:
   - En Windows, puede encontrar el nombre de su máquina ejecutando `hostname` en el símbolo del sistema (CMD).
   - Alternativamente, En SSMS, al realizar una nueva conexión (botón del enchufe en el Object Explorer), verá en nombre del servidor por defecto de su computadora.
   - Alternativamente, también puede usar `localhost` si el servidor SQL se ejecuta en la misma máquina que el cliente.

2. **Puerto de SQL Server**:
   - El puerto puede encontrarse en la configuración del SQL Server Configuration Manager bajo SQL Server Network Configuration -> Protocols for SQLEXPRESS -> TCP/IP.
   - Lo encontrará en la pestaña "Direcciones IP" abajo del todo. Puede ser un "Puerto TCP" o un "Puerto Dinamico TCP"
   - Asegúrese de que TCP/IP esté habilitado y revise las propiedades para encontrar el número de puerto utilizado.

### Ejemplo de URL de Conexión Configurada

Supongamos que su nombre de servidor es `MiPC` y el puerto es `1433`, la URL de conexión sería:
```
jdbc:sqlserver://MiPC\\SQLEXPRESS:1433;databaseName=terrenia;encrypt=false;
```

### Datos de Autenticación

- **Usuario**: root
- **Contraseña**: 1234

Asegúrese de que estos detalles coincidan con la configuración de autenticación de su SQL Server Express.

Deberá crear un usuario con esos datos si no existe.

## Configuración envio de recibos por correo electronico

En la clase ReciboService.java, dentro del paquete service, en el metodo enviarRecibo(Recibo recibo), se pueden encontrar los campos para añadir el correo electronico desde el que realizar envios y la contraseña para dicho correo electronico. En el caso de utilizar Google como servicio gestor de correo electrónico, se debe de utilizar una contraseña de aplicación.

### Nota Final

Es importante recordar que estos pasos pueden variar ligeramente dependiendo de la configuración específica de su sistema y la versión de SQL Server Express. Si encuentra problemas, contacte con el desarrollador del proyecto y le guiará en el proceso.

## Adicionales

- **Documentación**: Revise la documentación del código para una mejor comprensión de los componentes del proyecto.