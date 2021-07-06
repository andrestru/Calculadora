package calculadora.horas.Common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class validate {

    public static void notNull(String value, String message){
        if(value == null){
            throw new NullPointerException(message);
        }
    }

    public static void isTrue(boolean condition, String message){
        if(!condition){
            throw new IllegalArgumentException(message);
        }
    }

    public static boolean nonBlank(String value){
        String trimmed = value.trim();
        return trimmed.length()>0;
    }

    public static void date(nonEmptyString start, nonEmptyString finish, String message) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date starts = simpleDateFormat.parse(start.getValue());
        Date finis = simpleDateFormat.parse(finish.getValue());
        if(finis.before(starts)){
            throw new IllegalArgumentException(message);
        }
    }


}
