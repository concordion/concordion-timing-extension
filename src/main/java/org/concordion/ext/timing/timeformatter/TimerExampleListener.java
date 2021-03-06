package org.concordion.ext.timing.timeformatter;

import org.concordion.api.Element;
import org.concordion.api.listener.ExampleEvent;
import org.concordion.api.listener.ExampleListener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TimerExampleListener implements ExampleListener {

    private final Map<String, Long> exampleStartTimes; // Stores the start time of each example
    private final TimeFormatter timeFormatter;

    public TimerExampleListener(TimeFormatter timeFormatter) {
        this.exampleStartTimes = new ConcurrentHashMap<String, Long>();
        this.timeFormatter = timeFormatter;
    }

    @Override
    public void beforeExample(ExampleEvent event) {
        // Store the starting time when a example is started
        exampleStartTimes.put(event.getExampleName(), System.currentTimeMillis());
    }

    @Override
    public void afterExample(ExampleEvent event) {
        // Generate the time elapsed since start time for the specific example
        long startTime = exampleStartTimes.get(event.getExampleName());
        long elapsed = (System.currentTimeMillis() - startTime);

        if(event.getElement().getLocalName().equalsIgnoreCase("td")) {
            Element tde = new Element("td");
            tde.addAttribute("align", "right");
            tde.addStyleClass("time-fig-table-cell");

            Element tde_p = new Element("p");
            tde_p.appendText(timeFormatter.formatTime(elapsed));
            tde.appendChild(tde_p);

            event.getElement().getParentElement().appendChild(tde);
            return;
        }
        // creates new <div> container for styling the elapsed time
        Element timingContainer = new Element("div");
        timingContainer.addStyleClass("time-fig");

        // creates <p> tag for holding the elapsed time
        Element timingOut = new Element("p");

        // Adds the elapsed time to the <p> tag if the event was caused by an example
        timingOut.appendText(timeFormatter.formatTime(elapsed));

        // Add the timing duration to the timing container
        // (as the duration cannot be added directly to the root element)
        timingContainer.appendChild(timingOut);

        // add it to the bottom of the example HTML
        event.getElement().appendChild(timingContainer);
    }
}
