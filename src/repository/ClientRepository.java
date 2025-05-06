package repository;

import model.entities.Client;
import util.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientRepository {
    private List<Client> listClient;

    public ClientRepository() {
        if (listClient == null) {
            listClient = new ArrayList<>();
        }
        listClient = FileUtils.loadFromFileClient();
    }

    public void save(Client client) {
        listClient.add(client);
        FileUtils.saveToFileClient(listClient);
    }

    public List<Client> findAll() {
        return new ArrayList<>(listClient);
    }

    public Client findByCpf(String cpf) {
        return listClient.stream().filter(c -> c.getCpf().equals(cpf)).findFirst().orElse(null);
    }

    public Client findById(Integer id) {
        return listClient.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    public boolean deleteCliente(String cpf) {
        boolean removed = listClient.removeIf(c -> c.getCpf().equals(cpf));

        if (removed) {
            FileUtils.saveToFileClient(listClient);
        }
        return removed;
    }
}
