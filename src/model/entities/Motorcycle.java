package model.entities;

import model.enuns.TypeVehicle;

public class Motorcycle extends Vehicle {
    private Integer cylinder;

    public Motorcycle() {
    }

    public Motorcycle(String plate, String model, String brand, Integer year, Double valueDaily, TypeVehicle typeVehicle, Integer cylinder) {
        super(plate, model, brand, year, valueDaily, typeVehicle);
        this.cylinder = cylinder;
    }

    public Integer getCylinder() {
        return cylinder;
    }

    @Override
    public Double calculateRent(int days) {
        return (days * 100.0) + 5.0;
    }
}
