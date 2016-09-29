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

import java.io.IOError;

public class TimerSpecificationListenerTests {

    @Test
    public void TimerHasCorrectClass() {

        // Arrange
        TimerSpecificationListener listener = new TimerSpecificationListener(new SimpleTimeFormatter(), null);
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
        TimerSpecificationListener listener = new TimerSpecificationListener(new SimpleTimeFormatter(), new Resource("/"));
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

    private Element buildRunElement() {
        // Create Elements to emulate a HTML file.
        Element element = new Element("div");

        // A tag used parse the html name for use with the listener.
        Element aTag = new Element("a");
        aTag.addAttribute("href", "test.html");

        // A tag used to store the timing (no timing actually in it)
        Element spanTag = new Element("span");
        spanTag.addAttribute("class", "time-fig-inline");

        element.appendChild(aTag);

        return element;
    }

    private void assertTimeFigInline(AbstractRunEvent runEvent) {
        // Get the timing span added by the run event above
        Element timingContainer = runEvent.getElement().getParentElement().getFirstDescendantNamed("span");
        Assert.assertNotEquals(timingContainer, null);

        String timingSpanClassName = timingContainer.getAttributeValue("class");

        // Check if it actually timing span exists
        Assert.assertEquals("Container's class is 'time-fig-inline'", "time-fig-inline", timingSpanClassName);
    }

    @Test
    public void RunCommand_RunSuccess_Test() {
        /// Arrange
        // Initialize Concordion Listeners
        TimerSpecificationListener listener = new TimerSpecificationListener(new SimpleTimeFormatter(), null);
        ResultSummary summary = new SingleResultSummary(Result.SUCCESS);

        Element element = buildRunElement();

        // Create events to trigger the listener
        SpecificationProcessingEvent event = new SpecificationProcessingEvent(new Resource("/spec/test.html"), element);
        RunSuccessEvent runSuccessEvent = new RunSuccessEvent(element.getFirstChildElement("a"), summary);

        /// Act
        // Add events to listener to trigger them
        listener.beforeProcessingSpecification(event);
        listener.successReported(runSuccessEvent);

        /// Assert
        assertTimeFigInline(runSuccessEvent);
    }

    @Test
    public void RunCommand_RunFailure_Test() {
        /// Arrange
        // Initialize Concordion Listeners
        TimerSpecificationListener listener = new TimerSpecificationListener(new SimpleTimeFormatter(), null);
        ResultSummary summary = new SingleResultSummary(Result.FAILURE);

        // Create Elements to emulate a HTML file.
        Element element = buildRunElement();

        // Create events to trigger the listener
        SpecificationProcessingEvent event = new SpecificationProcessingEvent(new Resource("/spec/test.html"), element);
        RunFailureEvent runFailureEvent = new RunFailureEvent(element.getFirstChildElement("a"), summary);

        /// Act
        // Add events to listener to trigger them
        listener.beforeProcessingSpecification(event);
        listener.failureReported(runFailureEvent);

        /// Assert
        assertTimeFigInline(runFailureEvent);
    }

    @Test
    public void RunCommand_RunIgnore_Test() {
        /// Arrange
        // Initialize Concordion Listeners
        TimerSpecificationListener listener = new TimerSpecificationListener(new SimpleTimeFormatter(), null);
        ResultSummary summary = new SingleResultSummary(Result.SUCCESS);

        // Create Elements to emulate a HTML file.
        Element element = buildRunElement();

        // Create events to trigger the listener
        SpecificationProcessingEvent event = new SpecificationProcessingEvent(new Resource("/spec/test.html"), element);
        RunIgnoreEvent runIgnoreEvent = new RunIgnoreEvent(element.getFirstChildElement("a"), summary);

        /// Act
        // Add events to listener to trigger them
        listener.beforeProcessingSpecification(event);
        listener.ignoredReported(runIgnoreEvent);

        /// Assert
        assertTimeFigInline(runIgnoreEvent);
    }

    @Test
    public void RunCommand_RunCatchThrow_Test() {
        /// Arrange
        // Initialize Concordion Listeners
        TimerSpecificationListener listener = new TimerSpecificationListener(new SimpleTimeFormatter(), null);

        // Create Elements to emulate a HTML file.
        Element element = buildRunElement();

        // Create events to trigger the listener
        SpecificationProcessingEvent event = new SpecificationProcessingEvent(new Resource("/spec/test.html"), element);
        ThrowableCaughtEvent throwCaughtEvent = new ThrowableCaughtEvent(new Throwable("error"), element.getFirstChildElement("a"), "expr");

        /// Act
        // Add events to listener to trigger them
        listener.beforeProcessingSpecification(event);
        listener.throwableCaught(throwCaughtEvent);

        /// Assert
        // catch throw does nothing
        Element timingContainer = throwCaughtEvent.getElement().getParentElement().getFirstDescendantNamed("span");
        Assert.assertNull(timingContainer);
    }

    @Test
    public void toggleIconAtSameLevel() {
        assertToggleIconsPath("/test.png", "/", "test.png");
    }

    @Test
    public void toggleIconBelowSpec() {
        assertToggleIconsPath("/img/test.png", "/", "img/test.png");
    }

    @Test
    public void toggleIconAboveSpec() {
        assertToggleIconsPath("/test.png", "/sub/", "../test.png");
    }

    @Test
    public void toggleIconSiblingPathSpec() {
        assertToggleIconsPath("/img/test.png", "/sub/", "../img/test.png");
    }

    private void assertToggleIconsPath(String iconPath, String specificationPath, String expectedIconPath) {
        // Arrange
        Element html = new Element("html");
        html.appendChild(new Element("body"));

        TimerSpecificationListener listener = new TimerSpecificationListener(new SimpleTimeFormatter(), new Resource(iconPath));
        SpecificationProcessingEvent event = new SpecificationProcessingEvent(new Resource(specificationPath), html);

        // Act
        listener.beforeProcessingSpecification(event);
        listener.afterProcessingSpecification(event);

        // Assert
        Element toggleTimingIcon = event.getRootElement()
                .getElementById("toggle-button")
                .getFirstChildElement("img");

        Assert.assertNotNull(toggleTimingIcon);
        Assert.assertEquals("Toggle button 'toggle-button'", expectedIconPath,
                toggleTimingIcon.getAttributeValue("src"));

    }

}
