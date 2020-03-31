package singispace.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import singispace.domain.AccountData;
import singispace.repositories.users.AccountDataRepository;
import singispace.repositories.VerificationTokenRepository;

import java.util.Optional;

@Service
public class AccountDataService {

    @Autowired
    AccountDataRepository accountDataRepository;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Iterable<AccountData> getAllAccoutDatas() {
        return accountDataRepository.findAll();
    }

    public Optional<AccountData> getAccountByUsername(String username){
        return accountDataRepository.findByUsername(username);
    }

    public Optional<AccountData> getAccountDataById(String id) {
        return accountDataRepository.findById(id);
    }
//
//	public Optional<AccountData> getAccountDataUsername(String username) {
//        return accountDataRepository.findByUsername(username);
//    }

//	public Iterable<Optional<AccountData>> getAllUsernames() {
//		return accountDataRepository.getAllUsernames();
//	}

    public void addAccountData(AccountData accountData) {
        accountDataRepository.save(accountData);

//		VerificationToken verificationToken = new VerificationToken(accountData);
//
//		verificationTokenRepository.save(verificationToken);
//
//		SimpleMailMessage mailMessage = new SimpleMailMessage();
//		mailMessage.setTo(accountData.getEmail());
//		mailMessage.setSubject("Verify your account!");
//		mailMessage.setFrom("mudrinskinemajna@gmail.com");
//		mailMessage.setText("To confirm your account, please click here : "
////		+"http://localhost:4200/confirm-registration?token=/"+verificationToken.getToken());
//		+"http://localhost:4200/confirm-registration/"+verificationToken.getToken());
////		+"http://localhost:8080/account-data/confirm-account?token="+verificationToken.getToken());
//
//		emailSenderService.sendEmail(mailMessage);


    }

//	 public void confirmVerification(String verificationToken) {
//	    	VerificationToken token = verificationTokenRepository.findByToken(verificationToken);
//
//			if(token != null)
//			{
//				Optional<AccountData> accountData = accountDataRepository.findByEmail(token.getAccountData().getEmail());
//				accountData.get().setEnabled(true);
//				accountDataRepository.save(accountData.get());
//				System.out.println("Done!");
//
//			}
//			else
//			{
//				System.out.println("Somethnig's wrong");
//			}
//
//
//	}

//    public void forgotUserPassword(AccountData accountData) {
//
//        AccountData existingUser = accountDataRepository.getByEmail(accountData.getEmail());
//        if (existingUser != null) {
//            // Create token
//            ResetPasswordToken resetPasswordToken = new ResetPasswordToken(existingUser);
//
//            // Save it
//            resetPasswordTokenRepository.save(resetPasswordToken);
//
//            // Create the email
//            SimpleMailMessage mailMessage = new SimpleMailMessage();
//            mailMessage.setTo(existingUser.getEmail());
//            mailMessage.setSubject("Complete Password Reset!");
//            mailMessage.setFrom("test-email@gmail.com");
//            mailMessage.setText("To complete the password reset process, please click here: "
////	              + "http://localhost:8080/account-data/confirm-reset?token="+resetPasswordToken.getToken());
//                    +"http://localhost:4200/confirm-reset/"+resetPasswordToken.getToken());
//            // Send the email
//            emailSenderService.sendEmail(mailMessage);
//            System.out.println("DOne!");
//
//        } else {
//            System.out.println("Something is wrong");
//        }
//
//    }

//	public void validateResetToken(String confirmationToken) {
//	        ResetPasswordToken token = resetPasswordTokenRepository.findByToken(confirmationToken);
//
//
//	        if(token != null)
//			{
//				Optional<AccountData> accountData = accountDataRepository.findByEmail(token.getAccountData().getEmail());
//				accountData.get().setEnabled(true);
//				accountDataRepository.save(accountData.get());
//				System.out.println("validate!");
//
//			} else {
//				System.out.println("Something's wrong!");
//			}
//
//	    }

    // Endpoint to update a user's password
//    public void resetAccountDataPassword(AccountData accountData) {
//        if (accountData.getEmail() != null) {
//            // Use email to find user
//            AccountData tokenUser = accountDataRepository.findByEmail(accountData.getEmail());
//            tokenUser.setPassword(passwordEncoder.encode(accountData.getPassword()));
//            accountDataRepository.save(tokenUser);
//            System.out.println("reset");
//
//        } else {
//
//        }
//
//    }

    public void removeAccountData(String id) {
        Optional<AccountData> accountData = accountDataRepository.findById(id);
        accountDataRepository.delete(accountData.get());
    }

    public void updateAccountData(String id, AccountData accountData) {
        Optional<AccountData> Acc = accountDataRepository.findById(id);
        if(Acc.isPresent()) {
            accountData.setId(Acc.get().getId());
            accountDataRepository.save(accountData);
        }
    }

//    public Optional<AccountData> getToken(String username) {
//    	return accountDataRepository.getToken(username);
//    }
}

