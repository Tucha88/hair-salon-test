package hairsaon.models.timetable;

import hairsaon.myExtends.LightClock;
import hairsaon.myExtends.MyClock;

import java.io.Serializable;

/**
 * Created by Лимаренко on 28.04.2017.
 */
public class WeekDay implements Serializable {
    private static final long serialVersionUID = 112234550L;
    boolean activeDay;

    LightClock startWork;
    LightClock endWork;



    public WeekDay() {
        this.activeDay = false;
        this.startWork = new LightClock(0, 0);
        this.endWork = new LightClock(0, 0);
    }

    public boolean isActiveDay() {
        return activeDay;
    }
    public boolean getActiveDay() {
        return activeDay;
    }

    public void setActiveDay(boolean activeDay) {
        this.activeDay = activeDay;
    }

    /*public int getStartHour() {
        return startWork.getHourLight();
    }

    public void setStartHour(int startHour) {
        this.startWork.setHourLight(startHour);
    }

    public int getStartMin() {
        return startWork.getMinuteLight();
    }

    public void setStartMin(int startMin) {
        this.startWork.setMinuteLight(startMin);
    }

    public int getEndHour() {
        return endWork.getHourLight();
    }

    public void setEndHour(int endHour) {
        this.endWork.setHourLight(endHour);
    }

    public int getEndMin() {
        return endWork.getMinuteLight();
    }

    public void setEndMin(int endMin) {
        this.endWork.setMinuteLight(endMin);
    }*/


    public LightClock getStartWork() {
        return startWork;
    }

    public void setStartWork(LightClock startWork) {
        this.startWork = startWork;
    }

    public LightClock getEndWork() {
        return endWork;
    }

    public void setEndWork(LightClock endWork) {
        this.endWork = endWork;
    }

    public void setTime(boolean active, int startHour, int startMin, int endHour, int endMin) {
        this.activeDay = active;
        this.startWork = new LightClock(startHour, startMin);
        this.endWork = new LightClock(endHour, endMin);

    }

    @Override
    public String toString() {
        if (activeDay) {
            return "activeDay: " + activeDay +
                    ", start: " + startWork.getHourLight() +
                    ":" + startWork.getMinuteLight() +
                    ", end: " + endWork.getHourLight() +
                    ":" + endWork.getMinuteLight() + "; ";
        } else {
            return "activeDay: " + activeDay;
        }

    }
}
