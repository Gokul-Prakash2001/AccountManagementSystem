package com.eygds.ams;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;



public class CalculateAge {

   public static int getAge(String dob) throws ParseException {

      //Converting String to Date
      SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
      Date date = formatter.parse(dob);
      //Converting obtained Date object to LocalDate object
      Instant instant = date.toInstant();
      ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
      LocalDate givenDate = zone.toLocalDate();
      //Calculating the difference between given date to current date.
      Period period = Period.between(givenDate, LocalDate.now());
      return period.getYears();
   }
}