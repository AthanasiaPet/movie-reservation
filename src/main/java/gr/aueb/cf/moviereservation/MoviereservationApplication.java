package gr.aueb.cf.moviereservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MoviereservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviereservationApplication.class, args);
	}

}
