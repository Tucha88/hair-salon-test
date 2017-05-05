package hairsaon.models.classes_for_master;

import hairsaon.models.timetable.WeekDay;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Лимаренко on 06.05.2017.
 */
public class AddressTemp implements Serializable {

    String address;
    @ElementCollection
    ArrayList<WeekDay> weekTemplate;

    public AddressTemp() {
        weekTemplate = new ArrayList<WeekDay>();
        for (int i = 0; i < 7; i++) {
            weekTemplate.add(new WeekDay());
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<WeekDay> getWeekTemplate() {
        return weekTemplate;
    }

    public void setWeekTemplate(ArrayList<WeekDay> weekTemplate) {
        this.weekTemplate = weekTemplate;
    }

    public void addTimeOnWeek(int dayOnWeek, boolean active, int startHour, int startMin, int endHour, int endMin) { /** Добовление(изменение дня в шаблоне)*/
        weekTemplate.get(dayOnWeek).setTime(active, startHour, startMin, endHour, endMin);
    }


}