package hairsaon.models.timetable;

import hairsaon.models.Master;

import java.util.ArrayList;

/**
 * Created by Boris on 06.05.2017.
 */
public class MasterArray {
    private ArrayList<Master> masters = new ArrayList<>();

    public ArrayList<Master> getMasters() {
        return masters;
    }

    public void setMasters(ArrayList<Master> masters) {
        this.masters = masters;
    }

    public MasterArray() {

    }

    public MasterArray(ArrayList<Master> masters) {

        this.masters = masters;
    }
}
