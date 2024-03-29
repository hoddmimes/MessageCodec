package com.hoddmimes.transform;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils
{
    static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    static final String DATE_FILLER = "1970-01-01 00:00:00.000";

    /**
     * Convert routines date to localdate/locaDateTime and vice versa
     */
    public static LocalDate dateToLocalDate(Date pDate ) {
        return Instant.ofEpochMilli(pDate.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date localDateToDate(LocalDate pDate ) {
            return java.util.Date.from(pDate.atStartOfDay()
                    .atZone(ZoneId.systemDefault())
                    .toInstant());
    }

    public static LocalDateTime dateToLocalDateTime(Date pDate) {
        return Instant.ofEpochMilli(pDate.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static Date localDateTimeToDate(LocalDateTime pDate ) {
        return java.util.Date.from(pDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate utcDateToLocalDate( Date pDate )  {
        return Instant.ofEpochMilli(pDate.getTime())
                .atZone(ZoneOffset.UTC)
                .toLocalDate();
    }

    public static LocalDateTime utcDateToLocalDateTime( Date pDate )  {
        return Instant.ofEpochMilli(pDate.getTime())
                .atZone(ZoneOffset.UTC)
                .toLocalDateTime();
    }
    /**
     * String to LocalDate / LocalDateTime and vice versa
     */
     public static LocalDate stringToLocalDate( String pDateString ) {

         return LocalDate.parse( pDateString.substring(0,"yyyy-MM-dd".length()), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
     }

    public static LocalDateTime stringToLocalDateTime( String pDateString ) {
        String tDateStr = pDateString;
        if (tDateStr.length() > DATE_FILLER.length()) {
            tDateStr = tDateStr.substring(0, DATE_FILLER.length());
        } else if (tDateStr.length() < DATE_FILLER.length()) {
            tDateStr = tDateStr + DATE_FILLER.substring( tDateStr.length(), DATE_FILLER.length());
        }
        return LocalDateTime.parse( tDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
    }

    public static String localDateToString( LocalDate  pLocalDate ) {
        return pLocalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String localDateTimeToString( LocalDateTime  pLocalDateTime ) {
        return pLocalDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
    }



    public static String dateToString(Date pDate) {
        if (pDate == null){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat( DATE_FORMAT );
        return sdf.format( pDate );
    }

    public static Date stringToDate(String pDateString) {
        if (pDateString == null){
            return null;
        }

        String tDateStr = pDateString;
        if (tDateStr.length() > DATE_FILLER.length()) {
            tDateStr = tDateStr.substring(0, DATE_FILLER.length());
        } else if (tDateStr.length() < DATE_FILLER.length()) {
            tDateStr = tDateStr + DATE_FILLER.substring( tDateStr.length(), DATE_FILLER.length());
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat( DATE_FORMAT );
            return sdf.parse( tDateStr );
        }
        catch( ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String justDate( String pDateStr ) {
        if (pDateStr.length() > "yyyy-MM-dd".length()) {
            return pDateStr.substring(0, "yyyy-MM-dd".length());
        }
        return pDateStr;
    }
    public static String justDate( Date pDate ) {
        String tDateStr  = dateToString( pDate );
        return justDate( tDateStr );
    }

    public static String justTime( String pDateStr ) {
        if (pDateStr.length() >= DATE_FILLER.length()) {
            return pDateStr.substring("yyyy-MM-dd".length() + 1, DATE_FILLER.length());
        }
        throw new RuntimeException("invalid date and time string \"" + pDateStr  + "\"");
    }

    public static String justTime( Date pDate ) {
        String tDateStr  = dateToString( pDate );
        return justTime( tDateStr );
    }

    /*

    @Test
    public void test()
    {
      LocalDate ld = stringToLocalDate("2020-01-02");
      LocalDateTime ldt = stringToLocalDateTime("2020-01-02 01:02:03.456");

      Date d = localDateToDate( ld );
      Date dt = localDateTimeToDate( ldt );

      System.out.println("[Date: " + justDate( d ) + " DateTime: " + dateToString( dt ) + " ]");

        LocalDate cld = dateToLocalDate( d );
        LocalDateTime cldt = dateToLocalDateTime( dt );
        System.out.println("[LocalDate: " + localDateToString( cld ) + " DateTime: " + localDateTimeToString( cldt ) + " ]");

    }


    public static void main( String args[] ) {
         DateUtils du = new DateUtils();
         du.test();
    }
    */
}
