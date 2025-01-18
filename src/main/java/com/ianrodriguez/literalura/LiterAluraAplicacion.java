package com.ianrodriguez.literalura;
import com.ianrodriguez.literalura.main.Main;
import com.ianrodriguez.literalura.repository.AuthorRepository;
import com.ianrodriguez.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LiterAluraAplicacion implements CommandLineRunner
{

	public static void main(String[] args)
	{
		SpringApplication.run(LiterAluraAplicacion.class, args);
		}

	@Autowired
	private LibroRepository repository;
	@Autowired
	private AuthorRepository authorRepository;

	public void run(String[] args) {
		Main principal = new Main(repository,authorRepository);
		principal.showMenu();
	}
}
