package org.concordion.ext.timing.timeformatter;

/**
 * A simple implementation of TimeFormatter
 *
 * Formats the time by showing the two most significant denominations of time with
 * English shorthand for each denominations, e.g. 'h' for Hour, 'm' for minute, etc.
 *
 * This implementation only supports: hours, minutes, seconds and milliseconds
 *
 * A default example out of 10001 milliseconds is "10s 1ms"
 */
public class SimpleTimeFormatter implements TimeFormatter {

    private String hourWord;
    private String minuteWord;
    private String secondWord;
    private String millisecondWord;

    /**
     * Initialise a SimpleTimeFormatter and set the words used for
     *
     * defaults the words used to:
     *         hour: h
     *       minute: m
     *       second: s
     *  millisecond: ms
     */
    public SimpleTimeFormatter() {
        hourWord = "h";
        minuteWord = "m";
        secondWord = "s";
        millisecondWord = "ms";
    }

    /**
     * Initialise a SimpleTimeFormatter and set the words used to denote each denomination of time
     *
     * @param hour string used to denote an hour
     * @param minute string used to denote a minute
     * @param second string used to denote a second
     * @param millisecond string used to denote a millisecond
     */
    public SimpleTimeFormatter(String hour, String minute, String second, String millisecond) {
        hourWord = hour;
        minuteWord = minute;
        secondWord = second;
        millisecondWord = millisecond;
    }

    /**
     * Format milliseconds into a pretty time string
     *
     * @param millSec number of milliseconds to be formatted
     * @return A string with the two most significant units of time.
     */
    public String formatTime(long millSec) {
        if (millSec < 0) {
            throw new IllegalArgumentException("Need a positive time.");
        }

        long seconds = millSec / 1000;
        long minutes = seconds / 60;
        long hours   = minutes / 60;

        if (hours > 0) {
            return String.format("%d%s %d%s", hours, hourWord, minutes % 60, minuteWord);
        } else if (minutes > 0) {
            return String.format("%d%s %d%s", minutes, minuteWord, seconds % 60, secondWord);
        } else if (seconds > 0) {
            return String.format("%d%s %d%s", seconds, secondWord, millSec % 1000, millisecondWord);
        } else {
            return String.format("%d%s", millSec, millisecondWord);
        }
    }
}
