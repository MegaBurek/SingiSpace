package singispace.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class VerificationToken {
	
	private static final int EXPIRATION = 60 * 24;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String token;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiryDate;
	
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(foreignKey = @ForeignKey(name="FK_VERIFY_USER"))
	private AccountData accountData;
	
	public VerificationToken() {
		
	}
	
	public VerificationToken(String token, Date createdDate, AccountData accountData, Date expiryDate) {
		super();
		this.token = token;
		this.createdDate = createdDate;
		this.accountData = accountData;
		this.expiryDate = calculateExpiryDate(EXPIRATION);
	}
	
	public VerificationToken(AccountData accountData) {
		this.accountData = accountData;
		createdDate = new Date();
		expiryDate = new Date();
		token = UUID.randomUUID().toString();
	}

	private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public AccountData getAccountData() {
		return accountData;
	}

	public void setAccountData(AccountData accountData) {
		this.accountData = accountData;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	
}

