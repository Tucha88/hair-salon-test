package hairsaon.services;

import hairsaon.models.client.Client;
import hairsaon.models.client.ClientAuthType;
import hairsaon.repository.client.ClientRepository;
import hairsaon.models.master.Master;
import hairsaon.models.master.MasterAuthType;
import hairsaon.repository.master.MasterRepository;
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
    public ResponseEntity<String> loginMaster(@RequestBody MasterAuthType authType) throws ServletException {
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
            return new ResponseEntity<>("Please fill in username and password", HttpStatus.CONFLICT);
        }

        String jwtToken = Jwts.builder().setSubject(email).claim("roles", "user").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "ujhswljbnwygh2379633278uYYGHBGYG").compact();

        return new ResponseEntity<>("{\"token\":" + "\"" + jwtToken + "\"}", HttpStatus.OK);


    }
//    @PostMapping("images")
//    public ResponseEntity<byte[]> getImages(@RequestBody byte[] images){
//
//    }

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
        Date date = new Date();

        String jwtToken = Jwts.builder().setSubject(client.getClientEmail() + client.getClientPassword()).claim("roles", "user").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "ujhswljbnwygh2379633278uYYGHBGYG").compact();
        return new ResponseEntity<>("{\"token\":" + "\"" + jwtToken + "\"}", HttpStatus.OK);
    }

}
