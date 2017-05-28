package hairsaon.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by Boris on 09.04.2017.
 */

@Document
public class Services implements Serializable {
    private static final long serialVersionUID = 112234558L;
    @Id
    private String id;
    private String service;
    private String price;
    private String time;


    public Services() {
    }

    public String getId() {
        return id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
