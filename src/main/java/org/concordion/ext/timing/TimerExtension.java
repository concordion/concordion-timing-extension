package org.concordion.ext.timing;

import org.concordion.api.Resource;
import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.ext.timing.footer.TimeFormatter;
import org.concordion.ext.timing.footer.TimerSpecificationListener;

/**
 * Formats the footer of the Example to include how long the Example took to
 * run. It also adds a button at the top of the specification to toggle the
 * visibility of all of the timings via a linked javascript file.
 */
public class TimerExtension implements ConcordionExtension {

    private String toggleIconPath = "";

    @Override
    public void addTo(ConcordionExtender extender) {
        TimerSpecificationListener timerSpec = new TimerSpecificationListener();

        extender.withRunListener(timerSpec);
        extender.withSpecificationProcessingListener(timerSpec);
        extender.withExampleListener(timerSpec);

        extender.withLinkedCSS("/org/concordion/ext/timing/css/style.css", new Resource("/timingExtensionStyle.css"));
        extender.withLinkedJavaScript("/org/concordion/ext/timing/js/toggle.js", new Resource("/timingExtensionToggle.js"));
        extender.withResource(toggleIconPath , new Resource("/toggleIcon.png"));
        extender.withResource("/org/concordion/ext/timing/Resource/cog.png", new Resource("/cog.png"));
    }

    /**
     * This method  allows the user to be able to change the icon they use for toggling the timing text.
     * @param newIconPath is the path to the toggle icon image you wish to use.
     * @return Returns the extension with the changed toggle icon.
     */
    public ConcordionExtension withIcon(String newIconPath) {
        toggleIconPath = newIconPath;
        return this;
    }

    /**
     * Method for allowing the user to configure what language or abbreviated text they want to use for the time
     * formatting.
     * @param hour text to represent hour e.g. 1h or 1hour or 1heure (french)
     * @param minute text to represent minute e.g. 1m or 1minute or 1min or 1minuto (spanish)
     * @param sec text to represent second e.g. 1s or 1second or 1sec or 1zweite (german)
     * @param millisec text to represent millisecond e.g. 1ms or 1millisecond or 1millisec or 1milisegundo (portugese)
     */
    public static void withTimeFormat(String hour, String minute, String sec, String millisec){
        TimeFormatter.setFormatString(hour,minute,sec,millisec);
    }
}
