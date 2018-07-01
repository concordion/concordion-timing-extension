package org.concordion.ext.timing.junit.timeformatter;

import org.concordion.api.Element;
import org.concordion.api.Result;
import org.concordion.api.ResultSummary;
import org.concordion.api.listener.ExampleEvent;
import org.concordion.ext.timing.timeformatter.SimpleTimeFormatter;
import org.concordion.ext.timing.timeformatter.TimerExampleListener;
import org.concordion.internal.FixtureInstance;
import org.concordion.internal.SingleResultSummary;
import org.junit.Assert;
import org.junit.Test;

public class TimerExampleListenerTests {

    @Test
    public void TimerHasCorrectClass() {

        // Arrange
        TimerExampleListener listener = new TimerExampleListener(new SimpleTimeFormatter());
        ResultSummary summary = new SingleResultSummary(Result.SUCCESS);
        ExampleEvent event = new ExampleEvent("Test", new Element("div"), summary, new FixtureInstance(this));

        // Act
        listener.beforeExample(event);
        listener.afterExample(event);

        // Assert
        Element timingContainer = event.getElement().getFirstDescendantNamed("div");
        Assert.assertEquals("Container's class is 'time-fig'", "time-fig", timingContainer.getAttributeValue("class"));
    }


}