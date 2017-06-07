package hairsaon.models.personal_models_for_schedule;

import hairsaon.models.Services;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Boris on 05.06.2017.
 */
public class Appointment implements Serializable {
    private static final long serialVersionUID = 1122345561211L;
    @Id
    private String id;
    private Date starTime;
    private Date endTime;

    private ArrayList<Services> service;

    private String clientId;

    private String info;

    private String masterId;

    public Appointment() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStarTime() {
        return starTime;
    }

    public void setStarTime(Date starTime) {
        this.starTime = starTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public ArrayList<Services> getService() {
        return service;
    }

    public void setService(ArrayList<Services> service) {
        this.service = service;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }
}
