package hairsaon.myExtends;

import java.util.Calendar;

/**
 * Created by Лимаренко on 23.05.2017.
 */
public class LightClock implements Comparable {

    int hour;
    int minute;

    public LightClock() {
    }

    public LightClock(LightClock clock) {
        this.hour = clock.getHour();
        this.minute = clock.getMinute();
    }

    public LightClock(MyClock clock) {
        this.hour = clock.getHour();
        this.minute = clock.getMinute();
    }

    public LightClock(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    @Override
    public boolean equals(Object obj) {
        LightClock tempClock = (LightClock) obj;
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
    public int compareTo(Object obj) {
        LightClock tempClock = (LightClock) obj;
        int res;
        res = this.getHour() - tempClock.getHour();
        if (res == 0) {
            res = this.getMinute() - tempClock.getMinute();
        }
        return res;
    }

    public void addHour(int hour) {
        MyClock myClock = new MyClock(this.getHour(), this.getMinute());
        myClock.add(Calendar.HOUR_OF_DAY, hour);
        this.setTime(myClock.getHour(), myClock.getMinute());
    }

    public void addMinute(int minute) {
        MyClock myClock = new MyClock(this.getHour(), this.getMinute());
        myClock.add(Calendar.MINUTE, minute);
        this.setTime(myClock.getHour(), myClock.getMinute());
    }

    @Override
    public String toString() {
        return hour + ":" + minute;
    }
}
