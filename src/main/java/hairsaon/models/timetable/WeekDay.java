package hairsaon.models.timetable;

import hairsaon.myExtends.LightClock;
import hairsaon.myExtends.MyClock;

/**
 * Created by Лимаренко on 28.04.2017.
 */
public class WeekDay {
    boolean activeDay;

    LightClock startWork;
    LightClock endWork;


    public WeekDay() {
        this.activeDay = false;
        this.startWork = new LightClock(0, 0);
        this.endWork = new LightClock(0, 0);
    }

    public boolean getActiveDay() {
        return activeDay;
    }

    public void setActiveDay(boolean activeDay) {
        this.activeDay = activeDay;
    }

    public int getStartHour() {
        return startWork.getHour();
    }

    public void setStartHour(int startHour) {
        this.startWork.setHour(startHour);
    }

    public int getStartMin() {
        return startWork.getMinute();
    }

    public void setStartMin(int startMin) {
        this.startWork.setMinute(startMin);
    }

    public int getEndHour() {
        return endWork.getHour();
    }

    public void setEndHour(int endHour) {
        this.endWork.setHour(endHour);
    }

    public int getEndMin() {
        return endWork.getMinute();
    }

    public void setEndMin(int endMin) {
        this.endWork.setMinute(endMin);
    }


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
                    ", start: " + startWork.getHour() +
                    ":" + startWork.getMinute() +
                    ", end: " + endWork.getHour() +
                    ":" + endWork.getMinute() + "; ";
        } else {
            return "activeDay: " + activeDay;
        }

    }
}
