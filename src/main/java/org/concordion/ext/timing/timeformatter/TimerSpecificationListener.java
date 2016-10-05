package org.concordion.ext.timing.timeformatter;

import org.concordion.api.*;
import org.concordion.api.listener.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TimerSpecificationListener implements SpecificationProcessingListener, ExampleListener  {

    private Map<String, Long> exampleStartTimes; // Stores the start time of each example
    private final TimeFormatter timeFormatter;
    private final Resource iconResource;

    public TimerSpecificationListener(TimeFormatter timeFormatter, Resource icon) {
        // Initialise Variables
        exampleStartTimes = new HashMap<String, Long>();
        this.timeFormatter = timeFormatter;
        iconResource = icon;
    }

    @Override
    public void beforeExample(ExampleEvent event) {
        // Store the starting time when a example is started
        exampleStartTimes.put(event.getExampleName(), System.currentTimeMillis());
    }

    @Override
    public void afterExample(ExampleEvent event) {
        // Generation the time elapsed since start time for the specific example
        long startTime = exampleStartTimes.get(event.getExampleName());
        long elapsed = (System.currentTimeMillis() - startTime);

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

    @Override
    public void beforeProcessingSpecification(SpecificationProcessingEvent event) {
        //getPath() includes the /spec/ folder which we cant retrieve later so it it removed from path
        String path = event.getResource().getPath();
        int i = path.indexOf("/",1);
        path = path.substring(i+1);
    }

    @Override
    public void afterProcessingSpecification(SpecificationProcessingEvent event) {
        // Add toggle button to specification sheet

        // creates new <div> container for styling the toggle button
        Element toggleContainer = new Element("div");
        toggleContainer.setId("toggle-button");

        // creates <img> tag for the clickable icon that toggles the timing data
        Element toggleIcon = new Element("img");
        toggleIcon.setId("toggleImg");
        toggleIcon.addStyleClass("time-toggle-button");
        toggleIcon.addStyleClass("time-toggle-button-on");
        toggleIcon.addAttribute("src", event.getResource().getRelativePath(iconResource));
        toggleIcon.addAttribute("height", "24");
        toggleIcon.addAttribute("width", "24");
        // ensures it goes nowhere

        //toggleContainer.appendChild(toggleButton);
        toggleContainer.appendChild(toggleIcon);
        //toggleContainer.appendChild(iconSettings);

        // add it to the top of the concordion HTML
        event.getRootElement().getFirstDescendantNamed("body").prependChild(toggleContainer);
    }
}