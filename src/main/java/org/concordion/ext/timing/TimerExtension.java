package org.concordion.ext.timing;

import org.concordion.api.Resource;
import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.ext.timing.footer.TimerSpecificationListener;

/**
 * Formats the footer of the Example to include how long the Example took to
 * run. It also adds a button at the top of the specification to toggle the
 * visibility of all of the timings via a linked javascript file.
 */
public class TimerExtension implements ConcordionExtension {

    @Override
    public void addTo(ConcordionExtender extender) {
        TimerSpecificationListener timerSpec = new TimerSpecificationListener();

        extender.withRunListener(timerSpec);
        extender.withSpecificationProcessingListener(timerSpec);
        extender.withExampleListener(timerSpec);

        extender.withLinkedCSS("/org/concordion/ext/timing/css/style.css", new Resource("/timingExtensionStyle.css"));
        extender.withLinkedJavaScript("/org/concordion/ext/timing/js/toggle.js", new Resource("/timingExtensionToggle.js"));
        extender.withResource("/org/concordion/ext/timing/Resource/stopwatch.png", new Resource("/stopwatch.png"));
        extender.withResource("/org/concordion/ext/timing/Resource/cog.png", new Resource("/cog.png"));
    }
}
