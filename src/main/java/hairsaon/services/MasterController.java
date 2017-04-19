package hairsaon.services;

import hairsaon.models.Adress;
import hairsaon.models.Master;
import hairsaon.models.Services;
import hairsaon.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by Boris on 19.04.2017.
 */
@RestController
@CrossOrigin
@RequestMapping("master")
public class MasterController {
    @Autowired
    private MasterRepository masterRepository;

    @GetMapping("services")
    public ResponseEntity<Object> getMasterServices(@RequestHeader("authorization") String token) {
        Master master = masterRepository.findMasterByToken(token);
        if (master == null) {
            return new ResponseEntity<Object>("there is no such master", HttpStatus.CONFLICT);
        }
        ArrayList<Services> services = master.getSerivce();
        return new ResponseEntity<Object>(services, HttpStatus.OK);
    }

    @PostMapping("services")
    public ResponseEntity<Object> setMasterServices(@RequestHeader("authorization") String token, @RequestBody ArrayList<Services> services) {
        Master master = masterRepository.findMasterByToken(token);
        if (master == null) {
            return new ResponseEntity<Object>("there is no such master", HttpStatus.CONFLICT);
        }
        master.setSerivce(services);
        masterRepository.saveAndFlush(master);
        return new ResponseEntity<Object>(HttpStatus.OK);

    }

    @GetMapping("address")
    private ResponseEntity<Object> getMasterAdresses(@RequestHeader("authorization") String token) {
        Master master = masterRepository.findMasterByToken(token);
        if (master == null) {
            return new ResponseEntity<Object>("there is no such master", HttpStatus.CONFLICT);
        }
        ArrayList<Adress> adresses = master.getAdress();

        return new ResponseEntity<Object>(adresses, HttpStatus.OK);
    }

    @PostMapping("address")
    public ResponseEntity<Object> setMasterAddresses(@RequestHeader("authorization") String token, @RequestBody ArrayList<Adress> adresses) {
        Master master = masterRepository.findMasterByToken(token);
        if (master == null) {
            return new ResponseEntity<Object>("there is no such master", HttpStatus.CONFLICT);
        }
        master.setAdress(adresses);
        masterRepository.saveAndFlush(master);
        return new ResponseEntity<Object>("User addresses were updated", HttpStatus.OK);
    }

    @PostMapping("info")
    public ResponseEntity<Object> getMasterInfo(@RequestHeader("authorization") String token) {
        Master master = masterRepository.findMasterByToken(token);
        if (master == null) {
            return new ResponseEntity<Object>("there is no such master", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Object>(master, HttpStatus.OK);
    }

}
