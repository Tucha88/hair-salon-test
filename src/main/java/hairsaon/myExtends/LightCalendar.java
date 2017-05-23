package hairsaon.myExtends;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Лимаренко on 21.05.2017.
 */
public class LightCalendar implements Comparable {

    int year;
    int month;
    int day;


    public LightCalendar() {
    }

    public LightCalendar(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int hashCode() {
        return this.getYear() * 3 - 500 + this.getMonth() * 4 + this.getDay();
    }

    @Override
    public boolean equals(Object obj) {
        LightCalendar tempCalendar = (LightCalendar) obj;
        if (this.getYear() == tempCalendar.getYear()) {
            if (this.getMonth() == tempCalendar.getMonth()) {
                if (this.getDay() == tempCalendar.getDay()) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getMyDayOfWeek() {
        MyCalendar tempMyCalendar = new MyCalendar(this.getYear(), this.getMonth(), this.getDay());
        return (tempMyCalendar.get(Calendar.DAY_OF_WEEK) - 1);
    }


    @Override
    public int compareTo(Object obj) {
        LightCalendar tempLightCalendar = (LightCalendar) obj;
        int res = this.getYear() - tempLightCalendar.getYear();
        if (res == 0) {
            res = this.getMonth() - tempLightCalendar.getMonth();
            if (res == 0) {
                res = this.getDay() - tempLightCalendar.getDay();
            }
        }
        return res;
    }
}
