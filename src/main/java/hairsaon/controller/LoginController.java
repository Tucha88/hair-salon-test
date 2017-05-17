package hairsaon.controller;


import hairsaon.models.Client;
import hairsaon.models.Master;
import hairsaon.models.MasterAuthType;
import hairsaon.repository.ClientRepository;
import hairsaon.repository.MasterRepository;
import hairsaon.utils.IUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Boris on 05.04.2017.
 * Класс контролер Логина мастера и клиента
 */
@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private MasterRepository masterRepository;
    @Autowired
    private IUtils utils;


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody MasterAuthType authType){
        if (authType == null || authType.getEmail() == null || authType.getPassword() == null) {
            return new ResponseEntity<>("Error, there is no auth info", HttpStatus.UNAUTHORIZED);
        }
        if (authType.getEmail().equals("") || authType.getPassword().equals("")) {
            return new ResponseEntity<>("Please fill in username and password", HttpStatus.UNAUTHORIZED);
        }else if (clientRepository.findClientByClientEmail(authType.getEmail()) != null){
            Client client = clientRepository.findClientByClientEmail(authType.getEmail());
            if (!client.getClientPassword().equals(authType.getPassword())) {
                return new ResponseEntity<>("Wrong password", HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>("{\"token\":" + "\"" + utils.buildJwt(client.getClientEmail()) + "\"}", HttpStatus.OK);


        }else if (masterRepository.findByEmail(authType.getEmail()) != null){


            Master master = masterRepository.findByEmail(authType.getEmail());
            if (!master.getPassword().equals(authType.getPassword())) {
                return new ResponseEntity<>("Wrong password", HttpStatus.UNAUTHORIZED);
            }


            return new ResponseEntity<>("{\"token\":" + "\"" + utils.buildJwt(master.getEmail()) + "\"}", HttpStatus.OK);
        }
        return new ResponseEntity<>("Please register",HttpStatus.UNAUTHORIZED);
    }



}
