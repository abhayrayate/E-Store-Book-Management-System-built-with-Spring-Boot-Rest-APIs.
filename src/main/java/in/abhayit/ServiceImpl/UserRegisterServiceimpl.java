package in.abhayit.ServiceImpl;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.abhayit.Entity.FilesEntity;
import in.abhayit.Entity.UserRegister;
import in.abhayit.Model.LoginRequestDto;
import in.abhayit.Model.UserRequestDto;
import in.abhayit.Repository.FileRepo;
import in.abhayit.Repository.UserRegisterRepo;
import in.abhayit.Service.UserRegisterService;

@Service
public class UserRegisterServiceimpl implements UserRegisterService {

  
	@Autowired
	private UserRegisterRepo userRegisterRepo;

	@Autowired
	private FileRepo fileRepo;

  
	@Override
	public UserRegister insertUserRegister(UserRequestDto userRequestDto) {
		UserRegister user =new UserRegister();
		try {
		user.setFirstname(userRequestDto.getFirstName());
		user.setLastname(userRequestDto.getLastName());
		user.setEmail(userRequestDto.getEmail());
		user.setPassword(Base64.getEncoder().encodeToString(userRequestDto.getPassword().getBytes()));
		user.setContactno(userRequestDto.getContactno());
		userRegisterRepo.save(user);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
//	-------------------------------------------
	public UserRegister checkUserDetails(LoginRequestDto  loginRequestDto )
	{
		try {
		UserRegister user =userRegisterRepo.findByEmail(loginRequestDto.getEmail());
		if(user!=null)
		{
			String password = new String(Base64.getDecoder().decode(user.getPassword()));
			
			if(password.equals(loginRequestDto.getPassword()))
			{
					return user;
			}
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserRegister getUserById(Long id) {
		 try {
	            return userRegisterRepo.findById(id).orElse(null);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	}

	@Override
	public UserRegister uploadMultiUserRegister(UserRequestDto userRequestDto, MultipartFile[] files) {
		
		UserRegister user =new UserRegister();
		try {
			user.setFirstname(userRequestDto.getFirstName());
			user.setLastname(userRequestDto.getLastName());
			user.setEmail(userRequestDto.getEmail());
			user.setPassword(Base64.getEncoder().encodeToString(userRequestDto.getPassword().getBytes()));
			user.setContactno(userRequestDto.getContactno());
			userRegisterRepo.save(user);
			if(files!=null && files.length>0)
			{
				for(MultipartFile multipartfile :files) {
					FilesEntity fss = new FilesEntity();
					fss.setFileName(multipartfile.getOriginalFilename());
					fss.setFileType(multipartfile.getContentType());
					fss.setData(multipartfile.getBytes());
					fileRepo.save(fss);
				}
			}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return user;
	}

}

