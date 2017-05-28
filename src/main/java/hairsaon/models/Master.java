package hairsaon.models;


import hairsaon.models.classes_for_master.AddressMaster;
import hairsaon.models.classes_for_master.Record;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boris on 01.04.2017.
 * Класс Мастер для записи в базу банных
 */

//TODO добавить масив с услугами и календарь расписание
@Document(collection = "master_test")
public class Master implements Serializable {
    private static final long serialVersionUID = 112234556L;

    @Id
    private String email;
    private String phoneNumber;
    private String password;
    private String name;
    private String lastName;
    private ArrayList<String> lang;
    private String masterType;
    private ArrayList<Services> serivce = new ArrayList<Services>();
    private AddressMaster addressMaster = new AddressMaster();
    private List<Record> records = new ArrayList<>();
//    private String addresses;

    public Master() {
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public AddressMaster getAddressMaster() {
        return addressMaster;
    }

    public void setAddressMaster(AddressMaster addressMaster) {
        this.addressMaster = addressMaster;
    }

    //    public String getAddresses() {
//        return addresses;
//    }
//
//    public void setAddresses(String addresses) {
//        this.addresses = addresses;
//    }
//
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<String> getLang() {
        return lang;
    }

    public void setLang(ArrayList<String> lang) {
        this.lang = lang;
    }

    public String getMasterType() {
        return masterType;
    }

    public void setMasterType(String masterType) {
        this.masterType = masterType;
    }

    public ArrayList<Services> getSerivce() {
        return serivce;
    }

    public void setSerivce(ArrayList<Services> serivce) {
        this.serivce = serivce;
    }


    public void addServise(Services services) {
        this.serivce.add(services);
    }

    public Boolean isPassword(String hashPass) {
        return this.password.equals(hashPass);
    }
}
