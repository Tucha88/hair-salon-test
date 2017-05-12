package hairsaon.services;



import hairsaon.models.Master;
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
        MasterArray masterArray1 = new MasterArray();
        ArrayList<Master> masters = new ArrayList<>();
        for (Master master :
                masterRepository.findAll()) {
            masters.add(master);
        }
        masterArray1.setMasters(masters);
        return new ResponseEntity<>(masterArray1, HttpStatus.OK);
    }


}
