package hairsaon.services;

import hairsaon.models.Client;
import hairsaon.repository.ClientRepository;
import hairsaon.models.Master;
import hairsaon.repository.MasterRepository;
import hairsaon.utils.IUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Date;

/**
 * Created by Boris on 06.04.2017.
 * Класс контроллер для регистрации мастера и клиента
 */
@RestController
@CrossOrigin
@RequestMapping("/register")
public class RegisterService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private IUtils utils;

    @Autowired
    private MasterRepository masterRepository;


    @PostMapping("/master")
    public ResponseEntity<Object> registerMaster(@RequestBody Master master) throws ServletException {

        if (utils.isLoginInfoExist(master)) {
            return new ResponseEntity<>("Please fill in username and password", HttpStatus.CONFLICT);
        }
        if (masterRepository.findByEmail(master.getEmail())!=null){
            return new ResponseEntity<>("This user already exists", HttpStatus.CONFLICT);

        }
        masterRepository.save(master);

        String jwtToken = Jwts.builder().setSubject(master.getEmail())
                .claim("roles", "user")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "ujhswljbnwygh2379633278uYYGHBGYG")
                .compact();


        return new ResponseEntity<>("{\"token\":" + "\"" + jwtToken + "\"}", HttpStatus.OK);
    }

    @PostMapping("client")
    public ResponseEntity<Object> registerClient(@RequestBody Client client) {
        if (utils.isLoginInfoExist(client)){
            return new ResponseEntity<>("Enter correct login or password", HttpStatus.CONFLICT);// Wrong login or password
        }

        if (clientRepository.findClientByClientEmail(client.getClientEmail()) != null) {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT); // Found same login
        }
        String jwtToken = Jwts.builder()
                .setSubject(client.getClientEmail())
                .claim("roles", "user")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "ujhswljbnwygh2379633278uYYGHBGYG")
                .compact();

        clientRepository.save(client);

        return new ResponseEntity<>("{\"token\":" + "\"" + jwtToken + "\"}", HttpStatus.OK);
    }

}
