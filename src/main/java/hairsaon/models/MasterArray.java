package hairsaon.models;

import java.util.ArrayList;

/**
 * Created by Boris on 06.05.2017.
 */
public class MasterArray {
    private ArrayList<Master> masters = new ArrayList<>();

    public MasterArray() {

    }

    public MasterArray(ArrayList<Master> masters) {

        this.masters = masters;
    }

    public ArrayList<Master> getMasters() {
        return masters;
    }

    public void setMasters(ArrayList<Master> masters) {
        this.masters = masters;
    }
}
