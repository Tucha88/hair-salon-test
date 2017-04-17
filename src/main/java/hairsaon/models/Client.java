package hairsaon.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Boris on 01.04.2017.
 * Client entity
 * Класс для записи в базу данных
 */
@Entity
public class Client {
    private static final long serialVersionUID = 112234557L;
    private String clientPhoneNumber;
    @Id
    @Column(name = "email")
    private String clientEmail;
    @Column(name = "password")
    private String clientPassword;
    @Column(name = "name")
    private String clientName;
    @Column(name = "lastname")
    private String clientLastName;
    @Column(name = "token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Client() {
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }
}
