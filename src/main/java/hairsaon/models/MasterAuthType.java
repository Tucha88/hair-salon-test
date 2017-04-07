package hairsaon.models;

/**
 * Created by Boris on 01.04.2017.
 * Класс для проверки при логине Мастера
 */
public class MasterAuthType {
    private String email;
    private String password;

    public MasterAuthType() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
