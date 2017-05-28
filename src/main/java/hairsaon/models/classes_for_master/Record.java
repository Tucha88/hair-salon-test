package hairsaon.models.classes_for_master;

import hairsaon.models.Client;
import hairsaon.models.Master;
import hairsaon.myExtends.*;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Created by Лимаренко on 28.04.2017.
 */

//TODO придумать как связывать клиента с мастером через запись

public class Record implements Comparable<Record>, Serializable {
    private static final long serialVersionUID = 11223455612L;
    LightCalendar calendar;

    LightClock starTime;

    ServiceMaster service;

    //TODO Надо переделать на ID клиента. Зачем хранить всего клиента
    Client client;

    String info;

    public Record() {
    }

    public Record(LightCalendar calendar, LightClock starTime, ServiceMaster service, Client client) {
        this.calendar = calendar;
        this.starTime = starTime;
        this.service = service;
        this.client = client;
    }

    public Record(LightCalendar calendar, LightClock starTime, ServiceMaster service, Client client, String info) {
        this.calendar = calendar;
        this.starTime = starTime;
        this.service = service;
        this.client = client;
        this.info = info;
    }

    public LightCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(LightCalendar calendar) {
        this.calendar = calendar;
    }

    public LightClock getStarTime() {
        return starTime;
    }

    public void setStarTime(LightClock starTime) {
        this.starTime = starTime;
    }

    public ServiceMaster getService() {
        return service;
    }

    public void setService(ServiceMaster service) {
        this.service = service;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Record " +
                calendar +
                ", Star in: " + starTime +
                ", service=" + service +
                ", note: " + info + ".";
    }


    @Override
    public int compareTo(Record tempRecord) {
        int res = this.getCalendar().compareTo(tempRecord.getCalendar());
        if (res == 0) {
            res = this.getStarTime().compareTo(tempRecord.getStarTime());
        }
        return res;
    }
}
