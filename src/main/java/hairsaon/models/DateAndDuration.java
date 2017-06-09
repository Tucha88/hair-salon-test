package hairsaon.models;

import hairsaon.myExtends.LightCalendar;

/**
 * Created by Лимаренко on 07.06.2017.
 * Класс для подсчёта свободного времени
 */
public class DateAndDuration {
    String email;
    int duration;
    LightCalendar myCalendar;

    public DateAndDuration() {
    }

    public DateAndDuration(String email, int duration, LightCalendar myCalendar) {
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
