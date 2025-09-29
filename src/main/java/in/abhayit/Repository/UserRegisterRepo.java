package in.abhayit.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import in.abhayit.Entity.UserRegister;

public interface UserRegisterRepo extends JpaRepository<UserRegister, Long>{

//	----------------------------
	UserRegister findByEmail(String email);

	

	
}
