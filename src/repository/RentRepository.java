package repository;

import model.entities.Rent;
import util.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class RentRepository {
    private List<Rent> listRent;

    public RentRepository() {
        listRent = new ArrayList<>();
        listRent = FileUtils.loadFromFileRented();
    }

    public void save(Rent rent) {
        listRent.add(rent);
        FileUtils.saveToFileRented(listRent);
    }

    public List<Rent> findAll() {
        return new ArrayList<>(listRent);
    }
}
