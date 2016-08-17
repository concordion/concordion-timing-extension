package org.concordion.ext;

import org.concordion.api.Resource;
import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.ext.footer.TimerSpecificationListener;

/**
 * Formats the footer of the Example to include how long the Example took to
 * run. It also adds a button at the top of the specification to toggle the
 * visibility of all of the timings via a linked javascript file.
 */
public class TimerExtension implements ConcordionExtension {

    @Override
    public void addTo(ConcordionExtender extender) {
        TimerSpecificationListener timerSpec = new TimerSpecificationListener();

        extender.withSpecificationProcessingListener(timerSpec);
        extender.withExampleListener(timerSpec);

        extender.withLinkedCSS("/css/style.css", new Resource("/css/timingExtensionStyle.css"));
        extender.withLinkedJavaScript("/js/toggle.js", new Resource("/js/timingExtensionToggle.js"));
    }
}
