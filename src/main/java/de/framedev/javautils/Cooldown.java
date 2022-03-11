package de.framedev.javautils;

/**
 * / This Plugin was Created by FrameDev
 * / Package : de.framedev.javautils
 * / ClassName Cooldown
 * / Date: 05.03.22
 * / Project: JavaUtils
 * / Copyrighted by FrameDev
 */

public class Cooldown {

    private final int id;
    private final int seconds;
    private long secondsLeft;
    private long milliSeconds;
    private final long actualTime;

    public Cooldown(int id, int seconds, long actualTime) {
        this.id = id;
        this.seconds = seconds;
        this.actualTime = actualTime;
    }

    public Cooldown(int id, int seconds) {
        this.id = id;
        this.seconds = seconds;
        this.actualTime = System.currentTimeMillis();
    }

    public long getSecondsLeft() {
        return secondsLeft;
    }

    public int getSeconds() {
        return seconds;
    }

    public long getMilliSeconds() {
        return milliSeconds;
    }

    public int getId() {
        return id;
    }

    public boolean check() {
        secondsLeft = ((actualTime / 1000) + seconds) - (System.currentTimeMillis() / 1000);
        milliSeconds = actualTime + (seconds * 1000L) - System.currentTimeMillis();
        return secondsLeft <= 0;
    }

    public boolean isExpired() {
        return check();
    }

    public void sendInformations() {
        APIs.utils.getLogger().info("ID : " + id);
        APIs.utils.getLogger().info("Seconds Left : " + secondsLeft);
        APIs.utils.getLogger().info("Is Expired : " + isExpired());
    }
}