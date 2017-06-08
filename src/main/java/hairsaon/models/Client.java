package hairsaon.models;


import hairsaon.models.classes_for_master.Record;
import hairsaon.models.personal_models_for_schedule.Appointment;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boris on 01.04.2017.
 * Client entity
 * Класс для записи в базу данных
 */
@Document(collection = "client_test")
public class Client {
    private static final long serialVersionUID = 112234557L;


    @Id
    private String clientEmail;
    private String clientPassword;
    private String clientName;
    private String clientLastName;
    private String clientPhoneNumber;
    @DBRef
    private List<Appointment> records = new ArrayList<>();

    public Client() {
    }

    public void addRecord(Appointment record) {
        this.records.add(record);
    }

    public List<Appointment> getRecords() {
        return records;
    }

    public void setRecords(List<Appointment> records) {
        this.records = records;
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
