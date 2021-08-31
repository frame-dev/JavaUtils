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

    SECONDS(1, 60, 3600, 86400),
    MINUTES(60, 1, 60, 1440),
    HOURS(3600, 0.0166667, 60 / 60.0, 24),
    DAYS(86400, 0.000694444, 0.0416666, 1),
    WEEKS(604800, 0.0000992063, 0.00595238, 0.142857),
    MONTHS(WEEKS.second * 4, 0.00002283, WEEKS.hour / 4, 0.03287671),
    YEARS(31536000, 0.00000190259, 0.000114155, 0.00273973);

    private final long second;
    private final double minute;
    private final double hour;
    private final double day;

    Time(long second, double minute, double hour, double day) {
        this.second = second;
        this.minute = minute;
        this.hour = hour;
        this.day = day;
    }

    public double getDay() {
        return day;
    }

    public double getMinute() {
        return minute;
    }

    public double getHour() {
        return hour;
    }

    public long getSecond() {
        return second;
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
                return WEEKS.second * 4;
            case YEARS:
                return 31536000;
            default:
                throw new IllegalStateException("Unexpected value: " + time);
        }
    }

    public static double minToTime(long minutes, Time time) {
        switch (time) {
            case SECONDS:
                return (double) minutes / SECONDS.second;
            case MINUTES:
                return (double) minutes / MINUTES.second;
            case HOURS:
                return (double) minutes / HOURS.second;
            case DAYS:
                return (double) minutes / DAYS.second;
            case WEEKS:
                return (double) minutes / WEEKS.second;
            case MONTHS:
                return (double) minutes / MONTHS.second;
            case YEARS:
                return (double) minutes / YEARS.second;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static double hourToTime(long hours, Time time) {
        switch (time) {
            case SECONDS:
                return (double) hours / SECONDS.second;
            case MINUTES:
                return (double) hours / MINUTES.second;
            case HOURS:
                return (double) hours / HOURS.second;
            case DAYS:
                return (double) hours / DAYS.second;
            case WEEKS:
                return (double) hours / WEEKS.second;
            case MONTHS:
                return (double) hours / MONTHS.second;
            case YEARS:
                return (double) hours / YEARS.second;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static double dayToTime(long days, Time time) {
        switch (time) {
            case SECONDS:
                return (double) days / SECONDS.second;
            case MINUTES:
                return (double) days / MINUTES.second;
            case HOURS:
                return (double) days / HOURS.second;
            case DAYS:
                return (double) days / DAYS.second;
            case WEEKS:
                return (double) days / WEEKS.second;
            case MONTHS:
                return (double) days / MONTHS.second;
            case YEARS:
                return (double) days / YEARS.second;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static double weeksToTime(long weeks, Time time) {
        switch (time) {
            case SECONDS:
                return (double) weeks / SECONDS.second;
            case MINUTES:
                return (double) weeks / MINUTES.second;
            case HOURS:
                return (double) weeks / HOURS.second;
            case DAYS:
                return (double) weeks / DAYS.second;
            case WEEKS:
                return (double) weeks / WEEKS.second;
            case MONTHS:
                return (double) weeks / MONTHS.second;
            case YEARS:
                return (double) weeks / YEARS.second;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static double monthsToTime(long months, Time time) {
        switch (time) {
            case SECONDS:
                return (double) months / SECONDS.second;
            case MINUTES:
                return (double) months / MINUTES.second;
            case HOURS:
                return (double) months / HOURS.second;
            case DAYS:
                return (double) months / DAYS.second;
            case WEEKS:
                return (double) months / WEEKS.second;
            case MONTHS:
                return (double) months / MONTHS.second;
            case YEARS:
                return (double) months / YEARS.second;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static double yearsToTime(long years, Time time) {
        switch (time) {
            case SECONDS:
                return (double) years / SECONDS.second;
            case MINUTES:
                return (double) years / MINUTES.second;
            case HOURS:
                return (double) years / HOURS.second;
            case DAYS:
                return (double) years / DAYS.second;
            case WEEKS:
                return (double) years / WEEKS.second;
            case MONTHS:
                return (double) years / MONTHS.second;
            case YEARS:
                return (double) years / YEARS.second;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static double secToTime(long seconds, Time time) {
        switch (time) {
            case SECONDS:
                return (double) seconds / SECONDS.second;
            case MINUTES:
                return (double) seconds / MINUTES.second;
            case HOURS:
                return (double) seconds / HOURS.second;
            case DAYS:
                return (double) seconds / DAYS.second;
            case WEEKS:
                return (double) seconds / WEEKS.second;
            case MONTHS:
                return (double) seconds / MONTHS.second;
            case YEARS:
                return (double) seconds / YEARS.second;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return "Time : " + this.name() + " seconds : " + second;
    }
}

