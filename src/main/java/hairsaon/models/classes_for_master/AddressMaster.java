package hairsaon.models.classes_for_master;

import hairsaon.models.timetable.CalendarDay;
import hairsaon.models.timetable.WeekDay;
import hairsaon.myExtends.MyCalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by Лимаренко on 28.04.2017.
 */
public class AddressMaster {
    static private int counterId = 1;


    int id;
    String address;
    ArrayList<WeekDay> weekTemplate;
    HashMap<MyCalendar, CalendarDay> timetableMap;
    ArrayList<ServiceMaster> arrayServices;

    public AddressMaster() {
        weekTemplate = new ArrayList<WeekDay>();
        timetableMap = new HashMap<MyCalendar, CalendarDay>();
        this.id = counterId;
        counterId++;
        for (int i = 0; i < 7; i++) {
            weekTemplate.add(new WeekDay());
        }

    }

    public AddressMaster(String address) {
        this.address = address;
        weekTemplate = new ArrayList<WeekDay>();
        timetableMap = new HashMap<MyCalendar, CalendarDay>();
        this.id = counterId;
        counterId++;
        for (int i = 0; i < 7; i++) {
            weekTemplate.add(new WeekDay());
        }
    }

    public void addTimeOnWeek(int dayOnWeek, boolean active, int startHour, int startMin, int endHour, int endMin) { /**Добовление(изменение дня в шаблоне)*/
        weekTemplate.get(dayOnWeek).setTime(active, startHour, startMin, endHour, endMin);
    }

    public void addTimeOnDate(MyCalendar myCalendar, int startHour, int startMin, int endHour, int endMin) { /**Добовление дня в расписание (в клендарь)*/
        timetableMap.put(myCalendar, new CalendarDay(myCalendar, startHour, startMin, endHour, endMin, true));
    }

    public void startMasterTrmplatr() { /** Первое создание расписание мастера*/

        for (int i = 0; i < 21; i++) {
            MyCalendar tempMyCalendar = new MyCalendar();
            tempMyCalendar.add(Calendar.DAY_OF_MONTH, -7);
            tempMyCalendar.add(Calendar.DAY_OF_MONTH, i);
            if (timetableMap.get(tempMyCalendar) == null) {
                if (weekTemplate.get(tempMyCalendar.getMyDayOfWeek()).getActiveDay()) {
                    timetableMap.put(tempMyCalendar, new CalendarDay(tempMyCalendar,
                            weekTemplate.get(tempMyCalendar.getMyDayOfWeek()).getStartHour(),
                            weekTemplate.get(tempMyCalendar.getMyDayOfWeek()).getStartMin(),
                            weekTemplate.get(tempMyCalendar.getMyDayOfWeek()).getEndHour(),
                            weekTemplate.get(tempMyCalendar.getMyDayOfWeek()).getEndMin(), true));
                } else {
                    timetableMap.put(tempMyCalendar, new CalendarDay(tempMyCalendar, 0, 0, 0, 0, false));
                }
            }

        }

    }


    public void update() { /**удаление дня недельной давности и добавление дня через 2 недели*/


        MyCalendar tempMyCalendar = new MyCalendar();
        tempMyCalendar.add(Calendar.DAY_OF_MONTH, -7);
        timetableMap.remove(tempMyCalendar);
        tempMyCalendar = new MyCalendar();
        tempMyCalendar.add(Calendar.DAY_OF_MONTH, 14);
        if (timetableMap.get(tempMyCalendar) == null) { // если эта дата пустая проверить шаблон.
            if (weekTemplate.get(tempMyCalendar.getMyDayOfWeek()).getActiveDay()) { // если в шаблоне этот день рабочий берём време с шаблона
                timetableMap.put(tempMyCalendar, new CalendarDay(tempMyCalendar,
                        weekTemplate.get(tempMyCalendar.getMyDayOfWeek()).getStartHour(),
                        weekTemplate.get(tempMyCalendar.getMyDayOfWeek()).getStartMin(),
                        weekTemplate.get(tempMyCalendar.getMyDayOfWeek()).getEndHour(),
                        weekTemplate.get(tempMyCalendar.getMyDayOfWeek()).getEndMin(), true));
            } else {
                timetableMap.put(tempMyCalendar, new CalendarDay(tempMyCalendar, 0, 0, 0, 0, false));
            }
        }
    }

    public void removeDate(int year, int month, int dayOfMonth) {  /**удаление даты из колендаря (метот для админа)*/
        timetableMap.remove(new MyCalendar(year, month, dayOfMonth));
    }

    public void removeDate(MyCalendar myCalendar) {
        timetableMap.remove(myCalendar);
    }
}
