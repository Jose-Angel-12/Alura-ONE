package com.gelse.casadellibro;

import com.gelse.casadellibro.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CasadellibroApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CasadellibroApplication.class, args);
	}
	//comentario
	/// ootro comentario
	/// /sadgag
	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.muestraElMenu();
	}
}
