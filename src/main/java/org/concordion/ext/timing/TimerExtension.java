package org.concordion.ext.timing;

import org.concordion.api.Resource;
import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.ext.timing.timeformatter.*;

/**
 * Formats the footer of the Example to include how long the Example took to
 * run. It also adds a button at the top of the specification to toggle the
 * visibility of all of the timings via a linked javascript file.
 */
public class TimerExtension implements ConcordionExtension {

    private TimeFormatter timeFormatter = new SimpleTimeFormatter();
    private String onIconPath = "/org/concordion/ext/timing/img/timing-on.svg";
    private Resource onIconResource = new Resource("/timing-on.svg");
    private String offIconPath = "/org/concordion/ext/timing/img/timing-off.svg";
    private Resource offIconResource = new Resource("/timing-off.svg");
    private boolean showByDefault = true;
    private boolean highlightIcon = false;

    @Override
    public void addTo(ConcordionExtender extender) {
        TimerSpecificationListener specificationListener = new TimerSpecificationListener(onIconResource, offIconResource, highlightIcon, showByDefault);
        TimerExampleListener exampleListener = new TimerExampleListener(timeFormatter);
        TimerRunListener runListener = new TimerRunListener(timeFormatter);

        extender.withSpecificationProcessingListener(specificationListener);
        extender.withExampleListener(exampleListener);
        extender.withRunListener(runListener);
        extender.withLinkedCSS("/org/concordion/ext/timing/css/style.css", new Resource("/timingExtensionStyle.css"));
        extender.withLinkedJavaScript("/org/concordion/ext/timing/js/toggle.js", new Resource("/timingExtensionToggle.js"));
        extender.withResource(onIconPath, onIconResource);
        extender.withResource(offIconPath, offIconResource);
    }

    /**
     * This method  allows the user to be able to change the icon they use for toggling the timing text.
     * This uses a single icon, which is highlighted when on; as opposed to the {@link #withOnIcon(String)} and
     * {@link #withOffIcon(String)} methods which use different icons for on and off.
     * @param newIconPath is the path to the toggle icon image you wish to use.
     * @return Returns the extension with the changed toggle icon.
     */
    public TimerExtension withIcon(String newIconPath) {
        withOnIcon(newIconPath);
        withOffIcon(newIconPath);
        highlightIcon = true;
        return this;
    }

    /**
     * This method  allows the user to be able to change the icon they use for toggling the timing text on.
     * @param newIconPath is the path to the toggle icon image you wish to use.
     * @return Returns the extension with the changed toggle icon.
     */
    public TimerExtension withOnIcon(String newIconPath) {
        onIconPath = newIconPath;
        onIconResource = new Resource(onIconPath.substring(Math.max(onIconPath.lastIndexOf('/'), 0)));
        return this;
    }

    /**
     * This method  allows the user to be able to change the icon they use for toggling the timing text on.
     * @param newIconPath is the path to the toggle icon image you wish to use.
     * @return Returns the extension with the changed toggle icon.
     */
    public TimerExtension withOffIcon(String newIconPath) {
        offIconPath = newIconPath;
        offIconResource = new Resource(offIconPath.substring(Math.max(offIconPath.lastIndexOf('/'), 0)));
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
