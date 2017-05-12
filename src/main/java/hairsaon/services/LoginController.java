package hairsaon.services;


import hairsaon.models.Client;
import hairsaon.models.Master;
import hairsaon.models.MasterAuthType;
import hairsaon.repository.ClientRepository;
import hairsaon.repository.MasterRepository;
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


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody MasterAuthType authType){
        if (authType == null || authType.getEmail().equals("") || authType.getPassword().equals("") || authType.getPassword() == null ||
                authType.getEmail() == null){
            return new ResponseEntity<>("Please fill in username and password", HttpStatus.UNAUTHORIZED);
        }else if (clientRepository.findClientByClientEmail(authType.getEmail()) != null){
            Client client = clientRepository.findClientByClientEmail(authType.getEmail());
            if (!client.getClientPassword().equals(authType.getPassword())) {
                return new ResponseEntity<Object>("Wrong password", HttpStatus.UNAUTHORIZED);
            }
            String jwtToken = Jwts.builder()
                    .setSubject(client.getClientEmail())
                    .claim("roles", "user")
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "ujhswljbnwygh2379633278uYYGHBGYG").compact();


            return new ResponseEntity<>("{\"token\":" + "\"" + jwtToken + "\"}", HttpStatus.OK);
        }else if (masterRepository.findByEmail(authType.getEmail()) != null){
            Master master = masterRepository.findByEmail(authType.getEmail());
            if (!master.getPassword().equals(authType.getPassword())) {
                return new ResponseEntity<Object>("Wrong password", HttpStatus.UNAUTHORIZED);
            }
            String jwtToken = Jwts.builder()
                    .setSubject(master.getEmail())
                    .claim("roles", "user")
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "ujhswljbnwygh2379633278uYYGHBGYG").compact();

            return new ResponseEntity<>("{\"token\":" + "\"" + jwtToken + "\"}", HttpStatus.OK);
        }
        return new ResponseEntity<>("Please register",HttpStatus.UNAUTHORIZED);
    }



}
