package org.concordion.ext.timing.footer;

/**
 * Utility class for formatting time
 */
public class TimeFormatter {

    private static String hourWord = "h";
    private static String minuteWord = "m";
    private static String secondWord = "s";
    private static String milisecWord = "ms";

    private TimeFormatter() {
        throw new IllegalAccessError();
    }

    /**
     * Format milliseconds into a pretty time string
     *
     * @param millSec number of milliseconds to be formatted
     * @return A string with the two most significant units of time.
     */
    public static String formatMillSec(long millSec) {
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
            return String.format("%d%s %d%s", seconds, secondWord, millSec % 1000, milisecWord);
        } else {
            return String.format("%d%s", millSec, milisecWord);
        }

    }

    public static void setFormatString(String hours, String mins, String secs, String miliSecs){
        TimeFormatter.hourWord = hours;
        TimeFormatter.minuteWord = mins;
        TimeFormatter.secondWord = secs;
        TimeFormatter.milisecWord = miliSecs;
    }


}
