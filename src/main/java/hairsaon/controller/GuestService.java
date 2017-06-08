package hairsaon.controller;



import hairsaon.models.Master;
import hairsaon.models.MasterArray;
import hairsaon.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by Boris on 10.05.2017.
 */
@CrossOrigin
@RestController
@RequestMapping("guest")
public class GuestService {

    private MasterRepository masterRepository;

    @Autowired
    public GuestService(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<Object> getListMasters() {
        MasterArray masterArray = new MasterArray();
        ArrayList<Master> masters = new ArrayList<>();
        for (Master master : masterRepository.findAll()) {
            masters.add(master);
        }
        masterArray.setMasters(masters);
        return new ResponseEntity<>(masterArray, HttpStatus.OK);
    }


}
