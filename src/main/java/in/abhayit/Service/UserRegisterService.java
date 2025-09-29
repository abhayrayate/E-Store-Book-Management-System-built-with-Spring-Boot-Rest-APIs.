package in.abhayit.Service;

import in.abhayit.Entity.UserRegister;
import in.abhayit.Model.LoginRequestDto;
import in.abhayit.Model.UserRequestDto;

public interface UserRegisterService {
    
	// Register a new user
	public UserRegister  insertUserRegister(UserRequestDto userRequestDto);//abstract meth
   

    // Validate login (email + password)
    public  UserRegister checkUserDetails(LoginRequestDto loginRequestDto);
    
    
    public  UserRegister getUserById(Long id);


}
