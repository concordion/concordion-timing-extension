package org.concordion.ext.timing.junit.timeformatter;

import org.concordion.api.Element;
import org.concordion.api.Resource;
import org.concordion.api.Result;
import org.concordion.api.ResultSummary;
import org.concordion.api.listener.*;
import org.concordion.ext.timing.timeformatter.SimpleTimeFormatter;
import org.concordion.ext.timing.timeformatter.TimerSpecificationListener;
import org.concordion.internal.SingleResultSummary;
import org.junit.Assert;
import org.junit.Test;

public class TimerSpecificationListenerTests {

    @Test
    public void TimerHasCorrectClass() {

        // Arrange
        TimerSpecificationListener listener = new TimerSpecificationListener(new SimpleTimeFormatter());
        ResultSummary summary = new SingleResultSummary(Result.SUCCESS);
        ExampleEvent event = new ExampleEvent("Test", new Element("div"), summary);

        // Act
        listener.beforeExample(event);
        listener.afterExample(event);

        // Assert
        Element timingContainer = event.getElement().getFirstDescendantNamed("div");
        Assert.assertEquals("Container's class is 'time-fig'", "time-fig", timingContainer.getAttributeValue("class"));
    }

    @Test
    public void ToggleButtonHasCorrectId() {

        // Arrange
        Element html = new Element("html");
        html.appendChild(new Element("body"));
        TimerSpecificationListener listener = new TimerSpecificationListener(new SimpleTimeFormatter());
        SpecificationProcessingEvent event = new SpecificationProcessingEvent(new Resource("/"), html);

        // Act
        listener.beforeProcessingSpecification(event);
        listener.afterProcessingSpecification(event);

        // Assert
        Element timingContainer = event.getRootElement()
                .getFirstChildElement("body")
                .getFirstChildElement("div");
        Assert.assertEquals("Container's id is 'toggle-button'", "toggle-button",
                timingContainer.getAttributeValue("id"));
    }

    @Test
    public void RunCommand_RunSuccess_Test() {

        // Initialize Concordion Listeners
        TimerSpecificationListener listener = new TimerSpecificationListener(new SimpleTimeFormatter());
        ResultSummary summary = new SingleResultSummary(Result.SUCCESS);

        // Create Elements to emulate a HTML file.
        Element element = new Element("div");

        // A tag used parse the html name for use with the listener.
        Element aTag = new Element("a");
        aTag.addAttribute("href", "test.html");

        // A tag used to store the timing (no timing actually in it)
        Element spanTag = new Element("span");
        spanTag.addAttribute("class", "time-fig-inline");

        element.appendChild(aTag);

        // Create events to trigger the listener
        SpecificationProcessingEvent event = new SpecificationProcessingEvent(new Resource("/spec/test.html"), element);
        RunSuccessEvent runSuccessEvent = new RunSuccessEvent(aTag, summary);

        // Add events to listener to trigger them
        listener.beforeProcessingSpecification(event);
        listener.successReported(runSuccessEvent);

        // Get the timing span added by the run event above
        Element timingContainer = runSuccessEvent.getElement().getParentElement().getFirstDescendantNamed("span");
        Assert.assertNotEquals(timingContainer, null);

        String timingSpanClassName = timingContainer.getAttributeValue("class");

        // Check if it actually timing span exists
        Assert.assertEquals("Container's class is 'time-fig'", "time-fig-inline", timingSpanClassName);
    }

    @Test
    public void RunCommand_RunFailure_Test() {

        // Initialize Concordion Listeners
        TimerSpecificationListener listener = new TimerSpecificationListener(new SimpleTimeFormatter());
        ResultSummary summary = new SingleResultSummary(Result.SUCCESS);

        // Create Elements to emulate a HTML file.
        Element element = new Element("div");

        // A tag used parse the html name for use with the listener.
        Element aTag = new Element("a");
        aTag.addAttribute("href", "test.html");

        // A tag used to store the timing (no timing actually in it)
        Element spanTag = new Element("span");
        spanTag.addAttribute("class", "time-fig-inline");

        element.appendChild(aTag);

        // Create events to trigger the listener
        SpecificationProcessingEvent event = new SpecificationProcessingEvent(new Resource("/spec/test.html"), element);
        RunFailureEvent runFailureEvent = new RunFailureEvent(aTag, summary);

        // Add events to listener to trigger them
        listener.beforeProcessingSpecification(event);
        listener.failureReported(runFailureEvent);

        // Get the timing span added by the run event above
        Element timingContainer = runFailureEvent.getElement().getParentElement().getFirstDescendantNamed("span");
        Assert.assertNotEquals(timingContainer, null);

        String timingSpanClassName = timingContainer.getAttributeValue("class");

        // Check if it actually timing span exists
        Assert.assertEquals("Container's class is 'time-fig'", "time-fig-inline", timingSpanClassName);
    }

    @Test
    public void RunCommand_RunIgnore_Test() {

        // Initialize Concordion Listeners
        TimerSpecificationListener listener = new TimerSpecificationListener(new SimpleTimeFormatter());
        ResultSummary summary = new SingleResultSummary(Result.SUCCESS);

        // Create Elements to emulate a HTML file.
        Element element = new Element("div");

        // A tag used parse the html name for use with the listener.
        Element aTag = new Element("a");
        aTag.addAttribute("href", "test.html");

        // A tag used to store the timing (no timing actually in it)
        Element spanTag = new Element("span");
        spanTag.addAttribute("class", "time-fig-inline");

        element.appendChild(aTag);

        // Create events to trigger the listener
        SpecificationProcessingEvent event = new SpecificationProcessingEvent(new Resource("/spec/test.html"), element);
        RunIgnoreEvent runIgnoreEvent = new RunIgnoreEvent(aTag, summary);

        // Add events to listener to trigger them
        listener.beforeProcessingSpecification(event);
        listener.ignoredReported(runIgnoreEvent);

        // Get the timing span added by the run event above
        Element timingContainer = runIgnoreEvent.getElement().getParentElement().getFirstDescendantNamed("span");
        Assert.assertNotEquals(timingContainer, null);

        String timingSpanClassName = timingContainer.getAttributeValue("class");

        // Check if it actually timing span exists
        Assert.assertEquals("Container's class is 'time-fig'", "time-fig-inline", timingSpanClassName);
    }
}
