package hairsaon.models.client;

/**
 * Created by Boris on 06.04.2017.
 */
public class ClientAuthType {
    protected String clientEmail;
    protected String clientPassword;

    public ClientAuthType() {
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
}
