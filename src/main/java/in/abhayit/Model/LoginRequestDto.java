package in.abhayit.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

	  @NotBlank(message = "email cannot be blank")
	    @Schema(description = "email", example = "Enter Email")
	    private String email;

	    @NotBlank(message = "password cannot be blank")
	    @Schema(description = "password", example = "Enter Password")
	    private String password;
}
