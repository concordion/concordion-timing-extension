package org.concordion.ext.junit.footer;

import org.concordion.api.Element;
import org.concordion.api.Resource;
import org.concordion.api.Result;
import org.concordion.api.ResultSummary;
import org.concordion.api.listener.ExampleEvent;
import org.concordion.api.listener.SpecificationProcessingEvent;
import org.concordion.ext.footer.TimerSpecificationListener;
import org.concordion.internal.SingleResultSummary;
import org.junit.Assert;
import org.junit.Test;

public class TimerSpecificationListenerTests {

    @Test
    public void TimerHasCorrectClass() {

        // Arrange
        TimerSpecificationListener listener = new TimerSpecificationListener();
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
        TimerSpecificationListener listener = new TimerSpecificationListener();
        SpecificationProcessingEvent event = new SpecificationProcessingEvent(new Resource("/"), new Element("div"));

        // Act
        listener.beforeProcessingSpecification(event);
        listener.afterProcessingSpecification(event);

        // Assert
        Element timingContainer = event.getRootElement().getFirstDescendantNamed("div");
        Assert.assertEquals("Container's id is 'toggle-button'", "toggle-button",
                timingContainer.getAttributeValue("id"));
    }


}
