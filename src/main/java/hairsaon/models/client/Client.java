package hairsaon.models.client;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Boris on 01.04.2017.
 */
@Entity
@Table(name = "clientNew")
public class Client {
    private static final long serialVersionUID = 112234557l;
    protected String clientPhoneNumber;

    @Id
    @Column(name = "email")
    protected String clientEmail;
    @Column(name = "password")
    protected String clientPassword;
    @Column(name = "name")
    protected String clientName;
    @Column(name = "lastName")
    protected String clientLastName;

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
