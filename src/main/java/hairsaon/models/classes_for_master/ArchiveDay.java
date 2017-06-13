package hairsaon.models.classes_for_master;

import java.util.ArrayList;

/**
 * Created by Лимаренко on 12.06.2017.
 */
public class ArchiveDay {

    ArrayList<Record> records = new ArrayList<Record>();

    public ArchiveDay() {
    }

    public ArchiveDay(ArrayList<Record> records) {
        this.records = new ArrayList<>(records);
    }

    public ArrayList<Record> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<Record> records) {
        this.records = records;
    }
}
