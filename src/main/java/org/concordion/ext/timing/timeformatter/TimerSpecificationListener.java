package org.concordion.ext.timing.timeformatter;

import org.concordion.api.*;
import org.concordion.api.listener.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TimerSpecificationListener implements SpecificationProcessingListener, ExampleListener, RunListener  {

    private long startSpecTime; // Start Time of spec (Can be overridden)
    private Map<String, Long> exampleStartTimes; // Stores the start time of each example
    private static Map<String, Long> runStartTimes = new ConcurrentHashMap<String, Long>(); // Stores the start time of each run
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
        if(event.getResultSummary().isForExample()) {
            timingOut.appendText(timeFormatter.formatTime(elapsed));
        } else {
            // when the event was triggered by a non-example such as a 'before' command, we make sure the
            // time of execution for such commands are not counted in the total elapsed time.
            startSpecTime += elapsed;
        }

        // Add the timing duration to the timing container
        // (as the duration cannot be added directly to the root element)
        timingContainer.appendChild(timingOut);

        // add it to the bottom of the example HTML
        event.getElement().appendChild(timingContainer);
    }

    @Override
    public void beforeProcessingSpecification(SpecificationProcessingEvent event) {
        // Store starting time when the specification is executed.
        startSpecTime = System.currentTimeMillis();
        //getPath() includes the /spec/ folder which we cant retrieve later so it it removed from path
        String path = event.getResource().getPath();
        int i = path.indexOf("/",1);
        path = path.substring(i+1);
        runStartTimes.put(path, System.currentTimeMillis());
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

    @Override
    public void successReported(RunSuccessEvent runSuccessEvent) {
        // Get filename of html specification to be used to retrieve the start time of the run
        String fileNameHtml = runSuccessEvent.getElement().getAttributeValue("href").replace(".md", ".html");

        // Calculate the time elapsed of the specific run commnad
        Long timeElapsed = System.currentTimeMillis() - runStartTimes.get(fileNameHtml);

        // Write the time elapsed to the root element
        writeRunTotalTime(runSuccessEvent.getElement(), timeElapsed);
    }

    @Override
    public void failureReported(RunFailureEvent runFailureEvent) {
        // Get filename of html specification to be used to retrieve the start time of the run
        String fileNameHtml = runFailureEvent.getElement().getAttributeValue("href").replace(".md", ".html");

        // Calculate the time elapsed of the specific run commnad
        Long timeElapsed = System.currentTimeMillis() - runStartTimes.get(fileNameHtml);

        // Write the time elapsed to the root element
        writeRunTotalTime(runFailureEvent.getElement(), timeElapsed);
    }

    @Override
    public void ignoredReported(RunIgnoreEvent runIgnoreEvent) {
        // Get filename of html specification to be used to retrieve the start time of the run
        String fileNameHtml = runIgnoreEvent.getElement().getAttributeValue("href").replace(".md", ".html");

        // Calculate the time elapsed of the specific run command
        Long timeElapsed = System.currentTimeMillis() - runStartTimes.get(fileNameHtml);

        // Write the time elapsed to the root element
        writeRunTotalTime(runIgnoreEvent.getElement(), timeElapsed);
    }

    @Override
    public void throwableCaught(ThrowableCaughtEvent event) {
        // Code Not called.
    }

    /**
     * Writes the duration next to element and formats the time.
     *
     * @param element The sister/parent element to append the the duration to.
     * @param duration The timing duration of an example, spec or run
     */
    private void writeRunTotalTime(Element element, Long duration) {
        Element durationTag = new Element("span");
        durationTag.addAttribute("class", "time-fig-inline");
        durationTag.appendText(" (" + timeFormatter.formatTime(duration) + ")");
        element.appendSister(durationTag);
    }
}