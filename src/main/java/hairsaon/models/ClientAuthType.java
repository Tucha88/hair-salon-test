package hairsaon.models;

/**
 * Created by Boris on 06.04.2017.
 * Класс для проверки при логине Клиента
 */
public class ClientAuthType {
    private String clientEmail;
    private String clientPassword;

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
