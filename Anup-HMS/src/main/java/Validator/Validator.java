package Validator;

import java.util.regex.Pattern;

public class Validator {

    public  static boolean email_validator(String email){
        String email_regex="^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat=Pattern.compile(email_regex);
        if(email==null) return false;
        return pat.matcher(email).matches();

    }

    public static boolean mob_no_Validator(String mob_no){
        String mob_no_regex="^\\d{10}$";
        Pattern pat=Pattern.compile(mob_no_regex);
        return pat.matcher(mob_no).matches();
    }
}
