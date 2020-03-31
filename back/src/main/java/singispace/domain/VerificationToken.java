package singispace.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Document
public class VerificationToken {

    private static final int EXPIRATION = 60 * 24;


    @Id
    private Long id;

    private String token;

    private Date createdDate;

    private Date expiryDate;
    @DBRef
    private AccountData accountData;

    public VerificationToken(){}

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
