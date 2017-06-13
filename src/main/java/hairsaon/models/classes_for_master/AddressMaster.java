package hairsaon.models.classes_for_master;

import hairsaon.models.timetable.CalendarDay;
import hairsaon.models.timetable.WeekDay;
import hairsaon.myExtends.LightCalendar;
import hairsaon.myExtends.LightClock;
import hairsaon.myExtends.MyCalendar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by Лимаренко on 28.04.2017.
 */

public class AddressMaster implements Serializable {
    private static final long serialVersionUID = 11223L;
    String address;
    double latitude;
    double longitude;
    String placeId;

    ArrayList<WeekDay> weekTemplate;
    TreeMap<String, CalendarDay> timetableMap;
    TreeMap <String, ArrayList<Record>> archiveRecords;



    public AddressMaster() {
        weekTemplate = new ArrayList<WeekDay>();
        timetableMap = new TreeMap<String, CalendarDay>();
        for (int i = 0; i < 7; i++) {
            weekTemplate.add(new WeekDay());
        }
        archiveRecords = new TreeMap<String, ArrayList<Record>>();
        ArrayList<Record> arrayList = new ArrayList<Record>();

    }

    public AddressMaster(String address) {
        this.address = address;
        weekTemplate = new ArrayList<WeekDay>();
        timetableMap = new TreeMap<String, CalendarDay>();
        for (int i = 0; i < 7; i++) {
            weekTemplate.add(new WeekDay());
        }
        archiveRecords = new TreeMap<String,  ArrayList<Record>>();
    }

    public TreeMap<String, ArrayList<Record>> getArchiveRecords() {
        return archiveRecords;
    }

    public void setArchiveRecords(TreeMap<String, ArrayList<Record>> archiveRecords) {
        this.archiveRecords = archiveRecords;
    }

    public TreeMap<String, CalendarDay> getTimetableMap() {
        return timetableMap;
    }

    public void setTimetableMap(TreeMap<String, CalendarDay> timetableMap) {
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
        timetableMap.put(myCalendar.toString(), new CalendarDay(myCalendar, startHour, startMin, endHour, endMin, true));
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public void startMasterTrmplatr() { /** Первое создание расписание мастера*/

        for (int i = 0; i < 14; i++) {
            MyCalendar tempMyCalendar = new MyCalendar();
            //tempMyCalendar.add(Calendar.DAY_OF_MONTH, -1);
            tempMyCalendar.add(Calendar.DAY_OF_MONTH, i);
            LightCalendar lightTemp = new LightCalendar(tempMyCalendar);
            if (timetableMap.get(lightTemp.toString()) == null) {
                WeekDay tempWeekDay = weekTemplate.get(tempMyCalendar.getMyDayOfWeek());
                if (tempWeekDay.getActiveDay()) {
                    timetableMap.put(lightTemp.toString(), new CalendarDay(lightTemp, tempWeekDay));
                } else {
                    timetableMap.put(lightTemp.toString(), new CalendarDay(lightTemp, 0, 0, 0, 0, false));
                }
            }
        }

    }

    public TreeSet<LightClock> getFreeTimeOnDate(String dateStr, int durationService) {
        if (timetableMap.get(dateStr) == null) {
            return new TreeSet<LightClock>();
        }
        if (timetableMap.get(dateStr).isWorking()) {
            return this.timetableMap.get(dateStr).getFreeTime(durationService);
        } else {
            return new TreeSet<LightClock>();
        }

    }


    public void update() { /** удаление дня недельной давности и добавление дня через 2 недели*/


        MyCalendar tempMyCalendar = new MyCalendar();
        tempMyCalendar.add(Calendar.DAY_OF_MONTH, -1);
        String calendarStr = new LightCalendar(tempMyCalendar).toString();

        MyCalendar archiveCalendar = new MyCalendar();
        archiveCalendar.add(Calendar.DAY_OF_MONTH, -7);
        String archiveCalendarStr = new LightCalendar(tempMyCalendar).toString();

        if (archiveRecords.get(archiveCalendarStr)!=null){
            archiveRecords.remove(archiveCalendarStr);
        }
        if (timetableMap.get(calendarStr) != null) {
            ArrayList <Record> tempArray = timetableMap.get(calendarStr).getRecords();
            System.out.println(tempArray.get(0).getCalendar() + "  " + tempArray.get(0).getStarTime());
            if (tempArray != null) {
                if (tempArray.size() != 0) {
                    archiveRecords.put(calendarStr, new ArrayList<Record>(tempArray));
                }
            }
            timetableMap.remove(calendarStr);
        }
        tempMyCalendar = new MyCalendar();
        tempMyCalendar.add(Calendar.DAY_OF_MONTH, 14);
        LightCalendar lightTemp = new LightCalendar(tempMyCalendar);
        if (timetableMap.get(lightTemp.toString()) == null) {
            WeekDay tempWeekDay = weekTemplate.get(tempMyCalendar.getMyDayOfWeek());
            if (tempWeekDay.getActiveDay()) {
                timetableMap.put(lightTemp.toString(), new CalendarDay(lightTemp, tempWeekDay));
            } else {
                timetableMap.put(lightTemp.toString(), new CalendarDay(lightTemp, 0, 0, 0, 0, false));
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
