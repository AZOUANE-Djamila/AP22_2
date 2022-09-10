package MASTER_SISPRING_AP22;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Ap222Application {

	
	@GetMapping("/admin")
	public String index() {  
		return "Console d’administration"; 
		}
	
	@GetMapping("/portail")
	public String index2(){ 
		return "Portail utilisateur";  
		}

	public static void main(String[] args) {
		SpringApplication.run(Ap222Application.class, args);
	}

}
