package model.entities;

import java.time.LocalDate;

public class Rent {
    private Integer id;
    private Client client;
    private Vehicle vehicle;
    private LocalDate dateStart;
    private Integer daysRented;

    public Rent() {
    }

    public Rent(Integer id, Client client, Vehicle vehicle, LocalDate dateStart, Integer daysRented) {
        this.id = id;
        this.client = client;
        this.vehicle = vehicle;
        this.dateStart = dateStart;
        this.daysRented = daysRented;
    }

    public Integer getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public Integer getDaysRented() {
        return daysRented;
    }

    public Double totalValue() {
        return vehicle.calculateRent(daysRented);
    }

    @Override
    public String toString() {
        return id + "," +
                client.getId() + "," +
                vehicle.getPlate() + "," +
                vehicle.getTypeVehicle() + "," +
                dateStart + "," +
                daysRented + "," +
                totalValue();
    }
}
