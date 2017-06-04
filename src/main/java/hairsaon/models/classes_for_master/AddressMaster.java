package hairsaon.models.classes_for_master;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import hairsaon.models.timetable.CalendarDay;
import hairsaon.models.timetable.WeekDay;
import hairsaon.myExtends.LightCalendar;
import hairsaon.myExtends.LightClock;
import hairsaon.myExtends.MyCalendar;
import hairsaon.utils.MyLightCalendarDeserializer;
import hairsaon.utils.MyLightCalendarSerializer;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

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
    /*@JsonDeserialize(keyUsing = MyLightCalendarDeserializer.class)
    @JsonSerialize(keyUsing = MyLightCalendarSerializer.class)*/
    Map<String, CalendarDay> timetableMap;

    //ArrayList<ServiceMaster> arrayServices;


    public AddressMaster() {
        weekTemplate = new ArrayList<WeekDay>();
        timetableMap = new TreeMap<String, CalendarDay>();
        LightCalendar lightCalendar = new LightCalendar(2017, 7, 3);
        LightCalendar lightCalendar2 = new LightCalendar(2016, 10, 13);
        LightCalendar lightCalendar3 = new LightCalendar(2017, 7, 23);
        CalendarDay calendarDay = new CalendarDay(lightCalendar, 14, 0, 19, 30, true);


        timetableMap.put(lightCalendar.toString(), calendarDay);
        timetableMap.put(lightCalendar2.toString(), calendarDay);
        timetableMap.put(lightCalendar3.toString(), calendarDay);
        for (int i = 0; i < 7; i++) {
            weekTemplate.add(new WeekDay());
        }

    }

    public AddressMaster(String address) {
        this.address = address;
        weekTemplate = new ArrayList<WeekDay>();
        timetableMap = new TreeMap<String, CalendarDay>();
        for (int i = 0; i < 7; i++) {
            weekTemplate.add(new WeekDay());
        }
    }


    public Map<String, CalendarDay> getTimetableMap() {
        return timetableMap;
    }

    public void setTimetableMap(Map<String, CalendarDay> timetableMap) {
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

        for (int i = 0; i < 21; i++) {

            MyCalendar tempMyCalendar = new MyCalendar();
            tempMyCalendar.add(Calendar.DAY_OF_MONTH, -7);
            tempMyCalendar.add(Calendar.DAY_OF_MONTH, i);
            LightCalendar lightTemp = new LightCalendar(tempMyCalendar);
            if (timetableMap.get(lightTemp) == null) {
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
            return null;
        }
        return this.timetableMap.get(dateStr).getFreeTime(durationService);
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
