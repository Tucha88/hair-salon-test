package hairsaon.services;

import hairsaon.models.Client;
import hairsaon.repository.ClientRepository;
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

    @GetMapping("info")
    public ResponseEntity<Object> getClientInfo(@RequestHeader("authorization") String token) {
        String email = Jwts.parser()
                .setSigningKey("ujhswljbnwygh2379633278uYYGHBGYG")
                .parseClaimsJws(token)
                .getBody()
                .get("sub", String.class);


        Client client = clientRepository.findClientByClientEmail(email);

        System.out.println(token + client);
        if (client == null) {
            return new ResponseEntity<>("Such client was not found", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity<Object> updateClietn(@RequestBody Client client) {
        Client updatedClient = clientRepository.findClientByClientEmail(client.getClientEmail());
        if (updatedClient == null) {
            return new ResponseEntity<>("Such user does not exist", HttpStatus.CONFLICT);
        }
        if (!client.getClientPassword().equals(updatedClient.getClientPassword())) {
            return new ResponseEntity<>("Wrong password", HttpStatus.CONFLICT);
        }
        updatedClient = client;
        clientRepository.saveAndFlush(updatedClient);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }


}