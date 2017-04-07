package hairsaon.services;

import hairsaon.models.client.Client;
import hairsaon.repository.client.ClientRepository;
import hairsaon.repository.client.IClientRepo;
import hairsaon.models.master.Master;
import hairsaon.repository.master.IMasterRepo;
import hairsaon.repository.master.MasterRepository;
import hairsaon.utils.IUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.Set;

/**
 * Created by Boris on 06.04.2017.
 */
@RestController
@CrossOrigin
@RequestMapping("/register")
public class RegisterServise{

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private IUtils utils;

    @Autowired
    private MasterRepository masterRepository;


    @PostMapping("/master")
    public ResponseEntity<String> registerMaster(@RequestBody Master master) throws ServletException {

        if (utils.isLoginInfoExist(master)) {
            return new ResponseEntity<>("Please fill in username and password", HttpStatus.UNAUTHORIZED);
        }


        if (masterRepository.findByEmail(master.getEmail())!=null){
            return new ResponseEntity<>("This user already exists", HttpStatus.UNAUTHORIZED);

        }
        masterRepository.save(master);

        String jwtToken = Jwts.builder().setSubject(master.getEmail() + master.getPassword()).claim("roles", "user").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "ujhswljbnwygh2379633278uYYGHBGYG").compact();
        return new ResponseEntity<>("{\"toketn\":" + "\"" + jwtToken + "\"}", HttpStatus.OK);
    }

    @PostMapping("client")
    public ResponseEntity<String> registerClient(@RequestBody Client client) {
        if (utils.isLoginInfoExist(client)){
            return new ResponseEntity<>("enter correct login or password", HttpStatus.NOT_ACCEPTABLE);// Wrong login or password
        }

        if (clientRepository.findClientByClientEmail(client.getClientEmail()) != null) {
            return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST); // Found same login
        }
        clientRepository.save(client);
        String jwtToken = Jwts.builder().setSubject(client.getClientEmail() + client.getClientPassword()).claim("roles", "user").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "ujhswljbnwygh2379633278uYYGHBGYG").compact();
        return new ResponseEntity<>("{\"toketn\":" + "\"" + jwtToken + "\"}", HttpStatus.OK);
    }

}
