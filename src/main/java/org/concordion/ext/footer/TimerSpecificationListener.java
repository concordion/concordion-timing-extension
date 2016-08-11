package org.concordion.ext.footer;

import org.concordion.api.*;
import org.concordion.api.listener.*;

import java.util.HashMap;
import java.util.Map;

// FIXME: currently only gets the total time for the entire spec file not individual runs of tests
public class TimerSpecificationListener implements SpecificationProcessingListener, ExampleListener {

    private long startSpecTime;
    private Map<String, Long> exampleStartTimes;

    public TimerSpecificationListener() {
        exampleStartTimes = new HashMap<>();
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
        timingOut.appendText(elapsed + "ms");

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
        System.out.println("SpecTime: " + totalTime + "ms");

        // creates new <div> container for styling the toggle button
        Element toggleContainer = new Element("div");
        toggleContainer.setId("toggle-button");

        // creates <a> tag to be clickable and toggle the thing
        Element toggleButton = new Element("a");
        toggleButton.appendText("Toggle Timing");
        toggleButton.addAttribute("href", "#"); // ensures it goes nowhere

        toggleContainer.appendChild(toggleButton);

        // add it to the top of the concordion HTML
        event.getRootElement().prependChild(toggleContainer);
    }
}
