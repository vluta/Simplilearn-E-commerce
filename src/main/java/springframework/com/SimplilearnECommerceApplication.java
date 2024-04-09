package springframework.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "springframework.com.entity")				// @Entity
@EnableJpaRepositories(basePackages = "springframework.com.repository")	// enable jpa features
public class SimplilearnECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimplilearnECommerceApplication.class, args);
	}

}
