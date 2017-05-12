package hairsaon.myExtends;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Лимаренко on 28.04.2017.
 */
public class MyClock extends GregorianCalendar {


    public MyClock() {
    }

    public MyClock(int hourOfDay, int minute) {
        super(26, 03, 1992, hourOfDay, minute, 0);
    }

    public MyClock(MyClock tempClock) {
        super(26, 03, 1992, tempClock.getHour(), tempClock.getMinute(), 0);
    }


    public int getHour() {
        return this.get(Calendar.HOUR_OF_DAY);
    }

    public void setHour(int hour) {
        this.set(Calendar.HOUR_OF_DAY, hour);
    }

    public int getMinute() {
        return this.get(Calendar.MINUTE);
    }

    public void setMinute(int minute) {
        this.set(Calendar.MINUTE, minute);
    }

    public void addMinute(int minute) {
        this.add(MINUTE, minute);
    }


    @Override
    public boolean equals(Object obj) {
        MyClock tempClock = (MyClock) obj;
        if (this.getHour() == tempClock.getHour()) {
            if (this.getMinute() == tempClock.getMinute()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getHour() * 81 + this.getMinute() * 17 - this.getHour() * 34;
    }

    @Override
    public String toString() {
        int intHour = this.getHour();
        int intMinute = this.getMinute();
        String strHour;
        String strMinute;
        if (intHour < 10) {
            strHour = "0" + intHour;
        } else {
            strHour = String.valueOf(intHour);
        }
        if (intMinute < 10) {
            strMinute = "0" + intMinute;
        } else {
            strMinute = String.valueOf(intMinute);
        }
        return strHour + ":" + strMinute;
    }

    @Override
    public int compareTo(Calendar anotherCalendar) {
        //return super.compareTo(anotherCalendar);
        MyClock tempClock = (MyClock) anotherCalendar;
        int res;
        res = this.getHour() - tempClock.getHour();
        if (res == 0) {
            res = this.getMinute() - tempClock.getMinute();
        }
        return res;
    }
}
