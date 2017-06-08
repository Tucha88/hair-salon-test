package hairsaon.controller;


import hairsaon.models.Client;
import hairsaon.models.Master;
import hairsaon.models.MasterAuthType;
import hairsaon.repository.ClientRepository;
import hairsaon.repository.MasterRepository;
import hairsaon.utils.IUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Boris on 05.04.2017.
 * Класс контролер Логина мастера и клиента
 */
@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    private final ClientRepository clientRepository;
    private final MasterRepository masterRepository;
    private final IUtils utils;

    @Autowired
    public LoginController(ClientRepository clientRepository, IUtils utils, MasterRepository masterRepository) {
        this.clientRepository = clientRepository;
        this.masterRepository = masterRepository;
        this.utils = utils;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody MasterAuthType authType){
        if (authType == null || authType.getEmail() == null || authType.getPassword() == null) {
            return new ResponseEntity<>("Error, there is no auth info", HttpStatus.UNAUTHORIZED);
        }
        if (authType.getEmail().equals("") || authType.getPassword().equals("")) {
            return new ResponseEntity<>("Please fill in username and password", HttpStatus.UNAUTHORIZED);
        }else if (clientRepository.findClientByClientEmail(authType.getEmail()) != null){
            Client client = clientRepository.findClientByClientEmail(authType.getEmail());
            if (!utils.isPasswordCorrect(authType.getPassword(), client.getClientPassword())) {
                return new ResponseEntity<>("Wrong password", HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>("{\"token\":" + "\"" + utils.buildJwt(client.getClientEmail()) + "\"}", HttpStatus.OK);


        }else if (masterRepository.findByEmail(authType.getEmail()) != null){


            Master master = masterRepository.findByEmail(authType.getEmail());
            if (!utils.isPasswordCorrect(authType.getPassword(), master.getPassword())) {
                return new ResponseEntity<>("Wrong password", HttpStatus.UNAUTHORIZED);
            }


            return new ResponseEntity<>("{\"token\":" + "\"" + utils.buildJwt(master.getEmail()) + "\"}", HttpStatus.OK);
        }
        return new ResponseEntity<>("Please register",HttpStatus.UNAUTHORIZED);
    }



}
