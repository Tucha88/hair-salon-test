package hairsaon.myExtends;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Лимаренко on 23.05.2017.
 */
public class LightClock implements Comparable, Serializable {
    private static final long serialVersionUID = 112234554L;

    int hourLight;
    int minuteLight;

    public LightClock() {
    }

    public LightClock(LightClock clock) {
        this.hourLight = clock.getHourLight();
        this.minuteLight = clock.getMinuteLight();
    }

//    public LightClock(MyClock clock) {
//        this.hour = clock.getHour();
//        this.minute = clock.getMinute();
//    }

    public LightClock(int hour, int minute) {
        this.hourLight = hour;
        this.minuteLight = minute;
    }

    public int getHourLight() {
        return hourLight;
    }

    public void setHourLight(int hourLight) {
        this.hourLight = hourLight;
    }

    public int getMinuteLight() {
        return minuteLight;
    }

    public void setMinuteLight(int minuteLight) {
        this.minuteLight = minuteLight;
    }

    public void setTime(int hour, int minute) {
        this.hourLight = hour;
        this.minuteLight = minute;
    }

    @Override
    public boolean equals(Object obj) {
        LightClock tempClock = (LightClock) obj;
        if (this.getHourLight() == tempClock.getHourLight()) {
            if (this.getMinuteLight() == tempClock.getMinuteLight()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getHourLight() * 81 + this.getMinuteLight() * 17 - this.getHourLight() * 34;
    }


    @Override
    public int compareTo(Object obj) {
        LightClock tempClock = (LightClock) obj;
        int res;
        res = this.getHourLight() - tempClock.getHourLight();
        if (res == 0) {
            res = this.getMinuteLight() - tempClock.getMinuteLight();
        }
        return res;
    }

    public void addHour(int hour) {
        MyClock myClock = new MyClock(this.getHourLight(), this.getMinuteLight());
        myClock.add(Calendar.HOUR_OF_DAY, hour);
        this.setTime(myClock.getHour(), myClock.getMinute());
    }

    public void addMinute(int minute) {
        MyClock myClock = new MyClock(this.getHourLight(), this.getMinuteLight());
        myClock.add(Calendar.MINUTE, minute);
        this.setTime(myClock.getHour(), myClock.getMinute());
    }

    @Override
    public String toString() {
        if (minuteLight < 10) {
            String minuteStr = "0" + minuteLight;
            return hourLight + ":" + minuteStr;
        } else {
            return hourLight + ":" + minuteLight;
        }
    }
}
