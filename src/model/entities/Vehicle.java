package model.entities;

import model.enuns.TypeVehicle;

public abstract class Vehicle {
    private String plate;
    private String model;
    private String brand;
    private Integer year;
    private Double valueDaily;
    private TypeVehicle typeVehicle;

    public Vehicle() {
    }

    public Vehicle(String plate, String model, String brand, Integer year, Double valueDaily, TypeVehicle typeVehicle) {
        this.plate = plate;
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.valueDaily = valueDaily;
        this.typeVehicle = typeVehicle;
    }

    public String getPlate() {
        return plate;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public Integer getYear() {
        return year;
    }

    public Double getValueDaily() {
        return valueDaily;
    }

    public TypeVehicle getTypeVehicle() {
        return typeVehicle;
    }

    public abstract Double calculateRent(int days);

    @Override
    public String toString() {
        return plate + "," +
                model + "," +
                brand + "," +
                year + "," +
                valueDaily + "," +
                typeVehicle;
    }
}
