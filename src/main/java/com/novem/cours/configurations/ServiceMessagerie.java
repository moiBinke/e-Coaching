package com.novem.cours.configurations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.novem.cours.controllers.CourierElectronique;

@RestController
@CrossOrigin("*")
public class ServiceMessagerie {
	
	@PostMapping("/envoieMail")
	public void postController(
	  @RequestBody CourierElectronique courier) {
		courier.envoyer();
	}
}
