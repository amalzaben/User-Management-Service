package com.discuessit.userManagemnet;

import com.discuessit.userManagemnet.model.User;
import com.discuessit.userManagemnet.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserManagemnetApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagemnetApplication.class, args);
	}

}
