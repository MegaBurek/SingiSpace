package singispace.domain;

import singispace.DTO.RegisteredUserDTO;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Where(clause = "deleted = 'false'")
public class RegisteredUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Boolean deleted = false;

	@Version
	private int version = 0;

	@Column(nullable = false)
	@Size(max = 50)
	private String username;

	@Column(nullable = false)
	@Size(max = 50)
	private String password;

	@Column(nullable = false)
	@Size(max = 50)
	private String email;

	@Column(nullable = false)
	@Size(max = 50)
	private String firstName;

	@Column(nullable = false)
	@Size(max = 50)
	private String lastName;
	
	@Column(nullable = false)
	private Date DoB;

	@Column(nullable = false)
	@Size(max = 50)
	private String role;

	@Column(nullable = false)
	private String biography;
	
	@Column
	private List<RegisteredUserDTO> friends;

	@OneToOne(mappedBy = "registeredUser")
	@JsonIgnore
	private Learner learner;

	@OneToOne(mappedBy = "registeredUser")
	@JsonIgnore
	private Tutor tutor;

	@OneToOne(mappedBy = "registeredUser")
	@JsonIgnore
	private Admin administrator;

	public RegisteredUser() {
	}

	public RegisteredUser(@NotNull Boolean deleted, int version, @Size(max = 50) String username,
			@Size(max = 50) String password, @Size(max = 50) String email, @Size(max = 50) String firstName,
			@Size(max = 50) String lastName, Date DoB, @Size(max = 50) String role, String biography, List<RegisteredUserDTO> friends, Learner learner,
			Tutor tutor, Admin administrator) {
		super();
		this.deleted = deleted;
		this.version = version;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.DoB = DoB;
		this.role = role;
		this.biography = biography;
		this.friends = friends;
		this.learner = learner;
		this.tutor = tutor;
		this.administrator = administrator;
	}
	
	public RegisteredUser(@Size(max = 50) String username,
			@Size(max = 50) String password, @Size(max = 50) String email, @Size(max = 50) String firstName,
			@Size(max = 50) String lastName, Date DoB, @Size(max = 50) String role, String biography) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.DoB = DoB;
		this.role = role;
		this.biography = biography;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDoB() {
		return DoB;
	}

	public void setDoB(Date doB) {
		DoB = doB;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public List<RegisteredUserDTO> getFriends() {
		return friends;
	}

	public void setFriends(List<RegisteredUserDTO> friends) {
		this.friends = friends;
	}

	public Learner getLearner() {
		return learner;
	}

	public void setLearner(Learner learner) {
		this.learner = learner;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public Admin getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Admin administrator) {
		this.administrator = administrator;
	}

}
