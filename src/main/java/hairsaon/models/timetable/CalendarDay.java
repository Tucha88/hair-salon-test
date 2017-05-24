package hairsaon.models.timetable;

import hairsaon.models.classes_for_master.Record;
import hairsaon.myExtends.LightCalendar;
import hairsaon.myExtends.LightClock;
import hairsaon.myExtends.MyCalendar;
import hairsaon.myExtends.MyClock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by Лимаренко on 28.04.2017.
 */

public class CalendarDay implements Serializable {

    LightCalendar myCalendar;
    LightClock startWork;
    LightClock endWork;
    boolean working; // рабочий ли день
    ArrayList<Record> records;

    public CalendarDay() {
        records = new ArrayList<Record>();
    }

    public CalendarDay(LightCalendar myCalendar, int startHour, int startMin, int endHour, int endMin, boolean working) {
        this.myCalendar = myCalendar;

        startWork = new LightClock(startHour, startMin);
        endWork = new LightClock(endHour, endMin);
        this.working = working;
        records = new ArrayList<Record>();
    }

    public CalendarDay(LightCalendar lightCalendar, WeekDay weekDay) {
        this.myCalendar = lightCalendar;
        this.startWork = weekDay.getStartWork();
        this.endWork = weekDay.getEndWork();
        this.working = weekDay.getActiveDay();
    }


    public LightCalendar getMyCalendar() {
        return myCalendar;
    }

    public void setMyCalendar(LightCalendar myCalendar) {
        this.myCalendar = myCalendar;
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

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public ArrayList<Record> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<Record> records) {
        this.records = records;
    }

    public void addRecord(Record tempRecord) {
        TreeSet<Record> treeSet = new TreeSet<Record>(records);
        treeSet.add(tempRecord);
        records = new ArrayList<Record>(treeSet);
    }

    public TreeSet<LightClock> getFreeTime(int durationService) {
        TreeSet<LightClock> treeSet = new TreeSet<LightClock>();
        LightClock timeStart;
        LightClock timeEnd;
        LightClock tempTime;

        int arrSize = records.size();


        if (arrSize == 0) {
            timeStart = new LightClock(this.startWork);
            timeEnd = new LightClock(this.endWork);
            this.addToTreeSet(treeSet, timeStart, timeEnd, durationService);

        } else {
            Record tempRecord = records.get(arrSize - 1);
            timeStart = new LightClock(tempRecord.getStarTime());
            timeStart.addMinute(tempRecord.getService().getDuration());
            timeEnd = new LightClock(this.endWork);
            this.addToTreeSet(treeSet, timeStart, timeEnd, durationService);

            if (records.size() > 1) {
                for (int i = arrSize - 1; i >= 1; i--) {
                    timeEnd = new LightClock(records.get(i).getStarTime());
                    tempTime = new LightClock(records.get(i - 1).getStarTime());
                    tempTime.addMinute(records.get(i - 1).getService().getDuration());
                    timeStart = new LightClock(tempTime);
                    this.addToTreeSet(treeSet, timeStart, timeEnd, durationService);

                }
            }
            timeStart = new LightClock(this.startWork);
            timeEnd = new LightClock(records.get(0).getStarTime());
            this.addToTreeSet(treeSet, timeStart, timeEnd, durationService);

        }
        return treeSet;
    }


    public void addToTreeSet(TreeSet<LightClock> treeSet, LightClock timeStart, LightClock timeEnd, int durationService) {
        MyClock tempTime = new MyClock(timeStart);
        tempTime.addMinute(durationService);
        MyClock tempEndTime = new MyClock(timeEnd);
        while (tempTime.getTimeInMillis() <= tempEndTime.getTimeInMillis()) {
            treeSet.add(new LightClock(timeStart));
            timeStart.addMinute(15);
            tempTime = new MyClock(timeStart);
            tempTime.addMinute(durationService);
        }
    }

    @Override
    public String toString() {
        if (working) {
            return /*"Date: " + myCalendar.toString() +*/
                    " работает с  " + startWork +
                            " по " + endWork + ";";

        } else {
            return /*"Date: " + myCalendar.toString() + */" - Не работает;";
        }

    }
}
