package hairsaon.myExtends;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Лимаренко on 21.05.2017.
 */
public class LightCalendar implements Comparable, Serializable {
    private static final long serialVersionUID = 11223452L;
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

    public LightCalendar(MyCalendar myCalendar) {
        this.year = myCalendar.getYear();
        this.month = myCalendar.getMonth() + 1;
        this.day = myCalendar.getDayOfMonth();
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

    public void setDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String getDateString() {
        return day + "." + month + "." + year;
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
    public String toString() {
        String tempDay;
        String tempMonth;
        String tempYear = String.valueOf(this.year);
        if (this.day < 10) {
            tempDay = "0" + this.day;
        } else {
            tempDay = String.valueOf(this.day);
        }
        if (this.month < 10) {
            tempMonth = "0" + this.month;
        } else {
            tempMonth = String.valueOf(this.month);
        }


        return tempDay + "." + tempMonth + "." + tempYear;
    }
}
