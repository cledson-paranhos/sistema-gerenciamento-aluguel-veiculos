package model.entities;

import model.enuns.TypeVehicle;

public final class Car extends Vehicle {
    private Integer numberDoors;
    private Integer capacityTrunk;

    public Car() {
        super();
    }

    public Car(String plate, String model, String brand, Integer year, Double valueDaily, TypeVehicle typeVehicle, Integer numberDoors, Integer capacityTrunk) {
        super(plate, model, brand, year, valueDaily, typeVehicle);
        this.numberDoors = numberDoors;
        this.capacityTrunk = capacityTrunk;
    }

    public Integer getNumberDoors() {
        return numberDoors;
    }

    public Integer getCapacityTrunk() {
        return capacityTrunk;
    }

    @Override
    public Double calculateRent(int days) {
        return (days * 100.0) + 10.0;
    }
}
