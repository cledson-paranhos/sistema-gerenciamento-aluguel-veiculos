package services;

import model.entities.Client;
import repository.ClientRepository;
import exceptions.ServiceException;

import java.util.List;

public class RegisterClientService {
    private final ClientRepository clientRepository;

    public RegisterClientService() {
        this.clientRepository = new ClientRepository();
    }

    public void registerClient(Client client) {
        if (client.getName() == null || client.getName().isEmpty()) {
            throw new ServiceException("Nome não pode ser vazio!");
        }

        if (client.getCpf() == null || client.getCpf().isEmpty()) {
            throw new ServiceException("Cpf não pode ser vazio!");
        }

        if (clientRepository.findByCpf(client.getCpf()) != null || clientRepository.findById(client.getId()) != null) {
            throw new ServiceException("Cliente já cadastrado");
        }
        clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        List<Client> listClients = clientRepository.findAll();

        if (listClients.isEmpty()) {
            throw new ServiceException("Nenhum cliente encontrado!");
        } else {
            return listClients;
        }
    }

    public boolean existClient(int clientId) {
        Client client = clientRepository.findById(clientId);

        if (client == null) {
            throw new ServiceException("Nenhum cliente encontrado com esse Id, tente novamente!");
        }

        return true;
    }

    public Client getClient(int clientId) {
        Client client = clientRepository.findById(clientId);

        if (client == null) {
            throw new ServiceException("Nenhum cliente encontrado com esse Id, tente novamente!");
        }

        return client;
    }
}
