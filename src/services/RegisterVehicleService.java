package services;

import exceptions.ServiceException;
import model.entities.Vehicle;
import repository.VehicleRepository;

import java.util.List;

public class RegisterVehicleService {
    private VehicleRepository vehicleRepository;

    public RegisterVehicleService() {
        vehicleRepository = new VehicleRepository();
    }

    public void registerVehicle(Vehicle vehicle) {
        if (vehicle.getPlate().isEmpty() || vehicle.getPlate() == null) {
            throw new ServiceException("Veiculo deve ter uma placa válida!");
        }
        if (vehicle.getModel().isEmpty() || vehicle.getModel() == null) {
            throw new ServiceException("Veiculo deve ter um modelo válido!");
        }

        if (vehicle.getBrand().isEmpty() || vehicle.getBrand() == null) {
            throw new ServiceException("Veiculo deve ter uma marca válida!");
        }

        vehicleRepository.Save(vehicle);
    }

    public void getAllVehicle() {
        List<Vehicle> listVehicles = vehicleRepository.findAll();

        if (listVehicles.isEmpty()) {
            throw new ServiceException("Nenhum veículo encontrado.");
        } else {
            String strlist = "";

            for (Vehicle vehicle : listVehicles) {
                strlist = strlist + vehicle + "\n";
            }
            System.out.println(strlist);
        }
    }

    public boolean existPlate(String plate) {
        Vehicle vehicle = vehicleRepository.findByPlate(plate);

        if (vehicle == null) {
            throw new ServiceException("Nenhum veiculo encontrado com essa placa, tente novamente!");
        }

        return true;
    }

    public Vehicle getPlate(String plate) {
        Vehicle vehicle = vehicleRepository.findByPlate(plate);

        if (vehicle == null) {
            throw new ServiceException("Nenhum veiculo encontrado com essa placa, tente novamente!");
        }

        return vehicle;
    }
}
