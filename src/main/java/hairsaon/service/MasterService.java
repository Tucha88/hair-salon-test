package hairsaon.service;

import hairsaon.models.Master;
import hairsaon.models.MasterArray;

import java.util.List;

/**
 * Created by Boris on 15.05.2017.
 */
public interface MasterService {

    /**
     * сохраняет мастера
     **/
    public void saveMaster(Master master);


    void updateMaster(Master master);


    MasterArray getAll();


    Master findByEmail(String email);

    String getAddress(String email);


}
