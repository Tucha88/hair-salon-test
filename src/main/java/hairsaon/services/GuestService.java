package hairsaon.services;



import hairsaon.models.Master;
import hairsaon.models.MasterForArray;
import hairsaon.models.timetable.MasterArray;
import hairsaon.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by Boris on 10.05.2017.
 */
@RestController
@RequestMapping("guest")
public class GuestService {
    @Autowired
    private MasterRepository masterRepoMongo;



    @Autowired
    private MasterRepository masterRepository;


    @RequestMapping(value = "arraylist", method = RequestMethod.GET)
    public ResponseEntity<Object> getListMasters() {
        MasterArray masterArray = new MasterArray();
        ArrayList<MasterForArray> masters = new ArrayList<>();
        for (Master master :
                masterRepository.findAll()) {
            MasterForArray masterForArray = new MasterForArray();
            masterForArray.setEmail(master.getEmail());
            masterForArray.setAddresses(master.getAddresses().getAddress());
            masterForArray.setLang(master.getLang());
            masterForArray.setLastName(master.getLastName());
            masterForArray.setMasterType(master.getMasterType());
            masterForArray.setPhoneNumber(master.getPhoneNumber());
            masterForArray.setSerivce(master.getSerivce());
            masterForArray.setName(master.getName());
            masters.add(masterForArray);
        }
        masterArray.setMasters(masters);
        return new ResponseEntity<>(masterArray, HttpStatus.OK);
    }


}
