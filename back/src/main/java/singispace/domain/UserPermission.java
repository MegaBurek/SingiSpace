package singispace.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserPermission {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private AccountData accountData;

	@ManyToOne()
	private Permission permission;
	
	public UserPermission() {
		
	}
	
	public UserPermission(AccountData accountData, Permission permission) {
		this.accountData = accountData;
		this.permission = permission;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public AccountData getAccountData() {
		return accountData;
	}

	public void setAccountData(AccountData accountData) {
		this.accountData = accountData;
	}

}

