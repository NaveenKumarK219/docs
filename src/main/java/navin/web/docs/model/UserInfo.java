package navin.web.docs.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="users")
public class UserInfo implements  Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7411562968987798693L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;
	
	@Column(name = "email")
	@javax.validation.constraints.Email(message="Please provide a valid Email Id!!")
	@javax.validation.constraints.NotEmpty(message = "Please enter email id")
	private String email;
	
	@Column(name = "password")
	@Length(min = 5, message = "Password must contain minimum of 5 characters!!")
	@javax.validation.constraints.NotEmpty(message = "Please enter password!!!")
	// @Transient
	private String password;
	
	@Column(name = "name")
	@javax.validation.constraints.NotEmpty(message = "Please provide name")
	private String name;
	
	@Column(name = "last_name")
	@javax.validation.constraints.NotEmpty(message = "Please provide last name")
	private String lastName;
	
	@Column(name = "active")
	private boolean active;
	
	@Column(name = "role")
	@javax.validation.constraints.NotEmpty(message = "Please Select a role")
	private String role;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean getActive() {
		return active;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}
}
