package spec;

import org.concordion.api.extension.Extensions;
import org.concordion.ext.TimerExtension;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import java.util.regex.Pattern;

@RunWith(value = ConcordionRunner.class)
@Extensions(value = TimerExtension.class)
public class TimerFixture {

    public String name() {
        return "Andrew";
    }

    public String lastname() {
        return "Nah";
    }

    public int multiply(){
        return 60;
    }
    public int sq(int a){
        return a*a;
    }

    public int timeShort(){
   /*
        try {
            wait(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
     */
        return 0;
    }


    public void timeLong() {

        int p = 0;
       for(int i = 0;i < 99999999;i++){
           for(int j = 0;j < 999999999;j++){
           p = p*p-(p*3);
       }}
        //return 0;
    }

    public boolean checkFirstNameAsEmptyString() {
        return checkFirstName("");
    }

    public boolean checkFirstNameAsNull() {
        return checkFirstName(null);
    }

    public boolean checkFirstName(String name) {
        if(name == null) {
            return false;
        }
        Pattern p = Pattern.compile("^[A-Za-z]+$");
        boolean b = p.matcher(name).matches();
        // System.out.println(b);
        return b;
    }

    public boolean checkEmail(String email){
        if(email == null){ return false;}
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public boolean checkDateFormat(String dateString){
            return dateString.matches("^\\d+\\-\\d+\\-\\d+");
        }

    }



