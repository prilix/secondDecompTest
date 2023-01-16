package com.jch.racWiFi.energyConsumption.model.local;

import java.util.Comparator;

public class CurrencyModel implements Comparator<CurrencyModel> {
    private String code = "";
    private String name = "";
    private String symbol = "";

    public CurrencyModel(String str, String str2, String str3) {
        this.code = str;
        this.name = str2;
        this.symbol = str3;
    }

    public CurrencyModel() {
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String str) {
        this.symbol = str;
    }

    public int compare(CurrencyModel currencyModel, CurrencyModel currencyModel2) {
        return currencyModel.getName().compareTo(currencyModel2.name);
    }

    public String toString() {
        return "CurrencyModel{code='" + this.code + '\'' + ", name='" + this.name + '\'' + ", symbol='" + this.symbol + '\'' + '}';
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CurrencyModel)) {
            return false;
        }
        CurrencyModel currencyModel = (CurrencyModel) obj;
        if (!this.code.equals(currencyModel.code) || !this.name.equals(currencyModel.name) || !this.symbol.equals(currencyModel.symbol)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.code.hashCode() + this.name.hashCode() + this.symbol.hashCode();
    }
}
