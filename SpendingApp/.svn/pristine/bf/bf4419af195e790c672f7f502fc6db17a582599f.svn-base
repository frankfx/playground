package de.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateService {
	public static LocalDate getLocalDate(Date date) {
		return date != null ? date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : LocalDate.MIN;
	}
	
	public static Date getDate(LocalDate localDate) {
		return localDate != null ? Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()) : new Date();
	}
}