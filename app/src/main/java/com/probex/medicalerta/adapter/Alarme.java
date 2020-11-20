package com.probex.medicalerta.adapter;

import android.os.Parcel;
import android.os.Parcelable;

public class Alarme implements Parcelable {

    private int id_alarme;
    private int id_med;
    private long data_inicial;
    private long data_final;
    private long ultimo_alarme;
    private int intervalo;

    public Alarme(){

    }

    public Alarme(int id_med, long data_inicial, long data_final, long ultimo_alarme, int intervalo) {
        this.id_med = id_med;
        this.data_inicial = data_inicial;
        this.data_final = data_final;
        this.ultimo_alarme = ultimo_alarme;
        this.intervalo = intervalo;
    }

    public Alarme(int id_alarme, int id_med, long data_inicial, long data_final, long ultimo_alarme, int intervalo) {
        this.id_alarme = id_alarme;
        this.id_med = id_med;
        this.data_inicial = data_inicial;
        this.data_final = data_final;
        this.ultimo_alarme = ultimo_alarme;
        this.intervalo = intervalo;
    }

    protected Alarme(Parcel in) {
        id_alarme = in.readInt();
        id_med = in.readInt();
        data_inicial = in.readLong();
        data_final = in.readLong();
        ultimo_alarme = in.readLong();
        intervalo = in.readInt();
    }

    public static final Creator<Alarme> CREATOR = new Creator<Alarme>() {
        @Override
        public Alarme createFromParcel(Parcel in) {
            return new Alarme(in);
        }

        @Override
        public Alarme[] newArray(int size) {
            return new Alarme[size];
        }
    };

    public int getId_alarme() {
        return id_alarme;
    }

    public void setId_alarme(int id_alarme) {
        this.id_alarme = id_alarme;
    }

    public int getId_med() {
        return id_med;
    }

    public void setId_med(int id_med) {
        this.id_med = id_med;
    }

    public long getData_inicial() {
        return data_inicial;
    }

    public void setData_inicial(long data_inicial) {
        this.data_inicial = data_inicial;
    }

    public long getData_final() {
        return data_final;
    }

    public void setData_final(long data_final) {
        this.data_final = data_final;
    }

    public long getUltimo_alarme() {
        return ultimo_alarme;
    }

    public void setUltimo_alarme(long ultimo_alarme) {
        this.ultimo_alarme = ultimo_alarme;
    }

    public int getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id_alarme);
        parcel.writeInt(id_med);
        parcel.writeLong(data_inicial);
        parcel.writeLong(data_final);
        parcel.writeLong(ultimo_alarme);
        parcel.writeInt(intervalo);
    }
}
