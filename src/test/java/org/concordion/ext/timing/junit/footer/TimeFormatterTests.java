package org.concordion.ext.timing.junit.footer;

import org.concordion.ext.timing.TimerExtension;
import org.concordion.ext.timing.footer.TimeFormatter;
import org.junit.Assert;
import org.junit.Test;

public class TimeFormatterTests {

    static { TimerExtension.withTimeFormat( "h", "m", "s", "ms"); }


    @Test
    public void FormatZero() {
        // Act
        String result = TimeFormatter.formatMillSec(0);

        // Assert
        Assert.assertEquals("Should be 0ms",result, "0ms");
    }

    @Test(expected=IllegalArgumentException.class)
    public void FormatNegative() {
        // Act
        TimeFormatter.formatMillSec(-1000);
    }


    @Test
    public void FormatMilliseconds() {
        // Act
        String result = TimeFormatter.formatMillSec(100);

        // Assert
        Assert.assertEquals("Should be 100ms",result, "100ms");
    }

    @Test
    public void FormatSeconds() {
        // Act
        String result = TimeFormatter.formatMillSec(8100);

        // Assert
        Assert.assertEquals("Should be 8s 100ms",result, "8s 100ms");
    }

    @Test
    public void FormatMinutes() {
        // Act
        String result = TimeFormatter.formatMillSec(248000);

        // Assert
        Assert.assertEquals("Should be 4m 8s",result, "4m 8s");
    }


    @Test
    public void FormatHours() {
        // Act
        String result = TimeFormatter.formatMillSec(18240000);

        // Assert
        Assert.assertEquals("Should be 5h 4m",result, "5h 4m");
    }

    @Test
    public void FormatWithCustomizedStrings1() {
        TimerExtension.withTimeFormat( "hours", "minutes", "seconds", "milliseconds");

        // Act
        String result = TimeFormatter.formatMillSec(0);

        // Assert
        Assert.assertEquals("Should be 0milliseconds", result, "0milliseconds");

        TimerExtension.withTimeFormat( "h", "m", "s", "ms");
    }

    @Test
    public void FormatWithCustomizedStrings2() {
        TimerExtension.withTimeFormat( "hours", "minutes", "seconds", "milliseconds");

        // Act
        String result = TimeFormatter.formatMillSec(8100);

        // Assert
        Assert.assertEquals("Should be 8seconds 100milliseconds", result, "8seconds 100milliseconds");

        TimerExtension.withTimeFormat( "h", "m", "s", "ms");
    }

    @Test
    public void FormatWithCustomizedStrings3() {
        TimerExtension.withTimeFormat( "hours", "minutes", "seconds", "milliseconds");

        // Act
        String result = TimeFormatter.formatMillSec(18240000);

        // Assert
        Assert.assertEquals("Should be 5hours 4minutes", result, "5hours 4minutes");

        TimerExtension.withTimeFormat( "h", "m", "s", "ms");
    }
}
