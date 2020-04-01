package singispace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import singispace.config.AppProperties;


@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class singispaceApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(singispaceApp.class, args);

	}


//	@Bean
//	CommandLineRunner init(RoleRepository roleRepository) {
//
//		return args -> {
//
//			Role adminRole = roleRepository.findByRole("ADMIN");
//			if (adminRole == null) {
//				Role newAdminRole = new Role();
//				newAdminRole.setRole("ADMIN");
//				roleRepository.save(newAdminRole);
//			}
//
//			Role administratorRole = roleRepository.findByRole("ADMINISTRATOR");
//			if (administratorRole == null) {
//				Role newAdministratorRole = new Role();
//				newAdministratorRole.setRole("ADMINISTRATOR");
//				roleRepository.save(newAdministratorRole);
//			}
//
//			Role userRole = roleRepository.findByRole("USER");
//			if (userRole == null) {
//				Role newUserRole = new Role();
//				newUserRole.setRole("USER");
//				roleRepository.save(newUserRole);
//			}
//		};
//
//	}

}

