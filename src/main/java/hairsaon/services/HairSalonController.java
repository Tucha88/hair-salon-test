package hairsaon.services;

import hairsaon.models.master.Master;
import hairsaon.repository.master.IMasterRepo;
import hairsaon.utils.IUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Boris on 01.04.2017.
 */

@Controller
@CrossOrigin
@RequestMapping("service")
public class HairSalonController {
    @Autowired
    private IUtils utils;
    @Autowired
    private IMasterRepo masterRepo;



    @PutMapping("update")
    public ResponseEntity<Master> updateMuster(@RequestBody Master master){
        if (!utils.isLoginInfoExist(master)){
            return new ResponseEntity<Master>(HttpStatus.NOT_ACCEPTABLE);// Wrong login or password
        }
        Master master1 = masterRepo.updateMasterInfo(master);
        if (master1 == null){
            return  new ResponseEntity<Master>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Master>(master1,HttpStatus.OK);
    }




}
