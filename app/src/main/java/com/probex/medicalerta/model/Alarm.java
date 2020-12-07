package com.probex.medicalerta.model;


import java.io.Serializable;

public class Alarm implements Serializable {
    private String medName;
    private int medId;
    private int iconId;
    private long lastAlarmTime;
    private long currentAlarmTime;
    private int interval;
    private long initialDate;
    private long finalDate;

    public Alarm(String medName, int iconId, long lastAlarmTime, long currentAlarmTime, int interval, long initialDate, long finalDate) {
        this.medName = medName;
        this.iconId = iconId;
        this.lastAlarmTime = lastAlarmTime;
        this.currentAlarmTime = currentAlarmTime;
        this.interval = interval;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    public Alarm(String medName, int iconId, long lastAlarmTime, int interval, long initialDate, long finalDate) {
        this.medName = medName;
        this.iconId = iconId;
        this.lastAlarmTime = lastAlarmTime;
        this.interval = interval;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }


    public Alarm(String medName, int medId, int iconId, long lastAlarmTime, long currentAlarmTime, int interval, long initialDate, long finalDate) {
        this.medName = medName;
        this.medId = medId;
        this.iconId = iconId;
        this.lastAlarmTime = lastAlarmTime;
        this.currentAlarmTime = currentAlarmTime;
        this.interval = interval;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    public int getMedId() {
        return medId;
    }

    public void setMedId(int medId) {
        this.medId = medId;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public long getLastAlarmTime() {
        return lastAlarmTime;
    }

    public void setLastAlarmTime(long lastAlarmTime) {
        this.lastAlarmTime = lastAlarmTime;
    }

    public long getCurrentAlarmTime() {
        return currentAlarmTime;
    }

    public void setCurrentAlarmTime(long currentAlarmTime) {
        this.currentAlarmTime = currentAlarmTime;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public long getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(long initialDate) {
        this.initialDate = initialDate;
    }

    public long getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(long finalDate) {
        this.finalDate = finalDate;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "medName='" + medName + '\'' +
                ", medId=" + medId +
                ", iconId=" + iconId +
                ", lastAlarmTime=" + lastAlarmTime +
                ", currentAlarmTime=" + currentAlarmTime +
                ", interval=" + interval +
                ", initialDate=" + initialDate +
                ", finalDate=" + finalDate +
                '}';
    }
}