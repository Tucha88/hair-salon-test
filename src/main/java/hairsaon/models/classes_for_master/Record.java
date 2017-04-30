package hairsaon.models.classes_for_master;

import hairsaon.models.Client;
import hairsaon.myExtends.*;

import javax.persistence.*;


/**
 * Created by Лимаренко on 28.04.2017.
 */

//TODO придумать как связывать клиента с мастером через запись

//@Entity
@Table(name = "Record")
public class Record implements Comparable<Record> {
    @Column(name = "date")
    MyCalendar calendar;

    @Column(name = "start time")
    MyClock starTime;

    @Column(name = "service")
    ServiceMaster service;

    @Column(name = "client")
    Client client;

    @Column(name = "info")
    String info;

    public Record() {
    }

    public Record(MyCalendar calendar, MyClock starTime, ServiceMaster service, Client client) {
        this.calendar = calendar;
        this.starTime = starTime;
        this.service = service;
        this.client = client;
    }

    public Record(MyCalendar calendar, MyClock starTime, ServiceMaster service, Client client, String info) {
        this.calendar = calendar;
        this.starTime = starTime;
        this.service = service;
        this.client = client;
        this.info = info;
    }

    public MyCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(MyCalendar calendar) {
        this.calendar = calendar;
    }

    public MyClock getStarTime() {
        return starTime;
    }

    public void setStarTime(MyClock starTime) {
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
