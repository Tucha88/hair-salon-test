package hairsaon.models.classes_for_master;

import hairsaon.models.timetable.CalendarDay;
import hairsaon.models.timetable.WeekDay;
import hairsaon.myExtends.LightCalendar;
import hairsaon.myExtends.MyCalendar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by Лимаренко on 28.04.2017.
 */

public class AddressMaster implements Serializable {
    private static final long serialVersionUID = 11223L;
    String address;
    /*@ElementCollection*/
    ArrayList<WeekDay> weekTemplate;
    /*@ElementCollection*/
    TreeMap<LightCalendar, CalendarDay> timetableMap = new TreeMap<LightCalendar, CalendarDay>();
    //ArrayList<ServiceMaster> arrayServices;


    public AddressMaster() {
        weekTemplate = new ArrayList<WeekDay>();
        //timetableMap = new TreeMap<LightCalendar, CalendarDay>();
        for (int i = 0; i < 7; i++) {
            weekTemplate.add(new WeekDay());
        }

    }

    public AddressMaster(String address) {
        this.address = address;
        weekTemplate = new ArrayList<WeekDay>();
        //timetableMap = new TreeMap<LightCalendar, CalendarDay>();
        for (int i = 0; i < 7; i++) {
            weekTemplate.add(new WeekDay());
        }
    }

    public TreeMap<LightCalendar, CalendarDay> getTimetableMap() {
        return timetableMap;
    }

    public void setTimetableMap(TreeMap<LightCalendar, CalendarDay> timetableMap) {
        this.timetableMap = timetableMap;
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

    public void addTimeOnDate(LightCalendar myCalendar, int startHour, int startMin, int endHour, int endMin) { /** Добовление дня в расписание (в клендарь)*/
        timetableMap.put(myCalendar, new CalendarDay(myCalendar, startHour, startMin, endHour, endMin, true));
    }

    public void startMasterTrmplatr() { /** Первое создание расписание мастера*/

        for (int i = 0; i < 21; i++) {

            MyCalendar tempMyCalendar = new MyCalendar();
            tempMyCalendar.add(Calendar.DAY_OF_MONTH, -7);
            tempMyCalendar.add(Calendar.DAY_OF_MONTH, i);
            LightCalendar lightTemp = new LightCalendar(tempMyCalendar);
            if (timetableMap.get(lightTemp) == null) {
                WeekDay tempWeekDay = weekTemplate.get(tempMyCalendar.getMyDayOfWeek());
                if (tempWeekDay.getActiveDay()) {
                    timetableMap.put(lightTemp, new CalendarDay(lightTemp, tempWeekDay));
                } else {
                    timetableMap.put(lightTemp, new CalendarDay(lightTemp, 0, 0, 0, 0, false));
                }
            }



        }

    }


    public void update() { /** удаление дня недельной давности и добавление дня через 2 недели*/


        MyCalendar tempMyCalendar = new MyCalendar();
        tempMyCalendar.add(Calendar.DAY_OF_MONTH, -7);
        timetableMap.remove(new LightCalendar(tempMyCalendar));
        tempMyCalendar = new MyCalendar();
        tempMyCalendar.add(Calendar.DAY_OF_MONTH, 14);
        LightCalendar lightTemp = new LightCalendar(tempMyCalendar);
        if (timetableMap.get(lightTemp) == null) {
            WeekDay tempWeekDay = weekTemplate.get(tempMyCalendar.getMyDayOfWeek());
            if (tempWeekDay.getActiveDay()) {
                timetableMap.put(lightTemp, new CalendarDay(lightTemp, tempWeekDay));
            } else {
                timetableMap.put(lightTemp, new CalendarDay(lightTemp, 0, 0, 0, 0, false));
            }
        }
    }

    public void removeDate(int year, int month, int dayOfMonth) {  /** удаление даты из колендаря (метот для админа)*/
        timetableMap.remove(new MyCalendar(year, month, dayOfMonth));
    }

    public void removeDate(MyCalendar myCalendar) {
        timetableMap.remove(myCalendar);
    }
}
