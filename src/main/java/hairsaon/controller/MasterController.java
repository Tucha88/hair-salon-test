package hairsaon.controller;


import hairsaon.models.Master;
import hairsaon.models.Services;
import hairsaon.models.classes_for_master.AddressTemp;
import hairsaon.repository.MasterRepository;
import hairsaon.utils.IUtils;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boris on 19.04.2017.
 */
@RestController
@CrossOrigin
@RequestMapping("master")
public class MasterController {
    @Autowired
    private MasterRepository masterRepository;
    @Autowired
    private IUtils utils;



    @GetMapping("services")
    public ResponseEntity<Object> getMasterServices(@RequestHeader("Authorization") String token) {
        String email = utils.parsJwts(token);


        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        ArrayList<Services> services = master.getSerivce();
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @PostMapping("service")
    public ResponseEntity<Object> setMasterServices(@RequestHeader("Authorization") String token, @RequestBody ArrayList<Services> services) {
        String email = utils.parsJwts(token);


        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        master.setSerivce(services);
        masterRepository.save(master);
        return new ResponseEntity<>("Master controller were updated", HttpStatus.OK);

    }

    @PutMapping("service")
    public ResponseEntity<Object> addMasterService(@RequestHeader("Authorization") String token, @RequestBody Services service) {
        String email = utils.parsJwts(token);


        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        master.addServise(service);
        masterRepository.save(master);
        return new ResponseEntity<>("Service was added", HttpStatus.OK);
    }


    @GetMapping("address")
    public ResponseEntity<Object> getMasterAddresses(@RequestHeader("Authorization") String token) {
        String email = utils.parsJwts(token);


        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
//        AddressTemp addresses = master.getAddresses();

        return new ResponseEntity<>(master.getAddresses(), HttpStatus.OK);
    }

    @PutMapping("address")
    public ResponseEntity<Object> setMasterAddresses(@RequestHeader("Authorization") String token, @RequestBody String addresses) {
        String email = utils.parsJwts(token);

        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        master.setAddresses(addresses);
        masterRepository.save(master);
        return new ResponseEntity<>("User addresses were updated", HttpStatus.OK);
    }

    @GetMapping("info")
    public ResponseEntity<Object> getMasterInfo(@RequestHeader("Authorization") String token) {
        String email = utils.parsJwts(token);
        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(master, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<Object> updateMuster(@RequestHeader("Authorization") String token, @RequestBody Master master) {
        String email = utils.parsJwts(token);
        Master updatedMaster = masterRepository.findByEmail(email);

        if (updatedMaster == null) {
            return new ResponseEntity<>("master doesn't exist", HttpStatus.CONFLICT);
        }
        updatedMaster.setSerivce(master.getSerivce());
        updatedMaster.setPhoneNumber(master.getPhoneNumber());
        updatedMaster.setLastName(master.getLastName());
        updatedMaster.setName(master.getName());
        updatedMaster.setAddresses(master.getAddresses());
        updatedMaster.setLang(master.getLang());
        updatedMaster.setMasterType(master.getMasterType());
        masterRepository.save(updatedMaster);

        return new ResponseEntity<>("Master is updated", HttpStatus.OK);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<List<Master>> getAllMasters() {
        return new ResponseEntity<>(masterRepository.findAll(), HttpStatus.OK);
    }


}
