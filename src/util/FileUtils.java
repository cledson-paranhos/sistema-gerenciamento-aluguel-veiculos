package util;

import model.entities.*;
import model.enuns.TypeVehicle;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    private final static String filePathClient = System.getProperty("user.dir") + "\\cadastros\\clients.csv";
    private final static String filePathVehicle = System.getProperty("user.dir") + "\\cadastros\\vehicle.csv";
    private final static String filePathRented = System.getProperty("user.dir") + "\\cadastros\\rented.csv";

    public static void saveToFileClient(List<Client> clients) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathClient))) {
            for (Client client : clients) {
                writer.write(client.toString());
                writer.newLine();
            }

            System.out.println("Cliente cadastrado com sucesso!");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar os dados no arquivo: " + e.getMessage());
        }
    }

    public static List<Client> loadFromFileClient() {
        List<Client> listClients = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathClient))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 4) {
                    Integer id = Integer.parseInt(fields[0]);
                    String name = fields[1].trim();
                    String cpf = fields[2].trim();
                    String telephone = fields[3].trim();

                    listClients.add(new Client(id, name, cpf, telephone));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar os dados do arquivo: " + e.getMessage());
        }
        return listClients;
    }

    public static void saveToFileVehicle(List<Vehicle> vehicles) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathVehicle))) {
            for (Vehicle vehicle : vehicles) {
                if (vehicle instanceof Car) {
                    writer.write(vehicle.toString() + "," + ((Car) vehicle).getNumberDoors() + "," + ((Car) vehicle).getCapacityTrunk());
                } else if (vehicle instanceof Motorcycle) {
                    writer.write(vehicle.toString() + "," + ((Motorcycle) vehicle).getCylinder());
                }
                writer.newLine();
            }
            System.out.println("\nVeículo cadastrado com sucesso!\n");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar os dados no arquivo: " + e.getMessage());
        }
    }

    public static List<Vehicle> loadFromFileVehicle() {
        List<Vehicle> listVehicle = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathVehicle))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                String plate = fields[0];
                String model = fields[1];
                String brand = fields[2];
                Integer year = Integer.parseInt(fields[3]);
                Double valueDaily = Double.parseDouble(fields[4]);
                TypeVehicle typeVehicle = TypeVehicle.valueOf(fields[5]);

                if (typeVehicle.equals(TypeVehicle.CARRO)) {
                    Integer numberDoors = Integer.parseInt(fields[6]);
                    Integer capacityTrunk = Integer.parseInt(fields[7]);

                    listVehicle.add(new Car(plate, model, brand, year, valueDaily, typeVehicle, numberDoors, capacityTrunk));
                } else {
                    Integer cylinder = Integer.parseInt(fields[6]);

                    listVehicle.add(new Motorcycle(plate, model, brand, year, valueDaily, typeVehicle, cylinder));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar os dados do arquivo: " + e.getMessage());
        }
        return listVehicle;
    }

    public static void saveToFileRented(List<Rent> rents) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathRented))) {
            for (Rent rent : rents) {
                writer.write(rent.toString());
                writer.newLine();
            }

            System.out.println("\nAluguel realizado com sucesso!\n");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar os dados no arquivo: " + e.getMessage());
        }
    }

    public static List<Rent> loadFromFileRented() {
        List<Rent> listRent = new ArrayList<>();

        Rent rent;
        Vehicle vehicle;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathRented))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                Integer id = Integer.parseInt(fields[0]);
                Integer idClient = Integer.parseInt(fields[1]);
                String plateVehicle = fields[2];
                TypeVehicle typeVehicle = TypeVehicle.valueOf(fields[3]);
                LocalDate localDate = LocalDate.parse(fields[4]);
                Integer daysRented = Integer.parseInt(fields[5]);

                Client client = new Client(idClient, "", "", "");

                if (typeVehicle.equals(TypeVehicle.CARRO)) {
                    vehicle = new Car(plateVehicle, "", "", 0, 0.0, typeVehicle, 0, 0);
                } else {
                    vehicle = new Motorcycle(plateVehicle, "", "", 0, 0.0, typeVehicle, 0);
                }
                rent = new Rent(id, client, vehicle, localDate, daysRented);

                listRent.add(rent);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar os dados do arquivo: " + e.getMessage());
        }
        return listRent;
    }
}
