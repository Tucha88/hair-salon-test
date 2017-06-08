package hairsaon.models.classes_for_master;

import hairsaon.models.Services;
import hairsaon.myExtends.LightCalendar;
import hairsaon.myExtends.LightClock;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by Лимаренко on 28.04.2017.
 */

//TODO придумать как связывать клиента с мастером через запись

public class Record implements Comparable<Record>, Serializable {
    private static final long serialVersionUID = 11223455612L;
    LightCalendar calendar;
    LightClock starTime;
    ArrayList<Services> services;
    int duration;

    //TODO Надо переделать на ID клиента. Зачем хранить всего клиента
    //Client client;

    String info;

    public Record() {
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

    public ArrayList<Services> getServices() {
        return services;
    }

    public void setServices(ArrayList<Services> services) {
        this.services = services;
        this.duration = 0;
        for (int i = 0; i < services.size(); i++) {
            duration = duration + services.get(i).getDuration();
        }
    }

/*    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }*/

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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
                ", service=" + services +
                ", note: " + info +
                "duration: " + duration + ".";
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
