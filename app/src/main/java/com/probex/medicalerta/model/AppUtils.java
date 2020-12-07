package com.probex.medicalerta.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AppUtils {


    //Get hour as a string from a calendar instance
    public static String getHourFromCalendar(Calendar calendar){
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String Hour = sdf.format(date);

        return Hour;
    }

    //Convert the time in milliseconds to a calendar instance
    public static Calendar convertMillisToCalendar(long millis) {
        Timestamp timestamp = new Timestamp(millis);
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());

        return  calendar;
    }

    public static String getDateFromCalendar(Calendar calendar){
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy");
        String formattedDate = sdf.format(date);

        return formattedDate;
    }

    public static int getAge(Date dateOfBirth) {

        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();

        int age = 0;

        birthDate.setTime(dateOfBirth);
        if (birthDate.after(today)) {
            throw new IllegalArgumentException("Can't be born in the future");
        }

        age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        // If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year
        if ( (birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3) ||
                (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH ))){
            age--;

            // If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
        }else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH )) &&
                (birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH ))){
            age--;
        }

        return age;
    }
}
