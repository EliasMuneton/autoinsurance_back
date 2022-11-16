package hexaware.sc.autoinsurance;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;;

@SpringBootApplication
@EnableAsync
@OpenAPIDefinition(info = @Info(title = "Autoinsiurance API", version = "1.0", description = "Autoinsurance Information"))
@SecurityScheme(name = "token", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class AutoinsuranceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoinsuranceApplication.class, args);
	}

	
}
