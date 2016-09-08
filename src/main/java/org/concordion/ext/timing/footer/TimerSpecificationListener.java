package org.concordion.ext.timing.footer;

import org.concordion.api.*;
import org.concordion.api.listener.*;

import java.util.*;

public class TimerSpecificationListener implements SpecificationProcessingListener, ExampleListener {

    private long startSpecTime;
    private Map<String, Long> exampleStartTimes;

    public TimerSpecificationListener() {
        exampleStartTimes = new HashMap<String, Long>();
    }

    @Override
    public void beforeExample(ExampleEvent event) {
        // store the starting time
        exampleStartTimes.put(event.getExampleName(), System.currentTimeMillis());
    }

    @Override
    public void afterExample(ExampleEvent event) {
        long startTime = exampleStartTimes.get(event.getExampleName());
        long elapsed = (System.currentTimeMillis() - startTime);

        // creates new <div> container for styling the elapsed time
        Element timingContainer = new Element("div");
        timingContainer.addStyleClass("time-fig");

        // creates <p> tag for holding the elapsed time
        Element timingOut = new Element("p");

        // Adds the elapsed time to the <p> tag if the event was caused by an example
        if(event.getResultSummary().isForExample()) {
            timingOut.appendText(TimeFormatter.formatMillSec(elapsed));
        } else {
            // when the event was triggered by a non-example such as a 'before' command, we make sure the
            // time of execution for such commands are not counted in the total elapsed time.
            startSpecTime += elapsed;
        }

        timingContainer.appendChild(timingOut);

        // add it to the bottom of the example HTML
        event.getElement().appendChild(timingContainer);
    }

    @Override
    public void beforeProcessingSpecification(SpecificationProcessingEvent event) {
        startSpecTime = System.currentTimeMillis();
    }

    @Override
    public void afterProcessingSpecification(SpecificationProcessingEvent event) {
        long totalTime = System.currentTimeMillis() - startSpecTime;

        // creates new <div> container for styling the toggle button
        Element toggleContainer = new Element("div");
        toggleContainer.setId("toggle-button");

        // creates <img> tag for the clickable icon that toggles the timing data
        Element toggleIcon = new Element("img");
        toggleIcon.setId("toggleImg");
        toggleIcon.addAttribute("src", "../stopwatch.png");
        toggleIcon.addAttribute("height", "24");
        toggleIcon.addAttribute("width", "24");

        Element iconSettings = new Element("img");
        iconSettings.addStyleClass("cog");
        iconSettings.addAttribute("src", "../cog.png");
        iconSettings.addAttribute("height", "24");
        iconSettings.addAttribute("width", "24");

        // ensures it goes nowhere

        //toggleContainer.appendChild(toggleButton);
        toggleContainer.appendChild(toggleIcon);
        toggleContainer.appendChild(iconSettings);

        // add it to the top of the concordion HTML
        event.getRootElement().getFirstDescendantNamed("body").prependChild(toggleContainer);
    }
}
