package org.concordion.ext.timing.timeformatter;

/**
 * Interface to format elapsed time into a string
 */
public interface TimeFormatter {

    /**
     * Format milliseconds into a pretty time string
     *
     * @param milliseconds number of milliseconds to be formatted
     * @return a string representation of the time
     *
     * @throws IllegalArgumentException if the milliseconds is below 0
     */
    String formatTime(long milliseconds);
}
