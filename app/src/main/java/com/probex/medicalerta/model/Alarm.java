package com.probex.medicalerta.model;

import java.util.Date;

public class Alarm {
    private String alarmId;
    private String idMed;
    private Date initialDate;
    private Date finalDate;
    private Date lastAlarm;
    private int interval;

    @Override
    public String toString() {
        return "Alarm{" +
                "alarmId='" + alarmId + '\'' +
                ", idMed='" + idMed + '\'' +
                ", initialDate=" + initialDate +
                ", finalDate=" + finalDate +
                ", lastAlarm=" + lastAlarm +
                ", interval=" + interval +
                '}';
    }

    public Alarm(String alarmId, String idMed, Date initialDate, Date finalDate, Date lastAlarm, int interval) {
        this.alarmId = alarmId;
        this.idMed = idMed;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.lastAlarm = lastAlarm;
        this.interval = interval;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public Date getLastAlarm() {
        return lastAlarm;
    }

    public void setLastAlarm(Date lastAlarm) {
        this.lastAlarm = lastAlarm;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
