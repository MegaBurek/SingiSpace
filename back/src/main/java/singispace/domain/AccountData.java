package singispace.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

import singispace.utils.View.ShowUserPermission;
import singispace.utils.View.ShowVerificationToken;

@Entity
public class AccountData {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(unique = true, length = 55)
	private String username;
	
	@NotNull
	@Column(unique = true, length = 55)
	private String email;
	
	@NotNull
	private String password;
	
	private boolean isEnabled = false;
	
	@JsonView(ShowUserPermission.class)
	@OneToMany(mappedBy = "accountData", cascade = CascadeType.ALL)
	private Set<UserPermission> userPermission;
	
	@JsonView(ShowVerificationToken.class)
	@OneToOne(mappedBy = "accountData", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private VerificationToken verificationToken;
	
	public AccountData() {
		
	}
	

	public AccountData(@NotNull String username, @NotNull String email, @NotNull String password, boolean isEnabled) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.isEnabled = isEnabled;
	}

	public AccountData(Long id, String username, String password, String email, boolean isEnabled, Set<UserPermission> userPermission) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.isEnabled = isEnabled;
		this.userPermission = userPermission;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Set<UserPermission> getUserPermission() {
		return userPermission;
	}

	public void setUserPermission(Set<UserPermission> userPermission) {
		this.userPermission = userPermission;
	}


	public VerificationToken getVerificationToken() {
		return verificationToken;
	}


	public void setVerificationToken(VerificationToken verificationToken) {
		this.verificationToken = verificationToken;
	}
	
	
	
}

