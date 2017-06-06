package hairsaon.models;

import hairsaon.models.classes_for_master.Record;
import hairsaon.myExtends.LightCalendar;

/**
 * Created by Лимаренко on 07.06.2017.
 */
public class DateAndRecord {
    LightCalendar myCalendar;
    Record record;

    public DateAndRecord() {
    }

    public LightCalendar getMyCalendar() {
        return myCalendar;
    }

    public void setMyCalendar(LightCalendar myCalendar) {
        this.myCalendar = myCalendar;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
