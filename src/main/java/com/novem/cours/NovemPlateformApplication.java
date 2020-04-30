package com.novem.cours;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.novem.cours.dao.ProfesseurDao;
import com.novem.cours.entities.Professeur;
import com.novem.cours.entities.Role;
import com.novem.cours.validators.PasswordEncryption;

import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
public class NovemPlateformApplication implements CommandLineRunner{

	@Autowired private ProfesseurDao professeurDao;
	public static void main(String[] args) {
		SpringApplication.run(NovemPlateformApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Professeur admin = new Professeur("COULIBALY", "Issa", "icoulibaly@excilys.com", PasswordEncryption.encrypt("KaIsAdAmNovem"), Role.ROLE_ADMIN, true); 
		this.professeurDao.save(admin);
	}

}
