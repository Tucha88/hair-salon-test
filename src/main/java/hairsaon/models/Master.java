package hairsaon.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Boris on 01.04.2017.
 * Класс Мастер для записи в базу банных
 */

//TODO добавить масив с услугами и календарь расписание
@Entity
public class Master implements Serializable {
    private static final long serialVersionUID = 112234556L;
    @Id
    @NotNull
    @Column(name = "email")
    private String email;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "lang")
    private ArrayList<String> lang;

    @Column(name = "masterType")
    private String masterType;

    @Column(columnDefinition = "LONGBLOB")
    private ArrayList<Services> serivce = new ArrayList<Services>();

    @Column(name = "address")
    private String addresses;


    public Master() {
    }

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

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public void addServise(Services services) {
        this.serivce.add(services);
    }
}
