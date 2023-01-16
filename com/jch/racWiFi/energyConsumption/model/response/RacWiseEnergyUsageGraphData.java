package com.jch.racWiFi.energyConsumption.model.response;

import java.util.HashMap;

public class RacWiseEnergyUsageGraphData {
    private int APR;
    private int AUG;
    private int DEC;
    private int FEB;
    private int JAN;
    private int JUL;
    private int JUN;
    private int MAR;
    private int MAY;
    private int NOV;
    private int OCT;
    private int SEP;
    private HashMap<String, Integer> energyConsumedValue;

    public int getJAN() {
        return this.JAN;
    }

    public void setJAN(int i) {
        this.JAN = i;
    }

    public int getFEB() {
        return this.FEB;
    }

    public void setFEB(int i) {
        this.FEB = i;
    }

    public int getMAR() {
        return this.MAR;
    }

    public void setMAR(int i) {
        this.MAR = i;
    }

    public int getAPR() {
        return this.APR;
    }

    public void setAPR(int i) {
        this.APR = i;
    }

    public int getMAY() {
        return this.MAY;
    }

    public void setMAY(int i) {
        this.MAY = i;
    }

    public int getJUN() {
        return this.JUN;
    }

    public void setJUN(int i) {
        this.JUN = i;
    }

    public int getJUL() {
        return this.JUL;
    }

    public void setJUL(int i) {
        this.JUL = i;
    }

    public int getAUG() {
        return this.AUG;
    }

    public void setAUG(int i) {
        this.AUG = i;
    }

    public int getSEP() {
        return this.SEP;
    }

    public void setSEP(int i) {
        this.SEP = i;
    }

    public int getOCT() {
        return this.OCT;
    }

    public void setOCT(int i) {
        this.OCT = i;
    }

    public int getNOV() {
        return this.NOV;
    }

    public void setNOV(int i) {
        this.NOV = i;
    }

    public int getDEC() {
        return this.DEC;
    }

    public void setDEC(int i) {
        this.DEC = i;
    }

    public HashMap<String, Integer> getEnergyConsumedValue() {
        return this.energyConsumedValue;
    }

    public void setEnergyConsumedValue(HashMap<String, Integer> hashMap) {
        this.energyConsumedValue = hashMap;
    }

    public String toString() {
        return "ClassPojo [JUL = " + this.JUL + ", OCT = " + this.OCT + ", FEB = " + this.FEB + ", JUN = " + this.JUN + ", APR = " + this.APR + ", AUG = " + this.AUG + ", DEC = " + this.DEC + ", MAY = " + this.MAY + ", NOV = " + this.NOV + ", JAN = " + this.JAN + ", MAR = " + this.MAR + ", SEP = " + this.SEP + "]";
    }
}
