package singispace;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import singispace.domain.Role;
import singispace.repositories.RoleRepository;

@SpringBootApplication
public class singispaceApp {

	public static void main(String[] args) {
		SpringApplication.run(singispaceApp.class, args);
	}

	@Bean
	CommandLineRunner init(RoleRepository roleRepository) {

		return args -> {

			Role adminRole = roleRepository.findByRole("ADMIN");
			if (adminRole == null) {
				Role newAdminRole = new Role();
				newAdminRole.setRole("ADMIN");
				roleRepository.save(newAdminRole);
			}

			Role administratorRole = roleRepository.findByRole("ADMINISTRATOR");
			if (administratorRole == null) {
				Role newAdministratorRole = new Role();
				newAdministratorRole.setRole("ADMINISTRATOR");
				roleRepository.save(newAdministratorRole);
			}

			Role userRole = roleRepository.findByRole("USER");
			if (userRole == null) {
				Role newUserRole = new Role();
				newUserRole.setRole("USER");
				roleRepository.save(newUserRole);
			}
		};

	}

}

