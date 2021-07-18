package de.framedev.javautils;

/**
 * / This Plugin was Created by FrameDev
 * / Package : de.framedev.javautils
 * / ClassName Time
 * / Date: 18.07.21
 * / Project: JavaUtils
 * / Copyrighted by FrameDev
 */

public enum Time {

    SECONDS(1),
    MINUTES(60),
    HOURS(3600),
    DAYS(86400),
    WEEKS(604800),
    MONTHS(WEEKS.time * 4),
    YEARS(31536000);

    private final long time;

    Time(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public static long toSec(Time time) {
        switch (time) {
            case MINUTES:
                return 60;
            case HOURS:
                return 3600;
            case DAYS:
                return 86400;
            case WEEKS:
                return 604800;
            case MONTHS:
                return WEEKS.time * 4;
            case YEARS:
                return 31536000;
            default:
                throw new IllegalStateException("Unexpected value: " + time);
        }
    }

    public static double minToTime(long minutes, Time time) {
        switch (time) {
            case SECONDS:
                return (double) minutes / SECONDS.time;
            case MINUTES:
                return (double) minutes / MINUTES.time;
            case HOURS:
                return (double) minutes / HOURS.time;
            case DAYS:
                return (double) minutes / DAYS.time;
            case WEEKS:
                return (double) minutes / WEEKS.time;
            case MONTHS:
                return (double) minutes / MONTHS.time;
            case YEARS:
                return (double) minutes / YEARS.time;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static double hourToTime(long hours, Time time) {
        switch (time) {
            case SECONDS:
                return (double) hours / SECONDS.time;
            case MINUTES:
                return (double) hours / MINUTES.time;
            case HOURS:
                return (double) hours / HOURS.time;
            case DAYS:
                return (double) hours / DAYS.time;
            case WEEKS:
                return (double) hours / WEEKS.time;
            case MONTHS:
                return (double) hours / MONTHS.time;
            case YEARS:
                return (double) hours / YEARS.time;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static double dayToTime(long days, Time time) {
        switch (time) {
            case SECONDS:
                return (double) days / SECONDS.time;
            case MINUTES:
                return (double) days / MINUTES.time;
            case HOURS:
                return (double) days / HOURS.time;
            case DAYS:
                return (double) days / DAYS.time;
            case WEEKS:
                return (double) days / WEEKS.time;
            case MONTHS:
                return (double) days / MONTHS.time;
            case YEARS:
                return (double) days / YEARS.time;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static double weeksToTime(long weeks, Time time) {
        switch (time) {
            case SECONDS:
                return (double) weeks / SECONDS.time;
            case MINUTES:
                return (double) weeks / MINUTES.time;
            case HOURS:
                return (double) weeks / HOURS.time;
            case DAYS:
                return (double) weeks / DAYS.time;
            case WEEKS:
                return (double) weeks / WEEKS.time;
            case MONTHS:
                return (double) weeks / MONTHS.time;
            case YEARS:
                return (double) weeks / YEARS.time;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static double monthsToTime(long months, Time time) {
        switch (time) {
            case SECONDS:
                return (double) months / SECONDS.time;
            case MINUTES:
                return (double) months / MINUTES.time;
            case HOURS:
                return (double) months / HOURS.time;
            case DAYS:
                return (double) months / DAYS.time;
            case WEEKS:
                return (double) months / WEEKS.time;
            case MONTHS:
                return (double) months / MONTHS.time;
            case YEARS:
                return (double) months / YEARS.time;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static double yearsToTime(long years, Time time) {
        switch (time) {
            case SECONDS:
                return (double) years / SECONDS.time;
            case MINUTES:
                return (double) years / MINUTES.time;
            case HOURS:
                return (double) years / HOURS.time;
            case DAYS:
                return (double) years / DAYS.time;
            case WEEKS:
                return (double) years / WEEKS.time;
            case MONTHS:
                return (double) years / MONTHS.time;
            case YEARS:
                return (double) years / YEARS.time;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static double secToTime(long seconds, Time time) {
        switch (time) {
            case SECONDS:
                return (double) seconds / SECONDS.time;
            case MINUTES:
                return (double) seconds / MINUTES.time;
            case HOURS:
                return (double) seconds / HOURS.time;
            case DAYS:
                return (double) seconds / DAYS.time;
            case WEEKS:
                return (double) seconds / WEEKS.time;
            case MONTHS:
                return (double) seconds / MONTHS.time;
            case YEARS:
                return (double) seconds / YEARS.time;
            default:
                throw new IllegalArgumentException();
        }
    }
}

