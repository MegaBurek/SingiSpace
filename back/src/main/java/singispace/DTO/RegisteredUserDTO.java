package singispace.DTO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import japa.parser.ParseException;

public class RegisteredUserDTO {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	public RegisteredUserDTO() {

	}

	private String firstName;

	private String lastName;

	private String DoB;

	private String email;

	private String role;

	private String biography;

	public RegisteredUserDTO(String firstName, String lastName, String email, String role, String biography) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.biography = biography;
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

	public String getDoB() {
		return DoB;
	}

	public void setDoB(String doB) {
		DoB = doB;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Date getSubmissionDateConverted(String timezone) throws ParseException, java.text.ParseException {
		dateFormat.setTimeZone(TimeZone.getTimeZone(checkTimeZone()));// DTO to entity conversion
		return dateFormat.parse(this.DoB);
	}

	public void setSubmissionDate(Date date) {// Entity to DTO conversion
		dateFormat.setTimeZone(TimeZone.getTimeZone(checkTimeZone()));
		this.DoB = dateFormat.format(date);
	}

	public String checkTimeZone() {
		Calendar cal = Calendar.getInstance();
		long milliDiff = cal.get(Calendar.ZONE_OFFSET);
		String[] ids = TimeZone.getAvailableIDs();
		String tz_Name = null;
		for (String id : ids) {
			TimeZone tz = TimeZone.getTimeZone(id);
			if (tz.getRawOffset() == milliDiff) {
				tz_Name = id;
				break;
			}
		}
		return tz_Name;
	}

}
