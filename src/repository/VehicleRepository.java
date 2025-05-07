package repository;

import model.entities.Vehicle;
import util.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {
    private List<Vehicle> listVehicle;

    public VehicleRepository() {
        listVehicle = new ArrayList<>();
        listVehicle = FileUtils.loadFromFileVehicle();
    }

    public void Save(Vehicle vehicle) {
        listVehicle.add(vehicle);
        FileUtils.saveToFileVehicle(listVehicle);
    }

    public List<Vehicle> findAll() {
        return new ArrayList<>(listVehicle);
    }

    public Vehicle findByPlate(String plate) {
        return listVehicle.stream().filter(v -> v.getPlate().equals(plate)).findFirst().orElse(null);
    }
}
