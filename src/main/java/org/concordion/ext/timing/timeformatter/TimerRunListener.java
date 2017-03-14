package org.concordion.ext.timing.timeformatter;

import org.concordion.api.Element;
import org.concordion.api.listener.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TimerRunListener implements RunListener {

    private final Map<String, Long> runStartTimes; // Stores the start time of each example
    private final TimeFormatter timeFormatter;

    public TimerRunListener(TimeFormatter timeFormatter) {
        this.runStartTimes = new ConcurrentHashMap<String, Long>();
        this.timeFormatter = timeFormatter;
    }

    @Override
    public void runStarted(RunStartedEvent runStartedEvent) {
        // Store the starting time when a run is started
        runStartTimes.put(runStartedEvent.getElement().getAttributeValue("href"), System.currentTimeMillis());
    }

    @Override
    public void successReported(RunSuccessEvent runSuccessEvent) {
        String fileNameHtml = runSuccessEvent.getElement().getAttributeValue("href");
        // Generate the time elapsed since start time for the specific example
        Long timeElapsed = System.currentTimeMillis() - runStartTimes.get(fileNameHtml);

        writeRunTotalTime(runSuccessEvent.getElement(), timeElapsed);
    }

    @Override
    public void failureReported(RunFailureEvent runFailureEvent) {
        String fileNameHtml = runFailureEvent.getElement().getAttributeValue("href");
        // Generate the time elapsed since start time for the specific example
        Long timeElapsed = System.currentTimeMillis() - runStartTimes.get(fileNameHtml);

        writeRunTotalTime(runFailureEvent.getElement(), timeElapsed);
    }

    @Override
    public void ignoredReported(RunIgnoreEvent runIgnoreEvent) {
        String fileNameHtml = runIgnoreEvent.getElement().getAttributeValue("href");
        // Generate the time elapsed since start time for the specific example
        Long timeElapsed = System.currentTimeMillis() - runStartTimes.get(fileNameHtml);

        writeRunTotalTime(runIgnoreEvent.getElement(), timeElapsed);
    }

    @Override
    public void throwableCaught(ThrowableCaughtEvent event) {
        //never used (i think)
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
