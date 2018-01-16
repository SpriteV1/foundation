package us.vindere.foundation.utils;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

public class Time {
    public static String getTime(int seconds)
    {
        if (seconds < 60) {
            return seconds + "s";
        }
        int minutes = seconds / 60;
        int s = 60 * minutes;
        int secondsLeft = seconds - s;
        if (minutes < 60)
        {
            if (secondsLeft > 0) {
                return String.valueOf(minutes + "m " + secondsLeft + "s");
            }
            return String.valueOf(minutes + "m");
        }
        if (minutes < 1440)
        {
            String time = "";
            int hours = minutes / 60;
            time = hours + "h";
            int inMins = 60 * hours;
            int leftOver = minutes - inMins;
            if (leftOver >= 1) {
                time = time + " " + leftOver + "m";
            }
            if (secondsLeft > 0) {
                time = time + " " + secondsLeft + "s";
            }
            return time;
        }
        String time = "";
        int days = minutes / 1440;
        time = days + "d";
        int inMins = 1440 * days;
        int leftOver = minutes - inMins;
        if (leftOver >= 1) {
            if (leftOver < 60)
            {
                time = time + " " + leftOver + "m";
            }
            else
            {
                int hours = leftOver / 60;
                time = time + " " + hours + "h";
                int hoursInMins = 60 * hours;
                int minsLeft = leftOver - hoursInMins;
                if (leftOver >= 1) {
                    time = time + " " + minsLeft + "m";
                }
            }
        }
        if (secondsLeft > 0) {
            time = time + " " + secondsLeft + "s";
        }
        return time;
    }
    public static String getLongTime(long seconds)
    {
        if (seconds < 60) {
            return seconds + "s";
        }
        long minutes = seconds / 60;
        long s = 60 * minutes;
        long secondsLeft = seconds - s;
        if (minutes < 60)
        {
            if (secondsLeft > 0) {
                return String.valueOf(minutes + "m " + secondsLeft + "s");
            }
            return String.valueOf(minutes + "m");
        }
        if (minutes < 1440)
        {
            String time = "";
            long hours = minutes / 60;
            time = hours + "h";
            long inMins = 60 * hours;
            long leftOver = minutes - inMins;
            if (leftOver >= 1) {
                time = time + " " + leftOver + "m";
            }
            if (secondsLeft > 0) {
                time = time + " " + secondsLeft + "s";
            }
            return time;
        }
        String time = "";
        long days = minutes / 1440;
        time = days + "d";
        long inMins = 1440 * days;
        long leftOver = minutes - inMins;
        if (leftOver >= 1) {
            if (leftOver < 60)
            {
                time = time + " " + leftOver + "m";
            }
            else
            {
                long hours = leftOver / 60;
                time = time + " " + hours + "h";
                long hoursInMins = 60 * hours;
                long minsLeft = leftOver - hoursInMins;
                if (leftOver >= 1) {
                    time = time + " " + minsLeft + "m";
                }
            }
        }
        if (secondsLeft > 0) {
            time = time + " " + secondsLeft + "s";
        }
        return time;
    }
    public static String getUptime(){
        return getTime((int) TimeUnit.MILLISECONDS.toSeconds(ManagementFactory.getRuntimeMXBean().getUptime()));
    }
}
