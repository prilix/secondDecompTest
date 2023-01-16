package com.jch.racWiFi.energyConsumption.model.request.budgetSetUp;

public class ProgressiveUnitModel {
    private String currencyCode;
    private double from;

    /* renamed from: to */
    private double f433to;
    private double unitPrice;

    public double getFrom() {
        return this.from;
    }

    public void setFrom(double d) {
        this.from = d;
    }

    public double getTo() {
        return this.f433to;
    }

    public void setTo(double d) {
        this.f433to = d;
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(double d) {
        this.unitPrice = d;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public void setCurrencyCode(String str) {
        this.currencyCode = str;
    }

    public String toString() {
        return "ProgressiveUnitModel{from=" + this.from + ", to=" + this.f433to + ", unitPrice=" + this.unitPrice + ", currencyCode='" + this.currencyCode + '\'' + '}';
    }
}
