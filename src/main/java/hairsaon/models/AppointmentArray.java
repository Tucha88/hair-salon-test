package hairsaon.models;

import hairsaon.models.classes_for_master.Record;
import hairsaon.models.personal_models_for_schedule.Appointment;

import java.util.ArrayList;

/**
 * Created by Boris on 04.06.2017.
 */
public class AppointmentArray {
    private ArrayList<Appointment> records;

    public AppointmentArray() {
    }

    public ArrayList<Appointment> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<Appointment> records) {
        this.records = records;
    }
}
