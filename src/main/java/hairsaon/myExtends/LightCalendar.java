package hairsaon.myExtends;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Лимаренко on 21.05.2017.
 */
public class LightCalendar implements Comparable, Serializable {
    private static final long serialVersionUID = 11223452L;

    int yearLight;
    int monthLight;
    int dayLight;


    public LightCalendar(String all) {
        String[] strings = all.split(".");
        this.yearLight = Integer.valueOf(strings[0]);
        this.monthLight = Integer.valueOf(strings[1]);
        this.dayLight = Integer.valueOf(strings[2]);
    }

    public LightCalendar() {
    }

    public LightCalendar(int year, int month, int day) {
        this.yearLight = year;
        this.monthLight = month;
        this.dayLight = day;
    }
    public LightCalendar(MyCalendar myCalendar) {
        this.yearLight = myCalendar.getYear();
        this.monthLight = myCalendar.getMonth() + 1;
        this.dayLight = myCalendar.getDayOfMonth();
    }

    public int getYearLight() {
        return yearLight;
    }

    public void setYearLight(int yearLight) {
        this.yearLight = yearLight;
    }

    public int getMonthLight() {
        return monthLight;
    }

    public void setMonthLight(int monthLight) {
        this.monthLight = monthLight;
    }

    public int getDayLight() {
        return dayLight;
    }

    public void setDayLight(int dayLight) {
        this.dayLight = dayLight;
    }

    public void setDate(int year, int month, int day) {
        this.yearLight = year;
        this.monthLight = month;
        this.dayLight = day;
    }

 /*   public String getDateString() {
        return dayLight + "." + monthLight + "." + yearLight;
    }*/

    @Override
    public int hashCode() {
        return this.getYearLight() * 3 - 500 + this.getMonthLight() * 4 + this.getDayLight();
    }

    @Override
    public boolean equals(Object obj) {
        LightCalendar tempCalendar = (LightCalendar) obj;
        if (this.getYearLight() == tempCalendar.getYearLight()) {
            if (this.getMonthLight() == tempCalendar.getMonthLight()) {
                if (this.getDayLight() == tempCalendar.getDayLight()) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getMyDayOfWeek() {
        MyCalendar tempMyCalendar = new MyCalendar(this.getYearLight(), this.getMonthLight(), this.getDayLight());
        return (tempMyCalendar.get(Calendar.DAY_OF_WEEK) - 1);
    }


    @Override
    public int compareTo(Object obj) {
        LightCalendar tempLightCalendar = (LightCalendar) obj;
        int res = this.getYearLight() - tempLightCalendar.getYearLight();
        if (res == 0) {
            res = this.getMonthLight() - tempLightCalendar.getMonthLight();
            if (res == 0) {
                res = this.getDayLight() - tempLightCalendar.getDayLight();
            }
        }
        return res;
    }
//    public void addYear(int year) {
//        MyCalendar myCalendar = new MyCalendar(this.getYear(), this.getMonth(), this.getDay());
//        myCalendar.add(Calendar.YEAR, year);
//        this.setDate(myCalendar.getYear(), myCalendar.getMonth(), myCalendar.getDayOfMonth());
//    }
//
//    public void addMonth(int month) {
//        MyCalendar myCalendar = new MyCalendar(this.getYear(), this.getMonth(), this.getDay());
//        myCalendar.add(Calendar.MONTH, month);
//        this.setDate(myCalendar.getYear(), myCalendar.getMonth(), myCalendar.getDayOfMonth());
//    }
//
//    public void addDay(int day) {
//        MyCalendar myCalendar = new MyCalendar(this.getYear(), this.getMonth(), this.getDay());
//        myCalendar.add(Calendar.DAY_OF_MONTH, day);
//        this.setDate(myCalendar.getYear(), myCalendar.getMonth(), myCalendar.getDayOfMonth());
//    }


    @Override
    @JsonValue
    public String toString() {
        String tempDay;
        String tempMonth;
        String tempYear = String.valueOf(this.yearLight);
        if (this.dayLight < 10) {
            tempDay = "0" + this.dayLight;
        } else {
            tempDay = String.valueOf(this.dayLight);
        }
        if (this.monthLight < 10) {
            tempMonth = "0" + this.monthLight;
        } else {
            tempMonth = String.valueOf(this.monthLight);
        }


        return tempYear + "/" + tempMonth + "/" + tempDay;
    }
}
