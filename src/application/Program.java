package application;

import exceptions.ServiceException;
import model.entities.*;
import model.enuns.TypeVehicle;
import services.RegisterClientService;
import services.RegisterRentService;
import services.RegisterVehicleService;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        RegisterClientService registerClientService = new RegisterClientService();
        RegisterVehicleService registerVehicleService = new RegisterVehicleService();
        RegisterRentService registerRentService = new RegisterRentService();

        int option = 0;

        do {
            try {
                System.out.println("-- Sistema de Gerenciamento de Aluguel -- ");
                System.out.println("1. Cadastrar Cliente");
                System.out.println("2. Cadastrar Veículo");
                System.out.println("3. Realizar Aluguel");
                System.out.println("4. Consultar Veículos Disponíveis");
                System.out.println("5. Listar Aluguéis Realizados");
                System.out.println("0. Sair");

                System.out.print("\nEscolha uma opção: ");
                option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1:
                        registerClient(sc, registerClientService);
                        break;
                    case 2:
                        registerVehicle(sc, registerVehicleService);
                        break;
                    case 3:
                        registerRent(sc, registerClientService, registerVehicleService, registerRentService);
                        break;
                    case 4:
                        RegisterVehicleService vehicleService = new RegisterVehicleService();
                        vehicleService.getAllVehicle();
                        break;
                    case 5:
                        RegisterRentService rentService = new RegisterRentService();
                        rentService.getAllRented();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (ServiceException e) {
                System.out.println("\n" + e.getMessage() + "\n");
            } catch (InputMismatchException e) {
                System.out.println("\nDados digitados incorretamente.\n");
                sc.nextLine();
                option = -1;
            }
        } while (option != 0);
    }

    public static void registerClient(Scanner sc, RegisterClientService registerClientService) {
        System.out.println("Cadastro de clientes: ");

        System.out.print("Id: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Nome: ");
        String name = sc.nextLine();

        System.out.print("Cpf: ");
        String cpf = sc.nextLine();

        System.out.print("Telefone: ");
        String telephone = sc.nextLine();

        Client client = new Client(id, name, cpf, telephone);

        registerClientService.registerClient(client);
    }

    public static void registerVehicle(Scanner sc, RegisterVehicleService registerVehicleService) {
        System.out.println("Cadastro de veículos: ");

        System.out.println("Deseja cadastrar carro ou moto?");
        TypeVehicle typeVehicle = TypeVehicle.valueOf(sc.nextLine().toUpperCase());

        System.out.print("Placa: ");
        String plate = sc.nextLine();

        System.out.print("Modelo: ");
        String model = sc.nextLine();

        System.out.print("Marca: ");
        String brand = sc.nextLine();

        System.out.print("Ano: ");
        int year = sc.nextInt();

        System.out.print("Valor diaria: ");
        double valueDaily = sc.nextDouble();

        if (typeVehicle.equals(TypeVehicle.CARRO)) {
            System.out.print("Número de portas: ");
            int numberDoors = sc.nextInt();

            System.out.print("Capacidade porta malas: ");
            int capacityTrunk = sc.nextInt();

            Vehicle car = new Car(plate, model, brand, year, valueDaily, typeVehicle, numberDoors, capacityTrunk);
            registerVehicleService.registerVehicle(car);
        } else {
            System.out.print("Cilindradas: ");
            int cylinder = sc.nextInt();

            Vehicle motorcycle = new Motorcycle(plate, model, brand, year, valueDaily, typeVehicle, cylinder);
            registerVehicleService.registerVehicle(motorcycle);
        }
    }

    public static void registerRent(Scanner sc, RegisterClientService registerClientService, RegisterVehicleService registerVehicleService, RegisterRentService registerRentService) {
        System.out.println("Realizaçãao de aluguel: ");

        System.out.print("Id cliente: ");
        int idClient = sc.nextInt();
        sc.nextLine();

        System.out.print("Placa veiculo: ");
        String plateVeiculo = sc.nextLine();

        if (registerClientService.existClient(idClient) && registerVehicleService.existPlate(plateVeiculo)) {
            System.out.print("Id aluguel: ");
            int idRent = sc.nextInt();
            sc.nextLine();

            Client client = registerClientService.getClient(idClient);
            Vehicle vehicle = registerVehicleService.getPlate(plateVeiculo);

            LocalDate localDate = LocalDate.now();

            System.out.print("Quantos dias de aluguel? ");
            int daysRented = sc.nextInt();
            sc.nextLine();

            Rent rent = new Rent(idRent, client, vehicle, localDate, daysRented);
            registerRentService.RegisterRent(rent);
        }
    }
}
