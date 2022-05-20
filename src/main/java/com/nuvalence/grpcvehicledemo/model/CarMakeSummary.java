package com.nuvalence.grpcvehicledemo.model;


import java.util.List;

public class CarMakeSummary {

    private List<String> vehicleTypes;
    private List<String> models;
    private List<Manufacturer> manufacturers;

    public CarMakeSummary(List<String> vehicleTypes, List<String> models, List<Manufacturer> manufacturers) {
        this.vehicleTypes = vehicleTypes;
        this.models = models;
        this.manufacturers = manufacturers;
    }

    public List<String> getVehicleTypes() {
        return vehicleTypes;
    }

    public void setVehicleTypes(List<String> vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }

    public List<String> getModels() {
        return models;
    }

    public void setModels(List<String> models) {
        this.models = models;
    }

    public List<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(List<Manufacturer> manufacturers) {
        this.manufacturers = manufacturers;
    }

    @Override
    public String toString() {
        return "CarMakeSummary{" +
                "vehicleTypes=" + vehicleTypes +
                ", models=" + models +
                ", manufacturers=" + manufacturers +
                '}';
    }
}


