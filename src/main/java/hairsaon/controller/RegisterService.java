package hairsaon.controller;


import hairsaon.models.Client;
import hairsaon.models.Master;
import hairsaon.repository.ClientRepository;
import hairsaon.repository.MasterRepository;
import hairsaon.utils.IUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;

/**
 * Created by Boris on 06.04.2017.
 * Класс контроллер для регистрации мастера и клиента
 */
@RestController
@CrossOrigin
@RequestMapping("/register")
public class RegisterService {


    private final ClientRepository clientRepository;
    private final IUtils utils;
    private final MasterRepository masterRepository;

    @Autowired
    public RegisterService(ClientRepository clientRepository, IUtils utils, MasterRepository masterRepository) {
        this.clientRepository = clientRepository;
        this.masterRepository = masterRepository;
        this.utils = utils;
    }

    @PostMapping("/master")
    public ResponseEntity<Object> registerMaster(@RequestBody Master master) throws ServletException {

        if (utils.isLoginInfoExist(master)) {
            return new ResponseEntity<>("Please fill in username and password", HttpStatus.CONFLICT);
        }
        else  if (masterRepository.findByEmail(master.getEmail())!=null){
            return new ResponseEntity<>("This user already exists", HttpStatus.CONFLICT);
        }
        else if (clientRepository.findClientByClientEmail(master.getEmail()) != null) {
            return new ResponseEntity<>("This user already exists", HttpStatus.CONFLICT); // Found same login
        }
        String str = utils.hashPassword(master.getPassword());
        master.setPassword(str);
        masterRepository.save(master);


        return new ResponseEntity<>("{\"token\":" + "\"" + utils.buildJwt(master.getEmail()) + "\"}", HttpStatus.OK);
    }

    @PostMapping("client")
    public ResponseEntity<Object> registerClient(@RequestBody Client client) {
        if (utils.isLoginInfoExist(client)){
            return new ResponseEntity<>("Enter correct login or password", HttpStatus.CONFLICT);// Wrong login or password
        }

        else if (clientRepository.findClientByClientEmail(client.getClientEmail()) != null) {
            return new ResponseEntity<>("This user already exists", HttpStatus.CONFLICT); // Found same login
        }
        else if (masterRepository.findByEmail(client.getClientEmail())!=null){
            return new ResponseEntity<>("This user already exists", HttpStatus.CONFLICT);

        }

        String str = utils.hashPassword(client.getClientPassword());
        client.setClientPassword(str);
        clientRepository.save(client);

        return new ResponseEntity<>("{\"token\":" + "\"" + utils.buildJwt(client.getClientEmail()) + "\"}", HttpStatus.OK);
    }

}
