package hairsaon.services;

import hairsaon.models.client.Client;
import hairsaon.models.client.ClientAuthType;
import hairsaon.repository.client.IClientRepo;
import hairsaon.models.master.Master;
import hairsaon.models.master.MasterAuthType;
import hairsaon.repository.master.IMasterRepo;
import hairsaon.repository.master.MasterRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    private IClientRepo clientRepo;
    @Autowired
    private IMasterRepo masterRepo;
    @Autowired
    MasterRepository masterRepository;


    @PostMapping("/master")
    public String loginMaster(@RequestBody MasterAuthType authType) throws ServletException {
//
//        if (authType.getEmail().equals("") || authType.getEmail() == null || authType.getPassword().equals("") || authType.getPassword() == null){
//            return new ResponseEntity<Master>(HttpStatus.NOT_ACCEPTABLE);// Wrong login or password
//        }
//        Master master = masterRepo.loginMaster(authType);
//        if (master == null) {
//            return new ResponseEntity<Master>(HttpStatus.NOT_ACCEPTABLE);
//        }
//
//        return new ResponseEntity<Master>(master, HttpStatus.OK);

        String jwtToken = "";

        if (authType.getEmail() == null || authType.getPassword() == null) {
            throw new ServletException("Please fill in username and password");
        }

        String email = authType.getEmail();
        String password = authType.getPassword();

        Master master = masterRepository.findByEmail(email);

        if (master == null) {
            throw new ServletException("User name not found.");
        }

        String pwd = master.getPassword();

        if (!password.equals(pwd)) {
            throw new ServletException("Invalid login. Please check your name and password.");
        }

        jwtToken = Jwts.builder().setSubject(email).claim("roles", "user").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "ujhswljbnwygh2379633278uYYGHBGYG").compact();

        return jwtToken;


    }
    @PostMapping("/client")
    public ResponseEntity<Client> liginClient(@RequestBody ClientAuthType authType){
        if (authType.getClientEmail().equals("") || authType.getClientEmail() == null || authType.getClientPassword().
                equals("") || authType.getClientPassword() == null){
            return new ResponseEntity<Client>(HttpStatus.NOT_ACCEPTABLE);// Wrong login or password
        }
        Client client = clientRepo.loginClient(authType);
        if (client == null) {
            return new ResponseEntity<Client>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

}
