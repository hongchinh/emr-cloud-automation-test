package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.JapaneseChronology;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public DateUtil(){

    }
    public static String getTodayJapaneseDate(){
        DateTimeFormatter tdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentWesternDate = tdf.format(LocalDateTime.now());

        DateTimeFormatter japaneseEraDtf = DateTimeFormatter.ofPattern("yy/MM/dd")
                .withChronology(JapaneseChronology.INSTANCE)
                .withLocale(Locale.JAPAN);
        LocalDate gregorianDate = LocalDate.parse(currentWesternDate);
        JapaneseDate japaneseDate = JapaneseDate.from(gregorianDate);
        return japaneseDate.format(japaneseEraDtf);
    }
    public static String getJapanesePastDate(int day){
        DateTimeFormatter tdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentWesternDate = tdf.format(LocalDateTime.now().minusDays(day));

        DateTimeFormatter japaneseEraDtf = DateTimeFormatter.ofPattern("yy/MM/dd")
                .withChronology(JapaneseChronology.INSTANCE)
                .withLocale(Locale.JAPAN);
        LocalDate gregorianDate = LocalDate.parse(currentWesternDate);
        JapaneseDate japaneseDate = JapaneseDate.from(gregorianDate);
        return japaneseDate.format(japaneseEraDtf);
    }
    public static String getJapaneseFutureDate(int day){
        DateTimeFormatter tdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentWesternDate = tdf.format(LocalDateTime.now().plusDays(day));

        DateTimeFormatter japaneseEraDtf = DateTimeFormatter.ofPattern("yy/MM/dd")
                .withChronology(JapaneseChronology.INSTANCE)
                .withLocale(Locale.JAPAN);
        LocalDate gregorianDate = LocalDate.parse(currentWesternDate);
        JapaneseDate japaneseDate = JapaneseDate.from(gregorianDate);
        return japaneseDate.format(japaneseEraDtf);
    }
    public static String getJapaneseDate(int day){
        return LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern("yyyy年M月d日", Locale.JAPAN));
    }
    public static String getWesternDate(int day){
        return LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.JAPAN));
    }
    public static String convertJapaneseDateToWesternDate(String input){
        DateTimeFormatter originalFormat = DateTimeFormatter.ofPattern("yyyy年M月d日");
        DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        TemporalAccessor date = originalFormat.parse(input);
        return targetFormat.format(date);
    }
}
