package hairsaon.models;

import java.util.ArrayList;

/**
 * Created by Boris on 06.05.2017.
 */
public class MasterArray {
    private ArrayList<MasterForArray> masters = new ArrayList<>();

    public MasterArray() {

    }

    public MasterArray(ArrayList<MasterForArray> masters) {

        this.masters = masters;
    }

    public ArrayList<MasterForArray> getMasters() {
        return masters;
    }

    public void setMasters(ArrayList<MasterForArray> masters) {
        this.masters = masters;
    }
}
