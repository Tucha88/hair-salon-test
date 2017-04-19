package hairsaon.services;

import hairsaon.models.Client;
import hairsaon.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Boris on 17.04.2017.
 */

@RestController
@CrossOrigin
@RequestMapping("client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("info")
    public ResponseEntity<Object> getClientInfo(@RequestHeader("authorization") String token) {
        Client client = clientRepository.findClientByToken(token);

        System.out.println(token + client);
        if (client == null) {
            return new ResponseEntity<>("Such client was not found", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }


}
