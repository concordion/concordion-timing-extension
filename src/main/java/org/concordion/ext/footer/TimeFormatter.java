package org.concordion.ext.footer;

public class TimeFormatter {

    private TimeFormatter() {

    }

    public static String formatMillSec(long millSec) {
        if (millSec < 0) {
            throw new IllegalArgumentException("Need a positive time.");
        }

        long seconds = millSec / 1000;
        long minutes = seconds / 60;
        long hours   = minutes / 60;

        if (hours > 0) {
            return String.format("%dh %dm", hours, minutes % 60);
        } else if (minutes > 0) {
            return String.format("%dm %ds", minutes, seconds % 60);
        } else if (seconds > 0) {
            return String.format("%ds %dms", seconds, millSec % 1000);
        } else {
            return String.format("%dms", millSec);
        }

    }

}
