package org.concordion.ext.footer;

import org.concordion.api.*;
import org.concordion.api.listener.*;
import org.concordion.internal.command.ResultAnnouncer;

import java.util.HashMap;
import java.util.Map;

// FIXME: currently only gets the total time for the entire spec file not individual runs/examples
public class TimerSpecificationListener implements SpecificationProcessingListener, ExampleListener {

    private long startSpecTime;
    private Map<String, Long> exampleStartTimes;

    public TimerSpecificationListener() {
        exampleStartTimes = new HashMap<>();
    }

    @Override
    public void beforeExample(ExampleEvent event) {
        exampleStartTimes.put(event.getExampleName(), System.currentTimeMillis());
        System.out.println("ExpPre: ms");
    }

    @Override
    public void afterExample(ExampleEvent event) {
        long startTime = exampleStartTimes.get(event.getExampleName());
        long elapsed = (System.currentTimeMillis() - startTime);
        System.out.println("ExpPost:"  + elapsed  + " ms");

        Element timingOut = new Element("p");
        timingOut.addStyleClass("time-fig");
        timingOut.appendText(elapsed + "ms");
        event.getElement()
                .appendChild(timingOut);
    }

    @Override
    public void beforeProcessingSpecification(SpecificationProcessingEvent event) {
        startSpecTime = System.currentTimeMillis();
    }

    @Override
    public void afterProcessingSpecification(SpecificationProcessingEvent event) {
        long totalTime = System.currentTimeMillis() - startSpecTime;
        System.out.println("SpecTime: " + totalTime + "ms");

        Element toggleButton = new Element("a");
        toggleButton.appendText("Toggle Timing");
        toggleButton.addAttribute("href", "#");
        toggleButton.setId("toggle-button");
        event.getRootElement().prependChild(toggleButton);
    }
}
