package hairsaon.models;

import hairsaon.myExtends.LightCalendar;

/**
 * Created by Лимаренко on 07.06.2017.
 * Класс для подсчёта свободного времени
 */
public class DateAndDuration {
    int duration;
    LightCalendar myCalendar;

    public DateAndDuration() {
    }

    public DateAndDuration(int duration, LightCalendar myCalendar) {
        this.duration = duration;
        this.myCalendar = myCalendar;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LightCalendar getMyCalendar() {
        return myCalendar;
    }

    public void setMyCalendar(LightCalendar myCalendar) {
        this.myCalendar = myCalendar;
    }
}
