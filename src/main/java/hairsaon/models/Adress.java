package hairsaon.models;

import java.io.Serializable;

/**
 * Created by Boris on 09.04.2017.
 */
public class Adress implements Serializable {
    private static final long serialVersionUID = 112234550L;
    private String adress;
    private String note;

    public Adress() {
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
