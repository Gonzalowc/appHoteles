package edu.fpdual.hotelesapp.interfaz;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class ReservaRoomController {
	
	
	public Date convertToDate(LocalDate localDate) {
		
		ZonedDateTime zdt = localDate.atStartOfDay(ZoneId.systemDefault());
		Instant instant = zdt.toInstant();
		Date date = Date.from(instant);
		return date;
	}

}
