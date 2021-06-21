package in.ponram.model;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Table(value="user_details")
public class User {

	@Id
	private int userId;
	
	@NotEmpty(message = "Name cannot be empty")
	@Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
	@Column("user_name")
	private String username;

	@Column("mobile_number")
	private long mobileNumber;
	
	@NotNull
	@NotEmpty(message = "Gender cannot be empty")
	private String gender;
	
	@NotNull
	@NotEmpty(message = "Address cannot be empty")
	private String address;
	
	@NotNull
	@NotEmpty(message = "Email cannot be empty")
	@Email(message = "Email should be valid")
	private String email;
	
	@Column("admin_user")
	private boolean admin;
	
	@NotNull
	@JsonProperty(access = Access.WRITE_ONLY)
	@Size(min = 8, max = 20, message = "Password must be greater than 8 and less than 20 characters")
	@Column("user_password")
	private String password;


}
