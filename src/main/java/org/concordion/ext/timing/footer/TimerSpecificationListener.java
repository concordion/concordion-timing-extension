package org.concordion.ext.timing.footer;

import org.concordion.api.*;
import org.concordion.api.listener.*;

import java.util.*;

// FIXME: currently only gets the total time for the entire spec file not individual runs of tests
public class TimerSpecificationListener implements SpecificationProcessingListener, ExampleListener, RunListener  {

    private long startSpecTime;
    private Map<String, Long> exampleStartTimes;
    private static Map<String, Long> runStartTimes;

    public TimerSpecificationListener() {
        runStartTimes = new HashMap<String, Long>();
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
        System.out.println("beforeProcessingSpecification!");

        runStartTimes.put(event.getResource().getName(), System.currentTimeMillis());
        System.out.println(event.getResource().getName() + " : " + System.currentTimeMillis());
        //System.out.println(event.getResource().getName() + " : " + event.getResource().getPath());
    }

    @Override
    public void afterProcessingSpecification(SpecificationProcessingEvent event) {
        long totalTime = System.currentTimeMillis() - startSpecTime;

        // creates new <div> container for styling the toggle button
        Element toggleContainer = new Element("div");
        toggleContainer.setId("toggle-button");

        // creates <a> tag to be clickable and toggle the thing
        Element toggleButton = new Element("a");
        toggleButton.appendText("Toggle Timing");
        toggleButton.addAttribute("href", "#"); // ensures it goes nowhere

        toggleContainer.appendChild(toggleButton);

        // add it to the top of the concordion HTML
        event.getRootElement().getFirstDescendantNamed("body").prependChild(toggleContainer);
    }

    @Override
    public void successReported(RunSuccessEvent runSuccessEvent) {
        System.out.println(runSuccessEvent.getElement().getAttributeValue("href"));
        String fileNameHtml = runSuccessEvent.getElement().getAttributeValue("href").replace(".md", ".html");
        System.out.println("fileNameHtml:" + fileNameHtml);

        System.out.println("g:" + runStartTimes.get(fileNameHtml));

        //long totalTime = runStartTimes.get() - startSpecTime;
        Long soCLean = System.currentTimeMillis() - runStartTimes.get(fileNameHtml);
        System.out.println("f: " + soCLean);

        writeRunTotalTime(runSuccessEvent.getElement(), soCLean);


        //System.out.println("totalTime: "+totalTime);
        System.out.println(runSuccessEvent.getResultSummary().getSpecificationDescription());
        System.out.println(runSuccessEvent.getResultSummary().getImplementationStatus());
        System.out.println("apples!");
}

    @Override
    public void failureReported(RunFailureEvent runFailureEvent) {
        System.out.println(runFailureEvent.getElement().getAttributeValue("href"));
        String fileNameHtml = runFailureEvent.getElement().getAttributeValue("href").replace(".md", ".html");
        System.out.println("fileNameHtml:" + fileNameHtml);

        System.out.println("g:" + runStartTimes.get(fileNameHtml));

        //long totalTime = runStartTimes.get() - startSpecTime;
        Long soCLean = System.currentTimeMillis() - runStartTimes.get(fileNameHtml);
        System.out.println("f: " + soCLean);

        writeRunTotalTime(runFailureEvent.getElement(), soCLean);


        //System.out.println("totalTime: "+totalTime);
        System.out.println(runFailureEvent.getResultSummary().getSpecificationDescription());
        System.out.println(runFailureEvent.getResultSummary().getImplementationStatus());
        //System.out.println(runFailureEvent);
        System.out.println("apples1!");
    }

    @Override
    public void ignoredReported(RunIgnoreEvent runIgnoreEvent) {
        //not sure if needed remove is issues
        System.out.println(runIgnoreEvent.getElement().getAttributeValue("href"));
        String fileNameHtml = runIgnoreEvent.getElement().getAttributeValue("href").replace(".md", ".html");
        System.out.println("fileNameHtml:" + fileNameHtml);

        System.out.println("g:" + runStartTimes.get(fileNameHtml));

        //long totalTime = runStartTimes.get() - startSpecTime;
        Long soCLean = System.currentTimeMillis() - runStartTimes.get(fileNameHtml);
        System.out.println("f: " + soCLean);

        writeRunTotalTime(runIgnoreEvent.getElement(), soCLean);


        //System.out.println("totalTime: "+totalTime);
        System.out.println(runIgnoreEvent.getResultSummary().getSpecificationDescription());
        System.out.println(runIgnoreEvent.getResultSummary().getImplementationStatus());

        //System.out.println(runIgnoreEvent);
        System.out.println("apples2!");
    }

    @Override
    public void throwableCaught(ThrowableCaughtEvent event) {
        //not sure if needed remove is issues
        System.out.println(event.getElement().toXML());
        System.out.println(event.getElement().getAttributeValue("href"));
        String fileNameHtml = event.getElement().getAttributeValue("href").replace(".md", ".html");
        System.out.println("fileNameHtml:" + fileNameHtml);

        System.out.println("g:" + runStartTimes.get(fileNameHtml));

        //long totalTime = runStartTimes.get() - startSpecTime;
        Long soCLean = System.currentTimeMillis() - runStartTimes.get(fileNameHtml);
        System.out.println("f: " + soCLean);

        writeRunTotalTime(event.getElement(), soCLean);


        //System.out.println("totalTime: "+totalTime);
        //System.out.println(event);
        System.out.println("apples3!");
    }

    public void writeRunTotalTime(Element element, Long duration) {
        Element durationTag = new Element("span");
        durationTag.addAttribute("class", "time-fig-inline");
        durationTag.appendText(" (" + TimeFormatter.formatMillSec(duration) + ")");

        element.appendSister(durationTag);
    }
}
