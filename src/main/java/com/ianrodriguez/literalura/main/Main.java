package com.ianrodriguez.literalura.main;

import com.ianrodriguez.literalura.models.Author;
import com.ianrodriguez.literalura.models.DatosLibro;
import com.ianrodriguez.literalura.models.Libro;
import com.ianrodriguez.literalura.repository.AuthorRepository;
import com.ianrodriguez.literalura.repository.LibroRepository;
import com.ianrodriguez.literalura.services.ConvierteDatos;
import com.ianrodriguez.literalura.services.RequestAPI;

import java.util.List;
import java.util.Scanner;

public class Main {
    private RequestAPI requestAPI = new RequestAPI();
    private Scanner scanner = new Scanner(System.in);
    private String urlBase ="https://gutendex.com/books/";
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private LibroRepository libroRepository;
    private AuthorRepository authorRepository;
    private List<Libro> libros;
    private List<Author> autores;

    public Main(LibroRepository libroRepository, AuthorRepository authorRepository) {
        this.libroRepository = libroRepository;
        this.authorRepository = authorRepository;
    }

    // Mostrar menu
    public void showMenu()
    {
        var opcion = -1;
        while (opcion != 0){
            var menu = """
                    ********************************************************
                    *                Bienvenido a LiterAlura               *
                    *    Explora libros, autores y más a tu alcance        *
                    ********************************************************
                            
                    Por favor, elige una opción del menú: 
                    --------------------------------------------------------
                    1 - Buscar un libro 📚
                    2 - Ver libros buscados 🔍
                    3 - Explorar autores ✍️
                    4 - Autores de un año específico 📅
                    5 - Filtrar libros por idioma 🌍
                            
                    0 - Salir 🚪
                    --------------------------------------------------------
                    Tu opción: 
                    """;

            try {
                System.out.println(menu);
                opcion = scanner.nextInt();
                scanner.nextLine();
            }catch (Exception e){
                scanner.nextLine();
            }

            switch (opcion){
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    consultarLibros();
                    break;
                case 3:
                    consultarAutores();
                    break;
                case 4:
                    consultarAutoresPorAno();
                    break;
                case 5:
                    consultarLibrosLenguaje();
                    break;
                case 0:
                    System.out.println("¡Gracias por usar LiterAlura! 😊 ¡Esperamos verte pronto para descubrir más libros y autores!");
                    break;
                default:
                    System.out.println("😅 Parece que no entendí tu elección. Por favor, ingresa una opción válida del menú.");
                    break;
            }
        }
    }

    // Trae datos de libro
    private DatosLibro getDatosLibro() {
        System.out.println("📚 Por favor, ingresa el título del libro que deseas buscar:");
        var busqueda = scanner.nextLine().toLowerCase().replace(" ","%20");
        var json = requestAPI.getData(urlBase +
                "?search=" +
                busqueda);

        DatosLibro datosLibro = convierteDatos.obtenerDatos(json, DatosLibro.class);
        return datosLibro;
    }

    // Busca un libro y lo guarda en la base de datos
    private void buscarLibro()
    {
        DatosLibro datosLibro = getDatosLibro();

        try {
            Libro libro = new Libro(datosLibro.resultados().get(0));
            Author author = new Author(datosLibro.resultados().get(0).autorList().get(0));

            System.out.println("""
                                    
                    📖 Resultado del libro buscado
                    --------------------------------
                    Título: %s
                    Autor: %s
                    Idioma: %s
                    Descargas: %s
                    --------------------------------
                    """.formatted(libro.getTitulo(),
                    libro.getAutor(),
                    libro.getLenguaje(),
                    libro.getDescargas().toString()));

            libroRepository.save(libro);
            authorRepository.save(author);

        }catch (Exception e){
            System.out.println("""
                                    
                    ❌ No se encontró el libro ingresado
                    ------------------------------------
                    Por favor, verifica el título e inténtalo nuevamente.
                    """);
        }

    }

    // Trae todos los libros de la base de datos
    private void consultarLibros() {
        libros = libroRepository.findAll();
        libros.stream().forEach(l -> {
            System.out.println("""
                    📚 Información del Libro
                    ----------------------------------------
                    Título: %s
                    Autor: %s
                    Idioma: %s
                    Descargas: %s
                    ----------------------------------------
                    """.formatted(l.getTitulo(),
                    l.getAutor(),
                    l.getLenguaje(),
                    l.getDescargas().toString()));
        });
    }

    // Trae todos los autores de los libros consultados en la base de datos
    private void consultarAutores() {
        autores = authorRepository.findAll();
        autores.stream().forEach(a -> {
            System.out.println("""
                    ✍️ Información del Autor
                    ----------------------------------------
                    Nombre: %s
                    Año de Nacimiento: %s
                    Año de Fallecimiento: %s
                    ----------------------------------------
                    """.formatted(
                    a.getAutor(),
                    a.getNacimiento() != null ? a.getNacimiento().toString() : "Desconocido",
                    a.getDefuncion() != null ? a.getDefuncion().toString() : "Aún vivo"));
        });
    }

    // Trae a los autores apartir del año qie se indique
    public void consultarAutoresPorAno()
    {
        System.out.println("🔎 Ingresa el año a partir del cual deseas buscar autores:");
        var anoBusqueda = scanner.nextInt();
        scanner.nextLine();

        List<Author> authors = authorRepository.autorPorFecha(anoBusqueda);
        authors.forEach( a -> {
            System.out.println("""
                    📅 Autor relacionado con el año ingresado
                    ----------------------------------------
                    Nombre: %s
                    Fecha de Nacimiento: %s
                    Fecha de Fallecimiento: %s
                    ----------------------------------------
                    """.formatted(
                    a.getAutor(),
                    a.getNacimiento() != null ? a.getNacimiento().toString() : "Desconocido",
                    a.getDefuncion() != null ? a.getDefuncion().toString() : "Aún vivo"));
        });
    }


    private void consultarLibrosLenguaje()
    {
        System.out.println("""
                🌐 Selecciona el idioma de los libros que deseas consultar:
                ----------------------------------------
                1 - Inglés  (EN)
                2 - Español  (ES)
                ----------------------------------------
                """);

        try {

            var opcion2 = scanner.nextInt();
            scanner.nextLine();

            switch (opcion2) {
                case 1:
                    libros = libroRepository.findByLenguaje("en");
                    break;
                case 2:
                    libros = libroRepository.findByLenguaje("es");
                    break;

                default:
                    System.out.println("❌ Opción inválida. Por favor selecciona 1 o 2.");
                    return;// Salir del método si la opción no es válida
            }

            libros.stream().forEach(l -> {
                System.out.println("""
                        📖 Libros en el idioma seleccionado
                        ----------------------------------------
                        Título: %s
                        Autor: %s
                        Idioma: %s
                        Descargas: %s
                        ----------------------------------------
                        """.formatted(l.getTitulo(),
                        l.getAutor(),
                        l.getLenguaje(),
                        l.getDescargas().toString()));
            });

        } catch (Exception e){
            System.out.println("❌ Entrada inválida. Por favor, ingresa un número válido.");
            scanner.nextLine();
        }
    }

    public static class Principal {
        private RequestAPI requestAPI = new RequestAPI();
        private Scanner scanner = new Scanner(System.in);
        private String urlBase ="https://gutendex.com/books/";
        private ConvierteDatos convierteDatos = new ConvierteDatos();
        private LibroRepository libroRepository;
        private AuthorRepository authorRepository;
        private List<Libro> libros;
        private List<Author> autores;

        public Principal(LibroRepository libroRepository, AuthorRepository authorRepository) {
            this.libroRepository = libroRepository;
            this.authorRepository = authorRepository;
        }
    }
}
