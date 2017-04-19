package hairsaon.services;

import hairsaon.models.Client;
import hairsaon.models.ClientAuthType;
import hairsaon.repository.ClientRepository;
import hairsaon.models.Master;
import hairsaon.models.MasterAuthType;
import hairsaon.repository.MasterRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
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


    @PostMapping("/master")
    public ResponseEntity<Object> loginMaster(@RequestBody MasterAuthType authType) throws ServletException {
        if (authType.getEmail() == null || authType.getPassword() == null) {
            return new ResponseEntity<>("Please fill in username and password", HttpStatus.CONFLICT);
        }
        String email = authType.getEmail();
        String password = authType.getPassword();

        Master master = masterRepository.findByEmail(email);

        if (master == null) {
            return new ResponseEntity<>("User name not found.", HttpStatus.CONFLICT);
        }

        String pwd = master.getPassword();

        if (!password.equals(pwd)) {
            return new ResponseEntity<>("Please fillin username and password", HttpStatus.CONFLICT);
        }

        String jwtToken = Jwts.builder().setSubject(email).claim("roles", "user").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "ujhswljbnwygh2379633278uYYGHBGYG").compact();
        master.setToken("Bearer " + jwtToken);
        masterRepository.saveAndFlush(master);

        return new ResponseEntity<>("{\"token\":" + "\"" + jwtToken + "\"}", HttpStatus.OK);


    }

    @PostMapping("/client")
    public ResponseEntity<String> liginClient(@RequestBody ClientAuthType authType) {
        if (authType.getClientEmail().equals("") || authType.getClientEmail() == null || authType.getClientPassword().
                equals("") || authType.getClientPassword() == null) {
            return new ResponseEntity<>("Please fill in username and password", HttpStatus.CONFLICT);// Wrong login or password
        }
        Client client = clientRepository.findClientByClientEmail(authType.getClientEmail());
        if (client == null) {
            return new ResponseEntity<>("User name not found.", HttpStatus.CONFLICT);
        }
        if (!client.getClientPassword().equals(authType.getClientPassword())) {
            return new ResponseEntity<>("Password is incorrect.", HttpStatus.CONFLICT);
        }

        String jwtToken = Jwts.builder().setSubject(client.getClientEmail() + client.getClientPassword()).claim("roles", "user").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "ujhswljbnwygh2379633278uYYGHBGYG").compact();

        client.setToken("Bearer " + jwtToken);
        clientRepository.saveAndFlush(client);
        return new ResponseEntity<>("{\"token\":" + "\"" + jwtToken + "\"}", HttpStatus.OK);
    }

}
