package hexaware.sc.autoinsurance;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AutoinsuranceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoinsuranceApplication.class, args);
	}

	
}
