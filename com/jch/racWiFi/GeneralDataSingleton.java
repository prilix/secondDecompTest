package com.jch.racWiFi;

public class GeneralDataSingleton {
    private static final GeneralDataSingleton ourInstance = new GeneralDataSingleton();

    public static GeneralDataSingleton getInstance() {
        return ourInstance;
    }

    private GeneralDataSingleton() {
    }
}
