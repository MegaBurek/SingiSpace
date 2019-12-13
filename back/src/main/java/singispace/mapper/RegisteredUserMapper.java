package singispace.mapper;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.TimeZone;

import com.ibm.icu.util.Calendar;

import japa.parser.ParseException;
import singispace.DTO.RegisteredUserDTO;
import singispace.domain.RegisteredUser;

public class RegisteredUserMapper implements Mapper<RegisteredUser, RegisteredUserDTO>{
	
	private static final SimpleDateFormat DoBFormat
    = new SimpleDateFormat("dd-MM-yyyy");
	
	private static String tz_Name;
	private String date;

	public RegisteredUserDTO toDTO(RegisteredUser e) {
		if (e == null) {
			return null;
		}
		
		RegisteredUserDTO retVal = new RegisteredUserDTO();
		
		retVal.setFirstName(e.getFirstName());
		retVal.setLastName(e.getLastName());
		retVal.setEmail(e.getEmail());
		retVal.setDoB(e.getDoB());
	}

	public RegisteredUser toEntity(RegisteredUserDTO edto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Collection<RegisteredUserDTO> toDTO(Collection<RegisteredUser> es) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<RegisteredUser> toEntityList(Collection<RegisteredUserDTO> edtos) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Date getDateConvertedEntity() throws ParseException, java.text.ParseException { //DTO to entity date conversion
		tz_Name = checkTimeZone();
		DoBFormat.setTimeZone(TimeZone.getTimeZone(tz_Name));
        return DoBFormat.parse(this.date);
    }
 
    public void setDateDTO(Date date) { //entity to DTO date conversion
    	tz_Name = checkTimeZone();
    	DoBFormat.setTimeZone(TimeZone.getTimeZone(tz_Name));
        this.DoB = DoBFormat.format(date);
    }
    
    public String checkTimeZone() {
    	Calendar cal = Calendar.getInstance();
		long milliDiff = cal.get(Calendar.ZONE_OFFSET);
		String [] ids = TimeZone.getAvailableIDs();
		String tz_Name = null;
		for (String id: ids) {
			TimeZone tz = TimeZone.getTimeZone(id);
			if (tz.getRawOffset() == milliDiff) {
				tz_Name = id;
				break;
			}
		}
		return tz_Name;
    }

}
