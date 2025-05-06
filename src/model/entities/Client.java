package model.entities;

public class Client {
    private Integer id;
    private String name;
    private String cpf;
    private String telephone;

    public Client() {

    }

    public Client(Integer id, String name, String cpf, String telephone) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.telephone = telephone;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + cpf + "," + telephone;
    }
}
