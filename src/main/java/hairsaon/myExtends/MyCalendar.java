package hairsaon.myExtends;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Лимаренко on 28.04.2017.
 */
public class MyCalendar extends GregorianCalendar implements Serializable {
    private static final long serialVersionUID = 1122345567L;
    static public int SUNDAY = 0;
    static public int MONDAY = 1;
    static public int TUESDAY = 2;
    static public int WEDNESDAY = 3;
    static public int THURSDAY = 4;
    static public int FRIDAY = 5;
    static public int SATURDAY = 6;


    public MyCalendar() {
        super();
    }

    public MyCalendar(TimeZone zone) {
        super(zone);
    }

    public MyCalendar(Locale aLocale) {
        super(aLocale);
    }

    public MyCalendar(TimeZone zone, Locale aLocale) {
        super(zone, aLocale);
    }

    public MyCalendar(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    public MyCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
        super(year, month, dayOfMonth, hourOfDay, minute);
    }

    public MyCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second) {
        super(year, month, dayOfMonth, hourOfDay, minute, second);
    }

    public MyCalendar(LightCalendar lightCalendar) {
        super(lightCalendar.getYear(), lightCalendar.getMonth() - 1, lightCalendar.getDay());
    }

    public int getYear() {
        return this.get(Calendar.YEAR);
    }

    public void setHour(int year) {
        this.set(Calendar.YEAR, year);
    }

    public int getMonth() {
        return this.get(Calendar.MONTH);
    }

    public void setMonth(int month) {
        this.set(Calendar.MONTH, month);
    }

    public int getDayOfMonth() {
        return this.get(Calendar.DAY_OF_MONTH);
    }

    public void setDayOfMonth(int day) {
        this.set(Calendar.DAY_OF_MONTH, day);
    }


    @Override
    public int hashCode() {
        return this.get(Calendar.YEAR) * 3 - 500 + this.get(Calendar.MONTH) * 4 + this.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public boolean equals(Object obj) {
        GregorianCalendar tempCalendar = (GregorianCalendar) obj;
        if (this.get(Calendar.YEAR) == tempCalendar.get(Calendar.YEAR)) {
            if (this.get(Calendar.MONTH) == tempCalendar.get(Calendar.MONTH)) {
                if (this.get(Calendar.DAY_OF_MONTH) == tempCalendar.get(Calendar.DAY_OF_MONTH)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getMyDayOfWeek() {
        return (this.get(Calendar.DAY_OF_WEEK) - 1);
    }

    @Override
    public int compareTo(Calendar anotherCalendar) {
        MyCalendar tempCalendar = (MyCalendar) anotherCalendar;
        int res = this.getYear() - tempCalendar.getYear();
        if (res == 0) {
            res = this.getMonth() - tempCalendar.getMonth();
            if (res == 0) {
                res = this.getDayOfMonth() - tempCalendar.getDayOfMonth();
            }
        }
        return res;
    }
}
