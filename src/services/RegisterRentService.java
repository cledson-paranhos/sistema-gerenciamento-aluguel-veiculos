package services;

import exceptions.ServiceException;
import model.entities.Rent;
import model.entities.Vehicle;
import repository.RentRepository;

import java.util.List;

public class RegisterRentService {
    private RentRepository rentRepository;

    public RegisterRentService() {
        rentRepository = new RentRepository();
    }

    public void RegisterRent(Rent rent) {
        if (rent.getClient() == null) {
            throw new ServiceException("Cliente inválido!");
        }

        if (rent.getVehicle() == null) {
            throw new ServiceException("Veículo inválido!");
        }

        if (rent.getDateStart() == null) {
            throw new ServiceException("Data inválida");
        }
        rentRepository.save(rent);
    }

    public void getAllRented() {
        List<Rent> listRents = rentRepository.findAll();

        if (listRents.isEmpty()) {
            throw new ServiceException("Nenhum aluguel encontrado.");
        } else {
            String strList = "";

            for (Rent rent : listRents) {
                strList = strList + rent + "\n";
            }
            System.out.println(strList);
        }
    }
}
