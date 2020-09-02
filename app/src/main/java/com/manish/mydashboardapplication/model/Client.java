package com.manish.mydashboardapplication.model;

public class Client {
    long clientNumber;
    String propertyName;
    String cityName;
    String area;
    String ownersName;
    String language;

    public Client(long clientNumber, String propertyName, String cityName, String area, String ownersName, String language) {
        this.clientNumber = clientNumber;
        this.propertyName = propertyName;
        this.cityName = cityName;
        this.area = area;
        this.ownersName = ownersName;
        this.language = language;
    }
    public Client (){

    }

    public long getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(long clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getOwnersName() {
        return ownersName;
    }

    public void setOwnersName(String ownersName) {
        this.ownersName = ownersName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
