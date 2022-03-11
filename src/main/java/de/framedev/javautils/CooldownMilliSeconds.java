package de.framedev.javautils;

/**
 * / This Plugin was Created by FrameDev
 * / Package : de.framedev.javautils
 * / ClassName CooldownMilliSeconds
 * / Date: 05.03.22
 * / Project: JavaUtils
 * / Copyrighted by FrameDev
 */

public class CooldownMilliSeconds {
    private final int id;
    private final long millisecs;
    private long millisecondsLeft;
    private long milliSeconds;
    private final long actualTime;

    public CooldownMilliSeconds(int id, long millisecs, long actualTime) {
        this.id = id;
        this.millisecs = millisecs;
        this.actualTime = actualTime;
    }

    public CooldownMilliSeconds(int id, long millisecs) {
        this.id = id;
        this.millisecs = millisecs;
        this.actualTime = System.currentTimeMillis();
    }

    public long getMilliSecondsLeft() {
        return millisecondsLeft;
    }

    public long getMillisecs() {
        return millisecs;
    }

    public long getMilliSeconds() {
        return milliSeconds;
    }

    public int getId() {
        return id;
    }

    public boolean check() {
        millisecondsLeft = (actualTime + millisecs) - System.currentTimeMillis();
        milliSeconds = actualTime + millisecs - System.currentTimeMillis();
        return millisecondsLeft <= 0;
    }

    public boolean isExpired() {
        return check();
    }

    public void sendInformations() {
        APIs.utils.getLogger().info("ID : " + id);
        APIs.utils.getLogger().info("Seconds Left : " + millisecondsLeft);
        APIs.utils.getLogger().info("Is Expired : " + isExpired());
    }
}