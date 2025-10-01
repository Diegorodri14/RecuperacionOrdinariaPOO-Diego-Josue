package RecuperacionOrd_Diego_Josue.Diego_Josue_Recuperacion;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DiegoJosueRecuperacionApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(),entry.getValue()));
		SpringApplication.run(DiegoJosueRecuperacionApplication.class, args);
	}
}
