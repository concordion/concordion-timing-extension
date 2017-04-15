package org.concordion.ext.timing;

import org.concordion.api.Resource;
import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.ext.timing.timeformatter.SimpleTimeFormatter;
import org.concordion.ext.timing.timeformatter.TimeFormatter;
import org.concordion.ext.timing.timeformatter.TimerExampleListener;
import org.concordion.ext.timing.timeformatter.TimerSpecificationListener;

/**
 * Formats the footer of the Example to include how long the Example took to
 * run. It also adds a button at the top of the specification to toggle the
 * visibility of all of the timings via a linked javascript file.
 */
public class TimerExtension implements ConcordionExtension {

    private TimeFormatter timeFormatter = new SimpleTimeFormatter();
    private String toggleIconPath = "/org/concordion/ext/timing/Resource/stopwatch.png";
    private Resource toggleIconResource = new Resource("/toggleIcon.png");
    private boolean showByDefault = true;

    @Override
    public void addTo(ConcordionExtender extender) {
        TimerSpecificationListener specificationListener = new TimerSpecificationListener(toggleIconResource, showByDefault);
        TimerExampleListener exampleListener = new TimerExampleListener(timeFormatter);

        extender.withSpecificationProcessingListener(specificationListener);
        extender.withExampleListener(exampleListener);
        extender.withLinkedCSS("/org/concordion/ext/timing/css/style.css", new Resource("/timingExtensionStyle.css"));
        extender.withLinkedJavaScript("/org/concordion/ext/timing/js/toggle.js", new Resource("/timingExtensionToggle.js"));
        extender.withResource(toggleIconPath , toggleIconResource);
    }

    /**
     * This method  allows the user to be able to change the icon they use for toggling the timing text.
     * @param newIconPath is the path to the toggle icon image you wish to use.
     * @return Returns the extension with the changed toggle icon.
     */
    public TimerExtension withIcon(String newIconPath) {
        toggleIconPath = newIconPath;
        return this;
    }

    /**
     * Set the TimeFormatter used for the extension
     *
     * Defaults to using a SimpleTimeFormatter
     *
     * @param timeFormat an implementation of a TimeFormatter
     */
    public TimerExtension withTimeFormatter(TimeFormatter timeFormat){
        this.timeFormatter = timeFormat;
        return this;
    }

    /**
     * Option to configure if the timings are shown by default without
     * a press to the toggle button
     *
     * @param show
     * @return
     */
    public TimerExtension withShowByDefault(boolean show) {
        showByDefault = show;
        return this;
    }
}
