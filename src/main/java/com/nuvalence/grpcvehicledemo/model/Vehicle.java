package com.nuvalence.grpcvehicledemo.model;

public class Vehicle {


    private String id;
    private String make;
    private String model;
    private String manufacturer;
    private int modelYear;
    private String transmission;
    private String transDesc;
    private String driveDesc;
    private int cityFE;
    private int hwyFE;
    private int combFE;
    private double cityFEU;
    private double hwyFEU;
    private double combFEU;

    public Vehicle(String id, String make, String model, String manufacturer, int modelYear, String transmission, String transDesc, String driveDesc, int cityFE, int hwyFE, int combFE, double cityFEU, double hwyFEU, double combFEU) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.manufacturer = manufacturer;
        this.modelYear = modelYear;
        this.transmission = transmission;
        this.transDesc = transDesc;
        this.driveDesc = driveDesc;
        this.cityFE = cityFE;
        this.hwyFE = hwyFE;
        this.combFE = combFE;
        this.cityFEU = cityFEU;
        this.hwyFEU = hwyFEU;
        this.combFEU = combFEU;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getTransDesc() {
        return transDesc;
    }

    public void setTransDesc(String transDesc) {
        this.transDesc = transDesc;
    }

    public String getDriveDesc() {
        return driveDesc;
    }

    public void setDriveDesc(String driveDesc) {
        this.driveDesc = driveDesc;
    }

    public int getCityFE() {
        return cityFE;
    }

    public void setCityFE(int cityFE) {
        this.cityFE = cityFE;
    }

    public int getHwyFE() {
        return hwyFE;
    }

    public void setHwyFE(int hwyFE) {
        this.hwyFE = hwyFE;
    }

    public int getCombFE() {
        return combFE;
    }

    public void setCombFE(int combFE) {
        this.combFE = combFE;
    }

    public double getCityFEU() {
        return cityFEU;
    }

    public void setCityFEU(double cityFEU) {
        this.cityFEU = cityFEU;
    }

    public double getHwyFEU() {
        return hwyFEU;
    }

    public void setHwyFEU(double hwyFEU) {
        this.hwyFEU = hwyFEU;
    }

    public double getCombFEU() {
        return combFEU;
    }

    public void setCombFEU(double combFEU) {
        this.combFEU = combFEU;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id='" + id + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", modelYear=" + modelYear +
                ", transmission='" + transmission + '\'' +
                ", transDesc='" + transDesc + '\'' +
                ", driveDesc='" + driveDesc + '\'' +
                ", cityFE=" + cityFE +
                ", hwyFE=" + hwyFE +
                ", combFE=" + combFE +
                ", cityFEU=" + cityFEU +
                ", hwyFEU=" + hwyFEU +
                ", combFEU=" + combFEU +
                '}';
    }
}
