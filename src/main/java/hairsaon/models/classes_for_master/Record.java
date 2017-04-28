package hairsaon.models.classes_for_master;

import hairsaon.myExtends.*;


/**
 * Created by Лимаренко on 28.04.2017.
 */
public class Record implements Comparable<Record> {
    MyCalendar calendar;
    MyClock starTime;
    ServiceMaster service;
    String info;

    public Record(MyCalendar calendar, MyClock starTime, ServiceMaster service) {
        this.calendar = calendar;
        this.starTime = starTime;
        this.service = service;
    }

    public Record(MyCalendar calendar, MyClock starTime, ServiceMaster service, String info) {
        this.calendar = calendar;
        this.starTime = starTime;
        this.service = service;
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
        return this.starTime.compareTo(tempRecord.starTime);
    }
}
