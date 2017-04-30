package hairsaon.services;

import hairsaon.models.Address;
import hairsaon.models.Master;
import hairsaon.models.Services;
import hairsaon.repository.MasterRepository;
import io.jsonwebtoken.Jwts;
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
        String email = Jwts.parser()
                .setSigningKey("ujhswljbnwygh2379633278uYYGHBGYG")
                .parseClaimsJws(token)
                .getBody()
                .get("sub", String.class);


        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        ArrayList<Services> services = master.getSerivce();
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @PostMapping("services")
    public ResponseEntity<Object> setMasterServices(@RequestHeader("authorization") String token, @RequestBody ArrayList<Services> services) {
        String email = Jwts.parser()
                .setSigningKey("ujhswljbnwygh2379633278uYYGHBGYG")
                .parseClaimsJws(token)
                .getBody()
                .get("sub", String.class);


        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        master.setSerivce(services);
        masterRepository.saveAndFlush(master);
        return new ResponseEntity<>("User services were updated", HttpStatus.OK);

    }

    @PostMapping("service")
    public ResponseEntity<Object> addMasterService(@RequestHeader("authorization") String token, @RequestBody Services service) {
        String email = Jwts.parser()
                .setSigningKey("ujhswljbnwygh2379633278uYYGHBGYG")
                .parseClaimsJws(token)
                .getBody()
                .get("sub", String.class);


        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        master.addServise(service);
        masterRepository.saveAndFlush(master);
        return new ResponseEntity<>(master, HttpStatus.OK);
    }


    @GetMapping("address")
    public ResponseEntity<Object> getMasterAddresses(@RequestHeader("authorization") String token) {
        String email = Jwts.parser()
                .setSigningKey("ujhswljbnwygh2379633278uYYGHBGYG")
                .parseClaimsJws(token)
                .getBody()
                .get("sub", String.class);


        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        String addresses = master.getAddressString();

        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @PostMapping("address")
    public ResponseEntity<Object> setMasterAddresses(@RequestHeader("authorization") String token, @RequestBody String addresses) {
        String email = Jwts.parser()
                .setSigningKey("ujhswljbnwygh2379633278uYYGHBGYG")
                .parseClaimsJws(token)
                .getBody()
                .get("sub", String.class);

        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        master.setAddresses(addresses);
        masterRepository.saveAndFlush(master);
        return new ResponseEntity<>("User addresses were updated", HttpStatus.OK);
    }




    @GetMapping("info")
    public ResponseEntity<Object> getMasterInfo(@RequestHeader("authorization") String token) {
        String email = Jwts.parser()
                .setSigningKey("ujhswljbnwygh2379633278uYYGHBGYG")
                .parseClaimsJws(token)
                .getBody()
                .get("sub", String.class);
        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(master, HttpStatus.OK);
    }

}
