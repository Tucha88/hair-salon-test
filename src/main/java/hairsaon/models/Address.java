package hairsaon.models;

import java.io.Serializable;

/**
 * Created by Boris on 09.04.2017.
 */
public class Address implements Serializable {
    private static final long serialVersionUID = 112234550L;
    private String address;
    private String note;

    public Address() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
