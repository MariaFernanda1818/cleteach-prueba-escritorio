# Proyecto Prueba Técnica

Este repositorio es parte de una prueba técnica que consta de una aplicación de escritorio, una API y un front desarrollado con Angular. A continuación, se detallan los pasos necesarios para correr correctamente cada uno de estos proyectos.

## Requisitos Previos

Para ejecutar este proyecto, necesitarás:

- **Angular 17**: Para el front-end.
- **JDK 17**: Para la API y la aplicación de escritorio.
- **NetBeans (última versión)**: Para ejecutar la aplicación de escritorio.
- **IntelliJ IDEA**: Para ejecutar la API.
- **Visual Studio Code**: Para ejecutar el front-end.
- **MySQL**: Se provee la conexión mediante Docker Compose.

## Configuración Inicial

1. **Configurar MySQL con Docker Compose**  
   Se entrega un archivo `docker-compose.yml` para iniciar la base de datos MySQL.  
   Ejecuta el siguiente comando en el directorio donde se encuentra el archivo:

   ```bash
   docker-compose up -d
   ```

   Esto iniciará la base de datos necesaria para el proyecto, cabe aclarar que este docker-compose esta en cltech-api.

---

## Pasos para Ejecutar los Proyectos

### 1. **API (Backend)**

- Clona el repositorio de la API desde la siguiente URL:  
  [https://github.com/MariaFernanda1818/cltech-api.git](https://github.com/MariaFernanda1818/cltech-api.git)

- Abre el proyecto en **IntelliJ IDEA**.

- Asegúrate de que en el archivo `application.properties` de la API esté configurada la opción para generar las tablas automáticamente con Hibernate. Esta es la línea clave que debes verificar:
  ```
  spring.jpa.hibernate.ddl-auto=none
  ```

- Ejecuta el proyecto. Deja la API corriendo, ya que es fundamental para que los demás repositorios funcionen correctamente.

---

### 2. **Aplicación de Escritorio**

- Clona el repositorio de la aplicación de escritorio desde la siguiente URL:  
  [https://github.com/MariaFernanda1818/cltech-prueba-escritorio.git](https://github.com/MariaFernanda1818/cltech-prueba-escritorio.git)

- Abre el proyecto en **NetBeans (última versión)**.

- Una vez abierto, haz clic derecho sobre el nombre del proyecto y selecciona la opción **Clean and Build**. Esto compilará las dependencias definidas en el archivo `pom.xml`.

- Después de compilar, ejecuta la clase `main` para iniciar la aplicación de escritorio.

---

### 3. **Aplicación Front-End (Angular)**

- Clona el repositorio del front-end desde la siguiente URL:  
  [https://github.com/MariaFernanda1818/cltech-prueba-front.git](https://github.com/MariaFernanda1818/cltech-prueba-front.git)

- Abre el proyecto en **Visual Studio Code**.

- Ejecuta el siguiente comando para instalar las dependencias necesarias:
  ```bash
  npm install
  ```

- Luego, enciende la aplicación con:
  ```bash
  npm run start:dev
  ```

  Esto iniciará la aplicación Angular, que estará lista para interactuar con la API.

---

## Notas Adicionales

- **Conexión a la Base de Datos**:  
  La configuración de conexión a MySQL ya está incluida en el archivo `docker-compose.yml`. Al iniciar los servicios, la API detectará la base de datos automáticamente.

- **Orden de Ejecución**:  
  Es importante seguir este orden:
    1. Configurar y correr la base de datos con Docker Compose.
    2. Encender la API.
    3. Correr la aplicación de escritorio.
    4. Correr la aplicación Angular.

- **Problemas Comunes**:
    - Asegúrate de tener configuradas correctamente las variables de entorno necesarias para cada herramienta (JDK, Angular CLI, etc.).
    - Revisa que los puertos necesarios no estén ocupados (por ejemplo, el puerto de la API y el puerto del front).

---

¡Gracias por usar este proyecto! 😊