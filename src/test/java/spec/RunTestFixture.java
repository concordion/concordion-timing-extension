package spec;

/**
 * Created by andrew on 18/08/16.
 */
import org.concordion.api.extension.Extensions;
import org.concordion.ext.TimerExtension;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import java.util.regex.Pattern;

@RunWith(value = ConcordionRunner.class)
@Extensions(value = TimerExtension.class)

public class RunTestFixture {

    public String poi() {return "poi";}

}
