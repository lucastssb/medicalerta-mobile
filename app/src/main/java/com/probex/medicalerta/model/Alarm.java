package com.probex.medicalerta.model;


public class Alarm {
    private String medName;
    private String nextAlarmTime;
    private int interval;
    private String initialDate;
    private String finalDate;

    public Alarm(String medName, String nextAlarmTime, int interval, String initialDate, String finalDate) {
        this.medName = medName;
        this.nextAlarmTime = nextAlarmTime;
        this.interval = interval;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getNextAlarmTime() {
        return nextAlarmTime;
    }

    public void setNextAlarmTime(String nextAlarmTime) {
        this.nextAlarmTime = nextAlarmTime;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(String initialDate) {
        this.initialDate = initialDate;
    }

    public String getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(String finalDate) {
        this.finalDate = finalDate;
    }
}
