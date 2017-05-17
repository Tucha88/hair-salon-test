package hairsaon.controller;


import hairsaon.models.Client;
import hairsaon.repository.ClientRepository;
import hairsaon.utils.IUtils;
import io.jsonwebtoken.Jwts;
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
    @Autowired
    private IUtils utils;

    @GetMapping("info")
    public ResponseEntity<Object> getClientInfo(@RequestHeader("authorization") String token) {
        String email = utils.parsJwts(token);



        Client client = clientRepository.findClientByClientEmail(email);

        System.out.println(token + client);
        if (client == null) {
            return new ResponseEntity<>("Such client was not found", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<Object> updateClient(@RequestHeader("authorization")String token,@RequestBody Client client) {
        String email = utils.parsJwts(token);

        Client updatedClient = clientRepository.findClientByClientEmail(email);
        if (updatedClient == null) {
            return new ResponseEntity<>("Such user does not exist", HttpStatus.CONFLICT);
        }
        updatedClient.setClientName(client.getClientName());
        updatedClient.setClientLastName(client.getClientLastName());
        updatedClient.setClientPhoneNumber(client.getClientPhoneNumber());


        clientRepository.save(updatedClient);
        return new ResponseEntity<>("Client was updated", HttpStatus.OK);
    }


    @RequestMapping(value = "clients", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllClients() {
        return new ResponseEntity<>(clientRepository.findAll(), HttpStatus.OK);
    }



}
