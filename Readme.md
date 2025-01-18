# **LiteraAlura**

**LiteraAlura** es una aplicación construida como parte de un desafío del curso de Java y Spring Framework. Está diseñada para interactuar con un catálogo de libros y sus autores, proporcionando funcionalidades de búsqueda y filtrado.

Esta aplicación **se conecta a una API externa** para obtener información sobre libros y autores.

Utiliza la API de [Gutendex](https://gutendex.com/ "API para libros de dominio público") 📜, la cual retorna datos de libros en formato JSON. Estos datos son luego almacenados en una base de datos PostgreSQL para su posterior consulta.

Los resultados obtenidos de las búsquedas se gestionan a través del menú de la aplicación, y se presentan al usuario en la consola.

## 🔧 Tecnologías Empleadas 🔧

- **Java 21**
- **PostgreSQL**
- **Spring Boot**
- **Hibernate**

### 📝 Capturas de Pantalla de la Aplicación

<p>Al ejecutar la aplicación, el menú principal se muestra en la consola, permitiendo al usuario elegir la opción deseada mediante un número.</p>

<img src="./img/menu_inicio.PNG" alt="Imagen del menú principal" style="width: 450px">

<p>La primera opción permite realizar una búsqueda de libros por título en la base de datos de la API de Gutendex. Si el libro se encuentra disponible, se muestran los resultados; en caso contrario, se informa que no se ha encontrado el libro.</p>

<img src="./img/buscar_libro.PNG" alt="Opción de búsqueda de un libro" style="width: 450px">

<p>La segunda opción ofrece una lista de los libros que han sido buscados previamente y almacenados en la base de datos interna de la aplicación.</p>

<img src="./img/ver_libros.PNG" alt="Opción para ver los libros almacenados" style="width: 450px">

<p>La tercera opción permite visualizar todos los autores de los libros almacenados en la base de datos interna de la aplicación.</p>

<img src="./img/explorar_autores.PNG" alt="Opción para ver los autores almacenados" style="width: 450px">

<p>La cuarta opción permite filtrar a los autores que estuvieron vivos después de un año específico, el cual se ingresa durante la segunda etapa de la consulta.</p>

<img src="./img/ano_autores.PNG" alt="Consulta de autores vivos por año" style="width: 450px">

<p>La última opción permite realizar una búsqueda de libros según el idioma, eligiendo entre Inglés y Español, mediante un submenú adicional.</p>

<img src="./img/idiomas_libros.PNG" alt="Consulta de libros por idioma" style="width: 450px">

<p>Finalmente, para cerrar la aplicación, se debe seleccionar la opción 0, lo que muestra un mensaje de despedida.</p>

<img src="./img/salir.PNG" alt="Opción para salir del menú" style="width: 550px">

## Desarrollado por Ian Rodriguez

<div style="display: flex; justify-content: flex-start;">
<a href="https://www.linkedin.com/in/ian-rodriguez-8351a1221" target="_blank">
<img src="./img/linkedIn_icon.png" alt=linkedin style="margin-bottom: 5px;width:45px;" />
</a>
<a href="https://github.com/ianrodriguezy" target="_blank">
<img src="./img/github.svg" alt=github style="margin-bottom: 5px;width:46px;" />
</a>
</div>