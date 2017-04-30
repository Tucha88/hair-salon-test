package hairsaon.models.timetable;

import hairsaon.models.classes_for_master.Record;
import hairsaon.myExtends.MyCalendar;
import hairsaon.myExtends.MyClock;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by Лимаренко on 28.04.2017.
 */
public class CalendarDay {
    MyCalendar myCalendar;
    MyClock startWork;
    MyClock endWork;
    boolean working; // рабочий ли день
    ArrayList<Record> records;


    public CalendarDay(MyCalendar myCalendar, int startHour, int startMin, int endHour, int endMin, boolean working) {
        this.myCalendar = myCalendar;
        startWork = new MyClock(startHour, startMin);
        endWork = new MyClock(endHour, endMin);
        this.working = working;
        records = new ArrayList<Record>();
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

    public MyCalendar getMyCalendar() {
        return myCalendar;
    }

    public void setMyCalendar(MyCalendar myCalendar) {
        this.myCalendar = myCalendar;
    }

    public MyClock getStartWork() {
        return startWork;
    }

    public void setStartWork(MyClock startWork) {
        this.startWork = startWork;
    }

    public MyClock getEndWork() {
        return endWork;
    }

    public void setEndWork(MyClock endWork) {
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

    public TreeSet<MyClock> getFreeTime(int durationService) {
        TreeSet<MyClock> treeSet = new TreeSet<MyClock>();
        MyClock timeStart;
        MyClock timeEnd;
        MyClock tempTime;

        int arrSize = records.size();


        if (arrSize == 0) {
            timeStart = new MyClock(this.startWork);
            timeEnd = new MyClock(this.endWork);
            this.addToTreeSet(treeSet, timeStart, timeEnd, durationService);

        } else {
            Record tempRecord = records.get(arrSize - 1);
            timeStart = new MyClock(tempRecord.getStarTime());
            timeStart.addMinute(tempRecord.getService().getDuration());
            timeEnd = new MyClock(this.endWork);
            this.addToTreeSet(treeSet, timeStart, timeEnd, durationService);

            if (records.size() > 1) {
                for (int i = arrSize - 1; i >= 1; i--) {
                    timeEnd = new MyClock(records.get(i).getStarTime());
                    tempTime = new MyClock(records.get(i - 1).getStarTime());
                    tempTime.addMinute(records.get(i - 1).getService().getDuration());
                    timeStart = new MyClock(tempTime);
                    this.addToTreeSet(treeSet, timeStart, timeEnd, durationService);

                }
            }
            timeStart = new MyClock(this.startWork);
            timeEnd = new MyClock(records.get(0).getStarTime());
            this.addToTreeSet(treeSet, timeStart, timeEnd, durationService);

        }
        return treeSet;
    }


    public void addToTreeSet(TreeSet<MyClock> treeSet, MyClock timeStart, MyClock timeEnd, int durationService) {
        MyClock tempTime = new MyClock(timeStart);
        tempTime.addMinute(durationService);
        while (tempTime.getTimeInMillis() <= timeEnd.getTimeInMillis()) {
            treeSet.add(new MyClock(timeStart));
            timeStart.addMinute(15);
            tempTime = new MyClock(timeStart);
            tempTime.addMinute(durationService);
        }
    }

    @Override
    public String toString() {
        if (working) {
            return "Date: " + myCalendar.getTime() +
                    ", Работает с  " + startWork +
                    ", по " + endWork;

        } else {
            return "Date: " + myCalendar.getTime() + " - Не работает";
        }

    }
}
