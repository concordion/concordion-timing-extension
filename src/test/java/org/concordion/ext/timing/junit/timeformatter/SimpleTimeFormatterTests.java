package org.concordion.ext.timing.junit.timeformatter;

import org.concordion.ext.timing.timeformatter.SimpleTimeFormatter;
import org.concordion.ext.timing.timeformatter.TimeFormatter;
import org.junit.Assert;
import org.junit.Test;

public class SimpleTimeFormatterTests {

    @Test
    public void FormatZero() {
        // Arrange
        TimeFormatter timeFormatter = new SimpleTimeFormatter();

        // Act
        String result = timeFormatter.formatTime(0);

        // Assert
        Assert.assertEquals("Should be 0ms",result, "0ms");
    }

    @Test(expected=IllegalArgumentException.class)
    public void FormatNegative() {
        // Arrange
        TimeFormatter timeFormatter = new SimpleTimeFormatter();

        // Act
        timeFormatter.formatTime(-1000);
    }


    @Test
    public void FormatMilliseconds() {
        // Arrange
        TimeFormatter timeFormatter = new SimpleTimeFormatter();

        // Act
        String result = timeFormatter.formatTime(100);

        // Assert
        Assert.assertEquals("Should be 100ms",result, "100ms");
    }

    @Test
    public void FormatSeconds() {
        // Arrange
        TimeFormatter timeFormatter = new SimpleTimeFormatter();

        // Act
        String result = timeFormatter.formatTime(8100);

        // Assert
        Assert.assertEquals("Should be 8s 100ms",result, "8s 100ms");
    }

    @Test
    public void FormatMinutes() {
        // Arrange
        TimeFormatter timeFormatter = new SimpleTimeFormatter();

        // Act
        String result = timeFormatter.formatTime(248000);

        // Assert
        Assert.assertEquals("Should be 4m 8s",result, "4m 8s");
    }


    @Test
    public void FormatHours() {
        // Arrange
        TimeFormatter timeFormatter = new SimpleTimeFormatter();

        // Act
        String result = timeFormatter.formatTime(18240000);

        // Assert
        Assert.assertEquals("Should be 5h 4m",result, "5h 4m");
    }

    @Test
    public void FormatWithCustomizedStrings1() {
        // Arrange
        TimeFormatter timeFormatter = new SimpleTimeFormatter("hours", "minutes", "seconds", "milliseconds");

        // Act
        String result = timeFormatter.formatTime(0);

        // Assert
        Assert.assertEquals("Should be 0milliseconds", result, "0milliseconds");
    }

    @Test
    public void FormatWithCustomizedStrings2() {
        // Arrange
        TimeFormatter timeFormatter = new SimpleTimeFormatter("hours", "minutes", "seconds", "milliseconds");

        // Act
        String result = timeFormatter.formatTime(8100);

        // Assert
        Assert.assertEquals("Should be 8seconds 100milliseconds", result, "8seconds 100milliseconds");
    }

    @Test
    public void FormatWithCustomizedStrings3() {
        // Arrange
        TimeFormatter timeFormatter = new SimpleTimeFormatter("hours", "minutes", "seconds", "milliseconds");

        // Act
        String result = timeFormatter.formatTime(18240000);

        // Assert
        Assert.assertEquals("Should be 5hours 4minutes", result, "5hours 4minutes");
    }
}
