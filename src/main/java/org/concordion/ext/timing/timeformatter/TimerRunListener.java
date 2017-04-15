package org.concordion.ext.timing.timeformatter;

import org.concordion.api.Element;
import org.concordion.api.listener.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TimerRunListener implements RunListener {
    private Map<Element, Long> runTimes;
    private final TimeFormatter timeFormatter;

    public TimerRunListener(TimeFormatter timeFormatter) {
        this.runTimes = new ConcurrentHashMap<Element, Long>();
        this.timeFormatter = timeFormatter;
    }

    @Override
    public void runStarted(RunStartedEvent runStartedEvent) {
        // Store the starting time when a run is started
        runTimes.put(runStartedEvent.getElement(), System.currentTimeMillis());
    }

    @Override
    public void successReported(RunSuccessEvent runSuccessEvent) {
        addTimingTo(runSuccessEvent);
    }

    @Override
    public void failureReported(RunFailureEvent runFailureEvent) {
        addTimingTo(runFailureEvent);
    }

    @Override
    public void ignoredReported(RunIgnoreEvent runIgnoreEvent) {
        addTimingTo(runIgnoreEvent);
    }

    @Override
    public void throwableCaught(ThrowableCaughtEvent event) {
//        addTimingTo(event);
    }

    private void addTimingTo(AbstractRunEvent runEvent) {
        // Generation the time elapsed since start time for the specific example
        long startTime = runTimes.get(runEvent.getElement());
        long elapsed = (System.currentTimeMillis() - startTime);

        // creates new <div> container for styling the elapsed time
        Element timingContainer = new Element("span");
        timingContainer.addStyleClass("time-run");

        // Adds the elapsed time to the <p> tag if the event was caused by an example
        timingContainer.appendText(" (" + timeFormatter.formatTime(elapsed) + ")");

        // add it to the bottom of the example HTML
        runEvent.getElement().appendSister(timingContainer);
    }
}
