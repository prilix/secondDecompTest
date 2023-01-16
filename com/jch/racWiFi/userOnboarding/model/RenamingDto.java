package com.jch.racWiFi.userOnboarding.model;

public class RenamingDto {
    String name;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public RenamingDto() {
    }

    public RenamingDto(String str) {
        this.name = str;
    }
}
