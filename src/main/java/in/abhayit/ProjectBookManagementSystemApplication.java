package in.abhayit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
@OpenAPIDefinition(
        info = @Info(
        title = "Ecommerc Book Store Management",
        version = "1.0",
        description = "Welcome to the Abhay i Techlogies",
        contact = @Contact(name ="Abhay i Technology",email = "abhayrayate@gmail.com"))
//        ,servers = {
//    	        @Server(url = "https://localhost:8080", description = "Book Store Server")
//    	    }
		)
@SpringBootApplication
@EnableCaching
public class ProjectBookManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectBookManagementSystemApplication.class, args);
	}

}
