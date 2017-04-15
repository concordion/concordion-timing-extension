package spec;

import org.concordion.api.extension.ConcordionExtension;
import org.concordion.api.extension.Extension;
import org.concordion.ext.timing.TimerExtension;
import org.concordion.ext.timing.timeformatter.SimpleTimeFormatter;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.regex.Pattern;



@RunWith(value = ConcordionRunner.class)
public class IgnoreSuccessFixture {

    @Extension
    ConcordionExtension timingExtension = new TimerExtension()
            .withTimeFormatter(new SimpleTimeFormatter("hours", "minutes", "seconds", "milliseconds"));

    public void throwRuntimeException() {
        throw new RuntimeException();
    }

    public void throwRuntimeException2() throws IOException {
        throw new IOException();
    }

    public boolean checkFirstNameUnimpl(String name) {
        return false;
    }

    public boolean checkFirstName(String name) {
        if (name == null) {
            return false;
        }
        Pattern p = Pattern.compile("^[A-Za-z]+$");
        boolean b = p.matcher(name).matches();
        // System.out.println(b);
        return b;
    }
}
