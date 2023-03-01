package research;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class Time {

    public static void main(String[] args) {

        timeTest();
    }
    public static void timeTest(){
        System.out.println("=================");

        ZoneId gmtZone = ZoneId.of("GMT");
        ZoneId utcZone = ZoneId.of("UTC");

        Timestamp databaseUtc = timestamp("2022-12-19 11:59:37.009");
        Timestamp updateGmt = timestamp("2022-12-19 13:35:34.645");

        System.out.println(databaseUtc);
        System.out.println(updateGmt);

        System.out.println("=================");

        Instant dbUpdateUCTInstant = databaseUtc.toInstant();
        Instant updateGmtInstant = updateGmt.toInstant();

        System.out.println(dbUpdateUCTInstant);
        System.out.println(updateGmtInstant);

        System.out.println("=================");

        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(updateGmtInstant, utcZone);
        //System.out.println(zonedDateTime);
        Instant zonedUpdateGmtInstant = zonedDateTime.toInstant();

        System.out.println(dbUpdateUCTInstant);
        System.out.println(zonedUpdateGmtInstant);

        System.out.println("=================");

        System.out.println(databaseUtc.after(Timestamp.from(zonedUpdateGmtInstant)));

        System.out.println("=================");
        System.out.println("=================");

        LocalDateTime dbLocalDateTime = databaseUtc.toLocalDateTime();
        LocalDateTime upLocalDateTime = updateGmt.toLocalDateTime();

        System.out.println(dbLocalDateTime);
        System.out.println(upLocalDateTime);

        System.out.println("=================");

        System.out.println(dbLocalDateTime.atZone(utcZone));
        System.out.println(upLocalDateTime.atZone(utcZone));

        System.out.println("=================");

        System.out.println(dbLocalDateTime.atZone(gmtZone));
        System.out.println(upLocalDateTime.atZone(gmtZone));
    }

    static Timestamp timestamp(String value) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(value);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
